package edu.cnm.deepdive.nnmhlserver.controller;

import edu.cnm.deepdive.nnmhlserver.model.entity.PlaceType;
import edu.cnm.deepdive.nnmhlserver.service.PlaceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place-types")
public class PlaceTypeController {

  private final PlaceTypeService service;

  @Autowired
  public PlaceTypeController(PlaceTypeService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<PlaceType> list() {
    return service.getAll();
  }
}
