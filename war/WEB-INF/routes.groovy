get "/script/@id",      forward: "/view.groovy?id=@id",     validate: { id ==~ /\d+/ }, cache: 24.hours
get "/edit/@id",        forward: "/editscript.groovy?id=@id", validate: { id ==~ /\d+/ }

get "/author/@author",  forward: "/recentscripts.gtpl?author=@author&limit=100",        cache: 5.seconds

get "/tag/@tag",        forward: "/recentscripts.gtpl?tag=@tag&limit=100",              cache: 5.seconds

get "/scripts",         forward: "/recentscripts.gtpl?limit=100",                       cache: 5.seconds
get "/scripts/@limit",  forward: "/recentscripts.gtpl?limit=@limit",                    cache: 5.seconds

get "/badcaptcha",      forward: "/badcaptcha.gtpl",                                    cache: 24.hours

get "/nosuchscript",    forward: "/nosuchscript.gtpl",                                  cache: 24.hours

get "/about",           forward: "/WEB-INF/includes/about.gtpl",                        cache: 5.seconds