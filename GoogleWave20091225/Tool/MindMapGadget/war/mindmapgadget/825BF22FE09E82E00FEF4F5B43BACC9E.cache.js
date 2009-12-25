(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var pb='',xc=' ',mc='#fff',zc='$',qc=')',rc=',',Cc=', Size: ',bc='0',uc='2d',Ac=':',D='DOMMouseScroll',Fc='Delete',gc='INPUT',Bc='Index: ',id='Object;',fd='String;',Dc='Submit',hd='Widget;',gd='[Lcom.google.gwt.user.client.ui.',ed='[Ljava.lang.',yc='\\',r='__gwt_initWindowCloseHandler',ec='align',vc='backgroundColor',C='blur',Cb='bottom',tb='button',kc='canvas',cc='cellPadding',ac='cellSpacing',Ab='center',hb='change',ub='className',sb='click',E='contextmenu',Db='dblclick',A='error',ic='focus',mb='function',nb='function ',wc='g',vb='gwt-Button',lc='gwt-Canvas',jc='gwt-TextBox',oc='height',tc='keydown',Ec='keypress',jd='keyup',ob='left',s='load',t='losecapture',Eb='middle',bd='moduleStartup',u='mousedown',v='mousemove',w='mouseout',x='mouseover',y='mouseup',B='mousewheel',cd='onModuleLoadStart',jb='onblur',F='onclick',lb='oncontextmenu',kb='ondblclick',ib='onfocus',eb='onkeydown',fb='onkeypress',gb='onkeyup',ab='onmousedown',cb='onmousemove',bb='onmouseup',db='onmousewheel',dd='org.kyotogtug.client.MindMapGadget',rb='position',sc='rgb(',pc='rgba(',Bb='right',q='script',z='scroll',ad='startup',wb='submit',yb='table',zb='tbody',dc='td',hc='text',qb='top',Fb='tr',xb='type',fc='verticalAlign',nc='width';var _;function yp(a){return this===(a==null?null:a)}
function zp(){return this.$H||(this.$H=++zd)}
function wp(){}
_=wp.prototype={};_.eQ=yp;_.hC=zp;_.tM=bv;_.tI=1;function rd(b,a){return b.tM==bv||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function td(a){return a.tM==bv||a.tI==2?a.hC():a.$H||(a.$H=++zd)}
var zd=0;function fe(){fe=bv;Dd();new Bd()}
function ie(a,c){var b;b=a.createElement(q);b.text=c;return b}
function Ad(){}
_=Ad.prototype=new wp();_.tI=0;function Fd(){Fd=bv;fe()}
function ce(b,a){return b===a||b.contains(a)}
function Ed(){}
_=Ed.prototype=new Ad();_.tI=0;var ee=null;function Dd(){Dd=bv;Fd()}
function Bd(){}
_=Bd.prototype=new Ed();_.tI=0;function fg(){}
_=fg.prototype=new wp();_.tI=0;_.a=false;_.b=null;function Bf(a){rm()}
function Cf(b){var a;if(Af){a=new yf();ah(b,a)}}
function Df(){return Af}
function yf(){}
_=yf.prototype=new fg();_.m=Bf;_.q=Df;_.tI=0;var Af=null;function cg(){}
_=cg.prototype=new wp();_.tI=0;function hg(a){a.a=++kg;return a}
function jg(){return this.a}
function gg(){}
_=gg.prototype=new wp();_.hC=jg;_.tI=0;_.a=0;var kg=0;function Cg(b,c,a){if(b.b>0){Eg(b,pg(new og(),b,c,a))}else{vg(b.d,c,a)}return new cg()}
function Eg(b,a){if(!b.a){b.a=wt(new vt())}yt(b.a,a)}
function ah(c,a){var b;if(a.a){a.a=false;a.b=null}b=a.b;a.b=c.e;try{++c.b;xg(c.d,a,c.c)}finally{--c.b;if(c.b==0){bh(c)}}if(b==null){a.a=true;a.b=null}else{a.b=b}}
function bh(c){var a,b;if(c.a){try{for(b=os(new ms(),c.a);b.a<b.b.b;){a=Bh(rs(b),2);vg(a.a.d,a.c,a.b)}}finally{c.a=null}}}
function ng(){}
_=ng.prototype=new wp();_.tI=0;_.a=null;_.b=0;_.c=false;_.d=null;_.e=null;function pg(b,a,d,c){b.a=a;b.c=d;b.b=c;return b}
function og(){}
_=og.prototype=new wp();_.tI=7;_.a=null;_.b=null;_.c=null;function ug(a){a.a=cu(new bu());return a}
function vg(c,d,a){var b;b=Bh(as(c.a,d),3);if(!b){b=wt(new vt());gs(c.a,d,b)}vh(b.a,b.b++,a)}
function xg(i,e,h){var d,f,g,j,a,b,c;j=e.q();d=(a=Bh(as(i.a,j),3),!a?0:a.b);if(h){for(g=d-1;g>=0;--g){f=(b=Bh(as(i.a,j),3),Bh((ws(g,b.b),b.a[g]),12));e.m(f)}}else{for(g=0;g<d;++g){f=(c=Bh(as(i.a,j),3),Bh((ws(g,c.b),c.a[g]),12));e.m(f)}}}
function sg(){}
_=sg.prototype=new wp();_.tI=0;function eh(){}
_=eh.prototype=new wp();_.tI=0;function sh(d,c){var a=new Array(c);if(d>0){var e=[null,0,false,[0,0]][d];for(var b=0;b<c;++b){a[b]=e}}return a}
function th(a,f,c,b,e){var d;d=sh(e,b);jh();oh(d,kh,lh);d.tI=f;d.qI=c;return d}
function vh(a,b,c){if(c!=null){if(a.qI>0&&!zh(c.tI,a.qI)){throw new Eo()}if(a.qI<0&&(c.tM==bv||c.tI==2)){throw new Eo()}}return a[b]=c}
function hh(){}
_=hh.prototype=new wp();_.tI=0;_.length=0;_.qI=0;function jh(){jh=bv;kh=[];lh=[];mh(new hh(),kh,lh)}
function mh(e,a,b){var c=0,f;for(var d in e){if(f=e[d]){a[c]=d;b[c]=f;++c}}}
function oh(a,c,d){jh();for(var e=0,b=c.length;e<b;++e){a[c[e]]=d[e]}}
var kh,lh;function Ah(b,a){return b&&!!gi[b][a]}
function zh(b,a){return b&&gi[b][a]}
function Bh(b,a){if(b!=null&&!zh(b.tI,a)){throw new cp()}return b}
var gi=[{},{},{1:1,8:1,9:1,10:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{2:1},{4:1},{6:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1,14:1},{12:1},{4:1,5:1,6:1,7:1,14:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{9:1},{8:1,13:1},{17:1},{17:1},{15:1},{15:1},{15:1},{17:1},{3:1,8:1},{8:1,16:1},{8:1,17:1},{15:1},{8:1,13:1},{11:1}];function Ci(b,a,c){var d;if(a==aj){if(ck((fe(),b).type)==8192){aj=null}}d=Bi;Bi=b;try{c.w(b)}finally{Bi=d}}
function Fi(a){return true}
function cj(a,b){ek();Ej(a,b)}
var Bi=null,aj=null;function pj(a){xj();return qj(Af?Af:(Af=hg(new gg())),a)}
function qj(b,a){return Cg(vj(),b,a)}
function sj(){if(rj){Cf(vj())}}
function tj(){var a;if(rj){a=(hj(),new fj());uj(a);return null}return null}
function uj(a){if(wj){ah(wj,a)}}
function vj(){if(!wj){wj=mj(new lj())}return wj}
function xj(){if(!rj){mk(lk(),r);rj=true}}
var rj=false,wj=null;function hj(){hj=bv;ij=hg(new gg())}
function jj(a){null.D()}
function kj(){return ij}
function fj(){}
_=fj.prototype=new fg();_.m=jj;_.q=kj;_.tI=0;var ij;function mj(a){a.d=ug(new sg());a.e=null;a.c=false;return a}
function lj(){}
_=lj.prototype=new ng();_.tI=8;function ck(a){switch(a){case C:return 4096;case hb:return 1024;case sb:return 1;case Db:return 2;case ic:return 2048;case tc:return 128;case Ec:return 256;case jd:return 512;case s:return 32768;case t:return 8192;case u:return 4;case v:return 64;case w:return 32;case x:return 16;case y:return 8;case z:return 16384;case A:return 65536;case B:return 131072;case D:return 131072;case E:return 262144;}}
function ek(){if(!gk){Dj();gk=true}}
var gk=false;function Dj(){ak=function(){var c=(Fd(),ee);ee=this;if($wnd.event.returnValue==null){$wnd.event.returnValue=true;if(!Fi($wnd.event)){ee=c;return}}var b,a=this;while(a&&!(b=a.__listener)){a=a.parentElement}if(b){if(!(b!=null&&(b.tM!=bv&&b.tI!=2))&&(b!=null&&Ah(b.tI,5))){Ci($wnd.event,a,b)}}ee=c};Fj=function(){var a=$doc.createEventObject();if($wnd.event.returnValue==null){$wnd.event.srcElement.fireEvent(F,a)}if(this.__eventBits&2){ak.call(this)}else if($wnd.event.returnValue==null){$wnd.event.returnValue=true;Fi($wnd.event)}};var e=function(){ak.call($doc.body)};var d=function(){Fj.call($doc.body)};$doc.body.attachEvent(F,e);$doc.body.attachEvent(ab,e);$doc.body.attachEvent(bb,e);$doc.body.attachEvent(cb,e);$doc.body.attachEvent(db,e);$doc.body.attachEvent(eb,e);$doc.body.attachEvent(fb,e);$doc.body.attachEvent(gb,e);$doc.body.attachEvent(ib,e);$doc.body.attachEvent(jb,e);$doc.body.attachEvent(kb,d);$doc.body.attachEvent(lb,e)}
function Ej(c,a){var b=(c.__eventBits||0)^a;c.__eventBits=a;if(!b)return;if(b&1)c.onclick=a&1?ak:null;if(b&3)c.ondblclick=a&3?Fj:null;if(b&4)c.onmousedown=a&4?ak:null;if(b&8)c.onmouseup=a&8?ak:null;if(b&16)c.onmouseover=a&16?ak:null;if(b&32)c.onmouseout=a&32?ak:null;if(b&64)c.onmousemove=a&64?ak:null;if(b&128)c.onkeydown=a&128?ak:null;if(b&256)c.onkeypress=a&256?ak:null;if(b&512)c.onkeyup=a&512?ak:null;if(b&1024)c.onchange=a&1024?ak:null;if(b&2048)c.onfocus=a&2048?ak:null;if(b&4096)c.onblur=a&4096?ak:null;if(b&8192)c.onlosecapture=a&8192?ak:null;if(b&16384)c.onscroll=a&16384?ak:null;if(b&32768)c.onload=a&32768?ak:null;if(b&65536)c.onerror=a&65536?ak:null;if(b&131072)c.onmousewheel=a&131072?ak:null;if(b&262144)c.oncontextmenu=a&262144?ak:null}
var Fj=null,ak=null;function lk(){return function(d,g){var h=window,e=h.onbeforeunload,f=h.onunload;h.onbeforeunload=function(a){var c,b;try{c=d()}finally{b=e&&e(a)}if(c!=null){return c}if(b!=null){return b}};h.onunload=function(a){try{g()}finally{f&&f(a);h.onresize=null;h.onscroll=null;h.onbeforeunload=null;h.onunload=null}};h.__gwt_initWindowCloseHandler=undefined}.toString()}
function mk(b,a){var c;b=lq(b,mb,nb+a);c=ie((fe(),$doc),b);$doc.body.appendChild(c);nk();$doc.body.removeChild(c)}
function nk(){$wnd.__gwt_initWindowCloseHandler(function(){return tj()},function(){sj()})}
function dn(){}
_=dn.prototype=new wp();_.tI=9;_.j=null;function Fn(b){var a;if(b.g){throw new pp()}b.g=true;b.j.__listener=b;a=b.h;b.h=-1;if(a>0){fo(b,a)}b.n();b.x()}
function ao(c,a){var b;switch(ck((fe(),a).type)){case 16:case 32:b=a.relatedTarget||(a.type==w?a.toElement:a.fromElement);if(!!b&&ce(c.j,b)){return}}}
function bo(a){if(!a.g){throw new pp()}try{a.y()}finally{a.o();a.j.__listener=null;a.g=false}}
function co(a){if(!a.i){qm();if(Dr(vm.a,a)){bo(a);js(vm.a,a)!=null}}else if(a.i){a.i.z(a)}else if(a.i){throw new pp()}}
function eo(c,b){var a;a=c.i;if(!b){if(!!a&&a.g){bo(c)}c.i=null}else{if(a){throw new pp()}c.i=b;if(b.g){Fn(c)}}}
function fo(b,a){if(b.h==-1){cj(b.j,a|(b.j.__eventBits||0))}else{b.h|=a}}
function go(){}
function ho(){}
function io(a){ao(this,a)}
function jo(){}
function ko(){}
function on(){}
_=on.prototype=new dn();_.n=go;_.o=ho;_.w=io;_.x=jo;_.y=ko;_.tI=10;_.g=false;_.h=0;_.i=null;function fm(){var a,b;for(b=this.u();b.a<b.b.b-1;){a=un(b);Fn(a)}}
function gm(){var a,b;for(b=this.u();b.a<b.b.b-1;){a=un(b);bo(a)}}
function hm(){}
function im(){}
function dm(){}
_=dm.prototype=new on();_.n=fm;_.o=gm;_.x=hm;_.y=im;_.tI=11;function bl(c,a,b){co(a);yn(c.f,a);b.appendChild(a.j);eo(a,c)}
function dl(b,c){var a;if(c.i!=b){return false}eo(c,null);a=c.j;(fe(),a).parentElement.removeChild(a);Dn(b.f,c);return true}
function el(){return sn(new qn(),this.f)}
function fl(a){return dl(this,a)}
function Fk(){}
_=Fk.prototype=new dm();_.u=el;_.z=fl;_.tI=12;function qk(a,b){bl(a,b,a.j)}
function sk(a){a.style[ob]=pb;a.style[qb]=pb;a.style[rb]=pb}
function tk(b){var a;a=dl(this,b);if(a){sk(b.j)}return a}
function pk(){}
_=pk.prototype=new Fk();_.z=tk;_.tI=13;function gl(){}
_=gl.prototype=new on();_.tI=14;function wk(b,a){b.j=a;b.j.tabIndex=0;return b}
function vk(){}
_=vk.prototype=new gl();_.tI=15;function zk(a){wk(a,(fe(),$doc).createElement(tb));Bk(a.j);a.j[ub]=vb;return a}
function Bk(b){if(b.type==wb){try{b.setAttribute(xb,tb)}catch(a){}}}
function uk(){}
_=uk.prototype=new vk();_.tI=16;function Dk(a){a.f=xn(new pn());a.e=(fe(),$doc).createElement(yb);a.d=$doc.createElement(zb);a.e.appendChild(a.d);a.j=a.e;return a}
function Ck(){}
_=Ck.prototype=new Fk();_.tI=17;_.d=null;_.e=null;function pl(){pl=bv;nl(new ml(),Ab);rl=nl(new ml(),ob);nl(new ml(),Bb);ql=rl}
var ql,rl;function nl(b,a){b.a=a;return b}
function ml(){}
_=ml.prototype=new wp();_.tI=0;_.a=null;function yl(){yl=bv;wl(new vl(),Cb);wl(new vl(),Eb);zl=wl(new vl(),qb)}
var zl;function wl(a,b){a.a=b;return a}
function vl(){}
_=vl.prototype=new wp();_.tI=0;_.a=null;function Dl(a){Dk(a);a.a=(pl(),ql);a.c=(yl(),zl);a.b=(fe(),$doc).createElement(Fb);a.d.appendChild(a.b);a.e[ac]=bc;a.e[cc]=bc;return a}
function El(c,d){var b,a;b=(a=(fe(),$doc).createElement(dc),(a[ec]=c.a.a,undefined),(a.style[fc]=c.c.a,undefined),a);c.b.appendChild(b);co(d);yn(c.f,d);b.appendChild(d.j);eo(d,c)}
function bm(c){var a,b;b=(fe(),c.j).parentElement;a=dl(this,c);if(a){this.b.removeChild(b)}return a}
function Bl(){}
_=Bl.prototype=new Ck();_.z=bm;_.tI=18;_.b=null;function qm(){qm=bv;um=cu(new bu());vm=gu(new fu())}
function pm(b,a){qm();b.f=xn(new pn());b.j=a;Fn(b);return b}
function rm(){var b,a;qm();var c,d;for(d=(b=cr(new br(),ot(vm.a).b.a),Es(new Ds(),b));qs(d.a.a);){c=Bh((a=Bh(rs(d.a.a),15),a.r()),7);if(c.g){bo(c)}}Br(vm.a);Br(um)}
function tm(a){qm();var b;b=Bh(as(um,a),14);if(b){return b}if(um.d==0){pj(new km())}b=nm(new mm());gs(um,a,b);hu(vm,b);return b}
function jm(){}
_=jm.prototype=new pk();_.tI=19;var um,vm;function km(){}
_=km.prototype=new wp();_.tI=20;function om(){om=bv;qm()}
function nm(a){om();pm(a,$doc.body);return a}
function mm(){}
_=mm.prototype=new jm();_.tI=21;function Fm(a){var b;b=ck((fe(),a).type);if((b&896)!=0){ao(this,a)}else{ao(this,a)}}
function Dm(){}
_=Dm.prototype=new gl();_.w=Fm;_.tI=22;function an(b){var a;bn(b,(a=(fe(),$doc).createElement(gc),a.type=hc,a),jc);return b}
function bn(c,a,b){c.j=a;c.j.tabIndex=0;if(b!=null){c.j[ub]=b}return c}
function Cm(){}
_=Cm.prototype=new Dm();_.tI=23;function jn(a){Dk(a);a.a=(pl(),ql);a.b=(yl(),zl);a.e[ac]=bc;a.e[cc]=bc;return a}
function kn(c,e){var b,d,a;d=(fe(),$doc).createElement(Fb);b=(a=$doc.createElement(dc),(a[ec]=c.a.a,undefined),(a.style[fc]=c.b.a,undefined),a);d.appendChild(b);c.d.appendChild(d);co(e);yn(c.f,e);b.appendChild(e.j);eo(e,c)}
function nn(c){var a,b;b=(fe(),c.j).parentElement;a=dl(this,c);if(a){this.d.removeChild(b.parentElement)}return a}
function gn(){}
_=gn.prototype=new Ck();_.z=nn;_.tI=24;function xn(a){a.a=th(ji,0,7,4,0);return a}
function yn(a,b){Bn(a,b,a.b)}
function An(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function Bn(d,e,a){var b,c;if(a<0||a>d.b){throw new sp()}if(d.b==d.a.length){c=th(ji,0,7,d.a.length*2,0);for(b=0;b<d.a.length;++b){vh(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){vh(d.a,b,d.a[b-1])}vh(d.a,a,e)}
function Cn(c,b){var a;if(b<0||b>=c.b){throw new sp()}--c.b;for(a=b;a<c.b;++a){vh(c.a,a,c.a[a+1])}vh(c.a,c.b,null)}
function Dn(b,c){var a;a=An(b,c);if(a==-1){throw new Au()}Cn(b,a)}
function pn(){}
_=pn.prototype=new wp();_.tI=0;_.a=null;_.b=0;function sn(b,a){b.b=a;return b}
function un(a){if(a.a>=a.b.b){throw new Au()}return a.b.a[++a.a]}
function vn(){return this.a<this.b.b-1}
function wn(){return un(this)}
function qn(){}
_=qn.prototype=new wp();_.t=vn;_.v=wn;_.tI=0;_.a=-1;_.b=null;function xo(a){a.a=new po();a.j=(fe(),$doc).createElement(kc);so(a.a,a);a.j[ub]=lc;Ao(a,mc);a.a.c.setAttribute(nc,pb+300);a.a.c.setAttribute(oc,pb+150);return a}
function Ao(d,a){var b,c;if(a==null){throw new np()}a=pq(a);if(a.indexOf(pc)==0){b=a.indexOf(qc,12);if(b>-1){c=mq(a.substr(5,b-5),rc,0);if(c.length>=3){a=sc+c[0]+rc+c[1]+rc+c[2]+qc}}}to(d.a,a)}
function Bo(a){if(!a){throw new np()}ck((fe(),a).type)}
function oo(){}
_=oo.prototype=new on();_.w=Bo;_.tI=25;function so(b,a){b.c=a.j;b.b=b.c.getContext(uc)}
function to(b,a){b.a=a;b.c.style[vc]=b.a}
function po(){}
_=po.prototype=new wp();_.tI=0;_.a=null;_.b=null;_.c=null;function vq(){}
_=vq.prototype=new wp();_.tI=3;function lp(){}
_=lp.prototype=new vq();_.tI=4;function Ap(){}
_=Ap.prototype=new lp();_.tI=5;function Eo(){}
_=Eo.prototype=new Ap();_.tI=27;function fp(c,a){var b;b=new bp();return b}
function bp(){}
_=bp.prototype=new wp();_.tI=0;function cp(){}
_=cp.prototype=new Ap();_.tI=30;function np(){}
_=np.prototype=new Ap();_.tI=31;function pp(){}
_=pp.prototype=new Ap();_.tI=32;function tp(b,a){return b}
function sp(){}
_=sp.prototype=new Ap();_.tI=33;function hq(b,a){if(!(a!=null&&Ah(a.tI,1))){return false}return String(b)==a}
function lq(c,a,b){b=sq(b);return c.replace(RegExp(a),b)}
function mq(k,j,h){var a=new RegExp(j,wc);var i=[];var b=0;var l=k;var f=null;while(true){var g=a.exec(l);if(g==null||(l==pb||b==h-1&&h>0)){i[b]=l;break}else{i[b]=l.substring(0,g.index);l=l.substring(g.index+g[0].length,l.length);a.lastIndex=0;if(f==l){i[b]=l.substring(0,1);l=l.substring(1)}f=l;b++}}if(h==0){var e=i.length;while(e>0&&i[e-1]==pb){--e}if(e<i.length){i.splice(e,i.length-e)}}var d=th(li,0,1,i.length,0);for(var c=0;c<i.length;++c){d[c]=i[c]}return d}
function nq(b,a){return b.substr(a,b.length-a)}
function pq(c){if(c.length==0||c[0]>xc&&c[c.length-1]>xc){return c}var a=c.replace(/^(\s*)/,pb);var b=a.replace(/\s*$/,pb);return b}
function sq(b){var a;a=0;while(0<=(a=b.indexOf(yc,a))){if(b.charCodeAt(a+1)==36){b=b.substr(0,a-0)+zc+nq(b,++a)}else{b=b.substr(0,a-0)+nq(b,++a)}}return b}
function tq(a){return hq(this,a)}
function uq(){return dq(this)}
_=String.prototype;_.eQ=tq;_.hC=uq;_.tI=2;function Ep(){Ep=bv;Fp={};cq={}}
function aq(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function dq(c){Ep();var a=Ac+c;var b=cq[a];if(b!=null){return b}b=Fp[a];if(b==null){b=aq(c)}eq();return cq[a]=b}
function eq(){if(bq==256){Fp=cq;cq={};bq=0}++bq}
var Fp,bq=0,cq;function xq(){}
_=xq.prototype=new Ap();_.tI=35;function Bq(a,b){var c;while(a.t()){c=a.v();if(b==null?c==null:rd(b,c)){return a}}return null}
function Dq(a){throw new xq()}
function Eq(b){var a;a=Bq(this.u(),b);return !!a}
function Aq(){}
_=Aq.prototype=new wp();_.k=Dq;_.l=Eq;_.tI=0;function ot(b){var a;a=gr(new ar(),b);return dt(new Cs(),b,a)}
function pt(c){var a,b,d,e,f;if((c==null?null:c)===this){return true}if(!(c!=null&&Ah(c.tI,16))){return false}e=Bh(c,16);if(Bh(this,16).d!=e.d){return false}for(b=cr(new br(),gr(new ar(),e).a);qs(b.a);){a=Bh(rs(b.a),15);d=a.r();f=a.s();if(!(d==null?Bh(this,16).c:d!=null&&Ah(d.tI,1)?cs(Bh(this,16),Bh(d,1)):bs(Bh(this,16),d,~~td(d)))){return false}if(!av(f,d==null?Bh(this,16).b:d!=null&&Ah(d.tI,1)?Bh(this,16).e[Ac+Bh(d,1)]:Er(Bh(this,16),d,~~td(d)))){return false}}return true}
function qt(){var a,b,c;c=0;for(b=cr(new br(),gr(new ar(),Bh(this,16)).a);qs(b.a);){a=Bh(rs(b.a),15);c+=a.hC();c=~~c}return c}
function Bs(){}
_=Bs.prototype=new wp();_.eQ=pt;_.hC=qt;_.tI=0;function zr(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.k(a[f])}}}}
function Ar(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=xr(e,c.substring(1));a.k(b)}}}
function Br(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0}
function Dr(b,a){return a==null?b.c:a!=null&&Ah(a.tI,1)?cs(b,Bh(a,1)):bs(b,a,~~td(a))}
function as(b,a){return a==null?b.b:a!=null&&Ah(a.tI,1)?b.e[Ac+Bh(a,1)]:Er(b,a,~~td(a))}
function Er(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){return c.s()}}}return null}
function bs(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){return true}}}return false}
function cs(b,a){return Ac+a in b.e}
function gs(b,a,c){return a==null?es(b,c):a!=null&&Ah(a.tI,1)?fs(b,Bh(a,1),c):ds(b,a,c,~~td(a))}
function ds(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(i.p(g,d)){var h=c.s();c.A(j);return h}}}else{a=i.a[e]=[]}var c=tu(new su(),g,j);a.push(c);++i.d;return null}
function es(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function fs(d,a,e){var b,c=d.e;a=Ac+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function js(b,a){return !a?is(b):hs(b,a,~~(a.$H||(a.$H=++zd)))}
function hs(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){if(a.length==1){delete h.a[e]}else{a.splice(f,1)}--h.d;return c.s()}}}return null}
function is(b){var a;a=b.b;b.b=null;if(b.c){b.c=false;--b.d}return a}
function ks(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&rd(a,b)}
function Fq(){}
_=Fq.prototype=new Bs();_.p=ks;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function tt(b){var a,c,d;if((b==null?null:b)===this){return true}if(!(b!=null&&Ah(b.tI,17))){return false}c=Bh(b,17);if(c.B()!=this.B()){return false}for(a=c.u();a.t();){d=a.v();if(!this.l(d)){return false}}return true}
function ut(){var a,b,c;a=0;for(b=this.u();b.t();){c=b.v();if(c!=null){a+=td(c);a=~~a}}return a}
function rt(){}
_=rt.prototype=new Aq();_.eQ=tt;_.hC=ut;_.tI=36;function gr(b,a){b.a=a;return b}
function ir(d,c){var a,b,e;if(c!=null&&Ah(c.tI,15)){a=Bh(c,15);b=a.r();if(Dr(d.a,b)){e=as(d.a,b);return eu(a.s(),e)}}return false}
function jr(a){return ir(this,a)}
function kr(){return cr(new br(),this.a)}
function lr(){return this.a.d}
function ar(){}
_=ar.prototype=new rt();_.l=jr;_.u=kr;_.B=lr;_.tI=37;_.a=null;function cr(c,b){var a;c.b=b;a=wt(new vt());if(c.b.c){yt(a,nr(new mr(),c.b))}Ar(c.b,a);zr(c.b,a);c.a=os(new ms(),a);return c}
function er(){return qs(this.a)}
function fr(){return Bh(rs(this.a),15)}
function br(){}
_=br.prototype=new wp();_.t=er;_.v=fr;_.tI=0;_.a=null;_.b=null;function lt(b){var a;if(b!=null&&Ah(b.tI,15)){a=Bh(b,15);if(av(this.r(),a.r())&&av(this.s(),a.s())){return true}}return false}
function mt(){var a,b;a=0;b=0;if(this.r()!=null){a=td(this.r())}if(this.s()!=null){b=td(this.s())}return a^b}
function jt(){}
_=jt.prototype=new wp();_.eQ=lt;_.hC=mt;_.tI=38;function nr(b,a){b.a=a;return b}
function pr(){return null}
function qr(){return this.a.b}
function rr(a){return es(this.a,a)}
function mr(){}
_=mr.prototype=new jt();_.r=pr;_.s=qr;_.A=rr;_.tI=39;_.a=null;function tr(c,a,b){c.b=b;c.a=a;return c}
function vr(){return this.a}
function wr(){return this.b.e[Ac+this.a]}
function xr(b,a){return tr(new sr(),a,b)}
function yr(a){return fs(this.b,this.a,a)}
function sr(){}
_=sr.prototype=new jt();_.r=vr;_.s=wr;_.A=yr;_.tI=40;_.a=null;_.b=null;function vs(a){xt(this,this.B(),a);return true}
function ws(a,b){if(a<0||a>=b){zs(a,b)}}
function xs(e){var a,b,c,d,f;if((e==null?null:e)===this){return true}if(!(e!=null&&Ah(e.tI,3))){return false}f=Bh(e,3);if(this.B()!=f.b){return false}c=os(new ms(),Bh(this,3));d=os(new ms(),f);while(c.a<c.b.b){a=rs(c);b=rs(d);if(!(a==null?b==null:rd(a,b))){return false}}return true}
function ys(){var a,b,c;b=1;a=os(new ms(),Bh(this,3));while(a.a<a.b.b){c=rs(a);b=31*b+(c==null?0:td(c));b=~~b}return b}
function zs(a,b){throw tp(new sp(),Bc+a+Cc+b)}
function As(){return os(new ms(),this)}
function ls(){}
_=ls.prototype=new Aq();_.k=vs;_.eQ=xs;_.hC=ys;_.u=As;_.tI=0;function os(b,a){b.b=a;return b}
function qs(a){return a.a<a.b.b}
function rs(a){if(a.a>=a.b.b){throw new Au()}return At(a.b,a.a++)}
function ss(){return this.a<this.b.b}
function ts(){return rs(this)}
function ms(){}
_=ms.prototype=new wp();_.t=ss;_.v=ts;_.tI=0;_.a=0;_.b=null;function dt(b,a,c){b.a=a;b.b=c;return b}
function gt(a){return Dr(this.a,a)}
function ht(){var a;return a=cr(new br(),this.b.a),Es(new Ds(),a)}
function it(){return this.b.a.d}
function Cs(){}
_=Cs.prototype=new rt();_.l=gt;_.u=ht;_.B=it;_.tI=41;_.a=null;_.b=null;function Es(a,b){a.a=b;return a}
function bt(){return qs(this.a.a)}
function ct(){var a;return a=Bh(rs(this.a.a),15),a.r()}
function Ds(){}
_=Ds.prototype=new wp();_.t=bt;_.v=ct;_.tI=0;_.a=null;function wt(a){a.a=th(ki,0,0,0,0);a.b=0;return a}
function yt(b,a){vh(b.a,b.b++,a);return true}
function xt(c,a,b){if(a<0||a>c.b){zs(a,c.b)}c.a.splice(a,0,b);++c.b}
function At(b,a){ws(a,b.b);return b.a[a]}
function Bt(c,b,a){for(;a<c.b;++a){if(av(b,c.a[a])){return a}}return -1}
function Ct(a){return vh(this.a,this.b++,a),true}
function Dt(a){return Bt(this,a,0)!=-1}
function Et(){return this.b}
function vt(){}
_=vt.prototype=new ls();_.k=Ct;_.l=Dt;_.B=Et;_.tI=42;_.a=null;_.b=0;function cu(a){Br(a);return a}
function eu(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&rd(a,b)}
function bu(){}
_=bu.prototype=new Fq();_.tI=43;function gu(a){a.a=cu(new bu());return a}
function hu(c,a){var b;b=gs(c.a,a,c);return b==null}
function lu(b){var a;return a=gs(this.a,b,this),a==null}
function mu(a){return Dr(this.a,a)}
function nu(){var a;return a=cr(new br(),ot(this.a).b.a),Es(new Ds(),a)}
function ou(){return this.a.d}
function fu(){}
_=fu.prototype=new rt();_.k=lu;_.l=mu;_.u=nu;_.B=ou;_.tI=44;_.a=null;function tu(b,a,c){b.a=a;b.b=c;return b}
function vu(){return this.a}
function wu(){return this.b}
function yu(b){var a;a=this.b;this.b=b;return a}
function su(){}
_=su.prototype=new jt();_.r=vu;_.s=wu;_.A=yu;_.tI=45;_.a=null;_.b=null;function Au(){}
_=Au.prototype=new Ap();_.tI=46;function av(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&rd(a,b)}
function cv(){}
_=cv.prototype=new eh();_.tI=0;_.a=null;_.b=null;_.c=null;_.d=null;function ev(l){var j,k;k=jn(new gn());j=Dl(new Bl());l.a=xo(new oo());l.c=an(new Cm());l.d=zk(new uk());l.b=zk(new uk());(fe(),l.d.j).innerText=Dc;l.b.j.innerText=Fc;kn(k,l.a);kn(k,j);El(j,l.c);El(j,l.d);El(j,l.b);qk((qm(),tm(null)),k);return l}
function dv(){}
_=dv.prototype=new cv();_.tI=0;function Co(){!!$stats&&$stats({moduleName:$moduleName,subSystem:ad,evtGroup:bd,millis:(new Date()).getTime(),type:cd,className:dd});ev(new dv())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{Co()}catch(a){b(d)}else{Co()}}
function bv(){}
var li=fp(ed,fd),ji=fp(gd,hd),ki=fp(ed,id);$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (mindmapgadget) mindmapgadget.onScriptLoad(gwtOnLoad);})();