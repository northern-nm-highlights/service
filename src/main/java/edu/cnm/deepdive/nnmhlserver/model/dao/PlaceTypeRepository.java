package edu.cnm.deepdive.nnmhlserver.model.dao;

import edu.cnm.deepdive.nnmhlserver.model.entity.PlaceType;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Declares additional persistence operations for {@link PlaceType} instances.
 */
public interface PlaceTypeRepository extends JpaRepository<PlaceType, UUID> {

  /**
   * Queries and returns {@link PlaceType} by external key if present in database.
   * @param key public unique id.
   * @return {@link Optional Optional&lt;PlaceType&gt;} containing either a user instance or nothing.}
   */
  Optional<PlaceType> findByExternalKey(UUID key);



}
