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

/**
 * Provides high level persistence and business logic for {@link PlaceType} entity.
 */
@Service
public class PlaceTypeService {

  private final PlaceTypeRepository placeTypeRepository;
  private final FavoritePlaceRepository favoritePlaceRepository;

  /**
   * Initializes service with required repositories.
   * @param placeTypeRepository
   * @param favoritePlaceRepository
   */
  @Autowired
  public PlaceTypeService(PlaceTypeRepository placeTypeRepository,
      FavoritePlaceRepository favoritePlaceRepository) {
    this.placeTypeRepository = placeTypeRepository;
    this.favoritePlaceRepository = favoritePlaceRepository;
  }

  /**
   * Returns place type by primary key value.
   * @param id
   * @return place type if specified place type exists.
   */
  public Optional<PlaceType> get(UUID id) {
    return placeTypeRepository.findById(id);
  }

  /**
   * Returns place type by external key value.
   * @param key
   * @return place type if specified place type exists.
   */
  public Optional<PlaceType> getByExternalKey(UUID key) {
    return placeTypeRepository.findByExternalKey(key);
  }

}

