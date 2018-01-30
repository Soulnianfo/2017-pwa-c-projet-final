/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.servlet.http.Part;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author snianfo
 */
@Data 
@Entity
public class Student{
    @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    
    //@OneToOne
    String name;
    
    //@OneToOne
    String firstName;
    
   // @OneToOne
    String dateSt;
    
    //@OneToOne
    String passWord;
    
    //@OneToOne
     String username;
    
    //@OneToOne
    String email;

   @ManyToMany(fetch = FetchType.EAGER)
   public List<Student> followers = new ArrayList<>();
    
   @ManyToMany(fetch = FetchType.EAGER,mappedBy="students")
   
   public List<Skills> skills = new ArrayList<>();
   
    @OneToMany(fetch = FetchType.EAGER,mappedBy="author")
    
    public List <Project> myProjects = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy="students")
    List <Formation> myFormations = new ArrayList<>();
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<UserRole> roles;
    public Student(){   
        this.roles = new HashSet<>();
        this.roles.add(UserRole.STUDENT);
    }
    public Student(String name, String firstName,String date, String passWord,String speudo,String email){
        this.roles = new HashSet<>();
         this.roles.add(UserRole.STUDENT);
        this.name = name;
        this.firstName = firstName;
        this.dateSt = date;
        this.passWord = passWord;
        this.username = speudo;
        this.email = email;
    }
    public void addproject(Project p){
        myProjects.add(p);
    }
    void setDerivedPassword(String codedPassword) {
        this.passWord = codedPassword;
    }
    String getDerivedPassword() {
       return  this.passWord;
    }
    
    public void setRoles(Set<UserRole> roles){
        this.roles = roles;
    }
    public Set<UserRole> getRoles(Set<UserRole> roles){
        return roles;
    }
    public String getUsername(){
        return username;
    }
    
    public void changeImg(Image img){
       // this.img = img;
    }
    public String getNomFichier( Part part ) {

        /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            /* Recherche de l'éventuelle présence du paramètre "filename". */
            if ( contentDisposition.trim().startsWith("filename") ) {
                /* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }
    
    public void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        int TAILLE_TAMPON = 10240;
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            
            entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),  TAILLE_TAMPON );
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
            sortie.close();
             entree.close();
       } catch(IOException ignore ){
           
        }
    }
    
    
    
}
