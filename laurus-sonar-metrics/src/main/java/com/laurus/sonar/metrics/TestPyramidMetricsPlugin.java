package com.laurus.sonar.metrics;

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
