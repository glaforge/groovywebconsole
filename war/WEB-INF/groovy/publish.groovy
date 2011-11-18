import com.google.appengine.api.datastore.Entity

def captchaTestPassed = false
try {
    // if the question is an addition and the result provided by the user is a number with one or two digits
    // the we can safely evaluate those expressions with the GroovyShell#evaluate() method
    if (params.captchaQuestion ==~ /\d \+ \d/ && params.captchaAnswer ==~ /\d{1,2}/) {
        captchaTestPassed = evaluate(params.captchaQuestion) == evaluate(params.captchaAnswer)
    }
} catch (any) {}


if (captchaTestPassed) {
    def entity = new Entity("savedscript")
    entity.unindexed.script = params.script
    entity.title = params.title ?: "Untitled"
    entity.author = params.author ?: "Anonymous"
    entity.dateCreated = new Date()
    entity.tags = params.tags ? params.tags?.split(',')*.trim() : []
    entity.save()

    memcache.recentScripts = null

    request['entity'] = entity

    redirect "/script/${entity.key.id}"
} else {
    redirect "/badcaptcha"
}

