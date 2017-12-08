package edu.karazin.shop.web.controller;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.OrderList;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.BookStoreService;
import edu.karazin.shop.service.BasketService;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "order")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OrderController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private BookStoreService bookStoreService;

    @Autowired
    private UserService userService;


    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String getOrderPage(Model model, Principal principal) {
        User currentUser = userService.getUserByName(principal.getName());
        List<BookList> books = basketService.getOrderByUserId(currentUser).getBookLists();
        OrderList order = basketService.getOrderByUserId(currentUser);
        model.addAttribute("products", books);
        model.addAttribute("totalPrice", new TotalPrice(order.getTotalPrice()));
        return "bookstore-order";
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("isAuthenticated()")
    public String addProduct(@PathVariable(name = "id") Long id, Principal principal){
        User user = userService.getUserByName(principal.getName());
        basketService.addBookToOrder(user, bookStoreService.getBookById(id));
        return "redirect:/books/show";
    }

    @GetMapping(value = "remove/{id}")
    @PreAuthorize("isAuthenticated()")
    public String removeProductFromBasket(@PathVariable(name = "id") Long id, Principal principal){
        User user = userService.getUserByName(principal.getName());
        basketService.removeBookFromOrder(user, id);
        return "redirect:/order";
    }

    @GetMapping(value = "complete")
    @PreAuthorize("isAuthenticated()")
    public String completeOrder(Principal principal){
        User user = userService.getUserByName(principal.getName());
        OrderList order = basketService.getOrderByUserId(user);
        basketService.addOrderToStory(order);
        basketService.removeOrder(user);
        return "redirect:/books/show";
    }

    @GetMapping(value = "story")
    @PreAuthorize("isAuthenticated()")
    public String getOrderStory(Model model, Principal principal){
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("story", basketService.getOrderStory(user.getUsername()));
        return "bookstore-story";
    }
}
