/* global Mail001, idcl */

//12182022

function printHTML() {
    if (window.print) {
        window.print();
    }
}

let body = document.querySelector("body");

window.addEventListener("load", function (e) {
    e.preventDefault();
    const id = idcl;

    body.style.background = "transparent";
    TraerReporte(id);


});

function TraerReporte(idCliente) {
    $.ajax({
        url: "Reporte?accion=Reporte",
        type: "GET",
        data: "id_cliente=" + idCliente,
        dataType: "JSON",
        success: function (data) {

            LlenarEncabezado(data);
            document.querySelector('title').textContent = data.nombres;
        }
    });
}


function LlenarReporte(idCliente) {
    $.ajax({
        url: "Reporte?accion=Reporte",
        type: "GET",
        data: "id_cliente=" + idCliente,
        dataType: "JSON",
        success: function (data) {
            LlenarEncabezado(data);
            document.querySelector('title').textContent = data.nombres;
        }
    });
}

function LlenarEncabezado(data) {
    let nombres = document.querySelector("#r_Nombre");
    let correo = document.querySelector("#txtCorreo");
    let telefono = document.querySelector("#r_telefono");
    let edad = document.querySelector("#r_edad");
    let estatura = document.querySelector("#r_estatura");
    let nombresE = document.querySelector("#r_nombresE");
    let telefonoE = document.querySelector("#r_telefonoE");
    let fechaI = document.querySelector("#fechaI");
    let txtobservaciones = document.querySelector("#txtobservaciones");
    let fechaF = document.querySelector("#fecha");
    let genero, idreg;
    let date = new Date();
    let mes = date.getMonth() + 1;
    //   let fecha = date.getDate() + "-" + mes + "-" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();

    nombres.innerHTML = data.nombres + " " + data.apellidos;
    correo.innerHTML = data.correo;
    correo.href = "mailto:" + data.correo;
    telefono.innerHTML = data.Notelefonoc;
    edad.innerHTML = data.edad;
    estatura.innerHTML = data.estatura;
    nombresE.innerHTML = data.nombreE;
    telefonoE.innerHTML = data.telefonoE;
    fechaI.innerHTML = "Fecha inicio: " + data.fechaI;

    genero = data.genero;
    idreg = data.idreg;
    let simbolo;
    if (data.observaciones === null) {
        txtobservaciones.innerHTML = "No aplica";
    } else {
        txtobservaciones.innerHTML = data.observaciones;
    }
    if (genero === 1) {
        simbolo = "=";
        PreguntasC(idreg, simbolo);
    } else {
        simbolo = ">=";
        PreguntasC(idreg, simbolo);
    }
    CondicionesE(idreg);
    controlPeso(idreg);
    ControlMedidas(idreg);
}

function PreguntasC(id, simbolo) {
    $.ajax({
        url: "Reporte?accion=PreguntasCliente",
        type: "GET",
        data: "id_registro=" + id + "&simbolo=" + simbolo,
        dataType: "JSON",
        success: function (data) {
            crearPreguntas(data);
        }

    });
}

const PreguntasCC = document.querySelector("#PreguntasCC");
function crearPreguntas(data) {
    let contador = 0;
    data.forEach(datos => {
        contador++;
        let respuesta;
        if (datos[1] == 0) {
            respuesta = "No";
        } else {
            respuesta = "Si";
        }
        const $div = document.createElement("div");
        $div.className = "cPregunta";
        let $span1 = document.createElement("span");
        $span1.textContent = contador + ") " + datos[0];
        $div.appendChild($span1);
        let $span2 = document.createElement("span");
        $span2.textContent = respuesta;
        $div.appendChild($span2);
        PreguntasCC.appendChild($div);
    });
}

function CondicionesE(id) {
    $.ajax({
        url: "Reporte?accion=CondicionesE",
        type: "GET",
        data: "id_registro=" + id,
        dataType: "JSON",
        success: function (data) {
            verCondicionesE(data);
        }

    });
}

