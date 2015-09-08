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


object DataImport {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val text = env.readTextFile("data/unc.edu_LUAD.AgilentG4502A_07_3.Level_3.1.1.0/US82800149_251976011596_S01_GE2_105_Dec08.txt_lmean.out.logratio.gene.tcga_level3.data.txt")
    val text1 = env.readCsvFile("data/unc.edu_LUAD.AgilentG4502A_07_3.Level_3.1.1.0/US82800149_251976011661_S01_GE2_105_Dec08.txt_lmean.out.logratio.gene.tcga_level3.data.txt", fieldDelimiter = "\t")
    val text2 = env.readCsvFile("data/unc.edu_LUAD.AgilentG4502A_07_3.Level_3.1.1.0/US82800149_251976011662_S0_Dec08.txt_lmean.out.logratio.gene.tcga_level3.data.txt") ;
  }
}


