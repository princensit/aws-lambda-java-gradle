import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

public final class JsonUtils {

  private static final ObjectMapper OBJECT_MAPPER;

  static {
    OBJECT_MAPPER = JsonMapper.builder()
        .configure(SerializationFeature.INDENT_OUTPUT, true)
        .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
        .disable(MapperFeature.USE_GETTERS_AS_SETTERS)
        .build();
  }

  private JsonUtils() {
    // private constructor to avoid class instantiation as this class holds only utility methods.
  }

  public static String toJson(Object value) {
    try {
      return OBJECT_MAPPER.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T fromJson(String value, Type type) {
    JavaType javaType = getJavaType(type);
    try {
      return OBJECT_MAPPER.readValue(value, javaType);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T fromJson(InputStream inputStream, Type type) {
    JavaType javaType = getJavaType(type);
    try {
      return OBJECT_MAPPER.readValue(inputStream, javaType);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * converts string value to map.
   *
   * @param value value
   * @return map
   */
  public static Map<String, Object> convertToMap(String value) {
    try {
      return OBJECT_MAPPER.readValue(value, Map.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * converts object value to map.
   *
   * @param object object
   * @return map
   */
  public static Map<String, Object> convertToMap(Object object) {
    return OBJECT_MAPPER.convertValue(object, Map.class);
  }

  /**
   * converts map value to object.
   */
  public static <T> T convertMapToObject(Object fromValue, Class<T> toValueType) {
    return OBJECT_MAPPER.convertValue(fromValue, toValueType);
  }

  private static JavaType getJavaType(Type type) {
    return OBJECT_MAPPER.constructType(type);
  }
}
