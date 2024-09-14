## Projeto: Processamento de Dados com Scala e Apache Spark

### Objetivo do Projeto

O objetivo deste projeto é demonstrar a capacidade de processar e analisar dados de um arquivo CSV utilizando a linguagem de programação Scala em conjunto com o Apache Spark. O arquivo CSV utilizado, `BD_Pesquisa_Letramento_Financeiro_dados_abertos.csv`, contém dados relacionados a pesquisas sobre letramento financeiro. O projeto visa aplicar técnicas avançadas de filtragem e agregação para extrair e interpretar informações significativas a partir desses dados.

### Por que usamos Scala e Spark?

**Scala** foi escolhida por ser uma linguagem moderna e funcional, que oferece uma integração natural com o **Apache Spark**. O Spark é uma ferramenta altamente eficiente para processamento de grandes volumes de dados e análise em larga escala. Juntos, Scala e Spark proporcionam um ambiente robusto e de alto desempenho para trabalhar com big data, permitindo o processamento paralelo e rápido de grandes conjuntos de dados.

### Estrutura do Projeto

1. **Inicialização do SparkSession**:
   ```scala
   val spark = SparkSession.builder()
     .appName("DataProcessing")
     .master("local[*]")
     .getOrCreate()
   ```
   - **Objetivo**: Estabelecer uma conexão com o Spark para permitir a leitura e o processamento dos dados.
   - **Explicação**: O `SparkSession` é a principal entrada para interagir com o Spark. A configuração `.master("local[*]")` indica que o processamento deve usar todos os núcleos da CPU disponíveis localmente, o que é ideal para desenvolvimento e testes.

2. **Carregamento do Arquivo CSV**:
   ```scala
   val filePath = "caminho/para/seu/arquivo.csv"
   val df = spark.read
     .option("header", "true")
     .option("delimiter", ";")
     .csv(filePath)
   ```
   - **Objetivo**: Importar os dados do arquivo CSV para um DataFrame, que é a estrutura de dados do Spark para manipulação de tabelas.
   - **Explicação**: O DataFrame `df` é carregado com cabeçalhos e usa o ponto e vírgula como delimitador, correspondendo ao formato do arquivo CSV.

3. **Exibição dos Primeiros Registros**:
   ```scala
   df.show(5)
   ```
   - **Objetivo**: Verificar a integridade e o formato dos dados carregados.
   - **Explicação**: O método `show(5)` exibe as primeiras cinco linhas, permitindo uma revisão rápida dos dados para assegurar que foram lidos corretamente.

4. **Filtragem dos Dados**:
   ```scala
   val filteredDf = df.filter(col("Q_UF") === "13")
   ```
   - **Objetivo**: Selecionar apenas os dados que atendem a um critério específico.
   - **Explicação**: A filtragem é feita para extrair apenas os registros onde a coluna `Q_UF` é igual a "13", focando em um subconjunto relevante dos dados.

5. **Agregação dos Dados**:
   ```scala
   val aggregatedDf = filteredDf.groupBy("Q_Regiao")
     .agg(
       count("Q_SETOR").as("count_setor"),
       avg("QD3").as("avg_qd3")
     )
   ```
   - **Objetivo**: Resumir e consolidar os dados filtrados para análise.
   - **Explicação**: Os dados são agrupados pela coluna `Q_Regiao`. Em seguida, calculam-se a contagem de setores e a média dos valores de `QD3` para cada região, proporcionando uma visão consolidada.

6. **Exibição dos Dados Agregados**:
   ```scala
   aggregatedDf.show(5)
   ```
   - **Objetivo**: Visualizar os resultados da agregação para análise.
   - **Explicação**: Similar à visualização inicial, mas agora focada nos resultados agregados, ajudando a verificar e interpretar os dados consolidados.

7. **Salvamento dos Resultados**:
   ```scala
   aggregatedDf.write
     .option("header", "true")
     .csv("caminho/para/salvar/resultado.csv")
   ```
   - **Objetivo**: Exportar os dados processados e agregados para um novo arquivo CSV.
   - **Explicação**: O DataFrame com os resultados agregados é salvo em um arquivo CSV, mantendo os cabeçalhos das colunas para posterior análise.

8. **Encerramento do SparkSession**:
   ```scala
   spark.stop()
   ```
   - **Objetivo**: Finalizar a sessão do Spark para liberar recursos do sistema.
   - **Explicação**: Encerrar a sessão do Spark é uma prática importante para liberar os recursos do sistema após a conclusão do processamento.

### Conclusão

Este projeto demonstra a utilização de Scala e Apache Spark para o processamento e análise de grandes volumes de dados a partir de um arquivo CSV. Ele abrange desde a configuração inicial do ambiente Spark até a exportação dos resultados processados, destacando a eficácia dessas ferramentas na manipulação e análise de big data.
