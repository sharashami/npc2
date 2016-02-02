<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Inicio</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrap-theme.min.css"/>

<link href="${pageContext.request.contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
</head>


<body>
	<h1 style="margin-left:100px">Sistema de votação - DCE - UECE</h1>

<a href="${pageContext.request.contextPath}/ServletChapa?acao=form">cadastrar chapa</a>

	<form method="post" action="index.jsp" class="container" id="content">
		<table>
			<tr>
				
				<td colspan="2"><input type="button" name="cadstrarchapa" value="Cadastrar Chapa" class="btn btn-primary button"/></td>
				<td colspan="2"><input type="button" name="votar" value="Votar" class="btn btn-primary button""/></td>
				<td colspan="2"><input type="button" name="visualizarvotos" value="Resultados"  class="btn btn-primary button""/></td>
			</tr>
		
		</table>
	</form>
</body>

</html>
