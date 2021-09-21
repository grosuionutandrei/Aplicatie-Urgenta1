<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> HomePage </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="collapse navbar-collapse, container" id="collapsibleNavbar">
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
<div>
<form  role="form" method="post" id = "localitateForm">

<c:choose>
         <c:when test = "${alarm==null}">
            <button class="btn btn-danger"  style="background-color:red"  id="alarm"   value="true"  name="alarm"  >StartAlarm</button>
         </c:when>
         <c:when test = "${alarm.isValue()==false}">
            <button class="btn btn-danger"  style="background-color:red"  id="alarm"   value="true"  name="alarm"  >StartAlarm</button>
         </c:when>
</c:choose>

<c:choose>
  <c:when test = "${alarm.isValue()==true}">
          <button class="btn btn-danger"  id="stopAlarm"  style="background-color:green"   value="false" name="alarm" onclick="myFunction()">StopAlarm</button>
  </c:when>
</c:choose>

<script>
function myFunction() {
  confirm("Sunteti sigur ca doriti sa opriti aceasta alarma? ");
}
</script>


<label for="cities">Selecteaza localitatea</label>
            <select id="cities"  name="cities" onchange="initMap()">
             <option  value = "sl" >Selecteaza localitatea</option>
             <option  value = "Coasta Magurii">${settlements.get(0).name}</option>
             <option  value = "Bals">${settlements.get(1).name}</option>
             <option  value = "Boureni">${settlements.get(2).name}</option>
            </select>
</form>
</div>

  <h3>Linkuri utile</h3>
<div>
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
        <a href="http://www.meteoromania.ro/radarm/radar.index.php"
          target="popup"
          onclick="window.open('http://www.meteoromania.ro/radarm/radar.index.php','popup','width=600,height=600'); return false;">
             Radar meteo
        </a>
        </li>
        <li class="nav-item">
           <a href="http://www.inhga.ro/prognoza_rauri"
                  target="popup"
                  onclick="window.open('http://www.inhga.ro/prognoza_rauri','popup','width=600,height=600'); return false;">
                     Nivelul apelor
                </a>
        </li>
        <li class="nav-item">
          <a href="http://alerta.infp.ro/"
                          target="popup"
                          onclick="window.open('http://alerta.infp.ro/','popup','width=600,height=600'); return false;">
                            Alerta cutremur
                        </a>
        </li>
      </ul>
      </div>

</div>
<div class="col-sm-10">

          <h5></h5>
    <!-- beginning of the map -->
    <div id="map" style="width:100%;height:600px;"></div>
    <script>
    function initMap() {

            var sl = {lat: 45.76688588953352 , lng: 24.963281005330817}
            var cm = {lat: ${settlements.get(0).latitude}, lng: ${settlements.get(0).longitude}};
            var ba = {lat: ${settlements.get(1).latitude}, lng: ${settlements.get(1).longitude}};
            var bo = {lat: ${settlements.get(2).latitude}, lng: ${settlements.get(2).longitude}};
            if (document.getElementById("cities").value === 'sl') {
            var map = new google.maps.Map(document.getElementById('map'),{
                zoom: 6,
                center: sl
            });
             }

            else if (document.getElementById("cities").value === 'Coasta Magurii') {
            var map = new google.maps.Map(document.getElementById('map'),{
                zoom: 15,
                center: cm
            });
            }

            else if (document.getElementById("cities").value === 'Bals') {
            var map = new google.maps.Map(document.getElementById('map'),{
                zoom: 15,
                center: ba
            });
             }

            else if (document.getElementById("cities").value === 'Boureni') {
            var map = new google.maps.Map(document.getElementById('map'),{
                zoom: 15,
                center: bo
            });
            }
        }
    </script>
    <script async defer
     src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC96SFJYLPlDzPsh-IrQ_tF0uzHccVyXNk&callback=initMap"></script>
     </div>
</div>
</div>

</body>
</html>



