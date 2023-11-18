//18122022

/* global Swal */

window.addEventListener("load", function (e) {
    e.preventDefault();
    traerConfiguraciones();
    e.stopPropagation();
    return false;
});

let Ifacebook = document.querySelector("#Ifacebook");
let Itwitter = document.querySelector("#Itwitter");
let Iinstragram = document.querySelector("#Iinstragram");
let txtFraseI = document.querySelector("#txtFraseI");
let txtAutorI = document.querySelector("#txtAutorI");


function traerConfiguraciones() {
    $.ajax({
        url: "Configuraciones?accion=MostrarConfiguracion",
        type: "GET",
        dataType: "JSON",
        cache: false,
        async: false,
        success: function (data) {
            Ifacebook.href = data.facebook;
            Itwitter.href = data.tw;
            Iinstragram.href = data.ins;
            txtFraseI.innerHTML = data.frase;
            txtAutorI.innerHTML = data.autor;

        }
    });
}