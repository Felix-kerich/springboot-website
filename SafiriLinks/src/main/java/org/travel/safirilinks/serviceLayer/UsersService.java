package org.travel.safirilinks.serviceLayer;

import org.springframework.stereotype.Service;
import org.travel.safirilinks.Models.UserLogin;
import org.travel.safirilinks.Repository.UserRepository;
@Service
public class UsersService {
    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   public UserLogin saveUser(UserLogin userLogin){
        return userRepository.save(userLogin);
   }

}
