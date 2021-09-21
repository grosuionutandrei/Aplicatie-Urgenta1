<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Adauga aliment </title>
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
                  <a href ="/contacte" class="btn-link">Contacte</a>
          </div>

          <div class="col">
                         <a href ="/statistici" class="btn-link">Statistici</a>
          </div>

          <div class="col">
                         <a href ="/logout" class="btn btn-primary">Logout</a>
          </div>
  </div>
</nav>

<div class="wraper">
<form  role="form" method="post">
  <div class="form-group">
      <label for="tipAliment" >Nume</label>
      <input type="text" name="tipAliment" class="form-control" id="tipAliment" value="">
  </div>

  <div class="form-group">
     <label for="calories" >Calorii</label>
     <input type="text" name="calories" class="form-control" id="calories" value="">
  </div>

  <div class="form-group">
     <label for="quantity">Cantitate</label>
     <input type="text" name="quantity" class="form-control" id="quantity" value="">
  </div>

   <div>
         <input type="submit" value="Adauga" class="btn btn-info" onclick="clickedFood(event)" >
         <a href="<c:url value="/alimentePrimarie"/> " class="btn btn-info">Back</a>
   </div>
</form>
<script>
function clickedFood(e)
{
   var calories= document.getElementById("calories");
   var quantity = document.getElementById("quantity");
   var tipAliment=document.getElementById("tipAliment");
  if(tipAliment.value ==0) {
    e.preventDefault()
        alert("Introduceti numele produsului");
    }else if(calories.value==0){
    e.preventDefault()
    alert("Introduceti numarul de calorii");
    }else if(quantity.value==0){
    e.preventDefault()
    alert("Introduceti cantitatea");
    }
}

</script>



</div>
</body>
</html>