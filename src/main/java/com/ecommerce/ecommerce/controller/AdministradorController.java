package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Empresa;
import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.EmpresaService;
import com.ecommerce.ecommerce.service.OrdenService;
import com.ecommerce.ecommerce.service.ProductoService;
import com.ecommerce.ecommerce.service.PromoService;
import com.ecommerce.ecommerce.service.UploadFileService;
import com.ecommerce.ecommerce.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author elavincho
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    private final Logger logger = LoggerFactory.getLogger(AdministradorController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private PromoService promoService;

    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    public String home(Model model, HttpSession session, Empresa empresa) {

        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        // Pasamos los datos de la promo
        model.addAttribute("promo", promoService.findAll());

        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model, HttpSession session) {

        model.addAttribute("usuarios", usuarioService.findAll());

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model, HttpSession session) {

        model.addAttribute("ordenes", ordenService.findAll());

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "administrador/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id, HttpSession session) {

        logger.info("Id de la orden: {}", id);

        // Orden orden = ordenService.findById(id).get();
        // model.addAttribute("detalles", orden.getDetalle());

        // Le pasamos los datos de la orden para obtener los datos del cliente
        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());

        // Le pasamos la orden para obtener el total de la compra
        model.addAttribute("ordencompra", orden.get());

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "administrador/detalleorden";
    }

    @GetMapping("/bloquearUsuario/{id}")
    public String bloquearUsuario(@PathVariable Integer id, Model model, HttpSession session) {

        model.addAttribute("sesion", session.getAttribute("idusuario"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Usuario usuario = new Usuario();

        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        usuario = optionalUsuario.get();

        model.addAttribute("usuario", usuario);

        logger.info("Usuario a Editar: {}", usuario);

        return "administrador/bloquear";
    }

    @PostMapping("/bloqueo")
    public String bloqueo(Model model, Usuario usuario, @RequestParam("img") MultipartFile file,
            HttpSession session) throws IOException {

        model.addAttribute("sesion", session.getAttribute("idusuario"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Usuario u = new Usuario();
        // u =
        // usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        u = usuarioService.findById(Integer.parseInt(usuario.getId().toString())).get();

        /* cuando editamos el producto pero no cambiamos la imagen */
        if (file.isEmpty()) {
            usuario.setFoto(u.getFoto());
        } else {

            /* eliminar cuando no sea la imagen por defecto */
            if (!u.getFoto().equals("default.jpg")) {
                upload.deleteImage(u.getFoto());
            }
            String nombreFoto = upload.saveImage(file);
            usuario.setFoto(nombreFoto);
        }

        // Seteamos estos datos para que no se pierdan

        usuario.setEmail(u.getEmail());
        usuario.setPassword(u.getPassword());
        usuario.setTipo("BLOQUEADO");

        usuarioService.save(usuario);

        return "redirect:/administrador/usuarios";
    }

}
