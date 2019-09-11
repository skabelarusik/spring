/**
 * interface for different activities
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;


import by.epam.crackertracker.exception.TrackerServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request) throws TrackerServiceException;
}
