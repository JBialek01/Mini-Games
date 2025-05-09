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
import pl.games.lotek.domain.resultchecker.dto.UserResultsDto;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MainSpringBoot.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class LotekHappyPathIntegrationTestWithEmptyDb {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.4");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

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
    }

    @Test
    public void happyPathIntegrationTestWithEmptyDb() throws Exception {

// 1. when I go to /results then I can see no results
        mockMvc.perform(authenticatedGet("/results")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", empty()));

// 2. when I go to /ranking then I can see empty ranking
        mockMvc.perform(authenticatedGet("/ranking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));

// 3. when I submit ticket to /lotekSubmitTicket then submitted numbers and message is returned
        mockMvc.perform(authenticatedPost("/lotekSubmitTicket")
                        .content("""
                                {
                                    "numbers": [1, 2, 3, 4, 5, 6]
                                }
                                """.trim())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numbers", containsInAnyOrder(1, 2, 3, 4, 5, 6)))
                .andExpect(jsonPath("$.message", is("Ticket submitted!")));

// 4. when I go to /results/0 then can see no results
        mockMvc.perform(authenticatedGet("/results/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", empty()));

// 5. when I go to /ranking/0 then can see empty ranking
        mockMvc.perform(authenticatedGet("/ranking/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));
    }
}
