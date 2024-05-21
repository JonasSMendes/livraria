package com.jonasSmendes.livraria.principal;

import com.jonasSmendes.livraria.model.Livro;
import com.jonasSmendes.livraria.model.LivroData;
import com.jonasSmendes.livraria.repository.LivroRepository;
import com.jonasSmendes.livraria.service.ConsumoAPI;
import com.jonasSmendes.livraria.service.ConverteDados;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

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
            2-  Exibir livros listados
            
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
}
