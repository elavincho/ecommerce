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
