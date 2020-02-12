import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class EventProcessor implements RequestHandler<String, String> {

  /**
   * Entry point for lambda.
   *
   * @param event   event
   * @param context context
   * @return result
   */
  public String handleRequest(String event, Context context) {
    TransformerFactory factory = TransformerFactory.newInstance();
    try {
      String xslt = loadAsString("transform-to-xml.xslt");
      Transformer transformer = factory
          .newTransformer(
              new StreamSource(new StringReader(xslt)));

      InputStream inputStream = loadAsStream("input.xml");
      StreamSource source = new StreamSource(inputStream);
      StringWriter writer = new StringWriter();
      transformer.transform(source, new StreamResult(writer));

      System.out.println(writer.toString());
    } catch (Exception ex) {
      throw new RuntimeException(ex.getMessage());
    }

    return "Ok";
  }

  private String loadAsString(String path) throws IOException {
    InputStream inputStream = loadAsStream(path);
    InputStreamReader isReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(isReader);
    StringBuilder sb = new StringBuilder();
    String str;
    while ((str = reader.readLine()) != null) {
      sb.append(str);
    }
    return sb.toString();
  }

  private InputStream loadAsStream(String path) {
    return this.getClass().getClassLoader().getResourceAsStream(path);
  }
}
