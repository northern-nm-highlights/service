package edu.cnm.deepdive.nnmhlserver.model.dao;

import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Declares additional persistence operations for {@link User} instances.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

  /**
   * Queries and returns {@link User} by Oauth key if present in database.
   * @param oauthKey OAuth2.0 unique Id.
   * @return {@link Optional Optional&lt;User&gt;} containing either a user instance or nothing.
   */
  Optional<User> findByOauthKey(String oauthKey);

  /**
   * Queries and returns {@link User} by external key if present in database.
   * @param externalKey public unique Id.
   * @return {@link Optional Optional&lt;User&gt;} containing either a user instance or nothing.
   */
  Optional<User> findByExternalKey(UUID externalKey);

  /**
   * Returns all users in ascending order by their display name.
   * @return {@link Iterable Iterable&lt;User&gt;}
   */
  Iterable<User> getAllByOrderByDisplayNameAsc();
}
