# MedBioPro aka Medical Bioinformatics Project

#### This is the GitHub page for the project done during the Summer Term 2015's course of Medical Bioinformatics at the Freie Universität Berlin.

---

##### This project aims to
* build a workflow / pipeline based on Apache Flink to analyze data from TCGA
* implement three main components of the pipline that:
  * read in and preprocess data from the TCGA project
  * analyze the data using at least one machine learning and one network-based method
  * build a classifier that &mdash; based on input data &mdash; can distinguish two cohorts, e.g. "cancer vs. healthy" or "cancer sub-group A vs. cancer sub-group B" or similar
* run the pipeline on a flink cluster


##### Quick Install Guide
* `git clone https://github.com/BigDaddyAG/MedBioPro`
* open IntelliJ and select "Import Project" from start window
* switch to folder that contains the "pom.xml" file and follow the guided import
* after indexing you should be able to see (and compile) the files located at `src/main/scala/de.BigDaddyAG`
* have fun!



##### Git Repository Structure
  This git repository contains following files/folders

  * `src/main/scala/de/BigDaddyAG`: implemented Scala codes 
  * `Data`:
    * `BCR`: downloaded Clinical Biotab data  
    * `GCC`: TCGA data from 35 patients
      * `All`: 
        - `allGccDataClean.csv`: the final TCGA table of 16 patients, which are manually joined
    * `output`:contains following generated files: 
      * `somkeOutput.csv`: a table of four columns: patientID, Consent Status, end year and start year of smoking 
	  * `joinedSomkeOutput.csv`: here the third column shows how many years each patient smoked
  *  `Report`: the LaTeX and Word - report file 
  *  `Stuff`: the Git preparation commands, related papers to this project and pictures 
