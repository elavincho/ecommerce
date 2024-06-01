package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.DetalleOrden;
import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.DetalleOrdenService;
import com.ecommerce.ecommerce.service.EmpresaService;
import com.ecommerce.ecommerce.service.OrdenService;
import com.ecommerce.ecommerce.service.ProductoService;
import com.ecommerce.ecommerce.service.PromoService;
import com.ecommerce.ecommerce.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author elavincho
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private PromoService promoService;

    // Para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    // Datos de la orden
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model, HttpSession session) {

        logger.info("Sesion del usuario: {}", session.getAttribute("idusuario"));

        logger.info("Todos los datos del usuario: {}", session.getAttribute("usersession"));

        model.addAttribute("productos", productoService.findAll());

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());
        // Pasamos los datos de la promo
        model.addAttribute("promo", promoService.findAll());

        return "usuario/home";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model, HttpSession session) {

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());
        // Pasamos todos los datos del producto
        model.addAttribute("productos", productoService.findAll());

        logger.info("Id producto enviado como parametro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);

        logger.info("Sesion del usuario: {}", session.getAttribute("idusuario"));

        return "usuario/productoHome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model, HttpSession session) {

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        logger.info("Sesion del usuario: {}", session.getAttribute("idusuario"));

        // Si el cliente intenta agregar un producto al carrito y no esta logueado
        if (session.getAttribute("idusuario") == null) {
            return "redirect:/usuario/login";
        }

        DetalleOrden detalleOrden = new DetalleOrden();

        Producto producto = new Producto();

        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(id);

        logger.info("Producto agregado: {}", optionalProducto.get());
        logger.info("cantidad: {}", cantidad);

        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        // Formato de número
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        detalleOrden.setPrecioFormateado(formatoNumero.format(producto.getPrecio()));

        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        // Formato de número
        // NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        detalleOrden.setTotalFormateado(formatoNumero.format(producto.getPrecio() * cantidad));
        detalleOrden.setProducto(producto);

        // Validar que el producto no se agregue mas de dos veces
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        // Formato de número
        // NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        orden.setTotalFormateada(formatoNumero.format(detalles.stream().mapToDouble(dt -> dt.getTotal()).sum()));

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    // Quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProducto(@PathVariable Integer id, Model model, HttpSession session) {

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        logger.info("Sesion del usuario: {}", session.getAttribute("idusuario"));

        List<DetalleOrden> ordenNueva = new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden : detalles) {

            if (detalleOrden.getProducto().getId() != id) {
                ordenNueva.add(detalleOrden);
            }

        }

        // poner la nueva lista con los productos restantes
        detalles = ordenNueva;

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);

        // Formato de número
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        orden.setTotalFormateada(formatoNumero.format(sumaTotal));

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        // Evitamos que el usuario haga compras con el carrito vacio
        if (orden.getTotal() == 0) {
            return "redirect:/";
        } else {
            return "/usuario/carrito";
        }

    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        model.addAttribute("usuario", usuario);

        // Evitamos que el usuario realize una compra en cero
        if (orden.getTotal() == 0) {
            return "redirect:/";
        } else {
            return "usuario/resumenorden";
        }
    }

    @GetMapping("/saveOrder")
    public String saveOrder(Model model, HttpSession session) {

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario").toString());
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Date fechaCreacion = new Date();
        // Le damos formato a la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
        String formattedDate = sdf.format(fechaCreacion);
        orden.setFechaCreacionFormateada(formattedDate);

        // Guardamos la fecha con formato original
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        // Usuario
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        orden.setUsuario(usuario);
        ordenService.save(orden);

        // Guardar detalles
        for (DetalleOrden dt : detalles) {
            dt.setOrden(orden);
            detalleOrdenService.save(dt);

        }

        // Limpiar lista y orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String seachProduct(@RequestParam String nombre, Model model, HttpSession session) {

        logger.info("Nombre del Producto: {}", nombre);
        // Pasamos los datos de los productos
        model.addAttribute("productos", productoService.findAll());
        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());
        // Pasamos los datos de la promo
        model.addAttribute("promo", promoService.findAll());

        // List<Producto> productos = productoService.findAll().stream().filter(p ->
        // p.getNombre().contains(nombre))
        // .collect(Collectors.toList());

        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre) ||
                p.getCategoria().contains(nombre) || p.getNombre().toLowerCase().contains(nombre) ||
                p.getCategoria().toLowerCase().contains(nombre) || p.getNombre().toUpperCase().contains(nombre) ||
                p.getCategoria().toUpperCase().contains(nombre))
                .collect(Collectors.toList());

        model.addAttribute("productos", productos);
        return "usuario/allProducts";
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model, HttpSession session) {

        // Pasamos los datos de los productos
        model.addAttribute("productos", productoService.findAll());
        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());
        // Pasamos los datos de la promo
        model.addAttribute("promo", promoService.findAll());

        return "usuario/allProducts";
    }

    @GetMapping("/entrega/{id}")
    public String entrega(Model model, @PathVariable Integer id, HttpSession session) {

        logger.info("Id de la orden: {}", id);

        // Le pasamos los datos de la orden para obtener los datos del cliente
        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());

        // Le pasamos la orden para obtener el total de la compra
        model.addAttribute("ordencompra", orden.get());

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "administrador/entregar_orden";
    }

    @PostMapping("/entregado")
    public String entregado(Model model, Orden orden, @RequestParam String dni,
            @RequestParam String recibidoPor, @RequestParam String entregadoPor,
            HttpSession session) throws IOException {

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Orden o = new Orden();
        o = ordenService.get(orden.getId()).get();

        Date fechaRecibida = new Date();
        // Le damos formato a la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
        String formattedDate = sdf.format(fechaRecibida);
        orden.setFechaRecibidaFormateada(formattedDate);

        // Guardamos la fecha con formato original
        orden.setFechaRecibida(fechaRecibida);

        // Obtenemos y guardamos todos los datos para que no se borren de la bd
        orden.setNumero(o.getNumero());
        orden.setFechaCreacion(o.getFechaCreacion());
        orden.setFechaCreacionFormateada(o.getFechaCreacionFormateada());
        orden.setUsuario(o.getUsuario());
        orden.setTotal(o.getTotal());
        orden.setTotalFormateada(o.getTotalFormateada());

        ordenService.update(orden);

        return "redirect:/administrador/ordenes";
    }

}
