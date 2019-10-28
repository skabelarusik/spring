package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.SecurityConfig;
import by.epam.crackertracker.config.SpringConfig;
import by.epam.crackertracker.config.WebConfig;
import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.TrackerSubscription;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ReviewService;
import by.epam.crackertracker.service.SubscriptionService;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.helpers.Loader;
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
import org.springframework.ui.Model;

import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static by.epam.crackertracker.utils.TestParametres.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    private MockMvc mockMvc;

    private List<User> userList;

    {
        userList = new ArrayList<>();
        userList.add(new User("Login1", "Password1"));
        userList.add(new User("Login2", "Password2"));
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testUpdateUser() throws Exception {
        doNothing().when(service).updateDataUser(LOGIN_FIRST, LOGIN_FIRST, LOGIN_FIRST, LOGIN_FIRST, LOGIN_FIRST);
        mockMvc.perform(post(PageConstant.URI_FULL_UPDATE_USER).param(ParameterConstant.PARAM_NAME, LOGIN_FIRST)
            .param(ParameterConstant.PARAM_SURNAME, LOGIN_FIRST)
            .param(ParameterConstant.PARAM_EMAIL, LOGIN_FIRST)
            .param(ParameterConstant.PARAM_BIRTHDAY, LOGIN_FIRST)
            .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(view().name("redirect:" + PageConstant.PATH_PAGE_EDIT_USER));
    }


    @Test
    public void testWrongUpdateUser() throws Exception {
        doThrow(TrackerServiceException.class).when(service).updateDataUser(LOGIN_FIRST, LOGIN_FIRST, LOGIN_FIRST, LOGIN_FIRST, LOGIN_FIRST);
        mockMvc.perform(post(PageConstant.URI_FULL_UPDATE_USER).param(ParameterConstant.PARAM_NAME, LOGIN_FIRST)
                .param(ParameterConstant.PARAM_SURNAME, LOGIN_FIRST)
                .param(ParameterConstant.PARAM_EMAIL, LOGIN_FIRST)
                .param(ParameterConstant.PARAM_BIRTHDAY, LOGIN_FIRST)
                .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_EDIT_USER));
    }

    @Test
    public void testUpdateUserShouldForward() throws Exception {
        mockMvc.perform(get(PageConstant.URI_FULL_UPDATE_USER))
                .andDo(print())
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_EDIT_USER));
    }

    @Test
    public void testRegistr() throws Exception {
        mockMvc.perform(get(PageConstant.URI_USER_REGISTER))
                .andDo(print())
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_REGISTER));
    }

    @Test
    public void testDepositPage() throws Exception {
        mockMvc.perform(get(PageConstant.URI_DEP))
                .andDo(print())
                .andExpect(forwardedUrl(PageConstant.PATH_DEPOSIT));
    }


    @Test
    public void testSelectAllAdmins() throws Exception {
        when(service.selectAllAdmins()).thenReturn(userList);
        mockMvc.perform(get(PageConstant.URI_USER_REQUEST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userList))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_USER));
    }

}
