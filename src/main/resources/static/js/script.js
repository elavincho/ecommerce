// Alert para eliminar un producto
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
      }).then((ok)=> {
        if(ok){
            location.href="/productos";
        }
      })
    } 
   
  });
}


// Alert compra exitosa
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
          location.href="/usuario/compras";
        },
      });
    } 
  });
}
