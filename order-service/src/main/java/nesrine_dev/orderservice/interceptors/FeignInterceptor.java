package nesrine_dev.orderservice.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("**************************************");
        System.out.println(authentication.getClass().getName());
        System.out.println("**************************************");
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            String jwtAccessToken = jwtAuthenticationToken.getToken().getTokenValue();
            System.out.println("**************************************");
            System.out.println("Access Token => " + jwtAccessToken);
            System.out.println("**************************************");
            requestTemplate.header("Authorization", "Bearer " + jwtAccessToken);
        }

//        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
//        String jwtAccessToken = jwtAuthenticationToken.getToken().getTokenValue();
//        requestTemplate.header("Authorization", "Bearer " + jwtAccessToken);
    }
}
