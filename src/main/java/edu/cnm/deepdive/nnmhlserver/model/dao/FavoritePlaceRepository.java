package edu.cnm.deepdive.nnmhlserver.model.dao;

import edu.cnm.deepdive.nnmhlserver.model.entity.FavoritePlace;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Declares additional persistence operations for {@link FavoritePlace} instances.
 */
public interface FavoritePlaceRepository extends JpaRepository<FavoritePlace, UUID> {

  /**
   * Queries and returns {@link FavoritePlace} by external key if present in database.
   * @param key public unique id.
   * @return {@link Optional Optional&lt;FavoritePlace&gt;} containing either a external key instance or nothing.}
   */
  Optional<FavoritePlace> findByExternalKey(UUID key);

  /**
   * Queries and returns {@link FavoritePlace} by external key and {@link User}
   * @param key public unique id.
   * @param user current {@link User}
   * @return {@link Optional Optional&lt;FavoritePlace&gt;} containing either a favorite place instance or nothing.}
   */
  Optional<FavoritePlace> findByExternalKeyAndUser(UUID key, User user);

}
