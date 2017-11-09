package app.security.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;

@Component
public class GoogleProvider implements OAuthProvider {

    private static OAuth2Operations oAuthOperations;

    @Value("${google-api-key}")
    private String API_KEY;

    @Value("${google-api-secret}")
    private String API_SECRET;

    @Override
    public String getApiKey() {
        return API_KEY;
    }

    @Override
    public String getApiSecret() {
        return API_SECRET;
    }

    @Override
    public String getAuthorizeUrl(String callback, String state) {
        OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
        oAuth2Parameters.setRedirectUri(callback);
        oAuth2Parameters.setScope("https://www.googleapis.com/auth/userinfo.profile");
        oAuth2Parameters.setState(state);
        return getOAuthOperations().buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
    }

    @Override
    public AccessGrant getAccessGrant(String callback, String authorizationCode) {
        return getOAuthOperations().exchangeForAccess(authorizationCode, callback, null);
    }

    private OAuth2Operations getOAuthOperations() {
        if (oAuthOperations == null) {
            GoogleConnectionFactory connectionFactory = new GoogleConnectionFactory(getApiKey(), getApiSecret());
            oAuthOperations = connectionFactory.getOAuthOperations();
        }

        return oAuthOperations;
    }
}