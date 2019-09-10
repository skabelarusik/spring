/**
 * command to log out from account
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.service.DefaultService;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private DefaultService receiver = new DefaultService();

    @Override
    public String execute (HttpServletRequest request) {
        request.getSession().invalidate();
        return receiver.getPath();
    }
}
