package com.laurus.sonar.testpyramid.ui;

import org.sonar.api.web.*;

/**
 * User: thebeekeeper
 * Date: 1/1/14
 * Time: 1:23 PM
 */

@UserRole(UserRole.USER)
@Description("Test Pyramid Widget")
@WidgetCategory("Tests")
/*@WidgetProperties({
  @WidgetProperty(key = "param1",
    description = "This is a mandatory parameter",
    optional = false
  ),
  @WidgetProperty(key = "max",
    description = "max threshold",
    type = WidgetPropertyType.INTEGER,
    defaultValue = "80"
  ),
  @WidgetProperty(key = "param2",
    description = "This is an optional parameter"
  ),
  @WidgetProperty(key = "floatprop",
    description = "test description"
  )
})*/
public class TestPyramidWidget extends AbstractRubyTemplate implements RubyRailsWidget {
    @Override
    protected String getTemplatePath() {
        //return "/pyramid/pyramid_widget.html.erb";
        return "E:\\temp\\sonar-examples-master\\plugins\\sonar-reference-plugin\\laurus-sonar-testpyramid\\src\\main\\resources\\pyramid\\pyramid_widget.html.erb";
    }

    @Override
    public String getId() {
        return "pyramid";
    }

    @Override
    public String getTitle() {
        return "Test Pyramid";
    }
}
