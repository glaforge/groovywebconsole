import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.PreparedQuery
import com.google.appengine.api.datastore.Entity

import static com.google.appengine.api.datastore.FetchOptions.Builder.*

import com.ocpsoft.pretty.time.PrettyTime

def query = new Query("savedscript")
query.addSort("dateCreated", Query.SortDirection.DESCENDING)

if (params.author) {
    query.addFilter("author", Query.FilterOperator.EQUAL, params.author)
} else if (params.tag) {
    query.addFilter("tags", Query.FilterOperator.EQUAL, params.tag)
}

PreparedQuery preparedQuery = datastore.prepare(query)

def limit = params.limit ? Integer.parseInt(params.limit) : 10
def entities = preparedQuery.asList(withLimit(limit))

html.ul {
    entities.each { Entity entity ->
        li {
            a href: "/script/${entity.key.id}", entity.title ?: 'Untitled'
            if (!params.author) {
                span " by "
                if (entity.author && entity.author != 'Anonymous') {
                    a href: "/author/${entity.author}", entity.author
                } else {
                    span 'Anonymous'
                }
            } else {
                span ' - '
            }
            span new PrettyTime().format(entity.dateCreated)
            if (entity.tags?.join()) {
                span " with tags "
                entity.tags.each { tag ->
                    a href: "/tag/${tag}", tag
                }
            }
        }
    }
}
