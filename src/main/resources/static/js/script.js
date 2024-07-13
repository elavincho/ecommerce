// Validar Formulario de Registro

const formulario = document.getElementById("formulario");
const inputs = document.querySelectorAll("#formulario input");

const expresiones = {
  usuario: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
  nombre: /^[a-zA-ZÀ-ÿ\s]{3,16}$/, // Letras y espacios, pueden llevar acentos.
  apellido: /^[a-zA-ZÀ-ÿ\s]{2,16}$/, // Letras y espacios, pueden llevar acentos.
  password: /^.{6,12}$/, // 6 a 12 digitos.
  correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
  telefono: /^\d{10,14}$/, // 10 a 14 numeros.
  documento: /^\d{7,8}$/, // 7 a 8 numeros.
  direccion: /^[a-zA-ZÀ-ÿ\s\_\-]{1,20}$/, // Letras, numeros, guion y guion_bajo
  altura: /^\d{1,5}$/, // 1 a 4 numeros.
  piso: /^[a-zA-Z0-9]{1,2}$/, // Letras y espacios, pueden llevar acentos.
  depto: /^[a-zA-Z0-9]{1,2}$/, // Letras y espacios, pueden llevar acentos.
  localidad: /^[a-zA-ZÀ-ÿ\s]{4,16}$/, // Letras y espacios, pueden llevar acentos.
  provincia: /^[a-zA-ZÀ-ÿ\s]{5,16}$/, // Letras y espacios, pueden llevar acentos.
};

const campos = {
	usuario: false,
	nombre: false,
	password: false,
	correo: false,
	telefono: false
}

const validarFormulario = (e) => {
  // console.log(e.target.name); //nos indica el nombre del input
  switch (e.target.name) {
    case "username":
      validarCampo(expresiones.usuario, e.target, 'username');
      break;
    case "email":
      validarCampo(expresiones.correo, e.target, 'email');
      break;
    case "password":
      validarCampo(expresiones.password, e.target, 'password');
      validarPassword2();
      break;
    case "password2":
      validarCampo(expresiones.password, e.target, 'password2');
      break;
    case "nombre":
      validarCampo(expresiones.nombre, e.target, 'nombre');
      break;
    case "apellido":
      validarCampo(expresiones.apellido, e.target, 'apellido');
      break;
    case "documento":
      validarCampo(expresiones.documento, e.target, 'documento');
      break;
    case "telefono":
      validarCampo(expresiones.telefono, e.target, 'telefono');
      break;
    case "direccion":
      validarCampo(expresiones.direccion, e.target, 'direccion');
      break;
    case "altura":
      validarCampo(expresiones.altura, e.target, 'altura');
      break;
    case "piso":
      validarCampo(expresiones.piso, e.target, 'piso');
      break;
    case "depto":
      validarCampo(expresiones.depto, e.target, 'depto');
      break;
    case "localidad":
      validarCampo(expresiones.localidad, e.target, 'localidad');
      break;
    case "provincia":
      validarCampo(expresiones.provincia, e.target, 'provincia');
      break;
  }
};

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo-${campo}`).classList.remove('formulario-grupo-incorrecto');
		document.getElementById(`grupo-${campo}`).classList.add('formulario-grupo-correcto');
		document.querySelector(`#grupo-${campo} i`).classList.add('fa-circle-check');
		document.querySelector(`#grupo-${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo-${campo} .formulario-input-error`).classList.remove('formulario-input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo-${campo}`).classList.add('formulario-grupo-incorrecto');
		document.getElementById(`grupo-${campo}`).classList.remove('formulario-grupo-correcto');
		document.querySelector(`#grupo-${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo-${campo} i`).classList.remove('fa-circle-check');
		document.querySelector(`#grupo-${campo} .formulario-input-error`).classList.add('formulario-input-error-activo');
		campos[campo] = false;
	}
};

