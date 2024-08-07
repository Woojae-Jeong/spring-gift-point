package gift.kakaoOauth.Event.EventObject;

import gift.vo.AccessToken;

public class SendMessageToMeEvent {
    private AccessToken accessToken;
    private String message;

    public SendMessageToMeEvent(AccessToken accessToken, String message) {
        this.message = message;
        this.accessToken = accessToken;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public String getMessage() {
        return message;
    }
}
