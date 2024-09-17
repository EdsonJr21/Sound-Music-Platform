package EdsonJr21.SoundMusic.Platform.Repository;

import EdsonJr21.SoundMusic.Platform.Model.Artista;
import EdsonJr21.SoundMusic.Platform.Model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Musica> buscaMusicaPorArtista(String nome);
}
