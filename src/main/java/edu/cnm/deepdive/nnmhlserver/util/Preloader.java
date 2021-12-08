package edu.cnm.deepdive.nnmhlserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.nnmhlserver.model.dao.PlaceTypeRepository;
import edu.cnm.deepdive.nnmhlserver.model.entity.PlaceType;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Profile("preload")
public class Preloader implements CommandLineRunner {

  public static final String PLACE_TYPES_RESOURCE = "preload/place-types.json";
  private final PlaceTypeRepository repository;

  @Autowired
  public Preloader(PlaceTypeRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    ClassPathResource resource = new ClassPathResource(PLACE_TYPES_RESOURCE);
    try (InputStream input = resource.getInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      repository.saveAllAndFlush(
          Stream
              .of(mapper.readValue(input, String[].class))
              .map((name) -> {
                PlaceType placeType = new PlaceType();
                placeType.setName(name);
                placeType.setDisplayName(name.replace('_', ' '));
                return placeType;
              })
              .collect(Collectors.toList())
      );
    }
  }
}
