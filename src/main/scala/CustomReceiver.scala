import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import Constants._

/**
  * Created by rahul on 30/9/16.
  */
class CustomReceiver extends Receiver[String](StorageLevel.MEMORY_AND_DISK_2) {

  override def onStart(): Unit = {
    val dataService = new DataService()
    var tokenData: String = dataService.selectAll(offset, range)

    new Thread("Socket Receiver") {

      override def run() {

        while (!isStopped && !tokenData.isEmpty) {
          offset += range
          store(tokenData)
          tokenData = new DataService().selectAll(offset, range);
        }

      }
    }.start()

  }

  override def onStop(): Unit = ???
}
