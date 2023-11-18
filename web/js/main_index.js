//18122022
/* global Swal */

const pass = document.querySelector("#txtPassword");
const eye_show = document.querySelector("#eye-show");
const eye_hide = document.querySelector("#eye-hide");

let Ifacebook = "", Itwitter = "", Iinstragram = "";
eye_show.onclick = () => {
    if (pass.type === "password") {
        pass.type = "text";
        eye_show.classList.replace("show", "hide");
        eye_hide.classList.replace("hide", "show");
    }
};

eye_hide.onclick = () => {
    if (pass.type === "text") {
        pass.type = "password";
        eye_show.classList.replace("hide", "show");
        eye_hide.classList.replace("show", "hide");
    }
};


let userC, pass2;

const getvalueInput = () => {
    userC = document.querySelector("#txtCorreoUsuario").value;
    pass2 = document.querySelector("#txtPassword").value.toString();

};

var frm_login = $('#frm_login');

frm_login.submit(function (e) {
    e.preventDefault();
    // getvalueInput();

    $.ajax({
        type: frm_login.attr('method'),
        url: "IniciarSesion?accion=Ingresar",
        data: frm_login.serialize(),

        success: function (data) {

            let error = data;
            if (error == 0) {

                const Toast = Swal.mixin({
                    toast: true,
                    position: "bottom-end",
                    showConfirmButton: false,
                    iconColor: '#ff0e1d',
                    timer: 1000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener("mouseenter", Swal.stopTimer);
                        toast.addEventListener("mouseleave", Swal.resumeTimer);
                    },
                });

                Toast.fire({
                    icon: "warning",
                    title: "<h5 style='color:#8b0000; font-size:15px;'>correo o contraseña invalida</h5>",
                });

            } else {
                const Toast = Swal.mixin({
                    toast: true,
                    position: "bottom-end",
                    showConfirmButton: false,
                    iconColor: '#08bb40',
                    timer: 1000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener("mouseenter", Swal.stopTimer);
                        toast.addEventListener("mouseleave", Swal.resumeTimer);
                    },
                });

                Toast.fire({
                    icon: "success",
                    title: "<h5 style='color:#006400; font-size:15px;' >Inicio de sesión con exito</h5>",

                }).then(function () {

                    window.location.replace("Modulos?modulo=dashboard");

                });
            }
        },

        error: function (data) {

        }
    });
});


window.onload = function (e) {
    e.preventDefault();
//    e.stopImmediatePropagation();
    traerConfiguraciones();



    $.ajax({
        url: "Configuraciones?accion=MostrarConfiguracion",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
           console.log(data);

            Ifacebook.href = data.facebook;
            Itwitter.href = data.tw;
            Iinstragram.href = data.ins;
      
        },
        error: function (data) {
            //    console.log("ERROR");
        }
    });

};


//window.addEventListener("load", function (e) {
// 
//  
//});




//
function traerConfiguraciones() {
    Ifacebook = document.querySelector("#Ifacebook");
    Itwitter = document.querySelector("#Itwitter");
    Iinstragram = document.querySelector("#Iinstragram");

}
