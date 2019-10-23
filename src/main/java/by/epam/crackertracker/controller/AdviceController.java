package by.epam.crackertracker.controller;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(PageConstant.PATH_ADVICE)
public class AdviceController {

   @Autowired
   private AdviceService adviceService;

   @GetMapping(PageConstant.URI_SELECT)
   public String selectAdvice(Model model){
       model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, adviceService.selectAllAdvices());
       return PageConstant.PATH_RESULT_ADVICE;
    }

   @PostMapping(PageConstant.URI_ADD)
   public String addAdvice(@RequestParam String currentPage, @RequestParam String advice, Model model){
       try {
           adviceService.addAdvice(advice);
           model.addAttribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_CONGRAT);
       } catch (TrackerServiceException e) {
           model.addAttribute(ParameterConstant.ATTRIBUTE_RES_ADD, ParameterConstant.MESSAGE_ERROR_REGIST);
           return currentPage;
       }
       return "redirect:/" + currentPage;
   }

   @PostMapping(PageConstant.URI_DELETE)
   public String delete(@RequestParam String currentPage, @RequestParam String id, Model model){
       try {
           adviceService.deleteAdviceById(id);
           model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, adviceService.selectAllAdvices());
           model.addAttribute(ParameterConstant.ATTRIBUTE_ADD_DELETE_MESS, ParameterConstant.MESSAGE_CONGRAT);
       } catch (TrackerServiceException e) {
           model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, adviceService.selectAllAdvices());
           model.addAttribute(ParameterConstant.ATTRIBUTE_ADD_DELETE_MESS, ParameterConstant.MESSAGE_ERROR_REGIST);
       }
       return currentPage;
   }
}
