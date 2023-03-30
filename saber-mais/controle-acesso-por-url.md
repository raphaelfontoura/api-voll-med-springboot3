## Para saber mais: controle de acesso por url

Na aplicação utilizada no curso não teremos perfis de acessos distintos para os usuários. Entretanto, esse recurso é utilizado em algumas aplicações e podemos indicar ao _Spring Security_ que determinadas URLs somente podem ser acessadas por usuários que possuem um perfil específico.

Por exemplo, suponha que em nossa aplicação tenhamos um perfil de acesso chamado de **ADMIN**, sendo que somente usuários com esse perfil possam excluir médicos e pacientes. Podemos indicar ao _Spring Security_ tal configuração alterando o método `securityFilterChain`, na classe `SecurityConfigurations`, da seguinte maneira:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/login").permitAll()
        .requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}
```

Repare que no código anterior foram adicionadas duas linhas, indicando ao _Spring Security_ que as requisições do tipo `DELETE` para as URLs `/medicos` e `/pacientes` somente podem ser executadas por usuários autenticados e cujo perfil de acesso seja **ADMIN**.