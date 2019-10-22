package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.*;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.utils.TestParametres;
import by.epam.crackertracker.validator.AdviceLengthValidator;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class AdviceControllerTest {


    private MockMvc mvc;

    @Mock
    private UserPrincipal userPrincipal;

    @InjectMocks
    private AdviceController adviceController;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(adviceController).build();
    }

    @Test
    public void testSelectAdvice() throws Exception {
    //    when(userService.selectAllUsers(null, null)..thenReturn(true));
        mvc.perform(get(PageConstant.SECUR_PATH_ADVICE_SELECT))
                .andDo(print())
                .andExpect(view().name(PageConstant.PATH_RESULT_ADVICE));
    }

    @Test
    public void testAddAdviceShouldBeRedirect() throws Exception {
        mvc.perform(post(PageConstant.SECUR_PATH_ADVICE_ADD).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE,ParameterConstant.ROLE_ADMIN.toLowerCase())
                        .param(ParameterConstant.PARAM_ADVICE, TestParametres.VALIDE_TEXT)).andDo(print())
                        .andExpect(status().is3xxRedirection())
                        .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_CONGRAT))
                        .andExpect(view().name("redirect:/admin"));
    }

    @Test
    public void testAddNullAdviceShouldBeWrongMessage() throws Exception {
        mvc.perform(post(PageConstant.SECUR_PATH_ADVICE_ADD).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE,ParameterConstant.ROLE_ADMIN.toLowerCase())
                .param(ParameterConstant.PARAM_ADVICE, longer))
                 .andDo(print())
                .andExpect(model().attribute("addmessage", "congratulations!"))
                .andExpect(view().name("redirect:/admin"));
    }


    String longer = "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
    "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "       asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
    "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc";




    //    @PostMapping(PageConstant.URI_ADD)
//    public String addAdvice(@RequestParam String currentPage, @RequestParam String advice, Model model){
//        try {
//            adviceService.addAdvice(advice);
//            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_CONGRAT);
//        } catch (TrackerServiceException e) {
//            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_ERROR_REGIST);
//            return currentPage;
//        }
//        return "redirect:/" + currentPage;
//    }

}
//
//@Controller
//@RequestMapping(PageConstant.PATH_ADVICE)
//public class AdviceController {
//
//    @Autowired
//    private AdviceService adviceService;
//
//    @GetMapping(PageConstant.URI_SELECT)
//    public String selectAdvice(Model model){
//        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, adviceService.selectAllAdvices());
//        return PageConstant.PATH_RESULT_ADVICE;
//    }
//

//
//    @PostMapping(PageConstant.URI_DELETE)
//    public String delete(@RequestParam String currentPage, @RequestParam String id, Model model){
//        try {
//            adviceService.deleteAdviceById(id);
//            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, adviceService.selectAllAdvices());
//            model.addAttribute(ParameterConstant.ATTRIBUTE_ADD_DELETE_MESS, ParameterConstant.MESSAGE_CONGRAT);
//        } catch (TrackerServiceException e) {
//            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, adviceService.selectAllAdvices());
//            model.addAttribute(ParameterConstant.ATTRIBUTE_ADD_DELETE_MESS, ParameterConstant.MESSAGE_ERROR_REGIST);
//        }
//        return currentPage;
//    }
//}
//
