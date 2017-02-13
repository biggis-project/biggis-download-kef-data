//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.chart.fallenfaenge;

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
public class ImportChartsFallenfaengeJobConfiguration {

  @Autowired
  private JobBuilderFactory jobs;

  @Autowired
  private StepBuilderFactory steps;

  @Autowired
  private ChartsFallenfaengeReader reader;

  @Autowired
  private ChartRejectEmptyResponsesProcessor filter;

  @Autowired
  private ChartsFallenfaengeWriter writer;

  @Bean
  @Qualifier("importChartsFallenfaenge")
  public Job importChartsFallenfaenge() {
    return jobs.get("importChartsFallenfaenge").start(webToJsonChartFF()).build();
  }

  private Step webToJsonChartFF() {
    return steps
        .get("webToJsonChartFf")
        .<ChartDto, ChartDto> chunk(1)
        .reader(reader)
        .processor(filter)
        .writer(writer)
        .build();
  }
}
