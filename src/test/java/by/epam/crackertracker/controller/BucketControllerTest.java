package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.SecurityConfig;
import by.epam.crackertracker.config.SpringConfig;
import by.epam.crackertracker.config.UserPrincipal;
import by.epam.crackertracker.config.WebConfig;
import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.BucketService;
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

import java.util.*;
import java.util.ArrayList;

import static by.epam.crackertracker.utils.TestParametres.ADMIN;
import static by.epam.crackertracker.utils.TestParametres.GOOD_ID;
import static org.mockito.Mockito.when;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class BucketControllerTest {

    private MockMvc mvc;

    @Mock
    private BucketService service;

    @InjectMocks
    private BucketControllerTest controller;

    @Mock
    private UserPrincipal userPrincipal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testAddProductToBucket() throws Exception {
        List<Bucket> list = new ArrayList();
        list.add(new Bucket("test", "test", 3, 2.));
//        when(userPrincipal.getUsername()).thenReturn("andrey");
//        when(service.addProduct("andrey","COLA", "5")).thenReturn(list);

        mvc.perform(post(PageConstant.PATH_BUCKET_ADD).param(ParameterConstant.START_PAGE, ADMIN))
   //             .param(ParameterConstant.PARAM_NAME_PRODUCT, "COLA").param(ParameterConstant.PARAM_PORTIONS, GOOD_ID))
                .andDo(print())
//                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET, list))
                .andExpect(view().name(ADMIN));
    }

//    @Test
//    public void testAddLongAdviceShouldBeWrongMessage() throws Exception {
//        doThrow(TrackerServiceException.class).when(service).addAdvice(LONGER);
//        mvc.perform(post(PageConstant.SECUR_PATH_ADVICE_ADD).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN)
//                .param(ParameterConstant.PARAM_ADVICE, LONGER))
//                .andDo(print())
//                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_ERROR_REGIST))
//                .andExpect(view().name(ADMIN));
//    }

}
