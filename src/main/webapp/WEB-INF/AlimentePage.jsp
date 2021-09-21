
    <%@ page contentType="text/html;charset=UTF-8" language="java"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>
      <head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Alimente</title>
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

    <div class= "col-sm-10">

    <div>
    <c:choose>
             <c:when test = "${day==1}">
                <p>Necesarul de alimente per persoana pentru o zi.</p>
                </c:when>
             <c:when test = "${day>1}">
                <p>Necesarul de alimente per persoana pentru ${day} zile.</p>
             </c:when>
    </c:choose>
    <table id="people" class="table table-striped" width='100%' border="1px solid black">
               <tr>
               <th>Nume aliment</th>
               <th>Bucati pe persoana</th>
               </tr>
               <c:forEach items="${alimente}" var="aliment">
                <tr>
                  <td>
                  <c:out value="${aliment.getName()}"/></td>
                  <td><c:out value="${aliment.getValue()}"/></td>

              </td>
                  </tr>
                  </c:forEach>

                  </table>
                 </div>
    </div>

    </div>





