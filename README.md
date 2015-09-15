# MedBioPro aka Medical Bioinformatics Project

#### This is the GitHub page for the project done during the Summer Term's course of Medical Bioinformatics at the Freie Universität Berlin.

---

##### This project aims to
* build a workflow / pipelaine based on Apache Flink to analyze data from TCGA
* implements three main components of the pipline that:
  * read in and preprocess data from the TCGA project
  * analyze the data using at least one machine learning and one network-based method
  * build a classifier that &mdash; based on input data &mdash; can distinguish two cohorts, e.g. "cancer vs. healthy" or "cancer sub-group A vs. cancer sub-group B" or similar
* run the pipeline on a flink cluster


##### Quick Install Guide
* `git clone https://github.com/BigDaddyAG/MedBioPro`
* open IntelliJ and select "Import Project" from start window
* switch to folder that contains the "pom.xml" file and follow the guided import
* after indexing you should be able to see (and compile) the files located at "src/main/scala/de.BigDaddyAG"
* have fun!

