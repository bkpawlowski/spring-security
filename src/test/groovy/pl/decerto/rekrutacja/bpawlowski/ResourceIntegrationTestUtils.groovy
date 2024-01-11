package pl.decerto.rekrutacja.bpawlowski

import io.restassured.RestAssured
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.http.ContentType
import io.restassured.parsing.Parser
import io.restassured.specification.RequestSpecification
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.web.server.LocalServerPort

import static pl.decerto.rekrutacja.bpawlowski.TokenUtils.VALID_TOKEN

class ResourceIntegrationTestUtils extends IntegrationTest {

    @Value('${server.servlet.context-path}')
    String contextPath

    @LocalServerPort
    Integer serverPort


    static {
        RestAssured.defaultParser = Parser.JSON
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory { cls, charset -> defaultObjectMapper })
    }

    protected String url() {
        return "http://localhost:${serverPort}${contextPath}"
    }

    protected RequestSpecification unauthenticated() {
        return RestAssured.given()
                .baseUri(url())
                .contentType(ContentType.JSON)
    }

    protected RequestSpecification authenticated(token = VALID_TOKEN) {
        return unauthenticated()
                .auth()
                .oauth2(token)
    }
}
