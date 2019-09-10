/**
 * it's a class for helping to change uri to all path page
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.util;

public class PageSelector {

    private PageSelector(){}

    public static String selectPage(String uri){
        String page;
        switch (uri) {
            case PageConstant.URI_LOGIN:
                page = PageConstant.PATH_PAGE_LOGIN;
                break;
            case PageConstant.URI_REGISTER:
                page = PageConstant.PATH_PAGE_REGISTER;
                break;
            case PageConstant.URI_RESULT:
                page = PageConstant.PATH_PAGE_RESULT;
                break;
            case PageConstant.URI_LANG_ADMIN:
                page = PageConstant.PATH_PAGE_MAIN_ADMIN;
                break;
            case PageConstant.URI_EDIT:
                page = PageConstant.PATH_PAGE_EDITING;
                break;
            case PageConstant.URI_SERVICE:
                page = PageConstant.PATH_PAGE_SERVICE;
                break;
            case PageConstant.URI_EDIT_PROG :
                page = PageConstant.PATH_PAGE_UPDATE_PROG;
                break;
            case PageConstant.URI_LANG_USER :
                page = PageConstant.PATH_PAGE_MAIN_USER;
                break;
            case PageConstant.URI_LANG_SUPERUSER:
                page = PageConstant.PATH_PAGE_MAIN_SUPERUSER;
                break;
            case PageConstant.URI_LANG_CURATOR:
                page = PageConstant.PATH_PAGE_MAIN_CURATOR;
                break;
            case PageConstant.URI_DEPOSIT:
                page = PageConstant.PATH_DEPOSIT;
                break;
             default:
                page = PageConstant.PATH_PAGE_ERROR;
            }
            return page;
        }

    }
