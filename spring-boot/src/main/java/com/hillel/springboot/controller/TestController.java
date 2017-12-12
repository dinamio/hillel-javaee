package com.hillel.springboot.controller;

import com.hillel.springboot.dao.ServerDao;
import com.hillel.springboot.model.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by eugen on 11/21/17.
 */
@RestController
@RequestMapping("rest")
public class TestController {

    @Autowired
    ServerDao serverDao;

    @GetMapping("servers")
    public List<Server> getServers () {
        return (List<Server>) serverDao.findAll();
    }


    @GetMapping("servers/{name}")
    public List<Server> getServersByName (@PathVariable String name) {
        return serverDao.findByNameLike("%"+name+"%");
    }

    @PostMapping("server")
    public void addServer(@RequestBody Server server) {
        serverDao.save(server);
    }

    @GetMapping("server/{id}")
    public Server getServer(@PathVariable Integer id) {
        return serverDao.findOne(id);
    }

    @DeleteMapping("server/{id}")
    public void deleteServer(@PathVariable Integer id) {
        serverDao.delete(id);
    }


    @PutMapping("server/{id}")
    public void updateServer(@RequestBody Server server, @PathVariable Integer id) {
        server.setId(id);
        serverDao.save(server);
    }
}
