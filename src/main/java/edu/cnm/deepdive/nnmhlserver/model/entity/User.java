package edu.cnm.deepdive.nnmhlserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Encapsulates the key properties of a user including; display name, external key and user id.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    name = "user_profile",
    indexes = {
        @Index(columnList = "created")
    }
)
@JsonPropertyOrder({"id", "created", "displayName"})
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID id;

  @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey = UUID.randomUUID();

  @Column(nullable = false, updatable = false, unique = true, length = 30)
  @JsonIgnore
  private String oauthKey;

  @Column(nullable = false, updatable = false, unique = true, length = 50)
  private String displayName;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Date created;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("placeName ASC")
  @JsonIgnore
  private final List<FavoritePlace> favoritePlaces = new LinkedList<>();

  /**
   * Returns primary key for a user.
   * @return
   */
  public UUID getId() {
    return id;
  }

  /**
   * Returns unique Id for specific user.
   * @return
   */
  public UUID getExternalKey() {
    return externalKey;
  }

  /**
   * Returns the Oauth key associated with user.
   * @return
   */
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Assigns Oauth key associated with user.
   * @param oathKey
   */
  public void setOauthKey(String oathKey) {
    this.oauthKey = oathKey;
  }

  /**
   * Returns generated date timestamp of creation of the user.
   * @return
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Returns display name given to user when signed into Google Sign-in.
   * @return
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Assigns display name given to user when signed into Google Sign-in.
   * @param displayName
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Returns list of {@link FavoritePlace} selected by user.
   * @return
   */
  public List<FavoritePlace> getFavoritePlaces() {
    return favoritePlaces;
  }
}

