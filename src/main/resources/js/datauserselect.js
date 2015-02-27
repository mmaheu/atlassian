JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context) {juju_init(context)});

var  data = [
 	  		{"label" : "Aragorn", "value" : "mmaheu"},
 	  		{"label" : "Arwen" , "value" : "swilson"},
 	  		{"label" : "Bilbo Baggins" , "value" : "bperkins"}
 	  		];


function juju_init(context) {
    AJS.$("#txtControl").autocomplete(
      {    	 
    	//source:data,  
    	source: "http://localhost:2990/jira/plugins/servlet/juju/user-search",
        minLength: 2
        }
    );
}
