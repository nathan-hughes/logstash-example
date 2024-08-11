package com.ndh.logging.logstash

import spock.lang.Specification
import org.slf4j.LoggerFactory
import org.slf4j.Logger

class LogstashSpec extends Specification {

  def log = LoggerFactory.getLogger(LogstashSpec)
  ByteArrayOutputStream buffer
  PrintStream originalStdout

  void setup() {
    buffer = new ByteArrayOutputStream()
    originalStdout = System.out
    System.setOut(new PrintStream(buffer))
  }

  void 'test logstash encoder'() {
    given:
    when:
    log.error('hello')
    then:
    System.out.flush()

    final String expected =
'''{"SeverityText":"ERROR","InstrumentationScope":"com.ndh.logging.logstash.LogstashSpec","Body":"hello","Timestamp":1708721059490000000,"Attributes":{"thread.name":"Test worker"}}\n'''

    buffer.toString() == expected

    cleanup:
    System.setOut(originalStdout)
  }
}
