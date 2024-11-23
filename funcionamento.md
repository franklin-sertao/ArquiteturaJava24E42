# Uso do sistema de precificação de produtos para confeitaria

## Como gerenciar meus ingredientes?

- **Listar Ingredientes**: Acesse `/ingredientes` para ver a lista de todos os ingredientes.
- **Adicionar Novo Ingrediente**: Clique em "+ Novo Ingrediente" e preencha o formulário.
- **Buscar Ingredientes**: Utilize a barra de busca para obter informações sobre o produto pelo Código de Barras. Alguns produtos do cadastro tem informações adicionais, como o preço médio de mercado, o peso, volume ou outras informações relevantes.
- **Editar Ingrediente**: Na lista de ingredientes, clique em "Editar" para modificar um ingrediente existente.
- **Excluir Ingrediente**: Na lista, clique em "Excluir" para remover um ingrediente.


<p align="center">
	<a href="/assets/img/ingredientes_lista.jpg">
		<img src="/assets/img/ingredientes_lista.jpg" alt="Tela da listagem de ingredientes" height=300px />
	</a>
	<a href="/assets/img/ingrediente_form.jpg">
		<img src="/assets/img/ingrediente_form.jpg" alt="Tela do formulário de cadastro de ingredientes" height=300px />
	</a>
</p>


## Como gerenciar minhas receitas?

- **Listar Receitas**: Acesse `/receitas` para ver todas as receitas.
- **Adicionar/Editar/Excluir**: Similar ao gerenciamento de ingredientes.

<p align="center">
	<a href="/assets/img/receitas_lista.jpg">
		<img src="/assets/img/receitas_lista.jpg" alt="Tela da listagem de receitas" height=300px />
	</a>
	<a href="/assets/img/receita_form.jpg">
		<img src="/assets/img/receita_form.jpg" alt="Tela do formulário de cadastro de receitas" height=300px />
	</a>
</p>

## Como gerenciar minhas embalagens?

- **Listar Embalagens**: Acesse `/embalagens`.
- **Adicionar/Editar/Excluir**: Gerencie as opções de embalagens disponíveis.

<p align="center">
	<a href="/assets/img/embalagens_lista.jpg">
		<img src="/assets/img/embalagens_lista.jpg" alt="Tela da listagem de embalagens" height=300px />
	</a>
	<a href="/assets/img/embalagem_form.jpg">
		<img src="/assets/img/embalagem_form.jpg" alt="Tela do formulário de cadastro de embalagens" height=300px />
	</a>
</p>

## Como gerenciar meus produtos?

- **Listar Produtos**: Acesse `/produtos`.
- **Adicionar/Editar/Excluir**: Gerencie os produtos e suas associações com receitas e embalagens.

<p align="center">
	<a href="/assets/img/produtos_lista.jpg">
		<img src="/assets/img/produtos_lista.jpg" alt="Tela da listagem de produtos" height=300px />
	</a>
	<a href="/assets/img/produto_form.jpg">
		<img src="/assets/img/produto_form.jpg" alt="Tela do formulário de cadastro de produtos" height=300px />
	</a>
</p>


# Detalhamento técnico

## Definições

### Produto
Produto final, após todas as etapas de processamento. Pode conter:
- Receitas (que podem ter formas de preparo diferentes) e suas quantidades
- Embalagens (pode ser uma ou mais, por exemplo quanto se faz kits) e suas quantidades
- Ingredientes (podem ser acrescentados na finalização do produto) e suas quantidades


### Embalagens
Insumos destinados ao transporte e à conservação do produto final, por exemplo, uma Caixa display de acetato 20x20x10cm ou um papel celofane transparente para embalar fatias de bolo.
Embalagens geralmente não são compradas sozinhas, mas em pacotes fechados com muitas unidades.

>***OBS: O preços das embalagens será calculado pela divisão do preço do pacote, pela quantidade de unidades no pacote***


### Receita
Etapa intermediária do processo de confecção do produto. Nesta etapa, as partes que irão compor o produto são descritas separadamente, por exemplo, em um bolo temos pelo menos três receitas:
1. Massa de bolo
2. Recheio
3. Cobertura

Cada um desses itens pode ser considerado uma receita diferente, pois tem ingredientes e modos de preparo diferentes. Porém todos irão compor o produto "Bolo" que, por fim, terá sua embalagem definida para venda ao consumidor final


### Ingrediente
Insumos para produção de receitas e finalização de produtos. 
No nosso sistema, os ingredientes podem ser de três tipos:

- ### Ingrediente Seco
	Ingrediente que tem sua medida definida por peso (em gramas).
		
	>**OBS: O preço por unidade será definido dividindo o preço do ingrediente por seu peso líquido**
	>
	>ex: se 1kg (1000g) de farinha de trigo custa R$ 5,00, então o preço por grama será R$ 0,005

- ### Ingrediente Líquido
	Ingrediente que tem sua medida definida por volume (em mililitros).

	>**OBS: O preço por unidade será definido dividindo o preço do ingrediente por seu volume líquido**
	>
	>ex: se 1L (1000ml) de leite custa R$ 6,00, então o preço por mililitro será R$ 0,006

- ### Ingrediente Unitário
	Ingrediente que tem sua medida definida por unidade.

	>**OBS: O preço por unidade será definido dividindo o preço do lote por pela quandidade de unidades**
	>
	>ex: se uma dúzia de ovos custa R$ 10,00, então o preço por unidade será R$ 0,83


## Diagrama de Classes

No diagrama de classes abaixo, é possível visualizar a estrutura do projeto e as relações entre as entidades.
o diagrama foi gerado com a ferramenta PlantUML e está disponível em [`/assets/src/diagrama_classes.puml`](/assets/diagrama_classes.puml):

![Diagrama de Classes](http://www.plantuml.com/plantuml/proxy?src=https://github.com/franklin-sertao/ArquiteturaJava24E42/main/assets/diagrama_classes.puml)

## Tecnologias Utilizadas
Como parte do conhecimento adquirido no curso de Arquitetura Java, no Instituto Infnet, foram utilizadas as seguintjson tecnologias:

- **Java 17** (ou superior)
- **Spring Boot 3.x**
- **Thymeleaf** 
- **Spring Data JPA**
- **Hibernate**
- **Banco de Dados H2** *(configurável)*
- **Maven** para gerenciamento de dependências
- **Tailwind CSS** para estilização

## Estrutura do Projeto
O projeto foi estruturado da seguinte forma:

- `src/main/java/br/edu/infnet/franklin/`: Código fonte da aplicação.
    - `controller/`: Controladores Spring MVC.
    - `model/domain/`: Entidades JPA.
    - `model/dto/`: DTOs para formulários.
    - `repository/`: Repositórios JPA.
    - `service/`: Serviços de negócio.
- `src/main/resources/`: Recursos da aplicação.
    - `templates/`: Templates Thymeleaf.
    - `static/`: Recursos estáticos (CSS, JS, imagens).
    - `data/`: Arquivos `.txt` para carga de dados.
    - `application.properties`: Configurações da aplicação.
- `src/test/java/br/edu/infnet/franklin/`: Testes unitários e de integração.

# Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.
