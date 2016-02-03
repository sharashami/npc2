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
	<h1 style="margin-left:200px">Chapa</h1>

	<form method="post" action="index.jsp" class="form-container">
		
		
		
	
		<table class="tabela">
			<tr style="background:#696969">
				<th>Nome da chapa</th>
				<th>Composição</th>
			    <th id="acao">Ação</th>
		    </tr>
			   <c:forEach items="${chapas}" var="c">
				   	<tr>
						<td >${c.nome}</td>
						<td >${c.composicao}</td>
					    <td>
					    
					    
						<a href="${pageContext.request.contextPath}/ServletChapa?acao=editar&id=${c.id}">Editar</a>
						<a href="${pageContext.request.contextPath}/ServletChapa?acao=excluir&id=${c.id}">Remover</a>
					    
					    <!-- <input type="button" name="teditar" value="Editar" class="btn-primary"/>
						<input type="button" name="Excluir" value="Excluir" class="btn-primary"/> -->
						</td>
				    </tr>
			 	  
			   
			   </c:forEach>
			 
			 

		</table>
	</form>

</body>
</html>