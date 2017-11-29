/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

/**
 *
 * @author Smaïl
 */
public enum UserRole implements GrantedAuthority{

    STUDENT,
    COMPANY;
    
    @Override
    public String getAuthority() {
        return this.name();
    }
    
}
