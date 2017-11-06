/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.Controller;



import com.dsc.projet.model.Company;
import com.dsc.projet.model.CompanyRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Mycontroller {
 /*   @Inject 
    CompanyRepository rep;*/
   
@RequestMapping("/")
   public String Welcome(Model m){
    Company v;
       m.addAttribute("comp", v =new Company("casino","Paris", "casi@gmail", "123785", "coucou"));
       System.out.println("did");
        return "welcome";
   }
 
@RequestMapping("/addCompany")
   public String addCompany(Company v){
        System.out.println(v.name );
        System.out.println(v.mail );
        System.out.println(v.password );
        System.out.println(v.description );
      /* rep.save(v);
*/            return "homeStudent";
   }  
    
}
