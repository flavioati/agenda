<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="agenda.model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeans> contatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon/png">
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<script src="scripts/confirmador.js"></script>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Button1">Novo contato</a>
	<a href="report" class="Button2"> Relatório</a>
	<table id="contatos-table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
			</tr>
		</thead>
		<tbody>
			<%for (JavaBeans contato : contatos) {%>
			<tr>
				<%String idcon = contato.getIdcon();%>
				<td><%=idcon%></td>
				<td><%=contato.getNome()%></td>
				<td><%=contato.getFone()%></td>
				<td><%=contato.getEmail()%></td>
				<td>
					<a href="select?idcon=<%=idcon%>" class="Button1">Editar</a>
					<a href="javascript: confirmar(<%=idcon%>)" class="Button2">Deletar</a>
				</td>
			</tr>
			<%}%>
		</tbody>
	</table>
</body>
</html>