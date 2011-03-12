package com.google.code.japarser.parser;

import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.google.code.japarser.model.ClassInfo;
import com.google.code.japarser.model.FieldInfo;
import com.google.code.japarser.model.MethodInfo;

public class JavaParser implements Parser<ClassInfo> {
	private static final Logger logger = Logger.getLogger(JavaParser.class.getName());

	public ClassInfo parse(InputStream is) throws IOException, ParseException {
		final List<FieldInfo> fields = new ArrayList<FieldInfo>();
		final List<MethodInfo> methods = new ArrayList<MethodInfo>();
		final ClassInfo classInfo = new ClassInfo("", fields, methods);

		final CompilationUnit unit = japa.parser.JavaParser.parse(is);

		new VoidVisitorAdapter<Object>() {
			@Override
			public void visit(ClassOrInterfaceDeclaration node, Object arg) {
				if (StringUtils.isNotBlank(classInfo.getName())) {
					return;
				}

				classInfo.setLine(node.getBeginLine());
				String parameter = StringUtils.join(node.getTypeParameters(), ", ");
				String name = (parameter != null) ? node.getName() + "<" + parameter + ">" : node.getName();
				PackageDeclaration packageDec = unit.getPackage();
				classInfo.setQualifiedTypeName((packageDec != null) ? packageDec.getName() + "." + name : name);
				classInfo.setName(name);
				classInfo.setInterface(node.isInterface());
				
				copyExtendsInfo(node, classInfo);
				copyImplementsInfo(node, classInfo);

				super.visit(node, arg);
			}

			private void copyExtendsInfo(ClassOrInterfaceDeclaration node, final ClassInfo classInfo) {
				List<ClassOrInterfaceType> extendsTypes = node.getExtends();
				if (extendsTypes != null) {
					List<ClassInfo> extendsClasses = new ArrayList<ClassInfo>();
					for (ClassOrInterfaceType coi : extendsTypes) {
						List<Type> typeArgs = coi.getTypeArgs();
						String typeParameter = StringUtils.join(typeArgs, ", ");
						String superClassName = (typeParameter != null) ? coi.getName() + "<" + typeParameter + ">" : coi.getName();
						ClassInfo superClass = new ClassInfo(superClassName);
						superClass.setInterface(node.isInterface());
						extendsClasses.add(superClass);
					}
					classInfo.setExtendsClasses(extendsClasses);
				}
			}

			private void copyImplementsInfo(ClassOrInterfaceDeclaration node, final ClassInfo classInfo) {
				List<ClassOrInterfaceType> implementsTypes = node.getImplements();
				if (implementsTypes != null) {
					List<ClassInfo> implementsInterfaces = new ArrayList<ClassInfo>();
					for (ClassOrInterfaceType coi : implementsTypes) {
						List<Type> typeArgs = coi.getTypeArgs();
						String typeParameter = StringUtils.join(typeArgs, ", ");
						String interfaceName = (typeParameter != null) ? coi.getName() + "<" + typeParameter + ">" : coi.getName();
						ClassInfo implementsInterface = new ClassInfo(interfaceName);
						implementsInterface.setInterface(true);
						implementsInterfaces.add(implementsInterface);
					}
					classInfo.setImplementsInterfaces(implementsInterfaces);
				}
			}

			@Override
			public void visit(FieldDeclaration node, Object arg) {
				FieldInfo fieldInfo = new FieldInfo();
				fieldInfo.setLine(node.getBeginLine());
				fieldInfo.setModifiersName(Modifier.toString(node.getModifiers()));
				fieldInfo.setSimpleTypeName(node.getType().toString());
				fieldInfo.setQualifiedTypeName(node.getType().toString()); // TODO QualifiedTypeNameの取得
				VariableDeclarator variableDeclarator = node.getVariables().get(0);
				fieldInfo.setName(variableDeclarator.getId().getName());
				fields.add(fieldInfo);

				super.visit(node, arg);
			}

			@Override
			public void visit(MethodDeclaration node, Object arg) {
				MethodInfo methodInfo = new MethodInfo();
				methodInfo.setLine(node.getBeginLine());
				methodInfo.setModifiersName(Modifier.toString(node.getModifiers()));
				methodInfo.setReturnName(node.getType().toString());
				methodInfo.setName(node.getName().toString());
				String parameter = StringUtils.join(node.getParameters(), ", ");
				methodInfo.setParamName(parameter != null ? parameter : "");
				methods.add(methodInfo);

				super.visit(node, arg);
			}
		}.visit(unit, null);

		logger.log(Level.INFO, "Parse finished: " + classInfo.getName());
		return classInfo;
	}
}