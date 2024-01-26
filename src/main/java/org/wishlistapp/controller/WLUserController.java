package org.wishlistapp.controller;

import org.springframework.web.bind.annotation.*;
import org.wishlistapp.DTO.WLUserDTO;
import org.wishlistapp.service.WLUserService;

import java.util.List;

@RestController
public class WLUserController {
    private final WLUserService wlUserService;

    public WLUserController(WLUserService wlUserService) {
        this.wlUserService = wlUserService;
    }

    @GetMapping("/users")
    public List<WLUserDTO> getAllUsers() {
        return wlUserService.getAllUsers();
    }

    @GetMapping("/users/{username}")
    public WLUserDTO getUserByUsername(@PathVariable String username) {
        return wlUserService.getUserByUsername(username);
    }

    @PostMapping("/users")
    public WLUserDTO addUser(@RequestBody WLUserDTO wlUserDTO) {
        return wlUserService.addUser(wlUserDTO);
    }

}
