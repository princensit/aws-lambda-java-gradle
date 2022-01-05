import com.amazonaws.services.lambda.runtime.events.CloudWatchLogsEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.binary.Base64;

public class CloudWatchEventHandler extends EventHandler<CloudWatchLogsEvent> {

  @Override
  List<String> getRequests(CloudWatchLogsEvent event) {
    String data = event.getAwsLogs().getData();
    byte[] compressed = Base64.decodeBase64(data);
    String cloudWatchEventString = GzipUtils.unzip(compressed);
    System.out.println(cloudWatchEventString);
    CloudWatchEvent cloudWatchEvent =
        JsonUtils.fromJson(cloudWatchEventString, CloudWatchEvent.class);
    List<String> entries = new ArrayList<>();
    for (LogEvent logEvent : cloudWatchEvent.getLogEvents()) {
      entries.add(logEvent.getMessage());
    }

    return entries;
  }
}
