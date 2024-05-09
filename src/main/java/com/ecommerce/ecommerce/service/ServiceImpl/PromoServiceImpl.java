/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service.ServiceImpl;

import com.ecommerce.ecommerce.model.Promo;
import com.ecommerce.ecommerce.rerpository.PromoRepository;
import com.ecommerce.ecommerce.service.PromoService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author elavincho
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoRepository promoRepository;

    @Override
    public Promo save(Promo promo) {

        return promoRepository.save(promo);
    }

    @Override
    public void update(Promo promo) {

        promoRepository.save(promo);
    }

    @Override
    public void delete(Integer id) {

        promoRepository.deleteById(id);
    }

    @Override
    public List<Promo> findAll() {

        return promoRepository.findAll();
    }

    @Override
    public Optional<Promo> get(Integer id) {

        return promoRepository.findById(id);
    }

}
