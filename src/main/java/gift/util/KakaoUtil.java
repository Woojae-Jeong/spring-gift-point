package gift.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.kakaoOauth.KakaoProperties;
import gift.kakaoOauth.dto.Link;
import gift.kakaoOauth.dto.SendToMeTemplate;
import gift.kakaoOauth.exception.JsonRunTimeException;
import gift.kakaoOauth.exception.KaKaoServerErrorException;
import gift.kakaoOauth.exception.KakaoMessageBadRequestException;
import gift.vo.AccessToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Component
public class KakaoUtil {
    private final RestClient client;
    private final KakaoProperties properties;

    public KakaoUtil(RestClient client, KakaoProperties kakaoProperties) {
        this.client = client;
        this.properties = kakaoProperties;
    }

    public void sendMessageToMe(AccessToken accessToken, String message){
        LinkedMultiValueMap<String, String> body = generateBodyOfSendMessageToMe(message);
        client.post().
                uri(URI.create(properties.messageToMeUri()))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getValue()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new KakaoMessageBadRequestException();
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new KaKaoServerErrorException();
                })
                .toEntity(String.class);
    }

    private LinkedMultiValueMap<String, String> generateBodyOfSendMessageToMe(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        SendToMeTemplate templateObject = new SendToMeTemplate(
                "text",
                message,
                new Link(properties.messageToMeWebUri(), properties.messageToMeUri()));
        try {
            body.add("template_object", objectMapper.writeValueAsString(templateObject));
        } catch (JsonProcessingException e){
            throw new JsonRunTimeException();
        }
        return body;
    }
}
