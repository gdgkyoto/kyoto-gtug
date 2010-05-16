	var MoveElmId="";
	var SelectedTextID="";
	var dblClick=false;
	var TextMode="make";
		function load(){
			var svg=document.getElementById("svg");
			var _body=document.getElementsByTagName("body")[0];
			with(svg){
				setAttribute("width",screen.width);
				setAttribute("height",screen.height);
			}
			svg.addEventListener("click",function(ev){
			var p=document.getElementById("textsetting").style.display;
			if(p=="none"){
				document.getElementById("textsetting").style.display="table-cell";
				document.getElementById("textform").style.display="table-cell";
				document.getElementById("inputtext").focus();
				var d=new Date();
				var text=document.createElementNS("http://www.w3.org/2000/svg","text");
				text.setAttribute("x",ev.layerX);
				text.setAttribute("y",ev.layerY);
				SelectedTextID=("text_"+d.getTime());
				text.id=SelectedTextID;
				svg.appendChild(text);
			}
			},false);
			svg.addEventListener("dragover",function(ev){
				
				ev.preventDefault();
				return false;
			},false);
			svg.addEventListener("drop",function(ev){
				
				
				var dt=ev.dataTransfer;
				var f=dt.files[0];
				if (f && f.type.match(/image.*/)) {
					var fr=new FileReader();
					fr.readAsDataURL(f);
					fr.onload=function(){
						var img=document.createElementNS("http://www.w3.org/2000/svg", "image");
						var imageobj=new Image();
						img.setAttribute("x",(ev.layerX));
						img.setAttribute("y",ev.layerY);
						imageobj.src = fr.result; //xlink:href
						img.setAttributeNS("http://www.w3.org/1999/xlink","xlink:href", fr.result);
						img.setAttribute("width",imageobj.width);
						img.setAttribute("height",imageobj.height);
						img.setAttribute("draggable","true");
						var d=new Date();
						img.setAttribute("id",("img_"+d.getTime()));
						
						img.addEventListener("dragenter",function(ev){
							MoveElmId=ev.target.id;
						},false);
						
						svg.appendChild(img);
					
					}
				}else if(dt.dropEffect==="move"){
					var elm=document.getElementById(MoveElmId);
					with(elm){
						setAttribute("x",(ev.layerX));
						setAttribute("y",ev.layerY);
					}
				}
				
				
				ev.stopPropagation();
				return false;
			},false);
			
			setLayout();
			
		}
		function setLayout(){
			var svg  = document.getElementById("svg");
			var width  = svg.getAttribute("width");
			var height = svg.getAttribute("height");
			var aspect = 4.0 / 3.0;;
			if(width > height * aspect){
				width = height * aspect;
				svg.setAttribute("width",width);
			}else{
				height = width / aspect;
				svg.setAttribute("height",height);
			}
			
		}
		function textMake(){
			if(TextMode==="make"){
			document.getElementById("textsetting").style.display="none";
			document.getElementById("textform").style.display="none";
			var set=true;
			with(document.getElementById(SelectedTextID)){
				textContent=document.getElementById("inputtext").value;
				setAttribute("draggable","true");
				addEventListener("dragenter",function(ev){
					if(set){
						MoveElmId=ev.target.id;
						set=false;
					}
				},false);
				addEventListener("mouseover",function(ev){
					SelectedTextID=ev.target.id;
					document.getElementById("changetextbutton").style.display="inline";
					with(document.getElementById("changetextbutton").style){
						position="relative";
						left=(document.getElementById(SelectedTextID).getAttribute("x").toString()+"px");
						top=(document.getElementById(SelectedTextID).getAttribute("y").toString()+"px");
					}
				},false);
				addEventListener("mouseout",function(ev){
					setTimeout(function(){
						document.getElementById("changetextbutton").style.display="none";
					},5000);
				},false)
			}
			}else if(TextMode==="change"){
				
			}
			document.getElementById("inputtext").value="";
			return false;
		}
		function changeText(){
			document.getElementById("textsetting").style.display="table-cell";
			document.getElementById("textform").style.display="table-cell";
			document.getElementById("inputtext").focus();
			TextMode="change";
		}