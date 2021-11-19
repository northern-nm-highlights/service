package edu.cnm.deepdive.nnmhlserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;


/**
 * Encapsulates the key properties of a favorite place including; favorite place id, city name, place
 * id and place name tied to a {@link User}.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id", "created", "cityName", "placeId", "placeName"})
public class FavoritePlace {

  @Id
  @GeneratedValue
  @Column(name = "favorite_place_id", nullable = false, updatable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID id;

  @Column(updatable = false, nullable = false, columnDefinition = "UUID", unique = true)
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey = UUID.randomUUID();

  @Column(nullable = false, updatable = false, length = 100)
  private String cityName;

  @Column(nullable = false, updatable = false, length = 50)
  private String placeId;

  @Column(nullable = false, updatable = false, length = 255)
  private String placeName;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnore
  private User user;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Date created;

  /**
   * Returns primary key for a favorite place.
   * @return
   */
  public UUID getId() {
    return id;
  }

  /**
   * Returns unique Id for favorite place.
   * @return
   */
  public UUID getExternalKey() {
    return externalKey;
  }

  /**
   * Returns city name of favorite place's location.
   * @return
   */
  public String getCityName() {
    return cityName;
  }

  /**
   * Assigns specificed city to this city name.
   * @param cityName
   */
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  /**
   * Returns assigned {@link User} favorite places.
   * @return
   */
  public User getUser() {
    return user;
  }

  /**
   * Assigns {@link User} favorite places.
   * @param user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Returns generated date stamp of creation for the favorite place.
   * @return
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Returns the Google Place's API unique Id for favorite place.
   * @return
   */
  public String getPlaceId() {
    return placeId;
  }

  /**
   * Assigns Google Place's API unique Id for favorite place.
   * @param placeId
   */
  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  /**
   * Returns name of favorite place by the place Id.
   * @return
   */
  public String getPlaceName() {
    return placeName;
  }

  /**
   * Assigns name of favorite place by the place Id.
   * @param placeName
   */
  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }
}
