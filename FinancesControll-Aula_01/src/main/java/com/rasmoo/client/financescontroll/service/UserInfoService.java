package com.rasmoo.client.financescontroll.service;

import com.rasmoo.client.financescontroll.core.impl.ResourceOwner;
import com.rasmoo.client.financescontroll.entity.User;
import com.rasmoo.client.financescontroll.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> usuario = this.userRepository.findByEmail(email);

        if (usuario.isPresent()) {
            UserDetails userDetails = new ResourceOwner(usuario.get());
            return userDetails;
        } else {
            throw new UsernameNotFoundException("Usuário nao encontrado.");
        }

    }
}
