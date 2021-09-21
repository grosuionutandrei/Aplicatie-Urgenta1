<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CreateAccount</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
</head>
<body>
<p>Welcome to our registration page, follow the following steps to create your account</p>
	<div class="container">
		<form class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Nume :</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name" value="<c:out value="${client.nume}"/>" >

			</div>
				<label class="control-label col-sm-2" for="name">Prenume :</label>
                	<div class="col-sm-10">
                	 <input type="text" class="form-control" id="prenume" name="prenume" value="<c:out value="${client.prenume}"/>" >
                	</div>
				<label class="control-label col-sm-2" for="name">Parola: </label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="parola" name="parola" value="<c:out value="${client.parola}"/>" >
				</div>
			</div>

			<c:if test="${not empty error}">
				<div class="alert alert-danger">Error: ${error}</div>
			</c:if>

			<div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" value="Submit"  class="btn btn-warning" >
            <a href ="<c:url value="/login"/>" class="btn btn-primary">Abort</a>
            </div>

		</form>
	</div>

</body>
</html>
