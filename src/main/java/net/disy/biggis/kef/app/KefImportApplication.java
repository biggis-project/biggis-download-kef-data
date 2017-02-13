package net.disy.biggis.kef.app;

import java.time.Instant;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("net.disy.biggis.kef")
public class KefImportApplication implements CommandLineRunner {

  @Value("${startYear}")
  private int startYear;

  @Value("${endYear}")
  private int endYear;

  @Value("${startId}")
  private int startId;

  @Value("${endId}")
  private int endId;

  @Value("${ffImportEnabled}")
  private boolean isFfImportEnabled;

  @Value("${eiImportEnabled}")
  private boolean isEiImportEnabled;

  @Value("${mapImportEnabled}")
  private boolean isMapImportEnabled;

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  @Qualifier("importChartsFallenfaenge")
  private Job importChartsFallenfaenge;

  @Autowired
  @Qualifier("importChartsEiablageBeeren")
  private Job importChartsEiablageBeeren;

  @Autowired
  @Qualifier("importChartsEiablageFunde")
  private Job importChartsEiablageFunde;
  
  @Autowired
  @Qualifier("importMapFallenfaenge")
  private Job importMapFallenfaenge;

  @Autowired
  @Qualifier("importMapEiablageFunde")
  private Job importMapEiablageFunde;

  @Autowired
  @Qualifier("importMapEiablageBeeren")
  private Job importMapEiablageBeeren;

  @Override
  public void run(String... args) throws JobExecutionException, InterruptedException {

    if (isFfImportEnabled) {
      JobExecution execution = executeFallenfaengeImportJob();
      while (execution.isRunning()) {
        Thread.sleep(100);
      }
    }

    if (isEiImportEnabled) {
      JobExecution execution = executeEiablageBeerenImportJob();
      while (execution.isRunning()) {
        Thread.sleep(100);
      }
    }

    if (isEiImportEnabled) {
        JobExecution execution = executeEiablageFundeImportJob();
        while (execution.isRunning()) {
          Thread.sleep(100);
        }
      }

    
    if (isMapImportEnabled) {
      JobExecution execution = executeMapFallenfaengeImportJob();
      while (execution.isRunning()) {
        Thread.sleep(100);
      }
    }

    if (isMapImportEnabled) {
      JobExecution execution = executeMapEiablageFundeJob();
      while (execution.isRunning()) {
        Thread.sleep(100);
      }
    }

    if (isMapImportEnabled) {
      JobExecution execution = executeMapEiablageBeerenJob();
      while (execution.isRunning()) {
        Thread.sleep(100);
      }
    }
  }

  private JobExecution executeFallenfaengeImportJob() throws JobExecutionException {
    return jobLauncher.run(importChartsFallenfaenge, createUniqueChartParameters());
  }

  private JobExecution executeEiablageBeerenImportJob() throws JobExecutionException {
    return jobLauncher.run(importChartsEiablageBeeren, createUniqueChartParameters());
  }

  private JobExecution executeEiablageFundeImportJob() throws JobExecutionException {
	    return jobLauncher.run(importChartsEiablageFunde, createUniqueChartParameters());
	  }

  
  private JobExecution executeMapFallenfaengeImportJob() throws JobExecutionException {
    return jobLauncher.run(importMapFallenfaenge, createUniqueMapParameters());
  }

  private JobExecution executeMapEiablageFundeJob() throws JobExecutionException {
    return jobLauncher.run(importMapEiablageFunde, createUniqueMapParameters());
  }

  private JobExecution executeMapEiablageBeerenJob() throws JobExecutionException {
    return jobLauncher.run(importMapEiablageBeeren, createUniqueMapParameters());
  }

  private JobParameters createUniqueChartParameters() {
    return createYearParametrizedJobBuilder().addLong("startId", Long.valueOf(startId)) //$NON-NLS-1$
        .addLong("endId", Long.valueOf(endId)) //$NON-NLS-1$
        .toJobParameters();
  }

  private JobParameters createUniqueMapParameters() {
    return createYearParametrizedJobBuilder().toJobParameters();
  }

  private JobParametersBuilder createYearParametrizedJobBuilder() {
    return new JobParametersBuilder().addDate("timestamp", Date.from(Instant.now())) //$NON-NLS-1$
        .addLong("startYear", Long.valueOf(startYear)) //$NON-NLS-1$
        .addLong("endYear", Long.valueOf(endYear)); //$NON-NLS-1$
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(KefImportApplication.class, args);
  }
}
