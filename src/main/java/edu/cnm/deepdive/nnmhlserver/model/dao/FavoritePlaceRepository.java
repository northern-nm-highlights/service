package edu.cnm.deepdive.nnmhlserver.model.dao;

import edu.cnm.deepdive.nnmhlserver.model.entity.FavoritePlace;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePlaceRepository extends JpaRepository<FavoritePlace, UUID> {

//  TODO find by primary key instead of external key
  Optional<FavoritePlace> findByExternalKey(UUID key);

//  TODO find by primary key instead of external key
  Optional<FavoritePlace> findByExternalKeyAndUser(UUID key, User user);

}
