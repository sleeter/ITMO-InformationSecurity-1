package itmo.infosec.sleeter.service;

import itmo.infosec.sleeter.configuration.JwtUtil;
import itmo.infosec.sleeter.dto.LoginRequest;
import itmo.infosec.sleeter.model.User;
import itmo.infosec.sleeter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwt;

    public String login(LoginRequest req) {
        String token;
        var exists = userRepository.findByName(req.name());
        if (exists.isPresent()) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.name(), req.password()));
            token = jwt.generateToken(exists.get());
        } else {
            User user = new User();
            user.setName(req.name());
            user.setPassword(passwordEncoder.encode(req.password()));
            userRepository.save(user);
            token = jwt.generateToken(user);
        }
        return token;
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name).orElseThrow();
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByName(username);
    }
}
