//package com.eurisko.onboardingexercise.project.container.module;
//
//import com.eurisko.onboardingexercise.project.module.core.services.CoreServices;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableScheduling
//@EnableAsync
//public class AppInitializer {
//    private final CoreServices coreServices;
//
//    @Scheduled(fixedDelay=Long.MAX_VALUE)
//    @Async
//    public void init(){
//        new Thread(coreServices::fillDb).start();
//    }
//}
