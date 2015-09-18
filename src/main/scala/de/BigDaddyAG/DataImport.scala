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
// Important: include the TABLE API import
import org.apache.flink.api.scala.table._
import java.io.File

// Define a class describing the "items" (lines) in your CSV file
case class MyLineitem(col1: Int, col2: Int, col3: String)

object DataImport {


  // Let your main method call the actual method to read in the data and perform some select statement
  def main(args: Array[String]): Unit = {

    // enable recursive enumeration of nested input files
    val env = ExecutionEnvironment.getExecutionEnvironment

    def getListOfFiles(dir: String):List[File] = {
      val d = new File(dir)
      if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).toList
      } else {
        List[File]()
      }
    }
    val files = getListOfFiles("/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC")
    val filenameArray = files.toString.split(",")
    val sizeOfArray = filenameArray.size
    for (i <- 0 to sizeOfArray-1) {
      val lineArray = filenameArray(i).split("/")
      val sizeOfLine = lineArray.size
      println(lineArray(sizeOfLine-1))
    }


    val allColumns = readMyDataSet(env, Array(0, 1)).as( 'col1, 'col2)

    while () {
      // Read a file but only includes the 1st, 2nd column - returns DataSet[MyLineitem]
      val lineItems = readMyDataSet(env, Array(0, 1)).as('col1, 'col2)

      // Select only 'col1' and 'col2' from those lines where...
      val currentColumn = lineItems
        //      .where('col1 < 10) // value of the first column is less than 10
        //      .where('col1 !== 'col2) // values of col1 and col2 are different
        //      .where('col1 !== "x") // value of col3 is not "x"
        .select('col2)

      val Result =
        allColumns.join(currentColumn)
    }


  }

  // This method reads all rows but only selected columns from a file and returns a dataset
  def readMyDataSet(env: ExecutionEnvironment, includedCols: Array[Int]): DataSet[MyLineitem] = {

    // define file path where GCC data (transcriptomes) are stored
    //val dataGCCFilePath = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/GCC/"
    val dataGCCFilePath = "/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC"

    env.readCsvFile[MyLineitem] (
      dataGCCFilePath,
      fieldDelimiter = "\t",
      includedFields = includedCols )
  }

}