<html>
    <head>
        <title>Groovy web console</title>

        <link rel="alternate"
                type="application/atom+xml"
                href="/atom.groovy"
                title="Groovy Web Console snippets atom feed"
        />
        
        <script src="js/codemirror.js" type="text/javascript"></script>
        <script src="js/mirrorframe.js" type="text/javascript"></script>
        <script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.7.2.custom.min.js" type="text/javascript"></script>
        
        <link rel="stylesheet" type="text/css" href="css/redmond/jquery-ui-1.7.1.custom.css"/>
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
    </head>
    <body>
        <script src="js/main.js" type="text/javascript"></script>      

        <div id="loadingDiv">
            <img src="images/ajax-spinner-blue.gif">
        </div>        

        <h1><a href="/">Groovy web console</a></h1>


        <form id="publishform" action="publish.groovy" method="POST">
            <div id="textarea-container" class="border">
                <textarea id="script" name="script" cols="140" rows="40"><% include "/loadscript.groovy" %></textarea>
            </div>

            <table cellspacing="10" style="float:right">
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
            </tr>
        </table>
            
            <div id="button-bar">
                <input id="title" name="title" type="hidden" value="">
                <input id="author" name="author" type="hidden" value="">
                <input id="tags" name="tags" type="hidden" value="">
                <div id="actionsBreadcrumb">
                    <span class="actionsBreadcrumbHead">Actions &nbsp;&#x27A4;</span>
                    <span class="actionsBreadcrumbChild" id="executeButton"><a href="javascript:void(0)">Execute script</a></span>
                    <span class="actionsBreadcrumbChild" id="newScriptButton"><a href="/">New script</a></span>
                    <span class="actionsBreadcrumbChild" id="publishButton"><a href="/recentscripts.gtpl?limit=40">Publish script</a></span>
                    <span class="actionsBreadcrumbLastChild"><a href="/recentscripts.gtpl?limit=40">View recent scripts</a></span>
                </div>
            </div>
        </form>

        <div id="dialog" title="Publish your Groovy snippet">
            <label for="dialogTitle">Title</label>
            <br/>
            <input type="text" name="dialogTitle" id="dialogTitle" class="text ui-widget-content ui-corner-all"/>
            <br/>
            <br/>
            <label for="dialogAuthor">Author</label>
            <br/>
            <input type="text" name="dialogAuthor" id="dialogAuthor" value="" class="text ui-widget-content ui-corner-all"/>
            <br/>
            <br/>
            <label for="dialogTags">Tags <span class="smaller">(comma separated)</span></label>
            <br/>
            <input type="text" name="dialogTags" id="dialogTags" value="" class="text ui-widget-content ui-corner-all"/>
        </div>
        
        <div id="tabs">
            <ul>
            	<li><a href="#tabs-result">Result</a></li>
            	<li><a href="#tabs-output">Output</a></li>
            	<li><a href="#tabs-stacktrace">Stacktrace</a></li>
            </ul>
            
            <div id="tabs-result">
                <pre id="result" class="border hidden"></pre>
            </div>
        
            <div id="tabs-output">
                <pre id="output" class="border hidden"></pre>
            </div>
        
            <div id="tabs-stacktrace">
                <pre id="stacktrace" class="border hidden"></pre>
            </div>
        </div>

        <% include '/WEB-INF/includes/about.gtpl' %>

        <script language="javascript">
        	var editor = CodeMirror.fromTextArea('script', {
                height: "300px",
                parserfile: ["tokenizejavascript.js", "parsejavascript.js"],
                stylesheet: "css/jscolors.css",
                path: "js/",
                continuousScanning: 500,
                lineNumbers: true,
                textWrapping: false,
                tabMode: "spaces",
                submitFunction: function() {
                    \$("#executeButton").click();
                }
            });
        </script>
                
    </body>
</html>
