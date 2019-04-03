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

<h3>Primeira aplicação com Spring</h3>
<ul>
    <li>Escolha uma pasta para o projeto</li>
    <li>No prompt de comando digite:</li>
<ul>

```
mvn archetype:generate -DgroupId=br.gov.sp.fatec -DartifactId=springExample1 -
DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

```
Entre na pasta criada (springExample1) edite o arquivo pom.xml,incluindo as dependências básicas do Spring:

```
<properties>
  <org.springframework.version>4.1.4.RELEASE</org.springframework.version>
</properties>
<dependencies>
<!-- Outras dependencias -->
<!-- Spring -->
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-context</artifactId>
<version>${org.springframework.version}</version>
</dependency>
</dependencies>

```
Baixe as dependências e compile o projeto:

```
mvn clean package
```
Logo em seguida, importe o projeto como projeto Maven:
• File > Import... > Maven > Existing Maven Projects.

Crie um arquivo applicationContext.xml em src\main\resources:

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.1.xsd">
<bean id="placa"
class="br.gov.sp.fatec.Placa">
<property name="mensagem" ref="mensagem"/>
</bean>
<bean id="mensagem"
class="br.gov.sp.fatec.HelloWorld">
<property name="nome" value="Newcomer"/>
</bean>
</beans>
```
**Crie a pasta resource como "source folder" no Eclipse!**

Crie o arquivo para uma **interface** chamada Mensagem:

```
package br.gov.sp.fatec;
public interface Mensagem {
/**
* Uma mensagem
* @return Uma mensagem
*/
public String getMensagem();
}
```
Crie a **classe** HelloWorld:

```
package br.gov.sp.fatec;

public class HelloWorld implements Mensagem {

private String nome;

public HelloWorld() {
}

public void setNome(String nome) {
this.nome = nome;
}

@Override
public String getMensagem() {
return "Hello World! Hello " + nome + "!";
  }
}
```
Crie a **classe** Placa:

```
package br.gov.sp.fatec;

public class Placa {

private Mensagem mensagem;

public Placa() {
}

public String ler() {
return "[ <" + mensagem.getMensagem() + "> ]";
}

public void setMensagem(Mensagem mensagem) {
this.mensagem = mensagem;
  }
}
```
Edite a classe principal App:

```
package br.gov.sp.fatec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App{

public static void main(String[] args) {

//O seguinte trecho é responsável por ler o arquivo XML e gerar os beans de acordo com o configurado:
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//Para se utilizar um bean, basta solicitá-lo pelo nome:
Placa obj = (Placa) context.getBean("placa");

System.out.println(obj.ler());
context.close();
  }
}
```
Após arrumar a classe principal basta executar o projeto.

Por padrão todo bean definido possui o escopo do tipo **singleton**, ou seja, todos que requisitam o bean recebem a mesma instância.

**Qualquer alteração em um bean será vista por todos que o utilizarem!**

Vamos agora criar um bean com escopo diferente (prototype) que cria uma nova instância a cada requisição e ver a diferença.
acrescente o seguinte bean ao arquivo applicationContext.xml:

```
<bean id="mensagem2"
  class="br.gov.sp.fatec.HelloWorld" scope="prototype" >
  <property name="nome" value="Ze Ninguem"/>
