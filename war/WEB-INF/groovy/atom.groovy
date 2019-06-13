import groovy.xml.MarkupBuilder
import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.PreparedQuery

import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import java.text.SimpleDateFormat

def query = new Query("savedscript")
query.addSort("dateCreated", Query.SortDirection.DESCENDING)
PreparedQuery preparedQuery = datastore.prepare(query)
def entities = preparedQuery.asList(withLimit(10))

def isoTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

response.contentType = "application/atom+xml;charset=utf-8"

def builder = new MarkupBuilder(out)

//builder.yieldUnescaped '''<?xml version="1.0" encoding="utf-8"?>'''

builder.feed(xmlns: "http://www.w3.org/2005/Atom") {
    id "http://groovyconsole.appspot.com/"
    title "Groovy Web Console for the Groovy Programming Language"
    subtitle "Sharing Groovy programming language snippets, one script at a time"
    link href: "http://groovyconsole.appspot.com/", rel: "self"
    updated isoTime.format(entities[0].dateCreated)
    author {
        name "Guillaume Laforge"
        email "glaforge@gmail.com"
    }
    generator(uri: "http://gaelyk.appspot.com", version: "2.1.2", "Gaelyk lightweight Groovy toolkit for Google App Engine")

    entities.each { entity ->
        def authorText = entity.author && entity.author != 'Anonymous' ? entity.author : 'Anonymous'
        def titleText = entity.title ?: 'Untitled'
        entry {
            id "https://groovyconsole.appspot.com/script/${entity.key.id}"
            title titleText
            link href: "https://groovyconsole.appspot.com/script/${entity.key.id}"
            updated isoTime.format(entity.dateCreated)
            author {
                name authorText
            }
            content(type: 'xhtml') {
                div(xmlns: "http://www.w3.org/1999/xhtml") {
                    pre {
                        mkp.yieldUnescaped "<![CDATA[${entity.script}]]>"
                    }
                }
            }
        }
    }
}