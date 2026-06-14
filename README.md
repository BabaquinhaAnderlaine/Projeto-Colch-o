

# 🛏️ Sistema de Concessão de Colchão Novo

> **Status do Projeto:** 🚀 Concluído / Funcional

Uma aplicação desktop desenvolvida em **Java (Swing)** criada para automatizar e simplificar o processo de verificação de elegibilidade para o programa social *"Ganhe um Colchão Novo"*.

---

## 🎯 Proposta do Sistema

O objetivo principal do software é avaliar instantaneamente se um solicitante tem direito ao benefício do programa. O sistema conta com uma interface gráfica amigável que recebe os dados do usuário, valida as informações e aplica a regra de negócio central.

### 📋 Critério de Elegibilidade

Para ter direito ao benefício, o candidato deve atender ao seguinte requisito:

* **Renda Mensal:** Deve ser **menor ou igual a R$ 1.621,00**.

---

## 🛠️ Requisitos do Sistema

### 1. Requisitos Funcionais (RF)

| ID | Requisito | Descrição |
| --- | --- | --- |
| **RF-01** | **Cadastro de Candidato** | Coleta e armazenamento temporário de: Nome completo, CPF, Telefone e Renda mensal. |
| **RF-02** | **Validação de Elegibilidade** | Comparação automática da renda com o teto de **R$ 1.621,00**. |
| **RF-03** | **Interface Gráfica (GUI)** | Tela interativa construída em **Java Swing** com campos de entrada e retorno visual claro. |
| **RF-04** | **Tratamento de Erros** | Validação de campos vazios, CPFs/Telefones mal formatados e rendas negativas/não numéricas. |

### 2. Regras de Negócio & Arquitetura (RN)

* **OOP (Herança):** A classe `Candidato` estende uma classe base abstrata `Pessoa`, aplicando conceitos sólidos de Orientação a Objetos.
* **Persistência:** O sistema **não utiliza banco de dados externo**, mantendo os dados em memória durante a execução (conforme a especificação do projeto).

---

## 🔬 Validações Implementadas

O sistema possui um motor de validação rigoroso para garantir a consistência dos dados:

* **CPF:** Aceita apenas números e exige exatamente 11 dígitos.
* **Telefone:** Aceita apenas números (formatos de 10 ou 11 dígitos).
* **Renda:** Validação para garantir valores estritamente numéricos e positivos ($> 0$).

---

## 🚀 Como Executar o Projeto

Certifique-se de ter o **JDK (Java Development Kit)** instalado em sua máquina.

1. Abra o seu terminal e navegue até a pasta raiz do projeto (onde os arquivos `.java` estão localizados, geralmente dentro de `src`):
```bash
cd javajavajava\Projeto poo

```


2. Compile os arquivos Java:
```bash
javac *.java

```


3. Execute a aplicação (substitua `NomeDaClassePrincipal` pelo nome da classe que contém o método `main`, ex: `Main` ou `TelaPrincipal`):
```bash
java Main

```



---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java ☕
* **Interface Gráfica:** Java Swing 🖥️
* **Paradigma:** Orientação a Objetos (POO) 📐

---

*Desenvolvido para fins acadêmicos e de automação de processos.*
