package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.*;
import by.epam.crackertracker.entity.MealDay;
import by.epam.crackertracker.entity.MealTime;
import by.epam.crackertracker.entity.Program;
import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.service.ProductService;
import by.epam.crackertracker.service.ProgramService;
import by.epam.crackertracker.service.ProgramsNameService;
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
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class ProgramTest {

    @Mock
    private ProgramService service;

    @InjectMocks
    private ProgramController controller;

    private List<Program> programList;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    {
        programList = new ArrayList<>();
        programList.add(new Program("Program1", "Product1", 1.5, MealDay.FRIDAY, MealTime.BREAKFAST));
        programList.add(new Program("Program2", "Product2", 2.2, MealDay.MONDAY,MealTime.SECOND_BREAKFAST));
    }

    @Test
    public void testSelectAll() throws Exception {
        when(service.selectAll(LOGIN_FIRST)).thenReturn(programList);
        mockMvc.perform(get(PageConstant.PATH_PROGRAM_SELECT)
                .param(ParameterConstant.NAME, LOGIN_FIRST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_PROGRAMS, programList))
                .andExpect(model().attribute(ParameterConstant.NAME, LOGIN_FIRST))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_PROGRAM));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(service).deleteById(GOOD_ID);
        when(service.selectAll(LOGIN_FIRST)).thenReturn(programList);
        mockMvc.perform(get(PageConstant.PATH_PROGRAM_DELETE)
                .param(ParameterConstant.PARAM_ID, GOOD_ID)
                .param(ParameterConstant.NAME, LOGIN_FIRST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_PROGRAMS, programList))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_PROGRAM));
    }

}
