# biggis-download-kef-data
Download client to obtain data to use on vector data pipeline

This repository contains a HTTP client to download data on *Drosophila suzukii* from [http://www.vitimeteo.de/](http://www.vitimeteo.de/). 
The data contains the count of this invasive species at different loactions in the southwest of Germany.

The client is based on Spring Batch and Spring Boot.
Downloaded data will be saved in JSON-files.
These files are then imported into Apache Kafka topics in https://github.com/DisyInformationssysteme/biggis-import-kef-data-to-kafka.

This client is self-sustained and does not need the [Biggis Infrastructure](https://github.com/DisyInformationssysteme/biggis-infrastructure).
However the worklog by Spring Batch requires some database. Currently a Postgres Database is being used.

This work was done for the [BigGIS project](http://biggis-project.eu/) funded by the [German Federal Ministry of Education and Research (BMBF)](https://www.bmbf.de).
