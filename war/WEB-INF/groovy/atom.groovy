/*

<?xml version="1.0" encoding="utf-8"?>

<feed xmlns="http://www.w3.org/2005/Atom">

	<title>Example Feed</title>
	<subtitle>A subtitle.</subtitle>
	<link href="http://example.org/feed/" rel="self" />
	<link href="http://example.org/" />
	<id>urn:uuid:60a76c80-d399-11d9-b91C-0003939e0af6</id>
	<updated>2003-12-13T18:30:02Z</updated>
	<author>
		<name>John Doe</name>
		<email>johndoe@example.com</email>
	</author>

	<entry>
		<title>Atom-Powered Robots Run Amok</title>
		<link href="http://example.org/2003/12/13/atom03" />
		<id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>
		<updated>2003-12-13T18:30:02Z</updated>
		<summary>Some text.</summary>
	</entry>

</feed>

 */

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