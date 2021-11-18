package edu.cnm.deepdive.nnmhlserver.service;

import edu.cnm.deepdive.nnmhlserver.model.dao.FavoritePlaceRepository;
import edu.cnm.deepdive.nnmhlserver.model.dao.UserRepository;
import edu.cnm.deepdive.nnmhlserver.model.entity.FavoritePlace;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritePlaceService {


  private final FavoritePlaceRepository favoritePlaceRepository;
  private final UserRepository userRepository;

  @Autowired
  public FavoritePlaceService(
      FavoritePlaceRepository favoritePlaceRepository,
      UserRepository userRepository) {
    this.favoritePlaceRepository = favoritePlaceRepository;
    this.userRepository = userRepository;
  }

  public FavoritePlace save(FavoritePlace favoritePlace, User user) {
    favoritePlace.setUser(user);
    return favoritePlaceRepository.save(favoritePlace);
  }

  public void delete (User user, UUID externalKey) {
    favoritePlaceRepository
        .findByExternalKeyAndUser(externalKey, user)
        .ifPresent(favoritePlaceRepository::delete);
  }

  public Optional<FavoritePlace> get(User user, UUID externalKey) {
    return favoritePlaceRepository
        .findByExternalKeyAndUser(externalKey, user);
  }
}
