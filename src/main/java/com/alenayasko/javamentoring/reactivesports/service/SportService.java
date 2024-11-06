package com.alenayasko.javamentoring.reactivesports.service;

import com.alenayasko.javamentoring.reactivesports.model.Sport;
import com.alenayasko.javamentoring.reactivesports.repository.SportRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class SportService {

    private static final String ITEM_URI = "/services/api/IchibaItem/Search/20220601";
    private WebClient rakutenWebClient;

    private SportRepository sportRepository;

    @Autowired
    public SportService(WebClient rakutenWebClient, SportRepository sportRepository) {

        this.rakutenWebClient = rakutenWebClient;
        this.sportRepository = sportRepository;
    }

    @PostConstruct
    public void populateSports() {

        rakutenWebClient.get()
            .uri(builder -> builder.path(ITEM_URI)
                .queryParam("format", "json")
                .queryParam("keyword", "sport")
                .queryParam("applicationId", "1092019854803459142")
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Object.class)
            .map(response -> (Map<String, Object>) response)
            .flatMapMany(responseMap -> Flux.fromIterable((List<Map<String, Object>>) responseMap.get("Items")))
                .log()
                .limitRate(20, 0)
                .subscribe(itemMap -> {
                    Map<String, Object> item = (Map<String, Object>) itemMap.get("Item");
                    Sport sport = new Sport((String) item.get("itemName"));
                    sportRepository.save(sport).subscribe();
                });
    }

    public Mono<Sport> save(Sport sport) {

        return sportRepository.save(sport);
    }

    public Flux<Sport> findByNameContains(String name) {
        return sportRepository.findByNameContains(name);
    }
}
