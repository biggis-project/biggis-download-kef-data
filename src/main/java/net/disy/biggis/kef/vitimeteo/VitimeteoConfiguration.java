//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.vitimeteo;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

// NOT_PUBLISHED
@Configuration
public class VitimeteoConfiguration {

  @Bean
  public HttpClient httpClient() {
    return HttpClientBuilder
        .create()
        .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")
        .build();
  }

  @Bean
  public RestTemplate restTemplate(HttpClient httpClient) {
    return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
  }

}
