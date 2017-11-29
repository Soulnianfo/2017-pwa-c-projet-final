
package com.dsc.projet.controller;

import com.dsc.projet.model.Project;
import com.dsc.projet.model.ProjectRepos;
import com.dsc.projet.model.SkillsRepository;
import com.dsc.projet.model.Skills;
import com.dsc.projet.model.Student;
import com.dsc.projet.model.StudentRepos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author n
 */
@Controller
public class ControllerStudent {
    ArrayList<Project> listProject = new ArrayList<>();
    
    @Inject
    ProjectRepos repos;
    @Inject StudentRepos studRepos;
    @Inject SkillsRepository skillRepo;
    @Inject ProjectRepos projectComp;
    
   
    Student student1;
    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        
        model.addAttribute("projects", repos.findAll());
        model.addAttribute("compete", projectComp.findAll());
        model.addAttribute("student", studRepos.findByUsername((String) session.getAttribute("username")));
        
        model.addAttribute("images", "/images/");
        model.addAttribute("type", (String) session.getAttribute("type"));
       
        return "homeStudent";
    }
   
    @RequestMapping("/projectDetail")
    public String detailProject(Model model, @RequestParam("cid") Long id){
       
        model.addAttribute("detail", repos.findOne(id));
        
        return "projectDetails";
    }
    @RequestMapping("/monProfil")
    public String profil(Model model, HttpServletRequest request){
         HttpSession session = request.getSession();
         Project p = new Project("monProject", "collaboration", "10", "2017", "12", "2017",
               studRepos.findByUsername((String) session.getAttribute("username")));
       studRepos.findByUsername((String) session.getAttribute("username")).myProjects.
               add(p
               );
       ///repos.save(p);
        System.out.println("project save: ok");
        model.addAttribute("student", studRepos.findByUsername((String) session.getAttribute("username")));
       
        model.addAttribute("skills", skillRepo.findAll());
        model.addAttribute("skill",new Skills());
        model.addAttribute("images", "/images/");
        model.addAttribute("type", (String) session.getAttribute("type"));
        return "profil";
    }
    
    @RequestMapping("/saveProject")
    public String saveProjetc(Project p){
        repos.save(p);
        return "redirect: /monProfil";   
    }
    @RequestMapping("/profilAuthor")
    public String profilAuthor(Model m, @RequestParam("id") Long id, HttpServletRequest request){
        HttpSession session = request.getSession();
        m.addAttribute("student", studRepos.findByUsername((String) session.getAttribute("username")));
        m.addAttribute("author", studRepos.findOne(id));
        return "profilAuthor";
    }
    
    @RequestMapping("/addFollower")
    public String addFollower(Model m, @RequestParam("id") Long id, HttpServletRequest request){
        HttpSession session = request.getSession();
        boolean add;
        add = studRepos.findByUsername((String) session.getAttribute("username")).followers.add(studRepos.findOne(id));
        
        m.addAttribute("student", studRepos.findByUsername((String) session.getAttribute("username")));
        m.addAttribute("author", studRepos.findOne(id));
        if(add){
            
            return "profilAuthor?ok=1";
        }
        return "redirect:/profilAuthor?ok=0";
    }
    
    @PostMapping("/changeImg")
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
            String username = studRepos.findByUsername((String) session.getAttribute("username")).username;
            Path path = Paths.get("src/main/resources/static/images/" + username+type);
            //test if the path exist
            Files.deleteIfExists(path);
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("type",type);
            session.setAttribute("type", type);
            Student st = studRepos.findByUsername((String) session.getAttribute("username"));
            System.out.println("file added is :"+username+type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        
        return "redirect:/monProfil";
    }
   
}
