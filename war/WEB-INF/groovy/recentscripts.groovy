import com.google.appengine.api.datastore.Entity
import com.ocpsoft.pretty.time.PrettyTime

def recentScriptsInCache = memcache.recentScripts
def entities = recentScriptsInCache ?: datastore.execute {
    select all from savedscript
    sort desc by dateCreated
    limit params.limit?.toInteger() ?: 200
}
if (!recentScriptsInCache) memcache.recentScripts = entities

def tagMap    = entities.collect { it.tags   }.flatten().groupBy { it }
def authorMap = entities.collect { it.author }.flatten().groupBy { it }

html.div(class: 'indent') {
    ul {
        entities.each { Entity entity ->
            if (params.author == entity.author || entity.tags?.contains(params.tag) || !(params.author || params.tag)) {
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
    }
}

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
