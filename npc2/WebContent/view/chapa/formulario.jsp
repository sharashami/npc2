<%@ taglib uri="/WEB-INF/lib/c.tld" prefix="c" %>
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
<%@ include file="/menu.jsp" %>
	<h1 style="margin-left:200px">Cadastrar Chapa</h1>

	<form method="post" action="${pageContext.request.contextPath}/ServletChapa?acao=form" class="form-container">
		<input type="hidden" name="id" value="${chapa.id}"/>
		<table>
			<tr>
				<th>Nome da chapa:</th>
				<td><input type="text" name="nomechapa" value="${chapa.nome}"/></td>
			</tr>
			
			<c:choose >
				<c:when test="${!empty chapa.integrante}">
					<c:forEach var="i" items="${chapa.integrante}">
						<tr>
							<th>Matricula do ${i.funcao}</th>
							<td><input type="text" name="${i.funcao}matricula" value="${i.matricula}" /></td>
						</tr>
						<tr>
							<th>Nome ${i.funcao}</th>
							<td><input type="text" name="${i.funcao}nome" value="${i.nome}"/></td>
			
						</tr>
			
						<tr>
							<th>Curso do ${i.funcao}</th>
							<td><input type="text" name="${i.funcao}curso" value="${i.curso}" /></td>
			
						</tr>
					</c:forEach>
				</c:when>
				
				<c:otherwise>
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
				</c:otherwise>
			</c:choose>
			
		</table>
		
		<c:choose >
				<c:when test="${chapa.id > 0}">
					<input type="submit" name="botao" value="Editar" class="btn-primary"/>
				</c:when>
				<c:otherwise>
					<input type="submit" name="botao" value="Salvar" class="btn-primary"/>
				</c:otherwise>
		</c:choose>
	</form>

</body>

</html>

