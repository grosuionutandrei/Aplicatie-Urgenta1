<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title> Statistici </title>
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
                         <a href ="/contactePrimarie" class="btn-link">Contacte</a>
       </div>

       <div class="col">
          <a href ="/alimentePrimarie" class="btn-link">Alimente</a>
       </div>
       <div class="col">
                         <a href ="/logout" class="btn btn-primary">Logout</a>
       </div>
  </div>
</nav>


 <div class="row" >
    <div class="wraper"  style= "border-style: ridge" >
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
<div class="wraper" style= "border-style: ridge" >
<br>
<br>
<br>
<br>
 <div id="barChart" class="wraper"></div>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
           google.charts.load('current', {packages: ['corechart', 'bar']});
           google.charts.setOnLoadCallback(drawMaterial);

           function drawMaterial() {
                  var data = google.visualization.arrayToDataTable([
                  ['Alimente', 'Cantitate', { role: 'style' }],
                  <c:forEach items="${alimentes}" var="entry">
                   [ '${entry.getTipAliment()}', ${entry.getQuantity()},'green'],
                  </c:forEach>
                 ]);


                 var options = {
                   title: 'Stoc alimente',
                   hAxis: {
                     title: 'Time of Day',

                   },
                   vAxis: {
                     title: 'Rating (scale of 1-10)'
                   }
                 };

                 var materialChart = new google.charts.Bar(document.getElementById('barChart'));
                 materialChart.draw(data, options);
               }
</script>
</div>


</div>
</body>
</html>