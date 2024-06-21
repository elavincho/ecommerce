/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.EmailSender;
import jakarta.mail.MessagingException;

/**
 *
 * @author elavi
 */
public interface IEmailService {
    
    public String sendMail(EmailSender emailSender) throws MessagingException;
}
