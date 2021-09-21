<%@ page contentType="text/html;  charset=UTF-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> HomePage 2</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="collapse navbar-collapse, container" id="collapsibleNavbar">


        <div class="col">
           <a href ="/contactePrimarie" class="btn-link">Contacte</a>
        </div>

        <div class="col">
           <a href ="/alimentePrimarie" class="btn-link">Alimente</a>
        </div>
        <div class="col">
           <a href ="/statisticiPrimarie" class="btn-link">Statistici</a>
        </div>
        <div class="col">
        <a href ="/logout" class="btn btn-primary">Logout</a>
        </div>

  </div>
</nav>

<div class="container-fluid" style="margin-top:30px" >
      <div class="row" >
            <div class="col-sm-2">
      <div>
           <form name = "searchForm" id  = "searchForm" method = "post">
                 <label for="site-search">Cauta :</label>
                 <input type="text" id="site-search" name="name" aria-label="Search through site content">
                 <button class="btn btn-info " >Cauta</button>
           </form>
      </div>
            <div>
                 <a href="/addLocalnici"  class = "btn btn-info"> Adauga </a>
            </div>
      <div>
           <p>Selecteaza localitatea:</p>
               <c:forEach items = "${Settlements}" var ="settlement">
                    <div>
                         <a href="<c:url value="/homePage2/${settlement.name}"/> " class="btn btn-light ">${settlement.name}</a>
                    </div>
               </c:forEach>
      </div>
</div>
<div class="col-sm-8">
     <div>
     <table id="people" class="table table-striped" width='100%' border="1px solid black">
                <tr>
                <th>Nume</th>
                <th>Stare</th>
                <th>Varsta</th>
                <th>Observatii</th>
                <th>Optiuni</th>
                </tr>

                <c:forEach items="${peopleList}" var="people">
                 <tr ${people.state eq "nesalvat" ? 'style="background-color:red"' : 'style="background-color:green"'}>
                   <td>
                   <c:out value="${people.name}"/></td>
                   <td><c:out value="${people.state}"/></td>
                   <td><c:out value="${people.age}"/></td>
                   <td><c:out value="${people.observatii}"/></td>
                   <td>
                   <a href="<c:url value="/homePage2/${people.id}/edit"/> " class="btn btn-info">Modifica</a>
                   <a href="<c:url value="/homePage2/${people.id}/delete"/> " class="btn btn-info">Delete</a>
                   </td>
               </td>
                   </tr>
                   </c:forEach>
                   </table>
                  </div>
     </div>
</div>
</div>
</div>