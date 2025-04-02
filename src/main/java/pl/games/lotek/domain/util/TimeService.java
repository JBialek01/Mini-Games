package pl.games.lotek.domain.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class TimeService {

    public static Instant getStartOfPreviousUtcDay() {
        return LocalDate.now(ZoneOffset.UTC)
                .minusDays(1)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);
    }

    public static Instant getEndOfPreviousUtcDay() {
        return LocalDate.now(ZoneOffset.UTC)
                .minusDays(1)
                .atTime(23, 59, 59, 999_999_999)
                .toInstant(ZoneOffset.UTC);
    }
}
