//18122022
/* global moment, Swal */

const icon_error = document.querySelectorAll("#icoError");
const txtnombresCliente = document.querySelector("#txtnombresCliente");
const error_nombres = document.querySelector("#txtnombresCliente-error");
const txtxapellidoCliente = document.querySelector("#txtxapellidoCliente");
const error_apellidos = document.querySelector("#txtxapellidosCliente-error");
const txtcorreoCliente = document.querySelector("#txtcorreoCliente");
const error_correoCliente = document.querySelector("#txtcorreoCliente-error");
const txtfechaNacimiento = document.querySelector("#txtfechaNacimiento");
const error_Nacimiento = document.querySelector("#txtfechaNacimiento-error");
const txtxCUIC = document.querySelector("#txtxCUIC");
const error_cui = document.querySelector("#txtxcuic-error");
const txtEstatura = document.querySelector("#txtEstatura");
const error_estatura = document.querySelector("#txtEstatura-error");
const txtTelefono = document.querySelector("#txtTelefono");
const error_telefono = document.querySelector("#txtTelefono-error");
const txtsexo = document.querySelector("#txtsexo");
const error_sexo = document.querySelector("#sexo-error");
const edad = document.querySelector("#txtEdad");
const error_edad = document.querySelector("#txtEdad-error");
const txtnombresEmergencia = document.querySelector("#txtnombresEmergencia");
const error_nombreEm = document.querySelector("#txtnombresEmergencia-error");
const txtapellidosEmergencia = document.querySelector(
        "#txtapellidosEmergencia"
        );
const error_apellidosEm = document.querySelector(
        "#txtapellidosEmergencia-error"
        );
const txttelefonoEmergencia = document.querySelector("#txttelefonoEmergencia");
const error_TelEm = document.querySelector("#txttelefonoEmergencia-error");

//ABRIENDO EL MODAL
const modalDatosGe = document.querySelector("#modal_datos_generales");

const ocultarS = document.querySelectorAll(".ocultarC");


//BOTONES
const movPag = document.querySelector(".movPag");
const btn_adelante2 = document.querySelector(".sigPag");
const btn_atras1 = document.querySelector(".volver-pag1");
const btn_adelante3 = document.querySelector(".adelante-pag3");
const btn_atras2 = document.querySelector(".volver-pag2");
const btn_adelante4 = document.querySelector(".adelante-pag4");
const btn_atras3 = document.querySelector(".volver-pag3");
const btn_final = document.querySelector(".Fin");

const progressCheck = document.querySelectorAll(".progressbar__option .check");
const Siguiente = document.getElementById("btn_siguienteDT");
const btn_cancelarDatosG = document.querySelector("#btn_cancelarDatosG");
let progressOptions = document.querySelectorAll(".progressbar__option");
const Telc = document.querySelector("#telC");
const TelE = document.querySelector("#telE");

let cont = 1;

const modalDatosGenerales = document.querySelector("#modal_datos_generales");
const modal_detalleC = document.querySelector("#modal_control_cliente");

let idRegistro = document.querySelector("#txt_id");
let NombreCliente = document.querySelector("#txt_nombreD");
let correoCliente = document.querySelector("#txt_correoD");
let edadCliente = document.querySelector("#txt_edadD");
let alturaC = document.querySelector("#txt_alturaD");
let txt_idRegControl = document.querySelector("#txt_idRegControl");

//INPUT TELÉFONO INTERNACIONALES
const phoneInput = window.intlTelInput(txtTelefono, {
    preferredCountries: ["gt", "mx", "sv", "hn", "us"],
    utilsScript:
            "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
});

const phoneInputEmergency = window.intlTelInput(txttelefonoEmergencia, {
    preferredCountries: ["gt", "mx", "sv", "hn", "us"],
    utilsScript:
            "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
});

function calcularEdad(txtfechaNacimiento) {
    var hoy = new Date();
    var cumpleanos = new Date(txtfechaNacimiento);
    let edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    return edad;
}

