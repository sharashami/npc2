<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Autenticação de Usuário</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-theme.min.css" />

<link href="${pageContext.request.contextPath}/css/layout.css"
	rel="stylesheet" type="text/css" />
</head>


<body>
	<h1 style="margin-left:200px">Cadastrar Chapa</h1>

	<form method="post" action="index.jsp" class="form-container">
		<table>
			<tr>
				<th>Nome da chapa:</th>
				<td><input type="text" name="nomechapa" /></td>
			</tr>
			<tr>
				<th>Matricula do Presidente</th>
				<td><input type="text" name="presidentematricula" /></td>
			</tr>
			<tr>
				<th>Nome Presidente</th>
				<td><input type="text" name="presidentenome" /></td>

			</tr>

			<tr>
				<th>Curso do Presidente</th>
				<td><input type="text" name="presidentecurso" /></td>

			</tr>
			
			<tr>
				<th>Matricula do Secretario</th>
				<td><input type="text" name="secretariomatricula" /></td>
			</tr>
			<tr>
				<th>Nome do Secretário</th>
				<td><input type="text" name="secretarionome" /></td>
			</tr>

			<tr>
				<th>Curso do Secretario</th>
				<td><input type="text" name="secretariocurso" /></td>
			</tr>

<tr>
				<th>Matricula do Tesoureiro</th>
				<td><input type="text" name="tesoureiromatricula" /></td>
			</tr>
			<tr>
				<th>Nome do Tesoureiro</th>
				<td><input type="text" name="tesoureironome" /></td>
			</tr>

			<tr>
				<th>Curso do Tesoureiro</th>
				<td><input type="text" name="tesoureirocurso" /></td>
			</tr>
			
		</table>
		
		<input type="submit" name="cadastrar" value="Salvar" class="btn-primary"/>
		
	</form>

</body>

</html>

