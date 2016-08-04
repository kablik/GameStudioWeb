<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>


	<input type="hidden" name="nickName" value="${player.player}">
	<input type="hidden" name="game" value="${game.game}">


<div class="rightSideBar">
	<table class="tableSAG" <%=request.getAttribute("scoresTable")%>>
		<tr>
			<th>Meno hráča:</th>
			<th>Score hráča:</th>
		</tr>
		<c:forEach items="${scores}" var="score">
			<tr>
				<td>${score.player.player}
				<td>${score.score}
			</tr>
		</c:forEach>
	</table>



	<table class="tableSAG" <%=request.getAttribute("komentsTable")%>>
		<tr>
			<th>Meno hráča:</th>
			<th>Koment hráča:</th>
		</tr>
		<c:forEach items="${koments}" var="koment">
			<tr>
				<td>${koment.player.player}
				<td>${koment.koment}
			</tr>
		</c:forEach>
	</table>

	<p>
		Počet hodnotení hry je:
		<%=request.getAttribute("numOfRatings")%></p>
	<p>
		Priemerné hodnotenie hry je:
		<%=request.getAttribute("avgOfRatings")%></p>
</div>
