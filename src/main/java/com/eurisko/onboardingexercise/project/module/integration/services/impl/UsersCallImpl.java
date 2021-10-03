package com.eurisko.onboardingexercise.project.module.integration.services.impl;

import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import com.eurisko.onboardingexercise.project.module.integration.entities.User;
import com.eurisko.onboardingexercise.project.module.integration.model.response.UserResponse;
import com.eurisko.onboardingexercise.project.module.integration.repo.UserRepo;
import com.eurisko.onboardingexercise.project.module.integration.services.UserMapper;
import com.eurisko.onboardingexercise.project.module.integration.services.UsersCall;
import com.eurisko.onboardingexercise.project.module.integration.util.ExerciseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsersCallImpl implements UsersCall {
    private final ExerciseProperties properties;
    private final RestTemplate restTemplate;
    private final UserMapper mapper = UserMapper.INSTANCE;
    private final UserRepo repo;

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> list = repo.findAll();
        if (list.isEmpty()) {
            List<UserResponse> users = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(properties.getBaseUrl() + "/users", UserResponse[].class)));
            List<User> users1 = mapper.userToEntity(users);
            repo.saveAll(users1);
            return mapper.userToDto(users1);
        }
        return mapper.userToDto(list);
    }

//    @Override
//    public List<UserResponseDto> getAllUsers(Long id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", "application/json");
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("id", id.toString());
//        HttpEntity entity = new HttpEntity(headers);
//
//        HttpEntity<UserResponseDto> response = restTemplate.exchange(properties.getBaseUrl() + "/users", HttpMethod.GET, entity, UserResponseDto.class, params);
//        return List.of(Objects.requireNonNull(response.getBody()));
//    }

    @Override
    @Transactional
    public void fillDb() {
        List<User> list = repo.findAll();
        if (list.isEmpty()) {
            List<UserResponse> users = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(properties.getBaseUrl() + "/users", UserResponse[].class)));
            List<User> users1 = mapper.userToEntity(users);
            repo.saveAll(users1);
        }
    }
}
