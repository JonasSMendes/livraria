package com.jonasSmendes.livraria.repository;

import com.jonasSmendes.livraria.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE l.dataDefalecimento >= :dataDeaniversario AND l.dataDeAniversario <= :dataDeaniversario")
    List<Livro> anoDeAutoresVivos(int dataDeaniversario);


}
