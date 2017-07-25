#!/bin/bash

testNGLocation="src/test/resources/TestNG.xml"
resources="src/test/resources/"
extractValueRegex='value="\K[^"\047]+(?=["\047])'
nodeConfigLocations=($(xmllint --xpath '//suite/test/parameter[@name="nodeConfig"]/@value' ${testNGLocation} | grep -oP ${extractValueRegex}))

function createAppiumCmd {
    configJSONFileLocation=${resources}$1
    port=$(jq '.configuration.port' ${configJSONFileLocation})
    bootStrapPort=$(jq '.configuration.bootstrapPort' ${configJSONFileLocation})
    echo "appium -p $port -bp $bootStrapPort --nodeconfig ${configJSONFileLocation}"
}

appiumInstancesCmd=$(createAppiumCmd ${nodeConfigLocations[0]})
for (( i=1; i<${#nodeConfigLocations[@]}; i++ ));
do
    appiumInstancesCmd="${appiumInstancesCmd} & $(createAppiumCmd ${nodeConfigLocations[$i]})"
done

eval $appiumInstancesCmd
