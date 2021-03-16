package ru.otus.spring.service;

import lombok.Data;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.UserDao;
import ru.otus.spring.domain.User;

@Service
@Data
public class UserService implements UserDetailsService {
    UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null");
        }
        return user;
    }
}
