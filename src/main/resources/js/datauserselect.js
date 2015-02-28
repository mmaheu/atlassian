JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context) {juju_init(context)});


function juju_init(context) {
    AJS.$("#juju-user-search").autocomplete(
      {    	 
    	//source:data,  
    	source: AJS.params.baseURL + "/plugins/servlet/juju/user-search",
        minLength: 2 
        }
    );
}
