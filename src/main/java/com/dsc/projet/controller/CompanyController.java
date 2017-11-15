/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.controller;

import com.dsc.projet.model.Domain;
import com.dsc.projet.model.DomainRepository;
import com.dsc.projet.model.CompanyRepository;
import com.dsc.projet.model.Company;
import com.dsc.projet.model.Image;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author snianfo
 */
@Controller
public class CompanyController {
    @Inject
    CompanyRepository repoCom;
    
    @Inject
    DomainRepository repoDom;
    
    @RequestMapping("/my_company")
    public String companyPage(Model m){
       Domain d = new Domain("Informatique");
       repoDom.save(d);
        List<Domain> l = new ArrayList<>();
        l.add(d);
        Company c1 = new Company("Excilis", "Paris",
                "Excilys regroupe des passionnés d'informatique qui développent des applications de haut niveau pour leurs clients",
                l);
        repoCom.save(c1);
        m.addAttribute("company", c1);
        return "homeCompany";
    }
    
    @RequestMapping("/visit_company")
    public String visitCompany(Model m){
       Domain d = new Domain("Technologies et Services de l'Information");
       repoDom.save(d);
        List<Domain> l = new ArrayList<>();
        l.add(d);
        
        Company c1 = new Company("Excilis", "Paris",
                "Excilys regroupe des passionnés d'informatique qui développent des applications de haut niveau pour leurs clients",
                l);
        c1.img = new Image("/images/excilis.png","Logo Excilis");
        repoCom.save(c1);
        m.addAttribute("company", c1);
        return "visit_company";
    }
    
    @RequestMapping("/profil_company")
    public String profilCompany(Model m){
       Domain d = new Domain("Technologies et Services de l'Information");
       repoDom.save(d);
        List<Domain> l = new ArrayList<>();
        l.add(d);
        
        Company c1 = new Company("Excilis", "Paris",
                "Excilys regroupe des passionnés d'informatique qui développent des applications de haut niveau pour leurs clients",
                l);
        c1.img = new Image("/images/excilis.png","Logo Excilis");
        repoCom.save(c1);
        m.addAttribute("company", c1);
        return "profil_company";
    }
    
}
