package com.jonasSmendes.livraria.principal;

import com.jonasSmendes.livraria.model.Livro;
import com.jonasSmendes.livraria.model.LivroData;
import com.jonasSmendes.livraria.repository.LivroRepository;
import com.jonasSmendes.livraria.service.ConsumoAPI;
import com.jonasSmendes.livraria.service.ConverteDados;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;

public class ExibeLivraria {

    private  Scanner scanner = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private ConverteDados converte = new ConverteDados();

    private final String ENDERECO = "http://gutendex.com/books/?search=";

    private List<Livro> livro = new ArrayList<>();

    private LivroRepository repository;

    public ExibeLivraria(LivroRepository repository) {
        this.repository = repository;
    }

    String logo = """
            ⣿⣿⣷⣿⡿⣼⣿⣿⣿⣿⣿⡵⣻⣿⣧⣿⣿⣿⣮⡻⣿⠄⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣸⣧⠿⠟⢿⣿⣿⣿⣿⣦⠻⣿⣻⣿⣿⣿⣿⣮⣀⢿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⠏⣿⢡⣤⣤⣤⣈⠛⢿⣿⢿⣷⣝⣫⢿⣿⣿⣿⣿⣿⣷⣭⣟⡿⢿⣻⣯⣵
            ⣿⣿⡆⣏⢸⣿⣿⣿⣿⣷⣦⡈⣷⣽⣿⣯⡊⢿⣿⣿⣿⣿⣿⣭⣵⣶⣷⣝⡻⣿
            ⣿⣿⣷⣻⣼⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣾⣿⣿⡿⠋⠛⣋⣉⡉⠙⣿⣫⣾
            ⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⣿⣿⣗⣵⣿⣿
            ⠈⢿⣿⣿⣹⣿⣿⣿⡿⠟⠋⠉⠉⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣼⣿⣿⡟
            ⠄⠄⠹⣿⣷⢻⣿⢏⣶⣶⣶⣶⣶⣶⣦⡄⡀⠄⢹⣿⣿⣿⣿⣿⣿⣇⣿⣿⣿⠃
            ⣄⠄⠄⠄⠻⣧⢙⡺⢿⣿⣿⣿⣿⣿⣿⣿⣿⢇⣾⣿⣿⣿⣿⡿⣫⣾⣿⣿⡏⠄
            ⣷⣿⡿⢿⣿⣾⡥⢮⢪⣏⠿⠻⠻⠟⠛⠟⠯⠾⠿⠿⠛⠋⢱⣿⣿⣿⣿⣿⠁⠄
            """;

    String menu = """
            ########### Livraria #############
            
            1 - Buscar livro pelo titulo
            2 - Exibir livros listados
            3 - Exibir autores cadastrados
            4 - Exibir autores vivos em determinado ano
            5 - Listar livros em um idioma
            
            0 - sair.
            
            ##################################
            """;



    public void exibeMenu(){
        var chaveMenu = -1;
        System.out.println(logo);

        while (chaveMenu != 0){
            System.out.println(menu);

                chaveMenu = scanner.nextInt();
                scanner.nextLine();

                switch (chaveMenu){
                    case 1:
                        exibirLivros();
                        break;
                    case 2:
                        exibirListaDeLivros();
                        break;
                    case 3:
                        exibirAutoresLivro();
                        break;
                    case 4:
                        autoresQueEstavamVivos();
                        break;
                    case 5:
                        listaLivrosPorIdioma();
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
        }
    }




    private LivroData buscarLivros() {
        System.out.println("digite o livro que deseja");
        var nomeDoLivro = scanner.nextLine().toLowerCase();

        var json = consumoAPI.obterDados(ENDERECO + nomeDoLivro.replace(" ", "+"));
        LivroData dados = converte.obterDados(json, LivroData.class);

        return dados;
    }

    private void exibirLivros(){
        LivroData data = buscarLivros();
        if (data != null && data.resultados() != null && !data.resultados().isEmpty()) {
            LivroData.Result result = data.resultados().get(0);
            Livro livro = new Livro(result);

            try {
                repository.save(livro);
                System.out.println("Livro salvo: " + livro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("O livro '" + result.tituloLivro() + "' já existe no banco de dados.");
            }

        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    }


    private void exibirListaDeLivros(){
       livro = repository.findAll();
       livro.stream()
               .sorted(Comparator.comparing(Livro::getTituloLivro))
               .forEach(System.out::println);

    }

    private void exibirAutoresLivro(){
        livro = repository.findAll();
        List<String> autores = livro.stream()
                .map(Livro::getNomeAutor)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        autores.forEach(l -> System.out.println("Autor : " + l));
    }

    private void autoresQueEstavamVivos() {
        System.out.println("quer ver os autores vivos que de ano? ");
        var autoresvivos = scanner.nextInt();
        scanner.nextLine();

        livro = repository.anoDeAutoresVivos(autoresvivos);

        livro.stream()
                .sorted(Comparator.comparing(Livro::getTituloLivro))
                .forEach(System.out::println);
    }

    private void listaLivrosPorIdioma() {
        System.out.println("qual linguagem voce procura? user caracter Ex: pt, en ...");
        var linguagemEscolhida = scanner.nextLine();

        livro = repository.findAll();

        List<Livro> livrosEscolhidosPorLinguagem = livro.stream()
                .filter(l -> l.getLinguagem().contains(linguagemEscolhida))
                .sorted(Comparator.comparing(Livro::getNumeroDeDownload))
                .collect(Collectors.toList());

        livrosEscolhidosPorLinguagem.forEach(System.out::println);
    }

}
