package app.security.services.implementations;

import app.security.pojos.LoggedUser;
import app.security.services.OAuthService;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class OAuthServiceImpl implements OAuthService {

    @Override
    public LoggedUser parseFacebookUser(AccessGrant accessGrant) {
        FacebookTemplate template = new FacebookTemplate(accessGrant.getAccessToken());
        String[] fields = {"id", "picture", "first_name", "last_name", "gender", "email"};
        User profile = template.fetchObject("me", User.class, fields);
        String id = profile.getId();
        String firstName = profile.getFirstName();
        String lastName = profile.getLastName();

        byte[] image = template.userOperations().getUserProfileImage();
        StringBuilder imageString = new StringBuilder();
        imageString.append("data:image/png;base64,");
        imageString.append(Base64.getEncoder().encodeToString(image));

        return new LoggedUser(id, firstName + " " + lastName, imageString.toString());
    }

    @Override
    public LoggedUser parseGoogleUser(AccessGrant accessGrant) {
        GoogleTemplate template = new GoogleTemplate(accessGrant.getAccessToken());
        org.springframework.social.google.api.plus.Person profile = template.plusOperations().getGoogleProfile();
        String id = profile.getId();
        String givenName = profile.getGivenName();
        String familyName = profile.getFamilyName();

        String image = profile.getImageUrl().replace("?sz=50", "?sz=100");
        return new LoggedUser(id, givenName + " " + familyName, image);
    }
}