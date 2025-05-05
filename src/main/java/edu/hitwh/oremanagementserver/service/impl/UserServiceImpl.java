package edu.hitwh.oremanagementserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hitwh.oremanagementserver.controller.Code;
import edu.hitwh.oremanagementserver.controller.Result;
import edu.hitwh.oremanagementserver.dao.UserMapper;
import edu.hitwh.oremanagementserver.domain.User;
import edu.hitwh.oremanagementserver.dto.UserList;
import edu.hitwh.oremanagementserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword());
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Result add(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        if (userMapper.selectOne(queryWrapper) != null)  return new Result(Code.FAIL,"用户名已存在");
        userMapper.insert(user);
        return new Result(Code.SUCCESS,"添加成功");
    }

    @Override
    public Result delete(UserList users) {
        List<Long> list = users.getUsers().stream().map(User::getId).toList();
        userMapper.deleteBatchIds(list);
        return new Result(Code.SUCCESS,"删除成功");
    }

    @Override
    public Result getAll() {
        return new Result(Code.SUCCESS,userMapper.selectList(null),"获取成功");
    }
}
