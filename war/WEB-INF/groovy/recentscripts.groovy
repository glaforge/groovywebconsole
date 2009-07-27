import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.PreparedQuery
import com.google.appengine.api.datastore.Entity

import static com.google.appengine.api.datastore.FetchOptions.Builder.*

import com.ocpsoft.pretty.time.PrettyTime

def query = new Query("savedscript")
query.addSort("dateCreated", Query.SortDirection.DESCENDING)

if (params.author) {
    query.addFilter("author", Query.FilterOperator.EQUAL, params.author)
}

PreparedQuery preparedQuery = datastoreService.prepare(query)

def limit = params.limit ? Integer.parseInt(params.limit) : 10
def entities = preparedQuery.asList(withLimit(limit))

html.ul {
    entities.each {Entity entity ->
        li {
            a href: "/view.groovy?id=${entity.key.id}", entity.title ?: 'Untitled'
            if (!params.author) {
                span " by "
                if (entity.author && entity.author != 'Anonymous') {
                    a href: "/recentscripts.gtpl?author=${entity.author}&limit=40", entity.author
                } else {
                    span 'Anonymous'
                }
            } else {
                yieldUnescaped ' &mdash; '
            }
            span new PrettyTime().format(entity.dateCreated)
        }
    }
}
