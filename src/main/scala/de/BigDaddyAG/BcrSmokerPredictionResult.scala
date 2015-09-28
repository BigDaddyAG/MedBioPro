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



case class JoinedDataClass(consentPatientBarcode: String, patientConsentStatus: String, startedSmoking: Int, stoppedSmoking: Int)


object BcrSmokerPredictionResult {

  def main(args: Array[String]) {

    val env = ExecutionEnvironment.getExecutionEnvironment

    // Zarin
    //val smokerStatusFile = "/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_clinical_patient_luad.txt"
    //val consentStatusFile = "/Users/Zarin/Documents/Uni/BigDaddyAG/MedBioPro/data/BCR/Clinical/Biotab/nationwidechildrens.org_biospecimen_cqcf_luad.txt"
    // Stefan
    val joinedDataFile = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/output/somkeOutput.csv"


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
    joinedDataResult.writeAsCsv("/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/output/joinedSomkeOutput.csv", "\n", ",", WriteMode.OVERWRITE).setParallelism(1)


    env.execute("Make it run!!1!")

  }


  private def readJoinedData(env: ExecutionEnvironment, path: String, includedCols: Array[Int]): DataSet[JoinedDataClass] = {
    env.readCsvFile[JoinedDataClass](path, fieldDelimiter = ",", includedFields = includedCols)
  }


}