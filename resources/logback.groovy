import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.status.OnConsoleStatusListener

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.WARN

statusListener(OnConsoleStatusListener)
appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  }
}
appender("FILE", RollingFileAppender) {
  file = "\${jetty.home}/logs/jetty.log"
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "\${jetty.home}/logs/jetty_%d{yyyy-MM-dd}.log.gz"
    maxHistory = 30
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg%n"
  }
}
logger("org.eclipse.jetty.util.preventers", DEBUG)
logger("org.eclipse.jetty.webapp.WebInfConfiguration", DEBUG)
root(WARN, ["STDOUT", "FILE"])
