package kr.co.kwan.eatgo.domain;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {

    @Test
    public void creation() {
        User user = User.builder()
                .email("test@test.com")
                .name("tester")
                .level(100L)
                .build();

        assertThat(user.getName()).isEqualTo("tester");
        assertThat(user.isAdmin()).isEqualTo(true);
        assertThat(user.isActive()).isEqualTo(true);

        user.deactive();
        assertThat(user.isActive()).isEqualTo(false);

    }

}