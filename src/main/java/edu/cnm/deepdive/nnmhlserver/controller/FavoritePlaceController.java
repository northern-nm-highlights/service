package edu.cnm.deepdive.nnmhlserver.controller;

import edu.cnm.deepdive.nnmhlserver.model.entity.FavoritePlace;
import edu.cnm.deepdive.nnmhlserver.service.FavoritePlaceService;
import edu.cnm.deepdive.nnmhlserver.service.UserService;
import java.util.List;
import java.util.UUID;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/me/favorites")
public class FavoritePlaceController {

  private final UserService userService;
  private final FavoritePlaceService favoritePlaceService;

  @Autowired
  public FavoritePlaceController(UserService userService,
      FavoritePlaceService favoritePlaceService) {
    this.userService = userService;
    this.favoritePlaceService = favoritePlaceService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public FavoritePlace post(@RequestBody FavoritePlace favoritePlace) {
    return favoritePlaceService.save(favoritePlace, userService.getCurrentUser());
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FavoritePlace> list() {
    return userService
        .getCurrentUser()
        .getFavoritePlaces();

  }

  @DeleteMapping(value = "/{externalKey}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID externalKey) {
    favoritePlaceService.delete(userService.getCurrentUser(), externalKey);
  }

}
