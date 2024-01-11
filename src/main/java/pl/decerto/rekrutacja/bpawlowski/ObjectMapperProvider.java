package pl.decerto.rekrutacja.bpawlowski;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ObjectMapperProvider {
    private static final ObjectMapper INSTANCE = newObjectMapper();

    public static ObjectMapper objectMapper() {
        return INSTANCE;
    }

    public static ObjectMapper newObjectMapper() {
        final var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); // ISO8601DateFormat()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
