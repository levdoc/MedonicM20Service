package com.levdoc.m20service.controller;

import com.levdoc.m20service.dto.UserDTO;
import com.levdoc.m20service.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.levdoc.m20service.constants.UserRoleConstants.*;

@Controller
@RequestMapping("/admin")
public class AdminControllerMVC {
    private final UserService userService;

    public AdminControllerMVC(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getAllUser(Model model) {
        List<UserDTO> userList = userService.listAll();
        model.addAttribute("users", userList);
        return "admin/allUsers";
    }

    @GetMapping("/getinfo/user/{id}")
    public String getUserInfo(@PathVariable Long id,
                              Model model) {
        UserDTO user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/viewUserInfo";
    }

    @GetMapping("/edit/user/{id}")
    public String editUserInfo(@PathVariable Long id,
                               Model model) {
        UserDTO user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/editUserInfo";
    }

    @PostMapping("/edit/user/")
    public String update(@ModelAttribute("userUpdate") UserDTO userDTO) {
        userService.update(userDTO);
        return "redirect:/admin";
    }

    @GetMapping("/delete/user/{id}")
    public String hardDeleteUser(@PathVariable Long id) {
        userService.hardDelete(id);
        return "redirect:/admin";
    }

    @GetMapping("/block/user/{id}")
    public String block(@PathVariable Long id) {
        userService.blockUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/unblock/user/{id}")
    public String unblock(@PathVariable Long id) {
        userService.unblockUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/create/user")
    public String createUser(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "admin/registration";
    }

    @PostMapping("/create/user")
    public String createUser(@ModelAttribute("userForm") UserDTO userDTO,
                             BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "admin/registration";
        }
        userService.create(userDTO);
        return "redirect:/admin";
    }


}
