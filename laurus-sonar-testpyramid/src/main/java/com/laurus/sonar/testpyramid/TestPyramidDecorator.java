package com.laurus.sonar.testpyramid;

import com.laurus.sonar.metrics.TestPyramidMetrics;
import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.CoreMetrics;
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
      int unitTests = 0;
      int integrationTests = 0;
      int manualTests = 0;
      if(context.getMeasure(CoreMetrics.TESTS) != null) {
        unitTests = context.getMeasure(CoreMetrics.TESTS).getIntValue();
      }
      if(context.getMeasure(TestPyramidMetrics.INTEGRATION_TESTS) != null) {
        integrationTests = context.getMeasure(TestPyramidMetrics.INTEGRATION_TESTS).getIntValue();
      }
      if(context.getMeasure(TestPyramidMetrics.MANUAL_TESTS) != null) {
        manualTests = context.getMeasure(TestPyramidMetrics.MANUAL_TESTS).getIntValue();
      }

      double total = unitTests + integrationTests + manualTests;
      double pUnit = unitTests / total;
      double pIntegration = integrationTests / total;
      double pManual = manualTests / total;
      // TODO: allow site-wide configuration of test pyramid ideals
      double score = 100.0d * ((pUnit / 70.0d) + (pIntegration / 20.0d) + (pManual / 10.0d));
      context.saveMeasure(TestPyramidMetrics.PYRAMID_SCORE, score);
    }


  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

    private Settings settings;

}
