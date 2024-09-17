package EdsonJr21.SoundMusic.Platform.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Artista artistas;

    public Musica() {}

    public Musica(String nomeDaMusica) {
        this.titulo = nomeDaMusica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtistas() {
        return artistas;
    }

    public void setArtistas(Artista artistas) {
        this.artistas = artistas;
    }

    @Override
    public String toString() {
        return ", Titulo='" + titulo + '\'' +
                ", artista=" + artistas.getNome();
    }
}
