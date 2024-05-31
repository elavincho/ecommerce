/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Promo;
import com.ecommerce.ecommerce.service.EmpresaService;
import com.ecommerce.ecommerce.service.PromoService;
import com.ecommerce.ecommerce.service.UploadFileService;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
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

/**
 *
 * @author elavincho
 */

@Controller
@RequestMapping("/promos")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @Autowired
    private UploadFileService upload;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/show")
    public String show(Model model, HttpSession session) {

        List<Promo> promo = promoService.findAll();
        model.addAttribute("promo", promo);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "promos/show";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession session) {

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());
        // Pasamos todos los datos de la promo
        model.addAttribute("promo", promoService.findAll());

        return "promos/create";
    }

    @PostMapping("/saveUploadMultiple")
    public String saveUploadMultiple(Model model, Promo promo, @RequestParam("img") MultipartFile file,
            @RequestParam("img2") MultipartFile file2, @RequestParam("img3") MultipartFile file3,
            // @RequestParam("img4") MultipartFile file4, @RequestParam("img5")
            // MultipartFile file5,
            // @RequestParam("img6") MultipartFile file6, @RequestParam("img7")
            // MultipartFile file7,
            // @RequestParam("img8") MultipartFile file8, @RequestParam("img9")
            // MultipartFile file9,
            HttpSession session)
            throws IOException {

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("promo", promoService.findAll());

        // imagen

        if (promo.getId() == null) { // cuando se crea un producto
            String nombrePromo = upload.saveImage(file);
            promo.setPromo(nombrePromo);
        }

        if (promo.getId() == null) { // cuando se crea un producto
            String nombrePromo2 = upload.saveImage(file2);
            promo.setPromo2(nombrePromo2);
        }

        if (promo.getId() == null) { // cuando se crea un producto
            String nombrePromo3 = upload.saveImage(file3);
            promo.setPromo3(nombrePromo3);
        }

        // if (promo.getId() == null) { // cuando se crea un producto
        // String nombrePromo4 = upload.saveImage(file4);
        // promo.setPromo4(nombrePromo4);
        // }

        // if (promo.getId() == null) { // cuando se crea un producto
        // String nombrePromo5 = upload.saveImage(file5);
        // promo.setPromo5(nombrePromo5);
        // }

        // if (promo.getId() == null) { // cuando se crea un producto
        // String nombrePromo6 = upload.saveImage(file6);
        // promo.setPromo6(nombrePromo6);
        // }

        // if (promo.getId() == null) { // cuando se crea un producto
        // String nombrePromo7 = upload.saveImage(file7);
        // promo.setPromo7(nombrePromo7);
        // }

        // if (promo.getId() == null) { // cuando se crea un producto
        // String nombrePromo8 = upload.saveImage(file8);
        // promo.setPromo8(nombrePromo8);
        // }

        // if (promo.getId() == null) { // cuando se crea un producto
        // String nombrePromo9 = upload.saveImage(file9);
        // promo.setPromo9(nombrePromo9);
        // }

        promoService.save(promo);
        return "redirect:/promos/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, HttpSession session) {

        Promo promo = new Promo();
        Optional<Promo> optionalPromo = promoService.get(id);
        promo = optionalPromo.get();

        model.addAttribute("promo", promo);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "promos/edit";
    }

    @PostMapping("/update")
    public String update(Model model, Promo promo, @RequestParam("img") MultipartFile file,
            @RequestParam("img2") MultipartFile file2, @RequestParam("img3") MultipartFile file3,
            // @RequestParam("img4") MultipartFile file4, @RequestParam("img5")
            // MultipartFile file5,
            // @RequestParam("img6") MultipartFile file6, @RequestParam("img7")
            // MultipartFile file7,
            // @RequestParam("img8") MultipartFile file8, @RequestParam("img9")
            // MultipartFile file9,
            HttpSession session) throws IOException {

        Promo p = new Promo();
        p = promoService.get(promo.getId()).get();

        /* cuando editamos el producto pero no cambiamos la imagen */
        if (file.isEmpty()) {
            promo.setPromo(p.getPromo());

        } else {

            /* eliminar cuando no sea la imagen por defecto */
            if (!p.getPromo().equals("default.jpg")) {
                upload.deleteImage(p.getPromo());
            }
            String nombrePromo = upload.saveImage(file);
            promo.setPromo(nombrePromo);
        }

        if (file2.isEmpty()) {
            promo.setPromo2(p.getPromo2());

        } else {

            /* eliminar cuando no sea la imagen por defecto */
            if (!p.getPromo2().equals("default.jpg")) {
                upload.deleteImage(p.getPromo2());
            }
            String nombrePromo2 = upload.saveImage(file2);
            promo.setPromo2(nombrePromo2);
        }

        if (file3.isEmpty()) {
            promo.setPromo3(p.getPromo3());

        } else {

            /* eliminar cuando no sea la imagen por defecto */
            if (!p.getPromo3().equals("default.jpg")) {
                upload.deleteImage(p.getPromo3());
            }
            String nombrePromo3 = upload.saveImage(file3);
            promo.setPromo3(nombrePromo3);
        }

        // if (file4.isEmpty()) {
        // promo.setPromo4(p.getPromo4());

        // } else {

        // /* eliminar cuando no sea la imagen por defecto */
        // if (!p.getPromo4().equals("default.jpg")) {
        // upload.deleteImage(p.getPromo4());
        // }
        // String nombrePromo4 = upload.saveImage(file4);
        // promo.setPromo4(nombrePromo4);
        // }

        // if (file5.isEmpty()) {
        // promo.setPromo5(p.getPromo5());

        // } else {

        // /* eliminar cuando no sea la imagen por defecto */
        // if (!p.getPromo5().equals("default.jpg")) {
        // upload.deleteImage(p.getPromo5());
        // }
        // String nombrePromo5 = upload.saveImage(file5);
        // promo.setPromo5(nombrePromo5);
        // }

        // if (file6.isEmpty()) {
        // promo.setPromo6(p.getPromo6());

        // } else {

        // /* eliminar cuando no sea la imagen por defecto */
        // if (!p.getPromo6().equals("default.jpg")) {
        // upload.deleteImage(p.getPromo6());
        // }
        // String nombrePromo6 = upload.saveImage(file6);
        // promo.setPromo6(nombrePromo6);
        // }

        // if (file7.isEmpty()) {
        // promo.setPromo7(p.getPromo7());

        // } else {

        // /* eliminar cuando no sea la imagen por defecto */
        // if (!p.getPromo7().equals("default.jpg")) {
        // upload.deleteImage(p.getPromo7());
        // }
        // String nombrePromo7 = upload.saveImage(file7);
        // promo.setPromo7(nombrePromo7);
        // }

        // if (file8.isEmpty()) {
        // promo.setPromo8(p.getPromo8());

        // } else {

        // /* eliminar cuando no sea la imagen por defecto */
        // if (!p.getPromo8().equals("default.jpg")) {
        // upload.deleteImage(p.getPromo8());
        // }
        // String nombrePromo8 = upload.saveImage(file8);
        // promo.setPromo8(nombrePromo8);
        // }

        // if (file9.isEmpty()) {
        // promo.setPromo9(p.getPromo9());

        // } else {

        // /* eliminar cuando no sea la imagen por defecto */
        // if (!p.getPromo9().equals("default.jpg")) {
        // upload.deleteImage(p.getPromo9());
        // }
        // String nombrePromo9 = upload.saveImage(file9);
        // promo.setPromo9(nombrePromo9);
        // }

        //guardamos la ubicacion para que no se borre de la bd
        promo.setUbicacion(p.getUbicacion());

        promoService.update(promo);

        // Con esto obtenemos todos los datos del usuario
        model.addAttribute("usuario", session.getAttribute("usersession"));
        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "redirect:/promos/show";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Promo p = new Promo();
        p = promoService.get(id).get();

        /* eliminar cuando no sea la imagen por defecto */
        if (!p.getPromo().equals("default.jpg")) {
            upload.deleteImage(p.getPromo());
        }

        promoService.delete(id);

        return "redirect:/promos/show";
    }

}
