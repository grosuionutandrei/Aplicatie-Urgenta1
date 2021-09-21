<%@ page contentType="text/html;  charset=UTF-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
  <title> HomePage 2</title>
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
          <a href ="/alimentePrimarie" class="btn-link">Alimente</a>
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
      <div class="row" >

      <div class="wraper">
       <c:if test="${not empty error}">
                       <div class="alert alert-danger">${error}</div>
       </c:if>
  <form clas="form" role="form" method="post">

    <div  class="form-group">
         <label  for="name">Nume:</label>
         <input type="text" class="form-control" id="name" name="name" size= "10"value="${peopleAdapter.getName()}">

    </div>

    <div  class="form-group">
         <label for="state">Selecteaza starea:</label>
         <br>
               <select name="state" id="state" class="form-control">
               <option selected>${peopleAdapter.getState()}</option>
                <c:forEach items="${states}" var="state">
                       <option id="salvat"  value="${state.getState()}">${state}</option>
                 </c:forEach>
               </select>
    </div>

    <div  class="form-group">
       <label  for="observatii">Observatii: </label>
       <br>
       <small>(Acest camp este optional)</small>
       <input type="text" class="form-control" id="observatii" name="observatii" size= "10"value="${peopleAdapter.getObservatii()}">
    </div>

     <div  class="form-group">
           <label  for="date">Data nasterii :</label>
         <input type="date"  class="form-control" id="date" name="date" size= "10" value="${peopleAdapter.getBirthDate()}">
      </div>

    <div  class="form-group">
                   <label for="settlement">Selecteaza localitatea: </label>
                <select name="settlement" id="settlement" class="form-control">
                    <option selected>${peopleAdapter.getLocalitate()}</option>
                    <c:forEach items="${settlements}" var="settlement">
                       <option id="settlement"  value="${settlement.getName()}">${settlement.getName()}</option>
                    </c:forEach>
                </select>
    </div>
  <%--  de adaugat editMap --%>
