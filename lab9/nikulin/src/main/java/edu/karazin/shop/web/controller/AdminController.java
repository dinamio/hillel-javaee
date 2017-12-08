package edu.karazin.shop.web.controller;

import edu.karazin.shop.model.User;
import edu.karazin.shop.service.BasketService;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminPage(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "bookstore-admin";
    }

    @GetMapping(path = "menu")
    @PreAuthorize("hasRole('ADMIN')")
    public String loadMenuPage() {
        return "bookstore-admin-menu";
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getUserManagementPage(@PathVariable(name = "id") Integer id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "bookstore-admin-user-edit";
    }

    @PostMapping(path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUserInfo(User user){
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable(name = "id") Integer id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(path = "orders")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllOrders(Model model){
        model.addAttribute("orders", basketService.getAllOrders());
        return "bookstore-admin-orders";
    }
}
