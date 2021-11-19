package edu.cnm.deepdive.nnmhlserver.service;

import edu.cnm.deepdive.nnmhlserver.model.dao.FavoritePlaceRepository;
import edu.cnm.deepdive.nnmhlserver.model.dao.UserRepository;
import edu.cnm.deepdive.nnmhlserver.model.entity.FavoritePlace;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides high level persistence and business logic for {@link FavoritePlace} entity.
 */
@Service
public class FavoritePlaceService {


  private final FavoritePlaceRepository favoritePlaceRepository;
  private final UserRepository userRepository;

  /**
   * Initializes service with the required repositories.
   * @param favoritePlaceRepository
   * @param userRepository
   */
  @Autowired
  public FavoritePlaceService(
      FavoritePlaceRepository favoritePlaceRepository,
      UserRepository userRepository) {
    this.favoritePlaceRepository = favoritePlaceRepository;
    this.userRepository = userRepository;
  }

  /**
   * Saves a {@link FavoritePlace} to a set {@link User}.
   * @param favoritePlace
   * @param user
   * @return updated list of {@link FavoritePlace}.
   */
  public FavoritePlace save(FavoritePlace favoritePlace, User user) {
    favoritePlace.setUser(user);
    return favoritePlaceRepository.save(favoritePlace);
  }

  /**
   * Deletes a selected {@link FavoritePlace} from current {@link User} list of favorite places.
   * @param user
   * @param externalKey
   */
  public void delete (User user, UUID externalKey) {
    favoritePlaceRepository
        .findByExternalKeyAndUser(externalKey, user)
        .ifPresent(favoritePlaceRepository::delete);
  }

  /**
   * Returns specified {@link FavoritePlace} by the external key if it's present; Otherwise,
   * choose another option.
   * @param user
   * @param externalKey
   * @return
   */
  public Optional<FavoritePlace> get(User user, UUID externalKey) {
    return favoritePlaceRepository
        .findByExternalKeyAndUser(externalKey, user);
  }
}
