/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author dmitriy
 */

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "html:target/cucumber-html-report" })
public class CucumberTestRunner {   
}
