<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> AddUser </title>
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
<div class="wraper">
    <form  role="form" method="post" >
      <div class="form-group">
          <label for="name" >Nume</label>
          <input type="text" name="name" class="form-control" id="name" value="">
      </div>

      <div class="form-group">
         <label for="phoneNumber" >Numarul de telefon</label>
         <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" value="">
      </div>

      <div class="form-group" >
           <label for="rolUser">Rol</label>
                             <select name="rolUser" id="rolUser" class="form-control">
                              <option selected>selecteaza rolul</option>
                             <c:forEach items="${roles}" var="rol">
                                <option id="rol"  value="${rol}">${rol}</option>
                             </c:forEach>
                         </select>
      </div>

    <div class="form-group">
               <label for="password" >Parola</label>
               <input type="password" name="password" class="form-control" id="password" value="">
    </div>
    <div class="form-group">
    <input type="submit" value="Aduga" class="btn btn-info">
    <a href="<c:url value="/users"/> " class="btn btn-info">Back</a>
    </div>

</div>
</body>
</html>