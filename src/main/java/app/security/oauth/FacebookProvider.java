package app.security.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;

@Component
public class FacebookProvider implements OAuthProvider {

    private static OAuth2Operations oAuthOperations;

    @Value("${facebook-api-key}")
    private String API_KEY;

    @Value("${facebook-api-secret}")
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
        //scope could be email,user_about_me,user_birthday,user_friends,publish_actions, but facebook will warn users
        //your application is trying to access sensitive information or post for you
        oAuth2Parameters.setScope("email");
        oAuth2Parameters.setState(state);
        return getOAuthOperations().buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
    }

    @Override
    public AccessGrant getAccessGrant(String callback, String authorizationCode) {
        return getOAuthOperations().exchangeForAccess(authorizationCode, callback, null);
    }

    private OAuth2Operations getOAuthOperations() {
        if (oAuthOperations == null) {
            FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(getApiKey(), getApiSecret());
            oAuthOperations = connectionFactory.getOAuthOperations();
        }

        return oAuthOperations;
    }
}