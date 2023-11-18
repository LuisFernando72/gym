//19122022

window.addEventListener("load", function () {

    ContarPersona();
    Entrenadores();
    RegistrosPorDia();
});

// APEXCHART

let NumHombres = document.querySelector("#txtHombres");
let NumMujeres = document.querySelector("#txtMujeres");
let totalTodo = document.querySelector("#txtTotalTodo");
let arr = [];
let dataEntrenador = [];

function ContarPersona() {
    $.ajax({
        url: "Estadistica?accion=CantidadPersonas",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            arr = data.arra;
            NumHombres.innerHTML = arr[0];
            NumMujeres.innerHTML = arr[1];
            totalTodo.innerHTML = arr[2];

        },
        error: function (data) {
        }
    });
}

function Entrenadores() {

    $.ajax({
        url: "Estadistica?accion=TablaEntrenadores",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            dataEntrenador = data;
            CrearCuerpo();
        },
        error: function (data) {

        }
    });
}
const cuerpoTabla = document.querySelector("#cuerpoTabla");

function CrearCuerpo() {

    dataEntrenador.forEach(datos => {

        const $tr = document.createElement("tr");
        let $ImgUsuario = document.createElement("td");
        let $image = document.createElement("img");
        let $direccion = datos[2];
        $image.src = "imgUser/" + $direccion;
        $ImgUsuario.appendChild($image);
        $tr.appendChild($ImgUsuario);


        let $tdNombres = document.createElement("td");
        $tdNombres.textContent = datos[0] + " " + datos[1]; // el textContent del td es el nombre
        $tr.appendChild($tdNombres);

        let $star = document.createElement("span");
        let $starf = document.createElement("td");
        $star.className = "status completed";
        $star.textContent = "⭐⭐⭐⭐⭐";
        $starf.appendChild($star);
        $tr.appendChild($starf);

        cuerpoTabla.appendChild($tr);

    });
}
let dataRegistroChart1 = [];
let dataRegistroChart2 = [];

function RegistrosPorDia() {

    $.ajax({
        url: "Estadistica?accion=IntervaloSemanaA",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            dataRegistroChart1 = data.A1;
            dataRegistroChart2 = data.B1;
            DiagramaChart(dataRegistroChart1, dataRegistroChart2);

        },
        error: function (data) {
            // console.log("ERROR");
        }
    });
}


//var options;
function DiagramaChart(data1, data2) {

    let arreglo1 = Object.values(data1);
    let arreglo2 = Object.values(data2);
    let uno = [];
    let dos = [];
    arreglo1.forEach(arr1 => {
        uno.push(arr1);
    });
    arreglo2.forEach(arr2 => {
        dos.push(arr2);
    });


    options = {
        series: [
            {
                name: "Mes Actual",
                data: uno,
                color: "#f80686"
            }, {
                name: "Mes Pasado",
                data: dos,
                color: "#09faaa"
            }
        ],

        chart: {
            height: 330,
            type: "area",
        },
        xaxis: {
            categories: ["15-25", "26-35", "36-45", "46-55", "56-65", "+66"]
        },
        tooltip: {
            x: {
                format: 'dd/MM/yy HH:mm'
            },
        }
    };


    var chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
}

