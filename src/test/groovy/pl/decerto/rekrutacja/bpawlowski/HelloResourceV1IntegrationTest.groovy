package pl.decerto.rekrutacja.bpawlowski

import io.restassured.common.mapper.TypeRef


class HelloResourceV1IntegrationTest extends ResourceIntegrationTestUtils {
    public static final String RESOURCE_URL = "/api/v1/hello"

    def "/api/v1/hello - it should greet me"() {
        given: 'unauthenticated request'
            def greetedPerson = "decerto-candidate"
            def expectedGreet = "Hello, ${greetedPerson}"
            def request = unauthenticated()
        when: 'hello endpoint is called'
            def response = request.get("${RESOURCE_URL}/${greetedPerson}")
        then: 'response status should be 200 OK'
            200 == response.statusCode()
        then: 'response body should contain greet message'
            def responseBody = response.as(new TypeRef<ValueWrapper<String>>(){})
            expectedGreet == responseBody.value
    }

    def "/api/v1/hello/secured - it should reject unauthenticated request"() {
        given: 'unauthenticated request'
            def greetedPerson = "decerto-candidate"
            def request = unauthenticated()
        when: 'hello endpoint is called'
            def response = request.get("${RESOURCE_URL}/secured/${greetedPerson}")
        then: 'response status should be 401 unauthorized'
            401 == response.statusCode()
    }

    def "/api/v1/hello/secured - it should greet me"() {
        given: 'authenticated request'
            def greetedPerson = "decerto-candidate"
            def expectedGreet = "Hello, ${greetedPerson}"
            def request = authenticated()
        when: 'hello endpoint is called'
            def response = request.get("${RESOURCE_URL}/secured/${greetedPerson}")
        then: 'response status should be 200 OK'
            200 == response.statusCode()
        then: 'response body should contain greet message'
            def responseBody = response.as(new TypeRef<ValueWrapper<String>>(){})
            expectedGreet == responseBody.value
    }

}
