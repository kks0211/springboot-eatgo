package kr.co.kwan.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Region {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
