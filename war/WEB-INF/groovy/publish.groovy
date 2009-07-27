import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.Text

def entity = new Entity("savedscript")
entity.script = new Text(params.script)
entity.title = params.title ?: "Untitled"
entity.author = params.author ?: "Anonymous"
entity.dateCreated = new Date()
entity.save()

request.setAttribute('entity', entity)

redirect "view.groovy?id=${entity.key.id}"