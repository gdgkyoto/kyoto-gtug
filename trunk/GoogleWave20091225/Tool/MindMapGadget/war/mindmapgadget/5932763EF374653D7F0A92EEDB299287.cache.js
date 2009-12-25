(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var ab='',hc=' ',Cb='#fff',bc=')',cc=',',lc=', Size: ',rb='0',ec='2d',jc=':',A='DOMMouseScroll',nc='Delete',xb='INPUT',kc='Index: ',E='MouseEvents',xc='Object;',uc='String;',mc='Submit',wc='Widget;',vc='[Lcom.google.gwt.user.client.ui.',sc='[Ljava.lang.',vb='align',fc='backgroundColor',q='blur',nb='bottom',db='button',Ab='canvas',tb='cellPadding',qb='cellSpacing',lb='center',r='change',eb='className',C='click',B='contextmenu',hb='dblclick',y='error',sb='focus',gc='g',fb='gwt-Button',Bb='gwt-Canvas',zb='gwt-TextBox',Fb='height',D='html',Db='keydown',ic='keypress',tc='keyup',F='left',yc='load',zc='losecapture',ob='middle',pc='moduleStartup',s='mousedown',t='mousemove',u='mouseout',v='mouseover',w='mouseup',z='mousewheel',qc='onModuleLoadStart',rc='org.kyotogtug.client.MindMapGadget',cb='position',dc='rgb(',ac='rgba(',mb='right',x='scroll',oc='startup',gb='submit',jb='table',kb='tbody',ub='td',yb='text',bb='top',pb='tr',ib='type',wb='verticalAlign',Eb='width';var _;function vp(a){return this===(a==null?null:a)}
function wp(){return this.$H||(this.$H=++jd)}
function tp(){}
_=tp.prototype={};_.eQ=vp;_.hC=wp;_.tM=iv;_.tI=1;function bd(b,a){return b.tM==iv||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function dd(a){return a.tM==iv||a.tI==2?a.hC():a.$H||(a.$H=++jd)}
var jd=0;function wd(){wd=iv;od();new md()}
function Ad(a){var b=a.parentNode;if(b==null){return null}if(b.nodeType!=1)b=null;return b}
function kd(){}
_=kd.prototype=new tp();_.tI=0;function vd(){vd=iv;wd()}
function ud(){}
_=ud.prototype=new kd();_.tI=0;function rd(){rd=iv;vd()}
function sd(b){var d=b.relatedTarget;try{var c=d.nodeName;return d}catch(a){return null}}
function td(b,a){return b===a||!!(b.compareDocumentPosition(a)&16)}
function ld(){}
_=ld.prototype=new ud();_.tI=0;function od(){od=iv;rd()}
function pd(a,b){while(a.firstChild){a.removeChild(a.firstChild)}if(b!=null){a.appendChild(a.ownerDocument.createTextNode(b))}}
function md(){}
_=md.prototype=new ld();_.tI=0;function rf(){}
_=rf.prototype=new tp();_.tI=0;_.a=false;_.b=null;function gf(a){am()}
function hf(b){var a;if(ff){a=new df();mg(b,a)}}
function jf(){return ff}
function df(){}
_=df.prototype=new rf();_.n=gf;_.r=jf;_.tI=0;var ff=null;function of(){}
_=of.prototype=new tp();_.tI=0;function tf(a){a.a=++wf;return a}
function vf(){return this.a}
function sf(){}
_=sf.prototype=new tp();_.hC=vf;_.tI=0;_.a=0;var wf=0;function ig(b,c,a){if(b.b>0){kg(b,Bf(new Af(),b,c,a))}else{bg(b.d,c,a)}return new of()}
function kg(b,a){if(!b.a){b.a=rt(new qt())}tt(b.a,a)}
function mg(c,a){var b;if(a.a){a.a=false;a.b=null}b=a.b;a.b=c.e;try{++c.b;dg(c.d,a,c.c)}finally{--c.b;if(c.b==0){ng(c)}}if(b==null){a.a=true;a.b=null}else{a.b=b}}
function ng(c){var a,b;if(c.a){try{for(b=is(new gs(),c.a);b.a<b.b.E();){a=hh(ls(b),2);bg(a.a.d,a.c,a.b)}}finally{c.a=null}}}
function zf(){}
_=zf.prototype=new tp();_.tI=0;_.a=null;_.b=0;_.c=false;_.d=null;_.e=null;function Bf(b,a,d,c){b.a=a;b.c=d;b.b=c;return b}
function Af(){}
_=Af.prototype=new tp();_.tI=7;_.a=null;_.b=null;_.c=null;function ag(a){a.a=Ft(new Et());return a}
function bg(c,d,a){var b;b=hh(Ar(c.a,d),3);if(!b){b=rt(new qt());as(c.a,d,b)}bh(b.a,b.b++,a)}
function dg(i,e,h){var d,f,g,j,a,b,c;j=e.r();d=(a=hh(Ar(i.a,j),3),!a?0:a.b);if(h){for(g=d-1;g>=0;--g){f=(b=hh(Ar(i.a,j),3),hh((rs(g,b.b),b.a[g]),12));e.n(f)}}else{for(g=0;g<d;++g){f=(c=hh(Ar(i.a,j),3),hh((rs(g,c.b),c.a[g]),12));e.n(f)}}}
function Ef(){}
_=Ef.prototype=new tp();_.tI=0;function qg(){}
_=qg.prototype=new tp();_.tI=0;function Eg(d,c){var a=new Array(c);if(d>0){var e=[null,0,false,[0,0]][d];for(var b=0;b<c;++b){a[b]=e}}return a}
function Fg(a,f,c,b,e){var d;d=Eg(e,b);vg();Ag(d,wg,xg);d.tI=f;d.qI=c;return d}
function bh(a,b,c){if(c!=null){if(a.qI>0&&!fh(c.tI,a.qI)){throw new Bo()}if(a.qI<0&&(c.tM==iv||c.tI==2)){throw new Bo()}}return a[b]=c}
function tg(){}
_=tg.prototype=new tp();_.tI=0;_.length=0;_.qI=0;function vg(){vg=iv;wg=[];xg=[];yg(new tg(),wg,xg)}
function yg(e,a,b){var c=0,f;for(var d in e){if(f=e[d]){a[c]=d;b[c]=f;++c}}}
function Ag(a,c,d){vg();for(var e=0,b=c.length;e<b;++e){a[c[e]]=d[e]}}
var wg,xg;function gh(b,a){return b&&!!sh[b][a]}
function fh(b,a){return b&&sh[b][a]}
function hh(b,a){if(b!=null&&!fh(b.tI,a)){throw new Fo()}return b}
var sh=[{},{},{1:1,8:1,9:1,10:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{2:1},{4:1},{6:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1,14:1},{12:1},{4:1,5:1,6:1,7:1,14:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{9:1},{8:1,13:1},{18:1},{18:1},{15:1},{15:1},{15:1},{16:1},{18:1},{3:1,8:1,16:1},{8:1,17:1},{8:1,18:1},{15:1},{8:1,13:1},{8:1,16:1},{8:1,16:1},{11:1}];function ii(b,a,c){var d;if(a==mi){if(uj((wd(),b).type)==8192){mi=null}}d=hi;hi=b;try{c.y(b)}finally{hi=d}}
var hi=null,mi=null;function Ai(a){cj();return Bi(ff?ff:(ff=tf(new sf())),a)}
function Bi(b,a){return ig(aj(),b,a)}
function Di(){if(Ci){hf(aj())}}
function Ei(){var a;if(Ci){a=(si(),new qi());Fi(a);return null}return null}
function Fi(a){if(bj){mg(bj,a)}}
function aj(){if(!bj){bj=xi(new wi())}return bj}
function cj(){if(!Ci){Cj();Ci=true}}
var Ci=false,bj=null;function si(){si=iv;ti=tf(new sf())}
function ui(a){null.ab()}
function vi(){return ti}
function qi(){}
_=qi.prototype=new rf();_.n=ui;_.r=vi;_.tI=0;var ti;function xi(a){a.d=ag(new Ef());a.e=null;a.c=false;return a}
function wi(){}
_=wi.prototype=new zf();_.tI=8;function uj(a){switch(a){case q:return 4096;case r:return 1024;case C:return 1;case hb:return 2;case sb:return 2048;case Db:return 128;case ic:return 256;case tc:return 512;case yc:return 32768;case zc:return 8192;case s:return 4;case t:return 64;case u:return 32;case v:return 16;case w:return 8;case x:return 16384;case y:return 65536;case z:return 131072;case A:return 131072;case B:return 262144;}}
function wj(){if(!yj){nj();ij();yj=true}}
function zj(a){return !(a!=null&&(a.tM!=iv&&a.tI!=2))&&(a!=null&&gh(a.tI,5))}
var yj=false;function nj(){rj=function(b){if(qj(b)){var a=pj;if(a&&a.__listener){if(zj(a.__listener)){ii(b,a,a.__listener);b.stopPropagation()}}}};qj=function(a){return true};sj=function(b){var c,a=this;while(a&&!(c=a.__listener)){a=a.parentNode}if(a&&a.nodeType!=1){a=null}if(c){if(zj(c)){ii(b,a,c)}}};$wnd.addEventListener(C,rj,true);$wnd.addEventListener(hb,rj,true);$wnd.addEventListener(s,rj,true);$wnd.addEventListener(w,rj,true);$wnd.addEventListener(t,rj,true);$wnd.addEventListener(v,rj,true);$wnd.addEventListener(u,rj,true);$wnd.addEventListener(z,rj,true);$wnd.addEventListener(Db,qj,true);$wnd.addEventListener(tc,qj,true);$wnd.addEventListener(ic,qj,true)}
function oj(c,a){var b=(c.__eventBits||0)^a;c.__eventBits=a;if(!b)return;if(b&1)c.onclick=a&1?sj:null;if(b&2)c.ondblclick=a&2?sj:null;if(b&4)c.onmousedown=a&4?sj:null;if(b&8)c.onmouseup=a&8?sj:null;if(b&16)c.onmouseover=a&16?sj:null;if(b&32)c.onmouseout=a&32?sj:null;if(b&64)c.onmousemove=a&64?sj:null;if(b&128)c.onkeydown=a&128?sj:null;if(b&256)c.onkeypress=a&256?sj:null;if(b&512)c.onkeyup=a&512?sj:null;if(b&1024)c.onchange=a&1024?sj:null;if(b&2048)c.onfocus=a&2048?sj:null;if(b&4096)c.onblur=a&4096?sj:null;if(b&8192)c.onlosecapture=a&8192?sj:null;if(b&16384)c.onscroll=a&16384?sj:null;if(b&32768)c.onload=a&32768?sj:null;if(b&65536)c.onerror=a&65536?sj:null;if(b&131072)c.onmousewheel=a&131072?sj:null;if(b&262144)c.oncontextmenu=a&262144?sj:null}
var pj=null,qj=null,rj=null,sj=null;function ij(){$wnd.addEventListener(u,function(b){var a=$wnd.__captureElem;if(a&&!b.relatedTarget){if(D==b.target.tagName.toLowerCase()){var c=$doc.createEvent(E);c.initMouseEvent(w,true,true,$wnd,0,b.screenX,b.screenY,b.clientX,b.clientY,b.ctrlKey,b.altKey,b.shiftKey,b.metaKey,b.button,null);a.dispatchEvent(c)}}},true);$wnd.addEventListener(A,rj,true)}
function kj(b,a){wj();oj(b,a);jj(b,a)}
function jj(b,a){if(a&131072){b.addEventListener(A,sj,false)}}
function Cj(){var d=$wnd.onbeforeunload;var e=$wnd.onunload;$wnd.onbeforeunload=function(a){var c,b;try{c=Ei()}finally{b=d&&d(a)}if(c!=null){return c}if(b!=null){return b}};$wnd.onunload=function(a){try{Di()}finally{e&&e(a);$wnd.onresize=null;$wnd.onscroll=null;$wnd.onbeforeunload=null;$wnd.onunload=null}}}
function sm(){}
_=sm.prototype=new tp();_.tI=9;_.j=null;function on(b){var a;if(b.g){throw new mp()}b.g=true;b.j.__listener=b;a=b.h;b.h=-1;if(a>0){tn(b,a)}b.o();b.z()}
function pn(c,a){var b;switch(uj((wd(),a).type)){case 16:case 32:b=sd(a);if(!!b&&td(c.j,b)){return}}}
function qn(a){if(!a.g){throw new mp()}try{a.A()}finally{a.p();a.j.__listener=null;a.g=false}}
function rn(a){if(!a.i){Fl();if(xr(em.a,a)){qn(a);ds(em.a,a)!=null}}else if(a.i){a.i.B(a)}else if(a.i){throw new mp()}}
function sn(c,b){var a;a=c.i;if(!b){if(!!a&&a.g){qn(c)}c.i=null}else{if(a){throw new mp()}c.i=b;if(b.g){on(c)}}}
function tn(b,a){if(b.h==-1){kj(b.j,a|(b.j.__eventBits||0))}else{b.h|=a}}
function un(){}
function vn(){}
function wn(a){pn(this,a)}
function xn(){}
function yn(){}
function Cm(){}
_=Cm.prototype=new sm();_.o=un;_.p=vn;_.y=wn;_.z=xn;_.A=yn;_.tI=10;_.g=false;_.h=0;_.i=null;function ul(){var a,b;for(b=this.w();b.a<b.b.b-1;){a=cn(b);on(a)}}
function vl(){var a,b;for(b=this.w();b.a<b.b.b-1;){a=cn(b);qn(a)}}
function wl(){}
function xl(){}
function sl(){}
_=sl.prototype=new Cm();_.o=ul;_.p=vl;_.z=wl;_.A=xl;_.tI=11;function pk(c,a,b){rn(a);gn(c.f,a);b.appendChild(a.j);sn(a,c)}
function rk(b,c){var a;if(c.i!=b){return false}sn(c,null);a=c.j;Ad((wd(),a)).removeChild(a);mn(b.f,c);return true}
function sk(){return an(new Em(),this.f)}
function tk(a){return rk(this,a)}
function nk(){}
_=nk.prototype=new sl();_.w=sk;_.B=tk;_.tI=12;function Ej(a,b){pk(a,b,a.j)}
function ak(a){a.style[F]=ab;a.style[bb]=ab;a.style[cb]=ab}
function bk(b){var a;a=rk(this,b);if(a){ak(b.j)}return a}
function Dj(){}
_=Dj.prototype=new nk();_.B=bk;_.tI=13;function vk(){vk=iv;wk=(eo(),go)}
function uk(){}
_=uk.prototype=new Cm();_.tI=14;var wk;function fk(){fk=iv;vk()}
function ek(b,a){fk();b.j=a;wk.C(b.j,0);return b}
function dk(){}
_=dk.prototype=new uk();_.tI=15;function ik(){ik=iv;fk()}
function hk(a){ik();ek(a,(wd(),$doc).createElement(db));jk(a.j);a.j[eb]=fb;return a}
function jk(b){if(b.type==gb){try{b.setAttribute(ib,db)}catch(a){}}}
function ck(){}
_=ck.prototype=new dk();_.tI=16;function lk(a){a.f=fn(new Dm());a.e=(wd(),$doc).createElement(jb);a.d=$doc.createElement(kb);a.e.appendChild(a.d);a.j=a.e;return a}
function kk(){}
_=kk.prototype=new nk();_.tI=17;_.d=null;_.e=null;function Ek(){Ek=iv;Ck(new Bk(),lb);al=Ck(new Bk(),F);Ck(new Bk(),mb);Fk=al}
var Fk,al;function Ck(b,a){b.a=a;return b}
function Bk(){}
_=Bk.prototype=new tp();_.tI=0;_.a=null;function hl(){hl=iv;fl(new el(),nb);fl(new el(),ob);il=fl(new el(),bb)}
var il;function fl(a,b){a.a=b;return a}
function el(){}
_=el.prototype=new tp();_.tI=0;_.a=null;function ml(a){lk(a);a.a=(Ek(),Fk);a.c=(hl(),il);a.b=(wd(),$doc).createElement(pb);a.d.appendChild(a.b);a.e[qb]=rb;a.e[tb]=rb;return a}
function nl(c,d){var b,a;b=(a=(wd(),$doc).createElement(ub),(a[vb]=c.a.a,undefined),(a.style[wb]=c.c.a,undefined),a);c.b.appendChild(b);rn(d);gn(c.f,d);b.appendChild(d.j);sn(d,c)}
function ql(c){var a,b;b=Ad((wd(),c.j));a=rk(this,c);if(a){this.b.removeChild(b)}return a}
function kl(){}
_=kl.prototype=new kk();_.B=ql;_.tI=18;_.b=null;function Fl(){Fl=iv;dm=Ft(new Et());em=du(new cu())}
function El(b,a){Fl();b.f=fn(new Dm());b.j=a;on(b);return b}
function am(){var b,a;Fl();var c,d;for(d=(b=Cq(new Bq(),jt(em.a).b.a),zs(new ys(),b));ks(d.a.a);){c=hh((a=hh(ls(d.a.a),15),a.s()),7);if(c.g){qn(c)}}vr(em.a);vr(dm)}
function cm(a){Fl();var b;b=hh(Ar(dm,a),14);if(b){return b}if(dm.d==0){Ai(new zl())}b=Cl(new Bl());as(dm,a,b);eu(em,b);return b}
function yl(){}
_=yl.prototype=new Dj();_.tI=19;var dm,em;function zl(){}
_=zl.prototype=new tp();_.tI=20;function Dl(){Dl=iv;Fl()}
function Cl(a){Dl();El(a,$doc.body);return a}
function Bl(){}
_=Bl.prototype=new yl();_.tI=21;function nm(){nm=iv;vk()}
function om(a){var b;b=uj((wd(),a).type);if((b&896)!=0){pn(this,a)}else{pn(this,a)}}
function mm(){}
_=mm.prototype=new uk();_.y=om;_.tI=22;function rm(){rm=iv;nm()}
function pm(b){var a;rm();qm(b,(a=(wd(),$doc).createElement(xb),a.type=yb,a),zb);return b}
function qm(c,a,b){rm();c.j=a;wk.C(c.j,0);if(b!=null){c.j[eb]=b}return c}
function lm(){}
_=lm.prototype=new mm();_.tI=23;function xm(a){lk(a);a.a=(Ek(),Fk);a.b=(hl(),il);a.e[qb]=rb;a.e[tb]=rb;return a}
function ym(c,e){var b,d,a;d=(wd(),$doc).createElement(pb);b=(a=$doc.createElement(ub),(a[vb]=c.a.a,undefined),(a.style[wb]=c.b.a,undefined),a);d.appendChild(b);c.d.appendChild(d);rn(e);gn(c.f,e);b.appendChild(e.j);sn(e,c)}
function Bm(c){var a,b;b=Ad((wd(),c.j));a=rk(this,c);if(a){this.d.removeChild(Ad(b))}return a}
function vm(){}
_=vm.prototype=new kk();_.B=Bm;_.tI=24;function fn(a){a.a=Fg(vh,0,7,4,0);return a}
function gn(a,b){kn(a,b,a.b)}
function jn(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function kn(d,e,a){var b,c;if(a<0||a>d.b){throw new pp()}if(d.b==d.a.length){c=Fg(vh,0,7,d.a.length*2,0);for(b=0;b<d.a.length;++b){bh(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){bh(d.a,b,d.a[b-1])}bh(d.a,a,e)}
function ln(c,b){var a;if(b<0||b>=c.b){throw new pp()}--c.b;for(a=b;a<c.b;++a){bh(c.a,a,c.a[a+1])}bh(c.a,c.b,null)}
function mn(b,c){var a;a=jn(b,c);if(a==-1){throw new xu()}ln(b,a)}
function Dm(){}
_=Dm.prototype=new tp();_.tI=0;_.a=null;_.b=0;function an(b,a){b.b=a;return b}
function cn(a){if(a.a>=a.b.b){throw new xu()}return a.b.a[++a.a]}
function dn(){return this.a<this.b.b-1}
function en(){return cn(this)}
function Em(){}
_=Em.prototype=new tp();_.v=dn;_.x=en;_.tI=0;_.a=-1;_.b=null;function eo(){eo=iv;fo=Cn(new An());go=fo?(eo(),new zn()):fo}
function ho(a,b){a.tabIndex=b}
function zn(){}
_=zn.prototype=new tp();_.C=ho;_.tI=0;var fo,go;function Dn(){Dn=iv;eo()}
function Cn(a){Dn();En();Fn();ao();return a}
function En(){return function(a){if(this.parentNode.onblur){this.parentNode.onblur(a)}}}
function Fn(){return function(a){if(this.parentNode.onfocus){this.parentNode.onfocus(a)}}}
function ao(){return function(){this.firstChild.focus()}}
function bo(a,b){a.firstChild.tabIndex=b}
function An(){}
_=An.prototype=new zn();_.C=bo;_.tI=0;function uo(a){a.a=lo(new ko());a.j=(wd(),$doc).createElement(Ab);po(a.a,a);a.j[eb]=Bb;xo(a,Cb);a.a.c.setAttribute(Eb,ab+300);a.a.c.setAttribute(Fb,ab+150);return a}
function xo(d,a){var b,c;if(a==null){throw new kp()}a=kq(a);if(a.indexOf(ac)==0){b=a.indexOf(bc,12);if(b>-1){c=iq(a.substr(5,b-5),cc,0);if(c.length>=3){a=dc+c[0]+cc+c[1]+cc+c[2]+bc}}}qo(d.a,a)}
function yo(a){if(!a){throw new kp()}uj((wd(),a).type)}
function io(){}
_=io.prototype=new Cm();_.y=yo;_.tI=25;function po(b,a){b.c=a.j;b.b=b.c.getContext(ec)}
function qo(b,a){b.a=a;b.c.style[fc]=b.a}
function jo(){}
_=jo.prototype=new tp();_.tI=0;_.a=null;_.b=null;_.c=null;function lo(a){(new Bu()).a=rt(new qt());return a}
function ko(){}
_=ko.prototype=new jo();_.tI=0;function pq(){}
_=pq.prototype=new tp();_.tI=3;function ip(){}
_=ip.prototype=new pq();_.tI=4;function xp(){}
_=xp.prototype=new ip();_.tI=5;function Bo(){}
_=Bo.prototype=new xp();_.tI=27;function cp(c,a){var b;b=new Eo();return b}
function Eo(){}
_=Eo.prototype=new tp();_.tI=0;function Fo(){}
_=Fo.prototype=new xp();_.tI=30;function kp(){}
_=kp.prototype=new xp();_.tI=31;function mp(){}
_=mp.prototype=new xp();_.tI=32;function qp(b,a){return b}
function pp(){}
_=pp.prototype=new xp();_.tI=33;function eq(b,a){if(!(a!=null&&gh(a.tI,1))){return false}return String(b)==a}
function iq(k,j,h){var a=new RegExp(j,gc);var i=[];var b=0;var l=k;var f=null;while(true){var g=a.exec(l);if(g==null||(l==ab||b==h-1&&h>0)){i[b]=l;break}else{i[b]=l.substring(0,g.index);l=l.substring(g.index+g[0].length,l.length);a.lastIndex=0;if(f==l){i[b]=l.substring(0,1);l=l.substring(1)}f=l;b++}}if(h==0){var e=i.length;while(e>0&&i[e-1]==ab){--e}if(e<i.length){i.splice(e,i.length-e)}}var d=Fg(xh,0,1,i.length,0);for(var c=0;c<i.length;++c){d[c]=i[c]}return d}
function kq(c){if(c.length==0||c[0]>hc&&c[c.length-1]>hc){return c}var a=c.replace(/^(\s*)/,ab);var b=a.replace(/\s*$/,ab);return b}
function nq(a){return eq(this,a)}
function oq(){return aq(this)}
_=String.prototype;_.eQ=nq;_.hC=oq;_.tI=2;function Bp(){Bp=iv;Cp={};Fp={}}
function Dp(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function aq(c){Bp();var a=jc+c;var b=Fp[a];if(b!=null){return b}b=Cp[a];if(b==null){b=Dp(c)}bq();return Fp[a]=b}
function bq(){if(Ep==256){Cp=Fp;Fp={};Ep=0}++Ep}
var Cp,Ep=0,Fp;function rq(){}
_=rq.prototype=new xp();_.tI=35;function vq(a,b){var c;while(a.v()){c=a.x();if(b==null?c==null:bd(b,c)){return a}}return null}
function xq(a){throw new rq()}
function yq(b){var a;a=vq(this.w(),b);return !!a}
function uq(){}
_=uq.prototype=new tp();_.l=xq;_.m=yq;_.tI=0;function jt(b){var a;a=ar(new Aq(),b);return Es(new xs(),b,a)}
function kt(c){var a,b,d,e,f;if((c==null?null:c)===this){return true}if(!(c!=null&&gh(c.tI,17))){return false}e=hh(c,17);if(hh(this,17).d!=e.d){return false}for(b=Cq(new Bq(),ar(new Aq(),e).a);ks(b.a);){a=hh(ls(b.a),15);d=a.s();f=a.t();if(!(d==null?hh(this,17).c:d!=null&&gh(d.tI,1)?Cr(hh(this,17),hh(d,1)):Br(hh(this,17),d,~~dd(d)))){return false}if(!Fu(f,d==null?hh(this,17).b:d!=null&&gh(d.tI,1)?hh(this,17).e[jc+hh(d,1)]:yr(hh(this,17),d,~~dd(d)))){return false}}return true}
function lt(){var a,b,c;c=0;for(b=Cq(new Bq(),ar(new Aq(),hh(this,17)).a);ks(b.a);){a=hh(ls(b.a),15);c+=a.hC();c=~~c}return c}
function ws(){}
_=ws.prototype=new tp();_.eQ=kt;_.hC=lt;_.tI=0;function tr(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.l(a[f])}}}}
function ur(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=rr(e,c.substring(1));a.l(b)}}}
function vr(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0}
function xr(b,a){return a==null?b.c:a!=null&&gh(a.tI,1)?Cr(b,hh(a,1)):Br(b,a,~~dd(a))}
function Ar(b,a){return a==null?b.b:a!=null&&gh(a.tI,1)?b.e[jc+hh(a,1)]:yr(b,a,~~dd(a))}
function yr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){return c.t()}}}return null}
function Br(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){return true}}}return false}
function Cr(b,a){return jc+a in b.e}
function as(b,a,c){return a==null?Er(b,c):a!=null&&gh(a.tI,1)?Fr(b,hh(a,1),c):Dr(b,a,c,~~dd(a))}
function Dr(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(i.q(g,d)){var h=c.t();c.D(j);return h}}}else{a=i.a[e]=[]}var c=qu(new pu(),g,j);a.push(c);++i.d;return null}
function Er(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function Fr(d,a,e){var b,c=d.e;a=jc+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function ds(b,a){return !a?cs(b):bs(b,a,~~(a.$H||(a.$H=++jd)))}
function bs(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){if(a.length==1){delete h.a[e]}else{a.splice(f,1)}--h.d;return c.t()}}}return null}
function cs(b){var a;a=b.b;b.b=null;if(b.c){b.c=false;--b.d}return a}
function es(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&bd(a,b)}
function zq(){}
_=zq.prototype=new ws();_.q=es;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function ot(b){var a,c,d;if((b==null?null:b)===this){return true}if(!(b!=null&&gh(b.tI,18))){return false}c=hh(b,18);if(c.E()!=this.E()){return false}for(a=c.w();a.v();){d=a.x();if(!this.m(d)){return false}}return true}
function pt(){var a,b,c;a=0;for(b=this.w();b.v();){c=b.x();if(c!=null){a+=dd(c);a=~~a}}return a}
function mt(){}
_=mt.prototype=new uq();_.eQ=ot;_.hC=pt;_.tI=36;function ar(b,a){b.a=a;return b}
function cr(d,c){var a,b,e;if(c!=null&&gh(c.tI,15)){a=hh(c,15);b=a.s();if(xr(d.a,b)){e=Ar(d.a,b);return bu(a.t(),e)}}return false}
function dr(a){return cr(this,a)}
function er(){return Cq(new Bq(),this.a)}
function fr(){return this.a.d}
function Aq(){}
_=Aq.prototype=new mt();_.m=dr;_.w=er;_.E=fr;_.tI=37;_.a=null;function Cq(c,b){var a;c.b=b;a=rt(new qt());if(c.b.c){tt(a,hr(new gr(),c.b))}ur(c.b,a);tr(c.b,a);c.a=is(new gs(),a);return c}
function Eq(){return ks(this.a)}
function Fq(){return hh(ls(this.a),15)}
function Bq(){}
_=Bq.prototype=new tp();_.v=Eq;_.x=Fq;_.tI=0;_.a=null;_.b=null;function gt(b){var a;if(b!=null&&gh(b.tI,15)){a=hh(b,15);if(Fu(this.s(),a.s())&&Fu(this.t(),a.t())){return true}}return false}
function ht(){var a,b;a=0;b=0;if(this.s()!=null){a=dd(this.s())}if(this.t()!=null){b=dd(this.t())}return a^b}
function et(){}
_=et.prototype=new tp();_.eQ=gt;_.hC=ht;_.tI=38;function hr(b,a){b.a=a;return b}
function jr(){return null}
function kr(){return this.a.b}
function lr(a){return Er(this.a,a)}
function gr(){}
_=gr.prototype=new et();_.s=jr;_.t=kr;_.D=lr;_.tI=39;_.a=null;function nr(c,a,b){c.b=b;c.a=a;return c}
function pr(){return this.a}
function qr(){return this.b.e[jc+this.a]}
function rr(b,a){return nr(new mr(),a,b)}
function sr(a){return Fr(this.b,this.a,a)}
function mr(){}
_=mr.prototype=new et();_.s=pr;_.t=qr;_.D=sr;_.tI=40;_.a=null;_.b=null;function qs(a){this.k(this.E(),a);return true}
function ps(b,a){throw new rq()}
function rs(a,b){if(a<0||a>=b){us(a,b)}}
function ss(e){var a,b,c,d,f;if((e==null?null:e)===this){return true}if(!(e!=null&&gh(e.tI,16))){return false}f=hh(e,16);if(this.E()!=f.E()){return false}c=this.w();d=f.w();while(c.a<c.b.E()){a=ls(c);b=ls(d);if(!(a==null?b==null:bd(a,b))){return false}}return true}
function ts(){var a,b,c;b=1;a=this.w();while(a.a<a.b.E()){c=ls(a);b=31*b+(c==null?0:dd(c));b=~~b}return b}
function us(a,b){throw qp(new pp(),kc+a+lc+b)}
function vs(){return is(new gs(),this)}
function fs(){}
_=fs.prototype=new uq();_.l=qs;_.k=ps;_.eQ=ss;_.hC=ts;_.w=vs;_.tI=41;function is(b,a){b.b=a;return b}
function ks(a){return a.a<a.b.E()}
function ls(a){if(a.a>=a.b.E()){throw new xu()}return a.b.u(a.a++)}
function ms(){return this.a<this.b.E()}
function ns(){return ls(this)}
function gs(){}
_=gs.prototype=new tp();_.v=ms;_.x=ns;_.tI=0;_.a=0;_.b=null;function Es(b,a,c){b.a=a;b.b=c;return b}
function bt(a){return xr(this.a,a)}
function ct(){var a;return a=Cq(new Bq(),this.b.a),zs(new ys(),a)}
function dt(){return this.b.a.d}
function xs(){}
_=xs.prototype=new mt();_.m=bt;_.w=ct;_.E=dt;_.tI=42;_.a=null;_.b=null;function zs(a,b){a.a=b;return a}
function Cs(){return ks(this.a.a)}
function Ds(){var a;return a=hh(ls(this.a.a),15),a.s()}
function ys(){}
_=ys.prototype=new tp();_.v=Cs;_.x=Ds;_.tI=0;_.a=null;function rt(a){a.a=Fg(wh,0,0,0,0);a.b=0;return a}
function tt(b,a){bh(b.a,b.b++,a);return true}
function st(c,a,b){if(a<0||a>c.b){us(a,c.b)}c.a.splice(a,0,b);++c.b}
function vt(b,a){rs(a,b.b);return b.a[a]}
function wt(c,b,a){for(;a<c.b;++a){if(Fu(b,c.a[a])){return a}}return -1}
function yt(a){return bh(this.a,this.b++,a),true}
function xt(a,b){st(this,a,b)}
function zt(a){return wt(this,a,0)!=-1}
function At(a){return rs(a,this.b),this.a[a]}
function Bt(){return this.b}
function qt(){}
_=qt.prototype=new fs();_.l=yt;_.k=xt;_.m=zt;_.u=At;_.E=Bt;_.tI=43;_.a=null;_.b=0;function Ft(a){vr(a);return a}
function bu(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&bd(a,b)}
function Et(){}
_=Et.prototype=new zq();_.tI=44;function du(a){a.a=Ft(new Et());return a}
function eu(c,a){var b;b=as(c.a,a,c);return b==null}
function iu(b){var a;return a=as(this.a,b,this),a==null}
function ju(a){return xr(this.a,a)}
function ku(){var a;return a=Cq(new Bq(),jt(this.a).b.a),zs(new ys(),a)}
function lu(){return this.a.d}
function cu(){}
_=cu.prototype=new mt();_.l=iu;_.m=ju;_.w=ku;_.E=lu;_.tI=45;_.a=null;function qu(b,a,c){b.a=a;b.b=c;return b}
function su(){return this.a}
function tu(){return this.b}
function vu(b){var a;a=this.b;this.b=b;return a}
function pu(){}
_=pu.prototype=new et();_.s=su;_.t=tu;_.D=vu;_.tI=46;_.a=null;_.b=null;function xu(){}
_=xu.prototype=new xp();_.tI=47;function dv(a){return tt(this.a,a)}
function cv(a,b){st(this.a,a,b)}
function ev(a){return wt(this.a,a,0)!=-1}
function fv(a){return vt(this.a,a)}
function gv(){return is(new gs(),this.a)}
function hv(){return this.a.b}
function av(){}
_=av.prototype=new fs();_.l=dv;_.k=cv;_.m=ev;_.u=fv;_.w=gv;_.E=hv;_.tI=48;_.a=null;function Bu(){}
_=Bu.prototype=new av();_.tI=49;function Fu(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&bd(a,b)}
function jv(){}
_=jv.prototype=new qg();_.tI=0;_.a=null;_.b=null;_.c=null;_.d=null;function lv(l){var j,k;k=xm(new vm());j=ml(new kl());l.a=uo(new io());l.c=pm(new lm());l.d=hk(new ck());l.b=hk(new ck());pd((wd(),l.d.j),mc);pd(l.b.j,nc);ym(k,l.a);ym(k,j);nl(j,l.c);nl(j,l.d);nl(j,l.b);Ej((Fl(),cm(null)),k);return l}
function kv(){}
_=kv.prototype=new jv();_.tI=0;function zo(){!!$stats&&$stats({moduleName:$moduleName,subSystem:oc,evtGroup:pc,millis:(new Date()).getTime(),type:qc,className:rc});lv(new kv())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{zo()}catch(a){b(d)}else{zo()}}
function iv(){}
var xh=cp(sc,uc),vh=cp(vc,wc),wh=cp(sc,xc);$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (mindmapgadget) mindmapgadget.onScriptLoad(gwtOnLoad);})();