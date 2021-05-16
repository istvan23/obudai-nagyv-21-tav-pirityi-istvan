package cookbook.security;

import cookbook.persistence.entity.User;
import cookbook.persistence.repository.CookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CookbookUserDetailsService implements UserDetailsService {

    @Autowired
    private CookRepository cookRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) cookRepository.findByUsername(username).get();
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new CookbookUserPrincipal(user);
    }
}
