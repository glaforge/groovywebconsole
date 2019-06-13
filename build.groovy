def ant = new AntBuilder().sequential {
    webinf = "war/WEB-INF"
    taskdef name: "groovyc", classname: "org.codehaus.groovy.ant.Groovyc"
    groovyc srcdir: "src", destdir: "${webinf}/classes", {
        classpath {
            fileset dir: "${webinf}/lib", {
                include name: "*.jar"
            }
            pathelement path: "${webinf}/classes"
        }
        javac source: "1.8", target: "1.8", debug: "on"
    }
}

