//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.map.eiablage.funde;

import static java.text.MessageFormat.format;

import javax.annotation.PostConstruct;

import net.disy.biggis.kef.job.map.base.MapDto;
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
public class MapEiablageFundeReader implements ItemReader<MapDto> {

  @Autowired
  private VitimeteoClient client;

  @Value("${eiMapFundeUrlPattern}")
  private String urlPattern;

  @Value("#{jobParameters[startYear]}")
  private int startYear;

  @Value("#{jobParameters[endYear]}")
  private int endYear;

  private int currentYear;

  @PostConstruct
  public void initCurrentValues() {
    currentYear = startYear;
  }

  @Override
  public MapDto read()
      throws Exception,
      UnexpectedInputException,
      ParseException,
      NonTransientResourceException {
    if (currentYear > endYear) {
      return null;
    }
    String url = format(urlPattern, currentYear);
    String content = client.requestAsString(url);
    MapDto result = new MapDto(currentYear, content);
    advance();
    return result;
  }

  private void advance() {
    currentYear++;
  }

}
