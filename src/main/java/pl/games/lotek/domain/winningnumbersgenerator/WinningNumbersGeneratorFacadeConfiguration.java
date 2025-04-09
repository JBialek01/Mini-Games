package pl.games.lotek.domain.winningnumbersgenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WinningNumbersGeneratorFacadeConfiguration {

    @Bean
     WinningNumbersGeneratorFacade winningNumbersGeneratorFacade(WinningNumbersRepository winningNumbersRepository) {
         WinningNumbersGenerator winningNumbersGenerator = new WinningNumbersGenerator(winningNumbersRepository);
            return new WinningNumbersGeneratorFacade(winningNumbersGenerator);
     }
}
