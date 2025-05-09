package pl.games.guessnumber.domain;

import org.springframework.stereotype.Service;
import pl.games.guessnumber.domain.dto.DrawDateDto;
import pl.games.guessnumber.domain.util.TimeService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
class DrawDateRetriever {
    DrawDateDto getNextDrawDate() {
        Instant nextDrawDate = TimeService.getStartOfSpecifiedDay(-1L);
        return new DrawDateDto("Next draw date:", LocalDateTime.ofInstant(nextDrawDate, ZoneId.systemDefault()));
    }
}
