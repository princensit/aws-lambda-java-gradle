import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloudWatchEvent {
  private String logGroup;
  private String owner;
  private String logStream;
  private List<String> subscriptionFilters;
  private String messageType;
  private List<LogEvent> logEvents;
}
