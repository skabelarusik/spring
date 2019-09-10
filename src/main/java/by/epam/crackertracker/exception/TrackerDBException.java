/**
 * dao exception
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.exception;

public class TrackerDBException extends Exception {

    public TrackerDBException(){
        super();
    }

    public TrackerDBException(String message){
        super(message);
    }

    public TrackerDBException(String message, Throwable throwable){
        super(message, throwable);
    }
}
