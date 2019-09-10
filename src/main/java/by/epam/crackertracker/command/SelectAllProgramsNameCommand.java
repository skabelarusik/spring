/**
 * command to show table all products name (multitabling, 10 products name in page)
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

public class SelectAllProgramsNameCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    ProgramsNameService servise;

    public SelectAllProgramsNameCommand(ProgramsNameService servise){
        this.servise = servise;
    }
    static final int MAX_TABLE_PROGRAMS_NAME = 11;

    @Override
    public String execute(HttpServletRequest request) {
        String currentTablePage = request.getParameter(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = request.getParameter(ParameterConstant.PARAM_TYPE);
        int intPage;

        if(currentTablePage == null) {
            intPage = 1;
        } else {
            intPage = Integer.parseInt(currentTablePage);
            if(intPage > 1){
                request.setAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
            }
        }
        String page;
        try{
            int typeAction;
            if(type.equals(ParameterConstant.TYPE_1)){
                typeAction = Integer.parseInt(type);
                request.setAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
                request.setAttribute(ParameterConstant.PARAM_TYPE, ParameterConstant.TYPE_1);
            } else if(type.equals(ParameterConstant.TYPE_0)) {
                typeAction = Integer.parseInt(type);
                request.setAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE);
                request.setAttribute(ParameterConstant.PARAM_TYPE, ParameterConstant.TYPE_0);
            } else {
                throw new TrackerServiceException("Wrong service type select all programs name");
            }

            List<ProgramsName> listPrograms = servise.selectAllProgramsName(intPage, typeAction);
            List<ProgramsName> newList = new ArrayList<>();
            if(listPrograms.size() == MAX_TABLE_PROGRAMS_NAME){
                newList = new ArrayList<>(listPrograms.size() - 1);
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
                for(int i = 0; i < listPrograms.size() - 1; i++){
                    newList.add(listPrograms.get(i));
                }
            } else if(listPrograms.size() != 0){
                newList = new ArrayList<>(listPrograms.size());
                newList.addAll(listPrograms);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_PROGRAMS, newList);
            page = PageConstant.PATH_PAGE_RESULT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't select all programs name", e);
            page = PageConstant.PATH_PAGE_ERROR;
    }
        return page;
    }
}
