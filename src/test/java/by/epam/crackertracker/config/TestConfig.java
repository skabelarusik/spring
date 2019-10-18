package by.epam.crackertracker.config;

import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.validator.AdviceLengthValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public AdviceLengthValidator adviceValidator(){
        return new AdviceLengthValidator();
    }

    @Bean
    public AdviceService adviceService(){
        return mock(AdviceService.class);
    }
}
