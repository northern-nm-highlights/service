package edu.cnm.deepdive.nnmhlserver.service;

import edu.cnm.deepdive.nnmhlserver.model.dao.UserRepository;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * Provides high level persistence and business logic for {@link User} entity.
 */
@Service
@Profile("service")
public class UserService implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final UserRepository
      repository;

  /**
   * Initializes service with the required repositories.
   * @param repository
   */
  @Autowired
  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt source) {
    Collection<SimpleGrantedAuthority> grants =
        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    return new UsernamePasswordAuthenticationToken(
        getOrCreate(source.getSubject(), source.getClaimAsString("name")),
        source.getTokenValue(), grants);
  }

  /**
   * Returns display name if Oauth key associated with {@link User} exists and if it doesn't exist,
   * user must create one.
   * @param oauthKey
   * @param displayName
   * @return
   */
  public User getOrCreate(String oauthKey, String displayName) {
    return repository
        .findByOauthKey(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setOauthKey(oauthKey);
          user.setDisplayName(displayName);
          return repository.save(user);
        });
  }

  /**
   * Queries and returns {@link User} by unique Id if present in database.
   * @param id
   * @return
   */
  public Optional<User> get(UUID id) {
    return repository.findById(id);

  }

  /**
   * Queries and returns {@link User} by external key if present in database.
   * @param key
   * @return
   */
  public Optional<User> getByExternalKey(UUID key) {
    return repository.findByExternalKey(key);
  }

  /**
   * Returns all users in ascending order by their display name.
   * @return
   */
  public Iterable<User> getAll() {
    return repository.getAllByOrderByDisplayNameAsc();

  }

  /**
   * Saves information tied to {@link User}.
   * @param user
   * @return
   */
  public User save(User user) {
    return repository.save(user);
  }

  /**
   * Deletes current {@link User} profile.
   * @param user
   */
  public void delete(User user) {
    repository.delete(user);
  }


  /**
   * Returns information about the current {@link User}.
   * @return
   */
  public User getCurrentUser() {
    return (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
  }

  /**
   * Returns updated information about {@link User}.
   * @param currentUser
   * @param updatedUser
   * @return
   */
  public User update(User currentUser, User updatedUser) {
    if (updatedUser.getDisplayName() != null) {
      currentUser.setDisplayName(updatedUser.getDisplayName());
      repository.save(currentUser);
    }
    return currentUser;
  }
}
