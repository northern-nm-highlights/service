package edu.cnm.deepdive.nnmhlserver.controller;

import edu.cnm.deepdive.nnmhlserver.model.entity.FavoritePlace;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import edu.cnm.deepdive.nnmhlserver.service.FavoritePlaceService;
import edu.cnm.deepdive.nnmhlserver.service.UserService;
import java.util.List;
import java.util.UUID;
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


/**
 * Exposes rest endpoints on performing operation on {@link FavoritePlace} instances.
 */
@RestController
@RequestMapping("/users/me/favorites")
public class FavoritePlaceController {

  private final UserService userService;
  private final FavoritePlaceService favoritePlaceService;

  /**
   * Initialized this controller with the supporting service instances.
   * @param userService
   * @param favoritePlaceService
   */
  @Autowired
  public FavoritePlaceController(UserService userService,
      FavoritePlaceService favoritePlaceService) {
    this.userService = userService;
    this.favoritePlaceService = favoritePlaceService;
  }

  /**
   * Saves a {@link FavoritePlace} to a specific current {@link User}.
   * @param favoritePlace
   * @return updated {@link FavoritePlace}.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public FavoritePlace post(@RequestBody FavoritePlace favoritePlace) {
    return favoritePlaceService.save(favoritePlace, userService.getCurrentUser());
  }

  /**
   * Returns a list of {@link FavoritePlace} and assigns it to current {@link User}.
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FavoritePlace> list() {
    return userService
        .getCurrentUser()
        .getFavoritePlaces();

  }

  /**
   * Deletes current user's {@link FavoritePlace} by the externalKey.
   * @param externalKey is the unique Id for a current {@link User}.
   */
  @DeleteMapping(value = "/{externalKey}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID externalKey) {
    favoritePlaceService.delete(userService.getCurrentUser(), externalKey);
  }

  /**
   * Returns a specific {@link FavoritePlace} from a current {@link User} list of {@link FavoritePlace}.
   * @param externalKey is the unique Id for a current {@link FavoritePlace}.
   * @return
   */
  @GetMapping(value = "/{externalKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public FavoritePlace get(@PathVariable UUID externalKey) {
    return favoritePlaceService
        .get(userService.getCurrentUser(), externalKey)
        .orElseThrow();
  }

}
