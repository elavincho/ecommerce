/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.rerpository;

import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author elavincho
 */
@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    
    List<Orden> findByUsuario(Usuario usuario);
    
}
