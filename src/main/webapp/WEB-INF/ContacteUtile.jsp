<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> ContacteUtile</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
  <div class="col-sm-6"  style= "border-style: ridge" >
  <h5>Pompieri</h5>
  <div>
  <table id="people" class="table table-striped" width='100%' border="1px solid black">
             <tr>
             <th>Nume</th>
             <th>Telefon</th>
             <th>Rol</th>
             <th>Localitate</th>
             </tr>

             <c:forEach items="${pompieri}" var="contact">
              <tr>
                <td>
                <c:out value="${contact.getName()}"/></td>
                <td><c:out value="${contact.getPhoneNumber()}"/></td>
                <td><c:out value="${contact.getRol()}"/></td>
                <td><c:out value="${contact.getLocalitate()}"/></td>
                </td>
              </tr>
              </c:forEach>
  </table>
  </div>
  </div>
  <div class="col-sm-6"  style= "border-style: ridge"  >
  <h5>Primarie</h5>
    <div>
    <table id="people" class="table table-striped" width='100%' border="1px solid black">
               <tr>
               <th>Nume</th>
               <th>Telefon</th>
               <th>Rol</th>
               <th>Localitate</th>
               </tr>
               <c:forEach items="${primarie}" var="contact">
                <tr>
                <td>
                  <c:out value="${contact.getName()}"/></td>
                  <td><c:out value="${contact.getPhoneNumber()}"/></td>
                  <td><c:out value="${contact.getRol()}"/></td>
                  <td><c:out value="${contact.getLocalitate()}"/></td>
                </td>
                </tr>
                </c:forEach>
    </table>
    </div>
  </div>
</div>