<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Statistici </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
 <div class="row" >
    <div class="col-sm-6"  style= "border-style: ridge" >
        <div id="piechart"></div>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script type="text/javascript">

           google.charts.load('current', {'packages':['corechart']});
           google.charts.setOnLoadCallback(drawChart);


    function drawChart() {
       var data = google.visualization.arrayToDataTable([
                  ['Stare', '%'],
                  <c:forEach items="${chartValues}" var="entry">
                     [ '${entry.key}', ${entry.value} ],
                  </c:forEach>
    ]);


       var options = {'title':'Procentaj situatie alarma', 'width':550, 'height':400};
       var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                   chart.draw(data, options);
}
       </script>
</div>
<div class="col-sm-6" style= "border-style: ridge" >
<div id="piechart1"></div>
       <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
       <script type="text/javascript">
              google.charts.load('current', {'packages':['corechart']});
              google.charts.setOnLoadCallback(drawChart);
       function drawChart() {
              var data = google.visualization.arrayToDataTable([
                         ['Varsta', '%'],
              <c:forEach items="${ageChart}" var="entry">
                         [ '${entry.key}', ${entry.value} ],
              </c:forEach>
       ]);
              var options = {'title':'Procentaj varsta locuitori', 'width':550, 'height':400};
              var chart = new google.visualization.PieChart(document.getElementById('piechart1'));
                          chart.draw(data, options);
}
       </script>

</div>

</div>