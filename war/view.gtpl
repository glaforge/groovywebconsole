<html>
    <head>
        <%
            def script = request.script
        %>

        <title>Groovy web console - ${script?.title ?: 'Untitled script'}</title>

        <link rel="alternate"
                type="application/atom+xml"
                href="/atom.groovy"
                title="Groovy Web Console snippets atom feed"
        />

        <link rel="stylesheet" type="text/css" href="/css/redmond/jquery-ui-1.7.1.custom.css"/>
        <link rel="stylesheet" type="text/css" href="/css/main.css"/>

        <link type="text/css" rel="stylesheet" href="/css/shCore.css"/>
        <link type="text/css" rel="stylesheet" href="/css/shThemeDefault.css"/>

        <script src="/js/jquery-1.6.4.min.js" type="text/javascript"></script>
        <script src="/js/jquery-ui-1.7.2.custom.min.js" type="text/javascript"></script>

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
        <script src="/js/view.js" type="text/javascript"></script>
    
        <h1><a href="/">Groovy web console</a></h1>

        <div id="shareThis">
            <table cellspacing="20">
                <tr>
                    <td>
                        <a id="atomLink" href="/atom.groovy">
                            <table>
                                <tr>
                                    <td><img src="/images/atom-feed.png" alt="subscribe to the feed" align="left" border="0"></td>
                                    <td><i>Subscribe<br/>to this<br/>site</i></td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td>
                        <a href="#" onclick="return TweetAndTrack.open(this, 'http://groovyconsole.appspot.com/script/${script.key.id}');">
                            <span style="display:none;">${script.title} (via #groovywebconsole)</span>
                            <table>
                                <tr>
                                    <td><img src="/images/twitter.png" alt="tweet this snippet" align="left" border="0"></td>
                                    <td><i>Tweet<br/>this<br/>script</i></td>
                                </tr>
                            </table>
                        </a>
                    </td>
                </tr>
            </table>
        </div>

        <h2>${script?.title ?: 'Untitled script'}</h2>
        <div id="publishedby">
            <img src="/images/date.png" align="top">
            Published ${new com.ocpsoft.pretty.time.PrettyTime().format(script.dateCreated)}
            by
            <%
                if (script.author && script.author != 'Anonymous') {
            %>
                <img src="/images/user.png" align="top">
                <a href="/author/${script.author}">${script.author}</a>
            <%
                } else {
            %>
                Anonymous
            <%
                }
                if (script?.tags?.join()) {
            %>
                with tags
            <%
                    script.tags.each { tag ->
            %>
                <img src="/images/tag_blue.png" align="top">
                <a href="/tag/${tag}">${tag}</a>
            <%
                    }
                }
            %>
        </div>

        <div id="actionsBreadcrumb">
            <span class="actionsBreadcrumbHead">Actions &nbsp;&#x27A4;</span>
            <span class="actionsBreadcrumbChild"><a href="/edit/${script.key.id}">Edit in console</a></span>
            <span class="actionsBreadcrumbChild"><a href="/">Back to console</a></span>
            <span class="actionsBreadcrumbChild" id="toggleLineNumbers"><a href="javascript:void(0)">Show/hide line numbers</a></span>
            <span class="actionsBreadcrumbLastChild"><a href="/scripts">View recent scripts</a></span>
        </div>

        <pre class="brush:groovy">${script.script.replaceAll('<', '&lt;')}</pre>

        <div id="commentsArea">
            <script>
                var idcomments_acct = 'ffac2056f3a0f603b8799858d3af8299';
                var idcomments_post_id = 'http://groovyconsole.appspot.com/view.groovy?id=${script.key.id}';
                var idcomments_post_url;
                </script>
                <span id="IDCommentsPostTitle" style="display:none"></span>
            <script type='text/javascript' src='http://www.intensedebate.com/js/genericCommentWrapperV2.js'></script>
        </div>
    </body>
</html>