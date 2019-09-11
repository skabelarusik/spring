/**
 * command to show table all advices (multitabling, 10 advices in page)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.AdviceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SelectAllAdviceCommand implements Command {
    private AdviceService service;

    public SelectAllAdviceCommand(AdviceService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws TrackerServiceException {
        List<Advice> list = service.selectAllAdvices();
        request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_ADVICES, list);
        return PageConstant.PATH_PAGE_RESULT;
    }
}
