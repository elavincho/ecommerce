/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author elavincho
 */
public interface ProductoService {
    
    public Producto save(Producto producto);
    
    public Optional<Producto> get(Integer id); //Optional nos da la opcion de poder validar si el objeto que llamamos de la base de datos existe o no.
    
    public void update(Producto producto);
    
    public void delete(Integer id);
    
    public List<Producto> findAll();
    
}
