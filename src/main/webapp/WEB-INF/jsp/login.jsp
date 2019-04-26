<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Pulse Survey</title>
<link rel="stylesheet" href="css/site.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

<div class="container">
	<div class="row justify-content-center align-items-center"
		style="height: 100vh">
		<div class="col-4">
			<div class="card">
				<div class="card-body">
					<c:url var="pulseLogo" value="img/electricity.png" />
					<h1><img class="icon" src="${pulseLogo}" height="40" width="40">  Pulse Survey</h1>
					<c:url var="loginURL" value="login" />
					<form action="${loginURL}" method="POST">
						<div class="form-group">
							<input type="text" class="form-control" name="userName" placeholder="Username">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="password" placeholder="Password">
						</div>
						<button type="submit" id="sendlogin" class="btn btn-primary">login</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />

