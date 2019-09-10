/**
 * it's for cleaning attribute session after redirect
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AttributeSessionCleaner {
    private AttributeSessionCleaner attributeCleaner;

    public AttributeSessionCleaner getAttributeSessionCleaner() {
        if(attributeCleaner == null){
            attributeCleaner = new AttributeSessionCleaner();
        }
        return attributeCleaner;
    }

    private AttributeSessionCleaner(){}

    public static void clean(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE);
        session.removeAttribute(ParameterConstant.ATTRIBUTE_RES_ADD);
        session.removeAttribute(ParameterConstant.MESSAGE_ERROR_DOWNLOAD);
        session.removeAttribute(ParameterConstant.WRONG_DATA);
        session.removeAttribute(ParameterConstant.WRONG_DELETE_USER);
        session.removeAttribute(ParameterConstant.MESSAGE_UPDATE_PRODUCT);
        session.removeAttribute(ParameterConstant.MESSAGE_DELETE_PRODUCT);
        session.removeAttribute(ParameterConstant.MESSAGE_INSERT_PRODUCT);
        session.removeAttribute(ParameterConstant.MESSAGE_DELETE_REVIEW);
        session.removeAttribute(ParameterConstant.MESSAGE_UPDATE_ROLE);
        session.removeAttribute(ParameterConstant.MESSAGE_DEPOSIT);
        session.removeAttribute(ParameterConstant.MESSAGE_SEND_REVIEW);
        session.removeAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS);
        session.removeAttribute(ParameterConstant.MESSAGE_DEPOSIT);
        session.removeAttribute(ParameterConstant.MESSAGE_PROGRAM_NAME);
        session.removeAttribute(ParameterConstant.ATTRIBUTE_PROGRAM_COMPONENT);
        session.removeAttribute(ParameterConstant.PARAM_NAME_PROGR);
        session.removeAttribute(ParameterConstant.MESSAGE_PRODUCT);
        session.removeAttribute(ParameterConstant.WRONG_DATA);
        session.removeAttribute(ParameterConstant.ATTRIBUTE_ADD_DELETE_MESS);
    }
}
