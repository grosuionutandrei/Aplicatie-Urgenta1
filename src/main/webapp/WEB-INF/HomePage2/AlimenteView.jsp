<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Alimente </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
                                <a href ="/homePage2" class="btn-link">HomePage</a>
          </div>


         <div class="col">
                  <a href ="/contactePrimarie" class="btn-link">Contacte</a>
          </div>

          <div class="col">
                         <a href ="/statisticiPrimarie" class="btn-link">Statistici</a>
          </div>

          <div class="col">
                         <a href ="/logout" class="btn btn-primary">Logout</a>
          </div>
  </div>
</nav>

<div class="wraper">
<table id="alimente" class="table table-striped" width='100%' border="1px solid black">
             <tr>
             <th>Tip aliment</th>
             <th>Calorii</th>
             <th>Cantitate in stoc</th>
             <th>Optiuni</th>
             </tr>
             <c:forEach items="${aliments}" var="aliment">
              <tr>
                <td>
                <c:out value="${aliment.getTipAliment()}"/></td>
                <td><c:out value="${aliment.getCalories()}"/></td>
                <td><c:out value="${aliment.getQuantity()} buc."/></td>
                <td>
                    <a href="<c:url value="/alimente/${aliment.id}/delete"/> " class="btn btn-info">Delete</a></td>
                </td>
              </tr>
              </c:forEach>
  </table>
  <form   role="form" method="post">
  <div>
  <label for="selectAliment">Modifica cantitatea</label>
   <select name="aliment" id="selectAliment" class="form-control">
           <option selected value="selecteaza alimentul">selecteaza alimentul</option>
           <c:forEach items="${aliments}" var="aliment">
           <option id="selectedAliment"   value="${aliment.getId()}">${aliment.getTipAliment()}</option>
           </c:forEach>
   </select>
   <span>
     <label for="number">Numar bucati</label>
     <input type="text" name="nrBucati" id="bucati" class="form-control" value="">
   </span>
  </div>
  <div>
    <input type="submit" value="Modifica" class="btn-btn-info" onclick="clicked(event)" >
  </div>
  </form>
  <div>
     <a href="<c:url value="/alimentePrimarie/adauga"/> " class="btn btn-info">Adauga</a>
     <%--<a href="<c:url value="/alimentePrimarie/trimite"/> " class="btn btn-info">Trimite alimente</a>--%>




  </div>
</div>
<script>

function clicked(e)
{
   var select= document.getElementById("selectAliment");
   var selected = select.options[select.selectedIndex].value;
   if(selected=="selecteaza alimentul"){
       e.preventDefault()
       alert("Selectati alimentul");
       }else if(document.getElementById("bucati").value.length ==0) {
    e.preventDefault()
        alert("Introduceti numarul de bucati");
    }
}

</script>

</body>
</html>