const validarPassword2 = () => {
	const inputPassword1 = document.getElementById('password');
	const inputPassword2 = document.getElementById('password2');

	if(inputPassword1.value !== inputPassword2.value){
		document.getElementById(`grupo-password2`).classList.add('formulario-grupo-incorrecto');
		document.getElementById(`grupo-password2`).classList.remove('formulario-grupo-correcto');
		document.querySelector(`#grupo-password2 i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo-password2 i`).classList.remove('fa-circle-check');
		document.querySelector(`#grupo-password2 .formulario-input-error`).classList.add('formulario-input-error-activo');
		campos['password'] = false;
	} else {
		document.getElementById(`grupo-password2`).classList.remove('formulario-grupo-incorrecto');
		document.getElementById(`grupo-password2`).classList.add('formulario-grupo-correcto');
		document.querySelector(`#grupo-password2 i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo-password2 i`).classList.add('fa-circle-check');
		document.querySelector(`#grupo-password2 .formulario-input-error`).classList.remove('formulario-input-error-activo');
		campos['password'] = true;
	}
};

inputs.forEach((input) => {
  input.addEventListener("keyup", validarFormulario);
  //blur es para cuando hacemos un click afuera del campo
  input.addEventListener("blur", validarFormulario);
});

// esto previene que se envien los datos con el boton enviar
// formulario.addEventListener("submit", (e) => {
//   e.preventDefault();
// });

// Fin Validar Formulario de Registro

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

/* Alert para eliminar un producto */
function eliminar(id) {
  swal({
    title: "¿Estas Seguro?",
    text: "¡Realmente lo queres eliminar!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
    buttons: ["Cancelar", "Eliminar"],
  }).then((Eliminar) => {
    if (Eliminar) {
      $.ajax({
        url: "/productos/delete/" + id,
        success: function (res) {
          console.log(res);
        },
      });
      swal("Registro eliminado.", {
        icon: "success",
      }).then((ok) => {
        if (ok) {
          location.href = "/productos";
        }
      });
    }
  });
}
/* Fin Alert para eliminar un producto */

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

/* Alert compra exitosa */
function compraExitosa() {
  swal({
    title: "¡Compra Exitosa!",
    text: "Tu compra fue realizada con exito.",
    icon: "success",
    timer: 1000,

    //con el then vacio y el if !"" se realiza la compra automaticamente
  }).then(() => {
    if (!"") {
      $.ajax({
        url: "/saveOrder",
        success: function (res) {
          console.log(res);
          location.href = "/usuario/compras";
        },
      });
    }
  });
}
/* Fin Alert compra exitosa */

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

/* Alert para eliminar un promo */
function eliminarPromo(id) {
  swal({
    title: "¿Estas Seguro?",
    text: "¡Realmente lo queres eliminar!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
    buttons: ["Cancelar", "Eliminar"],
  }).then((Eliminar) => {
    if (Eliminar) {
      $.ajax({
        url: "/promos/delete/" + id,
        success: function (res) {
          console.log(res);
        },
      });
      swal("Registro eliminado.", {
        icon: "success",
      }).then((ok) => {
        if (ok) {
          location.href = "/promos/show";
        }
      });
    }
  });
}
/* FinAlert para eliminar un promo */

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

/* Buscador*/
document.addEventListener("keyup", (e) => {
  if (e.target.matches("#buscador")) {
    /*Para vaciar el campo al presionar esc*/
    if (e.key === "Escape") {
      e.target.value = "";
    }
    document.querySelectorAll("#buscar").forEach((articulo) => {
      articulo.textContent.toUpperCase().includes(e.target.value) ||
      articulo.textContent.toLowerCase().includes(e.target.value) ||
      articulo.textContent.includes(e.target.value)
        ? articulo.classList.remove("filtro")
        : articulo.classList.add("filtro");
    });
  }
});
/* Fin Buscador*/

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

/* Buscador Inverso*/
document.addEventListener("keyup", (e) => {
  if (e.target.matches("#buscador2")) {
    /*Para vaciar el campo al presionar esc*/
    if (e.key === "Escape") {
      e.target.value = "";
    }

    document.querySelectorAll("#buscar2").forEach((articulo) => {
      articulo.textContent.toUpperCase().includes(e.target.value) ||
      articulo.textContent.toLowerCase().includes(e.target.value) ||
      articulo.textContent.includes(e.target.value)
        ? articulo.classList.remove("filtro")
        : articulo.classList.add("filtro");
    });
  }
});
/* Fin Buscador Inverso*/

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

/* Alert para bloquear Usuarios */
function bloquearUsuario(id) {
  swal({
    title: "¿Estas Seguro?",
    text: "¡Realmente lo queres bloquear!",
    icon: "warning",
    buttons: true,
    dangerMode: true,
    buttons: ["Cancelar", "Bloquear"],
  }).then((Eliminar) => {
    if (Eliminar) {
      $.ajax({
        url: "/usuario/bloquear/" + id,
        success: function (res) {
          console.log(res);
        },
      });
      swal("Usuario Bloqueado.", {
        icon: "success",
      }).then((ok) => {
        if (ok) {
          location.href = "/administrador/usuarios";
        }
      });
    }
  });
}
/* Fin Alert para bloquear un usuario */

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

// Mostrar el nombre del archivo seleccionado en los input

document.getElementById("img").onchange = function () {
  document.getElementById("fichero").innerHTML = this.files[0].name;
};

document.getElementById("img2").onchange = function () {
  document.getElementById("fichero2").innerHTML = this.files[0].name;
};

document.getElementById("img3").onchange = function () {
  document.getElementById("fichero3").innerHTML = this.files[0].name;
};

document.getElementById("img").onchange = function () {
  document.getElementById("foto").innerHTML = this.files[0].name;
};

// Fin Mostrar el nombre del archivo seleccionado

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

// Mostrar vista previa de la imagen antes de subir al sevidor

function preview(event) {
  const fileInput = event.target;
  const file = fileInput.files[0];
  const reader = new FileReader();
  reader.onload = function () {
    const image = document.getElementById("output");
    image.src = reader.result;
    image.style.display = "block";
  };
  reader.readAsDataURL(file);
}

// Fin Mostrar vista previa de la imagen antes de subir al sevidor

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

//Verificar que las contraseñas coincidan

function actualizarContrasena(id) {
  swal({
    title: "¡Actualización Exitosa!",
    text: "Tu contraseña fue actualizada con exito.",
    icon: "success",
    timer: 1000,

    //con el then vacio y el if !"" se realiza la compra automaticamente
  }).then(() => {
    if (!"") {
      $.ajax({
        url: "/administrador/updatePassword" + id,
        success: function (res) {
          console.log(res);
          location.href = "/administrador";
        },
      });
    }
  });
}

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

//Maximizar Imagenes

function abrir() {
  document.getElementById("vent").style.display = "block";
}

function cerrar() {
  document.getElementById("vent").style.display = "none";
}

function abrir2() {
  document.getElementById("vent2").style.display = "block";
}

function cerrar2() {
  document.getElementById("vent2").style.display = "none";
}

function abrir3() {
  document.getElementById("vent3").style.display = "block";
}

function cerrar3() {
  document.getElementById("vent3").style.display = "none";
}

// fin Maximizar imagenes

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

/* Funcion para cambiar la imagen principal del producto*/

function imgSlider(cambiarImg) {
  document.querySelector(".productos").src = cambiarImg;
}

/* Fin Funcion para cambiar la imagen principal del producto*/

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>

// NO LO USE PORQUE NO ME GUSTA COMO QUEDA

// Funcion para maximizar imagenes abriendo otra ventana

// function maximizarImagen() {
//   const imagen = document.getElementById('imagen');
//   const ventanaEmergente = window.open('', '_blank', 'width=500,height=500');
//   ventanaEmergente.document.write(`<img src="${imagen.src}" style="max-width: 100%; max-height: 100%;">`);
// }

function maximizarImagen(src) {
  const imagen = document.getElementById("imagen");
  const ventanaEmergente = window.open("", "_blank", "width=500,height=500");
  ventanaEmergente.document.write(
    `<img src="${src}" style="max-width: 100%; max-height: 100%;">`
  );
}

// Fin Funcion para maximizar imagenes abriendo otra ventana

//  <<<---------- * ---------- * ---------- * ---------- * ---------- * ---------->>>
