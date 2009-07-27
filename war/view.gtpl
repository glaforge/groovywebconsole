<html>
    <head>
        <%
            def entity = request.getAttribute('entity')
        %>

        <title>Groovy web console - ${entity?.title ?: 'Untitled script'}</title>

        <link rel="stylesheet" type="text/css" href="/css/redmond/jquery-ui-1.7.1.custom.css"/>
        <link rel="stylesheet" type="text/css" href="/css/main.css"/>

        <link type="text/css" rel="stylesheet" href="/css/shCore.css"/>
        <link type="text/css" rel="stylesheet" href="/css/shThemeDefault.css"/>

        <script language="javascript" src="/js/shCore.js"></script>
        <script language="javascript" src="/js/shBrushGroovy.js"></script>
        <script language="javascript" src="/js/shBrushXml.js"></script>

        <script type="text/javascript">
        	SyntaxHighlighter.config.clipboardSwf = '/flash/clipboard.swf';
        	SyntaxHighlighter.defaults['gutter'] = false;
        	SyntaxHighlighter.all();
        </script>

    </head>

    <body>
        <h1>Groovy web console</h1>

        <h2>${entity?.title ?: 'Untitled script'}</h2>
        <div id="publishedby">
            Published ${new com.ocpsoft.pretty.time.PrettyTime().format(entity.dateCreated)}
            by
            <%
                if (entity.author && entity.author != 'Anonymous') {
            %>
                <a href="/recentscripts.gtpl?author=${entity.author}&limit=40">${entity.author}</a>
            <%
                } else {
            %>
                Anonymous
            <%
                }
            %>
        </div>

        <pre class="brush:groovy">${entity.script.value.replaceAll('<', '&lt;')}</pre>

        <div id="backtoconsole">
            <h3>Actions:</h3>
            <ul>
                <li><a href="/?id=${entity.key.id}">Edit in the console...</a>
                <li><a href="/">Back to the console...</a></li>
            </ul>
        </div>

        <% include '/WEB-INF/includes/about.gtpl' %>
    </body>
</html>