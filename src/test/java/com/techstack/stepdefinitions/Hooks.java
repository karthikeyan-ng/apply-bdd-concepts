package com.techstack.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class Hooks {

    @BeforeStep
    public void doSomethingBeforeStep(Scenario scenario){

    }

    @AfterStep
    public void doSomethingAfterStep(Scenario scenario){
    }

    @Before("@AddPlace")
    public void beforeScenarioExecute() {

        /**
         * Write a precondition logic here before run your actual scenario
         * Example: some preset | value to be available in the scenario
         */


    }

    @After("@AddPlace")
    public void afterScenarioExecute() {

        /**
         * Write a post condition logic here after ran your actual scenario
         * Example: some cleanup | value to be use for another process or report
         */

    }
}
