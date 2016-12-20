package morti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MortiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MortiApplication.class, args);
    }

}
