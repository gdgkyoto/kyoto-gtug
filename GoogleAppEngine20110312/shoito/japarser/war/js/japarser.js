$(function() {
	$("#tabs").tabs({fx:{opacity:'toggle', duration:'normal'}});

    $('.facebook_like').socialbutton('facebook_like', {
    	button: 'box_count',
    });
    $('.facebook_share').socialbutton('facebook_share');
    $('.twitter').socialbutton('twitter', {
    	button: 'vertical',
    	via: 'shoito',
    	text: 'Japarser | An API for parsing Java source code.'
    });
    $('.evernote').socialbutton('evernote', {
    	button: 'site-mem-22',
    	styling: 'full'
    });
    $('.hatena').socialbutton('hatena');

	$('#parseButton').click(function() {
		$('#fileUpload').upload('/src', function(json) {
			var jsonString = JSON.stringify(json, null, $('#checkPretty').attr('checked') ? 4 : '');
			document.getElementById('output').innerText = jsonString;
		}, 'json');
	});
	
	$('#generateButton').click(function() {
		$('#classDiagram').hide();
		$('#loading').addClass('square').show().activity({segments: 8, width:2, space: 0, length: 3, color: '#666', speed: 1.5});
		$('#fileUploadForUml').upload('/src', function(json) {
			generateImage(json);
		}, 'json');
	});
	
	function getModifier(modifiersName) {
		var modifier = '';
		if(modifiersName.indexOf('public') > -1) {
			modifier = '+';
		} else if (modifiersName.indexOf('protected') > -1) {
			modifier = '#';
		} else if (modifiersName.indexOf('private') > -1) {
			modifier = '-';
		}
		return modifier;
	}
	
	function buildClassDef(className, fields, methods) {
		var classDef = '[' + className + '|' + fields + '|' + methods + ']';
		return classDef;
	}
	
	function generateImage(json) {
		var fields = '';
		$.each(json.fields, function() {
			fields += (getModifier(this.modifiersName) 
						+ this.name + ';')
						.replace(/\[/g, '\uFF3B').replace(/\]/g, '\uFF3D'); //u005B, u005D is NG
		});
		
		var methods = '';
		$.each(json.methods, function() {
			methods += (getModifier(this.modifiersName) 
						+ this.name + '(' + this.paramName + '):' 
						+ this.returnName + ';')
						.replace(/\[/g, '\uFF3B').replace(/\]/g, '\uFF3D');
		});

		var classDef = buildClassDef(json.name, fields, methods);
		
		var superClassDef = (json.extendsClasses != null) ? '[' + json.extendsClasses[0].name + ']^-' : '';
		
		var diagram = '';
		var interfaceDef = '';
		if (json.implementsInterfaces != null) {
			$.each(json.implementsInterfaces, function() {
				interfaceDef += ('[' + this.name + ']^-.-' + classDef + ',');
			});
		}
		diagram += interfaceDef;
		diagram += ((superClassDef !== '') ? superClassDef + classDef : '');
		diagram = (interfaceDef === '' && superClassDef === '') ? classDef : diagram;
		diagram = diagram.replace(/</g, '\u2039')
						.replace(/>/g, '\u203A')
						.replace(/,/g, '\u201A');

		console.log(diagram);
		
		var requestUrl = 'http://yuml.me/diagram/class/' + encodeURI(diagram);
		//var requestUrl = 'http://yuml.me/diagram/scruffy/class/' + encodeURI(diagram);
		var cd = $('#classDiagram');
		if (cd.length) {
			cd.attr('src', requestUrl);
			cd.attr('alt', json.name);
			cd.load(function() {
				$('#loading').hide();
				$(this).show();
			});
		} else {
			$('#usage').append('<img id="classDiagram" src="' + requestUrl + '" alt="' + json.name + '" />');
			$('#classDiagram').load(function() {
				$('#loading').hide();
				$(this).show();
			});
		}
	}
});