package com.alenayasko.javamentoring.reactivesports.repository;

import com.alenayasko.javamentoring.reactivesports.model.Sport;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SportRepository extends R2dbcRepository<Sport, Long> {

    Flux<Sport> findByNameContains(String name);
}
