## Para saber mais: build com arquivo .war

Projetos que utilizam o Spring Boot geralmente utilizam o formato **jar** para o empacotamento da aplicação, conforme foi demonstrado ao longo desta aula. Entretanto, o Spring Boot fornece suporte para o empacotamento da aplicação via formato **war**, que era bastante utilizado em aplicações Java antigamente.

Caso você queira que o build do projeto empacote a aplicação em um arquivo no formato war, vai precisar realizar as seguintes alterações:

1) Adicionar a tag `<packaging>war</packaging>` no arquivo `pom.xml` do projeto, devendo essa tag ser filha da tag raiz `<project>`:

```properties
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>med.voll</groupId>
    <artifactId>api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>api</name>

    <packaging>war</packaging>
```

2) Ainda no arquivo `pom.xml`, adicionar a seguinte dependência:

```properties
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-tomcat</artifactId>
  <scope>provided</scope>
</dependency>
```

3) Alterar a classe main do projeto (`ApiApplication`) para herdar da classe `SpringBootServletInitializer`, bem como sobrescrever o método `configure`:

```java
@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
```

Pronto! Agora, ao realizar o build do projeto, será gerado um arquivo com a extensão `.war` dentro do diretório `target`, ao invés do arquivo com a extensão `.jar`.
