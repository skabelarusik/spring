/**
 * command to delete subscribe
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.service.SubscriptionService;
import javax.servlet.http.HttpServletRequest;

public class DeleteSubscribeCommand implements Command {
    SubscriptionService service;

    public DeleteSubscribeCommand(SubscriptionService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request)  {
        return null;
    }
}
