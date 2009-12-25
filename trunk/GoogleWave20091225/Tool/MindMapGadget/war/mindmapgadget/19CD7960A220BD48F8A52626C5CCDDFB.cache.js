(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var ab='',hc=' ',Cb='#fff',bc=')',cc=',',lc=', Size: ',rb='0',ec='2d',jc=':',A='DOMMouseScroll',nc='Delete',xb='INPUT',kc='Index: ',E='MouseEvents',xc='Object;',uc='String;',mc='Submit',wc='Widget;',vc='[Lcom.google.gwt.user.client.ui.',sc='[Ljava.lang.',vb='align',fc='backgroundColor',q='blur',nb='bottom',db='button',Ab='canvas',tb='cellPadding',qb='cellSpacing',lb='center',r='change',eb='className',C='click',B='contextmenu',hb='dblclick',y='error',sb='focus',gc='g',fb='gwt-Button',Bb='gwt-Canvas',zb='gwt-TextBox',Fb='height',D='html',Db='keydown',ic='keypress',tc='keyup',F='left',yc='load',zc='losecapture',ob='middle',pc='moduleStartup',s='mousedown',t='mousemove',u='mouseout',v='mouseover',w='mouseup',z='mousewheel',qc='onModuleLoadStart',rc='org.kyotogtug.client.MindMapGadget',cb='position',dc='rgb(',ac='rgba(',mb='right',x='scroll',oc='startup',gb='submit',jb='table',kb='tbody',ub='td',yb='text',bb='top',pb='tr',ib='type',wb='verticalAlign',Eb='width';var _;function gp(a){return this===(a==null?null:a)}
function hp(){return this.$H||(this.$H=++jd)}
function ep(){}
_=ep.prototype={};_.eQ=gp;_.hC=hp;_.tM=zu;_.tI=1;function bd(b,a){return b.tM==zu||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function dd(a){return a.tM==zu||a.tI==2?a.hC():a.$H||(a.$H=++jd)}
var jd=0;function td(){td=zu;nd();new ld()}
function xd(a){var b=a.parentNode;if(b==null){return null}if(b.nodeType!=1)b=null;return b}
function kd(){}
_=kd.prototype=new ep();_.tI=0;function sd(){sd=zu;td()}
function rd(){}
_=rd.prototype=new kd();_.tI=0;function nd(){nd=zu;sd()}
function od(b){var d=b.relatedTarget;try{var c=d.nodeName;return d}catch(a){return null}}
function pd(b,a){return b===a||!!(b.compareDocumentPosition(a)&16)}
function ld(){}
_=ld.prototype=new rd();_.tI=0;function of(){}
_=of.prototype=new ep();_.tI=0;_.a=false;_.b=null;function df(a){Cl()}
function ef(b){var a;if(cf){a=new af();jg(b,a)}}
function ff(){return cf}
function af(){}
_=af.prototype=new of();_.n=df;_.r=ff;_.tI=0;var cf=null;function lf(){}
_=lf.prototype=new ep();_.tI=0;function qf(a){a.a=++tf;return a}
function sf(){return this.a}
function pf(){}
_=pf.prototype=new ep();_.hC=sf;_.tI=0;_.a=0;var tf=0;function fg(b,c,a){if(b.b>0){hg(b,yf(new xf(),b,c,a))}else{Ef(b.d,c,a)}return new lf()}
function hg(b,a){if(!b.a){b.a=ct(new bt())}et(b.a,a)}
function jg(c,a){var b;if(a.a){a.a=false;a.b=null}b=a.b;a.b=c.e;try{++c.b;ag(c.d,a,c.c)}finally{--c.b;if(c.b==0){kg(c)}}if(b==null){a.a=true;a.b=null}else{a.b=b}}
function kg(c){var a,b;if(c.a){try{for(b=zr(new xr(),c.a);b.a<b.b.D();){a=eh(Cr(b),2);Ef(a.a.d,a.c,a.b)}}finally{c.a=null}}}
function wf(){}
_=wf.prototype=new ep();_.tI=0;_.a=null;_.b=0;_.c=false;_.d=null;_.e=null;function yf(b,a,d,c){b.a=a;b.c=d;b.b=c;return b}
function xf(){}
_=xf.prototype=new ep();_.tI=7;_.a=null;_.b=null;_.c=null;function Df(a){a.a=qt(new pt());return a}
function Ef(c,d,a){var b;b=eh(lr(c.a,d),3);if(!b){b=ct(new bt());rr(c.a,d,b)}Eg(b.a,b.b++,a)}
function ag(i,e,h){var d,f,g,j,a,b,c;j=e.r();d=(a=eh(lr(i.a,j),3),!a?0:a.b);if(h){for(g=d-1;g>=0;--g){f=(b=eh(lr(i.a,j),3),eh((cs(g,b.b),b.a[g]),12));e.n(f)}}else{for(g=0;g<d;++g){f=(c=eh(lr(i.a,j),3),eh((cs(g,c.b),c.a[g]),12));e.n(f)}}}
function Bf(){}
_=Bf.prototype=new ep();_.tI=0;function ng(){}
_=ng.prototype=new ep();_.tI=0;function Bg(d,c){var a=new Array(c);if(d>0){var e=[null,0,false,[0,0]][d];for(var b=0;b<c;++b){a[b]=e}}return a}
function Cg(a,f,c,b,e){var d;d=Bg(e,b);sg();xg(d,tg,ug);d.tI=f;d.qI=c;return d}
function Eg(a,b,c){if(c!=null){if(a.qI>0&&!ch(c.tI,a.qI)){throw new mo()}if(a.qI<0&&(c.tM==zu||c.tI==2)){throw new mo()}}return a[b]=c}
function qg(){}
_=qg.prototype=new ep();_.tI=0;_.length=0;_.qI=0;function sg(){sg=zu;tg=[];ug=[];vg(new qg(),tg,ug)}
function vg(e,a,b){var c=0,f;for(var d in e){if(f=e[d]){a[c]=d;b[c]=f;++c}}}
function xg(a,c,d){sg();for(var e=0,b=c.length;e<b;++e){a[c[e]]=d[e]}}
var tg,ug;function dh(b,a){return b&&!!ph[b][a]}
function ch(b,a){return b&&ph[b][a]}
function eh(b,a){if(b!=null&&!ch(b.tI,a)){throw new qo()}return b}
var ph=[{},{},{1:1,8:1,9:1,10:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{2:1},{4:1},{6:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1,14:1},{12:1},{4:1,5:1,6:1,7:1,14:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{9:1},{8:1,13:1},{18:1},{18:1},{15:1},{15:1},{15:1},{16:1},{18:1},{3:1,8:1,16:1},{8:1,17:1},{8:1,18:1},{15:1},{8:1,13:1},{8:1,16:1},{8:1,16:1},{11:1}];function fi(b,a,c){var d;if(a==ji){if(rj((td(),b).type)==8192){ji=null}}d=ei;ei=b;try{c.y(b)}finally{ei=d}}
var ei=null,ji=null;function xi(a){Fi();return yi(cf?cf:(cf=qf(new pf())),a)}
function yi(b,a){return fg(Di(),b,a)}
function Ai(){if(zi){ef(Di())}}
function Bi(){var a;if(zi){a=(pi(),new ni());Ci(a);return null}return null}
function Ci(a){if(Ei){jg(Ei,a)}}
function Di(){if(!Ei){Ei=ui(new ti())}return Ei}
function Fi(){if(!zi){zj();zi=true}}
var zi=false,Ei=null;function pi(){pi=zu;qi=qf(new pf())}
function ri(a){null.F()}
function si(){return qi}
function ni(){}
_=ni.prototype=new of();_.n=ri;_.r=si;_.tI=0;var qi;function ui(a){a.d=Df(new Bf());a.e=null;a.c=false;return a}
function ti(){}
_=ti.prototype=new wf();_.tI=8;function rj(a){switch(a){case q:return 4096;case r:return 1024;case C:return 1;case hb:return 2;case sb:return 2048;case Db:return 128;case ic:return 256;case tc:return 512;case yc:return 32768;case zc:return 8192;case s:return 4;case t:return 64;case u:return 32;case v:return 16;case w:return 8;case x:return 16384;case y:return 65536;case z:return 131072;case A:return 131072;case B:return 262144;}}
function tj(){if(!vj){kj();fj();vj=true}}
function wj(a){return !(a!=null&&(a.tM!=zu&&a.tI!=2))&&(a!=null&&dh(a.tI,5))}
var vj=false;function kj(){oj=function(b){if(nj(b)){var a=mj;if(a&&a.__listener){if(wj(a.__listener)){fi(b,a,a.__listener);b.stopPropagation()}}}};nj=function(a){return true};pj=function(b){var c,a=this;while(a&&!(c=a.__listener)){a=a.parentNode}if(a&&a.nodeType!=1){a=null}if(c){if(wj(c)){fi(b,a,c)}}};$wnd.addEventListener(C,oj,true);$wnd.addEventListener(hb,oj,true);$wnd.addEventListener(s,oj,true);$wnd.addEventListener(w,oj,true);$wnd.addEventListener(t,oj,true);$wnd.addEventListener(v,oj,true);$wnd.addEventListener(u,oj,true);$wnd.addEventListener(z,oj,true);$wnd.addEventListener(Db,nj,true);$wnd.addEventListener(tc,nj,true);$wnd.addEventListener(ic,nj,true)}
function lj(c,a){var b=(c.__eventBits||0)^a;c.__eventBits=a;if(!b)return;if(b&1)c.onclick=a&1?pj:null;if(b&2)c.ondblclick=a&2?pj:null;if(b&4)c.onmousedown=a&4?pj:null;if(b&8)c.onmouseup=a&8?pj:null;if(b&16)c.onmouseover=a&16?pj:null;if(b&32)c.onmouseout=a&32?pj:null;if(b&64)c.onmousemove=a&64?pj:null;if(b&128)c.onkeydown=a&128?pj:null;if(b&256)c.onkeypress=a&256?pj:null;if(b&512)c.onkeyup=a&512?pj:null;if(b&1024)c.onchange=a&1024?pj:null;if(b&2048)c.onfocus=a&2048?pj:null;if(b&4096)c.onblur=a&4096?pj:null;if(b&8192)c.onlosecapture=a&8192?pj:null;if(b&16384)c.onscroll=a&16384?pj:null;if(b&32768)c.onload=a&32768?pj:null;if(b&65536)c.onerror=a&65536?pj:null;if(b&131072)c.onmousewheel=a&131072?pj:null;if(b&262144)c.oncontextmenu=a&262144?pj:null}
var mj=null,nj=null,oj=null,pj=null;function fj(){$wnd.addEventListener(u,function(b){var a=$wnd.__captureElem;if(a&&!b.relatedTarget){if(D==b.target.tagName.toLowerCase()){var c=$doc.createEvent(E);c.initMouseEvent(w,true,true,$wnd,0,b.screenX,b.screenY,b.clientX,b.clientY,b.ctrlKey,b.altKey,b.shiftKey,b.metaKey,b.button,null);a.dispatchEvent(c)}}},true);$wnd.addEventListener(A,oj,true)}
function hj(b,a){tj();lj(b,a);gj(b,a)}
function gj(b,a){if(a&131072){b.addEventListener(A,pj,false)}}
function zj(){var d=$wnd.onbeforeunload;var e=$wnd.onunload;$wnd.onbeforeunload=function(a){var c,b;try{c=Bi()}finally{b=d&&d(a)}if(c!=null){return c}if(b!=null){return b}};$wnd.onunload=function(a){try{Ai()}finally{e&&e(a);$wnd.onresize=null;$wnd.onscroll=null;$wnd.onbeforeunload=null;$wnd.onunload=null}}}
function om(){}
_=om.prototype=new ep();_.tI=9;_.j=null;function kn(b){var a;if(b.g){throw new Do()}b.g=true;b.j.__listener=b;a=b.h;b.h=-1;if(a>0){pn(b,a)}b.o();b.z()}
function ln(c,a){var b;switch(rj((td(),a).type)){case 16:case 32:b=od(a);if(!!b&&pd(c.j,b)){return}}}
function mn(a){if(!a.g){throw new Do()}try{a.A()}finally{a.p();a.j.__listener=null;a.g=false}}
function nn(a){if(!a.i){Bl();if(ir(am.a,a)){mn(a);ur(am.a,a)!=null}}else if(a.i){a.i.B(a)}else if(a.i){throw new Do()}}
function on(c,b){var a;a=c.i;if(!b){if(!!a&&a.g){mn(c)}c.i=null}else{if(a){throw new Do()}c.i=b;if(b.g){kn(c)}}}
function pn(b,a){if(b.h==-1){hj(b.j,a|(b.j.__eventBits||0))}else{b.h|=a}}
function qn(){}
function rn(){}
function sn(a){ln(this,a)}
function tn(){}
function un(){}
function ym(){}
_=ym.prototype=new om();_.o=qn;_.p=rn;_.y=sn;_.z=tn;_.A=un;_.tI=10;_.g=false;_.h=0;_.i=null;function ql(){var a,b;for(b=this.w();b.a<b.b.b-1;){a=Em(b);kn(a)}}
function rl(){var a,b;for(b=this.w();b.a<b.b.b-1;){a=Em(b);mn(a)}}
function sl(){}
function tl(){}
function ol(){}
_=ol.prototype=new ym();_.o=ql;_.p=rl;_.z=sl;_.A=tl;_.tI=11;function mk(c,a,b){nn(a);cn(c.f,a);b.appendChild(a.j);on(a,c)}
function ok(b,c){var a;if(c.i!=b){return false}on(c,null);a=c.j;xd((td(),a)).removeChild(a);hn(b.f,c);return true}
function pk(){return Cm(new Am(),this.f)}
function qk(a){return ok(this,a)}
function kk(){}
_=kk.prototype=new ol();_.w=pk;_.B=qk;_.tI=12;function Bj(a,b){mk(a,b,a.j)}
function Dj(a){a.style[F]=ab;a.style[bb]=ab;a.style[cb]=ab}
function Ej(b){var a;a=ok(this,b);if(a){Dj(b.j)}return a}
function Aj(){}
_=Aj.prototype=new kk();_.B=Ej;_.tI=13;function rk(){}
_=rk.prototype=new ym();_.tI=14;function bk(b,a){b.j=a;b.j.tabIndex=0;return b}
function ak(){}
_=ak.prototype=new rk();_.tI=15;function ek(a){bk(a,(td(),$doc).createElement(db));gk(a.j);a.j[eb]=fb;return a}
function gk(b){if(b.type==gb){try{b.setAttribute(ib,db)}catch(a){}}}
function Fj(){}
_=Fj.prototype=new ak();_.tI=16;function ik(a){a.f=bn(new zm());a.e=(td(),$doc).createElement(jb);a.d=$doc.createElement(kb);a.e.appendChild(a.d);a.j=a.e;return a}
function hk(){}
_=hk.prototype=new kk();_.tI=17;_.d=null;_.e=null;function Ak(){Ak=zu;yk(new xk(),lb);Ck=yk(new xk(),F);yk(new xk(),mb);Bk=Ck}
var Bk,Ck;function yk(b,a){b.a=a;return b}
function xk(){}
_=xk.prototype=new ep();_.tI=0;_.a=null;function dl(){dl=zu;bl(new al(),nb);bl(new al(),ob);el=bl(new al(),bb)}
var el;function bl(a,b){a.a=b;return a}
function al(){}
_=al.prototype=new ep();_.tI=0;_.a=null;function il(a){ik(a);a.a=(Ak(),Bk);a.c=(dl(),el);a.b=(td(),$doc).createElement(pb);a.d.appendChild(a.b);a.e[qb]=rb;a.e[tb]=rb;return a}
function jl(c,d){var b,a;b=(a=(td(),$doc).createElement(ub),(a[vb]=c.a.a,undefined),(a.style[wb]=c.c.a,undefined),a);c.b.appendChild(b);nn(d);cn(c.f,d);b.appendChild(d.j);on(d,c)}
function ml(c){var a,b;b=xd((td(),c.j));a=ok(this,c);if(a){this.b.removeChild(b)}return a}
function gl(){}
_=gl.prototype=new hk();_.B=ml;_.tI=18;_.b=null;function Bl(){Bl=zu;Fl=qt(new pt());am=ut(new tt())}
function Al(b,a){Bl();b.f=bn(new zm());b.j=a;kn(b);return b}
function Cl(){var b,a;Bl();var c,d;for(d=(b=nq(new mq(),As(am.a).b.a),ks(new js(),b));Br(d.a.a);){c=eh((a=eh(Cr(d.a.a),15),a.s()),7);if(c.g){mn(c)}}gr(am.a);gr(Fl)}
function El(a){Bl();var b;b=eh(lr(Fl,a),14);if(b){return b}if(Fl.d==0){xi(new vl())}b=yl(new xl());rr(Fl,a,b);vt(am,b);return b}
function ul(){}
_=ul.prototype=new Aj();_.tI=19;var Fl,am;function vl(){}
_=vl.prototype=new ep();_.tI=20;function zl(){zl=zu;Bl()}
function yl(a){zl();Al(a,$doc.body);return a}
function xl(){}
_=xl.prototype=new ul();_.tI=21;function km(a){var b;b=rj((td(),a).type);if((b&896)!=0){ln(this,a)}else{ln(this,a)}}
function im(){}
_=im.prototype=new rk();_.y=km;_.tI=22;function lm(b){var a;mm(b,(a=(td(),$doc).createElement(xb),a.type=yb,a),zb);return b}
function mm(c,a,b){c.j=a;c.j.tabIndex=0;if(b!=null){c.j[eb]=b}return c}
function hm(){}
_=hm.prototype=new im();_.tI=23;function tm(a){ik(a);a.a=(Ak(),Bk);a.b=(dl(),el);a.e[qb]=rb;a.e[tb]=rb;return a}
function um(c,e){var b,d,a;d=(td(),$doc).createElement(pb);b=(a=$doc.createElement(ub),(a[vb]=c.a.a,undefined),(a.style[wb]=c.b.a,undefined),a);d.appendChild(b);c.d.appendChild(d);nn(e);cn(c.f,e);b.appendChild(e.j);on(e,c)}
function xm(c){var a,b;b=xd((td(),c.j));a=ok(this,c);if(a){this.d.removeChild(xd(b))}return a}
function rm(){}
_=rm.prototype=new hk();_.B=xm;_.tI=24;function bn(a){a.a=Cg(sh,0,7,4,0);return a}
function cn(a,b){fn(a,b,a.b)}
function en(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function fn(d,e,a){var b,c;if(a<0||a>d.b){throw new ap()}if(d.b==d.a.length){c=Cg(sh,0,7,d.a.length*2,0);for(b=0;b<d.a.length;++b){Eg(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){Eg(d.a,b,d.a[b-1])}Eg(d.a,a,e)}
function gn(c,b){var a;if(b<0||b>=c.b){throw new ap()}--c.b;for(a=b;a<c.b;++a){Eg(c.a,a,c.a[a+1])}Eg(c.a,c.b,null)}
function hn(b,c){var a;a=en(b,c);if(a==-1){throw new iu()}gn(b,a)}
function zm(){}
_=zm.prototype=new ep();_.tI=0;_.a=null;_.b=0;function Cm(b,a){b.b=a;return b}
function Em(a){if(a.a>=a.b.b){throw new iu()}return a.b.a[++a.a]}
function Fm(){return this.a<this.b.b-1}
function an(){return Em(this)}
function Am(){}
_=Am.prototype=new ep();_.v=Fm;_.x=an;_.tI=0;_.a=-1;_.b=null;function fo(a){a.a=Bn(new An());a.j=(td(),$doc).createElement(Ab);Fn(a.a,a);a.j[eb]=Bb;io(a,Cb);a.a.c.setAttribute(Eb,ab+300);a.a.c.setAttribute(Fb,ab+150);return a}
function io(d,a){var b,c;if(a==null){throw new Bo()}a=Bp(a);if(a.indexOf(ac)==0){b=a.indexOf(bc,12);if(b>-1){c=zp(a.substr(5,b-5),cc,0);if(c.length>=3){a=dc+c[0]+cc+c[1]+cc+c[2]+bc}}}ao(d.a,a)}
function jo(a){if(!a){throw new Bo()}rj((td(),a).type)}
function yn(){}
_=yn.prototype=new ym();_.y=jo;_.tI=25;function Fn(b,a){b.c=a.j;b.b=b.c.getContext(ec)}
function ao(b,a){b.a=a;b.c.style[fc]=b.a}
function zn(){}
_=zn.prototype=new ep();_.tI=0;_.a=null;_.b=null;_.c=null;function Bn(a){(new mu()).a=ct(new bt());return a}
function An(){}
_=An.prototype=new zn();_.tI=0;function aq(){}
_=aq.prototype=new ep();_.tI=3;function zo(){}
_=zo.prototype=new aq();_.tI=4;function ip(){}
_=ip.prototype=new zo();_.tI=5;function mo(){}
_=mo.prototype=new ip();_.tI=27;function to(c,a){var b;b=new po();return b}
function po(){}
_=po.prototype=new ep();_.tI=0;function qo(){}
_=qo.prototype=new ip();_.tI=30;function Bo(){}
_=Bo.prototype=new ip();_.tI=31;function Do(){}
_=Do.prototype=new ip();_.tI=32;function bp(b,a){return b}
function ap(){}
_=ap.prototype=new ip();_.tI=33;function vp(b,a){if(!(a!=null&&dh(a.tI,1))){return false}return String(b)==a}
function zp(k,j,h){var a=new RegExp(j,gc);var i=[];var b=0;var l=k;var f=null;while(true){var g=a.exec(l);if(g==null||(l==ab||b==h-1&&h>0)){i[b]=l;break}else{i[b]=l.substring(0,g.index);l=l.substring(g.index+g[0].length,l.length);a.lastIndex=0;if(f==l){i[b]=l.substring(0,1);l=l.substring(1)}f=l;b++}}if(h==0){var e=i.length;while(e>0&&i[e-1]==ab){--e}if(e<i.length){i.splice(e,i.length-e)}}var d=Cg(uh,0,1,i.length,0);for(var c=0;c<i.length;++c){d[c]=i[c]}return d}
function Bp(c){if(c.length==0||c[0]>hc&&c[c.length-1]>hc){return c}var a=c.replace(/^(\s*)/,ab);var b=a.replace(/\s*$/,ab);return b}
function Ep(a){return vp(this,a)}
function Fp(){return rp(this)}
_=String.prototype;_.eQ=Ep;_.hC=Fp;_.tI=2;function mp(){mp=zu;np={};qp={}}
function op(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function rp(c){mp();var a=jc+c;var b=qp[a];if(b!=null){return b}b=np[a];if(b==null){b=op(c)}sp();return qp[a]=b}
function sp(){if(pp==256){np=qp;qp={};pp=0}++pp}
var np,pp=0,qp;function cq(){}
_=cq.prototype=new ip();_.tI=35;function gq(a,b){var c;while(a.v()){c=a.x();if(b==null?c==null:bd(b,c)){return a}}return null}
function iq(a){throw new cq()}
function jq(b){var a;a=gq(this.w(),b);return !!a}
function fq(){}
_=fq.prototype=new ep();_.l=iq;_.m=jq;_.tI=0;function As(b){var a;a=rq(new lq(),b);return ps(new is(),b,a)}
function Bs(c){var a,b,d,e,f;if((c==null?null:c)===this){return true}if(!(c!=null&&dh(c.tI,17))){return false}e=eh(c,17);if(eh(this,17).d!=e.d){return false}for(b=nq(new mq(),rq(new lq(),e).a);Br(b.a);){a=eh(Cr(b.a),15);d=a.s();f=a.t();if(!(d==null?eh(this,17).c:d!=null&&dh(d.tI,1)?nr(eh(this,17),eh(d,1)):mr(eh(this,17),d,~~dd(d)))){return false}if(!qu(f,d==null?eh(this,17).b:d!=null&&dh(d.tI,1)?eh(this,17).e[jc+eh(d,1)]:jr(eh(this,17),d,~~dd(d)))){return false}}return true}
function Cs(){var a,b,c;c=0;for(b=nq(new mq(),rq(new lq(),eh(this,17)).a);Br(b.a);){a=eh(Cr(b.a),15);c+=a.hC();c=~~c}return c}
function hs(){}
_=hs.prototype=new ep();_.eQ=Bs;_.hC=Cs;_.tI=0;function er(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.l(a[f])}}}}
function fr(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=cr(e,c.substring(1));a.l(b)}}}
function gr(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0}
function ir(b,a){return a==null?b.c:a!=null&&dh(a.tI,1)?nr(b,eh(a,1)):mr(b,a,~~dd(a))}
function lr(b,a){return a==null?b.b:a!=null&&dh(a.tI,1)?b.e[jc+eh(a,1)]:jr(b,a,~~dd(a))}
function jr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){return c.t()}}}return null}
function mr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){return true}}}return false}
function nr(b,a){return jc+a in b.e}
function rr(b,a,c){return a==null?pr(b,c):a!=null&&dh(a.tI,1)?qr(b,eh(a,1),c):or(b,a,c,~~dd(a))}
function or(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(i.q(g,d)){var h=c.t();c.C(j);return h}}}else{a=i.a[e]=[]}var c=bu(new au(),g,j);a.push(c);++i.d;return null}
function pr(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function qr(d,a,e){var b,c=d.e;a=jc+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function ur(b,a){return !a?tr(b):sr(b,a,~~(a.$H||(a.$H=++jd)))}
function sr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){if(a.length==1){delete h.a[e]}else{a.splice(f,1)}--h.d;return c.t()}}}return null}
function tr(b){var a;a=b.b;b.b=null;if(b.c){b.c=false;--b.d}return a}
function vr(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&bd(a,b)}
function kq(){}
_=kq.prototype=new hs();_.q=vr;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function Fs(b){var a,c,d;if((b==null?null:b)===this){return true}if(!(b!=null&&dh(b.tI,18))){return false}c=eh(b,18);if(c.D()!=this.D()){return false}for(a=c.w();a.v();){d=a.x();if(!this.m(d)){return false}}return true}
function at(){var a,b,c;a=0;for(b=this.w();b.v();){c=b.x();if(c!=null){a+=dd(c);a=~~a}}return a}
function Ds(){}
_=Ds.prototype=new fq();_.eQ=Fs;_.hC=at;_.tI=36;function rq(b,a){b.a=a;return b}
function tq(d,c){var a,b,e;if(c!=null&&dh(c.tI,15)){a=eh(c,15);b=a.s();if(ir(d.a,b)){e=lr(d.a,b);return st(a.t(),e)}}return false}
function uq(a){return tq(this,a)}
function vq(){return nq(new mq(),this.a)}
function wq(){return this.a.d}
function lq(){}
_=lq.prototype=new Ds();_.m=uq;_.w=vq;_.D=wq;_.tI=37;_.a=null;function nq(c,b){var a;c.b=b;a=ct(new bt());if(c.b.c){et(a,yq(new xq(),c.b))}fr(c.b,a);er(c.b,a);c.a=zr(new xr(),a);return c}
function pq(){return Br(this.a)}
function qq(){return eh(Cr(this.a),15)}
function mq(){}
_=mq.prototype=new ep();_.v=pq;_.x=qq;_.tI=0;_.a=null;_.b=null;function xs(b){var a;if(b!=null&&dh(b.tI,15)){a=eh(b,15);if(qu(this.s(),a.s())&&qu(this.t(),a.t())){return true}}return false}
function ys(){var a,b;a=0;b=0;if(this.s()!=null){a=dd(this.s())}if(this.t()!=null){b=dd(this.t())}return a^b}
function vs(){}
_=vs.prototype=new ep();_.eQ=xs;_.hC=ys;_.tI=38;function yq(b,a){b.a=a;return b}
function Aq(){return null}
function Bq(){return this.a.b}
function Cq(a){return pr(this.a,a)}
function xq(){}
_=xq.prototype=new vs();_.s=Aq;_.t=Bq;_.C=Cq;_.tI=39;_.a=null;function Eq(c,a,b){c.b=b;c.a=a;return c}
function ar(){return this.a}
function br(){return this.b.e[jc+this.a]}
function cr(b,a){return Eq(new Dq(),a,b)}
function dr(a){return qr(this.b,this.a,a)}
function Dq(){}
_=Dq.prototype=new vs();_.s=ar;_.t=br;_.C=dr;_.tI=40;_.a=null;_.b=null;function bs(a){this.k(this.D(),a);return true}
function as(b,a){throw new cq()}
function cs(a,b){if(a<0||a>=b){fs(a,b)}}
function ds(e){var a,b,c,d,f;if((e==null?null:e)===this){return true}if(!(e!=null&&dh(e.tI,16))){return false}f=eh(e,16);if(this.D()!=f.D()){return false}c=this.w();d=f.w();while(c.a<c.b.D()){a=Cr(c);b=Cr(d);if(!(a==null?b==null:bd(a,b))){return false}}return true}
function es(){var a,b,c;b=1;a=this.w();while(a.a<a.b.D()){c=Cr(a);b=31*b+(c==null?0:dd(c));b=~~b}return b}
function fs(a,b){throw bp(new ap(),kc+a+lc+b)}
function gs(){return zr(new xr(),this)}
function wr(){}
_=wr.prototype=new fq();_.l=bs;_.k=as;_.eQ=ds;_.hC=es;_.w=gs;_.tI=41;function zr(b,a){b.b=a;return b}
function Br(a){return a.a<a.b.D()}
function Cr(a){if(a.a>=a.b.D()){throw new iu()}return a.b.u(a.a++)}
function Dr(){return this.a<this.b.D()}
function Er(){return Cr(this)}
function xr(){}
_=xr.prototype=new ep();_.v=Dr;_.x=Er;_.tI=0;_.a=0;_.b=null;function ps(b,a,c){b.a=a;b.b=c;return b}
function ss(a){return ir(this.a,a)}
function ts(){var a;return a=nq(new mq(),this.b.a),ks(new js(),a)}
function us(){return this.b.a.d}
function is(){}
_=is.prototype=new Ds();_.m=ss;_.w=ts;_.D=us;_.tI=42;_.a=null;_.b=null;function ks(a,b){a.a=b;return a}
function ns(){return Br(this.a.a)}
function os(){var a;return a=eh(Cr(this.a.a),15),a.s()}
function js(){}
_=js.prototype=new ep();_.v=ns;_.x=os;_.tI=0;_.a=null;function ct(a){a.a=Cg(th,0,0,0,0);a.b=0;return a}
function et(b,a){Eg(b.a,b.b++,a);return true}
function dt(c,a,b){if(a<0||a>c.b){fs(a,c.b)}c.a.splice(a,0,b);++c.b}
function gt(b,a){cs(a,b.b);return b.a[a]}
function ht(c,b,a){for(;a<c.b;++a){if(qu(b,c.a[a])){return a}}return -1}
function jt(a){return Eg(this.a,this.b++,a),true}
function it(a,b){dt(this,a,b)}
function kt(a){return ht(this,a,0)!=-1}
function lt(a){return cs(a,this.b),this.a[a]}
function mt(){return this.b}
function bt(){}
_=bt.prototype=new wr();_.l=jt;_.k=it;_.m=kt;_.u=lt;_.D=mt;_.tI=43;_.a=null;_.b=0;function qt(a){gr(a);return a}
function st(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&bd(a,b)}
function pt(){}
_=pt.prototype=new kq();_.tI=44;function ut(a){a.a=qt(new pt());return a}
function vt(c,a){var b;b=rr(c.a,a,c);return b==null}
function zt(b){var a;return a=rr(this.a,b,this),a==null}
function At(a){return ir(this.a,a)}
function Bt(){var a;return a=nq(new mq(),As(this.a).b.a),ks(new js(),a)}
function Ct(){return this.a.d}
function tt(){}
_=tt.prototype=new Ds();_.l=zt;_.m=At;_.w=Bt;_.D=Ct;_.tI=45;_.a=null;function bu(b,a,c){b.a=a;b.b=c;return b}
function du(){return this.a}
function eu(){return this.b}
function gu(b){var a;a=this.b;this.b=b;return a}
function au(){}
_=au.prototype=new vs();_.s=du;_.t=eu;_.C=gu;_.tI=46;_.a=null;_.b=null;function iu(){}
_=iu.prototype=new ip();_.tI=47;function uu(a){return et(this.a,a)}
function tu(a,b){dt(this.a,a,b)}
function vu(a){return ht(this.a,a,0)!=-1}
function wu(a){return gt(this.a,a)}
function xu(){return zr(new xr(),this.a)}
function yu(){return this.a.b}
function ru(){}
_=ru.prototype=new wr();_.l=uu;_.k=tu;_.m=vu;_.u=wu;_.w=xu;_.D=yu;_.tI=48;_.a=null;function mu(){}
_=mu.prototype=new ru();_.tI=49;function qu(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&bd(a,b)}
function Au(){}
_=Au.prototype=new ng();_.tI=0;_.a=null;_.b=null;_.c=null;_.d=null;function Cu(l){var j,k;k=tm(new rm());j=il(new gl());l.a=fo(new yn());l.c=lm(new hm());l.d=ek(new Fj());l.b=ek(new Fj());(td(),l.d.j).textContent=mc;l.b.j.textContent=nc;um(k,l.a);um(k,j);jl(j,l.c);jl(j,l.d);jl(j,l.b);Bj((Bl(),El(null)),k);return l}
function Bu(){}
_=Bu.prototype=new Au();_.tI=0;function ko(){!!$stats&&$stats({moduleName:$moduleName,subSystem:oc,evtGroup:pc,millis:(new Date()).getTime(),type:qc,className:rc});Cu(new Bu())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{ko()}catch(a){b(d)}else{ko()}}
function zu(){}
var uh=to(sc,uc),sh=to(vc,wc),th=to(sc,xc);$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (mindmapgadget) mindmapgadget.onScriptLoad(gwtOnLoad);})();