package by.epam.crackertracker.controller;


import by.epam.crackertracker.config.WebAppInitializer;
import by.epam.crackertracker.config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebAppInitializer.class,
        WebConfig.class})
@WebAppConfiguration
public class AdviceControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mvc;

    @BeforeEach
    public void serUp(){
        mvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void indexShouldToAdminPage() {
      //  mvc.perform();
    }


}
