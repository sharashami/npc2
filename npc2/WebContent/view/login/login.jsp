<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Autenticação de Usuário</title>
<link rel="stylesheet" type="text/css" href="style.css">



<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-theme.min.css" />

<link href="${pageContext.request.contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
</head>


<body>
${pageContext.request.contextPath}

				<div class="loginmodal-container">
					<h1>LOGIN</h1><br>
				  <form  method="post" action="${pageContext.request.contextPath}/ServletLogin?acao=login">
					<label for="matricula">Matricula</label><input type="text" name="matricula" placeholder="Matricula">
					<label for="senha">Senha</label><input type="password" name="senha" placeholder="Senha">
					<input type="submit" name="login" class="login loginmodal-submit" value="Login">
				  </form>
					
				</div>
		
</body>

</html>

