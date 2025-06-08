package com.techleads.paging.app.controller;

import com.techleads.paging.app.dto.UserDto;
import com.techleads.paging.app.entity.User;
import com.techleads.paging.app.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/details")

    public ResponseEntity<List<UserDto>> findUserDetails(@RequestParam("userid")
                                                         String userId) {
        try {
            List<UserDto> userDetails = userService.findUserDetails(userId);
            System.out.println("User Details: " + userDetails);
            return new ResponseEntity<>(userDetails, HttpStatus.OK); // Return user details with 200 OK status
        } catch (Exception e) {
            System.err.println("Error fetching user details: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Handle generic errors
        }

    }


    @GetMapping("/user/details-page")
    public ResponseEntity<Page<UserDto>> findUserDetails(
            @RequestParam("userid") String userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<UserDto> userDetails = userService.findUserDetailsPage(userId, pageable);
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user/details-page-test")
    public ResponseEntity<List<UserDto>> getAllEmployees(
            @RequestParam(name = "userid") String userId,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy) {
        List<UserDto> userList = userService.findUserDetailsPageTest(userId, pageNo, pageSize, sortBy);

        return new ResponseEntity<>(userList, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("/user/update-name")
   public ResponseEntity<User> updateUserNameById(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "id") Integer id) {
        User user = userService.updateUserNameById(name, id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
