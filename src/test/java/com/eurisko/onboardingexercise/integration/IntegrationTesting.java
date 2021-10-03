//package com.eurisko.onboardingexercise.integration;
//
//import com.eurisko.onboardingexercise.project.module.integration.util.ExerciseProperties;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Getter;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.mockserver.integration.ClientAndServer;
//import org.mockserver.model.HttpRequest;
//import org.mockserver.model.HttpResponse;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//import java.nio.charset.StandardCharsets;
//
//@Getter
//public class IntegrationTesting {
//    public static ClientAndServer mockServer;
//    private final String path;
//    private final ExerciseProperties properties;
//
//    public IntegrationTesting(String path, ExerciseProperties properties) {
//        this.path = path;
//        this.properties = properties;
//    }
//
//    @BeforeClass
//    public static void initMockServer() {
//        mockServer = ClientAndServer.startClientAndServer(8088);
//    }
//
//    @Before
//    public void setupSuite() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        MappingJackson2HttpMessageConverter jacksonMConverter = new MappingJackson2HttpMessageConverter(objectMapper);
//        properties.setBaseUrl("http://localhost:" + mockServer.getLocalPort());
//
//    }
//
//
//    public void httpMock(String body) {
//        mockServer.when(
//                HttpRequest.request()
//                        .withMethod("POST")
//                        .withHeader("API_VERSION", "2")
//                        .withPath(path)
//        ).respond(
//                HttpResponse.response()
//                        .withStatusCode(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody(body.getBytes(StandardCharsets.UTF_8))
//        );
//    }
//
//    @After
//    public void destroy() {
//        mockServer.reset();
//    }
//
//    @AfterClass
//    public static void destroyAll() {
//        mockServer.stop();
//    }
//}
//
