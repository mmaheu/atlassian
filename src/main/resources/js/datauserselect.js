JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context) {juju_init(context)});

function juju_init(context) {
    AJS.$( "#juju-user-search").autocomplete({
        source: "http://localhost:2990/jira/plugins/servlet/juju/user-search",
        minLength: 2
        }
    );
};