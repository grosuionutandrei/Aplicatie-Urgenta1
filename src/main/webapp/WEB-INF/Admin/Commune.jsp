<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Comune </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

  <script type="text/javascript">
     function displayForm(){

                   document.getElementById("nameLabel").style.display = "";
                   document.getElementById("name").style.display = "";
                   document.getElementById("formsubmit").style.display = "";
                   document.getElementById("cancelForm").style.display = "";
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
                 <a href ="/users" class="btn-link">Utilizatori</a>
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
<div class="col-sm-6">
<div class="wraper">
<form  role="form" method="post" >
<div>
           <button type= "button" id="addCommune" class="btn btn-light"  onclick="displayForm()" >Adauga o noua comuna</button>
 </div>

         <div class="form-group">
             <label for="name" id="nameLabel" style="display:none" >Nume</label>
             <input type="text" name="name" class="form-control" id="name" style="display:none" value="" >
         </div>

       <div class="form-group">
          <input type="submit" value="Aduga" class="btn btn-info" id="formsubmit" style="display:none" >
          <a href="<c:url value="/comune"/> " class="btn btn-info" id="cancelForm" style="display:none">Cancel</a>
       </div>
</form>
</div>
</div>

<div class="col-sm-6">
<div class="wraper">
    <div class="col">
        <h5>Comune</h5>

    </div>
    <table id="users" class="table table-striped" width='100%' border="1px solid black">
                         <tr>
                         <th>Nume</th>
                         <th>Optiuni</th>
                         </tr>

                          <c:forEach items="${commune}" var="commune">
                            <td><c:out value="${commune.getName()}"/></td>
                          <td>
                             <a href="<c:url value="/comune/${commune.id}/edit"/> " class="btn btn-info">Edit</a>
                             <a href="<c:url value="/comune/${commune.id}/delete"/> " class="btn btn-info">Delete</a></td>
                            </td>
                            </td>
                            </tr>
                          </c:forEach>
          </table>

</div>
</div>
</div>
</body>
</html>