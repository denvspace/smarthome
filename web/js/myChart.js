function createOneChart(idchart, label, dataarray, mindate) {
    let ctx = document.getElementById(idchart).getContext('2d');
    ctx.canvas.width = 800;
    ctx.canvas.height = 200;
    let myChart = new Chart(ctx, {
        type: 'line',
        data: {
            datasets: [{
                lineTension: 0,
                label: label,
                pointRadius: 0,
                // backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: dataarray
            }]
        },
        options: {
            animation: {
                duration: 700,
                animateScale: true
            },
            scales: {
                xAxes: [{
                    type: 'time',
                    time: {
                        unit: 'hour'
                    },
                    ticks: {
                        min: mindate, //,
                        max: moment().add(5, 'minutes')
                    }
                }],
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            legend: {
                display: true,
                position: 'bottom',
                labels: {
                    fontColor: 'rgb(255, 99, 132)'
                }
            }
        }
    });
    return myChart;
}

function createTwoCharts(idchart, label1, label2, dataarray1, dataarray2, mindate) {
    let ctx = document.getElementById(idchart).getContext('2d');
    ctx.canvas.width = 800;
    ctx.canvas.height = 200;
    let myChart = new Chart(ctx, {
        type: 'line',
        data: {
            datasets: [{
                lineTension: 0,
                label: label1,
                // backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: dataarray1
            }, {
                lineTension: 0,
                label: label2,
                // backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(0, 99, 132)',
                data: dataarray2
            }]
        },
        options: {
            animation: {
                duration: 700,
                animateScale: true
            },
            scales: {
                xAxes: [{
                    type: 'time',
                    time: {
                        unit: 'hour'
                    },
                    ticks: {
                        min: mindate,
                        max: moment().add(5, 'minutes')
                    }
                }],
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            legend: {
                display: true,
                position: 'bottom',
                labels: {
                    fontColor: 'rgb(255, 99, 132)'
                }
            }
        }
    });
    return myChart;
}

let data1 = [{x: moment().subtract(2.5, 'hours'), y: 12.5},
    {x: moment().subtract(2, 'hours'), y: 1},
    {x: moment().subtract(1.5, 'hours'), y: 10},
    {x: moment().subtract(1, 'hours'), y: 4},
    {x: moment().subtract(0.5, 'hours'), y: 7},
    {x: moment(), y: 6}];
let data2 = [{x: moment().subtract(2.5, 'hours'), y: 2},
    {x: moment().subtract(2, 'hours'), y: 5},
    {x: moment().subtract(1.5, 'hours'), y: 13},
    {x: moment().subtract(1, 'hours'), y: 5},
    {x: moment().subtract(0.5, 'hours'), y: 3},
    {x: moment(), y: 9}];

let littleRoomChart = null;
let tempLittleRoomMark = 'Температура';
let tempLittleRoomData = [];
function createLittleRoomChart(url = '') {
    return fetch(url)
        .then(response => response.json())
        .then(function (array) {
            for (let key in array) {
                tempLittleRoomData.push({'x': moment(array[key].date,"MMM DD, YYYY hh:mm:ss A")/*.add(3,'hours')*/, 'y': array[key].value});
            }
            littleRoomChart = createOneChart('tempRoom1', tempLittleRoomMark, tempLittleRoomData, tempLittleRoomData[0].date);
            addLittleRoomChart('/getSensorValuesServlet?sensorID=1&typeValue=2');
        }) // JSON-строка полученная после вызова `response.json()`
        .catch(error => console.error(error));
}
let humLittleRoomMark = 'Влажность';
let humLittleRoomData = [];
function addLittleRoomChart(url = '') {
    return fetch(url)
        .then(response => response.json())
        .then(function (array) {
            for (let key in array) {
                humLittleRoomData.push({'x': moment(array[key].date,"MMM DD, YYYY hh:mm:ss A")/*.add(3,'hours')*/, 'y': array[key].value});
            }
            let newDataset = {
                lineTension: 0,
                label: humLittleRoomMark,
                pointRadius: 0,
                // backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(0, 99, 132)',
                data: humLittleRoomData
            }
            littleRoomChart.data.datasets.push(newDataset);
            littleRoomChart.update();
        }) // JSON-строка полученная после вызова `response.json()`
        .catch(error => console.error(error));
}

function addData(chart, data) {
    console.log(chart.data.datasets);
    chart.data.datasets.forEach((dataset) => {
        dataset.data.push(data);
        console.log(dataset.data);
        console.log(data);
    });
    updateScale(chart);
}

function removeData(chart) {
    chart.data.labels.pop();
    chart.data.datasets.forEach((dataset) => {
        dataset.data.pop();
    });
    chart.update();
}

function updateConfigByMutating(chart, label) {
    chart.data.datasets[0].label = label;
    chart.update();
}

function updateScale(chart) {
    chart.options.scales.xAxes[0] = {
        type: 'time',
        time: {
            unit: 'hour'
        },
        ticks: {
            min: moment().subtract(3, 'hours'),
            max: moment().add(10, 'minutes'),
        }
    };
    chart.update();
}

function UpdateStatus() {
    let newLabel = document.getElementById("label").value;
    updateConfigByMutating(littleRoomChart, newLabel);
}

function getMoment() {
    return moment().add(5, 'minutes');
}

function updateChart(chart) {
    updateScale(chart);
}