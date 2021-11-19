package edu.cnm.deepdive.nnmhlserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import edu.cnm.deepdive.nnmhlserver.model.entity.User;

/**
 * Operates and hosts applications and associated services for {@link User}
 */
@SpringBootApplication
public class NnmhlServerApplication {

  /**
   * Main method for running Nnmhl server.
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(NnmhlServerApplication.class, args);
  }

}
