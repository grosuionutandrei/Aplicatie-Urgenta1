<%@ page contentType="text/html;  charset=UTF-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
  <title> Aduga localnic</title>
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
                              <a href ="/homePage2" class="btn-link">HomePage</a>
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

      <div class="wraper">
  <form  role="form" method="post">

    <div class="form-group">
         <label  for="name">Nume:</label>
         <input type="text" class="form-control" id="name" name="name" size= "10"value="">

    </div>
     <c:if test="${not empty error}">
                 <div class="alert alert-danger">${error}</div>
     </c:if>
    <div class="form-group">
         <label for="state">Selecteaza starea:</label>
         <br>
              <small>(Valoarea selectata automat este salvat)</small>
               <select name="state" id="state" class="form-control">
                <c:forEach items="${states}" var="state">
                                <c:if test="state.getstate() eq 'salvat'">
                                <option selected>${state}</option>
                                </c:if>
                                <option id="salvat"  value="${state.getState()}">${state}</option>
                 </c:forEach>
               </select>
    </div>

    <div class="form-group">
       <label  for="observatii">Observatii: </label>
       <br>
       <small>(Acest camp este optional)</small>
       <input type="text" class="form-control" id="observatii" name="observatii" size= "10"value="">
    </div>

    <div class="form-group">
       <label  for="date">Data nasterii :</label>
       <input type="date"  class="form-control" id="date" name="date" size= "10"value="">
    </div>

     <c:if test="${not empty error}">
                 <div class="alert alert-danger">${error}</div>
     </c:if>

    <div class="form-group">
                   <label for="settlement">Selecteaza localitatea: </label>
                <select name="settlement" id="settlement" class="form-control">
                    <option selected>Localitate</option>
                    <c:forEach items="${settlements}" var="settlement">
                       <option id="settlement"  value="${settlement.getName()}">${settlement.getName()}</option>
                    </c:forEach>
                </select>
    </div>

    <div class="form-group">
        <label for="image" id="iamgeLabel" >Imagine :</label>
          <input type="text" id="image" name="picture">
    </div>


    <div class="form-group">
      <label for="izolat">Izolat</label>
      <input type="checkbox" id="izolat" onclick="myFunction()">
      <input type="hidden"  id="hidden1" value="false"  name="isIzolat">
    </div>

    <div class="form-group">
      <label for="img" id="imgLabel" style="display:none">Select image:</label>
      <input type="file" id="img" name="map" accept="image/*" style="display:none">
    </div>

    <div class="form-group">
      <label for="mijlocDeAcces" id="mDaLabel" style="display:none" >Mijloc de acces</label>
      <input type="text" id="mijlocDeAcces" name="mijloc de acces" style="display:none" value=""
      class="form-control"  size= "10" >

    </div>

    <div class="form-group">
     <label for="handicap" id="handicapLabel">Handicap</label>
        <input type="checkbox" id="handicap" onclick="myFunction2()">
        <input type="hidden"  id="hidden2" value="false"  name="isHandicap">
    </div>

    <div class="form-group">
       <label for="handicapText" id="hLabel" style="display:none" >Tipul handicapului</label>
       <input type="text" id="handicapText" name="tip handicap" style="display:none" value=""
       class="form-control"  size= "10">
    </div>

    <div class="form-group">
       <input type="submit" value="Adauga" class="btn btn-info">
       <a href="<c:url value="/homePage2"/> " class="btn btn-info">Back</a>
    </div>

    </form>


    <script>
    function myFunction() {
        document.getElementById("imgLabel").style.display = "";
        document.getElementById("img").style.display = "";
        document.getElementById("mDaLabel").style.display = "";
        document.getElementById("mijlocDeAcces").style.display = "";
        document.getElementById("hidden1").value="true";

        }
    function myFunction2(){
       document.getElementById("hLabel").style.display = "";
       document.getElementById("handicapText").style.display = "";
       document.getElementById("hidden2").value="true";
        }
    </script>



</div>

</div>
</body>
</html>