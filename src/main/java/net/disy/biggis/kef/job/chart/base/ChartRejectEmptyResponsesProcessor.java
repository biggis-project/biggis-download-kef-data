//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.chart.base;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// NOT_PUBLISHED
@Component
@StepScope
public class ChartRejectEmptyResponsesProcessor implements ItemProcessor<ChartDto, ChartDto> {

  @Value("${minResponseLengthChart}")
  private int minimumResponseLength;

  @Override
  public ChartDto process(ChartDto data) throws Exception {
    if (data.getContent().length() <= minimumResponseLength) {
      return null;
    }
    return data;
  }

}
