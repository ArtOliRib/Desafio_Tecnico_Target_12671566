# Desafio_Tecnico_Target_12671566

Repositório com a resolução dos desafios técnicos da Target (12671566). São três projetos Maven
independentes, escritos em Java, cada um em sua própria pasta.

## Requisitos

- **JDK 25 ou superior**. Os três POMs apontam para `source/target` 25. Os Desafios 1 e 2 usam o
  `main` não-público e `java.lang.IO` (recursos do JDK 21+); o Desafio 3 usa o `main` tradicional
  `public static void main(String[])` e `System.out`.
- **Maven 3.x** (para compilar via linha de comando) *ou* **IntelliJ IDEA** (a configuração de IDE está versionada).
- As dependências do Jackson ficam no `~/.m2/repository` após o primeiro build.

---

## Estrutura e resumo de cada arquivo

```
Desafio_Tecnico_Target_12671566/
├── README.md                      # Este arquivo
├── .gitattributes                 # Normalização de line endings (LF) para todos os textos do repo
│
├── Desafio_Tecnico_1/             # Desafio 1 – Cálculo de comissões de vendas (JSON)
│   ├── pom.xml                    # Build Maven (Java 25) + dependência jackson-databind 2.17.0
│   ├── .gitignore                 # Ignora target/, arquivos de IDE e artefatos de build
│   ├── .idea/                     # Configuração do IntelliJ IDEA (encodings, misc e vcs versionados)
│   ├── src/main/arquivos/registros.json   # Dados de entrada: 36 vendas de 4 vendedores
│   ├── src/main/java/org/arthur/Main.java # Programa: lê o JSON e calcula a comissão de cada venda
│   └── target/                    # Saída do build (.class) – não versionar
│
├── Desafio_Tecnico_2/             # Desafio 2 – Sistema de estoque com menu interativo
│   ├── pom.xml                    # Build Maven (Java 25) + jackson-databind 2.17.0
│   ├── .gitignore                 # Ignora target/, arquivos de IDE e artefatos de build
│   ├── .idea/                     # Configuração do IntelliJ IDEA
│   ├── src/main/arquivos/estoque.json          # Dados de entrada: 5 produtos em estoque
│   ├── src/main/java/org/arthur/Main.java      # Programa: carrega o estoque e exibe o menu de movimentações
│   ├── src/main/java/org/arthur/model/Produto.java        # Modelo: código, descrição e quantidade (POJO)
│   ├── src/main/java/org/arthur/metodos/HashProdutos.java # Carrega o JSON em um HashMap<String, Produto> (chave = código)
│   ├── src/main/java/org/arthur/metodos/Movimentos.java   # Singleton que registra entradas/saídas e o histórico
│   └── target/                    # Saída do build (.class) – não versionar
│
└── Desafio_Tecnico_3/             # Desafio 3 – Calculadora de juros simples
    ├── pom.xml                    # Build Maven (Java 25, sem dependências)
    ├── .gitignore                 # Ignora target/, arquivos de IDE e artefatos de build
    ├── .idea/                     # Configuração do IntelliJ IDEA
    ├── src/main/java/org/arthur/Main.java              # Ponto de entrada: prompts, validação e saída
    ├── src/main/java/org/arthur/leitura/LeitorConsole.java  # Módulo leitura: encapsula o Scanner
    ├── src/main/java/org/arthur/metodo/CalculadoraJuros.java # Módulo método: cálculo dos juros simples
    └── target/                    # Saída do build (.class) – não versionar
```

### Resumo por arquivo

#### Raiz do projeto

| Arquivo | Resumo |
|---|---|
| `README.md` | Documentação do repositório: estrutura, resumo e instruções de teste. |
| `.gitattributes` | Garante que Git normalize automaticamente os line endings (`* text=auto`). |

#### Desafio_Tecnico_1 — Comissão de vendas

| Arquivo | Resumo |
|---|---|
| `pom.xml` | Projeto Maven `org.arthur:Desafio_Tecnico_1:1.0-SNAPSHOT`, compilador Java 25, dependência única: `jackson-databind:2.17.0`. |
| `src/main/arquivos/registros.json` | Arquivo de dados: objeto com a chave `"vendas"`, contendo 36 vendas (`vendedor` + `valor`) de João Silva, Maria Souza, Carlos Oliveira e Ana Lima. |
| `src/main/java/org/arthur/Main.java` | Lê o JSON com Jackson (`ObjectMapper.readTree`), percorre o array `vendas` e imprime a comissão de cada venda: **sem comissão** se valor ≤ 100; **1%** se ≤ 500; **5%** acima disso. Caminho do arquivo é relativo (`src/main/arquivos/registros.json`), logo o programa precisa rodar com a pasta do desafio como diretório corrente. |
| `.gitignore` | Padrão Maven/IDE: ignora `target/`, `.idea/*` selecionado, `.vscode/`, `.DS_Store`, etc. |
| `.idea/*` | Configurações do IntelliJ versionadas: `encodings.xml` (UTF-8), `misc.xml` (projeto Maven/JDK 25) e `vcs.xml` (Git). Os arquivos `compiler.xml`, `jarRepositories.xml` e `workspace.xml` existem apenas localmente — são ignorados pelo Git (`.gitignore`). |
| `target/classes/org/arthur/Main.class` | Bytecode compilado (build anterior). Pode ser regenerado/apagado. |

