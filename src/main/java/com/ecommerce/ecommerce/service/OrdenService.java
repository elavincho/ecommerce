/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author elavincho
 */
public interface OrdenService {
    
    List<Orden> findAll();
    
    Orden save(Orden orden);
    
    String generarNumeroOrden();
    
    List<Orden> findByUsuario(Usuario usuario);
    
    Optional<Orden> findById(Integer id);
    
}
