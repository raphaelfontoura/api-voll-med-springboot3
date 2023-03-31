## Para saber mais: Service Pattern

O **Padrão Service** é muito utilizado na programação e seu nome é muito comentado. Mas apesar de ser um nome único, _Service_ pode ser interpretado de várias maneiras: pode ser um _**Use Case**_ (_Application Service_); um **_Domain Service_**, que possui regras do seu domínio; um **_Infrastructure Service_**, que usa algum pacote externo para realizar tarefas; etc.

Apesar da interpretação ocorrer de várias formas, a ideia por trás do padrão é separar as regras de negócio, as regras da aplicação e as regras de apresentação para que elas possam ser facilmente testadas e reutilizadas em outras partes do sistema.

Existem duas formas mais utilizadas para criar Services. Você pode criar Services mais genéricos, responsáveis por todas as atribuições de um Controller; ou ser ainda mais específico, aplicando assim o **S** do **SOLID**: _Single Responsibility Principle_ (Princípio da Responsabilidade Única). Esse princípio nos diz que uma classe/função/arquivo deve ter apenas uma única responsabilidade.

Pense em um sistema de vendas, no qual provavelmente teríamos algumas funções como: _Cadastrar usuário_, _Efetuar login_, _Buscar produtos_, _Buscar produto por nome_, etc. Logo, poderíamos criar os seguintes Services: `CadastroDeUsuarioService`, `EfetuaLoginService`, `BuscaDeProdutosService`, etc.

Mas é importante ficarmos atentos, pois muitas vezes não é necessário criar um Service e, consequentemente, adicionar mais uma camada e complexidade desnecessária à nossa aplicação. Uma regra que podemos utilizar é a seguinte: se não houverem regras de negócio, podemos simplesmente realizar a comunicação direta entre os controllers e os repositories da aplicação.