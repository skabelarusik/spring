package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.*;
import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.service.ProductService;
import by.epam.crackertracker.service.ProgramsNameService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import static by.epam.crackertracker.utils.TestParametres.*;

import org.junit.Assert;
import org.junit.Before;
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
public class ProgramNameControllerTest {

    @Mock
    private ProgramsNameService service;

    @InjectMocks
    private ProgramNameController controller;

    private List<ProgramsName> programsNameList;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    {
        programsNameList = new ArrayList<>();
        programsNameList.add(new ProgramsName("Program1", "Curator1", BigDecimal.valueOf(2.),3));
        programsNameList.add(new ProgramsName("Program2", "Curator2", BigDecimal.valueOf(2.),3));
    }

    @Test
    public void testSelectAll() throws Exception {
        when(service.selectAllProgramsName(START_PAGE, START_PAGE)).thenReturn(programsNameList);
        mockMvc.perform(get(PageConstant.PATH_PROGRAM_NAME_SELECT))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, programsNameList))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_PROGRAM_NAME));
    }


    @Test
    public void testSelectAllDelete() throws Exception {
        when(service.selectAllProgramsName(START_PAGE, 0)).thenReturn(programsNameList);
        mockMvc.perform(get(PageConstant.PATH_PROGRAM_NAME_SELECT_DEL))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, programsNameList))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_PROGRAM_NAME));
    }

    @Test
    public void testSelectAllCurator() throws Exception {
        when(service.selectCuratorProgramsName(LOGIN_FIRST, START_PAGE, START_PAGE)).thenReturn(programsNameList);
        mockMvc.perform(get(PageConstant.PATH_PROGRAM_NAME_SELECT_CURATOR)
                .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, programsNameList))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_PROGRAM_NAME));
    }

    @Test
    public void testSelectAllCuratorDelete() throws Exception {
        when(service.selectCuratorProgramsName(LOGIN_FIRST, START_PAGE, 0)).thenReturn(programsNameList);
        mockMvc.perform(get(PageConstant.PATH_PROGRAM_NAME_SELECT_CURATOR_DEL)
                .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE))
                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, programsNameList))
                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_PROGRAM_NAME));
    }

    @Test
    public void testAddProgramName() throws Exception {
        doNothing().when(service).insert(LOGIN_FIRST, PROGRAM_NAME, COST, DURATION);
        mockMvc.perform(post(PageConstant.PATH_PROGRAM_NAME_ADD).sessionAttr(ParameterConstant.START_PAGE, ADMIN)
                .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST)
                .param(ParameterConstant.ATTRIBUTE_NAME_PROGRAM, PROGRAM_NAME)
                .param(ParameterConstant.ATTRIBUTE_COST_PROGRAM, COST)
                .param(ParameterConstant.ATTRIBUTE_DUR_PROGRAM, DURATION))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_PROGRAM_NAME, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(view().name(REDIRECT_ADMIN));
    }

    @Test
    public void testAddProgramNameWrong() throws Exception {
        doThrow(TrackerServiceException.class).when(service).insert(LOGIN_FIRST, PROGRAM_NAME, COST, DURATION);
        mockMvc.perform(post(PageConstant.PATH_PROGRAM_NAME_ADD).sessionAttr(ParameterConstant.START_PAGE, ADMIN)
                .sessionAttr(ParameterConstant.LOGIN, LOGIN_FIRST)
                .param(ParameterConstant.ATTRIBUTE_NAME_PROGRAM, PROGRAM_NAME)
                .param(ParameterConstant.ATTRIBUTE_COST_PROGRAM, COST)
                .param(ParameterConstant.ATTRIBUTE_DUR_PROGRAM, DURATION))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_PROGRAM_NAME, ParameterConstant.WRONG_DATA))
                .andExpect(forwardedUrl(ADMIN));
    }

    @Test
    public void testDeleteProgramName() throws Exception {
        doNothing().when(service).deleteById(GOOD_ID, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        when(service.selectAllProgramsName(START_PAGE, START_PAGE)).thenReturn(programsNameList);
        mockMvc.perform(post(PageConstant.PATH_PROGRAM_NAME_DELETE).param(ParameterConstant.PARAM_ID, GOOD_ID)
            .param(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE))
            .andDo(print())
            .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, programsNameList))
            .andExpect(forwardedUrl(PageConstant.PATH_RESULT_PROGRAM_NAME));
    }

}
