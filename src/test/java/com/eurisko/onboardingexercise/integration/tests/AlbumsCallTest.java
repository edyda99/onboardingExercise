//package com.eurisko.onboardingexercise.integration.tests;
//
//import com.eurisko.onboardingexercise.integration.IntegrationTesting;
//import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
//import com.eurisko.onboardingexercise.project.module.integration.repo.AlbumRepo;
//import com.eurisko.onboardingexercise.project.module.integration.repo.UserRepo;
//import com.eurisko.onboardingexercise.project.module.integration.services.AlbumMapper;
//import com.eurisko.onboardingexercise.project.module.integration.services.AlbumsCall;
//import com.eurisko.onboardingexercise.project.module.integration.services.impl.AlbumsCallImpl;
//import com.eurisko.onboardingexercise.project.module.integration.util.ExerciseProperties;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.client.RestTemplate;
//import org.assertj.core.api.Assertions;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class AlbumsCallTest extends IntegrationTesting {
//
//    private final ExerciseProperties properties;
//    private final AlbumRepo albumRepo;
//    private final AlbumMapper mapper = AlbumMapper.INSTANCE;
//    private final RestTemplate restTemplate;
//    private final UserRepo userRepo;
//
//    @Autowired
//    public AlbumsCallTest(ExerciseProperties properties,AlbumRepo albumRepo,RestTemplate restTemplate,UserRepo userRepo) {
//        super("/albums", properties);
//        this.properties = properties;
//        this.albumRepo = albumRepo;
//        this.restTemplate = restTemplate;
//        this.userRepo = userRepo;
//    }
//
//    @Test
//    public void successTest(){
//        String pf1 = "[\n" +
//                "  {\n" +
//                "    \"userId\": 1,\n" +
//                "    \"id\": 1,\n" +
//                "    \"title\": \"quidem molestiae enim\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 2,\n" +
//                "    \"id\": 13,\n" +
//                "    \"title\": \"ab rerum non rerum consequatur ut ea unde\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 2,\n" +
//                "    \"id\": 14,\n" +
//                "    \"title\": \"ducimus molestias eos animi atque nihil\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 2,\n" +
//                "    \"id\": 15,\n" +
//                "    \"title\": \"ut pariatur rerum ipsum natus repellendus praesentium\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 2,\n" +
//                "    \"id\": 16,\n" +
//                "    \"title\": \"voluptatem aut maxime inventore autem magnam atque repellat\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 3,\n" +
//                "    \"id\": 28,\n" +
//                "    \"title\": \"omnis neque exercitationem sed dolor atque maxime aut cum\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 3,\n" +
//                "    \"id\": 29,\n" +
//                "    \"title\": \"inventore ut quasi magnam itaque est fugit\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 3,\n" +
//                "    \"id\": 30,\n" +
//                "    \"title\": \"tempora assumenda et similique odit distinctio error\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 4,\n" +
//                "    \"id\": 31,\n" +
//                "    \"title\": \"adipisci laborum fuga laboriosam\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 4,\n" +
//                "    \"id\": 32,\n" +
//                "    \"title\": \"reiciendis dolores a ut qui debitis non quo labore\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 4,\n" +
//                "    \"id\": 33,\n" +
//                "    \"title\": \"iste eos nostrum\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 4,\n" +
//                "    \"id\": 34,\n" +
//                "    \"title\": \"cumque voluptatibus rerum architecto blanditiis\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 4,\n" +
//                "    \"id\": 35,\n" +
//                "    \"title\": \"et impedit nisi quae magni necessitatibus sed aut pariatur\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 4,\n" +
//                "    \"id\": 36,\n" +
//                "    \"title\": \"nihil cupiditate voluptate neque\"\n" +
//                "  },\n" +
//                "]";
//        httpMock(pf1);
//        AlbumsCall call = new AlbumsCallImpl(properties,albumRepo,userRepo,restTemplate);
//        List<AlbumResponseDto> albums = call.getAllAlbums();
//        Assertions.assertThat(albums.size()).isGreaterThan(1);
//        assertThat(albums.size()).isGreaterThan(1);
//    }
//}
