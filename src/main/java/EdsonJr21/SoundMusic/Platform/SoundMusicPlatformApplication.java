package EdsonJr21.SoundMusic.Platform;

import EdsonJr21.SoundMusic.Platform.Principal.Principal;
import EdsonJr21.SoundMusic.Platform.Repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoundMusicPlatformApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SoundMusicPlatformApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();
	}
}
