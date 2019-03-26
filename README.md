# JavaSpring

O Spring é um framework Java criado com o objetivo de facilitar o desenvolvimento de aplicações, explorando, para isso, os conceitos de **Inversão de Controle** e **Injeção de Dependências**. Dessa forma, ao adotá-lo, temos à nossa disposição uma tecnologia que nos fornece não apenas recursos necessários à grande parte das aplicações, como módulos para persistência de dados, integração, segurança, testes, desenvolvimento web, como também um conceito a seguir que nos permite criar soluções menos acopladas, mais coesas e, consequentemente, mais fáceis de compreender e manter.

<h3>Injeção de Dependencias</h3>

A injeção de dependência (Dependency Injection – DI) é um padrão de projeto que permite especificar a dependência externamente. A implementação a ser utilizada pode ser facilmente alterada, sem necessidade de reescrever a lógica de Placa.

```
A injeção de dependência pode ser de 3 tipos: metodos setter, constructor e na interface java.
```

<b>Para mais informações e melhor entendimento, consulte o site: </b>

https://blog.algaworks.com/injecao-de-dependencias-com-spring/

<h3>Inversão de controle</h3>

A inversão de controle é um padrão de projeto onde a sequência (controle) de chamadas dos métodos não é determinada diretamente pelo programador. Este controle é delegado a uma infraestrutura de software, muitas vezes chamada de container, ou a qualquer outro componente que possa tomar controle sobre a execução.

```
Basicamente, ao invés do código chamar o framework, o framework chama o código;

Injeção de dependências é um tipo de inversão de controle;

Um framework controla as instâncias de classes, permitindo uma programação para interfaces, sem dependências com implementações;

```
<b>Para mais informações e melhor entendimento, consulte o site: </b>

http://www.linhadecodigo.com.br/artigo/3418/inversao-de-controle-ioc-e-injecao-de-dependencia-di-diferencas.aspx

<h1>Spring Boot</h1>

O Spring Boot é um projeto que visa facilitar o desenvolvimento, configuração e publicação de aplicações que façam uso dos frameworks da família Spring.

>Convenção sobre configuração – O Spring Boot trabalha com configurações padrões. Apenas particularidades do projeto precisam ser configuradas

>Starters – Dependências que incluem diversas dependências. Exemplo: Basta incluir uma única dependência "spring-boot-starterdata-jpa" em vez de "spring-data-jpa", "spring-orm", "hibernatecore", etc

>Servidor web Java embutido – Não é necessário instalar um servidor em separado e realizar o deploy da aplicação Java desenvolvida. Ao executar a aplicação, o Spring Boot cuida de subir um servidor Tomcat (padrão) ou Jetty

>Spring Initializr – É possível montar um projeto Spring Boot para Maven ou Gradle pelo site https://start.spring.io/

>Sem XML – O Spring Boot aboliu o uso de arquivos XML para configuração

Para executar a aplicação Spring Boot a partir do Maven basta utilizar o comando: **mvn spring-boot:run**

>Caso a aplicação seja web, o servidor configurado (Tomcat por padrão) será iniciado e aplicação responderá na URL e porta configurados

O Spring Boot utiliza um arquivo de configuração "application.properties", algumas dependências extras devem ser especificadas **(conector do mysql por exemplo).**


<h1>Spring data JPA</h1>

O Spring Data JPA é um framework da família Spring que nos auxilia na tarefa de persistir dados com JPA, eliminando a necessidade de escrever código para persistência de dados ou realização de consultas.
Para configurar o spring data JPA, é nescessario incluir algumas dependencias no arquivo **pom.xml**:

>spring-boot-starter-parent – Dependência base do Spring Boot;

>spring-boot-starter-data-jpa – Starter do Spring Data JPA

É nescessario incluir algumas dependencias no arquivo **application.properties** no projeto:

```
## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url = jdbc:mysql://localhost:3306/estoque?useTimezone=true&serverTimezone=UTC
spring.datasource.username = root
spring.datasource.password = adm1n
```
<h3>Repository</h3>

Um repositório realiza as operações de persistência de dados sobre uma classe persistida associada, assim como um DAO, mas também pode oferecer consultas diversas. Para se criar um repositório utilizando o Spring Data JPA, basta criar uma interface que estende uma de suas interfaces:

```
Repository – Repositório geral, que não disponibiliza nenhum
método. A interface que o estender deve especificar os métodos que
deseja utilizar;

CrudRepository – Repositório que disponibiliza as funcionalidades
CRUD (Create, Read, Update and Delete). A interface que o estender
recebe automaticamente todas essas funcionalidades;

```
**Não é necessário criar implementações para essas interfaces!
Podemos utilizá-las diretamente!**

**Existe uma interface PagingAndSortingRepository, que estende
CrudRepository, com métodos adicionais de paginação e ordenação**

