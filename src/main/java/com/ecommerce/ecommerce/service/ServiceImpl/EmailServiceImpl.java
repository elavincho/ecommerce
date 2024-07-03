package com.ecommerce.ecommerce.service.ServiceImpl;

import com.ecommerce.ecommerce.model.EmailSender;
import com.ecommerce.ecommerce.model.Empresa;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.EmpresaService;
import com.ecommerce.ecommerce.service.IEmailService;
import com.ecommerce.ecommerce.service.UsuarioService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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

    // Esto tambien es inyeccion de dependencias
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaService empresaService;

    @Override
    public String sendMail(EmailSender emailSender) throws MessagingException {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailSender.getDestinatario());
            helper.setSubject(emailSender.getAsunto());
            // utilizamos un set text para añadir la imagen
            helper.setText("<img src='cid:imagen'/>", true);

            // Si quisieramos enviar un texto plano
            // helper.setText("Colocamos el texto que queremos o emailSender.getMensaje()");
            Context context = new Context();

            context.setVariable("mensaje", emailSender.getMensaje());

            // Obtenemos el usuario para pasar los datos
            Usuario usuario = new Usuario();
            Optional<Usuario> optionalUsuario =
            usuarioService.findByEmail(emailSender.getDestinatario());
            usuario = optionalUsuario.get();

            //Convertimos el id en string para poder pasarlo al link
            String idString = usuario.getId().toString();
            context.setVariable("id", idString);
            context.setVariable("nombre", usuario.getNombre());

            // Pasamos el nombre de la empresa
            Empresa empresa = new Empresa();
            Optional<Empresa> optionalEmpresa = empresaService.findById(7);
            empresa = optionalEmpresa.get();
            context.setVariable("empresa", empresa.getRazonSocial());

            context.setVariable("email", emailSender.getDestinatario());

            String contentHTML = templateEngine.process("usuario/email", context);

            helper.setText(contentHTML, true);

            // Agregamos la imagen en linea
            ClassPathResource imageResource = new ClassPathResource("/static/img/inicio-sesion.png");
            helper.addInline("imagen", imageResource);

            javaMailSender.send(message);
            javaMailSender.send(helper.getMimeMessage());

            return "Email enviado exitosamente";
        } catch (Exception e) {
            // throw new RuntimeException("Error al enviar el mail " + e.getMessage(), e);
            return "Error al enviar el mail";
        }
    }
}
