package com.laurus.manualtests;

import com.laurus.sonar.metrics.TestPyramidMetrics;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;
/**
 * User: thebeekeeper
 * Date: 1/5/14
 * Time: 3:54 PM
 */
public class ManualTestSensor implements Sensor {
    @Override
    public void analyse(Project project, SensorContext sensorContext) {
        sensorContext.saveMeasure(TestPyramidMetrics.MANUAL_TESTS, 3.0d);
    }

    @Override
    public boolean shouldExecuteOnProject(Project project) {
        return(project.isRoot());
    }
}
