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


    // METHODS ###################################

    @RequestMapping("welcome")
    public ModelAndView executeSecurity(ModelMap model, Principal principal) {

        String name = principal.getName();
        model.addAttribute("author", name);
        model.addAttribute("message", "Welcome To Lingueco user page");
        return new ModelAndView(viewPath + "welcome");

    }

    @RequestMapping("login")
    public ModelAndView login() {


        return new ModelAndView(viewPath + "login");
    }


    //#############################################
    @RequestMapping("admin")
    public ModelAndView admin() {

        return new ModelAndView(viewPath + "admin");
    }

    @RequestMapping("index")
    public ModelAndView user() {
        return new ModelAndView(viewPath + "index");
    }
    //##############################################


    @RequestMapping("fail2login")
    public ModelAndView loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return new ModelAndView(viewPath + "login");

    }

    @RequestMapping("logout")
    public ModelAndView logout(ModelMap model) {

        return new ModelAndView(viewPath + "login");

    }

    @RequestMapping(" ")
    public ModelAndView createUser(@ModelAttribute User user) {
        return new ModelAndView(viewPath + "form");
    }

    @RequestMapping("saveUser")
    public ModelAndView saveUser(@ModelAttribute User user, Role role) {


        if (user.getUser_id() == 0) {

            // TO COMPLETE
            user.setEnabled(1);
            userService.createUser(user);
            role.setUser_id(user.getUser_id());
            role.setRole("ROLE_USER");
            user_role_service.createUser_role(role);
            // TO COMPLETE
        } else {
            userService.updateUser(user);
        }
        return new ModelAndView("redirect:getAllUsers");
    }


    @RequestMapping("editUser")
    public ModelAndView editUser(@RequestParam long id, @ModelAttribute User user) {
        user = userService.getUser(id);
        return new ModelAndView(viewPath + "form", "userObject", user);
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
