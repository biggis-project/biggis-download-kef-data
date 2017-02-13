//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.map.base;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// NOT_PUBLISHED
@Component
@StepScope
public class MapRejectEmptyResponsesProcessor implements ItemProcessor<MapDto, MapDto> {

  @Value("${minResponseLengthMap}")
  private int minimumResponseLength;

  @Override
  public MapDto process(MapDto data) throws Exception {
    if (data.getContent().length() <= minimumResponseLength) {
      return null;
    }
    return data;
  }

}
