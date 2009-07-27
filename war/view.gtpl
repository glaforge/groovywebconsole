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

        <script type="text/javascript" charset="utf-8" src="http://bit.ly/javascript-api.js?version=latest&login=glaforge&apiKey=R_c6e14f0ec7fd7c31296c8d394cfbe929"></script>
        <script type="text/javascript" charset="utf-8" src="http://s.bit.ly/TweetAndTrack.js?v=1.01"></script>
    </head>

    <body>
        <h1>Groovy web console</h1>

        <div id="tweetthis">
            <a href="#" onclick="return TweetAndTrack.open(this, 'http://groovyconsole.appspot.com/view.groovy?id=${entity.key.id}');">
                <span style="display:none;">${entity.title} (via #groovywebconsole)</span>
                <table>
                    <tr>
                        <td><img src="/images/twitter.png" alt="tweet this snippet" align="left"></td>
                        <td><i>Tweet<br/>this<br/>script</i></td>
                    </tr>
                </table>
            </a>
        </div>

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