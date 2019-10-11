package by.epam.crackertracker.util;

import org.springframework.stereotype.Component;

@Component
public class LanguageSelector {

    public String select(String lang){
        switch (lang){
            case ParameterConstant.SUBMIT_BY:
                return ParameterConstant.BY;
            case ParameterConstant.SUBMIT_EN:
                return ParameterConstant.EN;
            default:
                return ParameterConstant.RU;
        }
    }
}
