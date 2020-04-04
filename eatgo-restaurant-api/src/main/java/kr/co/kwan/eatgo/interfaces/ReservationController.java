package kr.co.kwan.eatgo.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.kwan.eatgo.application.ReservationService;
import kr.co.kwan.eatgo.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public List<Reservation> list(Authentication authentication) {

        Claims claims = (Claims) authentication.getPrincipal();
        Long restaurantId = claims.get("restaurantId", Long.class);

        List<Reservation> reservations = reservationService.getReservations(restaurantId);

        return reservations;
    }
}
