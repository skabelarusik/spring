/**
 * main controller
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.controller;

import by.epam.crackertracker.command.ActionFactory;
import by.epam.crackertracker.command.EmptyCommand;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.pool.ConnectionPool;
import by.epam.crackertracker.util.AttributeSessionCleaner;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import by.epam.crackertracker.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/")
public class TrackerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TrackerServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (TrackerServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy(){
        try {
            ConnectionPool.getInstance().closePool();
        } catch (TrackerConnectionPoolException e) {
            throw new RuntimeException("Cant destroy pool connection");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TrackerServiceException {
        Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter(ParameterConstant.PARAM_COMMAND));
        Command command = commandOptional.orElse(new EmptyCommand());
        AttributeSessionCleaner.clean(request);
        String page = command.execute(request);

        if(request.getAttribute(ParameterConstant.ATTRIBUTE_ROUTE) != RouteType.REDIRECT){
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
             response.sendRedirect(page);
        }
    }
}
