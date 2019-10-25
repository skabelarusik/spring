/**
 * service exception
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TrackerServiceException extends Exception  {
    public TrackerServiceException(){
        super();
    }

    public TrackerServiceException(String message){
        super(message);
    }

    public TrackerServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
