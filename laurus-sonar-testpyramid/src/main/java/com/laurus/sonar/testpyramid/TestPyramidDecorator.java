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
      //double unitTests = 0.0d;
      double integrationTests = 0.0d;
      double manualTests = 0.0d;
      context.getChildrenMeasures(CoreMetrics.TESTS);
      /*if(context.getMeasure(CoreMetrics.TESTS) != null) {
        unitTests = context.getMeasure(CoreMetrics.TESTS).getValue();
      }*/
      double unitTests = MeasureUtils.sum(true, context.getChildrenMeasures(CoreMetrics.TESTS));
      if(context.getMeasure(TestPyramidMetrics.INTEGRATION_TESTS) != null) {
        integrationTests = context.getMeasure(TestPyramidMetrics.INTEGRATION_TESTS).getValue();
      }
      double x = MeasureUtils.sum(true, context.getChildrenMeasures(s)) + MeasureUtils.sum(true, context.getChildrenMeasures(c));
      integrationTests = x;
      if(context.getMeasure(TestPyramidMetrics.MANUAL_TESTS) != null) {
        manualTests = context.getMeasure(TestPyramidMetrics.MANUAL_TESTS).getValue();
      }

      double total = (double)(unitTests + integrationTests + manualTests);

      logger.debug("total tests: " + total);
      logger.debug("unit tests: " + unitTests);
      logger.debug("manual tests: " + manualTests);
      double pUnit = 0.0d;
      double pIntegration = 0.0d;
      double pManual = 0.0d;
      if(total > 0) {
          pUnit = 100.0d * (unitTests / total);
          pIntegration = 100.0d * (integrationTests / total);
          pManual = 100.0d * (manualTests / total);
      }
      // TODO: allow site-wide configuration of test pyramid ideals
      logger.debug("unit score: " + (pUnit / 70.0d));
      logger.debug("integration score: " + (pIntegration / 20.0d));
      logger.debug("manual score: " + (pManual / 10.0d));
      //double score = 100.0d * ((pUnit / 70.0d) + (pIntegration / 20.0d+ (pManual / 10.0d));
      double denominator = ((pUnit / 70.0d) + (pIntegration / 20.0d) + (pManual / 10.0d)) ;
      double score = 0.0d;
      if(denominator != 0) {
          score = 100 - ( Math.abs(100 - ( 1 / denominator * 300)));
      }
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
