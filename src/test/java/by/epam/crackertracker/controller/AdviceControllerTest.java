package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.*;
import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import static by.epam.crackertracker.utils.TestParametres.*;
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
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.*;

import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class AdviceControllerTest {

    private MockMvc mvc;

    @Mock
    private AdviceService service;

    @InjectMocks
    private AdviceController adviceController;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(adviceController).build();
    }

    @Test
    public void testSelectAdvice() throws Exception {
        List<Advice> list = new ArrayList<>();
        list.add(new Advice("Advice1"));
        list.add(new Advice("Advice2"));
        when(service.selectAllAdvices()).thenReturn(list);
        mvc.perform(get(PageConstant.SECUR_PATH_ADVICE_SELECT))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, list))
                .andExpect(view().name(PageConstant.PATH_RESULT_ADVICE));
    }

    @Test
    public void testAddAdviceShouldBeRedirect() throws Exception {
        mvc.perform(post(PageConstant.SECUR_PATH_ADVICE_ADD).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN)
                .param(ParameterConstant.PARAM_ADVICE, VALIDE_TEXT)).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(view().name(REDIRECT_ADMIN));
    }

    @Test
    public void testAddLongAdviceShouldBeWrongMessage() throws Exception {
        doThrow(TrackerServiceException.class).when(service).addAdvice(LONGER);
        mvc.perform(post(PageConstant.SECUR_PATH_ADVICE_ADD).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN)
                .param(ParameterConstant.PARAM_ADVICE, LONGER))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(view().name(ADMIN));
    }

    @Test
    public void testAddEmptyAdviceShouldBeWrongMessage() throws Exception {
        doThrow(TrackerServiceException.class).when(service).addAdvice(EMPTY);
        mvc.perform(post(PageConstant.SECUR_PATH_ADVICE_ADD).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN)
                .param(ParameterConstant.PARAM_ADVICE, EMPTY))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(view().name(ADMIN));
    }

    @Test
    public void testDeleteAdvice() throws Exception {
        List<Advice> list = new ArrayList<>();
        list.add(new Advice("Advice1"));
        list.add(new Advice("Advice2"));
        when(service.selectAllAdvices()).thenReturn(list);
        mvc.perform(post(PageConstant.SECUR_PATH_ADVICE_DELETE).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN).
                param(ParameterConstant.PARAM_ID, GOOD_ID))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_ADD_DELETE_MESS, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, list))
                .andExpect(view().name(ADMIN));
    }

    @Test
    public void testDeleteAdviceWrongId() throws Exception {
        List<Advice> list = new ArrayList<>();
        list.add(new Advice("Advice1"));
        list.add(new Advice("Advice2"));
        when(service.selectAllAdvices()).thenReturn(list);
        doThrow(TrackerServiceException.class).when(service).deleteAdviceById(WRONG_ID);
        mvc.perform(post(PageConstant.SECUR_PATH_ADVICE_DELETE).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN).
                param(ParameterConstant.PARAM_ID, WRONG_ID))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_ADD_DELETE_MESS, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, list))
                .andExpect(view().name(ADMIN));
    }
}