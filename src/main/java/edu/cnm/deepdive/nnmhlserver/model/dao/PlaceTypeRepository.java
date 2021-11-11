package edu.cnm.deepdive.nnmhlserver.model.dao;

import edu.cnm.deepdive.nnmhlserver.model.entity.PlaceType;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceTypeRepository extends JpaRepository<PlaceType, UUID> {

//  TODO find by primary key instead of external key.
  Optional<PlaceType> findByExternalKey(UUID key);

//  TODO find by primary key instead of external key.
  Optional<PlaceType> findByExternalKeyAndUser(UUID key, User user);

}
