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



function compraExitosa() {

  swal({
    title: "¡Compra Exitosa!",
    text: "Tu compra fue realizada con exito.",
    icon: "success",
    timer: 2000,
    showConfirmButton: false
  }).then((ok) => {
    if (ok) {
      $.ajax({
        url: "/saveOrder",
        success: function (res) {
          console.log(res);
          location.href="/";
        },
      });
     
    } 
   
  });

}
