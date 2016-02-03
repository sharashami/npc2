<%@ taglib uri="/WEB-INF/lib/c.tld" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chapa</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-theme.min.css" />



</head>
<body>
<%@ include file="/menu.jsp" %>
	<h1 style="margin-left: 200px">Resultado</h1>
	
	<form method="post" action="index.jsp" class="form-container">

		<table class="tabela">
			<tr style="background: #696969">
				<th>Nome da Chapa</th>
				<th>Quantidade de votos</th>
				<th >Porcentagem</th>
			</tr>

			<c:forEach items="${resultado}" var="r">
				<tr>
					<td>${r.chapa.nome}</td>
					<td>${r.votos}</td>
					<td>${r.porcentagem}%</td>
				</tr>
			</c:forEach>
		</table>
		<input type="button" value="Voltar" name="btn_votar" class="btn-primary">
	</form>

</body>
</html>