//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.map.fallenfaenge;

import net.disy.biggis.kef.job.map.base.MapDto;
import net.disy.biggis.kef.job.map.base.MapRejectEmptyResponsesProcessor;

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
public class ImportMapFallenfaengeJobConfiguration {

  @Autowired
  private JobBuilderFactory jobs;

  @Autowired
  private StepBuilderFactory steps;

  @Autowired
  private MapFallenfaengeReader reader;

  @Autowired
  private MapRejectEmptyResponsesProcessor filter;

  @Autowired
  private MapFallenfaengeWriter writer;

  @Bean
  @Qualifier("importMapFallenfaenge")
  public Job importMapFallenfaenge() {
    return jobs.get("importMapFallenfaenge").start(webToJsonChartFF()).build();
  }

  private Step webToJsonChartFF() {
    return steps
        .get("webToJsonMapFf")
        .<MapDto, MapDto> chunk(1)
        .reader(reader)
        .processor(filter)
        .writer(writer)
        .build();
  }
}