<%--
    <div  class="form-group">
      <label for="mijlocDeAcces" id="mDaLabel" style="display:" >Mijloc de acces</label>
      <input type="text" id="mijlocDeAcces" name="mijloc de acces" style="display:" value="${peopleAdapter.getMijlocDeAcces()}"
      class="form-control"  size= "10" >
    </div>
    --%>

    <c:choose>
                     <c:when test="${peopleAdapter.getMijlocDeAcces()!=null}">
                           <div  class="form-group">
                             <label for="mijlocDeAcces" id="mDaLabel" style="display:" >Mijloc de acces</label>
                             <input type="text" id="mijlocDeAcces" name="mijloc de acces" style="display:" value="${peopleAdapter.getMijlocDeAcces()}"
                             class="form-control"  size= "10" >
                           </div>
                     </c:when>
                     <c:when test ="${peopleAdapter.getMijlocDeAcces()==null}">
                           <input type="hidden" id="mijlocDeAcces" name="mijloc de acces"  value="">
                     </c:when>
    </c:choose>


    <c:choose>
              <c:when test="${peopleAdapter.getTipHandicap()!=null}">
                     <div  class="form-group">
                       <label for="handicapText" id="hLabel" style="display:" >Tipul handicapului</label>
                       <input type="text" id="handicapText" name="tip handicap" style="display:" value="${peopleAdapter.getTipHandicap()}"
                       class="form-control"  size= "10">
                   </div>
              </c:when>
              <c:when test ="${peopleAdapter.getTipHandicap()==null}">
                      <input type="hidden" id="handicapText" name="tip handicap" value="">
              </c:when>
    </c:choose>

    <c:choose>
                 <c:when test="${peopleAdapter.getMijlocDeAcces()!=null}">
                   <input type="hidden"  id="hiddenEdit1" value="false"  name="isIzolat">
                 </c:when>
                 <c:when test ="${peopleAdapter.getMijlocDeAcces()==null}">
                         <div  class="form-group">
                           <label for="izolatEdit"> Adauga ${peopleAdapter.getName()} in lista de izolati</label>
                           <input type="checkbox" id="izolatEdit" onclick="myFunctionEdit()" >
                           <input type="hidden"  id="hiddenEdit1" value="false"  name="isIzolat">
                         </div>
                 </c:when>
    </c:choose>

    <div  class="form-group">
      <label for="mijlocDeAcces" id="mDaLabelEdit" style="display:none" >Mijloc de acces</label>
      <input type="text" id="mijlocDeAccesEdit" name="mijloc de accesEdit" style="display:none" value=""
      class="form-control"  size= "10" >
    </div>

    <c:choose>
             <c:when test="${peopleAdapter.getTipHandicap()!=null}">
               <input type="hidden"  id="hiddenEdit2" value="false"  name="isHandicap">
             </c:when>
             <c:when test ="${peopleAdapter.getTipHandicap()==null}">
                  <div  class="form-group">
                    <label for="handicap" id="handicapLabelEdit">Adauga ${peopleAdapter.getName()} in lista localnicilor cu handicap</label>
                        <input type="checkbox" id="handicapEdit" onclick="myFunctionEdit2()" >
                        <input type="hidden"  id="hiddenEdit2" value="false"  name="isHandicap">
                    </div>
             </c:when>
    </c:choose>



    <div  class="form-group">
       <label for="handicapText" id="hLabelEdit" style="display:none" >Tipul handicapului</label>
       <input type="text" id="handicapTextEdit" name="tip handicapEdit" style="display:none" value=" "
       class="form-control"  size= "10">
    </div>


    <c:choose>
      <c:when test="${peopleAdapter.getMijlocDeAcces()!=null}">
           <div  class="form-group">
                 <label for="izolatEditRemove">Sterge din izolati</label>
                 <input type="checkbox" id="izolatEditRemove" onclick="myFunctionEditRemove()" >
                 <input type="hidden"  id="hiddenEditRemove" value="false"  name="isNotIzolat">
           </div>
      </c:when>
      <c:when test ="${peopleAdapter.getMijlocDeAcces()==null}">
           <input type="hidden"  id="hiddenEditRemove" value="false"  name="isNotIzolat">
      </c:when>
    </c:choose>

     <c:choose>
                  <c:when test="${peopleAdapter.getTipHandicap()!=null}">
                        <div  class="form-group">
                              <label for="handicapEditRemove" id="handicapLabelEditRemove">Sterge din handicap</label>
                              <input type="checkbox" id="handicapEditRemove" onclick="myFunctionEditRemove2()" >
                              <input type="hidden"  id="hiddenEditRemove2" value="false"  name="isNotHandicap">
                        </div>
                  </c:when>
                  <c:when test ="${peopleAdapter.getTipHandicap()==null}">
                        <input type="hidden"  id="hiddenEditRemove2" value="false"  name="isNotHandicap">
                  </c:when>
     </c:choose>

    <div  class="form-group">
        <input type="submit" value="Modifica" class="btn btn-info" >
        <a href="<c:url value="/homePage2"/> " class="btn btn-info">Back</a>
    </div>

    </form>

     <script>

     function myFunctionEdit() {


             var response=  confirm("Aceasta actiune va adauga localnicul ${peopleAdapter.getName()} in lista de izolati");
                          if (response == true)
                          {
                           document.getElementById("hiddenEdit1").value="true";
                           <%--document.getElementById("imgLabelEdit").style.display = "";
                           document.getElementById("imgEdit").style.display = "";
                           --%>
                           document.getElementById("mDaLabelEdit").style.display = "";
                           document.getElementById("mijlocDeAccesEdit").style.display = "";
                          }
                          else {
                           document.getElementById("hiddenEdit1").value="false";
                           document.getElementById("izolatEdit").checked = false;
                           document.getElementById("mDaLabelEdit").style.display = "none";
                           document.getElementById("mijlocDeAccesEdit").style.display = "none";
                          }
     }

     function myFunctionEdit2(){


             var response=  confirm("Aceasta actiune va adauga localnicul ${peopleAdapter.getName()} in lista localnicilor cu handicap");
                                      if (response == true)
                                      {
                                       document.getElementById("hiddenEdit2").value="true";
                                        document.getElementById("hLabelEdit").style.display = "";
                                        document.getElementById("handicapTextEdit").style.display = "";
                                      }
                                      else {
                                       document.getElementById("hiddenEdit2").value="false";
                                        document.getElementById("handicapEdit").checked = false;
                                        document.getElementById("hLabelEdit").style.display = "none";
                                        document.getElementById("handicapTextEdit").style.display = "none";
                                     }
     }

     function myFunctionEditRemove(){
             var response=  confirm("Aceasta actiune va sterge localnicul ${peopleAdapter.getName()} din lista de izolati");
             if (response == true)
             {
              document.getElementById("hiddenEditRemove").value="true";
             }
             else {
              document.getElementById("hiddenEditRemove").value="false";
               document.getElementById("izolatEditRemove").checked = false;
             }
     }

     function myFunctionEditRemove2(){

             var response=  confirm("Aceasta actiune va sterge localnicul ${peopleAdapter.getName()} din lista localnicilor cu handicap")
                          if (response == true)
                          {
                           document.getElementById("hiddenEditRemove2").value="true";
                          }
                          else {
                            document.getElementById("hiddenEditRemove2").value="false";
                            document.getElementById("handicapEditRemove").checked = false;
                          }
     }

         </script>
</div>
</div>
</body>
</html>