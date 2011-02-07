import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.PreparedQuery

import static com.google.appengine.api.datastore.FetchOptions.Builder.*

def query = new Query("savedscript")
query.addSort("dateCreated", Query.SortDirection.DESCENDING)

PreparedQuery preparedQuery = datastore.prepare(query)

def entities = preparedQuery.asList(withDefaults())

def tagMap    = entities.collect { it.tags   }.flatten().groupBy { it }
def authorMap = entities.collect { it.author }.flatten().groupBy { it }

html.ul(class: 'taglist') {
    li { b "Authors: " }
    authorMap.keySet().sort().each {author ->
        if (author && author != 'Anonymous') {
            li {
                img src: '/images/user.png', align: 'top'
                a href: "/author/${author}", author
                span class: 'smaller', "(${authorMap[author].size()})"
            }
        }
    }
}

html.ul(class: 'taglist') {
    li { b "Tags: " }
    tagMap.keySet().sort().each {tag ->
        if (tag) {
            li {
                img src: '/images/tag_blue.png', align: 'top'
                a href: "/tag/${tag}", tag
                span class: 'smaller', "(${tagMap[tag].size()})"
            }
        }
    }
}
