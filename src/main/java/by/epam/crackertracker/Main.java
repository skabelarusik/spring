package by.epam.crackertracker;

import by.epam.crackertracker.command.LoginCommand;
import by.epam.crackertracker.dao.AdviceDaoJdbcImpl;
import by.epam.crackertracker.dao.UserDaoJdbcImpl;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.service.ProgramService;
import by.epam.crackertracker.service.SubscriptionService;
import by.epam.crackertracker.service.UserService;

import java.net.http.HttpRequest;
import java.util.List;

public class Main {
    public static void main(String[] args) throws TrackerDBException {
  //      UserService userService, AdviceService adviceService, SubscriptionService subscriptionService,
   //             ProgramService programService
        AdviceDaoJdbcImpl daoJdbc = new AdviceDaoJdbcImpl();
       String str = daoJdbc.selectRandomAdvice();
        System.out.println(str);
    }
}
