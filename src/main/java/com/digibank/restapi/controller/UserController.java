//package com.digibank.restapi.controller;
//
//import com.digibank.restapi.dto.UsersDto;
//import com.digibank.restapi.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/users/otp-generate")
//    public ResponseEntity<String> register(@RequestBody UsersDto registerDto) {
//        return new ResponseEntity<>(userService.register(registerDto), HttpStatus.OK);
//    }
//
//    @PutMapping("/users/otp-verification")
//    public ResponseEntity<String> verifyAccount(@RequestParam String email,
//                                                @RequestParam String otp) {
//        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
//    }
//
//    @PutMapping("/users/otp-regenerate")
//    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
//        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
//    }
//
//    @PutMapping("/users/password")
//    public ResponseEntity<String> changePassword(@RequestParam Integer id_user, @RequestBody UsersDto userDto) {
//        return new ResponseEntity<>(userService.changePassword(id_user, userDto.getPassword()), HttpStatus.OK);
//    }
//
//    @PutMapping("/users/change-password")
//    public ResponseEntity<String> changePassword(
//            @RequestParam Integer id_user,
//            @RequestParam String oldPassword,
//            @RequestParam String newPassword
//    ) {
//        return new ResponseEntity<>(userService.changePasswordWithValidation(id_user, oldPassword, newPassword), HttpStatus.OK);
//    }
//}