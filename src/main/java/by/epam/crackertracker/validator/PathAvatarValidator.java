package by.epam.crackertracker.validator;

import by.epam.crackertracker.util.PageConstant;

public class PathAvatarValidator {
    public static final String SPLIT_PARAM = "\\.";
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final String JPG = "jpg";
    public static final String JPEG = "jpeg";
    public static final String PNG = "png";
    public static final String GIF = "gif";


    public boolean isValide(String login, String path){
        boolean status = false;
        if(path != null  && !path.isEmpty() && path.contains(PageConstant.PATH_PICTURE)){
            String [] str = path.split(PageConstant.PATH_PICTURE);
            if(str.length == TWO){
                String [] param = str[ONE].split(SPLIT_PARAM);
                if(param.length == TWO){
                    if(param[ZERO].equals(login) && ( param[ONE].equals(JPG) ||
                            param[ONE].equals(PNG) && param[1].equals(GIF) || param[1].equals(JPEG))){
                        status = true;
                    }
                }
            }
        }
        return status;
    }
}
