package edu.hitwh.oremanagementserver.controller;


import edu.hitwh.oremanagementserver.domain.User;
import edu.hitwh.oremanagementserver.dto.UserList;
import edu.hitwh.oremanagementserver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        User login = userService.login(user);
        if(login!=null){
            //session
            request.getSession().setAttribute("user",login);
            //cookie
            return new Result(Code.SUCCESS,"登陆成功");
        }else{
            return new Result(Code.FAIL,"用户名或密码错误");
        }
    }
    @PostMapping("/add")
    public Result add(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        return userService.add(user);
    }
    @PostMapping("/delete")
    public Result delete(@RequestBody UserList users, HttpServletRequest request, HttpServletResponse response){
        return userService.delete(users);
    }
    @GetMapping("/all")
    public Result getAll(HttpServletRequest request, HttpServletResponse response){
        return userService.getAll();
    }
}
