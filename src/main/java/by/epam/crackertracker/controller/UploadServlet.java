/**
 * servlet to load user avatar
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.controller;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//@WebServlet(name = "FileLoadServlet", urlPatterns = "/UploadServlet", loadOnStartup = 1)
//@MultipartConfig(fileSizeThreshold = 1024 * 1024,
//        maxFileSize = 1024 * 1024 * 5,
//        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "C:\\Users\\User\\Desktop\\cracker\\src\\main\\webapp\\jsp\\picture\\";
    private static final String SHOW_DIR = "/jsp/picture/";

    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPath = (String) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_AVATAR);
        response.setContentType("text/html;charset=UTF-8");
        Part part = request.getPart(ParameterConstant.PARAM_FILE);
        if (part != null && part.getSize() > 0) {
            String fileNameOld = part.getSubmittedFileName();
            String [] param = fileNameOld.split("\\.");
            String fileName = request.getSession().getAttribute(ParameterConstant.PARAM_LOGIN) + "." + param[param.length - 1];
            String path = UPLOAD_DIR + fileName;
            String pathFile = SHOW_DIR + fileName;
            part.write(path);
            UserService service = new UserService();
            boolean status = false;
            try {
                status = service.sendAvatar((String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN), pathFile);
            } catch (TrackerServiceException e) {
                LOGGER.error("Wrong upload picture");
            }
            if(status) {
                if(!oldPath.equals(PageConstant.DEFAULT_AVATAR_PATH)){
                    Files.deleteIfExists(Paths.get(oldPath));
                }
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_AVATAR, pathFile);
            } else {
                request.setAttribute(ParameterConstant.MESSAGE_ERROR_DOWNLOAD, ParameterConstant.WRONG);
            }
            getServletContext().getRequestDispatcher((String) request.getSession().getAttribute
                    (ParameterConstant.ATTRIBUTE_CURRENT_PAGE)).forward(request, response);
        }
    }

}