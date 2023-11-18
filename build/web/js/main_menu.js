//18122022

/* global miVar, Swal */

const body = document.querySelector("body");
const modeToggle = body.querySelector(".mode-toggle");
const sidebar = body.querySelector("nav");
const myframe = document.querySelector("#myFrame");
let txtUsuario = document.querySelector("#txtUsuario");
let id123 = document.querySelector("#fr12345");

const sidebar_toggle = body.querySelector(".sidebar-toggle");

let getMode = localStorage.getItem("mode");
if (getMode && getMode === "dark") {
    body.classList.toggle("dark");
}
let getStatus = localStorage.getItem("status");
if (getStatus && getStatus === "close") {
    sidebar.classList.toggle("close");
   
}

modeToggle.addEventListener("click", () => {
    body.classList.toggle("dark");
    if (body.classList.contains("dark")) {
        localStorage.setItem("mode", "dark");
    } else {
        localStorage.setItem("mode", "light");
    }
});

sidebar_toggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
    if (sidebar.classList.contains("close")) {
        localStorage.setItem("status", "close");
    } else {
        localStorage.setItem("status", "open");
    }
});

const profile = document.querySelector(".profile");
const imgProfile = profile.querySelector("img");
const dropdownProfile = profile.querySelector(".profile-link");

imgProfile.addEventListener("click", function () {
    dropdownProfile.classList.toggle("show");
});

window.onclick = function (event) {
    if (event.target != imgProfile) {
        dropdownProfile.classList.remove("show");
    }
};
let frm_usuario = "Usuario";
//VARIABLE CONTENEDORA DE LA IMAGEN
let txtImagenUsuario = document.querySelector("#txtImagenUsuario");
//VARIABLE PERFIL USUARIO
let imagenUsuario = document.querySelector("#txtImagenPerfil");
let inputNameImage = document.querySelector("#txtNameimg");
let dataImage;

document.addEventListener("DOMContentLoaded", (e) => {
  e.preventDefault();
    TraerDatouser(miVar);
     myframe.src = "Modulos?modulo=inicio"; 
});
 

function TraerDatouser(idUser) {

    $.ajax({
        url: frm_usuario + "?accion=obtenerUsuario&id_user=" + idUser,
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            txtUsuario.innerHTML = "Hola de nuevo, " + data.nombres + " " + data.apellidos + " 🖤";
            dataImage = data.imagen;
            imagenUsuario.src = "imgUser/" + data.imagen;
            inputNameImage.value = data.imagen;
        }

    });
}


// PARA FOTO Form
const btnAceptarImage = document.querySelector("#btnAceptarimg");
const btnCancelarImage = document.querySelector("#btnCancelarimg");

var archivoInput = document.querySelector("#txtFoto");
const btnCambiarFoto = document.querySelector("#btnCambiarFoto");
const modalCambiarFoto = document.querySelector("#modalCambiarFoto");

btnAceptarImage.toggleAttribute("disabled", true);

//VERIFICAR PARA IPAD LA IMAGEN DE BRUCEE LEE
let previsualizar = function (e) {
    var archivoRuta = archivoInput.value.toLowerCase();
    var extPermitidas = /(.png|.jpg)$/i;

    if (!extPermitidas.exec(archivoRuta)) {
        archivoInput.value = "";
        txtImagenUsuario.src = "";
        return false;
    } else {
        // VISTA DEL LA IMAGEN
        if (archivoInput.files && archivoInput.files[0]) {
            var visor = new FileReader();
            let image = archivoInput.files[0];
            if (image.name.length > 45) {

                btnAceptarImage.toggleAttribute("disabled", true);
            }
            visor.onload = function (e) {
                txtImagenUsuario.src = e.target.result;
                btnAceptarImage.toggleAttribute("disabled", false);

     
            };
            visor.readAsDataURL(archivoInput.files[0]);
        }
    }
};

btnCancelarImage.addEventListener("click", () => {
    archivoInput.value = "";
    txtImagenUsuario.src = "";
    modalCambiarFoto.close();
});

btnCambiarFoto.addEventListener("click", () => {
    txtImagenUsuario.src = "imgUser/" + dataImage;
    modalCambiarFoto.showModal();

});
//
var frm_CambiarFotoPerfil = $('#formCambiarFotoPerfil');
frm_CambiarFotoPerfil.submit(function (e) {
    e.preventDefault();
    $.ajax({
        type: frm_CambiarFotoPerfil.attr('method'),
        url: frm_CambiarFotoPerfil.attr('action') + "?accion=CambiarFotoPerfil",
        data: new FormData(this),
        processData: false,
        contentType: false,
        success: function (data) {
            let error = data;
            if (error == 0 || error === null) {

                const Toast = Swal.mixin({
                    toast: true,
                    position: "bottom-end",
                    showConfirmButton: false,
                    iconColor: '#ff0e1d',
                    timer: 1500,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener("mouseenter", Swal.stopTimer);
                        toast.addEventListener("mouseleave", Swal.resumeTimer);
                    },
                });

                Toast.fire({
                    icon: "warning",
                    title: "<h5 style='color:#8b0000; font-size:15px;'>Error esta imagen ya lo tiene en uso</h5>",
                });

            } else {
                const Toast = Swal.mixin({
                    toast: true,
                    position: "bottom-end",
                    showConfirmButton: false,
                    iconColor: '#08bb40',
                    timer: 1500,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener("mouseenter", Swal.stopTimer);
                        toast.addEventListener("mouseleave", Swal.resumeTimer);
                    },
                });

                Toast.fire({
                    icon: "success",
                    title: "<h5 style='color:#006400; font-size:15px;' >Foto procesada correctamente</h5>",

                }).then(function () {

                    window.location.replace("Modulos?modulo=dashboard");

                });
            }

        },
        error: function (data) {
        
        },
    });
});


