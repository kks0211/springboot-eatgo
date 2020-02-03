package kr.co.kwan.eatgo.domain;



import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","");
        assertThat(restaurant.getId()).isEqualTo(1004L);
        assertThat(restaurant.getName()).isEqualTo("Bob zip");
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1004L, "Bob zip","seoul");
        assertThat(restaurant.getInformation()).isEqualTo("Bob zip in seoul");
    }

}