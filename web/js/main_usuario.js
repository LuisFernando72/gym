//18122022

/* global Swal */

const modalUser = document.querySelector("#modalUser");
const txt_idUser = document.querySelector("#txt_idUser");
const txtNombres = document.querySelector("#txtNombres");
const txtApellidos = document.querySelector("#txtApellidos");
const txtCui = document.querySelector("#txtCui");
const txtCorreo = document.querySelector("#txtCorreo");
const txtRol = document.querySelector("#txtRol");
const txtFechai = document.querySelector("#txtFechai");
const btn_cancelarUs = document.querySelector("#btn_cancelarUs");
const btnAsignarPuesto = document.querySelector("#btnAsignarPuesto");
const btnEliminarU = document.querySelector("#btnEliminarU");
const btnAceptarR = document.querySelector("#btnAceptarR");
let txtImgU = document.querySelector("#txtImgU");

//REDES
const txtFace = document.querySelector("#txtFace");
const txtinsta = document.querySelector("#txtinsta");
const txtTw = document.querySelector("#txtTw");

let redes1 = document.querySelector("#Ifacebook");
let redes2 = document.querySelector("#Iinstagram");
let redes3 = document.querySelector("#Itw");

$(document).ready(function () {
    $("#tblCustomer").DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
        },
        scrollY: 380,
        scrollX: true
    });
});



$("#tblCustomer").on("click", "tr td", function (evt) {
    let image, target, id, Id_user, nombres, apellidos, cui,
            correo, rol, fecha_creacion, idp;

    target = $(event.target);
    id = target.parent().data("id");
    idImg = target.parent().data("idimg");
    idp = target.parent().data("idp");

    nombres = target.parent("tr").find("td").eq(2).html();
    apellidos = target.parent("tr").find("td").eq(3).html();
    cui = target.parent("tr").find("td").eq(4).html();
    correo = target.parent("tr").find("td").eq(5).html();
    rol = target.parent("tr").find("td").eq(6).html();
    fecha_creacion = target.parent("tr").find("td").eq(7).html();
    let tres = {
        id,
        idImg,
        Id_user,
        idp,
        nombres,
        apellidos,
        cui,
        correo,
        rol,
        fecha_creacion
    };

    AbrirModal(tres);
});

function AbrirModal(tres) {
    modalUser.showModal();
    let data = tres;
    txtImgU.src = "imgUser/" + data.idImg;
    txt_idUser.value = data.id;
    txtNombres.value = data.nombres;
    txtApellidos.value = data.apellidos;
    txtCui.value = data.cui;
    txtCorreo.value = data.correo;
    txtFechai.value = data.fecha_creacion;
    txtRol.value = data.idp;
}

window.addEventListener("load", function () {
    readDonly();
    traerConfiguraciones();

});

redes1.addEventListener("click", () => {
    redes();

});

redes2.addEventListener("click", () => {
    redes();

});
redes3.addEventListener("click", () => {
    redes();

});

function redes() {
    redes1.href = txtFace.value;
    redes2.href = txtinsta.value;
    redes3.href = txtTw.value;
}

function readDonly() {
    txtNombres.readOnly = true;
    txtApellidos.readOnly = true;
    txtCui.readOnly = true;
    txtCorreo.readOnly = true;
    txtFechai.readOnly = true;
}

function Limpiar() {
    txtNombres.value = "";
    txtApellidos.value = "";
    txtCui.value = "";
    txtCorreo.value = "";
    txtFechai.value = "";
}

btn_cancelarUs.addEventListener("click", () => {
    Limpiar();
    modalUser.close();
});

btnAsignarPuesto.addEventListener("click", () => {
    Asignar(txt_idUser.value, txtNombres.value, txtApellidos.value);
});

var frmAsignarP = $('#frmAsignarP');

function Asignar(id, nom, ape) {
    Swal.fire({
        target: document.querySelector("#modalUser"),
        title: "Puesto",
        text: "¿Desea asignar a " + nom + " " + ape + "?",
        icon: "warning",
        confirmButtonColor: "#0072ff",
        cancelButtonColor: "#D2122E",
        showCancelButton: true,
        confirmButtonText: "Sí, asignar",
        cancelButtonText: "Cancelar"
    }).then((resultado) => {
        if (resultado.value) {
            AsignarUsuario(id, txtRol.value);
        } else {

        }
    });


}

function AsignarUsuario(id, rol) {
    $.ajax({
        type: frmAsignarP.attr('method'),
        url: frmAsignarP.attr('action') + "?accion=AsignarUsuario",
        data: "txtid=" + id + "&txtrol=" + rol,
        success: function (data) {
            let error = data;
    
            if (error == 0 || error === null) {
                //   modalDatosGenerales.close();
                Swal.fire({
                    target: document.querySelector("#modalUser"),
                    title: "Error",
                    text: "Vaya, algo ha ocurrido mal",
                    icon: "error",
                    confirmButtonColor: "#ff004c"
                }).then(function () {
                    modalUser.close();
                    window.location.replace("Modulos?modulo=usuarios");
                });
            } else {
                Swal.fire({
                    target: document.querySelector("#modalUser"),
                    title: "Excelente!!",
                    text: "Nuevo rol agregado correctamente!!",
                    icon: "success",
                    confirmButtonColor: "#008d49"
                }).then(function () {
                    modalUser.close();
                    window.location.replace("Modulos?modulo=usuarios");
                });
            }
        }
    });
}


