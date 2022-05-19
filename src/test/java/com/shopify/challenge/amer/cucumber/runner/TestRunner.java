package com.shopify.challenge.amer.cucumber.runner;


//import cucumber.junit.Cucumber;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)


//@CucumberOptions(
//        features= {"src/test/resources/features"},
//        glue= {"com.amer.cucumber.runner.StepDefinition.java"},
////        glue= {"step/StepDefinition"},
//        plugin = { "pretty", "html:target/cucumber-reports" },
//        tags= "@smoke1"
//)
import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-reports/Cucumber.html")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.shopify.challenge.amer.cucumber.runner")
@Tag("@smoke1")
public  class  TestRunner  {

}

