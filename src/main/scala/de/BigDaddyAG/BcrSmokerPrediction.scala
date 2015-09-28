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

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala._
import org.apache.flink.api.scala.table._
import org.apache.flink.runtime.util.MathUtils


case class ConsentStatus(consentPatientBarcode: String, patientConsentStatus: String)
//case class SmokerStatus(smokerPatientBarcode: String, patientSmokerStatus: String, startedSmoking: String, stoppedSmoking: String)
case class SmokerStatus(smokerPatientBarcode: String, numberPacksSmoked: String)


object BcrSmokerPrediction {


  def main(args: Array[String]) {

    // Zarin
    //val smokerStatusFile = "/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_clinical_patient_luad.txt"
    //val consentStatusFile = "/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_biospecimen_cqcf_luad.txt"
    // Stefan
    val consentStatusFile = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_biospecimen_cqcf_luad.txt"
    val smokerStatusFile = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_clinical_patient_luad.txt"



    val env = ExecutionEnvironment.getExecutionEnvironment


    val consentStatusData =
      readConsentStatusData(env, consentStatusFile, Array(1, 3))
        .as('consentPatientBarcode, 'patientConsentStatus)

    /* old version where we weren't able to cast to Int
    val smokerStatusData =
      readSmokerStatusData(env, smokerStatusFile, Array(1, 43, 44, 45))
        .as('smokerPatientBarcode, 'startedSmoking, 'stoppedSmoking, 'patientSmokerStatus)

    val result =
      consentStatusData.join(smokerStatusData)
        .where('consentPatientBarcode === 'smokerPatientBarcode).where("startedSmoking != '[Not Available]' && stoppedSmoking != '[Not Available]'")
        .select('consentPatientBarcode, 'patientConsentStatus, 'patientSmokerStatus, 'stoppedSmoking - 'startedSmoking) // TODO: how to cast stoppedSmoking and startedSmoking from String to Int?!
        //{ (consentPatientBarcode, patientConsentStatus, patientSmokerStatus, yearsSmoked) => (consentPatientBarcode, patientConsentStatus, patientSmokerStatus, stoppedSmoking - startedSmoking) }
    */

    val smokerStatusData =
      readSmokerStatusData(env, smokerStatusFile, Array(1, 45))
        .as('smokerPatientBarcode, 'numberPacksSmoked)


    val result =
      consentStatusData.join(smokerStatusData)
        .where('consentPatientBarcode === 'smokerPatientBarcode)//.where("startedSmoking != '[Not Available]' && stoppedSmoking != '[Not Available]'")
        .select('consentPatientBarcode, 'patientConsentStatus, 'numberPacksSmoked)
    //{ (consentPatientBarcode, patientConsentStatus, patientSmokerStatus, yearsSmoked) => (consentPatientBarcode, patientConsentStatus, patientSmokerStatus, stoppedSmoking - startedSmoking) }


    // Zarin
    //result.writeAsCsv("/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/somkeOutput", "\n", "\t").setParallelism(1)
    // Stefan
    result.writeAsCsv("/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/somkeOutput.csv", "\n", ",").setParallelism(1)


    env.execute("Make it run!!1!")

  }


  private def readConsentStatusData(env: ExecutionEnvironment, path: String, includedCols: Array[Int]): DataSet[ConsentStatus] = {
    env.readCsvFile[ConsentStatus](path, fieldDelimiter = ",", includedFields = includedCols)
  }

  private def readSmokerStatusData(env: ExecutionEnvironment, path: String, includedCols: Array[Int]): DataSet[SmokerStatus] = {
    env.readCsvFile[SmokerStatus](path, fieldDelimiter = "\t", includedFields = includedCols)
  }


}