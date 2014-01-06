package com.laurus.itsensor;


import com.laurus.sonar.metrics.TestPyramidMetrics;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: thebeekeeper
 * Date: 1/5/14
 * Time: 3:20 PM
 */
public class IntegrationTestSensor implements Sensor {
    public void analyse(Project project, SensorContext sensorContext) {
        logger.info("running integration test sensor on " + project.getName());
        // temp
        sensorContext.saveMeasure(TestPyramidMetrics.INTEGRATION_TESTS, 10.0d);
        logger.info("Saved integration tests measure");
    }

    public boolean shouldExecuteOnProject(Project project) {
        boolean shouldExecute = project.getName().contains("IntegrationTest");
        logger.info("Should execute on " + project.getName() + "? " + shouldExecute);
        return shouldExecute;
    }
    private Logger logger = LoggerFactory.getLogger(getClass());
}
