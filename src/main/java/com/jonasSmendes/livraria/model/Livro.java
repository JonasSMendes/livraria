package com.jonasSmendes.livraria.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String tituloLivro;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_linguagens", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "linguagem")
    private List<String> linguagem;

    private int numeroDeDownload;
    private String nomeAutor;
    private int dataDeAniversario;
    private int dataDefalecimento;

    public Livro() {
    }

    public Livro(LivroData.Result result) {
        this.tituloLivro = result.tituloLivro().toLowerCase();
        this.linguagem = result.linguagem();
        this.numeroDeDownload = result.numeroDeDownload();
        if (!result.autores().isEmpty()) {
            LivroData.Author author = result.autores().get(0);
            this.nomeAutor = author.nomeAutor();
            this.dataDeAniversario = author.dataDeAniversario();
            this.dataDefalecimento = author.dataDefalecimento();
        }
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public List<String> getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(List<String> linguagem) {
        this.linguagem = linguagem;
    }

    public int getNumeroDeDownload() {
        return numeroDeDownload;
    }

    public void setNumeroDeDownload(int numeroDeDownload) {
        this.numeroDeDownload = numeroDeDownload;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public int getDataDeAniversario() {
        return dataDeAniversario;
    }

    public void setDataDeAniversario(int dataDeAniversario) {
        this.dataDeAniversario = dataDeAniversario;
    }

    public int getDataDefalecimento() {
        return dataDefalecimento;
    }

    public void setDataDefalecimento(int dataDefalecimento) {
        this.dataDefalecimento = dataDefalecimento;
    }

    @Override
    public String toString() {
        return """
                #########################
                titulo: %s
                autor: %s
                ano de nascimento: %s
                ano de falecimento: %s
                linguagem: %s
                numero de download: %s
                
                """.formatted(tituloLivro, nomeAutor,dataDeAniversario,dataDefalecimento, linguagem, numeroDeDownload);
    }
}
