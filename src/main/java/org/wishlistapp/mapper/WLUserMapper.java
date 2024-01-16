package org.wishlistapp.mapper;

import org.wishlistapp.DTO.WLUserDTO;
import org.wishlistapp.model.WLUser;

public class WLUserMapper {

    public WLUser toEntity(WLUserDTO wlUserDTO) {
        WLUser wlUser = new WLUser();
        wlUser.setName(wlUserDTO.getName());
        wlUser.setUsername(wlUserDTO.getUsername());
        return wlUser;
    }

    public WLUserDTO toDTO(WLUser wlUser) {
        WLUserDTO wlUserDTO = new WLUserDTO();
        wlUserDTO.setName(wlUser.getName());
        wlUserDTO.setUsername(wlUser.getUsername());
        return wlUserDTO;
    }
}
