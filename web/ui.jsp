<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 24.12.2019
  Time: 4:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <title>SmartNet</title>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
    <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.js"></script>
    <script src="js/moment.js"></script>
    <a href="index.jsp">Main</a>
    <a href="insertdata.jsp">InsertData</a>
    <br>
    <div style="width:800px; height:200px;">
        <div class="roomName">Маленькая комната</div>
        <canvas id="tempRoom1"></canvas>
    </div>
    <br>
    <div style="width:800px; height:200px;">
        <canvas id="tempRoom2"></canvas>
    </div>
    <br>
    <input id="label" type="text" placeholder="Введите label">
    <input type='button' onclick='UpdateStatus()' value='Status Update'>
    <input type='button' onclick='removeData(b)' value='Clear Data'>
    <input type='button' onclick='addData(b,{x: getMoment(), y: 20})' value='Add Data'>
    <input type='button' onclick='updateChart(b)' value='Update'>
    <br>
    <div class="switch">
        <label>
            Свет в комнате
            <br>
            Off
            <input type="checkbox">
            <span class="lever"></span>
            On
        </label>
    </div>
    <div class="switch">
        <label>
            Подсветка кровати
            <br>
            Off
            <input type="checkbox">
            <span class="lever"></span>
            On
        </label>
    </div>
    <div class="switch">
        <label>
            Свет в квартире
            <br>
            Off
            <input type="checkbox">
            <span class="lever"></span>
            On
        </label>
    </div>
    <script src="js/myChart.js"></script>
    <script>
        createLittleRoomChart('/getSensorValuesServlet?sensorID=1&typeValue=1');

        let lableTempChart1 = 'Температура большой комнаты';
        let lableHumChart1 = 'Влажность большой комнаты';
        let mindate = moment().subtract(3, 'hours');
        let z = createTwoCharts('tempRoom2', lableTempChart1, lableHumChart1, data1, data2, mindate);
    </script>
</body>
</html>
