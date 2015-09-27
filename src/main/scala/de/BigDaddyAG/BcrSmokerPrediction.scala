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


case class ConsentStatus(consentPatientBarcode: String, patientConsentStatus: String)
case class smokerStatus(smokerPatientBarcode: String, patientSmokerStatus: String)



object BcrSmokerPrediction {

  def main(args: Array[String]) {

    val consentStatusFile = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_biospecimen_cqcf_luad.txt"
    val smokerStatusFile = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_clinical_patient_luad.txt"

    // enable recursive enumeration of nested input files
    val env = ExecutionEnvironment.getExecutionEnvironment


    val consentStatusData =
      readConsentStatusData(env, consentStatusFile, Array(1, 3))
        .as('consentPatientBarcode, 'patientConsentStatus)

    val smokerStatusData =
      readSmokerStatusData(env, smokerStatusFile, Array(1, 45))
        .as('smokerPatientBarcode, 'patientSmokerStatus)

    val items =
      consentStatusData.join(smokerStatusData)
        .where('consentPatientBarcode === 'smokerPatientBarcode)
        .select('consentPatientBarcode, 'patientConsentStatus,'patientSmokerStatus )


          env.execute("Make it run!!1!")

  }


  private def readConsentStatusData(env: ExecutionEnvironment, path: String, includedCols: Array[Int]): DataSet[ConsentStatus] = {
    env.readCsvFile[ConsentStatus](path, fieldDelimiter = ",", includedFields = includedCols)
  }

  private def readSmokerStatusData(env: ExecutionEnvironment, path: String, includedCols: Array[Int]): DataSet[ConsentStatus] = {
    env.readCsvFile[ConsentStatus](path, fieldDelimiter = ",", includedFields = includedCols)
  }


}