#### Desafio_Tecnico_2 — Sistema de estoque (menu interativo)

| Arquivo | Resumo |
|---|---|
| `pom.xml` | Projeto Maven `org.arthur:Desafio_Tecnico_2:1.0-SNAPSHOT`, compilador Java 25, dependência única: `jackson-databind:2.17.0`. |
| `src/main/arquivos/estoque.json` | Dados de entrada: chave `"estoque"` com 5 produtos — Caneta Azul (101, 150 un.), Caderno Universitário (102, 75), Borracha Branca (103, 200), Lápis Preto HB (104, 320) e Marcador de Texto Amarelo (105, 90). |
| `src/main/java/org/arthur/Main.java` | Ponto de entrada: carrega o JSON, monta o `HashMap` de produtos via `HashProdutos` e roda o loop do menu (1 = entrada, 2 = saída, 3 = listar movimentações, 0 = sair) com `Scanner`. |
| `src/main/java/org/arthur/model/Produto.java` | POJO com `codigo`, `descricao`, `quantidade` e getters/setters. |
| `src/main/java/org/arthur/metodos/HashProdutos.java` | `iniciar(JsonNode)` percorre o array do JSON e popula um `HashMap<String, Produto>` usando `codigoProduto` como chave; imprime a listagem do estoque inicial. |
| `src/main/java/org/arthur/metodos/Movimentos.java` | **Singleton** (`getInstance()`) responsável pelas movimentações: `entradaProduto` soma estoque, `saidaProduto` subtrai, ambos geram um registro sequencial (`#1`, `#2`, ...) guardado em `ArrayList<String>`; `listarMovimentacoes` mostra o histórico. |
| `.gitignore`, `.idea/*`, `target/*` | Igual ao Desafio 1 (build, IDE e bytecode). |

#### Desafio_Tecnico_3 — Juros de 2,5% ao dia

| Arquivo | Resumo |
|---|---|
| `pom.xml` | Projeto Maven `org.arthur:Desafio_Tecnico_3:1.0-SNAPSHOT`, compilador Java 25, **sem dependências**. |
| `src/main/java/org/arthur/Main.java` | Ponto de entrada: lê valor e vencimento, valida a data (não pode ser anterior à atual), chama o cálculo e imprime o resultado. |
| `src/main/java/org/arthur/leitura/LeitorConsole.java` | Módulo **leitura**: encapsula o `Scanner` (`lerDecimal`, `lerLinha`, `fechar`). |
| `src/main/java/org/arthur/metodo/CalculadoraJuros.java` | Módulo **método**: juros simples `total = valor + (valor * taxa * dias)`, com `ChronoUnit.DAYS`; retorna uma classe `static` `Resultado` (campos `final` e getters). |
| `.gitignore`, `.idea/*`, `target/*` | Igual aos demais desafios. |

---

## Como testar

### Desafio 1 — Comissões de vendas

**Opção A — IntelliJ IDEA:** abra a pasta `Desafio_Tecnico_1` como projeto e execute `org.arthur.Main`
(botão verde ao lado de `main`). Certifique-se de que a working directory seja a raiz do módulo (padrão do IntelliJ).

**Opção B — Maven:**

```powershell
cd Desafio_Tecnico_1
mvn compile exec:java -Dexec.mainClass=org.arthur.Main
```

**Opção C — Manual (javac/java), sem Maven:**

```powershell
cd Desafio_Tecnico_1
$m2  = "$env:USERPROFILE\.m2\repository\com\fasterxml\jackson\core"
$cp  = "$m2\jackson-databind\2.17.0\jackson-databind-2.17.0.jar;$m2\jackson-core\2.17.0\jackson-core-2.17.0.jar;$m2\jackson-annotations\2.17.0\jackson-annotations-2.17.0.jar"
javac -encoding UTF-8 -cp $cp -d target\classes (Get-ChildItem -Recurse -Filter *.java src).FullName
java  -cp "target\classes;$cp" org.arthur.Main
```

**Saída esperada** (sem interação — o teste é a comparação dos valores):