//form cambiar contraseña

//PRIMER INPUT
const eye1 = document.querySelector("#eye-hide1");
const show1 = document.querySelector("#eye-show1");
const paswd_actual = document.querySelector("#paswd_actual");
// SEGUNDO INPUT
const eye2 = document.querySelector("#eye-hide2");
const show2 = document.querySelector("#eye-show2");
const passwd1 = document.querySelector("#passwd1");
const textError1 = document.querySelector("#textError1");
// TERCER INPUT
const eye3 = document.querySelector("#eye-hide3");
const show3 = document.querySelector("#eye-show3");
const passwd2 = document.querySelector("#passwd2");
const textError2 = document.querySelector("#textError2");
const btnAceptar = document.querySelector("#btnAceptar");
const btnCancelar = document.querySelector("#btnCancelar");
const modalCambiarPass = document.querySelector("#modalCambiarPasswd");
const AbrirModalPass = document.querySelector("#btnCambiarPass");
btnAceptar.setAttribute("disabled", true);

show1.addEventListener("click", () => hidePass(eye1, paswd_actual, show1));
eye1.addEventListener("click", () => hidePass(eye1, paswd_actual, show1));

show2.addEventListener("click", () => hidePass(eye2, passwd1, show2));
eye2.addEventListener("click", () => hidePass(eye2, passwd1, show2));

show3.addEventListener("click", () => hidePass(eye3, passwd2, show3));
eye3.addEventListener("click", () => hidePass(eye3, passwd2, show3));

const hidePass = (eye_show, inputField, eye_hide) => {
    if (inputField.type === "password") {
        inputField.type = "text";
        eye_show.classList.replace("show", "hide");
        eye_hide.classList.replace("hide", "show");
    }
    eye_hide.onclick = () => {
        if (inputField.type === "text") {
            inputField.type = "password";
            eye_show.classList.replace("hide", "show");
            eye_hide.classList.replace("show", "hide");
        }
    };
};
/////////

let TestingPassword = {
    passwd1: true,
    passwd2: true,
};

const validatefieldPass = (e, textError) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp(
            "^(?!.*(.)\1{3})(?![@#$d]*$)(?!^[a-zA-Z]*$)^([a-zA-Z@#$0-9]{8,12})$"
            );

    if (fieldValue.trim().length === 0) {
        TestingPassword[field_id] = true;
        textError.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        TestingPassword[field_id] = true;
        textError.innerHTML =
                "*La contraseña debe contener minimo 8 caracteres, incluye caracter especial, y números.";
    } else {
        TestingPassword[field_id] = false;
        textError.innerText = "";
    }
    submitController();
};

const ConfirmarPass = (e, textError, PassAnterior) => {
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const passAnterior = PassAnterior.value;

    if (fieldValue.trim().length === 0) {
        TestingPassword[field_id] = true;
        textError.innerHTML = "*Por favor llenar el campo";
    } else if (fieldValue != passAnterior) {
        TestingPassword[field_id] = true;
        textError.innerHTML = "*No conindicen las contraseñas";
    } else {
        TestingPassword[field_id] = false;
        textError.innerText = "";
    }
    submitController();
};

submitController = () => {
    if (TestingPassword.passwd1 || TestingPassword.passwd2) {
        btnAceptar.toggleAttribute("disabled", true);
        
    } else {
        btnAceptar.toggleAttribute("disabled", false);

    }
};

passwd1.addEventListener("blur", (e) => validatefieldPass(e, textError1));
passwd1.addEventListener("input", (e) => validatefieldPass(e, textError1));
passwd2.addEventListener("blur", (e) => ConfirmarPass(e, textError2, passwd1));
passwd2.addEventListener("input", (e) => ConfirmarPass(e, textError2, passwd1));

AbrirModalPass.addEventListener("click", () => {
    modalCambiarPass.showModal();
});

btnCancelar.addEventListener("click", (e) => {
    e.preventDefault();
    modalCambiarPass.close();
    limpiarInputPass();
});

function limpiarInputPass() {
    paswd_actual.value = "";
    passwd1.value = "";
    passwd2.value = "";
    textError1.innerHTML = "";
    textError2.innerHTML = "";
}

var frmModPass = $('#frmModificarPass');

frmModPass.submit(function (e) {
    e.preventDefault();
    $.ajax({
        type: frmModPass.attr('method'),
        url: frmModPass.attr('action') + "?accion=ModificarPass",
        data: frmModPass.serialize(),
        success: function (data) {
    
            let error = data;
            if (error == 0 || error === null) {
                //    modalDatosGenerales.close();
                const Toast = Swal.mixin({
                    toast: true,
                    position: "bottom-end",
                    showConfirmButton: false,
                    iconColor: '#ff0e1d',
                    timer: 2000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener("mouseenter", Swal.stopTimer);
                        toast.addEventListener("mouseleave", Swal.resumeTimer);
                    },
                });

                Toast.fire({
                    icon: "warning",
                    title: "<h5 style='color:#8b0000; font-size:15px;'>Vaya, ha ocurrido un error</h5>",
                });

            } else {
                const Toast = Swal.mixin({
                    toast: true,
                    position: "bottom-end",
                    showConfirmButton: false,
                    iconColor: '#08bb40',
                    timer: 2000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener("mouseenter", Swal.stopTimer);
                        toast.addEventListener("mouseleave", Swal.resumeTimer);
                    },
                });

                Toast.fire({
                    icon: "success",
                    title: "<h5 style='color:#006400; font-size:15px;' >Nueva contraseña agregada</h5>",

                }).then(function () {
              
                    window.location.replace("Modulos?modulo=dashboard");

                });
            }
        },
        error: function (xml, data) {
          
        },
    });

});


