package feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.games.MainSpringBoot;
import pl.games.lotek.domain.resultchecker.ResultCheckerFacade;
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = MainSpringBoot.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class LotekHappyPathIntegrationTestWithFilledDb {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.4");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ResultCheckerFacade resultCheckerFacade;

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    private MockHttpServletRequestBuilder authenticatedGet(String url) {
        return get(url)
                .with(oauth2Login()
                        .oauth2User(new DefaultOAuth2User(
                                Set.of(),
                                Map.of("sub", "userId", "name", "Test User"),
                                "name"
                        ))
                );
    }

    private MockHttpServletRequestBuilder authenticatedPost(String url) {
        return post(url)
                .with(oauth2Login()
                        .oauth2User(new DefaultOAuth2User(
                                Set.of(),
                                Map.of("sub", "userId", "name", "Test User"),
                                "name"
                        ))
                );
    }

    @BeforeEach
    public void setup() {
        mongoTemplate.remove(new Query(), "lotek_userResults");
        UserResultsDto testResult = new UserResultsDto(
                "userId",
                Instant.now().minusSeconds(86400).atZone(ZoneId.systemDefault()),
                Set.of(1, 2, 3, 4, 5, 6),
                Set.of(1, 2, 3, 4, 7, 8),
                4
        );
        resultCheckerFacade.saveUserResult(testResult);
        UserResultsDto testResult2 = new UserResultsDto(
                "userId2",
                Instant.now().minusSeconds(86400).atZone(ZoneId.systemDefault()),
                Set.of(3, 5, 7, 9, 11, 13),
                Set.of(1, 2, 3, 4, 7, 8),
                1
        );
        resultCheckerFacade.saveUserResult(testResult2);
        UserResultsDto testResult3 = new UserResultsDto(
                "userId",
                Instant.now().minusSeconds(259200).atZone(ZoneId.systemDefault()),
                Set.of(1, 2, 3, 4, 5, 6),
                Set.of(2, 4, 6, 8, 10, 12),
                3
        );
        resultCheckerFacade.saveUserResult(testResult3);
        UserResultsDto testResult4 = new UserResultsDto(
                "userId2",
                Instant.now().minusSeconds(259200).atZone(ZoneId.systemDefault()),
                Set.of(3, 5, 7, 9, 10, 12),
                Set.of(2, 4, 6, 8, 10, 12),
                2
        );
        resultCheckerFacade.saveUserResult(testResult4);
    }


    @Test
    public void happyPathIntegrationTestWithFilledDb() throws Exception {

        // 1. when I go to /results then I can see yesterday result
        mockMvc.perform(authenticatedGet("/results")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].userNumbers", containsInAnyOrder(1,2,3,4,5,6)))
                .andExpect(jsonPath("$.[0].winningNumbers", containsInAnyOrder(1,2,3,4,7,8)))
                .andExpect(jsonPath("$.[0].hits", is(4)));

        // 2. when I go to /ranking then I can see yesterday ranking with 2 users
        mockMvc.perform(authenticatedGet("/ranking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].userId", is("userId")))
                .andExpect(jsonPath("$.[0].hits", is(4)))
                .andExpect(jsonPath("$.[1].userId", is("userId2")))
                .andExpect(jsonPath("$.[1].hits", is(1)));

        // 3. when I submit ticket to /lotekSubmitTicket then submitted numbers and message is returned
        mockMvc.perform(authenticatedPost("/lotekSubmitTicket")
                        .content("""
                                {
                                    "numbers": [1, 2, 3, 4, 5, 6]
                                }
                                """.trim())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numbers", containsInAnyOrder(1, 2, 3, 4, 5, 6)))
                .andExpect(jsonPath("$.message", is("Ticket submitted!")));

        // 4. when I go to /results/3 then I can see results for 3 days ago
        mockMvc.perform(authenticatedGet("/results/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].userNumbers", containsInAnyOrder(1, 2, 3, 4, 5, 6)))
                .andExpect(jsonPath("$.[0].winningNumbers", containsInAnyOrder(2, 4, 6, 8, 10, 12)))
                .andExpect(jsonPath("$.[0].hits", is(3)));

        // 5. when I go to /ranking/3 then I can see ranking for 3 days ago
        mockMvc.perform(authenticatedGet("/ranking/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].userId", is("userId")))
                .andExpect(jsonPath("$.[0].hits", is(3)))
                .andExpect(jsonPath("$.[1].userId", is("userId2")))
                .andExpect(jsonPath("$.[1].hits", is(2)));
    }
}
