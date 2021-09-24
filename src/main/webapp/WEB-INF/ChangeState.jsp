<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Localnici</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
             <style>
             img {
               display: block;
               margin-left: auto;
               margin-right: auto;
               width:30%;
             }
             </style>

    </head>

     <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
       <div class="collapse navbar-collapse, container" id="collapsibleNavbar">
         <div class="col">
                <a href ="/homePage1" class="btn-link"  >Home Page</a>
             </div>
               <div class="col">
               <a href ="/localnici" class="btn-link">Localnici</a>
               </div>
             <div class="col">
               <a href ="/listaIzolati" class="btn-link">Lista izolati</a>
             </div>
             <div class="col">
               <a href ="/listaHandicap" class="btn-link">Lista Handicap</a>
             </div>
             <div class="col">
               <a href ="/contacteUtile" class="btn-link">Contacte utile</a>
             </div>
             <div class="col">
               <a href ="/statistici" class="btn-link">Statistici</a>
             </div>
             <div class="col">
               <a href ="/ajutoareAlimentare" class="btn-link">Ajutoare alimentare</a>
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
      <div class = "container-fluid">
       <input type="submit" value="Submit" class="btn btn-info">
       <a href="<c:url value="/localnici"/> " class="btn btn-info">Back</a>
       </div>
      </form>
    </div>
    <div class="col-sm-4">

          <h4>Nume: ${people.name}</h4>
          <hr>
          <h4>Stare: ${people.state}</h4>
          <hr>
          <h4>Varsta: ${people.age}</h4>
          <hr>
          <h4>Observatii :${people.observatii}</h4>
    </div>
    <div class="col-sm-6">
    <image src="data:image/jpeg;base64,${people.getImage()}">
    </div>
     </div>
     </body>
     </html>