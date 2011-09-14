import com.google.appengine.api.datastore.Entity

try {
    if (params.id) {
        def id = Long.parseLong(params.id)
        Entity entity = datastore.get("savedscript", id)
        out << entity.script
        out.flush()
    }
} catch (Throwable t) {
    t.printStackTrace()
}