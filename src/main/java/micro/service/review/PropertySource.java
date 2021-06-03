package micro.service.review;

import lombok.Data;

import java.util.Map;

@Data
public class PropertySource {
    String name;
    Map<String, String> source;
}