window.addEventListener("load", function () {
    txtfechaNacimiento.addEventListener("change", function () {
        if (this.value) {
            edad.value = `${calcularEdad(this.value)}`;
            edad.style.fontWeight = "bold";
            edad.style.borderColor = "#00ff00";
        }
    });
});

let errores_DT = {
    txtnombresCliente: true,
    txtxapellidoCliente: true,
    txtcorreoCliente: true,
    txtfechaNacimiento: true,
    txtxCUIC: true,
    txtsexo: true,
    txtTelefono: true,
    txtEstatura: true,
    txtnombresEmergencia: true,
    txtapellidosEmergencia: true,
    txttelefonoEmergencia: true
};

const validatefieldWhite = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    if (fieldValue.trim().length === 0) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        error.innerHTML = "*Por favor llenar el campo";
        errores_DT[field_id] = true;
    } else {
        icon_error[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        error.innerText = "";
        errores_DT[field_id] = false;
    }

    submitController();
};

const validatefield = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp(
            "^([A-ZÀ-ÅÇ-ÖÙ-Ý][a-zà-åç-öù-ÿ]+(?:[-' ][A-ZÀ-ÅÇ-ÖÙ-Ý][a-zà-åç-öù-ÿ]+)*)$"
            );

    if (fieldValue.trim().length === 0) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_DT[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_DT[field_id] = true;
        error.innerHTML = "*Inicial Mayúscula, solo se permiten letras";
    } else {
        icon_error[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        errores_DT[field_id] = false;
        error.innerText = "";
    }
    submitController();
};

txtnombresCliente.addEventListener("blur", (e) =>
    validatefield(0, error_nombres, e)
);

txtnombresCliente.addEventListener("input", (e) =>
    validatefield(0, error_nombres, e)
);
txtxapellidoCliente.addEventListener("blur", (e) =>
    validatefield(1, error_apellidos, e)
);

txtxapellidoCliente.addEventListener("input", (e) =>
    validatefield(1, error_apellidos, e)
);

const validateEmailB = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp(
            "^(.*)@(gmail|googlemail|(.*\.)google)\.com"
            );

    if (fieldValue.trim().length === 0) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#FF0000";
        errores_DT[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#FF0000";
        errores_DT[field_id] = true;
        error.innerHTML = "*No cumple como un correo válido";
    } else {
        icon_error[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        errores_DT[field_id] = false;
        error.innerText = "";
    }
    if (fieldValue === "Ni") {
        icon_error[contador].classList.remove("invalid-background-ico");
        errores_DT[field_id] = false;
        field.style.borderColor = "#00ff00";
        error.innerText = "";
    }
    submitController();
};

const validatefieldNumberFloat = (contador, med, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp("[0-9]+([.|,][0-9]+)?");

    if (fieldValue.trim().length === 0) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#FF0000";
        errores_DT[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        icon_error[contador].classList.add("invalid-background-ico");

        errores_DT[field_id] = true;
        field.style.borderColor = "#FF0000";
        error.innerHTML = "Números enteros y decimales";
    } else {
        icon_error[contador].classList.remove("invalid-background-ico");

        errores_DT[field_id] = false;
        field.style.borderColor = "#00ff00";
        error.innerText = "";
    }
    submitController();
};

const validatePhoneNumberI = (contador, TelI, error, phone, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const phone2 = phone.getNumber();
    if (phone.isValidNumber()) {
        icon_error[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        error.innerHTML = "";
        errores_DT[field_id] = false;
    } else if (fieldValue.trim().length === 0) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        error.innerHTML = "*Por favor llenar el campo";
        errores_DT[field_id] = true;
    } else {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        error.innerText = "Número Invalido";
        errores_DT[field_id] = true;
    }

    if (fieldValue === "Ni") {
        icon_error[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        error.innerHTML = "";
        errores_DT[field_id] = false;
    }
    submitController();
    TelI.value = phone2;
};

const validateSelect = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    if (fieldValue === -1) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_DT[field_id] = true;
        error.innerHTML = "*Por favor seleccionar sexo";
    } else {
        icon_error[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        errores_DT[field_id] = false;
        error.innerText = "";
    }
    submitController();
};

