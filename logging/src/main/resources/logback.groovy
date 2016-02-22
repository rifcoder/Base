import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.classic.turbo.MDCFilter

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.WARN


def USER_HOME = System.getProperty("user.home");
def loginFilter = new MDCFilter()
//loginFilter.putAt("user", USER_HOME)

println "USER_HOME=${USER_HOME}"
println "Host Name = ${hostname}"

scan("10 seconds")

appender("FILE", FileAppender) {
    println "Setting [file] property to [${USER_HOME}/myApp.log]"
    file = "${USER_HOME}/myApp.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%mdc{user} %msg%n"
    }
}


appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} %level %logger - %msg%n"
    }
}


logger("com.rifcoder", DEBUG, ["FILE"])

logger("demo", WARN, ["CONSOLE"])

root(INFO, ["CONSOLE"])