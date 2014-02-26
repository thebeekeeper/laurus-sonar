package com.laurus.sonar.testpyramid;

/**
 Copyright 2014 Nick Gamroth

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 * User: thebeekeeper
 * Date: 2/25/14
 * Time: 5:59 PM
 */

public class TestPyramidScoreCalculator {

    public double calculateScore(double unitTests, double integrationTests, double manualTests) {

        double total = (double)(unitTests + integrationTests + manualTests);

        double pUnit = 0.0d;
        double pIntegration = 0.0d;
        double pManual = 0.0d;
        if(total > 0) {
            pUnit = 100.0d * (unitTests / total);
            pIntegration = 100.0d * (integrationTests / total);
            pManual = 100.0d * (manualTests / total);
        }
        // TODO: allow site-wide configuration of test pyramid ideals
        double denominator = (pUnit / 70.0d) + (pIntegration / 20.0d) + (pManual / 10.0d);
        double score = 0.0d;
        if(pUnit == 100.0) {
            // hack: if there are only unit tests it messes up the calculation and it's not
            // possible to get a good number anyways without a measure of how good the unit
            // tests are, so we'll just give a 50%
            return 50.0d;
        }
        if(denominator != 0) {
            score = 100 - ( Math.abs(100 - ( 1 / denominator * 300)));
        }
        return score;
    }
}
