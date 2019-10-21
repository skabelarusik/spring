/**
 * it's a class for helping to change uri to all path page
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.util;

import by.epam.crackertracker.entity.Role;

public class PageSelector {

    private PageSelector(){}

    public static String selectHomePage(String role){
        String page;
        switch (role.toUpperCase()){
            case "ADMIN": page = PageConstant.PATH_PAGE_MAIN_ADMIN;
                break;
            case "USER": page = PageConstant.PATH_PAGE_MAIN_USER;
                break;
            case "CURATOR": page=PageConstant.PATH_PAGE_MAIN_CURATOR;
                break;
            case "SUPERUSER": page = PageConstant.PATH_PAGE_MAIN_SUPERUSER;
                break;
            default: page = PageConstant.PATH_PAGE_MAIN_INDEX;
        }
        return page;
    }

    }
