package com.laurus.manualtests;

import com.laurus.sonar.metrics.TestPyramidMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.api.utils.command.Command;
import org.sonar.api.utils.command.CommandExecutor;

import java.util.StringTokenizer;

/**
 * User: thebeekeeper
 * Date: 1/5/14
 * Time: 3:54 PM
 */
public class ManualTestSensor implements Sensor {

    public ManualTestSensor(Settings settings) {
       _settings = settings;
    }

    @Override
    public void analyse(Project project, SensorContext sensorContext) {
        String exeCmd = _settings.getString("laurus.manualtestcmd");
        String query = _settings.getString("laurus.manualtestquery");
        String regex = _settings.getString("laurus.manualtestregex");

        if(exeCmd == null || exeCmd.isEmpty() || regex == null || regex.isEmpty()) {
            logger.info("Manual test command is empty - not looking for manual tests");
            sensorContext.saveMeasure(TestPyramidMetrics.MANUAL_TESTS, 0.0d);
            return;
        }

        String[] queryParts = null;
        if(query != null && query.isEmpty() == false) {
            queryParts = splitQueryString(query);
        }

        Command c = Command.create(exeCmd);
        for(int i = 0 ; i < queryParts.length ; i++) {
            c.addArgument(queryParts[i]);
        }
        TestQueryStreamConsumer queryResults = new TestQueryStreamConsumer(regex);
        ErrorStreamConsumer errorConsumer = new ErrorStreamConsumer();
        logger.info("Executing command: " + c.toCommandLine());
        CommandExecutor.create().execute(c, queryResults, errorConsumer, 300000);
        int manualTestCount = queryResults.getTestCount();
        logger.info("Got " + manualTestCount + " tests from query");

        sensorContext.saveMeasure(TestPyramidMetrics.MANUAL_TESTS, (double)manualTestCount);
    }

    // because ProcessBuilder is so weird about quotes we have to split the query string into
    // multiple values.  Assuming the query comes in as a comma separated list
    private String[] splitQueryString(String query) {
        StringTokenizer tokenizer = new StringTokenizer(query, ",", false);
        String[] rval = new String[tokenizer.countTokens()];
        for(int i = 0 ; i < rval.length ; i++) {
            rval[i] = tokenizer.nextToken().trim();
        }
        return rval;
    }

    @Override
    public boolean shouldExecuteOnProject(Project project) {
        return(project.isRoot());
    }

    private Settings _settings;
    private Logger logger = LoggerFactory.getLogger(getClass());
}
