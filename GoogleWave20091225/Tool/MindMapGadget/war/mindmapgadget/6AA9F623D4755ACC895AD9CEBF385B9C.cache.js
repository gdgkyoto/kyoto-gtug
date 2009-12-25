(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var sb='',hd=' ',pc='#fff',jd='$',rc=')',sc=',',nd=', Size: ',ec='0',kd=':',ab='DOMMouseScroll',pd='Delete',jc='INPUT',ld='Index: ',v='Object;',vd='String;',od='Submit',u='Widget;',wd='[D',t='[Lcom.google.gwt.user.client.ui.',ud='[Ljava.lang.',id='\\',r='__gwt_initWindowCloseHandler',hc='align',ad='backgroundColor',F='blur',Fb='bottom',wb='button',nc='canvas',fc='cellPadding',dc='cellSpacing',Db='center',kb='change',xb='className',vb='click',bb='contextmenu',Bc='cursor',ac='dblclick',Cc='default',vc='display',D='error',cd='fillcolor',lc='focus',pb='function',qb='function ',gd='g',yb='gwt-Button',oc='gwt-Canvas',mc='gwt-TextBox',dd='height',zc='hidden',xc='inline-block',wc='keydown',bd='keypress',md='keyup',rb='left',s='load',w='losecapture',bc='middle',rd='moduleStartup',x='mousedown',y='mousemove',z='mouseout',A='mouseover',B='mouseup',E='mousewheel',sd='onModuleLoadStart',mb='onblur',cb='onclick',ob='oncontextmenu',nb='ondblclick',lb='onfocus',hb='onkeydown',ib='onkeypress',jb='onkeyup',db='onmousedown',fb='onmousemove',eb='onmouseup',gb='onmousewheel',td='org.kyotogtug.client.MindMapGadget',yc='overflow',ub='position',ed='px',uc='relative',tc='rgb(',qc='rgba(',Eb='right',q='script',C='scroll',qd='startup',zb='submit',Bb='table',Cb='tbody',gc='td',kc='text',Ac='textAlign',tb='top',cc='tr',Ab='type',Ec='urn:schemas-microsoft-com:vml',ic='verticalAlign',Dc='vml',Fc='vml\\:*{behavior:url(#default#VML);}',fd='width';var _;function rq(a){return this===(a==null?null:a)}
function sq(){return this.$H||(this.$H=++ge)}
function pq(){}
_=pq.prototype={};_.eQ=rq;_.hC=sq;_.tM=hw;_.tI=1;function Ed(b,a){return b.tM==hw||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function ae(a){return a.tM==hw||a.tI==2?a.hC():a.$H||(a.$H=++ge)}
var ge=0;function se(){se=hw;ke();new ie()}
function ve(a,c){var b;b=a.createElement(q);b.text=c;return b}
function he(){}
_=he.prototype=new pq();_.tI=0;function me(){me=hw;se()}
function pe(b,a){return b===a||b.contains(a)}
function le(){}
_=le.prototype=new he();_.tI=0;var re=null;function ke(){ke=hw;me()}
function ie(){}
_=ie.prototype=new le();_.tI=0;function tg(){}
_=tg.prototype=new pq();_.tI=0;_.a=false;_.b=null;function jg(a){cn()}
function kg(b){var a;if(ig){a=new gg();oh(b,a)}}
function lg(){return ig}
function gg(){}
_=gg.prototype=new tg();_.n=jg;_.r=lg;_.tI=0;var ig=null;function qg(){}
_=qg.prototype=new pq();_.tI=0;function vg(a){a.a=++yg;return a}
function xg(){return this.a}
function ug(){}
_=ug.prototype=new pq();_.hC=xg;_.tI=0;_.a=0;var yg=0;function kh(b,c,a){if(b.b>0){mh(b,Dg(new Cg(),b,c,a))}else{dh(b.d,c,a)}return new qg()}
function mh(b,a){if(!b.a){b.a=qu(new pu())}su(b.a,a)}
function oh(c,a){var b;if(a.a){a.a=false;a.b=null}b=a.b;a.b=c.e;try{++c.b;fh(c.d,a,c.c)}finally{--c.b;if(c.b==0){ph(c)}}if(b==null){a.a=true;a.b=null}else{a.b=b}}
function ph(c){var a,b;if(c.a){try{for(b=ht(new ft(),c.a);b.a<b.b.D();){a=ji(kt(b),2);dh(a.a.d,a.c,a.b)}}finally{c.a=null}}}
function Bg(){}
_=Bg.prototype=new pq();_.tI=0;_.a=null;_.b=0;_.c=false;_.d=null;_.e=null;function Dg(b,a,d,c){b.a=a;b.c=d;b.b=c;return b}
function Cg(){}
_=Cg.prototype=new pq();_.tI=7;_.a=null;_.b=null;_.c=null;function ch(a){a.a=Eu(new Du());return a}
function dh(c,d,a){var b;b=ji(zs(c.a,d),3);if(!b){b=qu(new pu());Fs(c.a,d,b)}di(b.a,b.b++,a)}
function fh(i,e,h){var d,f,g,j,a,b,c;j=e.r();d=(a=ji(zs(i.a,j),3),!a?0:a.b);if(h){for(g=d-1;g>=0;--g){f=(b=ji(zs(i.a,j),3),ji((qt(g,b.b),b.a[g]),12));e.n(f)}}else{for(g=0;g<d;++g){f=(c=ji(zs(i.a,j),3),ji((qt(g,c.b),c.a[g]),12));e.n(f)}}}
function ah(){}
_=ah.prototype=new pq();_.tI=0;function sh(){}
_=sh.prototype=new pq();_.tI=0;function ai(d,c){var a=new Array(c);if(d>0){var e=[null,0,false,[0,0]][d];for(var b=0;b<c;++b){a[b]=e}}return a}
function bi(a,f,c,b,e){var d;d=ai(e,b);xh();Ch(d,yh,zh);d.tI=f;d.qI=c;return d}
function di(a,b,c){if(c!=null){if(a.qI>0&&!hi(c.tI,a.qI)){throw new xp()}if(a.qI<0&&(c.tM==hw||c.tI==2)){throw new xp()}}return a[b]=c}
function vh(){}
_=vh.prototype=new pq();_.tI=0;_.length=0;_.qI=0;function xh(){xh=hw;yh=[];zh=[];Ah(new vh(),yh,zh)}
function Ah(e,a,b){var c=0,f;for(var d in e){if(f=e[d]){a[c]=d;b[c]=f;++c}}}
function Ch(a,c,d){xh();for(var e=0,b=c.length;e<b;++e){a[c[e]]=d[e]}}
var yh,zh;function ii(b,a){return b&&!!vi[b][a]}
function hi(b,a){return b&&vi[b][a]}
function ji(b,a){if(b!=null&&!hi(b.tI,a)){throw new Bp()}return b}
function ui(a){if(a!=null){throw new Bp()}return a}
var vi=[{},{},{1:1,8:1,9:1,10:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{2:1},{4:1},{6:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1,14:1},{12:1},{4:1,5:1,6:1,7:1,14:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{4:1,5:1,6:1,7:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{8:1,13:1},{9:1},{8:1,13:1},{18:1},{18:1},{15:1},{15:1},{15:1},{16:1},{18:1},{3:1,8:1,16:1},{8:1,17:1},{8:1,18:1},{15:1},{8:1,13:1},{8:1,16:1},{8:1,16:1},{11:1}];function mj(b,a,c){var d;if(a==qj){if(tk((se(),b).type)==8192){qj=null}}d=lj;lj=b;try{c.y(b)}finally{lj=d}}
function pj(a){return true}
function sj(a,b){vk();pk(a,b)}
var lj=null,qj=null;function Fj(a){hk();return ak(ig?ig:(ig=vg(new ug())),a)}
function ak(b,a){return kh(fk(),b,a)}
function ck(){if(bk){kg(fk())}}
function dk(){var a;if(bk){a=(xj(),new vj());ek(a);return null}return null}
function ek(a){if(gk){oh(gk,a)}}
function fk(){if(!gk){gk=Cj(new Bj())}return gk}
function hk(){if(!bk){Dk(Ck(),r);bk=true}}
var bk=false,gk=null;function xj(){xj=hw;yj=vg(new ug())}
function zj(a){null.F()}
function Aj(){return yj}
function vj(){}
_=vj.prototype=new tg();_.n=zj;_.r=Aj;_.tI=0;var yj;function Cj(a){a.d=ch(new ah());a.e=null;a.c=false;return a}
function Bj(){}
_=Bj.prototype=new Bg();_.tI=8;function tk(a){switch(a){case F:return 4096;case kb:return 1024;case vb:return 1;case ac:return 2;case lc:return 2048;case wc:return 128;case bd:return 256;case md:return 512;case s:return 32768;case w:return 8192;case x:return 4;case y:return 64;case z:return 32;case A:return 16;case B:return 8;case C:return 16384;case D:return 65536;case E:return 131072;case ab:return 131072;case bb:return 262144;}}
function vk(){if(!xk){ok();xk=true}}
var xk=false;function ok(){rk=function(){var c=(me(),re);re=this;if($wnd.event.returnValue==null){$wnd.event.returnValue=true;if(!pj($wnd.event)){re=c;return}}var b,a=this;while(a&&!(b=a.__listener)){a=a.parentElement}if(b){if(!(b!=null&&(b.tM!=hw&&b.tI!=2))&&(b!=null&&ii(b.tI,5))){mj($wnd.event,a,b)}}re=c};qk=function(){var a=$doc.createEventObject();if($wnd.event.returnValue==null){$wnd.event.srcElement.fireEvent(cb,a)}if(this.__eventBits&2){rk.call(this)}else if($wnd.event.returnValue==null){$wnd.event.returnValue=true;pj($wnd.event)}};var e=function(){rk.call($doc.body)};var d=function(){qk.call($doc.body)};$doc.body.attachEvent(cb,e);$doc.body.attachEvent(db,e);$doc.body.attachEvent(eb,e);$doc.body.attachEvent(fb,e);$doc.body.attachEvent(gb,e);$doc.body.attachEvent(hb,e);$doc.body.attachEvent(ib,e);$doc.body.attachEvent(jb,e);$doc.body.attachEvent(lb,e);$doc.body.attachEvent(mb,e);$doc.body.attachEvent(nb,d);$doc.body.attachEvent(ob,e)}
function pk(c,a){var b=(c.__eventBits||0)^a;c.__eventBits=a;if(!b)return;if(b&1)c.onclick=a&1?rk:null;if(b&3)c.ondblclick=a&3?qk:null;if(b&4)c.onmousedown=a&4?rk:null;if(b&8)c.onmouseup=a&8?rk:null;if(b&16)c.onmouseover=a&16?rk:null;if(b&32)c.onmouseout=a&32?rk:null;if(b&64)c.onmousemove=a&64?rk:null;if(b&128)c.onkeydown=a&128?rk:null;if(b&256)c.onkeypress=a&256?rk:null;if(b&512)c.onkeyup=a&512?rk:null;if(b&1024)c.onchange=a&1024?rk:null;if(b&2048)c.onfocus=a&2048?rk:null;if(b&4096)c.onblur=a&4096?rk:null;if(b&8192)c.onlosecapture=a&8192?rk:null;if(b&16384)c.onscroll=a&16384?rk:null;if(b&32768)c.onload=a&32768?rk:null;if(b&65536)c.onerror=a&65536?rk:null;if(b&131072)c.onmousewheel=a&131072?rk:null;if(b&262144)c.oncontextmenu=a&262144?rk:null}
var qk=null,rk=null;function Ck(){return function(d,g){var h=window,e=h.onbeforeunload,f=h.onunload;h.onbeforeunload=function(a){var c,b;try{c=d()}finally{b=e&&e(a)}if(c!=null){return c}if(b!=null){return b}};h.onunload=function(a){try{g()}finally{f&&f(a);h.onresize=null;h.onscroll=null;h.onbeforeunload=null;h.onunload=null}};h.__gwt_initWindowCloseHandler=undefined}.toString()}
function Dk(b,a){var c;b=er(b,pb,qb+a);c=ve((se(),$doc),b);$doc.body.appendChild(c);Ek();$doc.body.removeChild(c)}
function Ek(){$wnd.__gwt_initWindowCloseHandler(function(){return dk()},function(){ck()})}
function vn(){}
_=vn.prototype=new pq();_.tI=9;_.j=null;function ro(b){var a;if(b.g){throw new iq()}b.g=true;b.j.__listener=b;a=b.h;b.h=-1;if(a>0){wo(b,a)}b.o();b.z()}
function so(c,a){var b;switch(tk((se(),a).type)){case 16:case 32:b=a.relatedTarget||(a.type==z?a.toElement:a.fromElement);if(!!b&&pe(c.j,b)){return}}}
function to(a){if(!a.g){throw new iq()}try{a.A()}finally{a.p();a.j.__listener=null;a.g=false}}
function uo(a){if(!a.i){bn();if(ws(gn.a,a)){to(a);ct(gn.a,a)!=null}}else if(a.i){a.i.B(a)}else if(a.i){throw new iq()}}
function vo(c,b){var a;a=c.i;if(!b){if(!!a&&a.g){to(c)}c.i=null}else{if(a){throw new iq()}c.i=b;if(b.g){ro(c)}}}
function wo(b,a){if(b.h==-1){sj(b.j,a|(b.j.__eventBits||0))}else{b.h|=a}}
function xo(){}
function yo(){}
function zo(a){so(this,a)}
function Ao(){}
function Bo(){}
function Fn(){}
_=Fn.prototype=new vn();_.o=xo;_.p=yo;_.y=zo;_.z=Ao;_.A=Bo;_.tI=10;_.g=false;_.h=0;_.i=null;function wm(){var a,b;for(b=this.w();b.a<b.b.b-1;){a=go(b);ro(a)}}
function xm(){var a,b;for(b=this.w();b.a<b.b.b-1;){a=go(b);to(a)}}
function ym(){}
function zm(){}
function um(){}
_=um.prototype=new Fn();_.o=wm;_.p=xm;_.z=ym;_.A=zm;_.tI=11;function sl(c,a,b){uo(a);ko(c.f,a);b.appendChild(a.j);vo(a,c)}
function ul(b,c){var a;if(c.i!=b){return false}vo(c,null);a=c.j;(se(),a).parentElement.removeChild(a);po(b.f,c);return true}
function vl(){return eo(new bo(),this.f)}
function wl(a){return ul(this,a)}
function ql(){}
_=ql.prototype=new um();_.w=vl;_.B=wl;_.tI=12;function bl(a,b){sl(a,b,a.j)}
function dl(a){a.style[rb]=sb;a.style[tb]=sb;a.style[ub]=sb}
function el(b){var a;a=ul(this,b);if(a){dl(b.j)}return a}
function al(){}
_=al.prototype=new ql();_.B=el;_.tI=13;function xl(){}
_=xl.prototype=new Fn();_.tI=14;function hl(b,a){b.j=a;b.j.tabIndex=0;return b}
function gl(){}
_=gl.prototype=new xl();_.tI=15;function kl(a){hl(a,(se(),$doc).createElement(wb));ml(a.j);a.j[xb]=yb;return a}
function ml(b){if(b.type==zb){try{b.setAttribute(Ab,wb)}catch(a){}}}
function fl(){}
_=fl.prototype=new gl();_.tI=16;function ol(a){a.f=jo(new ao());a.e=(se(),$doc).createElement(Bb);a.d=$doc.createElement(Cb);a.e.appendChild(a.d);a.j=a.e;return a}
function nl(){}
_=nl.prototype=new ql();_.tI=17;_.d=null;_.e=null;function am(){am=hw;El(new Dl(),Db);cm=El(new Dl(),rb);El(new Dl(),Eb);bm=cm}
var bm,cm;function El(b,a){b.a=a;return b}
function Dl(){}
_=Dl.prototype=new pq();_.tI=0;_.a=null;function jm(){jm=hw;hm(new gm(),Fb);hm(new gm(),bc);km=hm(new gm(),tb)}
var km;function hm(a,b){a.a=b;return a}
function gm(){}
_=gm.prototype=new pq();_.tI=0;_.a=null;function om(a){ol(a);a.a=(am(),bm);a.c=(jm(),km);a.b=(se(),$doc).createElement(cc);a.d.appendChild(a.b);a.e[dc]=ec;a.e[fc]=ec;return a}
function pm(c,d){var b,a;b=(a=(se(),$doc).createElement(gc),(a[hc]=c.a.a,undefined),(a.style[ic]=c.c.a,undefined),a);c.b.appendChild(b);uo(d);ko(c.f,d);b.appendChild(d.j);vo(d,c)}
function sm(c){var a,b;b=(se(),c.j).parentElement;a=ul(this,c);if(a){this.b.removeChild(b)}return a}
function mm(){}
_=mm.prototype=new nl();_.B=sm;_.tI=18;_.b=null;function bn(){bn=hw;fn=Eu(new Du());gn=cv(new bv())}
function an(b,a){bn();b.f=jo(new ao());b.j=a;ro(b);return b}
function cn(){var b,a;bn();var c,d;for(d=(b=Br(new Ar(),iu(gn.a).b.a),yt(new xt(),b));jt(d.a.a);){c=ji((a=ji(kt(d.a.a),15),a.s()),7);if(c.g){to(c)}}us(gn.a);us(fn)}
function en(a){bn();var b;b=ji(zs(fn,a),14);if(b){return b}if(fn.d==0){Fj(new Bm())}b=Em(new Dm());Fs(fn,a,b);dv(gn,b);return b}
function Am(){}
_=Am.prototype=new al();_.tI=19;var fn,gn;function Bm(){}
_=Bm.prototype=new pq();_.tI=20;function Fm(){Fm=hw;bn()}
function Em(a){Fm();an(a,$doc.body);return a}
function Dm(){}
_=Dm.prototype=new Am();_.tI=21;function rn(a){var b;b=tk((se(),a).type);if((b&896)!=0){so(this,a)}else{so(this,a)}}
function pn(){}
_=pn.prototype=new xl();_.y=rn;_.tI=22;function sn(b){var a;tn(b,(a=(se(),$doc).createElement(jc),a.type=kc,a),mc);return b}
function tn(c,a,b){c.j=a;c.j.tabIndex=0;if(b!=null){c.j[xb]=b}return c}
function on(){}
_=on.prototype=new pn();_.tI=23;function An(a){ol(a);a.a=(am(),bm);a.b=(jm(),km);a.e[dc]=ec;a.e[fc]=ec;return a}
function Bn(c,e){var b,d,a;d=(se(),$doc).createElement(cc);b=(a=$doc.createElement(gc),(a[hc]=c.a.a,undefined),(a.style[ic]=c.b.a,undefined),a);d.appendChild(b);c.d.appendChild(d);uo(e);ko(c.f,e);b.appendChild(e.j);vo(e,c)}
function En(c){var a,b;b=(se(),c.j).parentElement;a=ul(this,c);if(a){this.d.removeChild(b.parentElement)}return a}
function yn(){}
_=yn.prototype=new nl();_.B=En;_.tI=24;function jo(a){a.a=bi(zi,0,7,4,0);return a}
function ko(a,b){no(a,b,a.b)}
function mo(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function no(d,e,a){var b,c;if(a<0||a>d.b){throw new lq()}if(d.b==d.a.length){c=bi(zi,0,7,d.a.length*2,0);for(b=0;b<d.a.length;++b){di(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){di(d.a,b,d.a[b-1])}di(d.a,a,e)}
function oo(c,b){var a;if(b<0||b>=c.b){throw new lq()}--c.b;for(a=b;a<c.b;++a){di(c.a,a,c.a[a+1])}di(c.a,c.b,null)}
function po(b,c){var a;a=mo(b,c);if(a==-1){throw new wv()}oo(b,a)}
function ao(){}
_=ao.prototype=new pq();_.tI=0;_.a=null;_.b=0;function eo(b,a){b.b=a;return b}
function go(a){if(a.a>=a.b.b){throw new wv()}return a.b.a[++a.a]}
function ho(){return this.a<this.b.b-1}
function io(){return go(this)}
function bo(){}
_=bo.prototype=new pq();_.v=ho;_.x=io;_.tI=0;_.a=-1;_.b=null;function mp(a){a.a=dp(new bp());a.j=(se(),$doc).createElement(nc);gp(a.a,a);a.j[xb]=oc;pp(a,pc);jp(a.a,300);ip(a.a,150);return a}
function pp(d,a){var b,c;if(a==null){throw new gq()}a=ir(a);if(a.indexOf(qc)==0){b=a.indexOf(rc,12);if(b>-1){c=fr(a.substr(5,b-5),sc,0);if(c.length>=3){a=tc+c[0]+sc+c[1]+sc+c[2]+rc}}}hp(d.a,a)}
function qp(a){if(!a){throw new gq()}tk((se(),a).type)}
function Fo(){}
_=Fo.prototype=new Fn();_.y=qp;_.tI=25;function ap(){}
_=ap.prototype=new pq();_.tI=0;_.b=null;_.c=null;function dp(a){a.a=qu(new pu());(new Av()).a=qu(new pu());(new Av()).a=qu(new pu());return a}
function gp(b,a){b.c=a.j;b.c.style[ub]=uc;b.c.style[vc]=xc;b.c.style[yc]=zc;b.c.style[Ac]=rb;b.c.style[Bc]=Cc;fp();tp(new rp())}
function fp(){if(!$doc.namespaces[Dc]){$doc.namespaces.add(Dc,Ec);$doc.createStyleSheet().cssText=Fc}}
function hp(d,a){var b,c;if(d.a.b!=0&&ar(a,sb)){throw new iq()}d.b=a;d.c.style[ad]=d.b;for(c=ht(new ft(),d.a);c.a<c.b.D();){ui(kt(c));b=d.c.children[null.F()];b.setAttribute(cd,d.b)}}
function ip(b,a){b.c.innerHTML=sb;b.c.style[dd]=a+ed}
function jp(a,b){a.c.innerHTML=sb;a.c.style[fd]=b+ed}
function bp(){}
_=bp.prototype=new ap();_.tI=0;function tp(a){a.a=bi(yi,50,-1,9,1);a.a[0]=1;a.a[1]=0;a.a[2]=0;a.a[3]=0;a.a[4]=1;a.a[5]=0;a.a[6]=0;a.a[7]=0;a.a[8]=1;return a}
function rp(){}
_=rp.prototype=new pq();_.tI=0;function or(){}
_=or.prototype=new pq();_.tI=3;function eq(){}
_=eq.prototype=new or();_.tI=4;function tq(){}
_=tq.prototype=new eq();_.tI=5;function xp(){}
_=xp.prototype=new tq();_.tI=27;function Ep(c,a){var b;b=new Ap();return b}
function Ap(){}
_=Ap.prototype=new pq();_.tI=0;function Bp(){}
_=Bp.prototype=new tq();_.tI=30;function gq(){}
_=gq.prototype=new tq();_.tI=31;function iq(){}
_=iq.prototype=new tq();_.tI=32;function mq(b,a){return b}
function lq(){}
_=lq.prototype=new tq();_.tI=33;function ar(b,a){if(!(a!=null&&ii(a.tI,1))){return false}return String(b)==a}
function er(c,a,b){b=lr(b);return c.replace(RegExp(a),b)}
function fr(k,j,h){var a=new RegExp(j,gd);var i=[];var b=0;var l=k;var f=null;while(true){var g=a.exec(l);if(g==null||(l==sb||b==h-1&&h>0)){i[b]=l;break}else{i[b]=l.substring(0,g.index);l=l.substring(g.index+g[0].length,l.length);a.lastIndex=0;if(f==l){i[b]=l.substring(0,1);l=l.substring(1)}f=l;b++}}if(h==0){var e=i.length;while(e>0&&i[e-1]==sb){--e}if(e<i.length){i.splice(e,i.length-e)}}var d=bi(Bi,0,1,i.length,0);for(var c=0;c<i.length;++c){d[c]=i[c]}return d}
function gr(b,a){return b.substr(a,b.length-a)}
function ir(c){if(c.length==0||c[0]>hd&&c[c.length-1]>hd){return c}var a=c.replace(/^(\s*)/,sb);var b=a.replace(/\s*$/,sb);return b}
function lr(b){var a;a=0;while(0<=(a=b.indexOf(id,a))){if(b.charCodeAt(a+1)==36){b=b.substr(0,a-0)+jd+gr(b,++a)}else{b=b.substr(0,a-0)+gr(b,++a)}}return b}
function mr(a){return ar(this,a)}
function nr(){return Cq(this)}
_=String.prototype;_.eQ=mr;_.hC=nr;_.tI=2;function xq(){xq=hw;yq={};Bq={}}
function zq(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function Cq(c){xq();var a=kd+c;var b=Bq[a];if(b!=null){return b}b=yq[a];if(b==null){b=zq(c)}Dq();return Bq[a]=b}
function Dq(){if(Aq==256){yq=Bq;Bq={};Aq=0}++Aq}
var yq,Aq=0,Bq;function qr(){}
_=qr.prototype=new tq();_.tI=35;function ur(a,b){var c;while(a.v()){c=a.x();if(b==null?c==null:Ed(b,c)){return a}}return null}
function wr(a){throw new qr()}
function xr(b){var a;a=ur(this.w(),b);return !!a}
function tr(){}
_=tr.prototype=new pq();_.l=wr;_.m=xr;_.tI=0;function iu(b){var a;a=Fr(new zr(),b);return Dt(new wt(),b,a)}
function ju(c){var a,b,d,e,f;if((c==null?null:c)===this){return true}if(!(c!=null&&ii(c.tI,17))){return false}e=ji(c,17);if(ji(this,17).d!=e.d){return false}for(b=Br(new Ar(),Fr(new zr(),e).a);jt(b.a);){a=ji(kt(b.a),15);d=a.s();f=a.t();if(!(d==null?ji(this,17).c:d!=null&&ii(d.tI,1)?Bs(ji(this,17),ji(d,1)):As(ji(this,17),d,~~ae(d)))){return false}if(!Ev(f,d==null?ji(this,17).b:d!=null&&ii(d.tI,1)?ji(this,17).e[kd+ji(d,1)]:xs(ji(this,17),d,~~ae(d)))){return false}}return true}
function ku(){var a,b,c;c=0;for(b=Br(new Ar(),Fr(new zr(),ji(this,17)).a);jt(b.a);){a=ji(kt(b.a),15);c+=a.hC();c=~~c}return c}
function vt(){}
_=vt.prototype=new pq();_.eQ=ju;_.hC=ku;_.tI=0;function ss(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.l(a[f])}}}}
function ts(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=qs(e,c.substring(1));a.l(b)}}}
function us(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0}
function ws(b,a){return a==null?b.c:a!=null&&ii(a.tI,1)?Bs(b,ji(a,1)):As(b,a,~~ae(a))}
function zs(b,a){return a==null?b.b:a!=null&&ii(a.tI,1)?b.e[kd+ji(a,1)]:xs(b,a,~~ae(a))}
function xs(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){return c.t()}}}return null}
function As(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){return true}}}return false}
function Bs(b,a){return kd+a in b.e}
function Fs(b,a,c){return a==null?Ds(b,c):a!=null&&ii(a.tI,1)?Es(b,ji(a,1),c):Cs(b,a,c,~~ae(a))}
function Cs(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(i.q(g,d)){var h=c.t();c.C(j);return h}}}else{a=i.a[e]=[]}var c=pv(new ov(),g,j);a.push(c);++i.d;return null}
function Ds(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function Es(d,a,e){var b,c=d.e;a=kd+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function ct(b,a){return !a?bt(b):at(b,a,~~(a.$H||(a.$H=++ge)))}
function at(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.s();if(h.q(g,d)){if(a.length==1){delete h.a[e]}else{a.splice(f,1)}--h.d;return c.t()}}}return null}
function bt(b){var a;a=b.b;b.b=null;if(b.c){b.c=false;--b.d}return a}
function dt(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Ed(a,b)}
function yr(){}
_=yr.prototype=new vt();_.q=dt;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function nu(b){var a,c,d;if((b==null?null:b)===this){return true}if(!(b!=null&&ii(b.tI,18))){return false}c=ji(b,18);if(c.D()!=this.D()){return false}for(a=c.w();a.v();){d=a.x();if(!this.m(d)){return false}}return true}
function ou(){var a,b,c;a=0;for(b=this.w();b.v();){c=b.x();if(c!=null){a+=ae(c);a=~~a}}return a}
function lu(){}
_=lu.prototype=new tr();_.eQ=nu;_.hC=ou;_.tI=36;function Fr(b,a){b.a=a;return b}
function bs(d,c){var a,b,e;if(c!=null&&ii(c.tI,15)){a=ji(c,15);b=a.s();if(ws(d.a,b)){e=zs(d.a,b);return av(a.t(),e)}}return false}
function cs(a){return bs(this,a)}
function ds(){return Br(new Ar(),this.a)}
function es(){return this.a.d}
function zr(){}
_=zr.prototype=new lu();_.m=cs;_.w=ds;_.D=es;_.tI=37;_.a=null;function Br(c,b){var a;c.b=b;a=qu(new pu());if(c.b.c){su(a,gs(new fs(),c.b))}ts(c.b,a);ss(c.b,a);c.a=ht(new ft(),a);return c}
function Dr(){return jt(this.a)}
function Er(){return ji(kt(this.a),15)}
function Ar(){}
_=Ar.prototype=new pq();_.v=Dr;_.x=Er;_.tI=0;_.a=null;_.b=null;function fu(b){var a;if(b!=null&&ii(b.tI,15)){a=ji(b,15);if(Ev(this.s(),a.s())&&Ev(this.t(),a.t())){return true}}return false}
function gu(){var a,b;a=0;b=0;if(this.s()!=null){a=ae(this.s())}if(this.t()!=null){b=ae(this.t())}return a^b}
function du(){}
_=du.prototype=new pq();_.eQ=fu;_.hC=gu;_.tI=38;function gs(b,a){b.a=a;return b}
function is(){return null}
function js(){return this.a.b}
function ks(a){return Ds(this.a,a)}
function fs(){}
_=fs.prototype=new du();_.s=is;_.t=js;_.C=ks;_.tI=39;_.a=null;function ms(c,a,b){c.b=b;c.a=a;return c}
function os(){return this.a}
function ps(){return this.b.e[kd+this.a]}
function qs(b,a){return ms(new ls(),a,b)}
function rs(a){return Es(this.b,this.a,a)}
function ls(){}
_=ls.prototype=new du();_.s=os;_.t=ps;_.C=rs;_.tI=40;_.a=null;_.b=null;function pt(a){this.k(this.D(),a);return true}
function ot(b,a){throw new qr()}
function qt(a,b){if(a<0||a>=b){tt(a,b)}}
function rt(e){var a,b,c,d,f;if((e==null?null:e)===this){return true}if(!(e!=null&&ii(e.tI,16))){return false}f=ji(e,16);if(this.D()!=f.D()){return false}c=this.w();d=f.w();while(c.a<c.b.D()){a=kt(c);b=kt(d);if(!(a==null?b==null:Ed(a,b))){return false}}return true}
function st(){var a,b,c;b=1;a=this.w();while(a.a<a.b.D()){c=kt(a);b=31*b+(c==null?0:ae(c));b=~~b}return b}
function tt(a,b){throw mq(new lq(),ld+a+nd+b)}
function ut(){return ht(new ft(),this)}
function et(){}
_=et.prototype=new tr();_.l=pt;_.k=ot;_.eQ=rt;_.hC=st;_.w=ut;_.tI=41;function ht(b,a){b.b=a;return b}
function jt(a){return a.a<a.b.D()}
function kt(a){if(a.a>=a.b.D()){throw new wv()}return a.b.u(a.a++)}
function lt(){return this.a<this.b.D()}
function mt(){return kt(this)}
function ft(){}
_=ft.prototype=new pq();_.v=lt;_.x=mt;_.tI=0;_.a=0;_.b=null;function Dt(b,a,c){b.a=a;b.b=c;return b}
function au(a){return ws(this.a,a)}
function bu(){var a;return a=Br(new Ar(),this.b.a),yt(new xt(),a)}
function cu(){return this.b.a.d}
function wt(){}
_=wt.prototype=new lu();_.m=au;_.w=bu;_.D=cu;_.tI=42;_.a=null;_.b=null;function yt(a,b){a.a=b;return a}
function Bt(){return jt(this.a.a)}
function Ct(){var a;return a=ji(kt(this.a.a),15),a.s()}
function xt(){}
_=xt.prototype=new pq();_.v=Bt;_.x=Ct;_.tI=0;_.a=null;function qu(a){a.a=bi(Ai,0,0,0,0);a.b=0;return a}
function su(b,a){di(b.a,b.b++,a);return true}
function ru(c,a,b){if(a<0||a>c.b){tt(a,c.b)}c.a.splice(a,0,b);++c.b}
function uu(b,a){qt(a,b.b);return b.a[a]}
function vu(c,b,a){for(;a<c.b;++a){if(Ev(b,c.a[a])){return a}}return -1}
function xu(a){return di(this.a,this.b++,a),true}
function wu(a,b){ru(this,a,b)}
function yu(a){return vu(this,a,0)!=-1}
function zu(a){return qt(a,this.b),this.a[a]}
function Au(){return this.b}
function pu(){}
_=pu.prototype=new et();_.l=xu;_.k=wu;_.m=yu;_.u=zu;_.D=Au;_.tI=43;_.a=null;_.b=0;function Eu(a){us(a);return a}
function av(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Ed(a,b)}
function Du(){}
_=Du.prototype=new yr();_.tI=44;function cv(a){a.a=Eu(new Du());return a}
function dv(c,a){var b;b=Fs(c.a,a,c);return b==null}
function hv(b){var a;return a=Fs(this.a,b,this),a==null}
function iv(a){return ws(this.a,a)}
function jv(){var a;return a=Br(new Ar(),iu(this.a).b.a),yt(new xt(),a)}
function kv(){return this.a.d}
function bv(){}
_=bv.prototype=new lu();_.l=hv;_.m=iv;_.w=jv;_.D=kv;_.tI=45;_.a=null;function pv(b,a,c){b.a=a;b.b=c;return b}
function rv(){return this.a}
function sv(){return this.b}
function uv(b){var a;a=this.b;this.b=b;return a}
function ov(){}
_=ov.prototype=new du();_.s=rv;_.t=sv;_.C=uv;_.tI=46;_.a=null;_.b=null;function wv(){}
_=wv.prototype=new tq();_.tI=47;function cw(a){return su(this.a,a)}
function bw(a,b){ru(this.a,a,b)}
function dw(a){return vu(this.a,a,0)!=-1}
function ew(a){return uu(this.a,a)}
function fw(){return ht(new ft(),this.a)}
function gw(){return this.a.b}
function Fv(){}
_=Fv.prototype=new et();_.l=cw;_.k=bw;_.m=dw;_.u=ew;_.w=fw;_.D=gw;_.tI=48;_.a=null;function Av(){}
_=Av.prototype=new Fv();_.tI=49;function Ev(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Ed(a,b)}
function iw(){}
_=iw.prototype=new sh();_.tI=0;_.a=null;_.b=null;_.c=null;_.d=null;function kw(l){var j,k;k=An(new yn());j=om(new mm());l.a=mp(new Fo());l.c=sn(new on());l.d=kl(new fl());l.b=kl(new fl());(se(),l.d.j).innerText=od;l.b.j.innerText=pd;Bn(k,l.a);Bn(k,j);pm(j,l.c);pm(j,l.d);pm(j,l.b);bl((bn(),en(null)),k);return l}
function jw(){}
_=jw.prototype=new iw();_.tI=0;function vp(){!!$stats&&$stats({moduleName:$moduleName,subSystem:qd,evtGroup:rd,millis:(new Date()).getTime(),type:sd,className:td});kw(new jw())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{vp()}catch(a){b(d)}else{vp()}}
function hw(){}
var Bi=Ep(ud,vd),yi=Ep(sb,wd),zi=Ep(t,u),Ai=Ep(ud,v);$stats && $stats({moduleName:'mindmapgadget',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (mindmapgadget) mindmapgadget.onScriptLoad(gwtOnLoad);})();