package com.lazydev.stksongbook.mailer.client.api;

import com.lazydev.stksongbook.mailer.client.invoker.ApiClient;

import com.lazydev.stksongbook.mailer.client.model.ApiHealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-08-25T00:05:28.841+02:00")
@Component("com.lazydev.stksongbook.mailer.client.api.UnsupportedPathsApi")
public class UnsupportedPathsApi {
    private ApiClient apiClient;

    public UnsupportedPathsApi() {
        this(new ApiClient());
    }

    @Autowired
    public UnsupportedPathsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ApiHealth
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHealth unsupported() throws RestClientException {
        return unsupportedWithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ResponseEntity&lt;ApiHealth&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHealth> unsupportedWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/*").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ApiHealth> returnType = new ParameterizedTypeReference<ApiHealth>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ApiHealth
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHealth unsupported_0() throws RestClientException {
        return unsupported_0WithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ResponseEntity&lt;ApiHealth&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHealth> unsupported_0WithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/*").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ApiHealth> returnType = new ParameterizedTypeReference<ApiHealth>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ApiHealth
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHealth unsupported_1() throws RestClientException {
        return unsupported_1WithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ResponseEntity&lt;ApiHealth&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHealth> unsupported_1WithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/*").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ApiHealth> returnType = new ParameterizedTypeReference<ApiHealth>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ApiHealth
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHealth unsupported_2() throws RestClientException {
        return unsupported_2WithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ResponseEntity&lt;ApiHealth&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHealth> unsupported_2WithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/*").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ApiHealth> returnType = new ParameterizedTypeReference<ApiHealth>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ApiHealth
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ApiHealth unsupported_3() throws RestClientException {
        return unsupported_3WithHttpInfo().getBody();
    }

    /**
     * 
     * 
     * <p><b>200</b> - Redirects to the Healt edpoint
     * @return ResponseEntity&lt;ApiHealth&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ApiHealth> unsupported_3WithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/*").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<ApiHealth> returnType = new ParameterizedTypeReference<ApiHealth>() {};
        return apiClient.invokeAPI(path, HttpMethod.PATCH, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
