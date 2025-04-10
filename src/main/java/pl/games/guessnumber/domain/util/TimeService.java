package pl.games.guessnumber.domain.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class TimeService {

    public static Instant getStartOfSpecifiedDay(final Long daysToSubtract) {
        return LocalDate.now(ZoneOffset.UTC)
                .minusDays(daysToSubtract)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);
    }

    public static Instant getEndOfSpecifiedDay(final Long daysToSubtract) {
        return LocalDate.now(ZoneOffset.UTC)
                .minusDays(daysToSubtract)
                .atTime(23, 59, 59, 999_999_999)
                .toInstant(ZoneOffset.UTC);
    }
}
