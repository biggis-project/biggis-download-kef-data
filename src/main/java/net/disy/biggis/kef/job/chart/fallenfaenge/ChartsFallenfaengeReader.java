//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.chart.fallenfaenge;

import static java.text.MessageFormat.format;

import javax.annotation.PostConstruct;

import net.disy.biggis.kef.job.chart.base.ChartDto;
import net.disy.biggis.kef.vitimeteo.VitimeteoClient;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// NOT_PUBLISHED
@StepScope
@Component
public class ChartsFallenfaengeReader implements ItemReader<ChartDto> {

  @Autowired
  private VitimeteoClient client;

  @Value("${ffChartUrlPattern}")
  private String urlPattern;

  @Value("#{jobParameters[startId]}")
  private int startId;

  @Value("#{jobParameters[endId]}")
  private int endId;

  @Value("#{jobParameters[startYear]}")
  private int startYear;

  @Value("#{jobParameters[endYear]}")
  private int endYear;

  private int currentId;
  private int currentYear;

  @PostConstruct
  public void initCurrentValues() {
    currentId = startId;
    currentYear = startYear;
  }

  @Override
  public ChartDto read()
      throws Exception,
      UnexpectedInputException,
      ParseException,
      NonTransientResourceException {
    if (currentId > endId || currentYear > endYear) {
      return null;
    }
    String url = format(urlPattern, currentId, currentYear);
    String content = client.requestAsString(url);
    ChartDto result = new ChartDto(currentId, currentYear, content);
    advance();
    return result;
  }

  private void advance() {
    if (currentId < endId) {
      currentId++;
    }
    else {
      currentId = startId;
      currentYear++;
    }
  }

}
