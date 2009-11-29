get "/script/@id",      forward: "/view.groovy?id=@id", validate: { id ==~ /\d+/ }
get "/edit/@id",        forward: "/?id=@id",            validate: { id ==~ /\d+/ }

get "/author/@author",  forward: "/recentscripts.gtpl?author=@author&limit=100"

get "/tag/@tag",        forward: "/recentscripts.gtpl?tag=@tag&limit=100"

get "/scripts",         forward: "/recentscripts.gtpl?limit=100"
get "/scripts/@limit",  forward: "/recentscripts.gtpl?limit=@limit"

get "/badcaptcha",      forward: "/badcaptcha.gtpl"

get "/nosuchscript",     forward: "/nosuchscript.gtpl"