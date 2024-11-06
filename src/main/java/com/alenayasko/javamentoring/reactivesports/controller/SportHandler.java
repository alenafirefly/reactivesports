package com.alenayasko.javamentoring.reactivesports.controller;

import com.alenayasko.javamentoring.reactivesports.model.Sport;
import com.alenayasko.javamentoring.reactivesports.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.alenayasko.javamentoring.reactivesports.util.ApiConstants.*;

@Component
public class SportHandler {

    private final SportService sportService;

    @Autowired
    public SportHandler(SportService sportService) {
        this.sportService = sportService;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Sport sport = new Sport(request.pathVariable(PATH_PARAM_SPORT_NAME));
        return sportService.save(sport).flatMap(createdSport ->
            ServerResponse.created(URI.create(String.format("%s/%s", URI_V1_SPORT, createdSport.getId()))).build());
    }

    public Mono<ServerResponse> getByQuery(ServerRequest request) {

        return request.queryParam(REQUEST_PARAM_QUERY)
            .map(query -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(sportService.findByNameContains(query), Sport.class))
            .orElse(ServerResponse.badRequest().build());
    }
}
