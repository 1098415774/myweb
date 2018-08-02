package top.zhkumanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.common.Utils.SecurityUtils;
import top.zhkumanage.equip.entity.EquipMSG;
import top.zhkumanage.user.entity.User;
import top.zhkumanage.user.service.UserService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("Login")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping
    public String Login(){
        return "login";
    }

    @RequestMapping("Console")
    public String Console(){
        return "console";
    }

    @RequestMapping("Check")
    @ResponseBody
    public String Check(@RequestBody User user){
        if (null != userService.selectByUser(user)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping("UserMsg")
    @ResponseBody
    public User UserMsg(@RequestBody User user){
        User ruser = userService.selectByUser(user);
        return ruser;
    }

    @RequestMapping("Signup")
    @ResponseBody
    public String SignUp(@RequestBody User user){
        String username = user.getUsername();
        if (null == userService.selectByUserName(username)){
            return "User is existed";
        }
        User user1 = new User();
        String password = user.getUserpasswork();
        try {
            user1.setUsername(username);
            user1.setUserpasswork(SecurityUtils.encryptPassWord(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "fail";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "fail";
        }
        userService.insert(user);
        return "success";
    }

    @RequestMapping("Sign")
    public String SignUp(){
        return "signup";
    }
//    public List<EquipMSG> equipLi
}
