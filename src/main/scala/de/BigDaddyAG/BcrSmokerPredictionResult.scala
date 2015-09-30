/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.BigDaddyAG

import org.apache.flink.api.scala._
import org.apache.flink.api.scala.table._
import org.apache.flink.core.fs.FileSystem.WriteMode
import org.apache.flink.ml.regression.MultipleLinearRegression
import org.apache.flink.api.scala.DataSet
import org.apache.flink.ml.common._
import org.apache.flink.ml.MLUtils._




case class JoinedDataClass(consentPatientBarcode: String, patientConsentStatus: String, startedSmoking: Int, stoppedSmoking: Int)


object BcrSmokerPredictionResult {

  def main(args: Array[String]) {

    val env = ExecutionEnvironment.getExecutionEnvironment

    // Zarin
    //val smokerStatusFile = "/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_clinical_patient_luad.txt"
    //val consentStatusFile = "/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_biospecimen_cqcf_luad.txt"
    // Stefan
   // val joinedDataFile = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/output/somkeOutput.csv"
    //Zarin
    val joinedDataFile = "/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/output/somkeOutput.csv"


    val joinedData =
      readJoinedData(env, joinedDataFile, Array(0,1,2,3))
        .as('id, 'status, 'stoppedSmoking, 'startedSmoking)

    val joinedDataResult =
      joinedData
        //.select('id, 'status, 'stoppedSmoking, 'startedSmoking)
        //{ ('id, 'status, 'yearsSmoked) => ('id, 'status, 'stoppedSmoking - 'startedSmoking) }
        .select('id, 'status, 'stoppedSmoking - 'startedSmoking)


    // Zarin
    //result.writeAsCsv("/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/somkeOutput", "\n", "\t").setParallelism(1)
    // Stefan
    //joinedDataResult.writeAsCsv("/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/output/joinedSomkeOutput.csv", "\n", ",", WriteMode.OVERWRITE).setParallelism(1)
    //Zarin
    joinedDataResult.writeAsCsv("/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/output/joinedSomkeOutput.csv", "\n", ",", WriteMode.OVERWRITE).setParallelism(1)



    /*
     * Flink MLR
     * test code from: https://ci.apache.org/projects/flink/flink-docs-master/libs/ml/multiple_linear_regression.html
     *
     * information about libSVM format:
     * In order to train SVMs, the machine learning library should be able to read standard SVM input file formats. A widespread format is used by libSVM and SMVLight which has the following format:
     *  <line> .=. <target> <feature>:<value> <feature>:<value> ... <feature>:<value> # <info>
     *  <target> .=. +1 | -1 | 0 | <float>
     *  <feature> .=. <integer> | "qid"
     *  <value> .=. <float>
     *  <info> .=. <string>
     *
     * More information:
     *  http://svmlight.joachims.org/
     *  http://www.csie.ntu.edu.tw/~cjlin/libsvm/faq.html#/Q03:_Data_preparation
     *  https://stackoverflow.com/questions/31368511/flink-hbase-input-for-machine-learning-algorithms
     */

    /*
    // Create multiple linear regression learner
    val mlr = MultipleLinearRegression()
      .setIterations(10)
      .setStepsize(0.5)
      .setConvergenceThreshold(0.001)

    // Obtain training and testing data set
    //val trainingDS: DataSet[LabeledVector] = ...
    //val testingDS: DataSet[Vector] = ...

    val trainingData: DataSet[LabeledVector] = readLibSVM(env, "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/output/joinedSomkeOutput.csv")
    //val testingData: DataSet[Vector] = readVectorFile(testingDataPath)

    // Fit the linear model to the provided data
    mlr.fit(joinedDataResult)

    // Calculate the predictions for the test data
    val predictions = mlr.predict(joinedDataResult)

    predictions.writeAsCsv("/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/output/predictions.csv", "\n", ",", WriteMode.OVERWRITE).setParallelism(1)
    */


    env.execute("Make it run!!1!")

  }


  private def readJoinedData(env: ExecutionEnvironment, path: String, includedCols: Array[Int]): DataSet[JoinedDataClass] = {
    env.readCsvFile[JoinedDataClass](path, fieldDelimiter = ",", includedFields = includedCols)
  }

  private def readVectorFile = (line: String) => {
    val Seq(id, vector @ _*) = line.split(',').toSeq
    id.toInt -> (vector map { _.toDouble } toArray)
  }

}