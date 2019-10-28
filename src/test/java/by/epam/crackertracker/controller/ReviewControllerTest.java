package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.SecurityConfig;
import by.epam.crackertracker.config.SpringConfig;
import by.epam.crackertracker.config.WebConfig;
import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerServiceException;
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
public class ReviewControllerTest {

    @Mock
    private ReviewService service;

    @InjectMocks
    private ReviewController controller;

    private MockMvc mockMvc;

    private List<Review> reviewList;

    {
        reviewList = new ArrayList<>();
        reviewList.add(new Review("Review1", "TEXT1", LocalDate.now()));
        reviewList.add(new Review("Review2", "TEXT2", LocalDate.now()));
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testShowReview() throws Exception {
        when(service.selectAllReview(START_PAGE)).thenReturn(reviewList);
        mockMvc.perform(get(PageConstant.PATH_REVIEW_SHOW))
                .andDo(print())
                .andExpect(model().attribute((ParameterConstant.ATTRIBUTE_LIST_REVIEW), reviewList))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_REVIEW));
    }

    @Test
    public void testShowDeleteReview() throws Exception {
        when(service.selectAllReview(ParameterConstant.SHOW_DELETED)).thenReturn(reviewList);
        mockMvc.perform(get(PageConstant.PATH_REVIEW_SHOW_DEL))
                .andDo(print())
                .andExpect(model().attribute((ParameterConstant.ATTRIBUTE_LIST_REVIEW), reviewList))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_REVIEW));
    }

    @Test
    public void testDeleteReview() throws Exception {
        doNothing().when(service).deleteById(GOOD_ID, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        when(service.selectAllReview(ParameterConstant.SHOW)).thenReturn(reviewList);
        mockMvc.perform(post(PageConstant.SECUR_PATH_REVIEW_DEL).param(ParameterConstant.PARAM_ID, GOOD_ID)
            .param(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_REVIEW, reviewList))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_REVIEW));
    }

    @Test
    public void testDeleteReviewWrongData() throws Exception {
        doThrow(TrackerServiceException.class).when(service).deleteById(GOOD_ID, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        mockMvc.perform(post(PageConstant.SECUR_PATH_REVIEW_DEL).param(ParameterConstant.PARAM_ID, GOOD_ID)
                .param(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE))
                .andDo(print())
                .andExpect(view().name( PageConstant.PATH_PAGE_ERROR));
    }

    @Test
    public void testDeleteReviewById() throws Exception {
        doNothing().when(service).deleteById(GOOD_ID, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        mockMvc.perform(post(PageConstant.SECUR_PATH_REVIEW_DELETE).param(ParameterConstant.PARAM_ID, GOOD_ID)
                .param(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE)
                .param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(view().name(REDIRECT_ADMIN));
    }

    @Test
    public void testDeleteReviewByWrongId() throws Exception {
        doThrow(TrackerServiceException.class).when(service).deleteById(WRONG_ID, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        mockMvc.perform(post(PageConstant.SECUR_PATH_REVIEW_DELETE).param(ParameterConstant.PARAM_ID, WRONG_ID)
                .param(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE)
                .param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(forwardedUrl(ADMIN));
    }

    @Test
    public void testSendReview() throws Exception {
        doNothing().when(service).sendReview(LOGIN_FIRST, VALIDE_TEXT);
        mockMvc.perform(post(PageConstant.SECUR_PATH_REVIEW_SEND).sessionAttr(ParameterConstant.PARAM_LOGIN, LOGIN_FIRST)
            .param(ParameterConstant.TEXT, VALIDE_TEXT))
            .andDo(print())
            .andExpect(model().attribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_CONGRAT))
            .andExpect(view().name("redirect:" + PageConstant.PATH_PAGE_SEND_REVIEW));
    }

    @Test
    public void testSendWrongReview() throws Exception {
        doThrow(TrackerServiceException.class).when(service).sendReview(LOGIN_FIRST, VALIDE_TEXT);
        mockMvc.perform(post(PageConstant.SECUR_PATH_REVIEW_SEND).sessionAttr(ParameterConstant.PARAM_LOGIN, LOGIN_FIRST)
                .param(ParameterConstant.TEXT, VALIDE_TEXT))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.WRONG_DATA))
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_SEND_REVIEW));
    }

    @Test
    public void testSendReviewShouldGoToForm() throws Exception {
        mockMvc.perform(get(PageConstant.SECUR_PATH_REVIEW_SEND))
                .andDo(print())
                .andExpect(forwardedUrl( PageConstant.PATH_PAGE_SEND_REVIEW));
    }



}
