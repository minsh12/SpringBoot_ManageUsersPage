package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    // 사용자 목록을 보기위한 요청을 처리하는 handler method
    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);     // (name, value)

        return "users";
    }

    // 새 사용자 양식을 표시하는 handler method
    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    // 새 사용자 양식의 제출을 처리하기위한 handler method
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "The user has been successfully.");
        return "redirect:/users";
    }

    // 기존 사용자의 정보를 수정하기 위한 handler method
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);            // id값을 호출
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID:" + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {     // 예외
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    // 기존 사용자의 정보를 삭제하기 위한 handler method
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserNotFoundException e) {     // 예외
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
