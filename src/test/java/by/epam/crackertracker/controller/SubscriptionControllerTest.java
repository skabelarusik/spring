package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.SecurityConfig;
import by.epam.crackertracker.config.SpringConfig;
import by.epam.crackertracker.config.WebConfig;
import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.entity.TrackerSubscription;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ReviewService;
import by.epam.crackertracker.service.SubscriptionService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.test.web.servlet.MockMvc;
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
public class SubscriptionControllerTest {

    @Mock
    private SubscriptionService service;

    @InjectMocks
    private SubscriptionController controller;

    private MockMvc mockMvc;

    private List<TrackerSubscription> subsList;

    {
        subsList = new ArrayList<>();
        subsList.add(new TrackerSubscription("Subs1", "TEXT1", LocalDate.now(), LocalDate.now().plusDays(1)));
        subsList.add(new TrackerSubscription("Subs2", "TEXT2", LocalDate.now(), LocalDate.now().plusDays(1)));
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testSelectSubs() throws Exception {
        when(service.selectAllSubscriptions()).thenReturn(subsList);
        mockMvc.perform(get(PageConstant.URI_SUBS_SELEC))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_SUBSCRIBERS, subsList))
                .andExpect(model().attribute(ParameterConstant.MESSAGE_HAVE_SUBS, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_SUBSCRIPTION));
    }

    @Test
    public void testWrongSelectSubs() throws Exception {
        doThrow(TrackerServiceException.class).when(service).selectAllSubscriptions();
        mockMvc.perform(get(PageConstant.URI_SUBS_SELEC))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_HAVE_SUBS, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_SUBSCRIPTION));
    }

    @Test
    public void testSelectCuratorsSubs() throws Exception {
        when(service.selectAllSubscriptions()).thenReturn(subsList);
        mockMvc.perform(get(PageConstant.URI_SUBS_SELEC))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_SUBSCRIBERS, subsList))
                .andExpect(model().attribute(ParameterConstant.MESSAGE_HAVE_SUBS, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_SUBSCRIPTION));
    }

}
