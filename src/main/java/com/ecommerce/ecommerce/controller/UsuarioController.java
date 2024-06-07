package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.EmpresaService;
import com.ecommerce.ecommerce.service.OrdenService;
import com.ecommerce.ecommerce.service.UploadFileService;
import com.ecommerce.ecommerce.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.*;
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
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public OrdenService ordenService;

    @Autowired
    private UploadFileService upload;

    @Autowired
    private EmpresaService empresaService;

    
    @GetMapping("/register")
    public String createAdmin(Model model) {

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "usuario/register";
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(Usuario usuario, @RequestParam("img") MultipartFile file) throws IOException {

        logger.info("Usuario Registro: {}", usuario);

        usuario.setTipo("ADMIN");

        // imagen
        if (usuario.getId() == null) { // cuando se crea un usuario
            String nombreFoto = upload.saveImage(file);
            usuario.setFoto(nombreFoto);
        }

        usuarioService.save(usuario);

        return "redirect:/";
    }

    // /usuario/registro
    @GetMapping("/registro")
    public String create(Model model) {

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario, @RequestParam("img") MultipartFile file) throws IOException {

        logger.info("Usuario Registro: {}", usuario);

        usuario.setTipo("USER");

        // imagen
        if (usuario.getId() == null) { // cuando se crea un usuario
            String nombreFoto = upload.saveImage(file);
            usuario.setFoto(nombreFoto);
        }

        usuarioService.save(usuario);

        return "redirect:/";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable Integer id, Model model, HttpSession session) {

        model.addAttribute("sesion", session.getAttribute("idusuario"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Usuario usuario = new Usuario();

        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        usuario = optionalUsuario.get();

        model.addAttribute("usuario", usuario);

        logger.info("Usuario a Editar: {}", usuario);

        return "usuario/editar";
    }

    @PostMapping("/update")
    public String update(Model model, Usuario usuario, @RequestParam("img") MultipartFile file,
            HttpSession session) throws IOException {

        model.addAttribute("sesion", session.getAttribute("idusuario"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Usuario u = new Usuario();
        u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

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
        usuario.setTipo("USER");

        usuarioService.save(usuario);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session, Model model) {

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        logger.info("Accesos : {}", usuario);

        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        // logger.info("Usuario de la bd: {}", user.get());

        // validacion momentanea
        if (user.isPresent()) {

            // Obtenemos el id del usuario para usarlo en cualquier lugar de la app
            session.setAttribute("idusuario", user.get().getId());

            // Obtenemos todos los datos del usuario para usarlo en cualquier lugar de la
            // app
            session.setAttribute("usersession", user.get());

            if (user.get().getTipo().equals("ADMIN")) {

                return "redirect:/administrador";
            } else if (user.get().getTipo().equals("USER")) {

                return "redirect:/";
            } else if (user.get().getTipo().equals("BLOQUEADO")) {
                return "redirect:/usuario/bloqueado";
            }
        } else {
            logger.info("Usuario no exsite");

            // crear un html o una alerta de que el usuario no existe
        }

        return "redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);

        model.addAttribute("ordenes", ordenes);

        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        logger.info("Id de la orden: {}", id);

        Optional<Orden> orden = ordenService.findById(id);

        model.addAttribute("detalles", orden.get().getDetalle());

        // Le pasamos la orden para obtener el total de la compra
        model.addAttribute("ordencompra", orden.get());

        // sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        return "usuario/detallecompra";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Usuario u = new Usuario();
        u = usuarioService.get(id).get();

        /* eliminar cuando no sea la imagen por defecto */
        if (!u.getFoto().equals("default.jpg")) {
            upload.deleteImage(u.getFoto());
        }

        usuarioService.delete(id);

        return "redirect:/administrador/usuarios";
    }

    @GetMapping("/bloqueado")
    public String bloqueado(HttpSession session) {
        session.removeAttribute("idusuario");
        return "usuario/bloqueado";
    }

    @GetMapping("/cambiarContrasena/{id}")
    public String cambiarContrasena(@PathVariable Integer id, Model model, HttpSession session) {

        model.addAttribute("sesion", session.getAttribute("idusuario"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Usuario usuario = new Usuario();

        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        usuario = optionalUsuario.get();

        model.addAttribute("usuario", usuario);

        logger.info("Usuario a Editar: {}", usuario);

        return "usuario/cambiarContrasena";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(Model model, Usuario usuario, @RequestParam String password1,
            @RequestParam String password2, @RequestParam String password3, HttpSession session)
            throws IOException {

        model.addAttribute("sesion", session.getAttribute("idusuario"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Usuario u = new Usuario();
        u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        // Verificamos el password del usuario y la cambiamos
        if (u.getPassword().equals(password1)) {
            if (password2.equals(password3)) {
                usuario.setPassword(password3);
            } else {
                return "usuario/cambiarContrasena";
            }
        } else {
            return "redirect:/";
        }

        // Seteamos estos datos para que no se pierdan
        usuario.setEmail(u.getEmail());
        usuario.setTipo("USER");
        usuario.setFoto(u.getFoto());
        usuario.setNombre(u.getNombre());
        usuario.setApellido(u.getApellido());
        usuario.setDocumento(u.getDocumento());
        usuario.setTelefono(u.getTelefono());
        usuario.setDireccion(u.getDireccion());
        usuario.setAltura(u.getAltura());
        usuario.setPiso(u.getPiso());
        usuario.setDepto(u.getDepto());
        usuario.setLocalidad(u.getLocalidad());
        usuario.setProvincia(u.getProvincia());

        usuarioService.save(usuario);

        return "redirect:/";
    }

}
