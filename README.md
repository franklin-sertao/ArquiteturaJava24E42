# Franklin - Sistema de Gerenciamento de Ingredientes e Produtos

## Visão Geral
Este projeto faz parte da disciplina de **Arquitetura Java**. É um sistema de gerenciamento de ingredientes, receitas e produtos desenvolvido com **Spring Boot** e **Thymeleaf**, com o principal objetivo de precificar produtos de confeitaria. 

Destinado a empresas do setor alimentício, o sistema permite a criação, edição e listagem de ingredientes, a associação desses ingredientes a receitas e produtos, além de gerenciar as embalagens utilizadas nos produtos finais. O usuário poderá cadastrar produtos, embalagens, receitas e ingredientes, além de visualizar os dados cadastrados.

![Tela inicial do sistema](/assets/img/tela_inicial.jpg)

## Funcionalidades

- **Gerenciamento de Ingredientes**: Criação, edição, visualização e exclusão de ingredientes categorizados como secos, líquidos ou unitários.
- **Gerenciamento de Receitas**: Definição de receitas com seus respectivos ingredientes e modos de preparo.
- **Gerenciamento de Produtos**: Associação de receitas a produtos finais, com definição de embalagens.
- **Gerenciamento de Embalagens**: Cadastro e gerenciamento das embalagens disponíveis.
- **Pesquisa por Código de Barras**: Busca de ingredientes por código de barras.
- **Interface Intuitiva**: Interface web amigável desenvolvida com Thymeleaf.
- **Validação de Dados**: Validações tanto no frontend quanto no backend para garantir a integridade dos dados.
- **Carga de Dados Inicial**: Loader para popular o banco de dados com dados a partir de arquivos `.txt`.

## Requisitos de Configuração

- **Java Development Kit (JDK) 17** ou superior
- **Maven 3.x**
- **Git** *(para clonar o repositório)*
- **Banco de Dados**: H2 (em memória) ou outro compatível com JPA (como MySQL, PostgreSQL)

## Instalação

1. **Clone o Repositório**

    ```bash
    git clone https://github.com/seu-usuario/franklin.git
    cd franklin
    ```

2. **Configuração do Banco de Dados**

    O projeto está configurado para utilizar o banco de dados H2 por padrão. Se desejar usar outro banco de dados, atualize as configurações em `src/main/resources/application.properties`.

    **Exemplo para MySQL:**

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/franklin_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    ```

	Obtenha a [Chave de API Cosmos](https://cosmos.bluesoft.com.br/api-pricings) e atualize as configurações também em  `src/main/resources/application.properties`. A API é de utilização gratuita para até 25 requisições por dia:

	```properties
	cosmos.api.token=sua_chave_de_api
	```
	

3. **Compilar o Projeto**

    ```bash
    mvn clean install
    ```
## Execução

1. **Executar a Aplicação**

    ```bash
    mvn spring-boot:run
    ```

    Ou, após compilar, executar o JAR:

    ```bash
    java -jar target/franklin-0.0.1-SNAPSHOT.jar
    ```

2. **Acessar a Aplicação**

    Abra o navegador e acesse: [http://localhost:8080](http://localhost:8080)

## Carga de Dados Inicial

Os dados iniciais são carregados a partir de arquivos `.txt` localizados em `src/main/resources/data/`. Certifique-se de que os arquivos estejam formatados corretamente e contenham as informações necessárias.

- **ingredientes.txt**
- **receitas.txt**
- **produtos.txt**
- **embalagens.txt**

Os **Loaders** correspondentes (`IngredienteLoader`, `ReceitaLoader`, etc.) leem esses arquivos e populam o banco de dados ao iniciar a aplicação.


1. **Fork o Repositório**
2. **Crie uma Branch para sua Feature**
    ```bash
    git checkout -b feature/nova-feature
    ```
3. **Commit suas Alterações**
    ```bash
    git commit -m "Adiciona nova feature"
    ```
4. **Push para a Branch**
    ```bash
    git push origin feature/nova-feature
    ```
5. **Abra um Pull Request**

> [!NOTE]
> Para mais informações sobre o uso do sistema e seu detalhamento técnico, consulte o arquivo [`funcionamento.md`](/funcionamento.md).

## Licença

Este projeto está licenciado sob a [GNU General Public License v3.0](LICENSE).

## Contato

Para dúvidas ou sugestões, entre em contato:

- **LinkedIn**: [Meu Perfil](https://www.linkedin.com/in/franklin-v%C3%A9ras-sert%C3%A3o)
- **GitHub**: [Meu Usuário](https://github.com/franklinveras)

---