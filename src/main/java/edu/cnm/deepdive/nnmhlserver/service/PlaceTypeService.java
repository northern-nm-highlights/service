package edu.cnm.deepdive.nnmhlserver.service;

import edu.cnm.deepdive.nnmhlserver.model.dao.FavoritePlaceRepository;
import edu.cnm.deepdive.nnmhlserver.model.dao.PlaceTypeRepository;
import edu.cnm.deepdive.nnmhlserver.model.entity.FavoritePlace;
import edu.cnm.deepdive.nnmhlserver.model.entity.PlaceType;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceTypeService {

  private final PlaceTypeRepository placeTypeRepository;
  private final FavoritePlaceRepository favoritePlaceRepository;

  @Autowired
  public PlaceTypeService(PlaceTypeRepository placeTypeRepository,
      FavoritePlaceRepository favoritePlaceRepository) {
    this.placeTypeRepository = placeTypeRepository;
    this.favoritePlaceRepository = favoritePlaceRepository;
  }

  public Optional<PlaceType> get(UUID id) {
    return placeTypeRepository.findById(id);
  }

  public Optional<PlaceType> get(UUID key, User user) {
    return placeTypeRepository.findByExternalKeyAndUser(key, user);
  }

  public void delete(UUID id) {
    placeTypeRepository.deleteById(id);
  }

  public void delete(UUID key, User user) {
    placeTypeRepository
        .findByExternalKeyAndUser(key, user)
        .ifPresent(placeTypeRepository::delete);
  }

}