# ED 
Automation Tests Behavior Driven Development (BDD)

## Run wrapper
```
(linux/mac)
./gradlew
(windows)
./gradlew.bat
```

## Install (optional if using wrapper)
Deps: [Gradle](https://gradle.org/install/)

```bash
gradle clean install
```

## API
### Host
Base url of the site.
```bash
-Dhost=http://localhost:4200/#/
-Dhost=https://apps-tst.essent.nl/agent-cockpit/#/
```

### BrowserName
Determines what browser will run the tests.
```bash
-DbrowserName=headless (default)
-DbrowserName=chrome
-DbrowserName=firefox
-DbrowserName=ie (Not working yet)
```

### UseTestData
By default the test scenarios use some unique accounts without real ids like {id: "household"}. This flag translates all
mock data to test data found in: [test-data.json](src/test/resources/test-data.json). This flag also activates the authentication flow.
```bash
-DuseTestData=true
```

### Groups
Without specifying a group TestNG will run all features. Features are categorized in groups that consists of tests that
have something in common. 
```bash
-Dgroups=budget-bill
-Dgroups=budget-bill,identify-customer-happy
```

### Tests not running?
```
--rerun-tasks
```

### Run Examples
Change gradle for ./gradlew/./gradlew.bat if using the wrapper.
```bash
# Headless mocked
gradle test --rerun-tasks -Dhost=http://localhost:4200/#/

# Chrome mocked only happy flow
gradle test --rerun-tasks -DbrowserName=chrome -Dhost=http://localhost:4200/#/ -Dgroups=identify-customer-happy

# Headless backend
gradle test --rerun-tasks -Dhost=https://apps-tst.essent.nl/agent-cockpit/#/ -DuseTestData=true

# Headless backend chrome budget-bill
gradle test --rerun-tasks -DbrowserName=chrome -Dhost=https://apps-tst.essent.nl/agent-cockpit/#/ -DuseTestData=true -Dgroups=budget-bill
```

