/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.Model;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author snianfo
 */
public interface StudentRepos extends CrudRepository<Student, Long>{
    
}
