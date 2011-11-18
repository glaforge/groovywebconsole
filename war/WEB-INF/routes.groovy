def longPeriod = localMode ? 0 : 24.hours
def shortPeriod = localMode ? 0 : 1.hour

get "/script/@id",      forward: "/view.groovy?id=@id",         validate: { id ==~ /\d+/ }, cache: longPeriod
get "/edit/@id",        forward: "/editscript.groovy?id=@id",   validate: { id ==~ /\d+/ }, cache: longPeriod
get "/raw/@id",			forward: "/loadscript.groovy?id=@id",   validate: { id ==~ /\d+/ }, cache: longPeriod

get "/author/@author",  forward: "/recentscripts.gtpl?author=@author&limit=100",            cache: shortPeriod

get "/tag/@tag",        forward: "/recentscripts.gtpl?tag=@tag&limit=100",                  cache: shortPeriod

get "/scripts",         forward: "/recentscripts.gtpl?limit=100",                           cache: shortPeriod
get "/scripts/@limit",  forward: "/recentscripts.gtpl?limit=@limit",                        cache: shortPeriod

get "/badcaptcha",      forward: "/badcaptcha.gtpl",                                        cache: longPeriod

get "/nosuchscript",    forward: "/nosuchscript.gtpl",                                      cache: longPeriod

