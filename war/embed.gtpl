<html>
    <head>
        <% def entity = request.getAttribute('entity') %>

        <link rel="stylesheet" type="text/css" href="/css/redmond/jquery-ui-1.7.1.custom.css"/>
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

        <script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>

        <script type="text/javascript" charset="utf-8" src="http://bit.ly/javascript-api.js?version=latest&login=glaforge&apiKey=R_c6e14f0ec7fd7c31296c8d394cfbe929"></script>
        <script type="text/javascript" charset="utf-8" src="http://s.bit.ly/TweetAndTrack.js?v=1.01"></script>
    </head>

    <body>
        <pre class="brush:groovy">${entity.script.value.replaceAll('<', '&lt;')}</pre>
    </body>
</html>