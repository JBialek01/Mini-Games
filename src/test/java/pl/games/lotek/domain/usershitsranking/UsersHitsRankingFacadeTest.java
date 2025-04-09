package pl.games.lotek.domain.usershitsranking;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.games.lotek.domain.resultchecker.ResultCheckerFacade;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;
import pl.games.lotek.domain.ticketsreceiver.TicketsReceiverFacade;
import pl.games.lotek.domain.usershitsranking.dto.UsersHitsRankingDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsersHitsRankingFacadeTest {

    TicketsReceiverFacade ticketsReceiverFacade = mock(TicketsReceiverFacade.class);
    ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);

    private final UsersHitsRankingRepository usersHitsRankingRepository = new UsersHitsRankingRepositoryTestImpl();
    UsersHitsRankingFacade usersHitsRankingFacade = new UsersHitsRankingFacade(
            new UsersHitsRankingGenerator(
                    resultCheckerFacade,
                    new UserBestHitsCalculator(),
                    new RankingEntriesSaver(usersHitsRankingRepository),
                    new AllTicketsChecker(ticketsReceiverFacade, resultCheckerFacade)),
            new UsersHitsRankingRetriever(usersHitsRankingRepository));


    @Test
    @DisplayName("It should return the best yesterday result for each user sorted descending by the highest number of hits")
    public void it_should_return_the_best_yesterday_result_for_each_user_sorted_descending_by_the_highest_number_of_hits(){
        //given
        List<UserResultsDto> userResultsDtos = new ArrayList<>();
        userResultsDtos.add(new UserResultsDto("user1", ZonedDateTime.now().minusDays(1), Set.of(1, 2, 3, 4, 9, 10), Set.of(1, 2, 3, 4, 5, 6), 4));
        userResultsDtos.add(new UserResultsDto("user1", ZonedDateTime.now().minusDays(1), Set.of(1, 22, 21, 4, 9, 10), Set.of(1, 2, 3, 4, 5, 6), 1));
        userResultsDtos.add(new UserResultsDto("user2", ZonedDateTime.now().minusDays(1), Set.of(1, 2, 3, 7, 9, 10), Set.of(1, 2, 3, 4, 5, 6), 3));
        userResultsDtos.add(new UserResultsDto("user2", ZonedDateTime.now().minusDays(1), Set.of(1, 2, 33, 7, 9, 10), Set.of(1, 2, 3, 4, 5, 6), 2));
        userResultsDtos.add(new UserResultsDto("user3", ZonedDateTime.now().minusDays(1), Set.of(1, 2, 3, 4, 5, 10), Set.of(1, 2, 3, 4, 5, 6), 5));
        when(resultCheckerFacade.findByDateBetween(any(), any())).thenReturn(userResultsDtos);
        usersHitsRankingFacade.generateRankingForYesterday();
        //when
        List<UsersHitsRankingDto> result = usersHitsRankingFacade.fetchAllRankingEntries();
        //then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).userId()).isEqualTo("user3");
        assertThat(result.get(0).hits()).isEqualTo(5);
        assertThat(result.get(1).userId()).isEqualTo("user1");
        assertThat(result.get(1).hits()).isEqualTo(4);
        assertThat(result.get(2).userId()).isEqualTo("user2");
        assertThat(result.get(2).hits()).isEqualTo(3);
    }

    @Test
    @DisplayName("It should return the best result of specified date for each user sorted descending by the highest number of hits")
    public void it_should_return_the_best_result_of_specified_date_for_each_user_sorted_descending_by_the_highest_number_of_hits(){
        //given
        List<UserResultsDto> userResultsDtos = new ArrayList<>();
        userResultsDtos.add(new UserResultsDto("user1", ZonedDateTime.now().minusDays(5), Set.of(1, 2, 3, 4, 9, 10), Set.of(1, 2, 3, 4, 5, 6), 4));
        userResultsDtos.add(new UserResultsDto("user1", ZonedDateTime.now().minusDays(5), Set.of(1, 22, 21, 4, 9, 10), Set.of(1, 2, 3, 4, 5, 6), 1));
        userResultsDtos.add(new UserResultsDto("user2", ZonedDateTime.now().minusDays(5), Set.of(1, 2, 3, 7, 9, 10), Set.of(1, 2, 3, 4, 5, 6), 3));
        userResultsDtos.add(new UserResultsDto("user2", ZonedDateTime.now().minusDays(5), Set.of(1, 2, 33, 7, 9, 10), Set.of(1, 2, 3, 4, 5, 6), 2));
        userResultsDtos.add(new UserResultsDto("user3", ZonedDateTime.now().minusDays(5), Set.of(1, 2, 3, 4, 5, 10), Set.of(1, 2, 3, 4, 5, 6), 5));
        when(resultCheckerFacade.findByDateBetween(any(), any())).thenReturn(userResultsDtos);
        when(resultCheckerFacade.checkAndSaveResults(any())).thenReturn(userResultsDtos);
        usersHitsRankingFacade.generateRankingForSpecifiedDay(5L);
        //when
        List<UsersHitsRankingDto> result = usersHitsRankingFacade.fetchAllRankingEntriesByDay(5L);
        //then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).userId()).isEqualTo("user3");
        assertThat(result.get(0).hits()).isEqualTo(5);
        assertThat(result.get(1).userId()).isEqualTo("user1");
        assertThat(result.get(1).hits()).isEqualTo(4);
        assertThat(result.get(2).userId()).isEqualTo("user2");
        assertThat(result.get(2).hits()).isEqualTo(3);
    }


    @Test
    public void it_should_generate_users_hits_ranking_for_yesterday(){
        //given
        //when
        String result = usersHitsRankingFacade.generateRankingForYesterday();
        //then
        assertThat(result).isEqualTo("Ranking for previous day generated successfully");
    }

    @Test
    public void it_should_generate_users_hits_ranking_for_specified_day(){
        //given
        //when
        String result = usersHitsRankingFacade.generateRankingForSpecifiedDay(5L);
        //then
        assertThat(result).isEqualTo("Ranking for specified day generated successfully");
    }
}