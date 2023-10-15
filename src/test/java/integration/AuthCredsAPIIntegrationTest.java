package integration;

import com.example.Profile.adaptor.inbound.controller.apiModels.AuthCredsApiModel;
import com.example.Profile.adaptor.outbound.repository.AuthCredsRepository;
import com.example.Profile.domain.dto.AuthCredsDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthCredsAPIIntegrationTest extends BaseIntegrationTest{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthCredsRepository authCredsRepository;

    private void setupMocksUnderIntegrationProfile(Runnable runnable) {
        runnable.run();
    }

    private String authCredsApiUrl() {
        return getBaseUrl() + "/v1/authcreds";
    }

    @AfterEach
    void cleanUp() {
        authCredsRepository.deleteAll();
    }

    public static AuthCredsApiModel getAuthCredsApiModel() {
        return AuthCredsApiModel.builder()
                .username("username-test")
                .password("password-test")
                .build();
    }

    @Test
    @SneakyThrows
    void givenValidAuthCreds_whenPostAuthCreds_thenAuthCredsInserted() {
        AuthCredsApiModel authCredsApiModel = getAuthCredsApiModel();
        HttpEntity<AuthCredsApiModel> httpEntity = new HttpEntity<>(authCredsApiModel);

        String url = authCredsApiUrl();
        ResponseEntity<Boolean> postResponseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Boolean.class);

        ResponseEntity<AuthCredsDTO> getResponseEntity = restTemplate.getForEntity(
                String.format(url + "/%s", authCredsApiModel.getUsername()),
                AuthCredsDTO.class
        );

        assertThat(authCredsApiModel.getPassword()).isEqualTo(getResponseEntity.getBody().getPassword());
        assertThat(authCredsApiModel.getUsername()).isEqualTo(getResponseEntity.getBody().getUsername());
    }
}
