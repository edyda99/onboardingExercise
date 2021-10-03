//package com.eurisko.onboardingexercise.integration.tests;
//
//import com.eurisko.onboardingexercise.integration.IntegrationTesting;
//import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
//import com.eurisko.onboardingexercise.project.module.integration.repo.AlbumRepo;
//import com.eurisko.onboardingexercise.project.module.integration.repo.PhotoRepo;
//import com.eurisko.onboardingexercise.project.module.integration.services.PhotoMapper;
//import com.eurisko.onboardingexercise.project.module.integration.services.PhotosCall;
//import com.eurisko.onboardingexercise.project.module.integration.services.impl.PhotosCallImpl;
//import com.eurisko.onboardingexercise.project.module.integration.util.ExerciseProperties;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class PhotosCallTest extends IntegrationTesting {
//
//    private final ExerciseProperties properties;
//    private final PhotoRepo photoRepo;
//    private final PhotoMapper mapper = PhotoMapper.INSTANCE;
//    private final RestTemplate restTemplate;
//    private final AlbumRepo albumRepo;
//
//    @Autowired
//    public PhotosCallTest(ExerciseProperties properties,PhotoRepo photoRepo, RestTemplate restTemplate,AlbumRepo albumRepo) {
//        super("/albums", properties);
//        this.properties = properties;
//        this.photoRepo = photoRepo;
//        this.restTemplate = restTemplate;
//        this.albumRepo = albumRepo;
//    }
//
//    @Test
//    public void successTest(){
//        String pf1 = "[\n" +
//                "  {\n" +
//                "    \"albumId\": 1,\n" +
//                "    \"id\": 1,\n" +
//                "    \"title\": \"accusamus beatae ad facilis cum similique qui sunt\",\n" +
//                "    \"url\": \"https://via.placeholder.com/600/92c952\",\n" +
//                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/92c952\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"albumId\": 1,\n" +
//                "    \"id\": 2,\n" +
//                "    \"title\": \"reprehenderit est deserunt velit ipsam\",\n" +
//                "    \"url\": \"https://via.placeholder.com/600/771796\",\n" +
//                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"albumId\": 1,\n" +
//                "    \"id\": 3,\n" +
//                "    \"title\": \"officia porro iure quia iusto qui ipsa ut modi\",\n" +
//                "    \"url\": \"https://via.placeholder.com/600/24f355\",\n" +
//                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/24f355\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"albumId\": 1,\n" +
//                "    \"id\": 4,\n" +
//                "    \"title\": \"culpa odio esse rerum omnis laboriosam voluptate repudiandae\",\n" +
//                "    \"url\": \"https://via.placeholder.com/600/d32776\",\n" +
//                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/d32776\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"albumId\": 1,\n" +
//                "    \"id\": 5,\n" +
//                "    \"title\": \"natus nisi omnis corporis facere molestiae rerum in\",\n" +
//                "    \"url\": \"https://via.placeholder.com/600/f66b97\",\n" +
//                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/f66b97\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"albumId\": 1,\n" +
//                "    \"id\": 6,\n" +
//                "    \"title\": \"accusamus ea aliquid et amet sequi nemo\",\n" +
//                "    \"url\": \"https://via.placeholder.com/600/56a8c2\",\n" +
//                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/56a8c2\"\n" +
//                "  }" +
//                "]";
//        httpMock(pf1);
//        PhotosCall call = new PhotosCallImpl(properties,photoRepo,restTemplate,albumRepo);
//        List<PhotoResponseDto> photos = call.getAllPhotos();
//        Assertions.assertThat(photos.size()).isGreaterThan(1);
//        assertThat(photos.size()).isGreaterThan(1);
//    }
//}
