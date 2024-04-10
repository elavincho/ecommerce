/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service.ServiceImpl;

/*
import com.ecommerce.ecommerce.controller.AdministradorController;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
*/

/**
 *
 * @author elavincho
 */
//@Service
public class UserDetailServiceImpl {
/*
    public class UserDetailServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    HttpSession session;

    private final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Este es el username");
        
        Optional<Usuario> optionalUser = usuarioService.findByEmail(username);

        if (optionalUser.isPresent()) {
            
            logger.info("Este es el id del usuario {}", optionalUser.get().getId());
            
            session.setAttribute("idusuario", optionalUser.get().getId());
            Usuario usuario = optionalUser.get();
        
            return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPassword())).roles(usuario.getTipo()).build();
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
       
    }
*/
}
