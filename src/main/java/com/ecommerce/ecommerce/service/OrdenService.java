package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Orden;
import com.ecommerce.ecommerce.model.Usuario;
import java.util.List;
import java.util.Optional;


public interface OrdenService {
    
    List<Orden> findAll();
    
    Orden save(Orden orden);
    
    String generarNumeroOrden();
    
    List<Orden> findByUsuario(Usuario usuario);
    
    Optional<Orden> findById(Integer id);

    Optional<Orden> get(Integer id);

    public void update (Orden orden);
    
}