//PARA ELIMINAR UN USUARIO
btnEliminarU.addEventListener("click", () => {
    EliminarU(txt_idUser.value, txtNombres.value, txtApellidos.value, txtCorreo.value, txtCui.value);
}
);

function EliminarU(id, nom, ape, correoD, cui) {
    Swal.fire({
        target: document.querySelector("#modalUser"),
        title: "Eliminar Usuario",
        text: "¿Desea eliminar a " + nom + " " + ape + "?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
        confirmButtonColor: "#0072ff",
        cancelButtonColor: "#D2122E"
    }).then((resultado) => {
        if (resultado.value) {
            EliminarUsuario(id, correoD, cui);

        } else {

        }
    });
}


function EliminarUsuario(id, correoD, cui) {
    $.ajax({
        type: frmAsignarP.attr('method'),
        url: frmAsignarP.attr('action') + "?accion=EliminarUsuario",
        data: "txtid=" + id + "&txtCorreo=" + correoD + "&txtCui=" + cui,
        success: function (data) {
            let error = data;

            if (error == 0 || error === null) {

                Swal.fire({
                    target: document.querySelector("#modalUser"),
                    title: "Error",
                    text: "Vaya, algo ha ocurrido mal",
                    icon: "error",
                    confirmButtonColor: "#ff004c"
                }).then(function () {
                    window.location.replace("Modulos?modulo=usuarios");
                });
            } else {

                Swal.fire({
                    target: document.querySelector("#modalUser"),
                    title: "Excelente!!",
                    text: "Cliente eliminado correctamente",
                    icon: "success",
                    confirmButtonColor: "#008d49"
                     }).then(function () {
                    window.location.replace("Modulos?modulo=usuarios");
                });
            }
        }
    });
}

btnAceptarR.addEventListener("click", () => {
    ActualizarRedesS(txtFace.value, txtinsta.value, txtTw.value);
});

function ActualizarRedesS(facebook, instagram, twitter) {
    Swal.fire({
        title: "Actualizar Redes Sociales",
        text: "¿Desea actualizar los enlazes?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, actualizar",
        cancelButtonText: "Cancelar",
        confirmButtonColor: "#0072ff",
        cancelButtonColor: "#D2122E"
    }).then((resultado) => {
        if (resultado.value) {
            ActualizarRedesF(facebook, instagram, twitter);
        } else {
        }
    });
}

function ActualizarRedesF(f, i, t) {
    $.ajax({
        type: "post",
        url: "Configuraciones?accion=ActualizarRedesS",
        data: "txtface=" + f + "&txtins=" + i + "&txttw=" + t,
        success: function (data) {
         
            let error = data;

            if (error == 0 || error === null) {

                Swal.fire({
                    title: "Error",
                    text: "Vaya, algo ha ocurrido mal",
                    icon: "error",
                    confirmButtonColor: "#ff004c"
                }).then(function () {
                    window.location.replace("Modulos?modulo=usuarios");
                });
            } else {
                Swal.fire({
                    title: "Excelente!!",
                    text: "Nuevos enlazes registrados",
                    icon: "success",
                    confirmButtonColor: "#008d49"
                }).then(function () {
                    window.location.replace("Modulos?modulo=usuarios");
                });
            }
        }
    });
}



//FRASE
const btnActualizarFrase = document.querySelector("#btnActualizarFrase");
let txtFrase = document.querySelector("#txtFrase");
let txtAutor = document.querySelector("#txtAutor");
btnActualizarFrase.addEventListener("click", () => {
    ActualizarFraseA(txtFrase.value, txtAutor.value);
});

function ActualizarFraseA(frase, autor) {
    Swal.fire({
        title: "Actualizar frase inicio",
        text: "¿Desea  actualizar frase inicio?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, actualizar",
        cancelButtonText: "Cancelar",
        confirmButtonColor: "#0072ff",
        cancelButtonColor: "#D2122E",
    }).then((resultado) => {
        if (resultado.value) {
            ActualizarFrase(frase, autor);
        } else {
        }
    });
}

function ActualizarFrase(frase, autor) {
    $.ajax({
        type: "post",
        url: "Configuraciones?accion=ActualizarFrase",
        data: "txtfrase=" + frase + "&txtautor=" + autor,
        success: function (data) {
            let error = data;

            if (error == 0 || error === null) {

                Swal.fire({
                    title: "Error",
                    text: "Vaya, algo ha ocurrido mal",
                    icon: "error",
                    confirmButtonColor: "#ff004c"
                }).then(function () {
                    window.location.replace("Modulos?modulo=usuarios");
                });
            } else {
                Swal.fire({
                    title: "Excelente!!",
                    text: "Nueva frase agregada.",
                    icon: "success",
                    confirmButtonColor: "#008d49"
                }).then(function () {
                    window.location.replace("Modulos?modulo=usuarios");
                });
            }
        }
    });
}


function traerConfiguraciones() {
    $.ajax({
        url: "Configuraciones?accion=MostrarConfiguracion",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            txtFace.value = data.facebook;
            txtTw.value = data.tw;
            txtinsta.value = data.ins;
            txtFrase.value = data.frase;
            txtAutor.value = data.autor;
        }
    });
}