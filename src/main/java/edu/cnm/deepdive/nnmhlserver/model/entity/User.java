package edu.cnm.deepdive.nnmhlserver.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * The user model class for a user of Northern New Mexico Highlights
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    name = "user_profile",
    indexes = {
        @Index(columnList = "name")
    }
)
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey = UUID.randomUUID();

  @Column(nullable = false, updatable = false, unique = true, length = 30)
  private String oauthKey;

  @Column(nullable = false, updatable = false, unique = true, length = 50)
  private String displayName;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("placeName ASC")
  private final List<FavoritePlace> favoritePlaces = new LinkedList<>();

  public UUID getId() {
    return id;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public UUID getExternalKey() {
    return externalKey;
  }

  public void setExternalKey(UUID externalKey) {
    this.externalKey = externalKey;
  }

  public void setOauthKey(String oathKey) {
    this.oauthKey = oathKey;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public List<FavoritePlace> getFavoritePlaces() {
    return favoritePlaces;
  }
}

