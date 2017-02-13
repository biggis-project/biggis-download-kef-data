//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.job.map.base;

// NOT_PUBLISHED
public class MapDto {

  private int year;
  private String content;

  public MapDto(int year, String content) {
    this.year = year;
    this.content = content;
  }

  public int getYear() {
    return year;
  }

  public String getContent() {
    return content;
  }

}
