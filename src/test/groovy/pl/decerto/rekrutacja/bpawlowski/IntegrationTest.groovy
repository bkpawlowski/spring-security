package pl.decerto.rekrutacja.bpawlowski

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IntegrationTest extends Specification {
    protected static ObjectMapper defaultObjectMapper

    static {
        defaultObjectMapper = ObjectMapperProvider.newObjectMapper()
    }
}
