import groovy.xml.MarkupBuilder
import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.PreparedQuery

import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import java.text.SimpleDateFormat

def query = new Query("savedscript")
query.addSort("dateCreated", Query.SortDirection.DESCENDING)
PreparedQuery preparedQuery = datastoreService.prepare(query)
def entities = preparedQuery.asList(withLimit(10))

def isoTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
def prettyDate = new SimpleDateFormat("EEE, d MMM yyyy", Locale.US)

response.contentType = "application/atom+xml"

def mkp = new MarkupBuilder(out)

mkp.yieldUnescaped '''<?xml version="1.0" encoding="utf-8"?>'''

mkp.feed(xmlns: "http://www.w3.org/2005/Atom") {
    title "Groovy Web Console"
    subtitle "Sharing Groovy snippets, one script at a time"
    link href: "http://groovyconsole.appspot.com", rel: "self"
    updated isoTime.format(entities[0].dateCreated)
    author {
        name "Guillaume Laforge"
        email "glaforge@gmail.com"
    }
    generator(uri: "http://gaelyk.appspot.com", version: "1.0", "Gaelyk lightweight Groovy toolkit for Google App Engine")

    entities.each { entity ->
        def authorText = entity.author && entity.author != 'Anonymous' ? entity.author : 'Anonymous'
        def titleText = entity.title ?: 'Untitled'
        entry {
            id entity.key.id
            title titleText
            link href: "http://groovyconsole.appspot.com/view.groovy?id=${entity.key.id}"
            updated isoTime.format(entity.dateCreated)
            summary "Snippet posted by ${authorText} on the Groovy Web Console on ${prettyDate.format(entity.dateCreated)}."
            author {
                name authorText
            }
        }
    }
}