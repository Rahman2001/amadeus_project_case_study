package com.example.amadeus_project_case_study.config;

import com.example.amadeus_project_case_study.repository.ExternalFlightAPIService;
import lombok.Data;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.google.common.collect.ImmutableMap.toImmutableMap;


@Data
@Configuration
public class RetrofitClientConfig {
    private final Long RETROFIT_CACHE_SIZE;
    private final Long RETROFIT_LONG_RUNNING_READ_TIMEOUT;
    private final Long RETROFIT_DEFAULT_READ_TIMEOUT;
    private OkHttpClient okHttpClient;
    private final Map<String, EndpointProperties> endpointPropertyMap;
    private final JacksonConverterFactory jacksonConverterFactory;

    @Autowired
    public RetrofitClientConfig(@Qualifier("restEndpoints") final List<EndpointProperties> restEndpoints,
                                  @NonNull @Value("${retrofit.integration.cacheSizeInMb}") final Long RETROFIT_CACHE_SIZE,
                                  @NonNull @Value("${retrofit.integration.longRunningReadTimeout}") Long RETROFIT_LONG_READING_TIMEOUT)
    {
        this.RETROFIT_CACHE_SIZE = RETROFIT_CACHE_SIZE;
        this.RETROFIT_LONG_RUNNING_READ_TIMEOUT = RETROFIT_LONG_READING_TIMEOUT;
        this.RETROFIT_DEFAULT_READ_TIMEOUT = 200L;

        this.endpointPropertyMap = groupEndpoints(restEndpoints);
        this.okHttpClient = defaultSetup();
        this.jacksonConverterFactory = JacksonConverterFactory.create();
    }

    private OkHttpClient defaultSetup() {
        return new OkHttpClient.Builder()
                .readTimeout(RETROFIT_DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(60L, TimeUnit.SECONDS)
                .callTimeout(20L, TimeUnit.SECONDS)
//                .addInterceptor(new RequestLoggerInterceptor())
//                .addInterceptor(new CorrelationIdHeaderInterceptor())
                .build();
    }
    @Bean
    public ExternalFlightAPIService externalFlightAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(this.okHttpClient)
                .baseUrl(this.endpointPropertyMap.get("External_Flight_API").getBaseUrl())
                .addConverterFactory(this.jacksonConverterFactory)
                .build();
        return retrofit.create(ExternalFlightAPIService.class);
    }

    private Map<String, EndpointProperties> groupEndpoints(List<EndpointProperties> restEndpoints) {
        return restEndpoints.stream().collect(toImmutableMap(EndpointProperties::getServiceName, Function.identity()));
    }
}
