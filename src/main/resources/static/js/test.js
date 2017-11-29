
var app = new Vue({
                  el: '.formation',
                    data: {
                            show: true,
                            show1: true,
                            show2: false,
                            projects: [],
                            nouv: {name: "name",descrip: "descrip"},
                            formations: [],
                            newFormation: {
                                domain: '',
                                name: '',
                                dateBegin: '',
                                dateEnd: ''
                            },
                            user: '',
                            ok:"okkkkkkkk"
                          
                    },
                    mounted(){
//                        var r = this.$resource("http://localhost:8080/api/students/26/myProjects");
//                            r.get().then( resp => {
//                                    this.projects = resp.body._embedded.projects;
//                                }, resp => {
//                                console.log("error: recovery projects");
//                            });
//                        var r2 = this.$resource("http://localhost:8080/api/students/26/myFormations")
//                            r2.get().then(
//                                    resp => {
//                                    this.projects = resp.body._embedded.projects;
//                                }, resp => {
//                                console.log("error: recovery formations");
//                            }
//                            );
                    },
                    methods: {
                       Show :function() {
                            this.show = true ;
                        },
                        Hidden : function() {
                            this.show = false ;
                        },
                        Show1 :function() {
                            this.show1 = true ;
                        },
                        Hidden1 : function() {
                            this.show1 = false ;
                        },
                       Show2 :function() {
                            this.show2 = true;
                            this.show1 = false;
                            this.show = false;
                        },
                        Hidden2 : function() {
                            this.show2 = false;
                            this.show1 = true;
                            this.show = true;
                        },
                        addNouvProj(){
                         
                           this.$http.post('http://localhost:8080/api/students/26/myProjects',this.nouv).then(
                                   resp =>{
                                       console.log("save ok");
                                       
                             this.projects = [...this.projects,this.nouv];
                                   },
                           resp =>{
                               console.log("save error");
                           });
                            
                        },
                        addFormation(){
                            this.$http.post('http://localhost:8080/api/students/26/myFormations',this.newFormation).then(
                            response =>{
                                console.log("success add formation");
                            },response =>{
                                console.log("Error to add formation");
                            })
                        }
                    }                   
              });
              
  var app = new Vue({
                  el: '#formation',
                    data: {
                            
                            formations: [],
                            newFormation: {
                                domain: "domain",
                                name: "propos",
                                dateBegin: "dateB",
                                dateEnd: "dateE"
                            },
                            user: '',
                            ok:"okkkkkkkk"
                          
                    },
                    mounted(){
                        
                        var r2 = this.$resource("http://localhost:8080/api/students/26/myFormations")
                            r2.get().then(
                                    resp => {
                                    this.projects = resp.body._embedded.formations;
                                }, resp => {
                                console.log("error: recovery formations");
                            }
                            );
                    },
                    methods: {
                       
                        addFormation(){
                            this.$http.post('http://localhost:8080/api/formations',this.newFormation).then(
                            response =>{
                                this.formations = [...this.formations,this.newFormation];
                                console.log("success add formation");
                            },response =>{
                                console.log("error: add formation");
                            });
                        }
                    }                   
              });                  