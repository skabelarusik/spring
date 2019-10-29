package by.epam.crackertracker.controller;
import by.epam.crackertracker.config.SecurityConfig;
import by.epam.crackertracker.config.SpringConfig;
import by.epam.crackertracker.config.WebConfig;
import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.MessageService;
import by.epam.crackertracker.service.ReviewService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
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
public class MessageControllerTest {

    @Mock
    private MessageService service;

    @InjectMocks
    private MessageController controller;

    private MockMvc mock;

    private List<Message> messageList;

    {
        messageList = new ArrayList<>();
        messageList.add(new Message());
        messageList.add(new Message());
    }


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mock = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testCheckInputMessage() throws Exception {
        User user = new User();
        user.setLogin(LOGIN_FIRST);
        when(service.checkInputMessage(LOGIN_FIRST, START_PAGE)).thenReturn(messageList);
        mock.perform(get(PageConstant.URI_INPUT_MESSAGE_FULL).sessionAttr(ParameterConstant.USER, user))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGES, messageList))
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_MESSAGE));
    }

    @Test
    public void testCheckOutputMessage() throws Exception {
        User user = new User();
        user.setLogin(LOGIN_FIRST);
        when(service.checkOutputMessage(LOGIN_FIRST, START_PAGE)).thenReturn(messageList);
        mock.perform(get(PageConstant.URI_OUTPUT_MESSAGE_FULL).sessionAttr(ParameterConstant.USER, user))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGES, messageList))
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_MESSAGE));
    }

    @Test
    public void testWriteMessage() throws Exception {
        mock.perform(get(PageConstant.URI_OUTPUT_MESSAGE_SEND).param(ParameterConstant.PARAM_RECIPIENT, LOGIN_FIRST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.PARAM_RECIPIENT, LOGIN_FIRST))
                .andExpect(forwardedUrl(PageConstant.PATH_SEND_MESSAGE));
    }

    @Test
    public void testSendMessage() throws Exception {
        mock.perform(get(PageConstant.URI_MESSAGE_SEND_FORM))
                .andDo(print())
                .andExpect(forwardedUrl(PageConstant.PATH_SEND_MESSAGE));
    }

    @Test
    public void testSendMessageShouldRedirect() throws Exception {
        doNothing().when(service).sendMessage(LOGIN_FIRST, LOGIN_SECOND, TOPIC, MESSAGE);
        mock.perform(post(PageConstant.URI_OUTPUT_MESSAGE_SEND).sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST)
            .param(ParameterConstant.PARAM_RECIPIENT, LOGIN_SECOND)
            .param(ParameterConstant.PARAM_TOPIC, TOPIC)
            .param(ParameterConstant.TEXT, MESSAGE))
            .andDo(print())
            .andExpect(model().attribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_CONGRAT))
            .andExpect(view().name(REDIRECT + PageConstant.PATH_SEND_MESSAGE));
    }

    @Test
    public void testSendMessageWrong() throws Exception {
        doThrow(TrackerServiceException.class).when(service).sendMessage(LOGIN_FIRST, LOGIN_SECOND, TOPIC, MESSAGE);
        mock.perform(post(PageConstant.URI_OUTPUT_MESSAGE_SEND).sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST)
                .param(ParameterConstant.PARAM_RECIPIENT, LOGIN_SECOND)
                .param(ParameterConstant.PARAM_TOPIC, TOPIC)
                .param(ParameterConstant.TEXT, MESSAGE))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(forwardedUrl(PageConstant.PATH_SEND_MESSAGE));
    }

    @Test
    public void testDeleteMessageInput() throws Exception {
        doNothing().when(service).deleteMessage(GOOD_ID, ParameterConstant.INPUT_MESSAGE);
        when(service.checkInputMessage(LOGIN_FIRST, START_PAGE)).thenReturn(messageList);
        mock.perform(post(PageConstant.URI_MESSAGE_DELETE).param(ParameterConstant.PARAM_ID, GOOD_ID)
            .param(ParameterConstant.PARAM_SENDER, LOGIN_SECOND)
            .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST))
            .andDo(print())
            .andExpect(model().attribute(ParameterConstant.MESSAGES, messageList))
            .andExpect(forwardedUrl(PageConstant.PATH_PAGE_MESSAGE));
    }

    @Test
    public void testDeleteMessageOutput() throws Exception {
        doNothing().when(service).deleteMessage(GOOD_ID, ParameterConstant.OUTPUT_MESSAGE);
        when(service.checkOutputMessage(LOGIN_FIRST, START_PAGE)).thenReturn(messageList);
        mock.perform(post(PageConstant.URI_MESSAGE_DELETE).param(ParameterConstant.PARAM_ID, GOOD_ID)
                .param(ParameterConstant.PARAM_SENDER, LOGIN_FIRST)
                .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGES, messageList))
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_MESSAGE));
    }

    @Test
    public void testDeleteMessageWrong() throws Exception {
        doThrow(TrackerServiceException.class).when(service).deleteMessage(WRONG_ID, ParameterConstant.OUTPUT_MESSAGE);
        mock.perform(post(PageConstant.URI_MESSAGE_DELETE).param(ParameterConstant.PARAM_ID, WRONG_ID)
                .param(ParameterConstant.PARAM_SENDER, LOGIN_FIRST)
                .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST))
                .andDo(print())
                .andExpect(forwardedUrl(PageConstant.PATH_PAGE_ERROR));
    }

}
