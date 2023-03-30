## Para saber mais: Filters

_Filter_ é um dos recursos que fazem parte da especificação de Servlets, a qual padroniza o tratamento de requisições e respostas em aplicações Web no Java. Ou seja, tal recurso não é específico do Spring, podendo assim ser utilizado em qualquer aplicação Java.

É um recurso muito útil para isolar códigos de infraestrutura da aplicação, como, por exemplo, segurança, logs e auditoria, para que tais códigos não sejam duplicados e misturados aos códigos relacionados às regras de negócio da aplicação.

Para criar um _Filter_, basta criar uma classe e implementar nela a interface `Filter` (pacote jakarta.servlet). Por exemplo:

```java
@WebFilter(urlPatterns = "/api/**")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Requisição recebida em: " + LocalDateTime.now());
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
```

O método `doFilter` é chamado pelo servidor automaticamente, sempre que esse _filter_ tiver que ser executado, e a chamada ao método `filterChain.doFilter` indica que os próximos _filters_, caso existam outros, podem ser executados. A anotação `@WebFilter`, adicionada na classe, indica ao servidor em quais requisições esse _filter_ deve ser chamado, baseando-se na URL da requisição.

No curso, utilizaremos outra maneira de implementar um _filter_, usando recursos do Spring que facilitam sua implementação.