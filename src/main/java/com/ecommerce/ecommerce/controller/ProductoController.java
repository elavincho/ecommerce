/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.ProductoService;
import com.ecommerce.ecommerce.service.UploadFileService;
import com.ecommerce.ecommerce.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
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

    @GetMapping("")
    public String show(Model model) {

        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session)
            throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);

        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        producto.setUsuario(u);

        // imagen
        if (producto.getId() == null) { // cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }

        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("Producto buscado: {}, producto");

        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

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
        /* obtenemos el usuario del producto para que no se borre de la base de datos */
        producto.setUsuario(p.getUsuario());

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
