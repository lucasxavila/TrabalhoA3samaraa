
# Sistema de Gestão de Contas Bancárias

## Descrição do Projeto

Este projeto é um sistema simples de gestão de contas bancárias, desenvolvido como parte da avaliação da disciplina de Gestão e Qualidade de Software. A aplicação, executada via console, permite o cadastro de clientes (Pessoa Física e Jurídica), a criação de diferentes tipos de contas (Corrente e Poupança) e a realização de operações bancárias básicas, como depósito e saque.

O desenvolvimento seguiu as melhores práticas de Orientação a Objetos, encapsulamento, e foi validado através de testes unitários e funcionais para garantir a qualidade e a corretude da lógica de negócio.

## Autores

1. Lucas Guilherme Ávila Barreto 	 |	 1232021323,
2. Gabriel Sousa Bastos		         |	 123115047,
3. Daniel De Moura Renda 		     |	 124115549,
4. Gabriela Falcão Penna Campos      |   12311964,
5. Yago Marquezini Magalhães 	     |   12319847

## Diagrama de Classes (UML)

O sistema foi modelado com base no seguinte diagrama de classes, que define a estrutura e o relacionamento entre as entidades.

[Diagrama UML do Sistema Bancário] ![image](https://github.com/user-attachments/assets/deb1bf02-2870-4c5d-b4f0-4ea6d8d4396b)

## Tecnologias Utilizadas

* **Java 17:** Linguagem de programação principal.
* **JUnit 5:** Framework para a criação e execução de testes unitários e funcionais.
* **Maven:** Ferramenta para gerenciamento de dependências e automação do build do projeto.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

* [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/) - Versão 17 ou superior.
* [Apache Maven](https://maven.apache.org/download.cgi) - Versão 3.8 ou superior.
* Uma IDE de sua preferência (recomendado: [VS Code](https://code.visualstudio.com/) com o Extension Pack for Java ou [IntelliJ IDEA](https://www.jetbrains.com/idea/)).

## Como Compilar e Executar o Projeto

### Via Maven (Método Recomendado)

1.  Clone ou baixe este repositório.
2.  Abra um terminal na pasta raiz do projeto (onde o arquivo `pom.xml` está localizado).
3.  **Para compilar o projeto:**
    ```bash
    mvn clean compile
    ```
4.  **Para executar a aplicação principal:**
    ```bash
    mvn exec:java -Dexec.mainClass="com.banco.Aula"
    ```

### Via Terminal (Manual)

1.  Navegue até a pasta raiz do projeto.
2.  **Compile todos os arquivos `.java`**, colocando os `.class` em uma pasta de saída (ex: `bin`):
    ```bash
    javac -d bin src/main/java/com/banco/*.java
    ```
3.  **Execute a aplicação**, especificando o classpath para a pasta `bin`:
    ```bash
    java -cp bin com.banco.Aula
    ```

## Como Executar os Testes

Os testes foram criados para garantir a qualidade do código e a corretude das funcionalidades.

### Via Maven

1.  Este é o comando padrão para rodar todos os testes (unitários e funcionais) do projeto:
    ```bash
    mvn test
    ```
