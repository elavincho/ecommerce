/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author elavincho
 */
public interface UsuarioService {

    Optional<Usuario> findById(Integer id);

    Usuario save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);
    
    List<Usuario> findAll();

    public void delete(Integer id);

    public Optional<Usuario> get(Integer id); //Optional nos da la opcion de poder validar si el objeto que llamamos de la base de datos existe o no.

}
