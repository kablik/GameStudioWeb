<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>

<form>
	<input type="hidden" name="nickName" value="${player.player}">
	<input type="hidden" name="game" value="${gamePlayed}">
<input type="hidden" name="action" value="play">

	<h3>Formulár:</h3>
	<div class="rightSideBar">
	<label for="content">Napíšte komentár:</label><br>
	<textarea name="koment" id="koment" spellcheck="true" lang="sk"
		cols="50" rows="5" placeholder="Vložte komentár ..."></textarea>
	<br>
		<div class="buttonsForm"><button name="actionForm" value="addKoment" type="submit">Pridať komentár</button></div>
	</div>
	</form>
		
	<form>
	<input type="hidden" name="nickName" value="${player.player}">
	<input type="hidden" name="game" value="${gamePlayed}">
	<input type="hidden" name="action" value="play">
	
		<div class="rightSideBar">
	<br> <label for="rating">Ohodnoťte hru:</label><br> <select
		id="rating" name="rating" size="1">
		<option value="0">-- vyberte rating --</option>
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>
	<div class="buttonsForm"><button name="actionForm" value="addRating" type="submit">Pridať rating</button></div>
	</div>
</form>