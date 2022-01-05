import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import java.util.ArrayList;
import java.util.List;

public class SqsEventHandler extends EventHandler<SQSEvent> {

  @Override
  List<String> getRequests(SQSEvent event) {
    List<SQSMessage> records = event.getRecords();
    List<String> entries = new ArrayList<>();
    for (SQSMessage record : records) {
      entries.add(record.getMessageId());
    }

    return entries;
  }
}
