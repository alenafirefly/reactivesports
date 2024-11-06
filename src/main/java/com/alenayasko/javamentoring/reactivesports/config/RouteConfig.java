package com.alenayasko.javamentoring.reactivesports.config;

import com.alenayasko.javamentoring.reactivesports.controller.SportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.alenayasko.javamentoring.reactivesports.util.ApiConstants.URI_V1_CREATE_SPORT;
import static com.alenayasko.javamentoring.reactivesports.util.ApiConstants.URI_V1_SPORT;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> route(SportHandler sportHandler) {
        return RouterFunctions.route(RequestPredicates.POST(URI_V1_CREATE_SPORT), sportHandler::create)
            .andRoute(RequestPredicates.GET(URI_V1_SPORT), sportHandler::getByQuery);
    }
}
