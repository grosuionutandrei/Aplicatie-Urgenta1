<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>HomePage2LocalniciLocalitate</title>
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

<div class="container-fluid" style="margin-top:30px" >
      <div class="row" >
      <div class="col-sm-2" >

           <form name="selectForm" id="selectForm" method="post">
            <div class="container-fluid" >
                     <label for="selectState">Selecteaza starea:</label>
                     <select name="selectState" id="selectState" class="form-control">
                      <c:forEach items="${states}" var="state">
                                      <option id="salvat"  value="${state.getState()} + ${people.id}">${state}</option>
                      </c:forEach>
                     </select>
            </div>
            <div class="container-fluid">
             <label for="observatii">Observatii:</label>
            <input type="text" id="observatii" name="observatii">
            </div>
            <div class="container-fluid">
             <input type="submit" value="Submit" class="btn btn-info">
             <a href="<c:url value="/homePage2"/> " class="btn btn-info">Back</a>
             </div>
            </form>
          </div>
          <div class="col-sm-2">

                <h4>Nume: ${people.name}</h4>
                <hr>
                <h4>Stare: ${people.state}</h4>
                <hr>
                <h4>Varsta: ${people.age}</h4>
                <hr>
                <h4>Observatii : ${people.observatii}</h4>
          </div>







      </div>