package edu.cnm.deepdive.nnmhlserver.model.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class PlaceType {

  @Id
  @GeneratedValue
  @Column(name = "place_type_id", nullable = false, updatable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(nullable = false, updatable = false, length = 50, unique = true)
  private String name;

  @Column(nullable = false, updatable = false, length = 50, unique = true)
  private String displayName;

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