const validatefieldCUI = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp("^[1-9]{1}[0-9]{3}\\s?[0-9]{5}\\s?[0-9]{4}$");

    if (fieldValue.trim().length === 0) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_DT[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        icon_error[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_DT[field_id] = true;
        error.innerHTML = "*Inicial Mayúscula, solo se permiten letras";
    } else {
        icon_error[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        errores_DT[field_id] = false;
        error.innerText = "";
    }
    submitController();
};

txtcorreoCliente.addEventListener("blur", (e) =>
    validateEmailB(2, error_correoCliente, e)
);
txtcorreoCliente.addEventListener("input", (e) =>
    validateEmailB(2, error_correoCliente, e)
);

txtfechaNacimiento.addEventListener("click", (e) =>
    validatefieldWhite(3, error_Nacimiento, e)
);
txtfechaNacimiento.addEventListener("change", (e) =>
    validatefieldWhite(3, error_Nacimiento, e)
);
txtxCUIC.addEventListener("input", (e) => validatefieldCUI(4, error_cui, e));
txtxCUIC.addEventListener("blur", (e) => validatefieldCUI(4, error_cui, e));



txtsexo.addEventListener("click", (e) => validateSelect(5, error_sexo, e));
txtsexo.addEventListener("change", (e) => validateSelect(5, error_sexo, e));



txtTelefono.addEventListener("blur", (e) =>
    validatePhoneNumberI(6, Telc, error_telefono, phoneInput, e)
);

txtTelefono.addEventListener("input", (e) =>
    validatePhoneNumberI(6, Telc, error_telefono, phoneInput, e)
);


txtEstatura.addEventListener("blur", (e) =>
    validatefieldNumberFloat(8, 0, error_estatura, e)
);
txtEstatura.addEventListener("input", (e) =>
    validatefieldNumberFloat(8, 0, error_estatura, e)
);




txtnombresEmergencia.addEventListener("blur", (e) =>
    validatefield(9, error_nombreEm, e)
);
txtnombresEmergencia.addEventListener("input", (e) =>
    validatefield(9, error_nombreEm, e)
);

txtapellidosEmergencia.addEventListener("blur", (e) =>
    validatefield(10, error_apellidosEm, e)
);

txtapellidosEmergencia.addEventListener("input", (e) =>
    validatefield(10, error_apellidosEm, e)
);


txttelefonoEmergencia.addEventListener("blur", (e) =>
    validatePhoneNumberI(11, TelE, error_TelEm, phoneInputEmergency, e)
);

txttelefonoEmergencia.addEventListener("input", (e) =>
    validatePhoneNumberI(11, TelE, error_TelEm, phoneInputEmergency, e)
);

window.addEventListener("load", function () {
    txtsexo.addEventListener("change", function () {
        if (this.value === "1") {
            ocultarS[0].style.display = "none";
            ocultarS[1].style.display = "none";
            ocultarS[2].style.display = "none";
            ocultarS[3].style.display = "none";
        } else {
            ocultarS[0].style.display = "";
            ocultarS[1].style.display = "";
            ocultarS[2].style.display = "";
            ocultarS[3].style.display = "";
        }
    });
});


btn_adelante2.addEventListener("click", function (e) {
    movPag.style.marginLeft = "-16.67%";
    progressOptions[cont - 1].classList.add("active");
    cont += 1;
});

btn_adelante3.addEventListener("click", function (e) {
    e.preventDefault();
    movPag.style.marginLeft = "-33.33%";
    progressOptions[cont - 1].classList.add("active");
    cont += 1;
});

btn_adelante4.addEventListener("click", function (e) {
    e.preventDefault();
    movPag.style.marginLeft = "-50%";
    progressOptions[cont - 1].classList.add("active");
    cont += 1;
});

btn_final.addEventListener("click", function (e) {
    return false;
});

// BOTONES HACIA ATRAS
btn_atras1.addEventListener("click", function (e) {
    e.preventDefault();
    movPag.style.marginLeft = "0%";
    progressOptions[cont - 2].classList.remove("active");
    cont -= 1;
});

btn_atras2.addEventListener("click", function (e) {
    e.preventDefault();
    movPag.style.marginLeft = "-16.67%";
    progressOptions[cont - 2].classList.remove("active");
    cont -= 1;
});

btn_atras3.addEventListener("click", function (e) {
    e.preventDefault();
    movPag.style.marginLeft = "-33.33%";
    progressOptions[cont - 2].classList.remove("active");
    cont -= 1;
});

btn_final.addEventListener("click", function (e) {
    return false;
    progressOptions[cont - 1].classList.add("active");
    cont += 1;
});

submitController = () => {
    if (
            errores_DT.txtnombresCliente ||
            errores_DT.txtxapellidoCliente ||
            errores_DT.txtcorreoCliente ||
            errores_DT.txtfechaNacimiento ||
            errores_DT.txtxCUIC ||
            errores_DT.txtsexo ||
            errores_DT.txtTelefono ||
            errores_DT.txtnombresEmergencia ||
            errores_DT.txtapellidosEmergencia ||
            errores_DT.txttelefonoEmergencia
            ) {
        Siguiente.toggleAttribute("disabled", true);
    } else {
        Siguiente.toggleAttribute("disabled", false);
    }
};

btn_cancelarDatosG.addEventListener("click", (e) => {
    e.preventDefault();
    Swal.fire({
        target: document.querySelector("#modal_datos_generales"),
        title: "¿Desea cancelar el registro?",
        text: "Se perderán los datos, si es que ya llenó algunos.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#0072ff",
        cancelButtonColor: "#D2122E",
        cancelButtonText: "Cancelar",
        confirmButtonText: "Sí, deseo salir"
    }).then((result) => {
        if (result.value) {
            LimpiarTodo();
            modalDatosGe.close();
        }
    });
});



function LimpiarTodo() {
    limpiarInputs();
    LimpiarColor();
    ColorIcon();
}

function limpiarInputs() {
    txtnombresCliente.value = "";
    txtxapellidoCliente.value = "";
    txtcorreoCliente.value = "";
    txtfechaNacimiento.value = "";
    txtxCUIC.value = "";
    txtTelefono.value = "";
    txtsexo.value = -1;
    edad.value = "";
    txtnombresEmergencia.value = "";
    txtapellidosEmergencia.value = "";
    txttelefonoEmergencia.value = "";
    error_nombres.innerHTML = "";
    error_apellidos.innerHTML = "";
    error_correoCliente.innerHTML = "";
    error_Nacimiento.innerHTML = "";
    error_cui.innerHTML = "";
    txtEstatura.innerHTML = "";
    error_estatura.innerHTML = "";
    error_telefono.innerHTML = "";
    error_sexo.innerHTML = "";
    error_edad.innerHTML = "";
    error_nombreEm.innerHTML = "";
    error_apellidosEm.innerHTML = "";
    error_TelEm.innerHTML = "";
}

function LimpiarColor() {
    txtnombresCliente.style.borderColor = "#515151";
    txtxapellidoCliente.style.borderColor = "#515151";
    txtcorreoCliente.style.borderColor = "#515151";
    txtfechaNacimiento.style.borderColor = "#515151";
    txtxCUIC.style.borderColor = "#515151";
    txtEstatura.style.borderColor = "#515151";
    txtTelefono.style.borderColor = "#515151";
    txtsexo.style.borderColor = "#515151";
    edad.style.borderColor = "#515151";
    txtnombresEmergencia.style.borderColor = "#515151";
    txtapellidosEmergencia.style.borderColor = "#515151";
    txttelefonoEmergencia.style.borderColor = "#515151";
}


function ColorIcon() {
    for (let i = 0; i < icon_error.length; i++) {
        icon_error[i].classList.replace("invalid-background-ico", "border-colorIcon");
    }
}

///CONDICIONES
// SALUD EN GENERAL

const allStars_salud = document.querySelectorAll(".star-salud");
let rating_salud = document.querySelector(".rating-salud");
allStars_salud.forEach((star, i) => {
    star.addEventListener("click", function (e) {
        e.preventDefault();
        let current_star_level = i + 1;
        let opcion = "";
        switch (current_star_level) {
            case 1:
                opcion = "Muy mala";
                break;
            case 2:
                opcion = "Mala";
                break;
            case 3:
                opcion = "Regular";
                break;
            case 4:
                opcion = "Buena";
                break;
            case 5:
                opcion = "Excelente";
                break;

            default:
                opcion = "Elegir";
                break;
        }

        rating_salud.value = opcion;
        allStars_salud.forEach((star, j) => {
            if (current_star_level >= j + 1) {
                star.innerHTML = "&#9733";
            } else {
                star.innerHTML = "&#9734";
            }
        });
    });
});

// CONDICIÓN FISICA
const allStarsFisico = document.querySelectorAll(".star-fisico");
let rating_fisico = document.querySelector(".rating-fisico");
allStarsFisico.forEach((star, i) => {
    star.addEventListener("click", function (e) {
        e.preventDefault();
        let current_star_level = i + 1;
        let opcion = "";
        switch (current_star_level) {
            case 1:
                opcion = "Muy mala";
                break;
            case 2:
                opcion = "Mala";
                break;
            case 3:
                opcion = "Regular";
                break;
            case 4:
                opcion = "Buena";
                break;
            case 5:
                opcion = "Excelente";
                break;

            default:
                opcion = "Elegir";
                break;
        }
        rating_fisico.value = opcion;
        allStarsFisico.forEach((star, j) => {
            if (current_star_level >= j + 1) {
                star.innerHTML = "&#9733";
            } else {
                star.innerHTML = "&#9734";
            }
        });
    });
});

// CONDICIÓN NUTRICIONAL
const allStarsNutricion = document.querySelectorAll(".star-nutricion");
let rating_nutricion = document.querySelector(".rating-nutricion");
allStarsNutricion.forEach((star, i) => {
    star.addEventListener("click", function (e) {
        e.preventDefault();
        let current_star_level = i + 1;
        let opcion = "";
        switch (current_star_level) {
            case 1:
                opcion = "Muy mala";
                break;
            case 2:
                opcion = "Mala";
                break;
            case 3:
                opcion = "Regular";
                break;
            case 4:
                opcion = "Buena";
                break;
            case 5:
                opcion = "Excelente";
                break;

            default:
                opcion = "Elegir";
                break;
        }
        rating_nutricion.value = opcion;
        allStarsNutricion.forEach((star, j) => {
            if (current_star_level >= j + 1) {
                star.innerHTML = "&#9733";
            } else {
                star.innerHTML = "&#9734";
            }
        });
    });
});

// CONDICIÓN NUTRICIONAL
const allStarsEstres = document.querySelectorAll(".star-estres");
let rating_estres = document.querySelector(".rating-estres");
allStarsEstres.forEach((star, i) => {
    star.addEventListener("click", function (e) {
        e.preventDefault();
        let current_star_level = i + 1;
        let opcion = "";
        switch (current_star_level) {
            case 1:
                opcion = "Muy mala";
                break;
            case 2:
                opcion = "Mala";
                break;
            case 3:
                opcion = "Regular";
                break;
            case 4:
                opcion = "Buena";
                break;
            case 5:
                opcion = "Excelente";
                break;

            default:
                opcion = "Elegir";
                break;
        }
        rating_estres.value = opcion;

        allStarsEstres.forEach((star, j) => {
            if (current_star_level >= j + 1) {
                star.innerHTML = "&#9733";
            } else {
                star.innerHTML = "&#9734";
            }
        });
    });
});

//Enviar datos
var frm = $('#form_datosg');

frm.submit(function (e) {
    e.preventDefault();

    $.ajax({
        type: frm.attr('method'),
        url: frm.attr('action') + "?accion=insertar_cliente",
        data: frm.serialize(),

        success: function (data) {

            let error = data;
            if (error == 0 || error === null) {
                modalDatosGenerales.close();
                Swal.fire({
                    title: "Error",
                    text: "Vaya, algo ha ocurrido mal",
                    icon: "error",
                    confirmButtonColor: "#ff004c",
                }).then(function () {
                    window.location.replace("Modulos?modulo=registros");
                });
            } else {

                modalDatosGenerales.close();
                Swal.fire({
                    title: "Excelente!!",
                    text: "Cliente creado correctamente",
                    icon: "success",
                    confirmButtonColor: "#008d49",
                }).then(function () {
                    traerDatos(error);
                });
                //        

            }
        },
        error: function (data) {

        },
    });

});



function traerDatos(idCliente) {

    $.ajax({
        url: frm.attr('action') + "?accion=traer_registro&id_cliente=" + idCliente,
        type: "GET",
        dataType: "JSON",
        success: function (data) {

            idRegistro.value = data.cod_registro;
            NombreCliente.value = data.nomC;
            correoCliente.value = data.email;
            edadCliente.value = data.edad;
            alturaC.value = data.altura;
            txt_idRegControl.value = data.cod_registro;
            size_input(idRegistro);
            size_input(NombreCliente);
            size_input(correoCliente);
            size_input(edadCliente);
            size_input(alturaC);
            
        
            modal_detalleC.showModal();
        }

    });
}

const size_input = (input_name) => {
    var texto = input_name;
    var txt = texto.value;
    var tamano = txt.length;
    tamano *= 8.1;
    texto.style.width = tamano + "px";
};

const txtidCliente2 = document.querySelector("#txtidCliente");
//PARA EL REGISTRO
const btnReporte = document.querySelector("#button-ver-reporte");

btnReporte.addEventListener("click", (e) => {
    e.preventDefault();
    localStorage.setItem("id", txtidCliente2.value);
    window.open("Modulos?modulo=reporte");

});


let cod_Cliente;
let frmoperacionesc = $('#operacionesCliente');
const getvalueIdcliente = () => {
    cod_Cliente = document.querySelector("#txtidCliente").value;
};

//$(document).ready(function () {
//    $("#button-eliminar-cliente").click(function (e) {
//        e.preventDefault();
//        getvalueIdcliente();
//        Swal.fire({
//            title: "¿Desea eliminar al cliente?",
//            text: "Se eliminara el registro completo del cliente",
//            icon: "warning",
//            showCancelButton: true,
//            confirmButtonColor: "#0072ff",
//            cancelButtonColor: "#D2122E",
//            cancelButtonText: "Cancelar",
//            confirmButtonText: "Sí, deseo eliminar"
//        }).then((result) => {
//            if (result.value) {
//                eliminarRegistro();
//            }
//        });
//    });


//    function eliminarRegistro() {
//        $.ajax({
//            type: frmoperacionesc.attr('method'),
//            url: frmoperacionesc.attr('action') + "?accion=eliminar_registro",
//            data: frmoperacionesc.serialize(),
//
//            success: function (data) {
//
//                let error = data;
//                if (error == 0 || error === null) {
//
//                    Swal.fire({
//                        title: "Error",
//                        text: "Vaya, algo ha ocurrido mal",
//                        icon: "error",
//                        confirmButtonColor: "#ff004c",
//                    }).then(function () {
//                        window.location.replace("Modulos?modulo=registros");
//                    });
//                } else {
//
//                    Swal.fire({
//                        title: "Excelente!!",
//                        text: "Cliente eliminado correctamente",
//                        icon: "success",
//                        confirmButtonColor: "#008d49",
//                    }).then(function () {
//                        window.location.replace("Modulos?modulo=registros");
//                    });
//                }
//            }
//        });
//    }
//});
//


