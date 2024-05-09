/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Empresa;
import com.ecommerce.ecommerce.service.EmpresaService;
import com.ecommerce.ecommerce.service.UploadFileService;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 *
 * @author elavincho
 */
@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UploadFileService upload;

    @GetMapping("/show")
    public String show(Model model, HttpSession session) {

        List<Empresa> empresa = empresaService.findAll();
        model.addAttribute("empresa", empresa);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        return "empresa/show";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession session) {

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "empresa/alta";
    }

    @PostMapping("/save")
    public String save(Model model, Empresa empresa, @RequestParam("img") MultipartFile file, HttpSession session)
            throws IOException {

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        // imagen
        if (empresa.getId() == null) { // cuando se crea un usuario
            String imgLogo = upload.saveImage(file);
            empresa.setLogo(imgLogo);
        }

        empresaService.save(empresa);

        return "redirect:/administrador";
    }

    @GetMapping("/editEmpresa/{id}")
    public String editEmpresa(@PathVariable Integer id, Model model, HttpSession session) {

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        

        Empresa empresa = new Empresa();

        Optional<Empresa> optionalEmpresa = empresaService.findById(id);
        empresa = optionalEmpresa.get();

        model.addAttribute("empresa", empresa);

        return "empresa/edit";
    }

    @PostMapping("/update")
    public String update(Model model, Empresa empresa, @RequestParam("img") MultipartFile file, HttpSession session)
            throws IOException {

        Empresa e = new Empresa();
        e = empresaService.findById(empresa.getId()).get();
        
        /* cuando editamos el producto pero no cambiamos la imagen */
        if (file.isEmpty()) {
            empresa.setLogo(e.getLogo());
        } else {

            /* eliminar cuando no sea la imagen por defecto */
            if (!e.getLogo().equals("default.jpg")) {
                upload.deleteImage(e.getLogo());
            }
            String nombreLogo = upload.saveImage(file);
            empresa.setLogo(nombreLogo);
        }

        // Seteamos estos datos para que no se pierdan
        

        empresaService.save(empresa);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        return "redirect:/empresa/show";
    }

}
