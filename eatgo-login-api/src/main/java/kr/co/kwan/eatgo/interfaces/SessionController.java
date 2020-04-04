package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.UserService;
import kr.co.kwan.eatgo.domain.User;
import kr.co.kwan.eatgo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/session")
    public ResponseEntity<?> create(@RequestBody SessionRequestDto resource) throws URISyntaxException {
        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);

        String accessToken = jwtUtil.createToken(user.getId(), user.getName(),
                user.isRestaurantOwner() ? user.getRestaurantId() : null);

        SessionResponseDto sessionDto = SessionResponseDto.builder()
                                                          .accessToken(accessToken)
                                                          .build();

        String url = "/session";
        return ResponseEntity.created(new URI(url)).body(sessionDto);
    }
}
