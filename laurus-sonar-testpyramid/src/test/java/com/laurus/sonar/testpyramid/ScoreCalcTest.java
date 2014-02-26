package com.laurus.sonar.testpyramid;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

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

@RunWith(JUnit4.class)
public class ScoreCalcTest {
    @Test
    public void onlyUnitTests() {
        TestPyramidScoreCalculator calc = new TestPyramidScoreCalculator();
        double score = calc.calculateScore(100, 0, 0);
        assertEquals(50.0d, score, 0.0d);
    }

    @Test
    public void unitAndManualTests() {
        TestPyramidScoreCalculator calc = new TestPyramidScoreCalculator();
        double score = calc.calculateScore(70, 0, 10);
        assertEquals(80.0d, score, 0.0d);
    }

    @Test
    public void unitAndIntegrationTests() {
        TestPyramidScoreCalculator calc = new TestPyramidScoreCalculator();
        double score = calc.calculateScore(70, 20, 0);
        // bad test alert!  65 is just what it's doing and i'm not certain why
        assertEquals(65.0d, score, 0.0d);
    }

    @Test
    public void perfectScore() {
        TestPyramidScoreCalculator calc = new TestPyramidScoreCalculator();
        double score = calc.calculateScore(70, 20, 10);
        assertEquals(100.0d, score, 0.0d);
    }

    @Test
    public void noTestsReturnsZero() {
        TestPyramidScoreCalculator calc = new TestPyramidScoreCalculator();
        double score = calc.calculateScore(0, 0, 0);
        assertEquals(0.0d, score, 0.0d);
    }
}
