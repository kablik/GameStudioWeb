<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>GS signed</title>
 
    <style>
   <%@ include file="normalize.css"%>
     <%@ include file="GamesChoice.css"%>
     <%@ include file="GameStudio.css"%>
     <%@ include file="StuffAboutGames.css"%>
</style>
</head>
<body>

<jsp:include page="GamesChoice.jsp"/>

<div class="leftPart"><div id="inner"><jsp:include page="/${gamePlayed}"/></div></div>
	

<div class="rightPart">

	<div class="SAG"><jsp:include page="StuffAboutGames.jsp"/></div>

	<div <%=request.getAttribute("formular")%>><jsp:include page="Formular.jsp"/></div>
</div>

</body>
</html>