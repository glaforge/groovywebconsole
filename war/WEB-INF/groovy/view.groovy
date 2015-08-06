import com.google.appengine.api.datastore.Entity

if (params.id) {
    log.info "Params: $params.id"
    def id = Long.parseLong(params['id'])

    try {
        def savedscript = memcache["savedscript-$id"]
        Entity script = savedscript ?: datastore.get("savedscript", id)
        if (!savedscript) memcache["savedscript-$id"] = script

        request.script = script
        forward params['embed'] ? 'embed.gtpl' : 'view.gtpl'
    } catch (Throwable t) {
        log.info t.message
        t.printStackTrace()
        forward 'nosuchscript.gtpl'
    }
} else {
    log.info "No params.id = $params.id"
    forward 'nosuchscript.gtpl'
}