## Para saber mais: hashing de senha

Ao implementar uma funcionalidade de autenticação em uma aplicação, independente da linguagem de programação utilizada, você terá que lidar com os dados de login e senha dos usuários, sendo que eles precisarão ser armazenados em algum local, como, por exemplo, um banco de dados.

**Senhas são informações sensíveis e não devem ser armazenadas em texto aberto**, pois se uma pessoa mal intencionada conseguir obter acesso ao banco de dados, ela conseguirá ter acesso às senhas de todos os usuários. Para evitar esse problema, você deve sempre utilizar algum algoritmo de **_hashing_** nas senhas antes de armazená-las no banco de dados.

_Hashing_ nada mais é do que uma _função matemática_ que converte um texto em outro texto totalmente diferente e de difícil dedução. Por exemplo, o texto _Meu nome é Rodrigo_ pode ser convertido para o texto _8132f7cb860e9ce4c1d9062d2a5d1848_, utilizando o algoritmo de _hashing MD5_.

Um detalhe importante é que os algoritmos de _hashing_ devem ser de _mão única_, ou seja, não deve ser possível obter o texto original a partir de um _hash_. Dessa forma, para saber se um usuário digitou a senha correta ao tentar se autenticar em uma aplicação, devemos pegar a senha que foi digitada por ele e gerar o _hash_ dela, para então realizar a comparação com o hash que está armazenado no banco de dados.

Existem diversos algoritmos de _hashing_ que podem ser utilizados para fazer essa transformação nas senhas dos usuários, sendo que alguns são mais antigos e não mais considerados seguros hoje em dia, como o MD5 e o SHA1. Os principais algoritmos recomendados atualmente são:

- **Bcrypt**
- **Scrypt**
- **Argon2**
- **PBKDF2**

Ao longo do curso utilizaremos o algoritmo BCrypt, que é bastante popular atualmente. Essa opção também leva em consideração o fato de que o _Spring Security_ já nos fornece uma classe que o implementa.