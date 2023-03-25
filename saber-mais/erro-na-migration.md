## Para saber mais: Erro na migration

Conforme orientado ao longo dessa aula é importante sempre parar o projeto ao criar os arquivos de migrations, para evitar que o Flyway os execute antes da hora, com o código ainda incompleto, causando com isso problemas.

Entretanto, eventualmente pode acontecer de esquecermos de parar o projeto e algum erro acontecer ao tentar inicializar a aplicação. Nesse caso será exibido o seguinte erro ao tentar inicializar a aplicação:

```
Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'flywayInitializer' defined in class path resource [org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]: Validate failed: Migrations have failed validation
```
Perceba na mensagem de erro que é indicado que alguma migration falhou, impedindo assim que o projeto seja inicializado corretamente. Esse erro também pode acontecer se o código da migration estiver inválido, contendo algum trecho de SQL digitado de maneira incorreta.

Para resolver esse problema será necessário acessar o banco de dados da aplicação e executar o seguinte comando sql:

```roomsql
delete from flyway_schema_history where success = 0;
```

O comando anterior serve para apagar na tabela do Flyway todas as migrations cuja execução falhou. Após isso, basta corrigir o código da migration e executar novamente o projeto.

*Obs.: Pode acontecer de alguma migration ter criado uma tabela e/ou colunas e com isso o problema vai persistir, pois o flyway não vai apagar as tabelas/colunas criadas em migrations que falharam. Nesse caso você pode apagar o banco de dados e criá-lo novamente:*

```roomsql
drop database vollmed_api;
create database vollmed_api;
```
