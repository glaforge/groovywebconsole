import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.KeyFactory

if (params['id']) {
    def id = Long.parseLong(params['id'])
    try {
        def key = KeyFactory.createKey("savedscript", id)
        Entity entity = datastoreService.get(key)

        request.setAttribute('entity', entity)
        forward 'view.gtpl'
    } catch (Throwable t) {
        forward 'nosuchscript.gtpl'
    }
} else {
    forward 'nosuchscript.gtpl'
}