**Existe também uma interface JpaRepository, que estende
PagingAndSortingRepository com métodos de flush (atualização
forçada do banco de dados) e exclusão em bloco**

<h3>Query methods</h3>

Repositórios também podem conter consultas diversas sobre a classe persistida associada. A maneira mais simples de se realizar uma consulta consiste em utilizar **query methods**, pois basta declará-los na interface do repositório e o Spring Data JPA monta a consulta de acordo com seu nome:
<ul>
  <li>
    Na primeira palavra do método indicamos a operação (find, read,
    query e get fazem buscas e count conta os resultados);
  </li>

  <li>
    Entre a primeira palavra e o By podemos formatar o resultado
    (Distinct retira registros duplicados, Top1 traz o primeiro registro,
    Last5 traz os 5 últimos registros, etc)
    </li>
  <li>
    Depois do By colocamos as condições:
    Se não informamos operadores, subentende-se a operação de
    igualdade
    </li>
</ul>

```
Exemplos

Um método com o nome findByNome(String nome) retornaria todos
os usuários cujo atributo nome seja igual ao parâmetro;

Um método com o nome findTop1ByNomeContains(String nome)
retornaria o primeiro registro de usuário que contenha em qualquer
lugar de seu atributo nome o texto passado como parâmetro;

Um método com o nome findByIdGreaterThan(Long id) retornaria
todas os registros de usuário com atributo id maior que o parâmetro;

Existem várias opções e é possível inclusive acessar atributos
internos:

Um método com o nome findByAutorizacoesNome(String nome), por
exemplo, traria todos os usuários que possuissem, em seu atributo
autorizacoes (uma lista da classe persistida Autorizacao), uma
autorização com atributo nome igual ao parâmetro;

```
<h3>@query</h3>

A utilização de query methods é bastante prática, contudo consultas complexas fatalmente gerarão métodos com nomes gigantescos e incompreensíveis. Para esse tipo de consulta existe a anotação **@Query**, que permite a construção de consultas em JPQL (Java Persistence Query Language):

Exemplos:

Essa consulta busca todos os usuários que possuem o texto contido no parâmetro "nome" em qualquer lugar de seu atributo nome e é equivalente ao query method findByNomeContains(String nome)

```
@Query("select u from Usuario u where u.nome like %?1%")
public List<Usuario> buscaUsuario(String nome);
```
Essa consulta busca todos os usuários que possuem uma autorização
cujo atributo nome é idêntico ao texto contido no parâmetro "nome"
e é equivalente ao query method findByAutorizacoesNome(String
nome)

```
@Query("select u from Usuario u join u.autorizacoes a where a.nome = ?1")
public List<Usuario> buscaUsuarioPorNomeAutorizacao(String nome);
```

<h3>Transactional</h3>

O Spring realiza o controle de transações por meio da anotação **@Transactional**. Qualquer método anotado com essa anotação passa a
funcionar como uma transação: se ocorrer uma exceção, todas as operações no BD são desfeitas

```
@Transactional
public void exemploTransacao() {
 // Cria usuario
 Usuario usuario1 = new Usuario();
 usuario1.setNome("Pedro");
 usuario1.setSenha("senha");
 usuarioRepo.save(usuario1);
 // Gera erro, pois nome e senha sao obrigatorios
 Usuario usuario2 = new Usuario();
 usuarioRepo.save(usuario2);
}
```

  <h1>Trabalho 1</h1>

  <p>Definir 2 beans utilizando o Spring Framework. Cada bean deve possuir, no mínimo, 4
  atributos, dentre os quais 1 texto e 1 numérico. Deve ocorrer injeção de dependências
  por referência em um dos beans.
  Use a forma que preferir (xml, anotações ou misto).</p>
  
 <h1>Trabalho 2</h1>
<p>Crie uma aplicação com:</p>

<ul>
    <li>No mínimo, 2 entidades relacionadas (usar um relacionamento @OneToMany), 
  mapeadas  como  tabelas  (não  utilizar  as  do  exemplo).  Cada  entidade  deve 
  possuir, no mínimo, 5 atributos;</li>  

  <li>Um repositório  para  cada  entidade  criada  com  pelo  menos  2  consultas  cada 
um: uma com query method e outra com @Query;</li>

  <li>No  mínimo,  1  serviço  que  utilize as duas entidades e faça uso de transações. 
  Um  serviço  deve  receber  parâmetros  e/ou  retornar  dados.  Um  serviço  deve 
  conter as regras de negócio da aplicação;</li>

  <li>Todas as dependências injetadas utilizando Spring;</li>

   <li>Testes de unidade para 1 repositório e 1 se
  rviço. No mínimo, dois testes para o 
  repositório e dois testes para o serviço.</li>

</ul>

