package kr.co.kwan.eatgo.domain;



import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTests {

    @Test
    public void creation(){
        //Restaurant restaurant = new Restaurant(1004L,"Bob Zip","");
        Restaurant restaurant = Restaurant.builder()
                                .id(1004L)
                                .name("Bob Zip")
                                .address("Seoul")
                                .build();

        assertThat(restaurant.getId()).isEqualTo(1004L);
        assertThat(restaurant.getName()).isEqualTo("Bob Zip");
    }

    @Test
    public void information(){
        //Restaurant restaurant = new Restaurant(1004L, "Bob zip","seoul");
        Restaurant restaurant = Restaurant.builder()
                                .id(1004L)
                                .name("Bob Zip")
                                .address("Seoul")
                                .build();
        assertThat(restaurant.getInformation()).isEqualTo("Bob Zip in Seoul");
    }

}