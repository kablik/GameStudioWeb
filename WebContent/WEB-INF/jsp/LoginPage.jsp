<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>GameStudio (Login Page)</title>

  <style>
   <%@ include file="normalize.css"%>
     <%@ include file="LoginPage.css"%>
</style> 
  
</head>
<body>

<div id="registracia">
<h3>Registrujte sa:</h3>
<form>
<h3>Meno:</h3>
    <div class="login"><input id="regLogin" class="loginName" type="text" name="nickNameReg" placeholder="Zadajte meno ..." onchange="regFcn"></div>
    <h3>Heslo:</h3>
    <div class="login"><input id="regPass" class="loginName" type="password" name="passwordReg" placeholder="Zadajte heslo ..." onchange="regFcn"></div>
    <h3>Heslo znova:</h3>
    <div class="login"><input id="regPassCheck" class="loginName" type="password" name="passwordCheck" placeholder="Zadajte heslo znova ..." onchange="regFcn"></div>
    <div id="registerWrong"><h3 id="wrong">Vami zadané meno už je v databáze alebo nebola zadaná niektoá povinná položka!</h3></div>
    <div class="buttons"><button id="registration" name="action" value="registration" type="submit">Registrovať sa</button></div>
    <div class="buttons"><button id="reload" type="refresh">Späť</button></div>
</form>
</div>

<div id="prihlasenie">
<h3>Prihláste sa:</h3>
<form>
<h3>Meno:</h3>
    <div class="login"><input id="loginInput" class="loginName" type="text" name="nickName" placeholder="Zadajte prihlasovacie meno ..." onchange="loginFcn()"></div>
    <h3>Heslo:</h3>
    <div class="login"><input id="passInput" class="loginName" type="password" name="password" placeholder="Zadajte heslo ..." onchange="loginFcn()"></div>
    <div id="loginWrong"><h3 id="wrong">Zlé prihlasovacie meno alebo heslo!</h3></div>
    <div class="buttons"><button id="withName" name="action" value="setNickName" type="submit">Prihlásiť</button></div>
    <div class="buttons"><button id="reload" type="refresh">Späť</button></div>
</form>
</div>

<form>
    <div class="buttons"><button id="reg" type="button" onclick="register()">Registrácia</button></div>
    <div class="buttons"><button id="log" type="button" onclick="login()">Prihlásenie</button></div>
    <div class="buttons"><button id="withoutName" name="action" value="hratBezPrihlasenia" type="submit">Hrať bez prihlásenia</button></div>
</form>

<script type="text/javascript">
document.getElementById("registracia").style.display = "none";
document.getElementById("prihlasenie").style.display = "none";
document.getElementById("loginWrong").style.visibility = "hidden";
document.getElementById("registerWrong").style.visibility = "hidden";

function login(){
	document.getElementById("registracia").style.display = "none";
	document.getElementById("prihlasenie").style.display = "inline";
	document.getElementById("log").style.display = "none";
	document.getElementById("reg").style.display = "none";
	document.getElementById("withoutName").style.display = "none";
	document.getElementById("loginWrong").style.visibility = "hidden";
}

function register(){
	document.getElementById("registracia").style.display = "inline";
	document.getElementById("prihlasenie").style.display = "none";
	document.getElementById("log").style.display = "none";
	document.getElementById("reg").style.display = "none";
	document.getElementById("withoutName").style.display = "none";
	document.getElementById("registerWrong").style.visibility = "hidden";
}

function loginWrong(){
	document.getElementById("registracia").style.display = "none";
	document.getElementById("prihlasenie").style.display = "inline";
	document.getElementById("log").style.display = "none";
	document.getElementById("reg").style.display = "none";
	document.getElementById("withoutName").style.display = "none";
	document.getElementById("loginWrong").style.visibility = "visible";
}

function registerWrong(){
	document.getElementById("registracia").style.display = "inline";
	document.getElementById("prihlasenie").style.display = "none";
	document.getElementById("log").style.display = "none";
	document.getElementById("reg").style.display = "none";
	document.getElementById("withoutName").style.display = "none";
	document.getElementById("registerWrong").style.visibility = "visible";
}

<%=request.getAttribute("loginWrong")%>
<%=request.getAttribute("registerWrong")%>

var loginInput = document.getElementById("loginInput");
var passInput = document.getElementById("passInput");
document.getElementById("withName").disabled = true;

function loginFcn() {
	if (loginInput.value != "" && passInput.value != "") {
		document.getElementById("withName").disabled = false;
	} else {
		document.getElementById("withName").disabled = true;
	}
}

setInterval('loginFcn()',100); 

var regLogin = document.getElementById("regLogin");
var regPass = document.getElementById("regPass");
var regPassCheck = document.getElementById("regPassCheck");
document.getElementById("registration").disabled = true;

function regFcn() {
	if (regLogin.value != "" && regPass.value != "" && regPassCheck.value != "") {
		if (regPass.value == regPassCheck.value) {
			regPass.style.backgroundColor = "#ccffcc";
			regPassCheck.style.backgroundColor = "#ccffcc";
			document.getElementById("registration").disabled = false;
		} else {
			regPass.style.backgroundColor = "#ffcccc";
			regPassCheck.style.backgroundColor = "#ffcccc";
			document.getElementById("registration").disabled = true;
		}
// 		document.getElementById("registration").disabled = false;
	} else {
		document.getElementById("registration").disabled = true;
	}
}

setInterval('regFcn()',100); 

</script>

</body>
</html>