```
Hello and welcome!
--- Lista de Vendas ---
Vendedor: João Silva | Comissão R$60,03 | Valor da venda R$1200.5
Vendedor: João Silva | Comissão R$47,54 | Valor da venda R$950.75
Vendedor: João Silva | Comissão R$90,00 | Valor da venda R$1800.0
...
```

Regra aplicada: venda ≤ R$ 100 → **Sem Comissão**; de R$ 100 a R$ 500 → **1%**; acima de R$ 500 → **5%**.

Verificações manuais (conferir com a saída):
- venda de R$ 90,75 (Maria Souza) → **Sem Comissão**.
- venda de R$ 480,75 (João Silva) → 1% = **R$ 4,81**.
- venda de R$ 1200,50 (João Silva) → 5% = **R$ 60,03**.

> **Importante:** o programa só encontra o JSON se executado com a pasta `Desafio_Tecnico_1` como
> diretório corrente. Rodando de outro lugar aparece `Erro ao ler o arquivo JSON: ... não pode encontrar caminho`.

### Desafio 2 — Sistema de estoque

**Opção A — IntelliJ IDEA:** abra a pasta `Desafio_Tecnico_2` e execute `org.arthur.Main`.

**Opção B — Maven:**

```powershell
cd Desafio_Tecnico_2
mvn compile exec:java -Dexec.mainClass=org.arthur.Main
```

**Opção C — Manual (javac/java), sem Maven:**

```powershell
cd Desafio_Tecnico_2
$m2  = "$env:USERPROFILE\.m2\repository\com\fasterxml\jackson\core"
$cp  = "$m2\jackson-databind\2.17.0\jackson-databind-2.17.0.jar;$m2\jackson-core\2.17.0\jackson-core-2.17.0.jar;$m2\jackson-annotations\2.17.0\jackson-annotations-2.17.0.jar"
javac -encoding UTF-8 -cp $cp -d target\classes (Get-ChildItem -Recurse -Filter *.java src).FullName
java  -cp "target\classes;$cp" org.arthur.Main
```

**Teste manual (interativo):**

1. Ao iniciar, a lista do estoque deve exibir os 5 produtos (101 a 105) com suas quantidades.
2. `1` → entrada: código `101`, quantidade `10` → deve responder `#1 Entrada ... Quantidade nova: 160`.
3. `2` → saída: código `101`, quantidade `5` → deve responder `#2 Saida ... Quantidade nova: 155`.
4. `3` → listar movimentações → deve mostrar `#1` e `#2`.
5. `0` → encerra.

Limitações conhecidas (comportamento a esperar nos testes):
- O caminho do JSON é relativo: execute sempre a partir da pasta `Desafio_Tecnico_2`.
- Informar um código inexistente gera `NullPointerException` (o `HashMap.get` retorna `null`).
- A entrada redirecionada por pipe/arquivo (`java ... < input.txt`) **não funciona**: há vários `Scanner`
  independentes sobre o mesmo `System.in` e o primeiro deles consome o buffer. Teste sempre de forma interativa.
- As alterações de estoque são apenas em memória; nada é gravado de volta no `estoque.json`.

### Desafio 3 — Calculadora de juros

**Opção A — IntelliJ IDEA:** abra a pasta `Desafio_Tecnico_3` e execute `org.arthur.Main`.

**Opção B — Maven:**

```powershell
cd Desafio_Tecnico_3
mvn compile exec:java -Dexec.mainClass=org.arthur.Main
```

**Opção C — Manual (javac/java), sem Maven:**

```powershell
cd Desafio_Tecnico_3
javac -encoding UTF-8 -d target\classes (Get-ChildItem -Recurse -Filter *.java src\main\java).FullName
java  -cp target\classes org.arthur.Main
```

**Teste manual (interativo):**

1. Valor `1000` e data futura (ex.: `31/12/2026`) → período de 84 dias,
   juros = 1000 × 0,025 × 84 = **R$ 2.100,00**, total **R$ 3.100,00**.
2. Data no formato errado (ex.: `2026-12-31`) → `Erro: Data inválida ou impossível...`.
3. Data anterior à atual → `Erro: A data informada não pode ser anterior à data atual.`.
4. Valor `0` com data de amanhã → total igual a `R$ 0,00`.

---

## Notas gerais de build

- Os três projetos usam `source/target` 25 e compilam sem flags extras. Os Desafios 1 e 2 usam
  `IO.println` e `main` sem `public`/sem `args` (Java 21+); o Desafio 3 usa o `public static void main(String[] args)`
  e `System.out` convencionais.
- Os POMs 1 e 2 dependem do `jackson-databind`, baixado do Maven Central no primeiro `mvn compile`.
- Para quem preferir não usar Maven, as Opções C acima executam cada desafio direto com `javac`/`java`
  (os JARs do Jackson são os mesmos do repositório local `~/.m2`).
