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


case class ConsentStatus(bcrPatientBarcode: String, patientConsentStatus: String)
case class smokeStatus(bcrPatientBarcode: String, patientConsentStatus: String)



object BcrSmokerPrediction {

  def main(args: Array[String]) {

    val consentStatusFile = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_biospecimen_cqcf_luad.txt"
    val smokeStatusFile = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_clinical_patient_luad.txt"

    // enable recursive enumeration of nested input files
    val env = ExecutionEnvironment.getExecutionEnvironment

/*    val smokeStatusData = env.readCsvFile[smokeStatus] (
      consentStatusFile,
      fieldDelimiter = ",",
      includedFields = Array(1, 45)
    )*/

    val gccFile =
      readConsentStatusData(env, consentStatusFile, Array(1,3))
        .as('bcrPatientStatus, 'patientConsentStatus)


    env.execute("Make it run!!1!")

  }

  // This method reads all rows but only selected columns from a file and returns a dataset
  private def readConsentStatusData(env: ExecutionEnvironment, path: String, fieldDelimiter: String, includedFields: String): DataSet[ConsentStatus] = {
    env.readCsvFile[gccColumnItems] (
      path,
      fieldDelimiter,
      includedFields )
  }

  private def readConsentStatusData(env: ExecutionEnvironment, path: String, includedCols: Array[Int]): DataSet[ConsentStatus] = {
    env.readCsvFile[ConsentStatus](path, fieldDelimiter = ",", includedFields = includedCols)
  }



}