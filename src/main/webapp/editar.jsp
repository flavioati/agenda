<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="agenda.model.JavaBeans"%>
<%
JavaBeans contato = (JavaBeans) request.getAttribute("contato");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="idcon-box"
					placeholder="<%=contato.getIdcon()%>" readonly></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Box1"
					value="<%=contato.getNome()%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Box1"
					value="<%=contato.getEmail()%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="Box2"
					value="<%=contato.getFone()%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Button1"
			onClick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>