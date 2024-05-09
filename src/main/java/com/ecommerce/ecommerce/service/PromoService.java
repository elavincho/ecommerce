/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Promo;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author elavincho
 */
public interface PromoService {
    
    public Promo save(Promo promo);
    
    public void update(Promo promo);
    
    public void delete(Integer id);

    public List<Promo> findAll();

    public Optional<Promo> get(Integer id);
    
    
    
}
