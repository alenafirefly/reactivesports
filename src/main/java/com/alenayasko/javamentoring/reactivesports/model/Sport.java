package com.alenayasko.javamentoring.reactivesports.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Sport {

    @Id
    private Long id;

    private String name;

    public Sport(String name) {
        this.name = name;
    }
}
