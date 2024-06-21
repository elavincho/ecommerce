package com.ecommerce.ecommerce.service.ServiceImpl;

import com.ecommerce.ecommerce.model.EmailSender;
import com.ecommerce.ecommerce.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author elavincho
 */
@Service
public class EmailServiceImpl implements IEmailService {

    //Esto tambien es inyeccion de dependencias
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public String sendMail(EmailSender emailSender) throws MessagingException {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailSender.getDestinatario());
            helper.setSubject(emailSender.getAsunto());
            //utilizamos un set text para añadir la imagen
            helper.setText("<img src='cid:imagen'/>", true);

            // Si quisieramos enviar un texto plano
            // helper.setText("Colocamos el texto que queremos o emailSender.getMensaje()");
            Context context = new Context();

            context.setVariable("mensaje", emailSender.getMensaje());
            String contentHTML = templateEngine.process("usuario/email", context);

            helper.setText(contentHTML, true);

            //Agregamos la imagen en linea
            ClassPathResource imageResource = new ClassPathResource("/static/img/inicio-sesion.png");
            helper.addInline("imagen", imageResource);

            javaMailSender.send(message);
            javaMailSender.send(helper.getMimeMessage());

            return "Email enviado exitosamente";
        } catch (Exception e) {
            //throw new RuntimeException("Error al enviar el mail " + e.getMessage(), e);
            return "Error al enviar el mail";
        }
    }
}
