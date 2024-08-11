package com.ndh.logging.provider

import ch.qos.logback.classic.spi.ILoggingEvent
import com.fasterxml.jackson.core.JsonGenerator
import net.logstash.logback.composite.AbstractFieldJsonProvider
import net.logstash.logback.composite.JsonWritingUtils

class FakeTimestampNanoProvider extends TimestampNanoProvider {

  @Override
  void writeTo(JsonGenerator generator, ILoggingEvent event) {
    long nanoseconds = 1708721059490L * 1_000_000
    JsonWritingUtils.writeNumberField(generator, getFieldName(), nanoseconds) 
  }
}
