<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="menu">
<ul>
<li><a href="${f:url('/translate/')}"><span><fmt:message key="menu.translate"/></span></a></li>
<li><a href="${f:url('/blog/')}"><span><fmt:message key="menu.mylist"/></span></a></li>
<li><a href="${f:url('/locale/')}"><span><fmt:message key="menu.exam"/></span></a></li>
</ul>
</div>