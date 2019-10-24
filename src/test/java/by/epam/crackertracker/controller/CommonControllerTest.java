package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.SecurityConfig;
import by.epam.crackertracker.config.SpringConfig;
import by.epam.crackertracker.config.UserPrincipal;
import by.epam.crackertracker.config.WebConfig;
import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.CalculatorService;
import by.epam.crackertracker.service.ReviewService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static by.epam.crackertracker.utils.TestParametres.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class CommonControllerTest {

    @Mock
    private CalculatorService service;

    @InjectMocks
    private CommonController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get(PageConstant.PATH_PAGE_INDEX))
                .andDo(print())
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_MAIN_INDEX));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get(PageConstant.LOGOUT))
                .andDo(print())
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_MAIN_INDEX));
    }

    @Test
    public void testLogoutSuccess() throws Exception {
        mockMvc.perform(get(PageConstant.LOGOUT_SUCCESS))
                .andDo(print())
                .andExpect(forwardedUrl(ParameterConstant.LOGIN));
    }

}
