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
                        <td><img src="/images/twitter.png" alt="tweet this snippet" align="left" border="0"></td>
                        <td><i>Tweet<br/>this<br/>script</i></td>
                    </tr>
                </table>
            </a>
        </div>

        <h2>${entity?.title ?: 'Untitled script'}</h2>
        <div id="publishedby">
            <img src="/images/date.png" align="top">
            Published ${new com.ocpsoft.pretty.time.PrettyTime().format(entity.dateCreated)}
            by
            <%
                if (entity.author && entity.author != 'Anonymous') {
            %>
                <img src="/images/user.png" align="top">
                <a href="/recentscripts.gtpl?author=${entity.author}&limit=40">${entity.author}</a>
            <%
                } else {
            %>
                Anonymous
            <%
                }
                if (entity?.tags) {
            %>
                with tags
            <%
                    entity.tags.each { tag ->
            %>
                <img src="/images/tag_blue.png" align="top">
                <a href="/recentscripts.gtpl?tag=${tag}&limit=40">${tag}</a>
            <%
                    }
                }
            %>
        </div>

        <div id="actionsBreadcrumb">
            <span class="actionsBreadcrumbHead">Actions &nbsp;&#x27A4;</span>
            <span class="actionsBreadcrumbChild"><a href="/?id=${entity.key.id}">Edit in console</a></span>
            <span class="actionsBreadcrumbChild"><a href="/">Back to console</a></span>
            <span class="actionsBreadcrumbLastChild"><a href="/recentscripts.gtpl?limit=40">View recent scripts</a></span>
        </div>

        <pre class="brush:groovy">${entity.script.value.replaceAll('<', '&lt;')}</pre>

        <div id="commentsArea">
            <script>
                var idcomments_acct = 'ffac2056f3a0f603b8799858d3af8299';
                var idcomments_post_id;
                var idcomments_post_url;
                </script>
                <span id="IDCommentsPostTitle" style="display:none"></span>
            <script type='text/javascript' src='http://www.intensedebate.com/js/genericCommentWrapperV2.js'></script>
        </div>

        <% include '/WEB-INF/includes/about.gtpl' %>
    </body>
</html>