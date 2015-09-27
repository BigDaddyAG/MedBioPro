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
import scala.io.Source
// Important: include the TABLE API import
import org.apache.flink.api.scala.table._
import java.io.File

// Define a class describing the "items" (lines) in your CSV file
case class gccColumnItems(col1: String, col2: String, col3: String, col4: String, col5: String, col6: String, col7: String, col8: String , col9: String)
//case class MyLineitem(col1: String, col2: String)
case class bcrColumnItems(id: String, consentStatus: String)


object DataPreprocessor {

  def main(args: Array[String]){

    // enable recursive enumeration of nested input files
    val env = ExecutionEnvironment.getExecutionEnvironment


    val gccFile =
      getGccFile(env, "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/GCC/All/allGccDataClean.csv")
        .as('f1col1, 'f1col2, 'f1col3, 'f1col4, 'f1col5, 'f1col6, 'f1col7, 'f1col8, 'f1col9, 'f1col9, 'f1col10,
          'f1col11 , 'f1col12 , 'f1col13, 'f1col14, 'f1col15, 'f1col16, 'f1col17)

    /*
    val items =
      gccFile.join(gccFile)
        .where('geneExpression.standardDeviation > 3)
        .select()
    */

    env.execute("Preprocess data")

  }

  // This method reads all rows but only selected columns from a file and returns a dataset
  private def getGccFile(env: ExecutionEnvironment, path:String): DataSet[gccColumnItems] = {
    env.readCsvFile[gccColumnItems](
      path,
      fieldDelimiter = "\t",
      includedFields = Array(0, 1, 2, 3 , 4, 5, 6, 7, 8, 9 , 10 , 11, 12, 13, 14, 15, 16))
  }

  // This method reads all rows but only selected columns from a file and returns a dataset
  private def getBcrFile(env: ExecutionEnvironment, path:String): DataSet[bcrColumnItems] = {
    env.readCsvFile[bcrColumnItems](
      path,
      fieldDelimiter = "\t",
      includedFields = Array(1, 3))
  }

  /*
  private def standardDeviation() = {

  }
  */

}