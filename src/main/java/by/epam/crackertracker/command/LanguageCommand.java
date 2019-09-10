/**
 * command to change language
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.util.PageSelector;
import by.epam.crackertracker.util.ParameterConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(ParameterConstant.SUBMIT_LANGUAGE);
        String page = PageSelector.selectPage(request.getRequestURI());
        HttpSession session = request.getSession(true);
        String lang = selectLang(language);
        session.setAttribute(ParameterConstant.ATTRIBUTE_LANGUAGE, lang);
        return page;
    }

    private String selectLang(String language){
        String lang;
        if(language.equals(ParameterConstant.SUBMIT_RU)){
            lang = ParameterConstant.RU;
        } else if(language.equals(ParameterConstant.SUBMIT_EN)){
            lang = ParameterConstant.EN;
        } else {
            lang = ParameterConstant.BY;
        }
        return lang;
    }
}
