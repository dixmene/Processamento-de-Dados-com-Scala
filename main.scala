import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object DataProcessing {
  def main(args: Array[String]): Unit = {
    // Inicializa o SparkSession
    val spark = SparkSession.builder()
      .appName("DataProcessing")
      .master("local[*]")
      .getOrCreate()

    // Carrega o arquivo CSV
    val filePath = "caminho/para/seu/arquivo.csv"
    val df = spark.read
      .option("header", "true")
      .option("delimiter", ";")
      .csv(filePath)

    // Exibe os primeiros registros
    df.show(5)

    // Passo 3: Filtrar os dados
    val filteredDf = df.filter(col("Q_UF") === "13")

    // Exibe os dados filtrados
    filteredDf.show(5)

    // Passo 4: Agregar os dados
    val aggregatedDf = filteredDf.groupBy("Q_Regiao")
      .agg(
        count("Q_SETOR").as("count_setor"),
        avg("QD3").as("avg_qd3")
      )

    // Exibe os dados agregados
    aggregatedDf.show(5)

    // Para salvar o resultado em um novo arquivo CSV
    aggregatedDf.write
      .option("header", "true")
      .csv("caminho/para/salvar/resultado.csv")

    // Para parar o SparkSession
    spark.stop()
  }
}
