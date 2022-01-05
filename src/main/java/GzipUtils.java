import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
public final class GzipUtils {

  private GzipUtils() {
    // private constructor to avoid class instantiation as this class holds only utility methods.
  }

  /**
   * Unzips the Compressed CloudWatch LogEvent.
   *
   * @throws DecodingException if unable to compress the data.
   */
  public static String unzip(byte[] compressed) {
    try (GZIPInputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(compressed))) {
      return IOUtils.toString(inputStream, Charset.defaultCharset());
    } catch (IOException e) {
      throw new RuntimeException("Cannot uncompress data", e);
    }
  }

  /**
   * GZIP String.
   *
   * @param data String
   * @return Byte Array
   * @throws DecodingException if unable to compress the data.
   */
  public static byte[] zip(String data, Boolean toBytes) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try (GZIPOutputStream gzipOut = new GZIPOutputStream(byteArrayOutputStream)) {
      gzipOut.write(data.getBytes(Charset.defaultCharset()));
    } catch (IOException e) {
      throw new RuntimeException("Cannot compress data", e);
    }
    return (Boolean.TRUE.equals(toBytes)) ? byteArrayOutputStream.toByteArray() : null;
  }
}
