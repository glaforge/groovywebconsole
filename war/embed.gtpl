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
        <pre class="brush:groovy">${entity.script.value.replaceAll('<', '&lt;')}</pre>
    </body>
</html>