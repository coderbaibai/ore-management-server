package edu.hitwh.oremanagementserver.service;

import edu.hitwh.oremanagementserver.controller.Result;
import edu.hitwh.oremanagementserver.domain.User;
import edu.hitwh.oremanagementserver.dto.UserList;


public interface UserService {
    User login(User user);

    Result add(User user);

    Result delete(UserList user);

    Result getAll();
}
