<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<c:url var="pulseLogo" value="img/electricity.png" />
	<img src="${pulseLogo}" height="40" width="40">
	<c:url var="surveyPage" value="/survey" />
	<a class="navbar-brand" href="${surveyPage}">Pulse Survey</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<c:url var="surveyPage" value="/survey" />
				<li class="nav-item active"><a class="nav-link" href="${surveyPage}">Home
						<span class="sr-only">(current)</span>
				</a></li>
			</ul>
			<div class="btn-group">
				<button type="button" class="btn btn-primary dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<c:url var="userLogo" value="img/icons/man.png" />
					<img src="${userLogo}" height="40" width="40">
					<c:out value="${currentUser.userName}"></c:out>
				</button>
				<div class="dropdown-menu dropdown-menu-right ml-auto mr-1">
					<c:set var="adminCheck" value="${currentUser.role}" />
					<c:if test="${adminCheck == 'Admin'}">
						<c:url var="userViewURL" value="userView" />
						<form action="${userViewURL}" method="GET">
							<button class="dropdown-item" type="submit">View Users</button>
						</form>
						<c:url var="logViewURL" value="log" />
						<form action="${logViewURL}" method="GET">
							<button class="dropdown-item" type="submit">View Log</button>
						</form>
					</c:if>
					<button class="dropdown-item" type="submit" data-toggle="modal"
						data-target="#changePasswordModal">Change Password</button>

					<c:url var="logoutURL" value="logout" />
					<form action="${logoutURL}" method="POST">
						<button class="dropdown-item" type="submit">Logout</button>
					</form>
				</div>
			</div>
		</div>
</nav>

<div class="list-group surveyView">
	<c:forEach var="user" items="${users}">

		<div class="list-group-item">
			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">
					<c:out value="${user.userName}"></c:out>
				</h5>

				<!-- Button trigger modals -->
				<small><button type="button" class="btn btn-primary"
						data-toggle="modal" data-target="#deleteModal${user.userNameId}">Delete</button>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#updateModal${user.userNameId}">Modify Role</button>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#oneTimePasswordModal${user.userNameId}">One-Time
						Password</button></small>
			</div>
			<p class="mb-1">
				<c:out value="Role: ${user.role}"></c:out>
			</p>
		</div>


		<!-- Delete Modal -->
		<div class="modal fade" id="deleteModal${user.userNameId}"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Are you Sure?</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<c:url var="deleteUserURL" value="deleteUser" />
						<form action="${deleteUserURL}" method="POST">
							<input type="hidden" value="${user.userNameId}" name="id">
							<button type="submit" class="btn btn-primary">Yes</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">No</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- Update Modal -->
		<div class="modal fade" id="updateModal${user.userNameId}"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Would you like
							to switch roles on this user?</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<c:url var="editUserURL" value="editUser" />
						<form action="${editUserURL}" method="POST">
							<input type="hidden" value="${user.role}" name="role"> <input
								type="hidden" value="${user.userNameId}" name="id">
							<button type="submit" class="btn btn-primary">Yes</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">No</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- One-Time Password Modal -->
		<div class="modal fade" id="oneTimePasswordModal${user.userNameId}"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">One-Time
							Password</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<c:url var="changePasswordURL" value="setOneTimePassword" />
						<div class="form-group">
							<form action="${changePasswordURL}" method="POST">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password" name="password"> <input
									type="hidden" class="form-control" value="${user.userNameId}"
									name="userNameId">
							
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save
							changes</button>
							</form>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<!-- Button trigger Add User modal -->
	<button type="button" class="btn btn-light btn-lg btn-block"
		data-toggle="modal" data-target="#addUserModal">Add User</button>

</div>

<!-- Add User Modal -->
<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Input User</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="textLeft" action="${userViewURL}" method="POST">
					<div class="form-group">
						<label for="exampleInputEmail1">Username</label> <input
							type="text" class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" placeholder="Enter Username"
							name="userName">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" id="exampleInputPassword1"
							placeholder="Password" name="password">
					</div>
					<p>Role</p>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="role"
							id="inlineRadio1" value="Admin"> <label
							class="form-check-label" for="inlineRadio1">Admin</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="role"
							id="inlineRadio2" value="User"> <label
							class="form-check-label" for="inlineRadio2">Normal User</label>
					</div>
					</br> </br>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</div>


<!-- Change Password Modal -->
<div class="modal fade" id="changePasswordModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Change Password</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<c:url var="changePasswordURL" value="changePassword" />
				<form action="${changePasswordURL}" method="POST">
				<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" id="exampleInputPassword1"
							placeholder="Password" name="password"> <input
							type="hidden" class="form-control"
							value="${currentUser.userName}" name="userName">
				</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">Save changes</button>
				</form>
			</div>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />