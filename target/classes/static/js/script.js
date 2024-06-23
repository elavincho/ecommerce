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

//Maximizar Imagenes

function abrir() {
  document.getElementById("vent").style.display = "block";
}

function cerrar() {
  document.getElementById("vent").style.display = "none";
}

// fin Maximizar imagenes




/* Funcion para cambiar la imagen principal del producto*/

function imgSlider(cambiarImg) {
  document.querySelector(".productos").src = cambiarImg;
}

/* Fin Funcion para cambiar la imagen principal del producto*/
