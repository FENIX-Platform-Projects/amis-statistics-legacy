function fenix(){var M='',nb='" for "gwt:onLoadErrorFn"',lb='" for "gwt:onPropertyErrorFn"',Y='"><\/script>',$='#',vb='&',Pb='.cache.html',ab='/',Db='311E1BC2323D78E16CCB6B3B324E6C8E',Fb='51AD2FA8332E57D84B5AE9F4AE154DC8',Hb='5EAB5CDE8F38818795E0B7F402E2BE60',Jb='672261E09B4DBD16B208B6A1598BCB0F',Lb='7DCC033DBDDAE6CD2E06D9CAF10B4B0A',Mb='81F150C080C23857AED5B039F3361066',_b='<script defer="defer">fenix.onInjectionDone(\'fenix\')<\/script>',X='<script id="',Zb='<script language="javascript" src="',ib='=',_='?',kb='Bad handler "',Xb='DOMContentLoaded',Ob='F81A1613B47D3C4F55BF7C07FFDDDA7B',Z='SCRIPT',yb='Unexpected exception in locale detection, using default: ',xb='_',wb='__gwt_Locale',W='__gwt_marker_fenix',bb='base',Kb='bd',Q='begin',P='bootstrap',db='clear.cache.gif',hb='content',tb='default',Gb='en',Ib='en_UK',V='end',Nb='es',N='fenix',Qb='fenix-style.css',Eb='fr',R='gwt.codesvr=',S='gwt.hosted=',T='gwt.hybrid',mb='gwt:onLoadErrorFn',jb='gwt:onPropertyErrorFn',gb='gwt:property',Vb='head',Ab='hosted.html?fenix',Ub='href',ob='iframe',cb='img',Cb='it',pb="javascript:''",Rb='link',zb='loadExternalRefs',sb='locale',ub='locale=',Wb='map-style.css',eb='meta',rb='moduleRequested',U='moduleStartup',fb='name',Yb='ofcgwt/swfobject.js',$b='ofcgwt/swfobject.js"><\/script>',qb='position:absolute;width:0;height:0;border:none',Sb='rel',Bb='selectingPermutation',O='startup',Tb='stylesheet';var k=window,l=document,m=k.__gwtStatsEvent?function(a){return k.__gwtStatsEvent(a)}:null,n=k.__gwtStatsSessionId?k.__gwtStatsSessionId:null,o,p,q,r=M,s={},t=[],u=[],v=[],w,x;m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:P,millis:(new Date).getTime(),type:Q});if(!k.__gwt_stylesLoaded){k.__gwt_stylesLoaded={}}if(!k.__gwt_scriptsLoaded){k.__gwt_scriptsLoaded={}}function y(){var b=false;try{var c=k.location.search;return (c.indexOf(R)!=-1||(c.indexOf(S)!=-1||k.external&&k.external.gwtOnLoad))&&c.indexOf(T)==-1}catch(a){}y=function(){return b};return b}
function z(){if(o&&p){var b=l.getElementById(N);var c=b.contentWindow;if(y()){c.__gwt_getProperty=function(a){return F(a)}}fenix=null;c.gwtOnLoad(w,N,r);m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:U,millis:(new Date).getTime(),type:V})}}
function A(){var e,f=W,g;l.write(X+f+Y);g=l.getElementById(f);e=g&&g.previousSibling;while(e&&e.tagName!=Z){e=e.previousSibling}function h(a){var b=a.lastIndexOf($);if(b==-1){b=a.length}var c=a.indexOf(_);if(c==-1){c=a.length}var d=a.lastIndexOf(ab,Math.min(c,b));return d>=0?a.substring(0,d+1):M}
;if(e&&e.src){r=h(e.src)}if(r==M){var i=l.getElementsByTagName(bb);if(i.length>0){r=i[i.length-1].href}else{r=h(l.location.href)}}else if(r.match(/^\w+:\/\//)){}else{var j=l.createElement(cb);j.src=r+db;r=h(j.src)}if(g){g.parentNode.removeChild(g)}}
function B(){var b=document.getElementsByTagName(eb);for(var c=0,d=b.length;c<d;++c){var e=b[c],f=e.getAttribute(fb),g;if(f){if(f==gb){g=e.getAttribute(hb);if(g){var h,i=g.indexOf(ib);if(i>=0){f=g.substring(0,i);h=g.substring(i+1)}else{f=g;h=M}s[f]=h}}else if(f==jb){g=e.getAttribute(hb);if(g){try{x=eval(g)}catch(a){alert(kb+g+lb)}}}else if(f==mb){g=e.getAttribute(hb);if(g){try{w=eval(g)}catch(a){alert(kb+g+nb)}}}}}}
function C(a,b){return b in t[a]}
function D(a){var b=s[a];return b==null?null:b}
function E(a,b){var c=v;for(var d=0,e=a.length-1;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
function F(a){var b=u[a](),c=t[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(x){x(a,d,b)}throw null}
var G;function H(){if(!G){G=true;var a=l.createElement(ob);a.src=pb;a.id=N;a.style.cssText=qb;a.tabIndex=-1;l.body.appendChild(a);m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:U,millis:(new Date).getTime(),type:rb});a.contentWindow.location.replace(r+J)}}
u[sb]=function(){try{var b;var c=tb||tb;if(b==null){var d=location.search;var e=d.indexOf(ub);if(e>=0){var f=d.substring(e);var g=f.indexOf(ib)+1;var h=f.indexOf(vb);if(h==-1){h=f.length}b=f.substring(g,h)}}if(b==null){b=D(sb)}if(b==null){b=k[wb]}else{k[wb]=b||c}if(b==null){return c}while(!C(sb,b)){var i=b.lastIndexOf(xb);if(i==-1){b=c;break}else{b=b.substring(0,i)}}return b}catch(a){alert(yb+a);return tb}};t[sb]={bd:0,'default':1,en:2,en_UK:3,es:4,fr:5,it:6};fenix.onScriptLoad=function(){if(G){p=true;z()}};fenix.onInjectionDone=function(){o=true;m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:zb,millis:(new Date).getTime(),type:V});z()};A();var I;var J;if(y()){if(k.external&&(k.external.initModule&&k.external.initModule(N))){k.location.reload();return}J=Ab;I=M}B();m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:P,millis:(new Date).getTime(),type:Bb});if(!y()){try{E([Cb],Db);E([Eb],Fb);E([Gb],Hb);E([Ib],Jb);E([Kb],Lb);E([tb],Mb);E([Nb],Ob);I=v[F(sb)];J=I+Pb}catch(a){return}}var K;function L(){if(!q){q=true;if(!__gwt_stylesLoaded[Qb]){var a=l.createElement(Rb);__gwt_stylesLoaded[Qb]=a;a.setAttribute(Sb,Tb);a.setAttribute(Ub,r+Qb);l.getElementsByTagName(Vb)[0].appendChild(a)}if(!__gwt_stylesLoaded[Wb]){var a=l.createElement(Rb);__gwt_stylesLoaded[Wb]=a;a.setAttribute(Sb,Tb);a.setAttribute(Ub,r+Wb);l.getElementsByTagName(Vb)[0].appendChild(a)}if(!__gwt_stylesLoaded[Qb]){var a=l.createElement(Rb);__gwt_stylesLoaded[Qb]=a;a.setAttribute(Sb,Tb);a.setAttribute(Ub,r+Qb);l.getElementsByTagName(Vb)[0].appendChild(a)}if(!__gwt_stylesLoaded[Qb]){var a=l.createElement(Rb);__gwt_stylesLoaded[Qb]=a;a.setAttribute(Sb,Tb);a.setAttribute(Ub,r+Qb);l.getElementsByTagName(Vb)[0].appendChild(a)}if(!__gwt_stylesLoaded[Qb]){var a=l.createElement(Rb);__gwt_stylesLoaded[Qb]=a;a.setAttribute(Sb,Tb);a.setAttribute(Ub,r+Qb);l.getElementsByTagName(Vb)[0].appendChild(a)}z();if(l.removeEventListener){l.removeEventListener(Xb,L,false)}if(K){clearInterval(K)}}}
if(l.addEventListener){l.addEventListener(Xb,function(){H();L()},false)}var K=setInterval(function(){if(/loaded|complete/.test(l.readyState)){H();L()}},50);m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:P,millis:(new Date).getTime(),type:V});m&&m({moduleName:N,sessionId:n,subSystem:O,evtGroup:zb,millis:(new Date).getTime(),type:Q});if(!__gwt_scriptsLoaded[Yb]){__gwt_scriptsLoaded[Yb]=true;document.write(Zb+r+$b)}l.write(_b)}
fenix();