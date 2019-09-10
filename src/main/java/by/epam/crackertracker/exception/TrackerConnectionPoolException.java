/**
 * connection pool exception
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.exception;

public class TrackerConnectionPoolException extends Exception {

    public TrackerConnectionPoolException(){
        super();
    }

    public TrackerConnectionPoolException(String message){
        super(message);
    }

    public TrackerConnectionPoolException(String message, Throwable throwable){
        super(message, throwable);
    }
}
