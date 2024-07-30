package com.personalkcal.service.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalkcal.configuration.ChatGPTConfig;
import com.personalkcal.dto.chatgpt.ChatCompletionDto;
import com.personalkcal.dto.chatgpt.CompletionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService{

    private final ChatGPTConfig chatGPTConfig;



    @Value("${openai.url.model}")
    private String modelUrl;

    @Value("${openai.url.model-list}")
    private String modelListUrl;

    @Value("${openai.url.prompt}")
    private String promptUrl;

    @Value("${openai.url.legacy-prompt}")
    private String legacyPromptUrl;

    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY = 5000;

    /**
     * 사용 가능한 모델 리스트를 조회하는 비즈니스 로직
     *
     * @return List<Map < String, Object>>
     */
    @Override
    public List<Map<String, Object>> modelList() {
        log.debug("[+] 모델 리스트를 조회합니다.");
        List<Map<String, Object>> resultList = null;

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity<String> response = chatGPTConfig
                .restTemplate()
                .exchange(modelUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();
            Map<String, Object> data = om.readValue(response.getBody(), new TypeReference<>() {
            });

            // [STEP4] 응답 값을 결과값에 넣고 출력을 해봅니다.
            resultList = (List<Map<String, Object>>) data.get("data");
            for (Map<String, Object> object : resultList) {
                log.debug("ID: " + object.get("id"));
                log.debug("Object: " + object.get("object"));
                log.debug("Created: " + object.get("created"));
                log.debug("Owned By: " + object.get("owned_by"));
            }
        } catch (JsonMappingException e) {
            log.debug("JsonMappingException :: " + e.getMessage());
        } catch (JsonProcessingException e) {
            log.debug("JsonProcessingException :: " + e.getMessage());
        } catch (RuntimeException e) {
            log.debug("RuntimeException :: " + e.getMessage());
        }
        return resultList;
    }


    /**
     * 모델이 유효한지 확인하는 비즈니스 로직
     *
     * @param modelName {}
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> isValidModel(String modelName) {
        log.debug("[+] 모델이 유효한지 조회합니다. 모델 : " + modelName);
        Map<String, Object> result = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity<String> response = chatGPTConfig
                .restTemplate()
                .exchange(modelListUrl + "/" + modelName, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();
            result = om.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.debug("JsonMappingException :: " + e.getMessage());
        } catch (RuntimeException e) {
            log.debug("RuntimeException :: " + e.getMessage());
        }
        return result;
    }


    @Override
    public Map<String, Object> legacyPrompt(CompletionDto completionDto) {
        log.debug("[+] 레거시 프롬프트를 수행합니다.");
        HttpHeaders headers = chatGPTConfig.httpHeaders();
        HttpEntity<CompletionDto> requestEntity = new HttpEntity<>(completionDto, headers);

        return makeRequestWithRetry(legacyPromptUrl, HttpMethod.POST, requestEntity);
    }

    @Override
    public Map<String, Object> prompt(ChatCompletionDto chatCompletionDto) {
        log.debug("[+] 신규 프롬프트를 수행합니다.");
        HttpHeaders headers = chatGPTConfig.httpHeaders();
        HttpEntity<ChatCompletionDto> requestEntity = new HttpEntity<>(chatCompletionDto, headers);

        return makeRequestWithRetry(promptUrl, HttpMethod.POST, requestEntity);
    }

    private Map<String, Object> makeRequestWithRetry(String url, HttpMethod method, HttpEntity<?> requestEntity) {
        int retryCount = 0;

        while (retryCount < MAX_RETRIES) {
            try {
                ResponseEntity<String> response = chatGPTConfig.restTemplate().exchange(url, method, requestEntity, String.class);
                ObjectMapper om = new ObjectMapper();
                return om.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
            } catch (HttpClientErrorException.TooManyRequests e) {
                retryCount++;
                log.warn("Too many requests. Retrying... ({}/{})", retryCount, MAX_RETRIES);
                if (retryCount >= MAX_RETRIES) {
                    throw e; // Re-throw the exception if max retries reached
                }
                try {
                    Thread.sleep(RETRY_DELAY); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry", ie);
                }
            } catch (JsonProcessingException e) {
                log.debug("JsonMappingException :: " + e.getMessage());
                throw new RuntimeException("Failed to process JSON response", e);
            }
        }
        return new HashMap<>(); // Return empty map if retries exhausted
    }
}
