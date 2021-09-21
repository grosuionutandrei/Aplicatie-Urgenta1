<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Localitati </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

   <script type="text/javascript">
       function displayFormLoc(){
                     document.getElementById("nameLocal").style.display = "";
                     document.getElementById("nameLocal1").style.display = "";
                     document.getElementById("nameCommune").style.display = "";
                     document.getElementById("nameCommune1").style.display = "";
                     document.getElementById("formLoc").style.display = "";
                     document.getElementById("cancelLoc").style.display = "";
                     }
      </script>
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
          <a href ="/users" class="btn-link">Utilizatori</a>
       </div>

        <div class="col">
                                <a href ="/logout" class="btn btn-primary">Logout</a>
        </div>

  </div>
</nav>
<div class="row">
<div class="col-sm-5">
<div class="wraper">
<form  role="form" method="post" >
   <div class="col">
   <button type= "button" id="addCommune" class="btn btn-light"  onclick="displayFormLoc()" >Adauga o noua localitate</button>

   </div>
    <div class="form-group">
                <label for="name" id="nameLocal" style="display:none" >Nume localitate</label>
                <input type="text" name="nameLoc" class="form-control" id="nameLocal1" style="display:none" value="" >
    </div>

    <div class="form-group">
                <label for="name" id="nameCommune" style="display:none" >Nume comuna</label>
                <input type="text" name="nameCom" class="form-control" id="nameCommune1" style="display:none" value="" >
    </div>

    <div class="form-group">
             <input type="submit" value="Aduga" class="btn btn-info" id="formLoc" style="display:none" >
             <a href="<c:url value="/comune"/> " class="btn btn-info" id="cancelLoc" style="display:none">Cancel</a>
    </div>
</form>
</div>
</div>

<div class="col-sm-6">
<div class="wraper">
   <h5>Localitati</h5>
<table id="people" class="table table-striped" width='100%' border="1px solid black">
                      <tr>
                      <th>Nume</th>
                      <th>Comuna</th>
                      <th>Optiuni</th>
                      </tr>

                       <c:forEach items="${settlements}" var="settlement">
                         <tr>
                         <td><c:out value="${settlement.getSettlement()}"/></td>
                         <td><c:out value="${settlement.getCommune()}"/></td>
                         </td>
                           <td>
                           <a href="<c:url value="/localitati/${settlement.idSettlement}/edit"/> " class="btn btn-info">Edit</a>
                           <a href="<c:url value="/localitati/${settlement.idSettlement}/edit"/> " class="btn btn-info">Delete</a></td>
                         </tr>
                       </c:forEach>
</table>
</div>
</div>

</div>
</body>
</html>


