import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Arrays;
import java.util.List;

public abstract class EventHandler<T> implements RequestHandler<T, Void> {

  @Override
  public Void handleRequest(T event, Context context) {
    List<String> requests = getRequests(event);
    System.out.println(Arrays.toString(requests.toArray()));
    return null;
  }

  abstract List<String> getRequests(T event);
}
