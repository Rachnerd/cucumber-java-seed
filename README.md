# ED 
Automation Tests Behavior Driven Development (BDD)

## Run

### Selenium grid
Download [here](http://selenium-release.storage.googleapis.com/index.html) (I use 3.4)

Run grid
```bash
java -jar selenium-server-standalone-X.Y.Z.jar -role hub
```

### Run N amount of Appium instances.
Prerequisites

Windows: Make sure [Cygwin](https://cygwin.com/install.html) (or any other bash terminal) is installed.

jq (parses nodeconfig.json)
```
sudo dnf install jq (Fedora)
brew install jq (Mac)
install jq package for Cygwin (Windows)
```

xmllint (parses TestNG.xml)
```
install libxml packages for Cygwin (Windows)
```

```bash
./run-appium.sh
```

### Run TestNG.xml
```
InteliJ: Rightclick TestNG.xml in resources and run.
```
##### Proxy

To pass a proxy to the emulators being used, add `-DproxyUrl` to the `VM options` of your IDE.

## Behavior Driven Development
BDD uses a language called Gherkin, which is a syntax that can be understood by all layers of the process 
(development, business etc). It's developed to create a mutual understanding of the behavior of the application.

### BDD Feature test
In BDD a feature is tested by creating scenarios. These scenarios contain steps:
#### Given
Defines a precondition of the test case.
#### When
Defines an action performed on the application.
#### Then 
Asserts the expected result.

#### Examples
Gherkin's file extension is .feature
```gherkin
Feature: Expectations screen
  Scenario: User opens the app for the first time
    Given The app is closed
    When I open the app
    Then I see the expectations screen
```

```gherkin
Feature: Login screen
  Scenario Outline: Regular user logs in with correct credentials

    Given I'm at the login screen
    When I enter my <username> and <password>
    And I tap the login button
    Then I see the timeline-loader screen

    Examples:
      | username   | password |
      | 0131002060 | Test123  |
      | 0132175343 | Test123  |
```
All feature files can be found here:
```
src/test/resources/features
```

## Stack
- Java 8
- Spring
- Cucumber-jvm
- Appium
- JUnit

### Java 8
Language this project is written in.

### Spring
This project uses the dependency injection mechanism of Spring. Spring is usually configured by applicationContext.xml found 
in ./src/test/resources. In this project the spring configuration is split into 2 files because cucumber-spring searches for cucumber.xml.
Cucumber's xml file imports applicationContext.xml.

#### Dependency injection example
Inside the spring configuration (cucumber.xml) is a tag called component-scan. This tag tells Spring where to look for components (injectables).
```xml
<context:component-scan base-package="nl.energiedirect"/>
```

Providing injectables to the framework is done by annotating classes with "Component". 
```java
package nl.energiedirect.screens;

@Component
public class ExpectationsScreen {
    
}
```
ExampleDependency can now be injected in every class within the base-package. 

Injecting a dependency is done by annotating a constructor or instance member with "Autowired". This is similar to
Angular's Inject(), but not limited to the constructor.

Instance member example:
```java
package nl.energiedirect.stepdefinitions;

public class ExpectationsStepDefinition {
    @Autowired
    private ExpectationsScreen expectationsScreen;
}
```

Constructor example:
```java
package nl.energiedirect.stepdefinitions;

public class ExpectationsStepDefinition {
    @Autowired
    public ExampleStepDefinition(ExpectationsScreen expectationsScreen) {
        
    }
}
```

Spring will inject the ExpectationsScreen dependency at runtime.

### Cucumber-jvm
Java 8 implementation of [Cucumber](https://cucumber.io/docs/reference/jvm#java). Brings along some other dependencies to
integrate with the stack:
- cucumber-spring
- cucumber-java8
- cucumber-jvm-deps
- cucumber-junit
- cucumber-reporting

#### Cucumber spring
...

### Appium
Selemium is a webdriver that can interact with web application. Appium is a layer on top of selenium that makes interaction
with iOS and Android apps possible...

### JUnit
...