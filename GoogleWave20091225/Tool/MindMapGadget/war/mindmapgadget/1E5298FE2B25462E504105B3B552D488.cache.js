(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var E='',fc=' ',Ab='#fff',Fb=')',ac=',',jc=', Size: ',pb='0',cc='2d',gc=':',A='DOMMouseScroll',lc='Delete',vb='INPUT',hc='Index: ',vc='Object;',rc='String;',kc='Submit',uc='Widget;',sc='[Lcom.google.gwt.user.client.ui.',qc='[Ljava.lang.',tb='align',dc='backgroundColor',q='blur',lb='bottom',bb='button',yb='canvas',qb='cellPadding',ob='cellSpacing',jb='center',r='change',cb='className',C='click',B='contextmenu',hb='dblclick',y='error',sb='focus',ec='g',db='gwt-Button',zb='gwt-Canvas',xb='gwt-TextBox',Cb='height',Db='keydown',ic='keypress',tc='keyup',D='left',wc='load',xc='losecapture',mb='middle',nc='moduleStartup',s='mousedown',t='mousemove',u='mouseout',v='mouseover',w='mouseup',z='mousewheel',oc='onModuleLoadStart',pc='org.kyotogtug.client.MindMapGadget',ab='position',bc='rgb(',Eb='rgba(',kb='right',x='scroll',mc='startup',eb='submit',gb='table',ib='tbody',rb='td',wb='text',F='top',nb='tr',fb='type',ub='verticalAlign',Bb='width';var _;function bp(a){return this===(a==null?null:a)}
function cp(){return this.$H||(this.$H=++hd)}
function Fo(){}
_=Fo.prototype={};_.eQ=bp;_.hC=cp;_.tM=hu;_.tI=1;function Fc(b,a){return b.tM==hu||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function bd(a){return a.tM==hu||a.tI==2?a.hC():a.$H||(a.$H=++hd)}
var hd=0;function qd(){qd=hu;ld();new jd()}
function ud(a){var b=a.parentNode;if(b==null){return null}if(b.nodeType!=1)b=null;return b}
function vd(a,b){while(a.firstChild){a.removeChild(a.firstChild)}if(b!=null){a.appendChild(a.ownerDocument.createTextNode(b))}}
function id(){}
_=id.prototype=new Fo();_.tI=0;function nd(){nd=hu;qd()}
function md(){}
_=md.prototype=new id();_.tI=0;function ld(){ld=hu;nd()}
function jd(){}
_=jd.prototype=new md();_.tI=0;function mf(){}
_=mf.prototype=new Fo();_.tI=0;_.a=false;_.b=null;function bf(a){yl()}
function cf(b){var a;if(af){a=new Ee();hg(b,a)}}
function df(){return af}
function Ee(){}
_=Ee.prototype=new mf();_.m=bf;_.q=df;_.tI=0;var af=null;function jf(){}
_=jf.prototype=new Fo();_.tI=0;function of(a){a.a=++rf;return a}
function qf(){return this.a}
function nf(){}
_=nf.prototype=new Fo();_.hC=qf;_.tI=0;_.a=0;var rf=0;function dg(b,c,a){if(b.b>0){fg(b,wf(new vf(),b,c,a))}else{Cf(b.d,c,a)}return new jf()}
function fg(b,a){if(!b.a){b.a=Cs(new Bs())}Es(b.a,a)}
function hg(c,a){var b;if(a.a){a.a=false;a.b=null}b=a.b;a.b=c.e;try{++c.b;Ef(c.d,a,c.c)}finally{--c.b;if(c.b==0){ig(c)}}if(b==null){a.a=true;a.b=null}else{a.b=b}}
function ig(c){var a,b;if(c.a){try{for(b=ur(new sr(),c.a);b.a<b.b.b;){a=ch(xr(b),2);Cf(a.a.d,a.c,a.b)}}finally{c.a=null}}}
function uf(){}
_=uf.prototype=new Fo();_.tI=0;_.a=null;_.b=0;_.c=false;_.d=null;_.e=null;function wf(b,a,d,c){b.a=a;b.c=d;b.b=c;return b}
function vf(){}
_=vf.prototype=new Fo();_.tI=7;_.a=null;_.b=null;_.c=null;function Bf(a){a.a=it(new ht());return a}
function Cf(c,d,a){var b;b=ch(gr(c.a,d),3);if(!b){b=Cs(new Bs());mr(c.a,d,b)}Cg(b.a,b.b++,a)}
function Ef(i,e,h){var d,f,g,j,a,b,c;j=e.q();d=(a=ch(gr(i.a,j),3),!a?0:a.b);if(h){for(g=d-1;g>=0;--g){f=(b=ch(gr(i.a,j),3),ch((Cr(g,b.b),b.a[g]),12));e.m(f)}}else{for(g=0;g<d;++g){f=(c=ch(gr(i.a,j),3),ch((Cr(g,c.b),c.a[g]),12));e.m(f)}}}
function zf(){}
_=zf.prototype=new Fo();_.tI=0;function lg(){}
_=lg.prototype=new Fo();_.tI=0;function zg(d,c){var a=new Array(c);if(d>0){var e=[null,0,false,[0,0]][d];for(var b=0;b<c;++b){a[b]=e}}return a}
function Ag(a,f,c,b,e){var d;d=zg(e,b);qg();vg(d,rg,sg);d.tI=f;d.qI=c;return d}
function Cg(a,b,c){if(c!=null){if(a.qI>0&&!ah(c.tI,a.qI)){throw new ho()}if(a.qI<0&&(c.tM==hu||c.tI==2)){throw new ho()}}return a[b]=c}
function og(){}
_=og.prototype=new Fo();_.tI=0;_.length=0;_.qI=0;function qg(){qg=hu;rg=[];sg=[];tg(new og(),rg,sg)}
function tg(e,a,b){var c=0,f;for(var d in e){if(f=e[d]){a[c]=d;b[c]=f;++c}}}
function vg(a,c,d){qg();for(var e=0,b=c.length;e<b;++e){a[c[e]]=d[e]}}
var rg,sg;function bh(b,a){return b&&!!nh[b][a]}
function ah(b,a){return b&&nh[b][a]}
function ch(b,a){if(b!=null&&!ah(b.tI,a)){throw new lo()}return b}
var nh=[{},{},{1:1,8:1,9:1,10:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{2:1},{4:1},{6:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1,14:1},{12:1},{4:1,5:1,6:1,7:1,14:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{9:1},{8:1,13:1},{17:1},{17:1},{15:1},{15:1},{15:1},{17:1},{3:1,8:1},{8:1,16:1},{8:1,17:1},{15:1},{8:1,13:1},{11:1}];function di(b,a,c){var d;if(a==hi){if(nj((qd(),b).type)==8192){hi=null}}d=ci;ci=b;try{c.w(b)}finally{ci=d}}
function ji(a,b){pj();a.__eventBits=b;a.onclick=b&1?lj:null;a.ondblclick=b&2?lj:null;a.onmousedown=b&4?lj:null;a.onmouseup=b&8?lj:null;a.onmouseover=b&16?lj:null;a.onmouseout=b&32?lj:null;a.onmousemove=b&64?lj:null;a.onkeydown=b&128?lj:null;a.onkeypress=b&256?lj:null;a.onkeyup=b&512?lj:null;a.onchange=b&1024?lj:null;a.onfocus=b&2048?lj:null;a.onblur=b&4096?lj:null;a.onlosecapture=b&8192?lj:null;a.onscroll=b&16384?lj:null;a.onload=b&32768?lj:null;a.onerror=b&65536?lj:null;a.onmousewheel=b&131072?lj:null;a.oncontextmenu=b&262144?lj:null}
var ci=null,hi=null;function wi(a){Ei();return xi(af?af:(af=of(new nf())),a)}
function xi(b,a){return dg(Ci(),b,a)}
function zi(){if(yi){cf(Ci())}}
function Ai(){var a;if(yi){a=(oi(),new mi());Bi(a);return null}return null}
function Bi(a){if(Di){hg(Di,a)}}
function Ci(){if(!Di){Di=ti(new si())}return Di}
function Ei(){if(!yi){vj();yi=true}}
var yi=false,Di=null;function oi(){oi=hu;pi=of(new nf())}
function qi(a){null.D()}
function ri(){return pi}
function mi(){}
_=mi.prototype=new mf();_.m=qi;_.q=ri;_.tI=0;var pi;function ti(a){a.d=Bf(new zf());a.e=null;a.c=false;return a}
function si(){}
_=si.prototype=new uf();_.tI=8;function nj(a){switch(a){case q:return 4096;case r:return 1024;case C:return 1;case hb:return 2;case sb:return 2048;case Db:return 128;case ic:return 256;case tc:return 512;case wc:return 32768;case xc:return 8192;case s:return 4;case t:return 64;case u:return 32;case v:return 16;case w:return 8;case x:return 16384;case y:return 65536;case z:return 131072;case A:return 131072;case B:return 262144;}}
function pj(){if(!rj){hj();rj=true}}
function sj(a){return !(a!=null&&(a.tM!=hu&&a.tI!=2))&&(a!=null&&bh(a.tI,5))}
var rj=false;function hj(){kj=function(b){if(jj(b)){var a=ij;if(a&&a.__listener){if(sj(a.__listener)){di(b,a,a.__listener);b.stopPropagation()}}}};jj=function(a){return true};lj=function(b){var c,a=this;while(a&&!(c=a.__listener)){a=a.parentNode}if(a&&a.nodeType!=1){a=null}if(c){if(sj(c)){di(b,a,c)}}};$wnd.addEventListener(C,kj,true);$wnd.addEventListener(hb,kj,true);$wnd.addEventListener(s,kj,true);$wnd.addEventListener(w,kj,true);$wnd.addEventListener(t,kj,true);$wnd.addEventListener(v,kj,true);$wnd.addEventListener(u,kj,true);$wnd.addEventListener(z,kj,true);$wnd.addEventListener(Db,jj,true);$wnd.addEventListener(tc,jj,true);$wnd.addEventListener(ic,jj,true)}
var ij=null,jj=null,kj=null,lj=null;function vj(){var d=$wnd.onbeforeunload;var e=$wnd.onunload;$wnd.onbeforeunload=function(a){var c,b;try{c=Ai()}finally{b=d&&d(a)}if(c!=null){return c}if(b!=null){return b}};$wnd.onunload=function(a){try{zi()}finally{e&&e(a);$wnd.onresize=null;$wnd.onscroll=null;$wnd.onbeforeunload=null;$wnd.onunload=null}}}
function km(){}
_=km.prototype=new Fo();_.tI=9;_.j=null;function fn(b){var a;if(b.g){throw new yo()}b.g=true;b.j.__listener=b;a=b.h;b.h=-1;if(a>0){ln(b,a)}b.n();b.x()}
function gn(c,a){var b;switch(nj((qd(),a).type)){case 16:case 32:b=a.relatedTarget;if(!!b&&c.j.contains(b)){return}}}
function hn(a){if(!a.g){throw new yo()}try{a.y()}finally{a.o();a.j.__listener=null;a.g=false}}
function jn(a){if(!a.i){xl();if(dr(Cl.a,a)){hn(a);pr(Cl.a,a)!=null}}else if(a.i){a.i.z(a)}else if(a.i){throw new yo()}}
function kn(c,b){var a;a=c.i;if(!b){if(!!a&&a.g){hn(c)}c.i=null}else{if(a){throw new yo()}c.i=b;if(b.g){fn(c)}}}
function ln(b,a){if(b.h==-1){ji(b.j,a|(b.j.__eventBits||0))}else{b.h|=a}}
function mn(){}
function nn(){}
function on(a){gn(this,a)}
function pn(){}
function qn(){}
function um(){}
_=um.prototype=new km();_.n=mn;_.o=nn;_.w=on;_.x=pn;_.y=qn;_.tI=10;_.g=false;_.h=0;_.i=null;function ml(){var a,b;for(b=this.u();b.a<b.b.b-1;){a=Am(b);fn(a)}}
function nl(){var a,b;for(b=this.u();b.a<b.b.b-1;){a=Am(b);hn(a)}}
function ol(){}
function pl(){}
function kl(){}
_=kl.prototype=new um();_.n=ml;_.o=nl;_.x=ol;_.y=pl;_.tI=11;function ik(c,a,b){jn(a);Em(c.f,a);b.appendChild(a.j);kn(a,c)}
function kk(b,c){var a;if(c.i!=b){return false}kn(c,null);a=c.j;ud((qd(),a)).removeChild(a);dn(b.f,c);return true}
function lk(){return ym(new wm(),this.f)}
function mk(a){return kk(this,a)}
function gk(){}
_=gk.prototype=new kl();_.u=lk;_.z=mk;_.tI=12;function xj(a,b){ik(a,b,a.j)}
function zj(a){a.style[D]=E;a.style[F]=E;a.style[ab]=E}
function Aj(b){var a;a=kk(this,b);if(a){zj(b.j)}return a}
function wj(){}
_=wj.prototype=new gk();_.z=Aj;_.tI=13;function nk(){}
_=nk.prototype=new um();_.tI=14;function Dj(b,a){b.j=a;b.j.tabIndex=0;return b}
function Cj(){}
_=Cj.prototype=new nk();_.tI=15;function ak(a){Dj(a,(qd(),$doc).createElement(bb));ck(a.j);a.j[cb]=db;return a}
function ck(b){if(b.type==eb){try{b.setAttribute(fb,bb)}catch(a){}}}
function Bj(){}
_=Bj.prototype=new Cj();_.tI=16;function ek(a){a.f=Dm(new vm());a.e=(qd(),$doc).createElement(gb);a.d=$doc.createElement(ib);a.e.appendChild(a.d);a.j=a.e;return a}
function dk(){}
_=dk.prototype=new gk();_.tI=17;_.d=null;_.e=null;function wk(){wk=hu;uk(new tk(),jb);yk=uk(new tk(),D);uk(new tk(),kb);xk=yk}
var xk,yk;function uk(b,a){b.a=a;return b}
function tk(){}
_=tk.prototype=new Fo();_.tI=0;_.a=null;function Fk(){Fk=hu;Dk(new Ck(),lb);Dk(new Ck(),mb);al=Dk(new Ck(),F)}
var al;function Dk(a,b){a.a=b;return a}
function Ck(){}
_=Ck.prototype=new Fo();_.tI=0;_.a=null;function el(a){ek(a);a.a=(wk(),xk);a.c=(Fk(),al);a.b=(qd(),$doc).createElement(nb);a.d.appendChild(a.b);a.e[ob]=pb;a.e[qb]=pb;return a}
function fl(c,d){var b,a;b=(a=(qd(),$doc).createElement(rb),(a[tb]=c.a.a,undefined),(a.style[ub]=c.c.a,undefined),a);c.b.appendChild(b);jn(d);Em(c.f,d);b.appendChild(d.j);kn(d,c)}
function il(c){var a,b;b=ud((qd(),c.j));a=kk(this,c);if(a){this.b.removeChild(b)}return a}
function cl(){}
_=cl.prototype=new dk();_.z=il;_.tI=18;_.b=null;function xl(){xl=hu;Bl=it(new ht());Cl=mt(new lt())}
function wl(b,a){xl();b.f=Dm(new vm());b.j=a;fn(b);return b}
function yl(){var b,a;xl();var c,d;for(d=(b=iq(new hq(),us(Cl.a).b.a),es(new ds(),b));wr(d.a.a);){c=ch((a=ch(xr(d.a.a),15),a.r()),7);if(c.g){hn(c)}}br(Cl.a);br(Bl)}
function Al(a){xl();var b;b=ch(gr(Bl,a),14);if(b){return b}if(Bl.d==0){wi(new rl())}b=ul(new tl());mr(Bl,a,b);nt(Cl,b);return b}
function ql(){}
_=ql.prototype=new wj();_.tI=19;var Bl,Cl;function rl(){}
_=rl.prototype=new Fo();_.tI=20;function vl(){vl=hu;xl()}
function ul(a){vl();wl(a,$doc.body);return a}
function tl(){}
_=tl.prototype=new ql();_.tI=21;function gm(a){var b;b=nj((qd(),a).type);if((b&896)!=0){gn(this,a)}else{gn(this,a)}}
function em(){}
_=em.prototype=new nk();_.w=gm;_.tI=22;function hm(b){var a;im(b,(a=(qd(),$doc).createElement(vb),a.type=wb,a),xb);return b}
function im(c,a,b){c.j=a;c.j.tabIndex=0;if(b!=null){c.j[cb]=b}return c}
function dm(){}
_=dm.prototype=new em();_.tI=23;function pm(a){ek(a);a.a=(wk(),xk);a.b=(Fk(),al);a.e[ob]=pb;a.e[qb]=pb;return a}
function qm(c,e){var b,d,a;d=(qd(),$doc).createElement(nb);b=(a=$doc.createElement(rb),(a[tb]=c.a.a,undefined),(a.style[ub]=c.b.a,undefined),a);d.appendChild(b);c.d.appendChild(d);jn(e);Em(c.f,e);b.appendChild(e.j);kn(e,c)}
function tm(c){var a,b;b=ud((qd(),c.j));a=kk(this,c);if(a){this.d.removeChild(ud(b))}return a}
function nm(){}
_=nm.prototype=new dk();_.z=tm;_.tI=24;function Dm(a){a.a=Ag(qh,0,7,4,0);return a}
function Em(a,b){bn(a,b,a.b)}
function an(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function bn(d,e,a){var b,c;if(a<0||a>d.b){throw new Bo()}if(d.b==d.a.length){c=Ag(qh,0,7,d.a.length*2,0);for(b=0;b<d.a.length;++b){Cg(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){Cg(d.a,b,d.a[b-1])}Cg(d.a,a,e)}
function cn(c,b){var a;if(b<0||b>=c.b){throw new Bo()}--c.b;for(a=b;a<c.b;++a){Cg(c.a,a,c.a[a+1])}Cg(c.a,c.b,null)}
function dn(b,c){var a;a=an(b,c);if(a==-1){throw new au()}cn(b,a)}
function vm(){}
_=vm.prototype=new Fo();_.tI=0;_.a=null;_.b=0;function ym(b,a){b.b=a;return b}
function Am(a){if(a.a>=a.b.b){throw new au()}return a.b.a[++a.a]}
function Bm(){return this.a<this.b.b-1}
function Cm(){return Am(this)}
function wm(){}
_=wm.prototype=new Fo();_.t=Bm;_.v=Cm;_.tI=0;_.a=-1;_.b=null;function Fn(a){a.a=new wn();a.j=(qd(),$doc).createElement(yb);An(a.a,a);a.j[cb]=zb;co(a,Ab);a.a.c.setAttribute(Bb,E+300);a.a.c.setAttribute(Cb,E+150);return a}
function co(d,a){var b,c;if(a==null){throw new wo()}a=wp(a);if(a.indexOf(Eb)==0){b=a.indexOf(Fb,12);if(b>-1){c=up(a.substr(5,b-5),ac,0);if(c.length>=3){a=bc+c[0]+ac+c[1]+ac+c[2]+Fb}}}Bn(d.a,a)}
function eo(a){if(!a){throw new wo()}nj((qd(),a).type)}
function un(){}
_=un.prototype=new um();_.w=eo;_.tI=25;function An(b,a){b.c=a.j;b.b=b.c.getContext(cc)}
function Bn(b,a){b.a=a;b.c.style[dc]=b.a}
function vn(){}
_=vn.prototype=new Fo();_.tI=0;_.a=null;_.b=null;_.c=null;function wn(){}
_=wn.prototype=new vn();_.tI=0;function Bp(){}
_=Bp.prototype=new Fo();_.tI=3;function uo(){}
_=uo.prototype=new Bp();_.tI=4;function dp(){}
_=dp.prototype=new uo();_.tI=5;function ho(){}
_=ho.prototype=new dp();_.tI=27;function oo(c,a){var b;b=new ko();return b}
function ko(){}
_=ko.prototype=new Fo();_.tI=0;function lo(){}
_=lo.prototype=new dp();_.tI=30;function wo(){}
_=wo.prototype=new dp();_.tI=31;function yo(){}
_=yo.prototype=new dp();_.tI=32;function Co(b,a){return b}
function Bo(){}
_=Bo.prototype=new dp();_.tI=33;function qp(b,a){if(!(a!=null&&bh(a.tI,1))){return false}return String(b)==a}
function up(k,j,h){var a=new RegExp(j,ec);var i=[];var b=0;var l=k;var f=null;while(true){var g=a.exec(l);if(g==null||(l==E||b==h-1&&h>0)){i[b]=l;break}else{i[b]=l.substring(0,g.index);l=l.substring(g.index+g[0].length,l.length);a.lastIndex=0;if(f==l){i[b]=l.substring(0,1);l=l.substring(1)}f=l;b++}}if(h==0){var e=i.length;while(e>0&&i[e-1]==E){--e}if(e<i.length){i.splice(e,i.length-e)}}var d=Ag(sh,0,1,i.length,0);for(var c=0;c<i.length;++c){d[c]=i[c]}return d}
function wp(c){if(c.length==0||c[0]>fc&&c[c.length-1]>fc){return c}var a=c.replace(/^(\s*)/,E);var b=a.replace(/\s*$/,E);return b}
function zp(a){return qp(this,a)}
function Ap(){return mp(this)}
_=String.prototype;_.eQ=zp;_.hC=Ap;_.tI=2;function hp(){hp=hu;ip={};lp={}}
function jp(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function mp(c){hp();var a=gc+c;var b=lp[a];if(b!=null){return b}b=ip[a];if(b==null){b=jp(c)}np();return lp[a]=b}
function np(){if(kp==256){ip=lp;lp={};kp=0}++kp}
var ip,kp=0,lp;function Dp(){}
_=Dp.prototype=new dp();_.tI=35;function bq(a,b){var c;while(a.t()){c=a.v();if(b==null?c==null:Fc(b,c)){return a}}return null}
function dq(a){throw new Dp()}
function eq(b){var a;a=bq(this.u(),b);return !!a}
function aq(){}
_=aq.prototype=new Fo();_.k=dq;_.l=eq;_.tI=0;function us(b){var a;a=mq(new gq(),b);return js(new cs(),b,a)}
function vs(c){var a,b,d,e,f;if((c==null?null:c)===this){return true}if(!(c!=null&&bh(c.tI,16))){return false}e=ch(c,16);if(ch(this,16).d!=e.d){return false}for(b=iq(new hq(),mq(new gq(),e).a);wr(b.a);){a=ch(xr(b.a),15);d=a.r();f=a.s();if(!(d==null?ch(this,16).c:d!=null&&bh(d.tI,1)?ir(ch(this,16),ch(d,1)):hr(ch(this,16),d,~~bd(d)))){return false}if(!gu(f,d==null?ch(this,16).b:d!=null&&bh(d.tI,1)?ch(this,16).e[gc+ch(d,1)]:er(ch(this,16),d,~~bd(d)))){return false}}return true}
function ws(){var a,b,c;c=0;for(b=iq(new hq(),mq(new gq(),ch(this,16)).a);wr(b.a);){a=ch(xr(b.a),15);c+=a.hC();c=~~c}return c}
function bs(){}
_=bs.prototype=new Fo();_.eQ=vs;_.hC=ws;_.tI=0;function Fq(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.k(a[f])}}}}
function ar(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=Dq(e,c.substring(1));a.k(b)}}}
function br(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0}
function dr(b,a){return a==null?b.c:a!=null&&bh(a.tI,1)?ir(b,ch(a,1)):hr(b,a,~~bd(a))}
function gr(b,a){return a==null?b.b:a!=null&&bh(a.tI,1)?b.e[gc+ch(a,1)]:er(b,a,~~bd(a))}
function er(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){return c.s()}}}return null}
function hr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){return true}}}return false}
function ir(b,a){return gc+a in b.e}
function mr(b,a,c){return a==null?kr(b,c):a!=null&&bh(a.tI,1)?lr(b,ch(a,1),c):jr(b,a,c,~~bd(a))}
function jr(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(i.p(g,d)){var h=c.s();c.A(j);return h}}}else{a=i.a[e]=[]}var c=zt(new yt(),g,j);a.push(c);++i.d;return null}
function kr(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function lr(d,a,e){var b,c=d.e;a=gc+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function pr(b,a){return !a?or(b):nr(b,a,~~(a.$H||(a.$H=++hd)))}
function nr(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.r();if(h.p(g,d)){if(a.length==1){delete h.a[e]}else{a.splice(f,1)}--h.d;return c.s()}}}return null}
function or(b){var a;a=b.b;b.b=null;if(b.c){b.c=false;--b.d}return a}
function qr(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Fc(a,b)}
function fq(){}
_=fq.prototype=new bs();_.p=qr;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function zs(b){var a,c,d;if((b==null?null:b)===this){return true}if(!(b!=null&&bh(b.tI,17))){return false}c=ch(b,17);if(c.B()!=this.B()){return false}for(a=c.u();a.t();){d=a.v();if(!this.l(d)){return false}}return true}
function As(){var a,b,c;a=0;for(b=this.u();b.t();){c=b.v();if(c!=null){a+=bd(c);a=~~a}}return a}
function xs(){}
_=xs.prototype=new aq();_.eQ=zs;_.hC=As;_.tI=36;function mq(b,a){b.a=a;return b}
function oq(d,c){var a,b,e;if(c!=null&&bh(c.tI,15)){a=ch(c,15);b=a.r();if(dr(d.a,b)){e=gr(d.a,b);return kt(a.s(),e)}}return false}
function pq(a){return oq(this,a)}
function qq(){return iq(new hq(),this.a)}
function rq(){return this.a.d}
function gq(){}
_=gq.prototype=new xs();_.l=pq;_.u=qq;_.B=rq;_.tI=37;_.a=null;function iq(c,b){var a;c.b=b;a=Cs(new Bs());if(c.b.c){Es(a,tq(new sq(),c.b))}ar(c.b,a);Fq(c.b,a);c.a=ur(new sr(),a);return c}
function kq(){return wr(this.a)}
function lq(){return ch(xr(this.a),15)}
function hq(){}
_=hq.prototype=new Fo();_.t=kq;_.v=lq;_.tI=0;_.a=null;_.b=null;function rs(b){var a;if(b!=null&&bh(b.tI,15)){a=ch(b,15);if(gu(this.r(),a.r())&&gu(this.s(),a.s())){return true}}return false}
function ss(){var a,b;a=0;b=0;if(this.r()!=null){a=bd(this.r())}if(this.s()!=null){b=bd(this.s())}return a^b}
function ps(){}
_=ps.prototype=new Fo();_.eQ=rs;_.hC=ss;_.tI=38;function tq(b,a){b.a=a;return b}
function vq(){return null}
function wq(){return this.a.b}
function xq(a){return kr(this.a,a)}
function sq(){}
_=sq.prototype=new ps();_.r=vq;_.s=wq;_.A=xq;_.tI=39;_.a=null;function zq(c,a,b){c.b=b;c.a=a;return c}
function Bq(){return this.a}
function Cq(){return this.b.e[gc+this.a]}
function Dq(b,a){return zq(new yq(),a,b)}
function Eq(a){return lr(this.b,this.a,a)}
function yq(){}
_=yq.prototype=new ps();_.r=Bq;_.s=Cq;_.A=Eq;_.tI=40;_.a=null;_.b=null;function Br(a){Ds(this,this.B(),a);return true}
function Cr(a,b){if(a<0||a>=b){Fr(a,b)}}
function Dr(e){var a,b,c,d,f;if((e==null?null:e)===this){return true}if(!(e!=null&&bh(e.tI,3))){return false}f=ch(e,3);if(this.B()!=f.b){return false}c=ur(new sr(),ch(this,3));d=ur(new sr(),f);while(c.a<c.b.b){a=xr(c);b=xr(d);if(!(a==null?b==null:Fc(a,b))){return false}}return true}
function Er(){var a,b,c;b=1;a=ur(new sr(),ch(this,3));while(a.a<a.b.b){c=xr(a);b=31*b+(c==null?0:bd(c));b=~~b}return b}
function Fr(a,b){throw Co(new Bo(),hc+a+jc+b)}
function as(){return ur(new sr(),this)}
function rr(){}
_=rr.prototype=new aq();_.k=Br;_.eQ=Dr;_.hC=Er;_.u=as;_.tI=0;function ur(b,a){b.b=a;return b}
function wr(a){return a.a<a.b.b}
function xr(a){if(a.a>=a.b.b){throw new au()}return at(a.b,a.a++)}
function yr(){return this.a<this.b.b}
function zr(){return xr(this)}
function sr(){}
_=sr.prototype=new Fo();_.t=yr;_.v=zr;_.tI=0;_.a=0;_.b=null;function js(b,a,c){b.a=a;b.b=c;return b}
function ms(a){return dr(this.a,a)}
function ns(){var a;return a=iq(new hq(),this.b.a),es(new ds(),a)}
function os(){return this.b.a.d}
function cs(){}
_=cs.prototype=new xs();_.l=ms;_.u=ns;_.B=os;_.tI=41;_.a=null;_.b=null;function es(a,b){a.a=b;return a}
function hs(){return wr(this.a.a)}
function is(){var a;return a=ch(xr(this.a.a),15),a.r()}
function ds(){}
_=ds.prototype=new Fo();_.t=hs;_.v=is;_.tI=0;_.a=null;function Cs(a){a.a=Ag(rh,0,0,0,0);a.b=0;return a}
function Es(b,a){Cg(b.a,b.b++,a);return true}
function Ds(c,a,b){if(a<0||a>c.b){Fr(a,c.b)}c.a.splice(a,0,b);++c.b}
function at(b,a){Cr(a,b.b);return b.a[a]}
function bt(c,b,a){for(;a<c.b;++a){if(gu(b,c.a[a])){return a}}return -1}
function ct(a){return Cg(this.a,this.b++,a),true}
function dt(a){return bt(this,a,0)!=-1}
function et(){return this.b}
function Bs(){}
_=Bs.prototype=new rr();_.k=ct;_.l=dt;_.B=et;_.tI=42;_.a=null;_.b=0;function it(a){br(a);return a}
function kt(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Fc(a,b)}
function ht(){}
_=ht.prototype=new fq();_.tI=43;function mt(a){a.a=it(new ht());return a}
function nt(c,a){var b;b=mr(c.a,a,c);return b==null}
function rt(b){var a;return a=mr(this.a,b,this),a==null}
function st(a){return dr(this.a,a)}
function tt(){var a;return a=iq(new hq(),us(this.a).b.a),es(new ds(),a)}
function ut(){return this.a.d}
function lt(){}
_=lt.prototype=new xs();_.k=rt;_.l=st;_.u=tt;_.B=ut;_.tI=44;_.a=null;function zt(b,a,c){b.a=a;b.b=c;return b}
function Bt(){return this.a}
function Ct(){return this.b}
function Et(b){var a;a=this.b;this.b=b;return a}
function yt(){}
_=yt.prototype=new ps();_.r=Bt;_.s=Ct;_.A=Et;_.tI=45;_.a=null;_.b=null;function au(){}
_=au.prototype=new dp();_.tI=46;function gu(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Fc(a,b)}
function iu(){}
_=iu.prototype=new lg();_.tI=0;_.a=null;_.b=null;_.c=null;_.d=null;function ku(l){var j,k;k=pm(new nm());j=el(new cl());l.a=Fn(new un());l.c=hm(new dm());l.d=ak(new Bj());l.b=ak(new Bj());vd((qd(),l.d.j),kc);vd(l.b.j,lc);qm(k,l.a);qm(k,j);fl(j,l.c);fl(j,l.d);fl(j,l.b);xj((xl(),Al(null)),k);return l}
function ju(){}
_=ju.prototype=new iu();_.tI=0;function fo(){!!$stats&&$stats({moduleName:$moduleName,subSystem:mc,evtGroup:nc,millis:(new Date()).getTime(),type:oc,className:pc});ku(new ju())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{fo()}catch(a){b(d)}else{fo()}}
function hu(){}
var sh=oo(qc,rc),qh=oo(sc,uc),rh=oo(qc,vc);$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (mindmapgadget) mindmapgadget.onScriptLoad(gwtOnLoad);})();