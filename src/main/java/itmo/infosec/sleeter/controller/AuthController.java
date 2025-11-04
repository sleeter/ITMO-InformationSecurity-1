package itmo.infosec.sleeter.controller;

import itmo.infosec.sleeter.dto.JwtResponse;
import itmo.infosec.sleeter.dto.LoginRequest;
import itmo.infosec.sleeter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest req) {
        String jwt = userService.login(req);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
