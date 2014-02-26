package com.laurus.sonar.testpyramid;

/*
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
*/

import com.laurus.sonar.metrics.TestPyramidMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.MeasureUtils;
import org.sonar.api.measures.Metric;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;

public class TestPyramidDecorator implements Decorator {

    public TestPyramidDecorator(Settings settings) {
        this.settings = settings;
    }

  public boolean shouldExecuteOnProject(Project project) {
      // only execute on the root project
      return project.isRoot();
  }
  public void decorate(Resource resource, DecoratorContext context) {
      double integrationTests = 0;
      double manualTests = 0;
      //context.getChildrenMeasures(CoreMetrics.TESTS);
      double unitTests = MeasureUtils.sum(true, context.getChildrenMeasures(CoreMetrics.TESTS));
      if(context.getMeasure(TestPyramidMetrics.INTEGRATION_TESTS) != null) {
        integrationTests = context.getMeasure(TestPyramidMetrics.INTEGRATION_TESTS).getValue();
      }
      double x = MeasureUtils.sum(true, context.getChildrenMeasures(s)) + MeasureUtils.sum(true, context.getChildrenMeasures(c));
      integrationTests = x;
      if(context.getMeasure(TestPyramidMetrics.MANUAL_TESTS) != null) {
        manualTests = context.getMeasure(TestPyramidMetrics.MANUAL_TESTS).getValue();
      }

      TestPyramidScoreCalculator scoreCalculator = new TestPyramidScoreCalculator();
      double score = scoreCalculator.calculateScore(unitTests, integrationTests, manualTests);

      context.saveMeasure(TestPyramidMetrics.PYRAMID_SCORE, score);
    }

    private Metric s = new Metric.Builder("service_test_count", "service_test_count", Metric.ValueType.INT).create();
    private Metric c = new Metric.Builder("controller_test_count", "controller_test_count", Metric.ValueType.INT).create();

  public String toString() {
    return getClass().getSimpleName();
  }

    private Settings settings;
    private Logger logger = LoggerFactory.getLogger(getClass());
}
