/**
 * command to show table all program(by curator) (multitabling, 10 programs in page)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.ProgramsNameService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static by.epam.crackertracker.command.SelectAllProgramsNameCommand.MAX_TABLE_PROGRAMS_NAME;

public class SelectCuratorProgramCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private ProgramsNameService service;

    public SelectCuratorProgramCommand(ProgramsNameService service){
        this.service = service;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String currentTablePage = request.getParameter(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        String type = request.getParameter(ParameterConstant.PARAM_TYPE);
        int intPage;
        if(currentTablePage == null){
            intPage = 1;
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
        } else {
            intPage = Integer.parseInt(currentTablePage);
            if(intPage > 1){
                request.setAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
            }
        }
        String page;
        int typeAction;
        try{
            if(type.equals(ParameterConstant.TYPE_1)){
                typeAction = Integer.parseInt(type);
                request.setAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
                request.setAttribute(ParameterConstant.PARAM_TYPE, ParameterConstant.TYPE_1);
            } else if(type.equals(ParameterConstant.TYPE_0)) {
                typeAction = Integer.parseInt(type);
                request.setAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE);
                request.setAttribute(ParameterConstant.PARAM_TYPE, ParameterConstant.TYPE_0);
            } else {
                throw new TrackerServiceException("Wrong service type select all curators programs name");
            }
            List<ProgramsName> list = service.selectCuratorProgramsName(login, intPage, typeAction);
            List<ProgramsName> newList = new ArrayList<>();
            if(list.size() == MAX_TABLE_PROGRAMS_NAME){
                newList = new ArrayList<>(list.size() - 1);
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
                for(int i = 0; i < list.size() - 1; i++){
                    newList.add(list.get(i));
                }
            } else if(!list.isEmpty()){
                newList = new ArrayList<>(list.size());
                newList.addAll(list);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_PROGRAMS_CURATOR, newList);
            page = PageConstant.PATH_PAGE_RESULT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't select curators : " + login + " programs", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
