# Test automation framework
This respository is a simple template project that allows for API and frontend testing. 

It's based on:
 - Gradle 
 - Junit 5 
 - Selenide.
  
It is possible to split this project into a pure frontend or a pure API testing template.

## Getting Started
The project does not have any external dependencies, it is simply a collection of methods and classes to get a test automation framework of the ground. 
Gradle and java 8 is expected though. Download the repo and check the underlying chapters for more info.

## Running the tests

Tests can run inside of Intellij, but can also be run through gradle tasks. See build.gradle for more information regarding the tasks.
A short overview of the three example tasks are:
- All API tests
- All Frontend tests
- All tests in general

To run a complete test suite simply type gradle test into your terminal.

## Built With

* See gradle.properties or build.gradle for an overview of the different libraries used for the framework.

## Authors

* **Emil Burman** - *Initial work* - [Github](https://github.com/EmilBurman)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
