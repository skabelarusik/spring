package by.epam.crackertracker;

import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        try {
            AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
            UserService service = configApplicationContext.getBean(UserService.class);
            User user = service.checkUser("clockworktimes", "Ferdinand2");
            System.out.println(user.getName());
        } catch (TrackerServiceException e) {
            System.out.println("WRONG!!!");
        }
    }
}
