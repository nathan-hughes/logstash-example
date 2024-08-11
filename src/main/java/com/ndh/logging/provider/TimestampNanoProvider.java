package com.ndh.logging.provider;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import net.logstash.logback.composite.AbstractFieldJsonProvider;
import net.logstash.logback.composite.JsonWritingUtils;
import java.io.IOException;

public class TimestampNanoProvider extends AbstractFieldJsonProvider<ILoggingEvent> {

  public TimestampNanoProvider() {
    setFieldName("Timestamp");
  }  

  @Override
  public void writeTo(JsonGenerator generator, ILoggingEvent event) throws IOException {
    long nanoseconds = event.getInstant().getEpochSecond() * 1_000_000_000L 
      + event.getInstant().getNano();
    JsonWritingUtils.writeNumberField(generator, getFieldName(), nanoseconds);
  }
}
  
