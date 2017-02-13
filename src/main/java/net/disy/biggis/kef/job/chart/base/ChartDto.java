//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.chart.base;

// NOT_PUBLISHED
public class ChartDto {

  private int id;
  private int year;
  private String content;

  public ChartDto(int id, int year, String content) {
    this.id = id;
    this.year = year;
    this.content = content;
  }

  public int getId() {
    return id;
  }

  public int getYear() {
    return year;
  }

  public String getContent() {
    return content;
  }

}
