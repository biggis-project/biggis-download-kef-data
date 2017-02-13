//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.chart.fallenfaenge;

import java.util.List;

import net.disy.biggis.kef.job.chart.base.ChartDto;
import net.disy.biggis.kef.service.KefImportFileService;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// NOT_PUBLISHED
@Component
@StepScope
public class ChartsFallenfaengeWriter implements ItemWriter<ChartDto> {

  @Autowired
  private KefImportFileService fileService;

  @Override
  public void write(List<? extends ChartDto> data) throws Exception {
    for (ChartDto current : data) {
      fileService.writeChartsFallenfaenge(current.getId(), current.getYear(), current.getContent());
    }

  }

}
