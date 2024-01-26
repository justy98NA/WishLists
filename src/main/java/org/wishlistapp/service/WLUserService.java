package org.wishlistapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.wishlistapp.DTO.WLUserDTO;
import org.wishlistapp.model.WLUser;
import org.wishlistapp.repository.WLUserRepository;

import java.util.List;

@Service
public class WLUserService {
    private final WLUserRepository wlUserRepository;
    private final ModelMapper modelMapper;

    public WLUserService(WLUserRepository wlUserRepository, ModelMapper modelMapper) {
        this.wlUserRepository = wlUserRepository;
        this.modelMapper = modelMapper;
    }

    public WLUserDTO getUserByUsername(String username) {
        var user = wlUserRepository.findByUsername(username);
        return modelMapper.map(user, WLUserDTO.class);
    }

    public List<WLUserDTO> getAllUsers() {
        var users = wlUserRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, WLUserDTO.class))
                .toList();
    }

    public WLUserDTO addUser(WLUserDTO wlUserDTO) {
        var user = modelMapper.map(wlUserDTO, WLUser.class);
        wlUserRepository.save(user);
        return wlUserDTO;
    }
}
