<html>
    <head>
        <% def entity = request.getAttribute('entity') %>

        <link rel="stylesheet" type="text/css" href="/css/main.css"/>

        <link type="text/css" rel="stylesheet" href="/css/shCore.css"/>
        <link type="text/css" rel="stylesheet" href="/css/shThemeDefault.css"/>

        <link type="text/css" rel="stylesheet" href="/css/embed.css"/>

        <script language="javascript" src="/js/shCore.js"></script>
        <script language="javascript" src="/js/shBrushGroovy.js"></script>
        <script language="javascript" src="/js/shBrushXml.js"></script>

        <script type="text/javascript">
        	SyntaxHighlighter.config.clipboardSwf = '/flash/clipboard.swf';
        	SyntaxHighlighter.defaults['gutter'] = true;
        	SyntaxHighlighter.all();
        </script>
    </head>

    <body>
        <div class="gwcEmbedded">
            <div class="gwcHeader">
                <span class="gwcTitle">${entity.title ?: 'Untitled'}</span>
                <span class="gwcPublished">
                    <img src="/images/date.png" align="top">
                    Published ${new com.ocpsoft.pretty.time.PrettyTime().format(entity.dateCreated)}
                    on the <a href="http://groovyconsole.appspot.com/script/${entity.key.id}">Groovy Web Console</a>
                </span>
                <span class="gwcAuthor">
                    by <img src="/images/user.png" align="top">
                    <a href="http://groovyconsole.appspot.com/author/${URLEncoder.encode(entity.author)}">${entity.author}</a>
                </span>
            </div>
            <pre class="brush:groovy">${entity.script.value.replaceAll('<', '&lt;')}</pre>
        </div>
    </body>
</html>