</bean>
```
Altere a classe principal para:

```
public static void main(String[] args) {
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

Placa obj = (Placa) context.getBean("placa");
System.out.println(obj.ler());

HelloWorld msg1 = (HelloWorld) context.getBean("mensagem");
System.out.println(msg1.getMensagem());
msg1.setNome("Teste");

HelloWorld msg2 = (HelloWorld) context.getBean("mensagem");
System.out.println(msg2.getMensagem());

HelloWorld msg3 = (HelloWorld) context.getBean("mensagem2");
System.out.println(msg3.getMensagem());
msg3.setNome("Teste");

HelloWorld msg4 = (HelloWorld) context.getBean("mensagem2");
System.out.println(msg4.getMensagem());

context.close();
}
```
A saída do programa será:

```
[ <Hello World! Hello Newcomer!> ]
Hello World! Hello Newcomer!
Hello World! Hello Teste!
Hello World! Hello Ze Ninguem!
Hello World! Hello Ze Ninguem!
```
Isso ocorre porque msg1 e msg2 na verdade referenciam a mesma instância da classe HelloWorld, enquanto msg3 e msg4 possuem instâncias diferentes. Tudo isso por conta do escopo definido!

<h3>Spring com anotações</h3>

Para habilitar anotações é preciso incluir a seguinte tag no arquivo de configuração do Spring (applicationContext.xml).

```
<context:component-scan base-package="br.gov.sp.fatec"/>
```
**No parâmetro "base-package" é preciso configurar o package a partir do qual se encontram as classes com anotações aceitas pelo Spring. Esse parâmetro é opcional e, caso não seja informado, todas as classes do projeto serão verificadas**

Abaixo um exemplo de classe anotada:

```
@Component("placaMensagem")
public class Placa {

@Autowired
@Qualifier("mensagem1")
private Mensagem mensagem;
```
A anotação **@Component** indica ao Spring que um bean deve ser criado para a classe anotada (no exemplo, Placa), opcionalmente, pode ser informado um id para o bean criado (no exemplo, placaMensagem). A desvantagem de anotar a classe diretamente reside no fato de
que apenas um bean pode ser criado dessa forma.

A anotação **@Autowired** vale somente para o atributo **"mensagem"** e serve para carregá-lo com um bean do tipo Mensagem.
A anotação opcional **@Qualifier** identifica qual o id do bean a utilizar.

<h3>Spring sem XML</h3>

É possível substituir o arquivo de configuração XML (applicationContext.xml) por uma classe anotada:

```
@Configuration
@ComponentScan(value={"br.gov.sp.fatec"})
public class DIConfig {

@Bean(name={"mensagem1"}) //Nao e necessario especificar um nome
public Mensagem getMensagem() {
HelloWorld mensagem = new HelloWorld();
mensagem.setNome("Newcomer");
return mensagem;
}

@Bean(name={"mensagem2"}) //Nao e necessario especificar um nome
@Scope("prototype")
public Mensagem getMensagem2() {
HelloWorld mensagem = new HelloWorld();
mensagem.setNome("Ze Ninguem");
return mensagem;
  }
}
```
A anotação **@Configuration** indica que a classe possui configurações do Spring. Habilita o uso das anotações **@Bean**, **@Scope** e **@ComponentScan**. A anotação **@Bean** é utilizada para definir um bean, de forma similar à tag XML "bean"

<ul>
  <li>Ao contrário da configuração XML, é preciso instanciar a classe desejada e "setar" seus atributos manualmente
  Opcionalmente, pode-se nomear o bean
</li>
  
  <li>
    A anotação @Bean permite a criação de inúmeros beans de uma mesma classe (o que não seria possível com a anotação de classe      @Component)
  </li>

  <li>
    A anotação **@Scope** é utilizada para definir o escopo de um bean, de forma similar ao parâmetro "scope" da tag XML "bean"
  </li>

  <li>
  A anotação **@ComponentScan** é similar à tag XML "component-scan". No parâmetro "value" é preciso configurar o package a partir do qual se encontram as classes com anotações aceitas pelo Spring. Esse parâmetro é opcional e, caso não seja informado, todas as classes do projeto serão verificadas.
  </li>
</ul>

Para utilizar a classe de configuração em vez do arquivo XML é preciso alterar o programa principal de:

```
ClassPathXmlApplicationContext context =
new ClassPathXmlApplicationContext("applicationContext.xml");
```
para

```
AnnotationConfigApplicationContext context =
new AnnotationConfigApplicationContext(DIConfig.class);
```
A classe responsável por interpretar uma classe é diferente da utilizada para interpretar um arquivo XML, mas a utilização é idêntica.

<h1>Trabalho 1</h1>

Definir 2 beans utilizando o Spring Framework. Cada bean deve possuir, no mínimo, 4 atributos, dentre os quais 1 texto e 1 numérico. Deve ocorrer injeção de dependências por referência em um dos beans. Use a forma que preferir (xml, anotações ou misto).



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

<h3>@Transactional</h3>

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

