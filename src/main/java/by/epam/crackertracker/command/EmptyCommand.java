package by.epam.crackertracker.command;

import by.epam.crackertracker.util.PageConstant;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return PageConstant.PATH_PAGE_INDEX;
    }
}
