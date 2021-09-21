<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Utilizatori</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="collapse navbar-collapse, container" id="collapsibleNavbar">
       <div class="col">
                 <a href ="/adminPage" class="btn-link">HomePage</a>
       </div>

       <div class="col">
                 <a href ="/comune" class="btn-link">Comune</a>
       </div>

       <div class="col">
          <a href ="/localitati" class="btn-link">Localitati</a>
       </div>

        <div class="col">
           <a href ="/logout" class="btn btn-primary">Logout</a>
        </div>

  </div>
</nav>
<div class="row">
<div class="wraper">
    <table id="users" class="table table-striped" width='100%' border="1px solid black">
                         <tr>
                         <th>Nume</th>
                         <th>Rol</th>
                         <th>Nr.Telefon</th>
                         <th>Optiuni</th>
                         </tr>

                          <c:forEach items="${users}" var="user">
                            <tr ${alarm.isValue()? 'style="background-color:green"' : 'style="background-color:red"'}>
                            <td><c:out value="${user.getName()}"/></td>
                            <td><c:out value="${user.getPhoneNumber()}"/></td>
                            <td><c:out value="${user.getRole()}"/></td>
                          <td>
                             <a href="<c:url value="/users/${user.id}/edit"/> " class="btn btn-info">Edit</a>
                             <a href="<c:url value="/users/${user.id}/delete"/> " class="btn btn-info">Delete</a></td>
                            </td>
                            </td>
                            </tr>
                          </c:forEach>
          </table>
          <div class="col">
           <a href="<c:url value="/users/add"/> " class="btn btn-light">Adauga utilizator nou</a></td>
          </div>



</div>


</div>







</body>
</html>