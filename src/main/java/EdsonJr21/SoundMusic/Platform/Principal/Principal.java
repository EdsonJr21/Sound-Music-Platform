package EdsonJr21.SoundMusic.Platform.Principal;

import EdsonJr21.SoundMusic.Platform.Model.Artista;
import EdsonJr21.SoundMusic.Platform.Model.Musica;
import EdsonJr21.SoundMusic.Platform.Model.TipoArtista;
import EdsonJr21.SoundMusic.Platform.Repository.ArtistaRepository;
import EdsonJr21.SoundMusic.Platform.Service.ConsultaAPI;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        int opcao = -1;

        while (opcao != 9) {
            String menu = """
                    *** Sound Music Platform ***                    
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        cadastrarArtistas();
                        break;
                    case 2:
                        cadastrarMusicas();
                        break;
                    case 3:
                        listarMusicas();
                        break;
                    case 4:
                        buscarMusicasPorArtista();
                        break;
                    case 5:
                        pesquisarDadosDoArtista();
                        break;
                    case 9:
                        System.out.println("Encerrando a aplicação!");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar a operação: " + e.getMessage());
            }
        }
    }

    private void pesquisarDadosDoArtista() {
        System.out.println("Pesquisar dados de qual artista? ");
        var nome = leitura.nextLine();
        try {
            String informacao = ConsultaAPI.obterInformacao(nome);
            if (informacao != null && !informacao.isEmpty()) {
                System.out.println(informacao);
            } else {
                System.out.println("Artista não encontrado ou sem informações disponíveis.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar informações: " + e.getMessage());
        }
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar músicas de qual artista? ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicaPorArtista(nome);

        if (!musicas.isEmpty()) {
            System.out.println("Músicas do artista " + nome + ":");
            musicas.forEach(System.out::println);
        } else {
            System.out.println("Nenhuma música encontrada para o artista: " + nome);
        }
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        if (!artistas.isEmpty()) {
            System.out.println("Listando todas as músicas:");
            artistas.forEach(a -> {
                System.out.println("Artista: " + a.getNome());
                a.getMusicas().forEach(m -> System.out.println(" - " + m.getTitulo()));
            });
        } else {
            System.out.println("Nenhuma música ou artista cadastrado.");
        }
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar música de qual artista? ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);

        if (artista.isPresent()) {
            System.out.println("Qual o título da música? ");
            var nomeDaMusica = leitura.nextLine();
            Musica musica = new Musica(nomeDaMusica);
            musica.setArtistas(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
            System.out.println("Música cadastrada com sucesso.");
        } else {
            System.out.println("Artista não encontrado.");
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("S")) {
            System.out.println("Qual o nome do artista: ");
            var nome = leitura.nextLine();
            System.out.println("Qual o tipo do artista? (SOLO, DUPLA, BANDA)");
            var tipo = leitura.nextLine().toUpperCase();

            try {
                TipoArtista tipoArtista = TipoArtista.valueOf(tipo);
                Artista artista = new Artista(nome, tipoArtista);
                repositorio.save(artista);
                System.out.println("Artista cadastrado com sucesso.");
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de artista inválido. Escolha entre: SOLO, DUPLA, BANDA.");
            }

            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
