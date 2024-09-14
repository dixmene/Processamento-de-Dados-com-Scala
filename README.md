## Projeto: Processamento de Dados com Scala

### Objetivo do Projeto

O objetivo deste projeto é demonstrar a capacidade de processar e analisar dados de um arquivo CSV usando a linguagem de programação Scala. O arquivo CSV utilizado, `BD_Pesquisa_Letramento_Financeiro_dados_abertos.csv`, contém informações sobre pesquisas de letramento financeiro e está estruturado para permitir a realização de análises significativas. Este projeto visa aplicar técnicas de filtragem e agregação para extrair insights relevantes dos dados.

### Por que usamos Scala e Spark?

**Scala** foi escolhida por ser uma linguagem moderna que se integra perfeitamente com o **Apache Spark**, uma poderosa ferramenta para processamento de grandes volumes de dados. O Spark é conhecido por sua capacidade de realizar análises rápidas e eficientes, e sua integração com Scala torna o desenvolvimento de projetos de processamento de dados mais fluido e direto.

### Estrutura do Projeto

1. **Inicialização do SparkSession**:
   ```scala
   val spark = SparkSession.builder()
     .appName("DataProcessing")
     .master("local[*]")
     .getOrCreate()
   ```
   - **Objetivo**: Criar uma instância do Spark que permite a leitura e processamento dos dados.
   - **Explicação**: O `SparkSession` é o ponto de entrada para usar o Spark. O método `.master("local[*]")` indica que o Spark deve usar todos os núcleos da CPU disponíveis no ambiente local para processamento.

2. **Carregamento do Arquivo CSV**:
   ```scala
   val filePath = "caminho/para/seu/arquivo.csv"
   val df = spark.read
     .option("header", "true")
     .option("delimiter", ";")
     .csv(filePath)
   ```
   - **Objetivo**: Ler os dados do arquivo CSV para uma estrutura que o Spark pode processar.
   - **Explicação**: O arquivo CSV é carregado em um DataFrame (`df`), que é uma estrutura de dados semelhante a uma tabela de banco de dados. A opção `header=true` indica que a primeira linha do arquivo contém os nomes das colunas, e `delimiter=";"` especifica que o separador de colunas é o ponto e vírgula.

3. **Exibição dos Primeiros Registros**:
   ```scala
   df.show(5)
   ```
   - **Objetivo**: Verificar as primeiras linhas dos dados carregados para garantir que foram lidos corretamente.
   - **Explicação**: O método `show(5)` exibe as primeiras cinco linhas do DataFrame, facilitando a verificação inicial dos dados.

4. **Filtragem dos Dados**:
   ```scala
   val filteredDf = df.filter(col("Q_UF") === "13")
   ```
   - **Objetivo**: Filtrar os dados para incluir apenas aqueles que atendem a um critério específico.
   - **Explicação**: O filtro seleciona apenas as linhas onde a coluna `Q_UF` tem o valor "13". Isso é útil para focar em um subconjunto relevante dos dados.

5. **Agregação dos Dados**:
   ```scala
   val aggregatedDf = filteredDf.groupBy("Q_Regiao")
     .agg(
       count("Q_SETOR").as("count_setor"),
       avg("QD3").as("avg_qd3")
     )
   ```
   - **Objetivo**: Agregar e resumir os dados filtrados para análise.
   - **Explicação**: Os dados filtrados são agrupados pela coluna `Q_Regiao`. Em seguida, é calculada a contagem de setores (`count_setor`) e a média de `QD3` para cada região. Isso fornece uma visão consolidada dos dados.

6. **Exibição dos Dados Agregados**:
   ```scala
   aggregatedDf.show(5)
   ```
   - **Objetivo**: Visualizar os resultados da agregação.
   - **Explicação**: Similar à exibição inicial, mas agora para os dados agregados, mostrando as primeiras cinco linhas do resultado.

7. **Salvamento dos Resultados**:
   ```scala
   aggregatedDf.write
     .option("header", "true")
     .csv("caminho/para/salvar/resultado.csv")
   ```
   - **Objetivo**: Salvar os dados agregados em um novo arquivo CSV.
   - **Explicação**: O DataFrame com os dados agregados é escrito em um novo arquivo CSV, incluindo os cabeçalhos das colunas.

8. **Encerramento do SparkSession**:
   ```scala
   spark.stop()
   ```
   - **Objetivo**: Finalizar a sessão Spark para liberar recursos.
   - **Explicação**: É uma prática importante parar o SparkSession após o término das operações para liberar recursos do sistema.

### Conclusão

Este projeto demonstra o uso de Scala e Apache Spark para processar e analisar dados de um arquivo CSV. As etapas incluem a leitura do arquivo, filtragem, agregação e salvamento dos dados. A escolha de Scala e Spark permite a realização eficiente dessas operações, e o projeto destaca habilidades em processamento de dados e análise usando ferramentas modernas.
