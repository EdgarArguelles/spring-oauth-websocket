package app.security.services;

import app.security.pojos.LoggedUser;
import org.springframework.social.oauth2.AccessGrant;

/**
 * Deals with parsing oauth profile process
 */
public interface OAuthService {

    /**
     * Parse Facebook profile to LoggedUser instance
     *
     * @param accessGrant instance of AccessGrant generated for Facebook API
     * @return LoggedUser instance associated with Facebook profile
     */
    LoggedUser parseFacebookUser(AccessGrant accessGrant);

    /**
     * Parse Google profile to LoggedUser instance
     *
     * @param accessGrant instance of AccessGrant generated for Google API
     * @return LoggedUser instance associated with Google profile
     */
    LoggedUser parseGoogleUser(AccessGrant accessGrant);
}