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
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.Arrays;
import java.util.List;

public final class TestPyramidMetrics implements Metrics {

  public static final Metric PYRAMID_SCORE = new Metric.Builder("pyramid", "Test Pyramid Score", Metric.ValueType.FLOAT)
      .setDescription("Test pyramid score")
      .setDirection(Metric.DIRECTION_BETTER)
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_TESTS)
      .create();

    public static final Metric INTEGRATION_TESTS = new Metric.Builder("integration_tests", "Integration Test Count", Metric.ValueType.INT)
            .setDescription("Integration Test Count")
            .setDirection(Metric.DIRECTION_BETTER)
            .setQualitative(false)
            .setDomain(CoreMetrics.DOMAIN_TESTS)
            .create();

    public static final Metric MANUAL_TESTS = new Metric.Builder("manual_tests", "Manual Test Count", Metric.ValueType.INT)
            .setDescription("Manual Test Count")
            .setDirection(Metric.DIRECTION_BETTER)
            .setQualitative(false)
            .setDomain(CoreMetrics.DOMAIN_TESTS)
            .create();

  // getMetrics() method is defined in the Metrics interface and is used by
  // Sonar to retrieve the list of new metrics
  public List<Metric> getMetrics() {
    return Arrays.asList(PYRAMID_SCORE, INTEGRATION_TESTS, MANUAL_TESTS);
  }
}
