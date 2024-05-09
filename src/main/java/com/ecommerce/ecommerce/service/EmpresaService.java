/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;



import com.ecommerce.ecommerce.model.Empresa;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author elavincho
 */
public interface EmpresaService {
    
    public Empresa save(Empresa empresa);
    
    public void update(Empresa empresa);

    public List<Empresa> findAll();

    public Optional<Empresa> findById(Integer id);
    
}
