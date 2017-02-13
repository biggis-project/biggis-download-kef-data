//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.map.eiablage.funde;

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
public class ImportMapEiablageFundeJobConfiguration {

  @Autowired
  private JobBuilderFactory jobs;

  @Autowired
  private StepBuilderFactory steps;

  @Autowired
  private MapEiablageFundeReader reader;

  @Autowired
  private MapRejectEmptyResponsesProcessor filter;

  @Autowired
  private MapEiablageFundeWriter writer;

  @Bean
  @Qualifier("importMapEiablageFunde")
  public Job importMapEiablageFunde() {
    return jobs.get("importMapEiablageFunde").start(webToJsonMapEiFunde()).build();
  }

  private Step webToJsonMapEiFunde() {
    return steps
        .get("webToJsonMapEiFunde")
        .<MapDto, MapDto> chunk(1)
        .reader(reader)
        .processor(filter)
        .writer(writer)
        .build();
  }
}
