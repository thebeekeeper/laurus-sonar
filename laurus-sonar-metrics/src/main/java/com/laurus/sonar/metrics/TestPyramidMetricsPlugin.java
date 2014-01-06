package com.laurus.sonar.metrics;

import org.sonar.api.SonarPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * User: thebeekeeper
 * Date: 1/1/14
 * Time: 1:31 PM
 */
public class TestPyramidMetricsPlugin extends SonarPlugin {
    public List getExtensions() {
        return Arrays.asList(
                // Definitions
                TestPyramidMetrics.class
        );
    }
}
