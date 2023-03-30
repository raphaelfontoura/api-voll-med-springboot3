## Para saber mais: controle de acesso por anotações

Outra maneira de restringir o acesso a determinadas funcionalidades, com base no perfil dos usuários, é com a utilização de um recurso do Spring Security conhecido como **Method Security**, que funciona com a utilização de anotações em métodos:

```java
@GetMapping("/{id}")
@Secured("ROLE_ADMIN")
public ResponseEntity detalhar(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
}
```

No exemplo de código anterior o método foi anotado com `@Secured("ROLE_ADMIN")`, para que apenas usuários com o perfil **ADMIN** possam disparar requisições para detalhar um médico. A anotação `@Secured` pode ser adicionada em métodos individuais ou mesmo na classe, que seria o equivalente a adicioná-la em **todos** os métodos.

**Atenção!** Por padrão esse recurso vem desabilitado no spring Security, sendo que para o utilizar devemos adicionar a seguinte anotação na classe `Securityconfigurations` do projeto:

```java
@EnableMethodSecurity(securedEnabled = true)
```

Você pode conhecer mais detalhes sobre o recurso de method security na documentação do Spring Security, disponível em: https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html