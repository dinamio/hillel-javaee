package com.hillel.bookstorespringboot.controller;

import com.hillel.bookstorespringboot.dao.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("author")
public class AuthorController {

    public AuthorController() {
    }

    @Autowired
    private AuthorDao authorDao;

//
//    @GetMapping("/listAuthor")
//    public List<Author> findAll() {
//        return (List<Author>) authorDao.findAll();
//    }
//
//    @PostMapping("/addAuthor")
//    public void addAuthor(@RequestBody Author author) {
//        authorDao.save(author);
//    }
//
//    @GetMapping("find/{id}")
//    public Author findOne(@PathVariable Integer id) {
//        return authorDao.findOne(id);
//    }
//
//    @DeleteMapping("delete/{id}")
//    public void deleteAuthor(@PathVariable Integer id) {
//        authorDao.delete(findOne(id));
//    }
//
//    @PutMapping("update/{id}")
//    public void updateAuthor(@RequestBody Author author, @PathVariable Integer id) {
//        author.setId(id);
//        authorDao.save(author);
//    }


}
