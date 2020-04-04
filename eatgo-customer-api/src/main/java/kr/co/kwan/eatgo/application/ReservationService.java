package kr.co.kwan.eatgo.application;

import kr.co.kwan.eatgo.domain.Reservation;
import kr.co.kwan.eatgo.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation addReservation(Long restaurantId, Long userId, String name, String date, String time, Integer partySize) {
        // TODO:
         Reservation reservation =  Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

         return reservationRepository.save(reservation);
    }
}
