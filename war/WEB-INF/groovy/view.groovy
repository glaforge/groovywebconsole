import com.google.appengine.api.datastore.Entity

if (params.id) {
    def id = Long.parseLong(params['id'])

    try {
        Entity script = datastore.get("savedscript", id)
        request.script = script
		forward params['embed'] ? 'embed.gtpl' : 'view.gtpl'
    } catch (Throwable t) {
        forward 'nosuchscript.gtpl'
    }
} else {
    forward 'nosuchscript.gtpl'
}