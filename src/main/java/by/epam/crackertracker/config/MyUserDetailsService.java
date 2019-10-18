package by.epam.crackertracker.config;

import by.epam.crackertracker.dao.UserDaoJdbcImpl;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDaoJdbcImpl dao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = dao.findUser(s);
        if(user == null){
            throw new UsernameNotFoundException("User 404");
        }

        return new UserPrincipal(user);
    }
}
