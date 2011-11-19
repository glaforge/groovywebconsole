import com.google.appengine.api.datastore.Entity

if (params.id) {
    def id = Long.parseLong(params['id'])

    try {
        def savedscript = memcache["savedscript-$id"]
        Entity script = savedscript ?: datastore.get("savedscript", id)
        if (!savedscript) memcache["savedscript-$id"] = script

        request.script = script
		forward params['embed'] ? 'embed.gtpl' : 'view.gtpl'
    } catch (Throwable t) {
        forward 'nosuchscript.gtpl'
    }
} else {
    forward 'nosuchscript.gtpl'
}