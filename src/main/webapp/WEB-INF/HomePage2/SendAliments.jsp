<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Send Aliments </title>
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
                 <a href ="/listaIzolati" class="btn-link">Lista izolati</a>
       </div>

       <div class="col">
                 <a href ="/listaHandicap" class="btn-link">Lista Handicap</a>
       </div>

       <div class="col">
                <a href ="/statisticiPrimarie" class="btn-link">Statistici</a>
       </div>

       <div class="col">
                  <a href ="/logout" class="btn btn-primary">Logout</a>
       </div>
  </div>
</nav>
<div class="row">
<form   role="form" method="post">
   <div class="wraper">

   <table id="alimente" class="table table-striped" width='100%' border="1px solid black" style="display:" >
   <tr>
                <th>Tip aliment</th>
                <th>Numar Bucati</th>
   </tr>
     <c:forEach items="${aliments}" var="aliment">
                 <tr>
                   <td>
                   <c:out value="${aliment.getTipAliment()}"/>
                   </td>
                   <td>
                 <input type="text" name="prodBuc" id="prod"  class="form-control" value="">
                 <input type="hidden" name="alimentId" value="${aliment.id}+'prod.value'">
                   </td>
                 </tr>
                 </c:forEach>

   </table>


    </div>
    <div class="form-group">
    <input type="submit" id="trimite" value="Trimite" class="btn-btn-info" style="display:" onclick="clickedTrimite(event)" >
    </div>

   </form
</div>
<script>

 function clickedTrimite(e)
 {
    var aliment= document.getElementById("prod");
        if(aliment.value==0) {
          e.preventDefault()
         alert("Introduceti numarul de bucati");
     }
 }


</script>




</body>
</html>