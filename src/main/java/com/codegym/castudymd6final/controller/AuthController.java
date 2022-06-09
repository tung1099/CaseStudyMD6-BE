package com.codegym.castudymd6final.controller;
import com.codegym.castudymd6final.model.dto.JwtResponse;
import com.codegym.castudymd6final.model.dto.SignUpForm;
import com.codegym.castudymd6final.model.entity.User;
import com.codegym.castudymd6final.service.JwtService;
import com.codegym.castudymd6final.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        //Kiểm tra username và pass có đúng hay không
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        //Lưu user đang đăng nhập vào trong context của security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUserName(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody SignUpForm user) {
        if (!user.getPasswordForm().getPassword().equals(user.getPasswordForm().getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user1 = new User(user.getUsername(), user.getPasswordForm().getPassword());
        return new ResponseEntity<>(userService.save(user1), HttpStatus.CREATED);
    }

    @PostMapping("/password/{userId}")
    public ResponseEntity<User> editpass(@RequestBody SignUpForm user, @PathVariable Long userId) {
        User u = userService.findById(userId).get();
        if (!user.getPasswordForm().getPassword().equals(user.getPasswordForm().getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user1 = new User(userId, u.getUsername(), user.getPasswordForm().getPassword());
        return new ResponseEntity<>(userService.save(user1), HttpStatus.CREATED);    }
}
