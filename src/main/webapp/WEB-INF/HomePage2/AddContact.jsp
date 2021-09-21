<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Add contact </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
                               <a href ="/homePage2" class="btn-link">HomePage</a>
    </div>

         <div class="col">
                  <a href ="/alimentePrimarie" class="btn-link">Alimente</a>
         </div>

          <div class="col">
                         <a href ="/statistici" class="btn-link">Statistici</a>
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
         <label for="rol">Rol</label>
         <input type="text" name="rol" class="form-control" id="rol" value="">
      </div>

     <div class="form-group">
                    <label for="settlement">Selecteaza localitatea: </label>
                     <select name="settlement" id="settlement" class="form-control">
                      <option selected>selecteaza localitatea</option>
                     <c:forEach items="${settlements}" var="settlement">
                        <option id="settlement"  value="${settlement.getName()}">${settlement.getName()}</option>
                     </c:forEach>
                 </select>
     </div>

   <div>
         <input type="submit" value="Adauga" class="btn btn-info">
         <a href="<c:url value="/contactePrimarie"/> " class="btn btn-info">Back</a>
   </div>

</form>
</div>
</body>
</html>

