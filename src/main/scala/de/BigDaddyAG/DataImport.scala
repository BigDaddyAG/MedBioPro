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


import breeze.linalg.Axis._1
import org.apache.flink.api.scala._

// Important: include the TABLE API import

import org.apache.flink.api.scala.table._
import java.io.File

// Define a class describing the "items" (lines) in your CSV file
case class MyLineitem(col1: String, col2: String)

object DataImport {


  // define file path where GCC data (transcriptomes) is stored
  //val dataGCCFilePath = "/Users/stefan/Documents/Uni/SoSe 2015/Medical Bioinformatics/assignment11/BigDaddyAG/MedBioPro/data/GCC/"
  val dataGCCFilePath = "/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC/"
 // val path = "/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC/"

  // Let your main method call the actual method to read in the data and perform some select statement
  def main(args: Array[String]){

    // enable recursive enumeration of nested input files
    val env = ExecutionEnvironment.getExecutionEnvironment

    // function to list all files in a directory
    def getListOfFiles(dir: String): List[File] = {
      val d = new File(dir)
      if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).toList
      } else {
        List[File]()
      }
    }


    // extract the filename of absolute path to file and print it
    val files = getListOfFiles(dataGCCFilePath)
    val filenameArray = files.toString.split(",")
    val sizeOfFilenameArray = filenameArray.size
    //println(filenameArray(2))

    val fileNames  =  filenameArray.toArray
    for (i <- 0 to fileNames.size-1) {
      println(fileNames(i))

    }

    //    for (i <- 0 to sizeOfFilenameArray-1) {
    //      val lineArray = filenameArray(i).split("/")
    //      val sizeOfLine = lineArray.size
    //      println(lineArray(sizeOfLine-1))
    //    }

/*   val firstFile =
      getDataSetFile(env, "/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC/US82800149_251976011596_S01_GE2_105_Dec08.txt_lmean.out.logratio.gene.tcga_level3.data.txt")
        .as('f1col1, 'f1col2)

    val secondFile =
      getDataSetFile(env, "/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC/US82800149_251976011662_S01_GE2_105_Dec08.txt_lmean.out.logratio.gene.tcga_level3.data.txt")
        .as('f2col1, 'f2col2)

    val items =
      firstFile.join(secondFile)
        .where('f1col1 === 'f2col1)
        .select('f1col1, 'f1col2, 'f2col2)


    items.writeAsCsv("/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC/output", "\t", "\n")*/


    val firstFile = getDataSetFile(env,filenameArray(1)).as('firstFileCol1, 'firstFileCol2)
    for (i <- 2 to filenameArray.size-2) {

     // val COl = i+"col1"

       // Read a file but only includes the 1st, 2nd column - returns DataSet[MyLineitem]
       val CurrentFile = getDataSetFile(env,filenameArray(i)).as('col1, 'col2)

      val items =
        firstFile.join(CurrentFile)
          .where('firstFileCol1 === 'col1)
          .select('firstFileCol1, 'firstFileCol2, 'col2)

     }

    items.writeAsCsv("/Users/Zarin/Documents/Uni/BigDaddy/MedBioPro/data/GCC/output", "\t", "\n")


    env.execute("Join")

  }

  // This method reads all rows but only selected columns from a file and returns a dataset
  private def getDataSetFile(env: ExecutionEnvironment, path:String): DataSet[MyLineitem] = {
    env.readCsvFile[MyLineitem](
      path,
      fieldDelimiter = "\t",
      includedFields = Array(0, 1))

  }

/*
  // This method reads all rows but only selected columns from a file and returns a dataset
  private def readMyDataSet(env: ExecutionEnvironment): DataSet[MyLineitem] = {
    env.readCsvFile[MyLineitem](
      path,
      fieldDelimiter = "\t",
      includedFields = Array(0, 1))

  }
*/





  // This method reads all rows but only selected columns from a file and returns a dataset
 private def readMyDataSet(env: ExecutionEnvironment, includedCols: Array[Int], path: String): DataSet[MyLineitem] = {
    env.readCsvFile[MyLineitem](
      path,
      fieldDelimiter = "\t",
      includedFields = includedCols)

  }

}