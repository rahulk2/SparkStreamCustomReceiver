import org.apache.spark.streaming.dstream.DStream

object Stream extends App {
  import org.apache.spark.streaming._

  val streamingContext = Config.configReceiver()

  val lines = streamingContext.receiverStream(new CustomReceiver)
  val words: DStream[String] = lines.flatMap(_.split(","))

  val windowedWords = words.reduceByWindow((a: String, b: String) => (a + b), Seconds(10), Seconds(4))
  windowedWords.print()

  streamingContext.start()
  streamingContext.awaitTermination()
}
