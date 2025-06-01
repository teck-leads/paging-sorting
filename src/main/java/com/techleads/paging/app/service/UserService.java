package com.techleads.paging.app.service;

import com.techleads.paging.app.dto.UserDto;
import com.techleads.paging.app.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findUserDetails(
            String userId){
        return userRepository.findUserDetails(userId);

    }

    public Page<UserDto> findUserDetailsPage(String userId, Pageable pageable) {
        return userRepository.findUserDetailsPage(userId, pageable);
    }



    public List<UserDto> findUserDetailsPageTest(String userId, Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<UserDto> pagedResult = userRepository.findUserDetailsPage(userId, paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<UserDto>();
        }
    }

//    public List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
//    {
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//
//        Page<EmployeeEntity> pagedResult = repository.findAll(paging);
//
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<EmployeeEntity>();
//        }
//    }
}
