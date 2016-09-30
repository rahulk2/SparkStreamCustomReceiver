import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Config {

  def configReceiver(): StreamingContext = {
    val conf = new SparkConf()
      .setAppName("wohooo")
      .setMaster("local[2]")

    val streamingContext = new StreamingContext(conf, Seconds.apply(2))
   return streamingContext

  }

}
