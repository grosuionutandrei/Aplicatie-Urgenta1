<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> HomePage</title>
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
                 <a href ="/users" class="btn-link">Utilizatori</a>
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
         <div class="col">
          <h5>Situatie alarme</h5>
         </div>
      <table id="people" class="table table-striped" width='100%' border="1px solid black">
                      <tr>
                      <th>Comuna</th>
                      <th>Stare</th>
                      </tr>

                       <c:forEach items="${alarms}" var="alarm">
                         <tr ${alarm.isValue()? 'style="background-color:red"' : 'style="background-color:green"'}>
                         <td><c:out value="${alarm.getCommune()}"/></td>
                         <td><c:out value="${alarm.isValue().toString()}"/></td>
                         </td>
                         </tr>
                       </c:forEach>
       </table>
</div>
</div>
</body>
</html>