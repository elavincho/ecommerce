package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.EmailSender;
import com.ecommerce.ecommerce.service.EmpresaService;
import com.ecommerce.ecommerce.service.IEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author elavincho
 */
// @RestController
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/recuperarContrasena")
    public String recuperarContrasena(Model model) {

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        // model.addAllAttributes("emailSender", new EmailSender);
        return "usuario/recuperarContrasena";
    }

    @PostMapping("/send")
    private String sendEmail(@RequestParam String destinatario, EmailSender emailSender) throws MessagingException {
        emailService.sendMail(emailSender);
        return "redirect:/email/emailEnviado";
    }

    @GetMapping("/emailEnviado")
    public String emailEnviado(Model model) {

        // Pasamos todos los datos de la empresa
        model.addAttribute("empresa", empresaService.findAll());

        return "usuario/emailEnviado";
    }

    // Metodo para utilizar con una rest controller
    // @PostMapping("/send-email")
    // private ResponseEntity<String> sendEmail(@RequestBody EmailSender
    // emailSender) throws MessagingException {
    // emailService.sendMail(emailSender);
    // return new ResponseEntity<>("Correo enviado exitosamente", HttpStatus.OK);
    // }

}
