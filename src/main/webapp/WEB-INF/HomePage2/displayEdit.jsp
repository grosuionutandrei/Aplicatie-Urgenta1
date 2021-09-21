<%@ page contentType="text/html;  charset=UTF-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
  <title> HomePage 2</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>



<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="collapse navbar-collapse, container" id="collapsibleNavbar">

     <div class="col">
            <a href ="/homePage2" class="btn-link">HomePage</a>
     </div>



     <div class="col">
        <a href ="/contactePrimarie" class="btn-link">Contacte</a>
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
      <div class="row" >
      <div class="container">
          <h4>Nume: ${peopleEdit.getName()}</h4>
          <hr>
          <h4>Stare: ${peopleEdit.getState()}</h4>
          <hr>
          <h4>Varsta: ${peopleEdit.getBirthDate()}</h4>
          <hr>
          <h4>Observatii : ${peopleEdit.getObservatii()}</h4>
          <hr>
          <h4>Mijloc de acces : ${peopleEdit.getMijlocDeAcces()}</h4>
          <hr>
          <h4>Tip handicap : ${peopleEdit.getTipHandicap()}</h4>
    <div>
       <a href="<c:url value="/homePage2"/> " class="btn btn-info">HomePage</a>
    </div>
      </div>
      </div>