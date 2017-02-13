//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.vitimeteo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

// NOT_PUBLISHED
@Component
public class VitimeteoClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(VitimeteoClient.class);

	@Autowired
	private RestTemplate restTemplate;

	public String requestAsString(String url) {
		try {
			LOGGER.debug("Calling: \"" + url + "\""); //$NON-NLS-1$//$NON-NLS-2$
			ResponseEntity<String> getResult = restTemplate.getForEntity(url, String.class);
			LOGGER.trace(getResult.toString());
			String result = restTemplate.postForObject(url, createFakeWindowParameters(), String.class);
			LOGGER.trace(result);
			return result;
		} catch (RestClientException cause) {
			LOGGER.warn("Error when requesting " + url, cause);
			return "";
		}

	}

	@SuppressWarnings("nls")
	private MultiValueMap<String, String> createFakeWindowParameters() {
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("IW_height", "650");
		postParameters.add("IW_width", "1250");
		return postParameters;
	}

}
