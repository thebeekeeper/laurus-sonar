package com.laurus.sonar.testpyramid;

import com.laurus.sonar.testpyramid.ui.TestPyramidWidget;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * This class is the entry point for all extensions
 */
@Properties({
  @Property(
    key = TestPyramidPlugin.MY_PROPERTY,
    name = "Plugin Property",
    description = "A property for the plugin",
    defaultValue = "Hello World!")})
public final class TestPyramidPlugin extends SonarPlugin {

  public static final String MY_PROPERTY = "sonar.example.myproperty";

  // This is where you're going to declare all your Sonar extensions
  public List getExtensions() {
    return Arrays.asList(
        // Batch
        TestPyramidDecorator.class,

        // UI
        TestPyramidWidget.class);
  }
}
