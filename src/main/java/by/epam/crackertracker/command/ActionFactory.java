/**
 * factory to define command
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import org.apache.log4j.Logger;

import java.util.Optional;

public class ActionFactory {
    private static final Logger LOGGER = Logger.getRootLogger();
    public static Optional<Command> defineCommand(String commandName){
        Optional<Command> current = Optional.empty();
        if(commandName == null){
            return current;
        }
        try{
            TypeCommand typeCommand = TypeCommand.valueOf(commandName.toUpperCase().trim());
            current = Optional.of(typeCommand.getCommand());
        } catch (IllegalArgumentException e){
            LOGGER.warn("Wrong in define command", e);
        }
        return current;
    }


}
