package edu.cnm.deepdive.nnmhlserver.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
 * Encapsulates the key properties of a place type including; place type name, place type id and
 * display name of place tied to a {@link User}.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class PlaceType {

  @Id
  @GeneratedValue
  @Column(name = "place_type_id", nullable = false, updatable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(updatable = false, nullable = false, columnDefinition = "UUID", unique = true)
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey = UUID.randomUUID();

  @Column(nullable = false, updatable = false, length = 50, unique = true)
  private String name;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  private Date created;

  @Column(nullable = false, updatable = false, length = 50, unique = true)
  private String displayName;

  /**
   * Returns primary key for a place type.
   * @return
   */
  public UUID getId() {
    return id;
  }

  /**
   * Returns unique Id for a place type.
   * @return
   */
  public UUID getExternalKey() {
    return externalKey;
  }

  /**
   * Returns name of places within a place type.
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Assigns name of places within a place type.
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns date timestamp from creation of place type.
   * @return
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Returns display name of place type.
   * @return
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Assigns display name of place type.
   * @param displayName
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
