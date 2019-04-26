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

<section class="detailSection">
	<div class="flexBox">
		<div class="flexItem title">
			</br>
			<h1>
				<c:out value="${selectedSurvey.surveyName}"></c:out>
			</h1>
		</div>
		<div class="flexItem">
			<div style="display: in-line">
				<h4>
					<c:out value="Location: ${selectedSurvey.campus}"></c:out>
				</h4>
				<h4>
					<c:out value="Cohort Number: ${selectedSurvey.cohortNumber}"></c:out>
				</h4>
			</div>
			<div style="display: in-line">
				<h4>
					<c:out value="Instructor: ${selectedSurvey.instructor}"></c:out>
				</h4>
				<h4>
					<c:out value="Topic: ${selectedSurvey.topic}"></c:out>
				</h4>
			</div>
		</div>
	</div>
	</br>
	<div class="list-group">
		<c:forEach var="question" items="${questions}">

			<c:url value="/answers" var="questionDetailsURL">
				<c:param name="questionId" value="${question.questionId}" />
				<c:param name="surveyId" value="${selectedSurvey.surveyId}" />
			</c:url>

			<div class="list-group-item">
				<div class="d-flex w-100 justify-content-between">
					<h5 class="mb-1">
						<c:out value="${question.questionText}"></c:out>
					</h5>

					<small><a href="${questionDetailsURL}"><button
								type="button" class="btn btn-primary">View</button></a></small>

				</div>
			</div>
		</c:forEach>
		<br> <br>
		<div>
			<table class="table">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">Question Number</th>
						<th scope="col">Number of Responses</th>
						<th scope="col">Response</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stat" items="${surveyStats}">
						<tr>
							<td></td>
							<td><c:out value="${stat.questionId}" /></td>
							<td><c:out value="${stat.countOfAnswers}" /></td>
							<td><c:out value="${stat.answerText}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${adminCheck == 'Admin'}">
	<button type="button" class="btn btn-primary btn-lg btn-block"
		data-toggle="modal" data-target="#exampleModal">Edit Survey</button>
		</c:if>
</section>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Input Info</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="<c:url value="editSurvey"/>" method="POST">
					<label>Location:</label> <select class="form-control" name="campus">
						<option value="Columbus">Columbus</option>
						<option value="Cleveland">Cleveland</option>
						<option value="Detroit">Detroit</option>
						<option value="Pittsburgh">Pittsburgh</option>
						<option value="Cincinnati">Cincinnati</option>
					</select> <br /> <label>Cohort Number:</label> <select class="form-control"
						name="cohortNumber">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select> <br /> <input type="text" class="form-control"
						placeholder="Instructor" name="instructor"
						value="${selectedSurvey.instructor}" required> <br /> <input
						type="text" class="form-control" placeholder="Topic" name="topic"
						value="${selectedSurvey.topic}" required>
						<input type="hidden" value="${param.surveyId}" name="id">
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save
							changes</button>
					</div>
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