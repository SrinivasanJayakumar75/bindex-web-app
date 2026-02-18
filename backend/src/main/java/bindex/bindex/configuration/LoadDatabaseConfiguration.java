package bindex.bindex.configuration;


import bindex.bindex.features.authentication.controller.model.AuthenticationUser;
import bindex.bindex.features.authentication.controller.repository.AuthenticationUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import bindex.bindex.features.authentication.utils.Encoder;

@Configuration
public class LoadDatabaseConfiguration {
    private final Encoder encoder;

    public LoadDatabaseConfiguration(Encoder encoder){
        this.encoder = encoder;
    }

    @Bean
    public CommandLineRunner initDatabase (AuthenticationUserRepository authenticationUserRepository){
        return args -> {
            AuthenticationUser authenticationUser = new AuthenticationUser("sjayakumar862412@gmail.com", encoder.encode("sri75"));
            authenticationUserRepository.save(authenticationUser);
        };
    }
}
