<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Login</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
        <style>
                .wraper {
                    width: 50%;
                    max-width: 0 auto;
                    margin-left: auto;
                    margin-right: auto;
                }
                </style>

</head>
<body>
	<div class="wraper">
		<form class="form" role="form" method="post">
     <div class="form-group">
				<label  for="name">Nume :</label>
				<input type="text" class="form-control" id="user" name="user" value="">
    </div>
	<div class="form-group">
				 <label for="name">Parola :</label>
			     <input type="password" class="form-control" id="password" name="password" value="">
	</div>
	<div class = "form-group">
                				<label for="commune">Selecteaza comuna</label>
                			    <select id= "commune", name="commune">
                			    <option selected></option>
                				<c:forEach items="${commune}" var = "com">
                			    <option id="optionSelected"value="${com.name}">${com.name}</option>
                					</c:forEach>
                				</select>

	</div>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">Error: ${error}</div>
			</c:if>
	<div class="form-group">
			<button class="btn btn-primary"  onclick="clickedLogin(event)" >Login</button>
	</div>

		</form>
	</div>
  <script>
  function clickedLogin(e)
  {
     var name = document.getElementById("user");
     var password = document.getElementById("password");
     var commune=document.getElementById("commune");
    if(name.value ==0) {
      e.preventDefault()
          alert("Introduceti numele utilizatorului");
      }else if(password.value==0){
      e.preventDefault()
      alert("Introduceti parola ");
      }else if(commune.value==0){
      e.preventDefault()
      alert("Selectati o comuna");
      }
  }

  </script>
</body>
</html>
