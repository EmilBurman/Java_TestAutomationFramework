# Test automation framework
This respository is a simple template project that allows for API and frontend testing. Either executed through the Junit plattform, or through Cucumber based features.

It's based on:
 - Gradle 
 - Junit 5 
 - Selenide
 - Cucumber
  
It is possible to split this project into a pure frontend or a pure API testing template.The project also supports Cucumber and BDD, but it is optional and the other parts of the project can be used without the BDD integration.

## Getting Started
The project does not have any external dependencies, it is simply a collection of methods and classes to get a test automation framework of the ground. 

Gradle and java 8 is expected though. Download the repo and check the underlying chapters for more info.

## Running the tests

Tests can run inside of IntelliJ, but can also be run through gradle tasks. See build.gradle for more information regarding the tasks.
All tests can be executed through:
- gradle testWithTag -Ptestwith.tag=[NAME OF YOUR TAG HERE]

Its important to understand that the tags are specific, and can be found in either utils/tags for junit tags, or in the features themselves for Cucumber and BDD tests.

It it also possible to use predefined tasks specified for the types of testing the framework offers:
- gradle testAllFrontEnd/testAllAPI/testAllBDD

To run a complete test suite simply type gradle test into your terminal.

## Built With

* See gradle.properties or build.gradle for an overview of the different libraries used for the framework.

## Authors

* **Emil Burman** - *Initial work* - [Github](https://github.com/EmilBurman)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
