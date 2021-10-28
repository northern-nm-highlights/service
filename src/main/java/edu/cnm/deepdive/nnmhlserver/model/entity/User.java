package edu.cnm.deepdive.nnmhlserver.model.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(
    name = "user_profile",
    indexes = {
        @Index(columnList = "name")
    }
    )
/**
 * The user model class for a user of Norther New Mexico Hightlights
 */
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(nullable = false, updatable = false, unique = true, columnDefinition = "UUID")
  private UUID name;

  @Column(nullable = false, updatable = false, unique = true, length = 30)
  private String oathKey;

  @Column(nullable = false, updatable = false, unique = true, length = 50)
  private String displayName;

  public UUID getUser() {
    return id;
  }

  public UUID getName() {
    return name;
  }

  public void setName(UUID name) {
    this.name = name;
  }

  public String getOathKey() {
    return oathKey;
  }

  public void setOathKey(String oathKey) {
    this.oathKey = oathKey;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
