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
import com.dsc.projet.model.CompanyProjectRepository;
import com.dsc.projet.model.Image;
import com.dsc.projet.model.Project;
import com.dsc.projet.model.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Inject CompanyProjectRepository projectComp;
    
    @RequestMapping("/data")
    public String data(){
        repoDom.save(new Domain("Réseaux Informatique"));
        repoDom.save(new Domain("Grande Distribution"));
        repoDom.save(new Domain("Travaux Publics"));
        repoDom.save(new Domain("Développement Informatique"));
        repoDom.save(new Domain("Paiement Electronique"));
        repoDom.save(new Domain("Sécurité Sociale"));
        repoDom.save(new Domain("Finance"));
        repoDom.save(new Domain("Assurance"));
        
        return "redirect:/profil_company";
    }
       
    @RequestMapping("/home_company")
    public String companyPage(Model m, HttpServletRequest request){
        HttpSession session = request.getSession();
        m.addAttribute("compete", projectComp.findAll());
        m.addAttribute("company", repoCom.findByUsername((String) session.getAttribute("username")));
        m.addAttribute("images", "/images/");
        m.addAttribute("type", (String) session.getAttribute("type"));
        return "home_company";
    }
    
    @RequestMapping("/visit_company")
    public String visitCompany(Model m){
//       long x;
//       x=1;
//       m.addAttribute("company", repoCom.findOne(x));
        return "visit_company";
    }
    
    @RequestMapping("/profil_company")
    public String profilCompany(Model m, HttpServletRequest request){
       HttpSession session = request.getSession();
       m.addAttribute("company", repoCom.findByUsername((String) session.getAttribute("username")));
        m.addAttribute("images", "/images/");
        m.addAttribute("type", (String) session.getAttribute("type"));
       return "profil_company";
    }
    
    @PostMapping("/changeImgComp")
    public String handleFileUpload(@RequestParam("photo") MultipartFile file,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
         if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/monProfil";
        }

        try {

            // Geting file
            byte[] bytes = file.getBytes();
            System.out.println("type file :"+ file.getContentType());
            String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'), file.getOriginalFilename().length());
            String newNom = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'));
            //create path to store file
            System.out.println("type "+type);
            String username = repoCom.findByUsername((String) session.getAttribute("username")).username;
            Path path = Paths.get("src/main/resources/static/images/" + username+type);
            //test if the path exist
            Files.deleteIfExists(path);
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("type",type);
            session.setAttribute("type", type);
            Company st = repoCom.findByUsername((String) session.getAttribute("username"));
            System.out.println("file added is :"+username+type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        
        return "redirect:/profil_company";
    }
    
}
