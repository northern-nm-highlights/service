package edu.cnm.deepdive.nnmhlserver.controller;

import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import edu.cnm.deepdive.nnmhlserver.service.UserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get() {
    return userService.getCurrentUser();
  }

  @GetMapping(value = "/{externalKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable UUID externalKey) {
    return userService
        .getByExternalKey(externalKey)
        .orElseThrow();
  }

  @PutMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public User put(@RequestBody User user) {
    return userService
        .update(userService.getCurrentUser(), user);
  }

}
