# Desafio livraria - Alura/oracle education 📚

## tecnologia do projeto 📖:

* Java
* Spring-boot
* Spring Data JPA
* [PostgreSQL](https://www.postgresql.org/docs/)
* Maven
* JSON
* Usando Api - [Gutendex](https://gutendex.com/)

## Projeto 📊

***Sistema em linha, que busca livros da API Gutendex e salva no banco, depois
é tratado para as de mais funcionalidades, sendo essas como pode ver a baixo⬇️***

![image](https://github.com/JonasSMendes/livraria-desafio-oracle-education/assets/119429346/38225385-3fb8-4bd0-9cbc-fc0dbe6a47c2)

## Aplicabilidade do banco de dados 📍

Como o desafio foi feito em postgress, a pasta application.properties precisa ser preenchida com os seus dados do banco:

* spring.datasource.url=jdbc:postgresql://${DB_HOST -- seu localhost}/livraria
* spring.datasource.username=${DB_USERNAME  -- nome}
* spring.datasource.password=${DB_PASS  -- senha}
* spring.datasource.driver-class-name=org.postgresql.Driver
* hibernate.dialect=org.hibernate.dialect.HSQLDialect

## Autor

***esse projeto foi desenvolvido por [JonasSmendes](https://repositorio-jonas-mendes.vercel.app/) no desafio Alura juntamenta com a oracle - education.***
