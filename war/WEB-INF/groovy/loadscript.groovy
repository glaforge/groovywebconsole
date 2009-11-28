import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.KeyFactory

try {
    if (params.id) {
        def id = Long.parseLong(params.id)
        def key = KeyFactory.createKey("savedscript", id)
        Entity entity = datastore.get(key)
        println entity.script.value
    }
} catch (Throwable t) {
    t.printStackTrace()
}