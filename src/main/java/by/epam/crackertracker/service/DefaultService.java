/**
 * default service
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.util.PageConstant;

public class DefaultService {
    private String path = PageConstant.PATH_PAGE_LOGIN;

    public void setPath(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
