import org.apache.flink.api.java.ExecutionEnvironment

object DataImport {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val text = env.readTextFile("")
  }
}