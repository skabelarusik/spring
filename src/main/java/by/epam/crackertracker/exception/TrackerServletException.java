/**
 * servlet exception
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.exception;

public class TrackerServletException extends Exception{
    public TrackerServletException(){
        super();
    }

    public TrackerServletException(String message){
        super(message);
    }

    public TrackerServletException(String message, Throwable throwable){
        super(message, throwable);
    }
}
