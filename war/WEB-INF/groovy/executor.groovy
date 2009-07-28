import org.codehaus.groovy.control.MultipleCompilationErrorsException

def scriptText = request.getParameter("script") ?: "'The received script was null.'"

def output = new StringWriter()
def binding = new Binding([out: new PrintWriter(output)])

def stacktrace = new StringWriter()
def errWriter = new PrintWriter(stacktrace)

class NoGaeSdkAccessGCL extends GroovyClassLoader {
    Class loadClass(final String name, boolean lookupScriptFiles, boolean preferClassOverScript, boolean resolve) {
        if (name.startsWith('com.google.appengine.'))
            throw new SecurityException("Access to $name forbidden. You're not allowed to use the App Engine SDK within the console.")
        super.loadClass(name, lookupScriptFiles, preferClassOverScript, resolve)
    }
}

def gcl = new NoGaeSdkAccessGCL()

def originalAsType = String.metaClass.metaMethods.find { it.name == 'asType' }
String.metaClass.asType = { Class c ->
    if (delegate.startsWith('com.google.appengine.')) {
        throw new SecurityException("Access to $delegate forbidden. You're not allowed to use the App Engine SDK within the console.")
    } else {
        return originalAsType.doMethodInvoke(delegate, c)
    }
}

def result = ""
try {
	result = new GroovyShell(gcl, binding).evaluate(scriptText)
} catch (MultipleCompilationErrorsException e) {
	stacktrace.append(e.message - 'startup failed, Script1.groovy: ')
} catch (Throwable t) {
	sanitizeStacktrace(t)
	def cause = t
	while (cause = cause?.cause) {
		sanitizeStacktrace(cause)
	}
	t.printStackTrace(errWriter)
}

response.contentType = "application/json"

out.println """{
	executionResult: "${escape(result)}",
 	outputText: "${escape(output)}",
 	stacktraceText: "${escape(stacktrace)}"
}"""

def escape(object) {
	object.toString().replaceAll(/\n/, /\\\n/).replaceAll(/"/, /\\"/)
}

def sanitizeStacktrace(t) {
    def filtered = [
        'com.google.', 'org.mortbay.',
        'java.', 'javax.', 'sun.', 
        'groovy.', 'org.codehaus.groovy.',
        'groovyx.gaelyk.', 'executor'
	]
    def trace = t.getStackTrace()
    def newTrace = []
    trace.each { stackTraceElement -> 
        if (filtered.every { !stackTraceElement.className.startsWith(it) }) {
            newTrace << stackTraceElement
        }
    }
    def clean = newTrace.toArray(newTrace as StackTraceElement[])
    t.stackTrace = clean
}