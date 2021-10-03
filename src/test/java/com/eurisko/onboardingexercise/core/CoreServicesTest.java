package com.eurisko.onboardingexercise.core;

import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.core.services.CoreServices;
import com.eurisko.onboardingexercise.project.module.integration.entities.Photo;
import com.eurisko.onboardingexercise.project.module.integration.repo.PhotoRepo;
import com.eurisko.onboardingexercise.project.module.integration.services.AlbumsCall;
import com.eurisko.onboardingexercise.project.module.integration.services.PhotosCall;
import com.eurisko.onboardingexercise.project.module.integration.services.UsersCall;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class CoreServicesTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();
    @MockBean
    CoreServices services;
    @Mock
    AlbumsCall albumsCall;
    @Mock
    PhotosCall photosCall;
    @Mock
    UsersCall usersCall;
    @Mock
    PhotoRepo photoRepo;
    @BeforeEach
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    }

    @Test
    public void basicTest() throws JsonProcessingException {
        ServletContext servletContext = webApplicationContext.getServletContext();
        PhotoResponseDto[] photoResponses = mapper.readValue("[\n" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"accusamus beatae ad facilis cum similique qui sunt\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/92c952\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/92c952\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"reprehenderit est deserunt velit ipsam\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/771796\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 3,\n" +
                "    \"title\": \"officia porro iure quia iusto qui ipsa ut modi\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/24f355\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/24f355\"\n" +
                "  }" +
                "]", PhotoResponseDto[].class);
        System.out.println(photoResponses);
        Assert.assertNotNull(photoResponses);
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("greetController"));
    }

    @Test
    public void TestGetAllPhotos() throws Exception {
        PhotoResponseDto[] photoResponses = mapper.readValue("[\n" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"accusamus beatae ad facilis cum similique qui sunt\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/92c952\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/92c952\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"reprehenderit est deserunt velit ipsam\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/771796\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 3,\n" +
                "    \"title\": \"officia porro iure quia iusto qui ipsa ut modi\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/24f355\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/24f355\"\n" +
                "  }" +
                "]", PhotoResponseDto[].class);
        when(photoRepo.findAll()).thenReturn(Collections.singletonList(new Photo()));
        when(photosCall.getAllPhotos()).thenReturn(Arrays.asList(photoResponses));
        mvc.perform(post("/api/v1/get-all-photos")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());
    }
}
