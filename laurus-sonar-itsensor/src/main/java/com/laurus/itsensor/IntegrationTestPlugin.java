package com.laurus.itsensor;

import org.sonar.api.SonarPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * User: thebeekeeper
 * Date: 1/5/14
 * Time: 3:57 PM
 */
public class IntegrationTestPlugin extends SonarPlugin {
    public List getExtensions() {
        return Arrays.asList(
            IntegrationTestSensor.class
        );
    }
}
