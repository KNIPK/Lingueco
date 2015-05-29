package com.lingueco.app;

import com.lingueco.user.entity.Role;
import com.lingueco.user.entity.User;
import com.lingueco.user.service.User_Role_service;
import com.lingueco.user.service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private String viewPath = "controller/user/";

    @Autowired
    private User_service userService;

    @Autowired
    private User_Role_service user_role_service;


    @RequestMapping("/welcome")
    public ModelAndView executeSecurity(ModelMap model, Principal principal) {

        String name = principal.getName();
        model.addAttribute("author", name);
        model.addAttribute("message", "Welcome To Lingueco user page");
        return new ModelAndView(viewPath + "welcome");

    }

    @RequestMapping(" ")
    public ModelAndView login() {
        return new ModelAndView(viewPath + "login");
    }

    @RequestMapping("admin")
    public ModelAndView admin() {

        return new ModelAndView(viewPath + "admin");
    }

    @RequestMapping("index")
    public ModelAndView user() {
        return new ModelAndView(viewPath + "index");
    }


    @RequestMapping("fail2login")
    public ModelAndView loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return new ModelAndView(viewPath + "login");

    }

    @RequestMapping("logout")
    public ModelAndView logout(ModelMap model) {

        return new ModelAndView(viewPath + "login");

    }

    @RequestMapping("register")
    public ModelAndView createUser(@ModelAttribute User user) {
        return new ModelAndView(viewPath + "form");
    }

    @RequestMapping("saveUser")
    public ModelAndView saveUser(@ModelAttribute User user, Role role) {

        user.setEnabled(1);
        userService.createUser(user);

        role.setUser_id(user.getUser_id());
        role.setRole("ROLE_ADMIN");
        user_role_service.createUser_role(role);

        return new ModelAndView(viewPath + "login");
    }


    @RequestMapping(value = {"getAllUsers"})
    public ModelAndView getAllUsers() {
        List userList = userService.getAllUsers();
        return new ModelAndView(viewPath + "userList", "userList", userList);
    }


    @RequestMapping(value = {"getAllUsers_Roles"})
    public ModelAndView getAllUsers_roles() {
        List user_roles_List = user_role_service.getAllUsers_roles();
        return new ModelAndView(viewPath + "user_roles_List", "user_roles_List", user_roles_List);
    }


    @RequestMapping("deleteUser")
    public ModelAndView deleteUser(@RequestParam long id) {

        deleteUser_role(id);
        userService.deleteUser(id);
        return new ModelAndView("redirect:getAllUsers");

    }

    @RequestMapping("deleteUser_Role")
    public void deleteUser_role(@RequestParam long id) {

        for (Role role : user_role_service.getAllUsers_roles()) {

            if (role.getUser_id() == id) {
                id = role.getUser_role_id();
            }
        }
        user_role_service.deleteUser_role(id);
    }

}


//CREATE TABLE user_roles (
//        user_role_id INT(11) NOT NULL AUTO_INCREMENT,
//    user_id INT(20) NOT NULL,
//    ROLE VARCHAR(45) NOT NULL,
//    PRIMARY KEY (user_role_id),
//UNIQUE KEY uni_user_id_role (ROLE,user_id),
//    KEY fk_user_id_idx (user_id);


