<div id="recentScripts">
    <h2>Recent scripts</h2>

    <div id="recentScriptsList">
        <% include '/recentscripts.groovy' %>
    </div>

    <div id="viewAllScripts">
    </div>
    <ul>
        <li><a href="/recentscripts.gtpl?limit=40">View all recent scripts</a></li>
    </ul>
</div>

<div id="footer">
    <h2>Tips</h2>
    <ul>
        <li>You can hit Alt-Meta-R to execute the current script without using the execute button.</li>
    </ul>
    <h2>About this site:</h2>
    <ul>
        <li>Application deployed on <a href="http://code.google.com/appengine">Google App Engine</a></li>
        <li>Developed with the <a href="http://gaelyk.appspot.com">Gaelyk</a> lightweigh Groovy toolkit for Google App Engine</li>
        <li>Code hosted on <a href="http://github.com/glaforge/groovywebconsole/tree/master">GitHub</a> and managed with Git</li>
        <li>Live syntax highlighting provided by <a href="http://marijn.haverbeke.nl/codemirror/">CodeMirror</a></li>
    </ul>
</div>