//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.map.fallenfaenge;

import java.util.List;

import net.disy.biggis.kef.job.map.base.MapDto;
import net.disy.biggis.kef.service.KefImportFileService;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// NOT_PUBLISHED
@Component
@StepScope
public class MapFallenfaengeWriter implements ItemWriter<MapDto> {

  @Autowired
  private KefImportFileService fileService;

  @Override
  public void write(List<? extends MapDto> data) throws Exception {
    for (MapDto current : data) {
      fileService.writeMapFallenfaenge(current.getYear(), current.getContent());
    }

  }

}
