package EdsonJr21.SoundMusic.Platform.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsultaAPI {

    private static final String WIKIPEDIA_API_URL = "https://en.wikipedia.org/api/rest_v1/page/summary/";

    public static String obterInformacao(String artista) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = WIKIPEDIA_API_URL + artista.replace(" ", "_");
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        return root.path("extract").asText();
    }
}
