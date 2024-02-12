package org.wishlistapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.wishlistapp.DTO.WLUserDTO;
import org.wishlistapp.exception.EntityNotFoundException;
import org.wishlistapp.exception.UsernameExistsException;
import org.wishlistapp.model.WLUser;
import org.wishlistapp.repository.WLUserRepository;

import java.util.List;
import java.util.Optional;

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
        if (user == null) {
            throw new EntityNotFoundException(WLUser.class.getName(), username);
        }
        return modelMapper.map(user, WLUserDTO.class);
    }

    public List<WLUserDTO> getAllUsers() {
        var users = wlUserRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, WLUserDTO.class))
                .toList();
    }

    public WLUserDTO addUser(WLUserDTO wlUserDTO) {
        var username = wlUserDTO.getUsername();
        var userExists = wlUserRepository.findByUsername(username);
        if (userExists != null) {
            throw new UsernameExistsException(username);
        }
        var user = modelMapper.map(wlUserDTO, WLUser.class);
        wlUserRepository.save(user);
        return wlUserDTO;
    }
}
