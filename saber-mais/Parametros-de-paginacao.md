## Para saber mais: parâmetros de paginação

Conforme aprendemos nos vídeos anteriores, por padrão, os parâmetros utilizados para realizar a paginação e a ordenação devem se chamar `page`, `size` e `sort`. Entretanto, o Spring Boot permite que os nomes de tais parâmetros sejam modificados via configuração no arquivo _application.properties_.

Por exemplo, poderíamos traduzir para português os nomes desses parâmetros com as seguintes propriedades:

```properties
spring.data.web.pageable.page-parameter=pagina
spring.data.web.pageable.size-parameter=tamanho
spring.data.web.sort.sort-parameter=ordem
```

Com isso, nas requisições que utilizam paginação, devemos utilizar esses nomes que foram definidos. Por exemplo, para listar os médicos de nossa API trazendo apenas 5 registros da página 2, ordenados pelo e-mail e de maneira decrescente, a URL da requisição deve ser:

```
http://localhost:8080/medicos?tamanho=5&pagina=1&ordem=email,desc
```
