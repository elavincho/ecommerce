/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.EmpresaService;
import com.ecommerce.ecommerce.service.ProductoService;
import com.ecommerce.ecommerce.service.UploadFileService;
import com.ecommerce.ecommerce.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author elavincho
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UploadFileService upload;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("")
    public String show(Model model, HttpSession session) {

        model.addAttribute("productos", productoService.findAll());

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "productos/show";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession session) {

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "productos/create";
    }

    // Para guardar un producto con una sola imagen
    @PostMapping("/save")
    public String save(Model model, Producto producto, @RequestParam("img") MultipartFile file, HttpSession session)
            throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);

        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        producto.setUsuario(u);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        // imagen
        if (producto.getId() == null) { // cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }

        productoService.save(producto);
        return "redirect:/productos";
    }

    //Para guardar productos con multiples imagenes
    @PostMapping("/saveUploadMultiple")
    public String saveUploadMultiple(Model model, Producto producto, @RequestParam("img") MultipartFile file,
            @RequestParam("img2") MultipartFile file2, @RequestParam("img3") MultipartFile file3,
            @RequestParam double precio, HttpSession session)
            throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);

        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        producto.setUsuario(u);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        // imagen

        if (producto.getId() == null) { // cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }

        if (producto.getId() == null) { // cuando se crea un producto
            String nombreImagen2 = upload.saveImage(file2);
            producto.setImagen2(nombreImagen2);
        }

        if (producto.getId() == null) { // cuando se crea un producto
            String nombreImagen3 = upload.saveImage(file3);
            producto.setImagen3(nombreImagen3);
        }

        double formatearPrecio = precio;
        // Formato de número
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        //System.out.println(formatoNumero.format(formatearPrecio));
        producto.setPrecioFormateado(formatoNumero.format(formatearPrecio));

        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, HttpSession session) {

        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("Producto buscado: {}, producto");

        model.addAttribute("producto", producto);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Model model, Producto producto, @RequestParam("img") MultipartFile file,
            @RequestParam("img2") MultipartFile file2, @RequestParam("img3") MultipartFile file3,
            @RequestParam double precio, HttpSession session) throws IOException {

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        Producto p = new Producto();
        p = productoService.get(producto.getId()).get();

        /* cuando editamos el producto pero no cambiamos la imagen */
        if (file.isEmpty()) {
            producto.setImagen(p.getImagen());

        } else {

            /* eliminar cuando no sea la imagen por defecto */
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }

        if (file2.isEmpty()) {
            producto.setImagen2(p.getImagen2());

        } else {
            /* eliminar cuando no sea la imagen por defecto */
            if (!p.getImagen2().equals("default.jpg")) {
                upload.deleteImage(p.getImagen2());
            }
            String nombreImagen2 = upload.saveImage(file2);
            producto.setImagen2(nombreImagen2);
        }

        if (file3.isEmpty()) {
            producto.setImagen3(p.getImagen3());
        } else {
            /* eliminar cuando no sea la imagen por defecto */
            if (!p.getImagen3().equals("default.jpg")) {
                upload.deleteImage(p.getImagen3());
            }

            String nombreImagen3 = upload.saveImage(file3);
            producto.setImagen3(nombreImagen3);
        }

        /* obtenemos el usuario del producto para que no se borre de la base de datos */
        producto.setUsuario(p.getUsuario());

        double formatearPrecio = precio;
        // Formato de número
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        producto.setPrecioFormateado(formatoNumero.format(formatearPrecio));

        productoService.update(producto);

        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Producto p = new Producto();
        p = productoService.get(id).get();

        /* eliminar cuando no sea la imagen por defecto */
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleteImage(p.getImagen());
        }

        productoService.delete(id);

        return "redirect:/productos";
    }

}
