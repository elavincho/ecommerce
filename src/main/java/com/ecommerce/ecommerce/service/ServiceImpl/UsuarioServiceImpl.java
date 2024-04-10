/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service.ServiceImpl;

import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.rerpository.UsuarioRepository;
import com.ecommerce.ecommerce.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author elavincho
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findById(Integer id) {

        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {

        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        
        return usuarioRepository.findAll();
    }

}
