package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.*;
import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.service.ProductService;
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

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    private MockMvc mockMvc;

    private List<Product> productList;

    {
        productList = new ArrayList<>();
        productList.add(new Product("Product1", 444));
        productList.add(new Product("Product2", 333));
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }


//    @Test
//    public void testSelectAllProducts() throws Exception {
//        when(service.selectAllProduct(null, START_PAGE)).thenReturn(productList);
//        mockMvc.perform(get(PageConstant.SECUR_PATH_PRODUCT_SELECT))
//                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, productList))
//                .andExpect(forwardedUrl(PageConstant.PATH_RESULT_PRODUCT));
//    }

    //TODO check it
//    @Test
//    public void testSearchProductWrongData() throws Exception{
//        when(service.searchProducts("search")).thenReturn(productList);
//        doThrow(TrackerServiceException.class).when(service).searchProducts("search");
//        mockMvc.perform(get(PageConstant.SECUR_PATH_PRODUCT_SELECT)
//                .param("startPage", "admin")
//                .param("text","search"))
//                .andExpect(model().attribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, productList))
//                .andExpect(forwardedUrl("resultProduct"));
//    }

    @Test
    public void testUpdateProduct() throws Exception {
        doNothing().when(service).updateProduct( GOOD_ID, ParameterConstant.NAME, ParameterConstant.PRODUCT_CALORIES
            ,  ParameterConstant.PRODUCT_FATS, ParameterConstant.PRODUCT_CARBS,  ParameterConstant.PRODUCT_PROTEINS );
        mockMvc.perform(post(PageConstant.SECUR_PATH_PRODUCT_UPDATE).param(ParameterConstant.PARAM_ID_PRODUCT, GOOD_ID)
            .param(ParameterConstant.PARAM_NAME_PRODUCT,ParameterConstant.NAME).param(ParameterConstant.PARAM_CALORIES_PRODUCT,
                        ParameterConstant.PRODUCT_CALORIES)
                .param(ParameterConstant.PARAM_PROTEINS_PRODUCT, ParameterConstant.PRODUCT_PROTEINS)
                .param(ParameterConstant.PARAM_CARBS_PRODUCT, ParameterConstant.PRODUCT_CARBS)
                .param(ParameterConstant.PARAM_FATS_PRODUCT, ParameterConstant.PRODUCT_FATS)
                .param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_UPDATE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(forwardedUrl(ADMIN));
    }

    @Test
    public void testUpdateProductWrongData() throws Exception {
        doThrow(TrackerServiceException.class).when(service).updateProduct( GOOD_ID, ParameterConstant.NAME, ParameterConstant.PRODUCT_CALORIES
                ,  ParameterConstant.PRODUCT_FATS, ParameterConstant.PRODUCT_CARBS,  ParameterConstant.PRODUCT_PROTEINS );
        mockMvc.perform(post(PageConstant.SECUR_PATH_PRODUCT_UPDATE).param(ParameterConstant.PARAM_ID_PRODUCT, GOOD_ID)
                .param(ParameterConstant.PARAM_NAME_PRODUCT,ParameterConstant.NAME).param(ParameterConstant.PARAM_CALORIES_PRODUCT,
                        ParameterConstant.PRODUCT_CALORIES)
                .param(ParameterConstant.PARAM_PROTEINS_PRODUCT, ParameterConstant.PRODUCT_PROTEINS)
                .param(ParameterConstant.PARAM_CARBS_PRODUCT, ParameterConstant.PRODUCT_CARBS)
                .param(ParameterConstant.PARAM_FATS_PRODUCT, ParameterConstant.PRODUCT_FATS)
                .param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_UPDATE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(forwardedUrl(ADMIN));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(service).deleteProduct(GOOD_ID);
        mockMvc.perform(post(PageConstant.SECUR_PATH_PRODUCT_DELETE).param(ParameterConstant.PARAM_ID, GOOD_ID)
                .param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_DELETE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(view().name(REDIRECT_ADMIN));
    }

    @Test
    public void testDeleteProductWrongId() throws Exception {
        doThrow(TrackerServiceException.class).when(service).deleteProduct(WRONG_ID);
        mockMvc.perform(post(PageConstant.SECUR_PATH_PRODUCT_DELETE).param(ParameterConstant.PARAM_ID, WRONG_ID)
                .param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_DELETE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(forwardedUrl(ADMIN));
    }

    @Test
    public void testAddProduct() throws Exception {
        doNothing().when(service).addProduct(ParameterConstant.NAME, ParameterConstant.PRODUCT_CALORIES,
                ParameterConstant.PRODUCT_FATS, ParameterConstant.PRODUCT_CARBS, ParameterConstant.PRODUCT_PROTEINS);
        mockMvc.perform(post(PageConstant.SECUR_PATH_PRODUCT_ADD).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN)
            .param(ParameterConstant.PARAM_NAME_PRODUCT, ParameterConstant.NAME)
            .param(ParameterConstant.PARAM_CALORIES_PRODUCT, ParameterConstant.PRODUCT_CALORIES)
            .param(ParameterConstant.PARAM_FATS_PRODUCT, ParameterConstant.PRODUCT_FATS)
            .param(ParameterConstant.PARAM_CARBS_PRODUCT, ParameterConstant.PRODUCT_CARBS)
            .param(ParameterConstant.PARAM_PROTEINS_PRODUCT, ParameterConstant.PRODUCT_PROTEINS))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_INSERT_PRODUCT, ParameterConstant.MESSAGE_CONGRAT))
                .andExpect(forwardedUrl(ADMIN));
    }

    @Test
    public void testAddProductWrongData() throws Exception {
        doThrow(TrackerServiceException.class).when(service).addProduct(ParameterConstant.NAME, ParameterConstant.PRODUCT_CALORIES,
                ParameterConstant.PRODUCT_FATS, ParameterConstant.PRODUCT_CARBS, ParameterConstant.PRODUCT_PROTEINS);
        mockMvc.perform(post(PageConstant.SECUR_PATH_PRODUCT_ADD).param(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, ADMIN)
                .param(ParameterConstant.PARAM_NAME_PRODUCT, ParameterConstant.NAME)
                .param(ParameterConstant.PARAM_CALORIES_PRODUCT, ParameterConstant.PRODUCT_CALORIES)
                .param(ParameterConstant.PARAM_FATS_PRODUCT, ParameterConstant.PRODUCT_FATS)
                .param(ParameterConstant.PARAM_CARBS_PRODUCT, ParameterConstant.PRODUCT_CARBS)
                .param(ParameterConstant.PARAM_PROTEINS_PRODUCT, ParameterConstant.PRODUCT_PROTEINS))
                .andDo(print())
                .andExpect(model().attribute(ParameterConstant.MESSAGE_INSERT_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST))
                .andExpect(forwardedUrl(ADMIN));
    }


}
