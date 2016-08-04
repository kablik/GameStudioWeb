<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GameStudio (Games)</title>
 
     <style>
   <%@ include file="normalize.css"%>
     <%@ include file="GamesChoice.css"%>
     <%@ include file="StuffAboutGames.css"%>
     <%@ include file="GameStudio.css"%>
</style>

</head>
<body>

<div class="left" <%=request.getAttribute("formular")%>>
<h3 class="gameChoice">Prihlásený ako:</h3>
<h2 class="gameChoice">${player.player}</h2>
<div class="logout"><a href="?action=logOut" class="btn">Odhlásiť</a></div>
</div>

<div class="left2" <%=request.getAttribute("back")%>>
<a href="?action=loginMenu" class="btn">Späť</a>
</div>

<div class="right">
<h3 class="gameChoice">Vyberte možnosť:</h3>
<a href="?action=mainMenu&nickName=${player.player}" class="btn">Štatistiky</a>

<c:forEach items="${games}" var="game">
    <a href="?action=play&game=${game.game}&nickName=${player.player}" class="btn">${game.game}</a>
</c:forEach>
</div>
<div class="clear"></div>

<div <%=request.getAttribute("statistics")%>>

<table class="tableSAG">
<tr><th>Počet hráčov GameStudiu:</th></tr>
<tr><td>${numbersOfPlayers}</td></tr>
</table>

<table class="tableSAG">
<tr><th>Počet hier v GameStudiu:</th></tr>
<tr><td>${numbersOfGames}</td></tr>
</table>
 
<table class="tableSAG">
<tr><th>Najviac hrané hry:</th></tr>
<c:forEach items="${mostPlayedGames}" var="mpg">
<tr><td>${mpg}</td></tr>
</c:forEach>
</table>

<table class="tableSAG">
<tr><th>Hráč čo najviac hrá:</th></tr>
<c:forEach items="${topPlayer}" var="tp">
<tr><td>${tp}</td></tr>
</c:forEach>
</table>

<table class="tableSAG">
<tr><th>Koľko krát sa hrali hry:</th></tr>
<c:forEach items="${numOfScoresForGames}" var="gamesPlayed">
<tr><td>${gamesPlayed.game}</td><td>${gamesPlayed.scoresCount}</td></tr>
</c:forEach>
</table>

<table class="tableSAG">
<tr><th>Koľko krát sa hodnotili hry:</th></tr>
<c:forEach items="${numOfRatingsForGames}" var="gamesRated">
<tr><td>${gamesRated.game}</td><td>${gamesRated.ratingsCount}</td></tr>
</c:forEach>
</table>

<table class="tableSAG">
<tr><th>Koľko krát sa komentovali hry:</th></tr>
<c:forEach items="${numOfKomentsForGames}" var="gamesKomented">
<tr><td>${gamesKomented.game}</td><td>${gamesKomented.komentsCount}</td></tr>
</c:forEach>
</table>

</div>

</body>
</html>