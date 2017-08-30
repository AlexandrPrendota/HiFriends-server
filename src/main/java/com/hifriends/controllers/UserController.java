package com.hifriends.controllers;

import com.hifriends.model.dto.UserDto;
import com.hifriends.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for user operation
 *
 * @author by aleksandrprendota on 24.08.17.
 */
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get list of active user in chat
     *
     * @return user dto
     */
    @RequestMapping(value = "{id}/active/", method = RequestMethod.GET)
    public List<UserDto> getActiveUsers(@PathVariable long id) {
        return userService.getAllActiveUsers(id);
    }

    /**
     * Logout user after he left the chat.
     * Set his active to false
     *
     * @param id
     */

    @RequestMapping(value = "/logout/{id}", method = RequestMethod.POST)
    public void updateUserStatus(@PathVariable long id) {
        userService.updateUserStatus(id);
    }

    /**
     * User notification without current companion notifications
     *
     * @param ownerId id
     * @return userDto
     */
    @RequestMapping(value = "/notify/owner/{ownerId}", method = RequestMethod.GET)
    public List<UserDto> userNotifications(@PathVariable long ownerId) {
        return userService.getUsersIdsByNewMessages(ownerId);
    }
}
