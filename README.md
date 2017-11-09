Spring Oauth Websocket
===============

This is a basic example how to get access to Facebook and Google account using OAuth protocol and
sending profile info to frontend with Websockets 

# Stack:
- Gradle
- Spring Boot
- OAuth (access with Facebook and Google)
- WebSocket
- Project Lombok (Getter and Setter Annotations)

# Before Run

Configure application.properties with your Google and Facebook Oauth api-key and api-secret

use
- http://localhost:8000/app/oauth/google/callback as Google Authorized callback
- http://localhost:8000/app/oauth/facebook/callback as Facebook Authorized callback

# Run
    ./gradlew bootRun