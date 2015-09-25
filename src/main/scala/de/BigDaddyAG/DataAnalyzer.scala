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

// Define a class describing the "items" (lines) in your CSV file
case class GccData(col0: String, col1: String, col2: String, col3: String, col4: String, col5: String, col6: String,
                   col7: String, col8: String, col9: String, col10: String, col11: String, col12: String,
                   col13: String, col14: String, col15: String, col16: String)


object DataAnalyzer {

  def main(args: Array[String]) {

    val gccFilePath = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/GCC/All/allGccDataClean.csv"
    // val path = "/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC/"

    // enable recursive enumeration of nested input files
    val env = ExecutionEnvironment.getExecutionEnvironment

    val gccData = env.readCsvFile[GccData] (
      gccFilePath,
      fieldDelimiter = ",",
      includedFields = Array(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
    )

    //gccData.writeAsCsv("/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/output")

    /*
     * let's get inspired by the following code...

    // Training data
    val input: DataSet[LabeledVector] = ...
    // Test data
    val unlabeled: DataSet[Vector] = ...

    val scaler = StandardScaler()
    val polyFeatures = PolynomialFeatures()
    val mlr = MultipleLinearRegression()

    // Construct the pipeline
    val pipeline = scaler
      .chainTransformer(polyFeatures)
      .chainPredictor(mlr)

    // Train the pipeline (scaler and multiple linear regression)
    pipeline.fit(input)

    // Calculate predictions for the testing data
    val predictions: DataSet[LabeledVector] = pipeline.predict(unlabeled)

    */


    env.execute("Make it run!!1!")

  }
}