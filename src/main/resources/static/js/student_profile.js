
var idSt = document.getElementById("idSt").textContent;
var monvue = new Vue({
    el: '#bodyy',
    data: {
      unMessage: 'Suivre',
      projects:[],
      compets:[],
      show: true,
      show1: true,
      show2: false,
      showImg : false,
      hidenImg: true,
      img: "adel.jpg",
      newSkill: {label: "Spring",
      students: ["http://localhost:8080/api/students/"+idSt]
       },
      skills: [],
      projects: [],
      nouv: {name: "name",descrip: "descrip"},
      formations: [],
      newFormation: {
          domain: 'domain',
          name: 'DSC',
          dateBegin: '01/09/2017',
          dateEnd: '30/03/2018',
         students:["http://localhost:8080/api/students/"+idSt]
          
      },
      newProject: {title: 'Donner un Titre', descrip:'Donner une Description',
          author:"http://localhost:8080/api/students/"+idSt,beginMonth: 'Janvier',
          beginYear:'2015',endMonth:'Mai',endYear : '2017'}
    },
    mounted() {
        this.listPro = this.$resource("http://localhost:8080/api/students{/id}/myProjects");
        this.pro = this.$resource("http://localhost:8080/api/projects");
        this.form = this.$resource("http://localhost:8080/api/students/"+idSt+"/myFormations");
        this.forms = this.$resource("http://localhost:8080/api/formations");
        
        this.skillStud = this.$resource("http://localhost:8080/api/students/"+idSt+"/skills");
        this.skils = this.$resource("http://localhost:8080/api/skillses");
        this.fetchProjectFromSpring();
    },
    methods: {
      addProject() {
        var newP = {
          ...this.newProject,
          encours:true
        };
        this.projects = [...this.projects, newP]; // add a new Project
        this.pro.save(this.newProject).then(//save the project in the list of all projects 
          resp => { // if the response is OK
//            alert("OK");  
            var newnew = resp.body;
            this.projects = this.projects.map(h => h!==newP ? h : newnew);
          },
          resp => { // ERREUR
//              alert("Non OK"); 
            this.projects = this.projects.filter(h => h!==newP);
            console.log;
          }
          );
        },

         delProject() {
        this.pro.delete({id: idSt}).then(
          console.log,
          console.log
            );
        },
        fetchProjectFromSpring() {
            
       this.listPro.get({id: idSt}).then(
              resp => this.projects = resp.body._embedded.projects, // OK
              console.log // ERREUR
            );
        this.form.get().then(
              
            response => {this.formations = response.body._embedded.formations;},
            response => {console.log();}
        );
        this.skillStud.get().then(
            resp => {
                this.skills = resp.body._embedded.skillses;
                console.log;
            }, resp =>{console.log;}
        );
        },
        addFormation(){
          
           this.forms.save(this.newFormation).then(
           response => {
             
               this.formations = [...this.formations,this.newFormation];
               console.log("success add formation");
              
           },response => {
               
               console.log;
           });
       },
       
       addSkill(){
          
           this.skils.save(this.newSkill).then(
           response => {
              
               this.skills = [...this.skills,this.newSkill];
               console.log("success add formation");
              
           },response => {
             
               console.log;
           });
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


