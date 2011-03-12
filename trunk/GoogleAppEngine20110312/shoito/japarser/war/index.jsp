<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Japarser | An API for parsing Java source code.</title>
<link rel="shortcut icon" href="favicon.ico">
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/JavaScript" />
<meta name="keywords" content="Web,API,Java,GAE,source code">
<meta name="description" content="An API for parsing Java source code.">
<link type="text/css" href="css/style.css?v1" rel="stylesheet" />
<link type="text/css" href="css/faary.css?v1" rel="stylesheet" />
<link type="text/css" href="css/jquery-ui-1.8.10.custom.css?v1" rel="stylesheet" />
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.js"></script>
<script	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.10/jquery-ui.min.js"></script>
<script src="js/jquery.upload-1.0.2.js?v1"></script>
<script src="js/jquery.socialbutton-1.7.1.js?v1"></script>
<script src="js/jquery.activity-indicator-1.0.0.min.js?v1"></script>
<script src="js/japarser.js?v1"></script>
</head>
<body>
	<div class="container clearfix">
		<div id="header">
			<h1><span class="titlewrapper">{</span><span class="title">japarser</span><span class="titlewrapper">}</span></h1>
		</div>
		<div id="tabs" class="grid11 first">
			<ul>
				<li><a href="#intro">Intro</a>
				</li>
				<li><a href="#demo">Demo</a>
				</li>
				<li><a href="#usage">Usage example</a>
				</li>
				<li><a href="#about">About</a>
				</li>
			</ul>
			<div id="intro">
				<h2>An experimental API for parsing Java source code.</h2>
				<p>The Java source code is parsed, and the result is returned by the JSON form.</p>
				<h3>Request URL</h3>
				<p><a href="http://japarser.appspot.com/src">http://japarser.appspot.com/src</a></p>
				<hr>
				<h4>Method: GET</h4>
				<table>
				    <tbody>
				        <tr>
				            <th>Parameter</th>
				            <th>Type</th>
				            <th>Description</th>
				        </tr>
				        <tr>
				            <td>url(required)</td>
				            <td>string</td>
				            <td>URL of Java source code(.java)</td>
				        </tr>
				        <tr>
				            <td>pretty</td>
				            <td>boolean</td>
				            <td>the result is formatted in JSON that the readability is high.</td>
				        </tr>
				    </tbody>
				</table>
				<h5>Examples</h5>
				<div>
					<small>
						<ul>
							<li><a href="http://japarser.appspot.com/src?url=http://google-guice.googlecode.com/svn/trunk/core/src/com/google/inject/Key.java" target="_blank">http://japarser.appspot.com/src?url=http://google-guice.googlecode.com/svn/trunk/core/src/com/google/inject/Key.java</a></li>
							<li><a href="http://japarser.appspot.com/src?url=https://github.com/jenkinsci/jenkins/raw/master/core/src/main/java/hudson/ExtensionFinder.java" target="_blank">http://japarser.appspot.com/src?url=https://github.com/jenkinsci/jenkins/raw/master/core/src/main/java/hudson/ExtensionFinder.java</a></li>
							<li><a href="http://japarser.appspot.com/src?url=https://bitbucket.org/jmurty/jets3t/raw/da5139dc1f23/src/org/jets3t/service/model/StorageObject.java" target="_blank">http://japarser.appspot.com/src/?url=https://bitbucket.org/jmurty/jets3t/raw/da5139dc1f23/src/org/jets3t/service/model/StorageObject.java</a></li>
						</ul>
					</small>
				</div>
				<hr>
				<h4>Method: POST</h4>
				<table>
				    <tbody>
				        <tr>
				            <th>Parameter</th>
				            <th>Type</th>
				            <th>Description</th>
				        </tr>
				        <tr>
				            <td>file(require)</td>
				            <td>multipart/form-data</td>
				            <td>Java source code file(.java)</td>
				        </tr>
				        <tr>
				            <td>pretty</td>
				            <td>boolean</td>
				            <td>the result is formatted in JSON that the readability is high.</td>
				        </tr>
				    </tbody>
				</table>
				<h5>Examples</h5>
				<div>
					<small>
						<p>Please refer to <a href="#demo">Demo page</a>.</p>
					</small>
				</div>
			</div>
			<div id="demo" class="iform">
				<ul>
					<li class="iheader">Select .java file</li>
					<li><label>.java</label><input type="file" name="file" id="fileUpload" class="itext" /></li>
					<li><label for="CheckOption">JSON format</label>
					<ul>
						<li><input class="icheckbox" type="checkbox" name="CheckOption"
							id="checkPretty" value="pretty"><label for="checkPretty"
							class="ilabel">pretty</label></li>
					</ul>
					</li>
					<li><label>&nbsp;</label><input type="button" class="ibutton" id="parseButton" value="Start Parsing" /></li>
					<li class="iheader">Result</li>
					<li><textarea id="output" class="itextarea"></textarea></li>
				</ul>
			</div>
			<div id="usage" class="iform">
				<h2>ex1. JavaDoc to Class diagram. (<a href="http://dl.dropbox.com/u/227786/crx/java2class/java2class.crx">DL: Chrome Extensions</a>)</h2>
				<img src="img/javadoc2class_thumb.jpg" class="thumbnail" />
				<h2>ex2. Java to Class diagram. (<a href="http://dl.dropbox.com/u/227786/crx/java2class/java2class.crx">DL: Chrome Extensions</a>)</h2>
				<img src="img/java2class_thumb.jpg" class="thumbnail" />
				<h2>ex3. Generate Class diagram from Java source code.</h2>
				<ul>
					<li class="iheader">Select .java file</li>
					<li><label>.java</label><input type="file" name="file" id="fileUploadForUml" class="itext" /></li>
					<li><label>&nbsp;</label><input type="button" class="ibutton" id="generateButton" value="Generate UML" /></li>
				</ul>
				<div id="loading"></div>
			</div>
			<div id="about">
				<iframe src="http://about.me/shoito" width="100%" height="100%" seamless scrolling="no"></iframe>
			</div>
		</div>
		<div id="share" class="grid1">
			<div class="facebook_like"></div>
			<div class="twitter"></div>
			<div class="hatena"></div>
			<div class="facebook_share"></div>
			<div class="evernote"></div>
		</div>
		<div id="footer" class="grid12">
			<p id="copyright"><small>Copyright 2011 <a href="http://twitter.com/shoito">shoito</a> All Rights Reserved.</small></p>
		</div>
	</div>
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-677679-8']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
</body>
</html>
