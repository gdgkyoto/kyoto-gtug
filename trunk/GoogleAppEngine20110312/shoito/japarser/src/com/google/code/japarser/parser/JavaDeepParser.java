package com.google.code.japarser.parser;

import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("rawtypes")
public class JavaDeepParser implements Parser<Map> {
	private static final Logger logger = Logger.getLogger(JavaDeepParser.class.getName());

	@SuppressWarnings("unchecked")
	public Map parse(InputStream is) throws IOException, ParseException {
		final CompilationUnit unit = japa.parser.JavaParser.parse(is);
		final List<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();
		final List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
		final Map ret = new HashMap();
		ret.put("fields", fields);
		ret.put("methods", methods);

		new VoidVisitorAdapter<Object>() {
			@Override
			public void visit(ClassOrInterfaceDeclaration node, Object arg) {
				if (ret.containsKey("classOrInterface")) {
					return;
				}
				
				ret.put("classOrInterface", node);
				super.visit(node, arg);
			}

			@Override
			public void visit(FieldDeclaration node, Object arg) {
				fields.add(node);
				super.visit(node, arg);
			}

			@Override
			public void visit(MethodDeclaration node, Object arg) {
				methods.add(node);
				super.visit(node, arg);
			}
		}.visit(unit, null);

		logger.log(Level.INFO, "Parse finished.");
		return ret;
	}
}