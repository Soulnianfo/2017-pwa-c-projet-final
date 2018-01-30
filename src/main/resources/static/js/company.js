/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var idCom = document.getElementById("idd").textContent,
    usn = document.getElementById("usn").textContent,  
    psw = document.getElementById("psw").textContent,
    mail = document.getElementById("mail").textContent,
    comp_name = document.getElementById("comp_name").textContent,
    comp_city = document.getElementById("comp_city").textContent,
    comp_descrip = document.getElementById("comp_descrip").textContent;
    
    var modal = document.getElementById("modal_3");

var monvue = new Vue({
    el: '#bodyy',
    data: {
      listDom:[],  
      projects:[],
      compets:[],
      comp_dom:[],
      showImg : false,
      hidenImg: true,
      compa: {username: usn, pswd: psw, email: mail, name: comp_name, 
                city: comp_city, description: comp_descrip},
   
      newProject: {title: 'Donner un Titre', descrip:'Donner une Description',
          creator:"http://localhost:8080/api/companies/"+idCom,beginMonth: 'Janvier',
          beginYear:'2015',endMonth:'Mai',endYear : '2017'},
      
      modProject: {title: 'Donner un titre', descrip:'Donner une Description',
          creator:"http://localhost:8080/api/companies/"+idCom,beginMonth: 'Janvier',
          beginYear:'2015',endMonth:'Mai',endYear : '2017'},

      newCompet: {title: 'Donner un Titre', descrip:'Donner une Description',
          creator:"http://localhost:8080/api/companies/"+idCom,beginMonth: 'Janvier',
          beginYear:'2015',endMonth:'Mai',endYear : '2017'},
      modCompet: {title: 'Donner un Titre', descrip:'Donner une Description',
          creator:"http://localhost:8080/api/companies/"+idCom,beginMonth: 'Janvier',
          beginYear:'2015',endMonth:'Mai',endYear : '2017'}
    },
    mounted() {
        this.company = this.$resource("http://localhost:8080/api/companies/"+idCom);
        this.listDom = this.$resource("http://localhost:8080/api/domains/");
        this.listPro = this.$resource("http://localhost:8080/api/companies/{id}/projects");
        this.listComp = this.$resource("http://localhost:8080/api/companies/{id}/competitions");
        this.pro = this.$resource("http://localhost:8080/api/companyProjects/{id}");
        this.proMan;
        this.comp = this.$resource("http://localhost:8080/api/competitions");
        this.compMan;
        this.fetchProjectFromSpring();
        this.fetchCompetFromSpring();
        this.fetchDomainFromSpring();
    },
    methods: {
        modProAddress(add,idx){
        
          this.proMan = this.$resource(add);
          
          this.modProject.title = this.projects[idx].title;
          this.modProject.descrip = this.projects[idx].descrip;
          this.modProject.beginMonth = this.projects[idx].beginMonth;
          this.modProject.beginYear = this.projects[idx].beginYear;
          this.modProject.endMonth = this.projects[idx].endMonth;
          this.modProject.endYear = this.projects[idx].endYear;
          
        },
        modCompAddress(adc,idc){
        
          this.compMan = this.$resource(adc);
          
          this.modCompet.title = this.compets[idc].title;
          this.modCompet.descrip = this.compets[idc].descrip;
          this.modCompet.beginMonth = this.compets[idc].beginMonth;
          this.modCompet.beginYear = this.compets[idc].beginYear;
          this.modCompet.endMonth = this.compets[idc].endMonth;
          this.modCompet.endYear = this.compets[idc].endYear;
        },
        addCompet(){
            var newC = {
          ...this.newCompet,
          encours: true
        };
        this.compets = [...this.compets, newC]; // add a new Project
        this.comp.save(this.newCompet).then(//save the project in the list of all projects 
          resp => { // if the response is OK
            var newneww = resp.body;
            this.compets = this.compets.map(h => h!==newC ? h : newneww);
          },
          resp => { // ERREUR
            this.compets = this.compets.filter(h => h!==newC);
            console.log;
          }
          );
        },
        
        modifyCompet(){
            this.compMan.update(this.modCompet).then(
            resp => {
                alert("OK");
                console.log;
            },
            resp => {
                alert("Non OK");
                console.log;
            });
        },

        delCompet(addr) {
            this.compMan = this.$resource(addr);   
            this.compMan.delete().then(
              resp => {
                  this.fetchCompetFromSpring();
              },
              console.log
            );   
        },

      addProject() {  
        var newP = {
          ...this.newProject,
          encours: true
        };
        this.projects = [...this.projects, newP]; // add a new Project
        this.pro.save(this.newProject).then(//save the project in the list of all projects 
          resp => { // if the response is OK
            var newnew = resp.body;
            this.projects = this.projects.map(h => h!==newP ? h : newnew);
          },
          resp => { // ERREUR
            this.projects = this.projects.filter(h => h!==newP);
          }
          );
        },
        
        modifyProject(){
            this.proMan.update(this.modProject).then(
            resp => {
                alert("OK");
                console.log;
            },
            resp => {
                alert("Non OK");
                console.log;
            });
        },

        delProject(addr) {
            this.proMan = this.$resource(addr);   
            this.proMan.delete().then(
              resp => {
                  this.fetchProjectFromSpring();
              },
              console.log
            );   
        },
        
        modCompany() {           
            this.company.update(this.compa).then(
              resp => {
                  alert("OK");
                  console.log;
              },
              resp => {
                  alert("Non OK");
                  console.log;
              });
        },

        fetchCompetFromSpring() {
            this.listComp.get({id: idCom}).then(
              resp => this.compets = resp.body._embedded.competitions, // OK
              console.log // ERREUR
            );
        },
        fetchProjectFromSpring() {
            this.listPro.get({id: idCom}).then(
              resp => this.projects = resp.body._embedded.companyProjects, // OK
              console.log // ERREUR
            );
        },
        fetchDomainFromSpring() {
            this.listDom.get().then(
              resp => this.listDom = resp.body._embedded.domains, // OK
              console.log // ERREUR
            );
        },
         ShowImg :function() {
                         this.showImg = true ;
                     },
        HiddenImg : function() {
                   this.showImg = false ;
                    }
    },
    computed: {
      
    }
  });
  