package edu.hitwh.oremanagementserver.dto;

import edu.hitwh.oremanagementserver.domain.User;
import lombok.Data;

import java.util.List;
@Data
public class UserList {
    private List<User> users;
}
