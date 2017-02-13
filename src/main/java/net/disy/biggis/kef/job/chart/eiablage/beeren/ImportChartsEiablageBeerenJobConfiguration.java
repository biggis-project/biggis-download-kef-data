//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.chart.eiablage.beeren;

import net.disy.biggis.kef.job.chart.base.ChartDto;
import net.disy.biggis.kef.job.chart.base.ChartRejectEmptyResponsesProcessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// NOT_PUBLISHED
@Configuration
@EnableBatchProcessing
public class ImportChartsEiablageBeerenJobConfiguration {

  @Autowired
  private JobBuilderFactory jobs;

  @Autowired
  private StepBuilderFactory steps;

  @Autowired
  private ChartsEiablageBeerenReader reader;

  @Autowired
  private ChartRejectEmptyResponsesProcessor filter;

  @Autowired
  private ChartsEiablageBeerenWriter writer;

  @Bean
  @Qualifier("importChartsEiablageBeeren")
  public Job importChartsEiablageBeeren() {
    return jobs.get("importChartsEiablageBeeren").start(webToJsonChartEi()).build();
  }

  private Step webToJsonChartEi() {
    return steps
        .get("webToJsonChartEiBeeren")
        .<ChartDto, ChartDto> chunk(1)
        .reader(reader)
        .processor(filter)
        .writer(writer)
        .build();
  }
}
