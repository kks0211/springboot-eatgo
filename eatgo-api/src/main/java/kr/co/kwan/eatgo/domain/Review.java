package kr.co.kwan.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {

    @GeneratedValue
    @Id
    private Long id;
    @NotEmpty
    private String name;
    @NonNull
    private Integer score;
    @NotEmpty
    private String  description;


}
