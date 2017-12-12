package com.hillel.security.reCaptcha;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class ReCaptchaVerifier {

    @Value("${app.reCaptcha.apiUrl}")
    private String reCaptchaApiUrl;

    @Value("${app.reCaptcha.secretKey}")
    private String secretKey;

    private RestTemplate restTemplate;

    public ReCaptchaVerifier() {
        this.restTemplate = new RestTemplate();
    }

    public ReCaptchaResponseDto verify(@NonNull String reCaptchaResponse) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("secret", secretKey);
        form.add("response", reCaptchaResponse);
        return restTemplate.postForObject(reCaptchaApiUrl, form, ReCaptchaResponseDto.class);
    }


}
