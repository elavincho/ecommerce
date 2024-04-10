/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.service.OrdenService;
import com.ecommerce.ecommerce.service.ProductoService;
import com.ecommerce.ecommerce.service.UsuarioService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("")
    public String home(Model model) {

        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);

        return "administrador/home";
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model model){
        
        model.addAttribute("usuarios",usuarioService.findAll());
        
        return "administrador/usuarios";
    }
    
    @GetMapping("/ordenes")
    public String ordenes(Model model){
        
        model.addAttribute("ordenes",ordenService.findAll());
        
        return "administrador/ordenes";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id){
        
        logger.info("Id de la orden: {}", id);
        
        Orden orden = ordenService.findById(id).get();
        
        model.addAttribute("detalles", orden.getDetalle());
        
        return "administrador/detalleorden";
        
    }

}
