//package pl.games.auth;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/lotek/**").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(new OidcUserService()))
//                )
//                .logout(logout -> logout.logoutSuccessUrl("/").permitAll());
//        return http.build();
//    }
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
//    }
//
//    private ClientRegistration googleClientRegistration() {
//        return ClientRegistration.withRegistrationId("google")
//                .clientId("YOUR_GOOGLE_CLIENT_ID")
//                .clientSecret("YOUR_GOOGLE_CLIENT_SECRET")
//                .scope("openid", "profile", "email")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("{baseUrl}/login/oauth2/code/google")
//                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
//                .tokenUri("https://oauth2.googleapis.com/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//                .clientName("Google")
//                .build();
//    }
//}
