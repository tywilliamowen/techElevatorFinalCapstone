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


	<h1>
		<c:out value="${selectedQuestion.questionText}"></c:out>
	</h1>

	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

	<canvas id="myChart" width="400" height="200"></canvas>
	<script>

var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
    	
        labels: [<c:forEach items="${truncatedStats}" var="listItem"> `${listItem.answerText}`, </c:forEach>],
        datasets: [{
            label: '# of Votes',
            data: [<c:forEach items="${surveyQuestionStats}" var="listItem"> `${listItem.countOfAnswers}`, </c:forEach>],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});
</script>

	</br>
	<div class="list-group">
		<c:forEach var="answer" items="${answers}" varStatus="loop">

			<div class="list-group-item">
				<div class="d-flex w-100 justify-content-between">
					<h5 class="mb-1">
						<c:out value="${answer.answerText}" />
					</h5>
					<small><button type="button" class="btn btn-primary"
							data-toggle="modal" data-target="#editModal${loop.index}">Edit</button></small>
				</div>
				<p class="mb-1" style="text-align:left">
					<c:out value="${answer.studentName}" />
				</p>
			</div>

			<!-- Edit Answers Modal -->
			<div class="modal fade" id="editModal${loop.index}" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
						<c:url var="editAnswerURL" value="editAnswer"/>
						<form action="${editAnswerURL}" method="POST">
						<div class="form-group">
						<input class="form-control" type="text" value="${answer.answerText}" name="answerText">
						<input type="hidden" value="${answer.answerText}" name="beforeAnswerText">
						<input type="hidden" value="${answer.answerId}" name="id">
						<input type="hidden" value="${param.questionId}" name="questionId">
						<input type="hidden" value="${param.surveyId}" name="surveyId">
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
	</div>

	<div>
		<%-- 			<table class="table">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">Number of Responses</th>
						<th scope="col">Response</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stat" items="${surveyQuestionStats}">
					<tr>
							<td></td>
							<td><c:out value= "${stat.countOfAnswers}"/></td>
							<td><c:out value= "${stat.answerText}"/></td>
					</tr>
					</c:forEach>
				 </tbody>
			</table> --%>
	</div>

</section>



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