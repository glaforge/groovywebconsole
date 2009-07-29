<html>
    <head>
        <title>Groovy web console - Recent scripts ${params.author ? "by " + params.author : ""}</title>

        <link rel="stylesheet" type="text/css" href="/css/redmond/jquery-ui-1.7.1.custom.css"/>
        <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    </head>

    <body>
        <h1>Groovy web console</h1>

        <h2>Recent scripts ${params.author ? "by " + params.author : ""} ${params.tag ? " with tag '" + params.tag + "'" : ""}</h2>

        <% include "/recentscripts.groovy" %>

        <div id="backtoconsole">
            <h3>Actions:</h3>
            <ul>
                <% if (params.author) { %>
                <li><a href="/recentscripts.gtpl?limit=40">View all recent scripts</a></li>
                <%
                    }
                %>
                <li><a href="/">Back to the console...</a></li>
            </ul>
        </div>

    </body>
</html>