function verCondicionesE(data) {
    let cond1 = document.querySelector("#cond1");
    let cond2 = document.querySelector("#cond2");
    let cond3 = document.querySelector("#cond3");
    let cond4 = document.querySelector("#cond4");
    let salud = puntuacion(data.salud);
    cond1.innerHTML = salud;
    let Cfisica = puntuacion(data.cfisica);
    cond2.innerHTML = Cfisica;
    let nivelN = puntuacion(data.nivelN);
    cond3.innerHTML = nivelN;
    let nivelE = puntuacion(data.nivelE);
    cond4.innerHTML = nivelE;
}

const puntuacion = (datos) => {
    let estrella;
    switch (datos) {
        case 1:
            estrella = "Muy mala";
            break;
        case 2:
            estrella = "Mala";
            break;
        case 3:
            estrella = "Regular";
            break;
        case 4:
            estrella = "Buena";
            break;
        case 5:
            estrella = "Excelente";
            break;
    }
    return estrella;
};
function controlPeso(id) {
    $.ajax({
        url: "Reporte?accion=ControlPesos",
        type: "GET",
        data: "id_reg=" + id,
        dataType: "JSON",
        success: function (data) {
            TableControlPeso(data);
        }

    });
}
const containerCPeso = document.querySelector("#containerCPeso");
function TableControlPeso(data) {
    let contador = 0;
    data.forEach(datos => {
        contador++;
        const $tr = document.createElement("tr");
        let $td0 = document.createElement("td");
        $td0.textContent = contador;
        $tr.appendChild($td0);
        let $td2 = document.createElement("td");
        $td2.textContent = datos[0];
        $tr.appendChild($td2);
        let $td3 = document.createElement("td");
        $td3.textContent = datos[1];
        $tr.appendChild($td3);
        let $td4 = document.createElement("td");
        $td4.textContent = datos[2];
        $tr.appendChild($td4);
        let $td5 = document.createElement("td");
        $td5.textContent = datos[3];
        $tr.appendChild($td5);
        let $td6 = document.createElement("td");
        $td6.textContent = datos[4];
        $tr.appendChild($td6);
        let $td7 = document.createElement("td");
        $td7.textContent = datos[5];
        $tr.appendChild($td7);
        let $td8 = document.createElement("td");
        $td8.textContent = datos[6];
        $tr.appendChild($td8);
        let $td9 = document.createElement("td");
        $td9.textContent = datos[7];
        $tr.appendChild($td9);
        let $td10 = document.createElement("td");
        $td10.textContent = datos[8];
        $tr.appendChild($td10);
        let $td11 = document.createElement("td");
        $td11.textContent = datos[9];
        $tr.appendChild($td11);
        containerCPeso.appendChild($tr);
    });
}


function  ControlMedidas(id) {
    $.ajax({
        url: "Reporte?accion=ControlMedidas",
        type: "GET",
        data: "id_reg=" + id,
        dataType: "JSON",
        success: function (data) {
            TableControlmedidas(data);
        }

    });
}

const containerMedidas = document.querySelector("#containerMedidas");
function TableControlmedidas(data) {
    let contador = 0;
    data.forEach(datos => {
        contador++;
        const $tr = document.createElement("tr");
        let $td0 = document.createElement("td");
        $td0.textContent = contador;
        $tr.appendChild($td0);
        let $td2 = document.createElement("td");
        $td2.textContent = datos[0];
        $tr.appendChild($td2);
        let $td3 = document.createElement("td");
        $td3.textContent = datos[1];
        $tr.appendChild($td3);
        let $td4 = document.createElement("td");
        $td4.textContent = datos[2];
        $tr.appendChild($td4);
        let $td5 = document.createElement("td");
        $td5.textContent = datos[3];
        $tr.appendChild($td5);
        let $td6 = document.createElement("td");
        $td6.textContent = datos[4];
        $tr.appendChild($td6);
        let $td7 = document.createElement("td");
        $td7.textContent = datos[5];
        $tr.appendChild($td7);
        let $td8 = document.createElement("td");
        $td8.textContent = datos[6];
        $tr.appendChild($td8);
        let $td9 = document.createElement("td");
        $td9.textContent = datos[7];
        $tr.appendChild($td9);
        let $td10 = document.createElement("td");
        $td10.textContent = datos[8];
        $tr.appendChild($td10);
        containerMedidas.appendChild($tr);
    });
}

