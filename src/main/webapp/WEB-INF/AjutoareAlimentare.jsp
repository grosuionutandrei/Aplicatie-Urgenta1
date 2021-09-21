<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Ajutoare alimentare</title>
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
      <a href="/homePage1" class="btn-link" >Home Page </a>
      </div>
    <div class="col">
      <a href ="/localnici" class="btn-link"  >Localnici</a>
    </div>
    <div class="col">
      <a href ="/listaIzolati" class="btn-link">Lista izolati</a>
    </div>
    <div class="col">
      <a href ="/listaHandicap" class="btn-link">Lista Handicap</a>
    </div>
    <div class="col">
      <a href="/contacteUtile" class="btn-link">Contacte utile</a>
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
  <div class="wraper" >
<form class="form-horizontal" role="form" method="post">
<div>
   <label  for="alimente">Introdu numarul de persoane:</label>
   <input type="text" class="form-control" id="alimente" name="numarPersoane" size= "10"value="">
</div>
   <c:if test="${not empty error}">
         				<div class="alert alert-danger">${error}</div>
   </c:if>
<div>
<label for="days">Selecteaza numarul de zile
<small>(Valoarea selectata automata este de o zi)</small>
</label>
        <select id= "days", name="days">
        	                    <option selected>1</option>
                				<c:forEach items="${days}" var = "day">
                			    <option value="${day}">${day}</option>
                			    </c:forEach>
        </select>
</div>

   <input type="submit" value="Trimite">
</form>

</div>







  </div>
  </div>