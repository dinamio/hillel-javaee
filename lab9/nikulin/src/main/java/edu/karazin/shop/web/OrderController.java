package edu.karazin.shop.web;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.OrderList;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.BookStoreService;
import edu.karazin.shop.service.BasketService;
import edu.karazin.shop.util.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private BookStoreService bookStoreService;

    @Autowired
    private UserData userData;


    @GetMapping
    public String getOrderPage(Model model) {

        edu.karazin.shop.model.User currentUser = userData.getUser();
        List<BookList> books = basketService.getOrderByUserId(currentUser).getBookLists();
        OrderList order = basketService.getOrderByUserId(currentUser);
        model.addAttribute("products", books);

        System.out.println(new TotalPrice(order.getTotalPrice()).getPrice());

        model.addAttribute("totalPrice", new TotalPrice(order.getTotalPrice()));

        return "bookstore-order";
    }

    @GetMapping(value = "{id}")
    public String addProduct(@PathVariable(name = "id") Long id){
        edu.karazin.shop.model.User user = userData.getUser();
        basketService.addBookToOrder(user, bookStoreService.getBookById(id));
        return "redirect:/order";
    }

    @GetMapping(value = "remove/{id}")
    public String removeProductFromBasket(@PathVariable(name = "id") Long id){
        User user = userData.getUser();
        basketService.removeBookFromOrder(user, id);
        return "redirect:/order";
    }

    @GetMapping(value = "complete")
    public String completeOrder(){
        User user = userData.getUser();
        OrderList order = basketService.getOrderByUserId(user);
        basketService.addOrderToStory(order);
        basketService.removeOrder(user);
        return "redirect:/books/show";
    }

    @GetMapping(value = "story")
    public String getOrderStory(Model model){
        User user = userData.getUser();
        model.addAttribute("story", basketService.getOrderStory(user.getUsername()));
        return "bookstore-story";
    }
}
