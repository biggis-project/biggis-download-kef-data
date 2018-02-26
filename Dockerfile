FROM anapsix/alpine-java

MAINTAINER wipatrick

ADD ./target/import-charts-0.1-SNAPSHOT.jar  /import-charts-0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/import-charts-0.1-SNAPSHOT.jar"]