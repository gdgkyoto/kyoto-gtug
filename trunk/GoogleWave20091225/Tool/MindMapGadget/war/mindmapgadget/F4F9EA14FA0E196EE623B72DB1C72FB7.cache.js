(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var E='',fc=' ',Ab='#fff',Fb=')',ac=',',jc=', Size: ',pb='0',cc='2d',gc=':',A='DOMMouseScroll',lc='Delete',vb='INPUT',hc='Index: ',vc='Object;',rc='String;',kc='Submit',uc='Widget;',sc='[Lcom.google.gwt.user.client.ui.',qc='[Ljava.lang.',tb='align',dc='backgroundColor',q='blur',lb='bottom',bb='button',yb='canvas',qb='cellPadding',ob='cellSpacing',jb='center',r='change',cb='className',C='click',B='contextmenu',hb='dblclick',y='error',sb='focus',ec='g',db='gwt-Button',zb='gwt-Canvas',xb='gwt-TextBox',Cb='height',Db='keydown',ic='keypress',tc='keyup',D='left',wc='load',xc='losecapture',mb='middle',nc='moduleStartup',s='mousedown',t='mousemove',u='mouseout',v='mouseover',w='mouseup',z='mousewheel',oc='onModuleLoadStart',pc='org.kyotogtug.client.MindMapGadget',ab='position',bc='rgb(',Eb='rgba(',kb='right',x='scroll',mc='startup',eb='submit',gb='table',ib='tbody',rb='td',wb='text',F='top',nb='tr',fb='type',ub='verticalAlign',Bb='width';var _;function qp(a){return this===(a==null?null:a)}
function rp(){return this.$H||(this.$H=++hd)}
function op(){}
_=op.prototype={};_.eQ=qp;_.hC=rp;_.tM=wu;_.tI=1;function Fc(b,a){return b.tM==wu||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function bd(a){return a.tM==wu||a.tI==2?a.hC():a.$H||(a.$H=++hd)}
var hd=0;function qd(){qd=wu;ld();new jd()}
function ud(a){var b=a.parentNode;if(b==null){return null}if(b.nodeType!=1)b=null;return b}
function vd(a,b){while(a.firstChild){a.removeChild(a.firstChild)}if(b!=null){a.appendChild(a.ownerDocument.createTextNode(b))}}
function id(){}
_=id.prototype=new op();_.tI=0;function od(){od=wu;qd()}
function nd(){}
_=nd.prototype=new id();_.tI=0;function ld(){ld=wu;od()}
function md(b,a){while(a){if(b==a){return true}a=a.parentNode;if(a&&a.nodeType!=1){a=null}}return false}
function jd(){}
_=jd.prototype=new nd();_.tI=0;function mf(){}
_=mf.prototype=new op();_.tI=0;_.a=false;_.b=null;function bf(a){xl()}
function cf(b){var a;if(af){a=new Ee();hg(b,a)}}
function df(){return af}
function Ee(){}
_=Ee.prototype=new mf();_.m=bf;_.q=df;_.tI=0;var af=null;function jf(){}
_=jf.prototype=new op();_.tI=0;function of(a){a.a=++rf;return a}
function qf(){return this.a}
function nf(){}
_=nf.prototype=new op();_.hC=qf;_.tI=0;_.a=0;var rf=0;function dg(b,c,a){if(b.b>0){fg(b,wf(new vf(),b,c,a))}else{Cf(b.d,c,a)}return new jf()}
function fg(b,a){if(!b.a){b.a=lt(new kt())}nt(b.a,a)}
function hg(c,a){var b;if(a.a){a.a=false;a.b=null}b=a.b;a.b=c.e;try{++c.b;Ef(c.d,a,c.c)}finally{--c.b;if(c.b==0){ig(c)}}if(b==null){a.a=true;a.b=null}else{a.b=b}}
function ig(c){var a,b;if(c.a){try{for(b=ds(new bs(),c.a);b.a<b.b.b;){a=ch(gs(b),2);Cf(a.a.d,a.c,a.b)}}finally{c.a=null}}}
function uf(){}
_=uf.prototype=new op();_.tI=0;_.a=null;_.b=0;_.c=false;_.d=null;_.e=null;function wf(b,a,d,c){b.a=a;b.c=d;b.b=c;return b}
function vf(){}
_=vf.prototype=new op();_.tI=7;_.a=null;_.b=null;_.c=null;function Bf(a){a.a=xt(new wt());return a}
function Cf(c,d,a){var b;b=ch(vr(c.a,d),3);if(!b){b=lt(new kt());Br(c.a,d,b)}Cg(b.a,b.b++,a)}
function Ef(i,e,h){var d,f,g,j,a,b,c;j=e.q();d=(a=ch(vr(i.a,j),3),!a?0:a.b);if(h){for(g=d-1;g>=0;--g){f=(b=ch(vr(i.a,j),3),ch((ls(g,b.b),b.a[g]),12));e.m(f)}}else{for(g=0;g<d;++g){f=(c=ch(vr(i.a,j),3),ch((ls(g,c.b),c.a[g]),12));e.m(f)}}}
function zf(){}
_=zf.prototype=new op();_.tI=0;function lg(){}
_=lg.prototype=new op();_.tI=0;function zg(d,c){var a=new Array(c);if(d>0){var e=[null,0,false,[0,0]][d];for(var b=0;b<c;++b){a[b]=e}}return a}
function Ag(a,f,c,b,e){var d;d=zg(e,b);qg();vg(d,rg,sg);d.tI=f;d.qI=c;return d}
function Cg(a,b,c){if(c!=null){if(a.qI>0&&!ah(c.tI,a.qI)){throw new wo()}if(a.qI<0&&(c.tM==wu||c.tI==2)){throw new wo()}}return a[b]=c}
function og(){}
_=og.prototype=new op();_.tI=0;_.length=0;_.qI=0;function qg(){qg=wu;rg=[];sg=[];tg(new og(),rg,sg)}
function tg(e,a,b){var c=0,f;for(var d in e){if(f=e[d]){a[c]=d;b[c]=f;++c}}}
function vg(a,c,d){qg();for(var e=0,b=c.length;e<b;++e){a[c[e]]=d[e]}}
var rg,sg;function bh(b,a){return b&&!!nh[b][a]}
function ah(b,a){return b&&nh[b][a]}
function ch(b,a){if(b!=null&&!ah(b.tI,a)){throw new Ao()}return b}
var nh=[{},{},{1:1,8:1,9:1,10:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{2:1},{4:1},{6:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1,14:1},{12:1},{4:1,5:1,6:1,7:1,14:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{9:1},{8:1,13:1},{17:1},{17:1},{15:1},{15:1},{15:1},{17:1},{3:1,8:1},{8:1,16:1},{8:1,17:1},{15:1},{8:1,13:1},{11:1}];function di(b,a,c){var d;if(a==hi){if(lj((qd(),b).type)==8192){hi=null}}d=ci;ci=b;try{c.w(b)}finally{ci=d}}
function ji(a,b){nj();fj(a,b)}
var ci=null,hi=null;function wi(a){Ei();return xi(af?af:(af=of(new nf())),a)}
function xi(b,a){return dg(Ci(),b,a)}
function zi(){if(yi){cf(Ci())}}
function Ai(){var a;if(yi){a=(oi(),new mi());Bi(a);return null}return null}
function Bi(a){if(Di){hg(Di,a)}}
function Ci(){if(!Di){Di=ti(new si())}return Di}
function Ei(){if(!yi){tj();yi=true}}
var yi=false,Di=null;function oi(){oi=wu;pi=of(new nf())}
function qi(a){null.E()}
function ri(){return pi}
function mi(){}
_=mi.prototype=new mf();_.m=qi;_.q=ri;_.tI=0;var pi;function ti(a){a.d=Bf(new zf());a.e=null;a.c=false;return a}
function si(){}
_=si.prototype=new uf();_.tI=8;function lj(a){switch(a){case q:return 4096;case r:return 1024;case C:return 1;case hb:return 2;case sb:return 2048;case Db:return 128;case ic:return 256;case tc:return 512;case wc:return 32768;case xc:return 8192;case s:return 4;case t:return 64;case u:return 32;case v:return 16;case w:return 8;case x:return 16384;case y:return 65536;case z:return 131072;case A:return 131072;case B:return 262144;}}
function nj(){if(!pj){ej();pj=true}}
function qj(a){return !(a!=null&&(a.tM!=wu&&a.tI!=2))&&(a!=null&&bh(a.tI,5))}
var pj=false;function ej(){ij=function(b){if(hj(b)){var a=gj;if(a&&a.__listener){if(qj(a.__listener)){di(b,a,a.__listener);b.stopPropagation()}}}};hj=function(a){return true};jj=function(b){var c,a=this;while(a&&!(c=a.__listener)){a=a.parentNode}if(a&&a.nodeType!=1){a=null}if(c){if(qj(c)){di(b,a,c)}}};$wnd.addEventListener(C,ij,true);$wnd.addEventListener(hb,ij,true);$wnd.addEventListener(s,ij,true);$wnd.addEventListener(w,ij,true);$wnd.addEventListener(t,ij,true);$wnd.addEventListener(v,ij,true);$wnd.addEventListener(u,ij,true);$wnd.addEventListener(z,ij,true);$wnd.addEventListener(Db,hj,true);$wnd.addEventListener(tc,hj,true);$wnd.addEventListener(ic,hj,true)}
function fj(c,a){var b=(c.__eventBits||0)^a;c.__eventBits=a;if(!b)return;if(b&1)c.onclick=a&1?jj:null;if(b&2)c.ondblclick=a&2?jj:null;if(b&4)c.onmousedown=a&4?jj:null;if(b&8)c.onmouseup=a&8?jj:null;if(b&16)c.onmouseover=a&16?jj:null;if(b&32)c.onmouseout=a&32?jj:null;if(b&64)c.onmousemove=a&64?jj:null;if(b&128)c.onkeydown=a&128?jj:null;if(b&256)c.onkeypress=a&256?jj:null;if(b&512)c.onkeyup=a&512?jj:null;if(b&1024)c.onchange=a&1024?jj:null;if(b&2048)c.onfocus=a&2048?jj:null;if(b&4096)c.onblur=a&4096?jj:null;if(b&8192)c.onlosecapture=a&8192?jj:null;if(b&16384)c.onscroll=a&16384?jj:null;if(b&32768)c.onload=a&32768?jj:null;if(b&65536)c.onerror=a&65536?jj:null;if(b&131072)c.onmousewheel=a&131072?jj:null;if(b&262144)c.oncontextmenu=a&262144?jj:null}
var gj=null,hj=null,ij=null,jj=null;function tj(){var d=$wnd.onbeforeunload;var e=$wnd.onunload;$wnd.onbeforeunload=function(a){var c,b;try{c=Ai()}finally{b=d&&d(a)}if(c!=null){return c}if(b!=null){return b}};$wnd.onunload=function(a){try{zi()}finally{e&&e(a);$wnd.onresize=null;$wnd.onscroll=null;$wnd.onbeforeunload=null;$wnd.onunload=null}}}
function jm(){}
_=jm.prototype=new op();_.tI=9;_.j=null;function en(b){var a;if(b.g){throw new hp()}b.g=true;b.j.__listener=b;a=b.h;b.h=-1;if(a>0){kn(b,a)}b.n();b.x()}
function fn(c,a){var b;switch(lj((qd(),a).type)){case 16:case 32:b=a.relatedTarget;if(!!b&&md(c.j,b)){return}}}
function gn(a){if(!a.g){throw new hp()}try{a.y()}finally{a.o();a.j.__listener=null;a.g=false}}
function hn(a){if(!a.i){wl();if(sr(Bl.a,a)){gn(a);Er(Bl.a,a)!=null}}else if(a.i){a.i.z(a)}else if(a.i){throw new hp()}}
function jn(c,b){var a;a=c.i;if(!b){if(!!a&&a.g){gn(c)}c.i=null}else{if(a){throw new hp()}c.i=b;if(b.g){en(c)}}}
function kn(b,a){if(b.h==-1){ji(b.j,a|(b.j.__eventBits||0))}else{b.h|=a}}
function ln(){}
function mn(){}
function nn(a){fn(this,a)}
function on(){}
function pn(){}
function tm(){}
_=tm.prototype=new jm();_.n=ln;_.o=mn;_.w=nn;_.x=on;_.y=pn;_.tI=10;_.g=false;_.h=0;_.i=null;function ll(){var a,b;for(b=this.u();b.a<b.b.b-1;){a=zm(b);en(a)}}
function ml(){var a,b;for(b=this.u();b.a<b.b.b-1;){a=zm(b);gn(a)}}
function nl(){}
function ol(){}
function jl(){}
_=jl.prototype=new tm();_.n=ll;_.o=ml;_.x=nl;_.y=ol;_.tI=11;function gk(c,a,b){hn(a);Dm(c.f,a);b.appendChild(a.j);jn(a,c)}
function ik(b,c){var a;if(c.i!=b){return false}jn(c,null);a=c.j;ud((qd(),a)).removeChild(a);cn(b.f,c);return true}
function jk(){return xm(new vm(),this.f)}
function kk(a){return ik(this,a)}
function ek(){}
_=ek.prototype=new jl();_.u=jk;_.z=kk;_.tI=12;function vj(a,b){gk(a,b,a.j)}
function xj(a){a.style[D]=E;a.style[F]=E;a.style[ab]=E}
function yj(b){var a;a=ik(this,b);if(a){xj(b.j)}return a}
function uj(){}
_=uj.prototype=new ek();_.z=yj;_.tI=13;function mk(){mk=wu;nk=(Dn(),Fn)}
function lk(){}
_=lk.prototype=new tm();_.tI=14;var nk;function Cj(){Cj=wu;mk()}
function Bj(b,a){Cj();b.j=a;nk.A(b.j,0);return b}
function Aj(){}
_=Aj.prototype=new lk();_.tI=15;function Fj(){Fj=wu;Cj()}
function Ej(a){Fj();Bj(a,(qd(),$doc).createElement(bb));ak(a.j);a.j[cb]=db;return a}
function ak(b){if(b.type==eb){try{b.setAttribute(fb,bb)}catch(a){}}}
function zj(){}
_=zj.prototype=new Aj();_.tI=16;function ck(a){a.f=Cm(new um());a.e=(qd(),$doc).createElement(gb);a.d=$doc.createElement(ib);a.e.appendChild(a.d);a.j=a.e;return a}
function bk(){}
_=bk.prototype=new ek();_.tI=17;_.d=null;_.e=null;function vk(){vk=wu;tk(new sk(),jb);xk=tk(new sk(),D);tk(new sk(),kb);wk=xk}
var wk,xk;function tk(b,a){b.a=a;return b}
function sk(){}
_=sk.prototype=new op();_.tI=0;_.a=null;function Ek(){Ek=wu;Ck(new Bk(),lb);Ck(new Bk(),mb);Fk=Ck(new Bk(),F)}
var Fk;function Ck(a,b){a.a=b;return a}
function Bk(){}
_=Bk.prototype=new op();_.tI=0;_.a=null;function dl(a){ck(a);a.a=(vk(),wk);a.c=(Ek(),Fk);a.b=(qd(),$doc).createElement(nb);a.d.appendChild(a.b);a.e[ob]=pb;a.e[qb]=pb;return a}
function el(c,d){var b,a;b=(a=(qd(),$doc).createElement(rb),(a[tb]=c.a.a,undefined),(a.style[ub]=c.c.a,undefined),a);c.b.appendChild(b);hn(d);Dm(c.f,d);b.appendChild(d.j);jn(d,c)}
function hl(c){var a,b;b=ud((qd(),c.j));a=ik(this,c);if(a){this.b.removeChild(b)}return a}
function bl(){}
_=bl.prototype=new bk();_.z=hl;_.tI=18;_.b=null;function wl(){wl=wu;Al=xt(new wt());Bl=Bt(new At())}
function vl(b,a){wl();b.f=Cm(new um());b.j=a;en(b);return b}
function xl(){var b,a;wl();var c,d;for(d=(b=xq(new wq(),dt(Bl.a).b.a),ts(new ss(),b));fs(d.a.a);){c=ch((a=ch(gs(d.a.a),15),a.r()),7);if(c.g){gn(c)}}qr(Bl.a);qr(Al)}
function zl(a){wl();var b;b=ch(vr(Al,a),14);if(b){return b}if(Al.d==0){wi(new ql())}b=tl(new sl());Br(Al,a,b);Ct(Bl,b);return b}
function pl(){}
_=pl.prototype=new uj();_.tI=19;var Al,Bl;function ql(){}
_=ql.prototype=new op();_.tI=20;function ul(){ul=wu;wl()}
function tl(a){ul();vl(a,$doc.body);return a}
function sl(){}
_=sl.prototype=new pl();_.tI=21;function em(){em=wu;mk()}
function fm(a){var b;b=lj((qd(),a).type);if((b&896)!=0){fn(this,a)}else{fn(this,a)}}
function dm(){}
_=dm.prototype=new lk();_.w=fm;_.tI=22;function im(){im=wu;em()}
function gm(b){var a;im();hm(b,(a=(qd(),$doc).createElement(vb),a.type=wb,a),xb);return b}
function hm(c,a,b){im();c.j=a;nk.A(c.j,0);if(b!=null){c.j[cb]=b}return c}
function cm(){}
_=cm.prototype=new dm();_.tI=23;function om(a){ck(a);a.a=(vk(),wk);a.b=(Ek(),Fk);a.e[ob]=pb;a.e[qb]=pb;return a}
function pm(c,e){var b,d,a;d=(qd(),$doc).createElement(nb);b=(a=$doc.createElement(rb),(a[tb]=c.a.a,undefined),(a.style[ub]=c.b.a,undefined),a);d.appendChild(b);c.d.appendChild(d);hn(e);Dm(c.f,e);b.appendChild(e.j);jn(e,c)}
function sm(c){var a,b;b=ud((qd(),c.j));a=ik(this,c);if(a){this.d.removeChild(ud(b))}return a}
function mm(){}
_=mm.prototype=new bk();_.z=sm;_.tI=24;function Cm(a){a.a=Ag(qh,0,7,4,0);return a}
function Dm(a,b){an(a,b,a.b)}
function Fm(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function an(d,e,a){var b,c;if(a<0||a>d.b){throw new kp()}if(d.b==d.a.length){c=Ag(qh,0,7,d.a.length*2,0);for(b=0;b<d.a.length;++b){Cg(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){Cg(d.a,b,d.a[b-1])}Cg(d.a,a,e)}
function bn(c,b){var a;if(b<0||b>=c.b){throw new kp()}--c.b;for(a=b;a<c.b;++a){Cg(c.a,a,c.a[a+1])}Cg(c.a,c.b,null)}
function cn(b,c){var a;a=Fm(b,c);if(a==-1){throw new pu()}bn(b,a)}
function um(){}
_=um.prototype=new op();_.tI=0;_.a=null;_.b=0;function xm(b,a){b.b=a;return b}
function zm(a){if(a.a>=a.b.b){throw new pu()}return a.b.a[++a.a]}
function Am(){return this.a<this.b.b-1}
function Bm(){return zm(this)}
function vm(){}
_=vm.prototype=new op();_.t=Am;_.v=Bm;_.tI=0;_.a=-1;_.b=null;function Dn(){Dn=wu;En=zn(new yn());Fn=En?(Dn(),new qn()):En}
function ao(a,b){a.tabIndex=b}
function qn(){}
_=qn.prototype=new op();_.A=ao;_.tI=0;var En,Fn;function un(){un=wu;Dn()}
function vn(){return function(a){if(this.parentNode.onblur){this.parentNode.onblur(a)}}}
function wn(){return function(a){if(this.parentNode.onfocus){this.parentNode.onfocus(a)}}}
function xn(a,b){a.firstChild.tabIndex=b}
function rn(){}
_=rn.prototype=new qn();_.A=xn;_.tI=0;function An(){An=wu;un()}
function zn(a){An();vn();wn();Bn();return a}
function Bn(){return function(){var a=this.firstChild;$wnd.setTimeout(function(){a.focus()},0)}}
function yn(){}
_=yn.prototype=new rn();_.tI=0;function po(a){a.a=new go();a.j=(qd(),$doc).createElement(yb);ko(a.a,a);a.j[cb]=zb;so(a,Ab);a.a.c.setAttribute(Bb,E+300);a.a.c.setAttribute(Cb,E+150);return a}
function so(d,a){var b,c;if(a==null){throw new fp()}a=fq(a);if(a.indexOf(Eb)==0){b=a.indexOf(Fb,12);if(b>-1){c=dq(a.substr(5,b-5),ac,0);if(c.length>=3){a=bc+c[0]+ac+c[1]+ac+c[2]+Fb}}}lo(d.a,a)}
function to(a){if(!a){throw new fp()}lj((qd(),a).type)}
function bo(){}
_=bo.prototype=new tm();_.w=to;_.tI=25;function ko(b,a){b.c=a.j;b.b=b.c.getContext(cc)}
function lo(b,a){b.a=a;b.c.style[dc]=b.a}
function co(){}
_=co.prototype=new op();_.tI=0;_.a=null;_.b=null;_.c=null;function eo(){}
_=eo.prototype=new co();_.tI=0;function go(){}
_=go.prototype=new eo();_.tI=0;function kq(){}
_=kq.prototype=new op();_.tI=3;function dp(){}
_=dp.prototype=new kq();_.tI=4;function sp(){}
_=sp.prototype=new dp();_.tI=5;function wo(){}
_=wo.prototype=new sp();_.tI=27;function Do(c,a){var b;b=new zo();return b}
function zo(){}
_=zo.prototype=new op();_.tI=0;function Ao(){}
_=Ao.prototype=new sp();_.tI=30;function fp(){}
_=fp.prototype=new sp();_.tI=31;function hp(){}
_=hp.prototype=new sp();_.tI=32;function lp(b,a){return b}
function kp(){}
_=kp.prototype=new sp();_.tI=33;function Fp(b,a){if(!(a!=null&&bh(a.tI,1))){return false}return String(b)==a}
function dq(k,j,h){var a=new RegExp(j,ec);var i=[];var b=0;var l=k;var f=null;while(true){var g=a.exec(l);if(g==null||(l==E||b==h-1&&h>0)){i[b]=l;break}else{i[b]=l.substring(0,g.index);l=l.substring(g.index+g[0].length,l.length);a.lastIndex=0;if(f==l){i[b]=l.substring(0,1);l=l.substring(1)}f=l;b++}}if(h==0){var e=i.length;while(e>0&&i[e-1]==E){--e}if(e<i.length){i.splice(e,i.length-e)}}var d=Ag(sh,0,1,i.length,0);for(var c=0;c<i.length;++c){d[c]=i[c]}return d}
function fq(c){if(c.length==0||c[0]>fc&&c[c.length-1]>fc){return c}var a=c.replace(/^(\s*)/,E);var b=a.replace(/\s*$/,E);return b}
function iq(a){return Fp(this,a)}
function jq(){return Bp(this)}
_=String.prototype;_.eQ=iq;_.hC=jq;_.tI=2;function wp(){wp=wu;xp={};Ap={}}
function yp(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function Bp(c){wp();var a=gc+c;var b=Ap[a];if(b!=null){return b}b=xp[a];if(b==null){b=yp(c)}Cp();return Ap[a]=b}
function Cp(){if(zp==256){xp=Ap;Ap={};zp=0}++zp}
var xp,zp=0,Ap;function mq(){}
_=mq.prototype=new sp();_.tI=35;function qq(a,b){var c;while(a.t()){c=a.v();if(b==null?c==null:Fc(b,c)){return a}}return null}
function sq(a){throw new mq()}
function tq(b){var a;a=qq(this.u(),b);return !!a}
function pq(){}
_=pq.prototype=new op();_.k=sq;_.l=tq;_.tI=0;function dt(b){var a;a=Bq(new vq(),b);return ys(new rs(),b,a)}
function et(c){var a,b,d,e,f;if((c==null?null:c)===this){return true}if(!(c!=null&&bh(c.tI,16))){return false}e=ch(c,16);if(ch(this,16).d!=e.d){return false}for(b=xq(new wq(),Bq(new vq(),e).a);fs(b.a);){a=ch(gs(b.a),15);d=a.r();f=a.s();if(!(d==null?ch(this,16).c:d!=null&&bh(d.tI,1)?xr(ch(this,16),ch(d,1)):wr(ch(this,16),d,~~bd(d)))){return false}if(!vu(f,d==null?ch(this,16).b:d!=null&&bh(d.tI,1)?ch(this,16).e[gc+ch(d,1)]:tr(ch(this,16),d,~~bd(d)))){return false}}return true}
function ft(){var a,b,c;c=0;for(b=xq(new wq(),Bq(new vq(),ch(this,16)).a);fs(b.a);){a=ch(gs(b.a),15);c+=a.hC();c=~~c}return c}
function qs(){}
_=qs.prototype=new op();_.eQ=et;_.hC=ft;_.tI=0;function or(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.k(a[f])}}}}
function pr(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=mr(e,c.substring(1));a.k(b)}}}
function qr(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0}
function sr(b,a){return a==null?b.c:a!=null&&bh(a.tI,1)?xr(b,ch(a,1)):wr(b,a,~~bd(a))}
function vr(b,a){return a==null?b.b:a!=null&&bh(a.tI,1)?b.e[gc+ch(a,1)]:tr(b,a,~~bd(a))}
function tr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){return c.s()}}}return null}
function wr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){return true}}}return false}
function xr(b,a){return gc+a in b.e}
function Br(b,a,c){return a==null?zr(b,c):a!=null&&bh(a.tI,1)?Ar(b,ch(a,1),c):yr(b,a,c,~~bd(a))}
function yr(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(i.p(g,d)){var h=c.s();c.B(j);return h}}}else{a=i.a[e]=[]}var c=iu(new hu(),g,j);a.push(c);++i.d;return null}
function zr(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function Ar(d,a,e){var b,c=d.e;a=gc+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function Er(b,a){return !a?Dr(b):Cr(b,a,~~(a.$H||(a.$H=++hd)))}
function Cr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){if(a.length==1){delete h.a[e]}else{a.splice(f,1)}--h.d;return c.s()}}}return null}
function Dr(b){var a;a=b.b;b.b=null;if(b.c){b.c=false;--b.d}return a}
function Fr(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Fc(a,b)}
function uq(){}
_=uq.prototype=new qs();_.p=Fr;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function it(b){var a,c,d;if((b==null?null:b)===this){return true}if(!(b!=null&&bh(b.tI,17))){return false}c=ch(b,17);if(c.C()!=this.C()){return false}for(a=c.u();a.t();){d=a.v();if(!this.l(d)){return false}}return true}
function jt(){var a,b,c;a=0;for(b=this.u();b.t();){c=b.v();if(c!=null){a+=bd(c);a=~~a}}return a}
function gt(){}
_=gt.prototype=new pq();_.eQ=it;_.hC=jt;_.tI=36;function Bq(b,a){b.a=a;return b}
function Dq(d,c){var a,b,e;if(c!=null&&bh(c.tI,15)){a=ch(c,15);b=a.r();if(sr(d.a,b)){e=vr(d.a,b);return zt(a.s(),e)}}return false}
function Eq(a){return Dq(this,a)}
function Fq(){return xq(new wq(),this.a)}
function ar(){return this.a.d}
function vq(){}
_=vq.prototype=new gt();_.l=Eq;_.u=Fq;_.C=ar;_.tI=37;_.a=null;function xq(c,b){var a;c.b=b;a=lt(new kt());if(c.b.c){nt(a,cr(new br(),c.b))}pr(c.b,a);or(c.b,a);c.a=ds(new bs(),a);return c}
function zq(){return fs(this.a)}
function Aq(){return ch(gs(this.a),15)}
function wq(){}
_=wq.prototype=new op();_.t=zq;_.v=Aq;_.tI=0;_.a=null;_.b=null;function at(b){var a;if(b!=null&&bh(b.tI,15)){a=ch(b,15);if(vu(this.r(),a.r())&&vu(this.s(),a.s())){return true}}return false}
function bt(){var a,b;a=0;b=0;if(this.r()!=null){a=bd(this.r())}if(this.s()!=null){b=bd(this.s())}return a^b}
function Es(){}
_=Es.prototype=new op();_.eQ=at;_.hC=bt;_.tI=38;function cr(b,a){b.a=a;return b}
function er(){return null}
function fr(){return this.a.b}
function gr(a){return zr(this.a,a)}
function br(){}
_=br.prototype=new Es();_.r=er;_.s=fr;_.B=gr;_.tI=39;_.a=null;function ir(c,a,b){c.b=b;c.a=a;return c}
function kr(){return this.a}
function lr(){return this.b.e[gc+this.a]}
function mr(b,a){return ir(new hr(),a,b)}
function nr(a){return Ar(this.b,this.a,a)}
function hr(){}
_=hr.prototype=new Es();_.r=kr;_.s=lr;_.B=nr;_.tI=40;_.a=null;_.b=null;function ks(a){mt(this,this.C(),a);return true}
function ls(a,b){if(a<0||a>=b){os(a,b)}}
function ms(e){var a,b,c,d,f;if((e==null?null:e)===this){return true}if(!(e!=null&&bh(e.tI,3))){return false}f=ch(e,3);if(this.C()!=f.b){return false}c=ds(new bs(),ch(this,3));d=ds(new bs(),f);while(c.a<c.b.b){a=gs(c);b=gs(d);if(!(a==null?b==null:Fc(a,b))){return false}}return true}
function ns(){var a,b,c;b=1;a=ds(new bs(),ch(this,3));while(a.a<a.b.b){c=gs(a);b=31*b+(c==null?0:bd(c));b=~~b}return b}
function os(a,b){throw lp(new kp(),hc+a+jc+b)}
function ps(){return ds(new bs(),this)}
function as(){}
_=as.prototype=new pq();_.k=ks;_.eQ=ms;_.hC=ns;_.u=ps;_.tI=0;function ds(b,a){b.b=a;return b}
function fs(a){return a.a<a.b.b}
function gs(a){if(a.a>=a.b.b){throw new pu()}return pt(a.b,a.a++)}
function hs(){return this.a<this.b.b}
function is(){return gs(this)}
function bs(){}
_=bs.prototype=new op();_.t=hs;_.v=is;_.tI=0;_.a=0;_.b=null;function ys(b,a,c){b.a=a;b.b=c;return b}
function Bs(a){return sr(this.a,a)}
function Cs(){var a;return a=xq(new wq(),this.b.a),ts(new ss(),a)}
function Ds(){return this.b.a.d}
function rs(){}
_=rs.prototype=new gt();_.l=Bs;_.u=Cs;_.C=Ds;_.tI=41;_.a=null;_.b=null;function ts(a,b){a.a=b;return a}
function ws(){return fs(this.a.a)}
function xs(){var a;return a=ch(gs(this.a.a),15),a.r()}
function ss(){}
_=ss.prototype=new op();_.t=ws;_.v=xs;_.tI=0;_.a=null;function lt(a){a.a=Ag(rh,0,0,0,0);a.b=0;return a}
function nt(b,a){Cg(b.a,b.b++,a);return true}
function mt(c,a,b){if(a<0||a>c.b){os(a,c.b)}c.a.splice(a,0,b);++c.b}
function pt(b,a){ls(a,b.b);return b.a[a]}
function qt(c,b,a){for(;a<c.b;++a){if(vu(b,c.a[a])){return a}}return -1}
function rt(a){return Cg(this.a,this.b++,a),true}
function st(a){return qt(this,a,0)!=-1}
function tt(){return this.b}
function kt(){}
_=kt.prototype=new as();_.k=rt;_.l=st;_.C=tt;_.tI=42;_.a=null;_.b=0;function xt(a){qr(a);return a}
function zt(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Fc(a,b)}
function wt(){}
_=wt.prototype=new uq();_.tI=43;function Bt(a){a.a=xt(new wt());return a}
function Ct(c,a){var b;b=Br(c.a,a,c);return b==null}
function au(b){var a;return a=Br(this.a,b,this),a==null}
function bu(a){return sr(this.a,a)}
function cu(){var a;return a=xq(new wq(),dt(this.a).b.a),ts(new ss(),a)}
function du(){return this.a.d}
function At(){}
_=At.prototype=new gt();_.k=au;_.l=bu;_.u=cu;_.C=du;_.tI=44;_.a=null;function iu(b,a,c){b.a=a;b.b=c;return b}
function ku(){return this.a}
function lu(){return this.b}
function nu(b){var a;a=this.b;this.b=b;return a}
function hu(){}
_=hu.prototype=new Es();_.r=ku;_.s=lu;_.B=nu;_.tI=45;_.a=null;_.b=null;function pu(){}
_=pu.prototype=new sp();_.tI=46;function vu(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Fc(a,b)}
function xu(){}
_=xu.prototype=new lg();_.tI=0;_.a=null;_.b=null;_.c=null;_.d=null;function zu(l){var j,k;k=om(new mm());j=dl(new bl());l.a=po(new bo());l.c=gm(new cm());l.d=Ej(new zj());l.b=Ej(new zj());vd((qd(),l.d.j),kc);vd(l.b.j,lc);pm(k,l.a);pm(k,j);el(j,l.c);el(j,l.d);el(j,l.b);vj((wl(),zl(null)),k);return l}
function yu(){}
_=yu.prototype=new xu();_.tI=0;function uo(){!!$stats&&$stats({moduleName:$moduleName,subSystem:mc,evtGroup:nc,millis:(new Date()).getTime(),type:oc,className:pc});zu(new yu())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{uo()}catch(a){b(d)}else{uo()}}
function wu(){}
var sh=Do(qc,rc),qh=Do(sc,uc),rh=Do(qc,vc);$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (mindmapgadget) mindmapgadget.onScriptLoad(gwtOnLoad);})();