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

           <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                 <div class="collapse navbar-collapse, container" id="collapsibleNavbar">
                   <div class="col">
                          <a href ="/homePage1" class="btn-link"  >Home Page</a>
                       </div>
                         <div class="col">
                         <a href ="/localnici" class="btn-link">Localnici</a>
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
<div class  = "col-sm-2">



<div>
     <form name = "searchForm" id  = "searchForm" method = "post">
     <label for="site-search">Cauta :</label>
     <input type="text" id="site-search" name="name"
            aria-label="Search through site content">
     <button class="btn btn-info">Cauta</button>
     <a href="<c:url value="/listaIzolati"/> " class="btn btn-info">Back</a>
     </form>
     </div>
</div>
<div class= "col-sm-10">
<div>
          <table id="people" class="table table-striped" width='100%' border="1px solid black">
           <tr>
           <th>Nume</th>
           <th>Stare</th>
           <th>Varsta</th>
           <th>Observatii</th>
           <th>Mijloc de acces</th>
           <th>Optiuni</th>

           </tr>
           <c:forEach items="${peoples}" var="people">
            <tr ${people.state eq "nesalvat" ? 'style="background-color:red"' : 'style="background-color:green"'}>
              <td>
              <c:out value="${people.name}"/></td>
              <td><c:out value="${people.state}"/></td>
              <td><c:out value="${people.age}"/></td>
              <td><c:out value="${people.observatii}"/></td>
              <td><c:out value="${people.mijlocDeAcces}"/></td>
              <td><a href="<c:url value="/listaIzolati/${people.id}/detalii"/> " class="btn btn-info">Detalii/Modifica</a></td>
              </tr>
              </c:forEach>
              </table>
              </div>

</div>
</div>
</div>