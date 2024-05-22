# Desafio livraria - Alura/oracle education

* Projeto java | Spring-boot

* Usando Api - [Gutendex](https://gutendex.com/)

* Banco [Postgress](https://www.postgresql.org/docs/)

![image](https://github.com/JonasSMendes/livraria-desafio-oracle-education/assets/119429346/38225385-3fb8-4bd0-9cbc-fc0dbe6a47c2)

# Aplicabilidade do banco de dados

Como o desafio foi feito em postgress, a pasta application.properties precisa ser preenchida com os seus dados do banco:

* spring.datasource.url=jdbc:postgresql://${DB_HOST -- seu localhost}/livraria
* spring.datasource.username=${DB_USERNAME  -- nome}
* spring.datasource.password=${DB_PASS  -- senha}
* spring.datasource.driver-class-name=org.postgresql.Driver
* hibernate.dialect=org.hibernate.dialect.HSQLDialect

