package cui.shibing.freeread.security;

import cui.shibing.freeread.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(!StringUtils.isEmpty(s)){
            return new UserDetailsImpl(userDao.selectByUserName(s));
        }
        return null;
    }
}