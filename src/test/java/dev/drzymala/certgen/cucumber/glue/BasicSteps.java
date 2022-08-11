package dev.drzymala.certgen.cucumber.glue;

import io.cucumber.java.en.Given;


public class BasicSteps {

    @Given("user navigated to main page")
    public void userNavigatedToMainPage() {

        System.out.println("LOL");
    }
}
