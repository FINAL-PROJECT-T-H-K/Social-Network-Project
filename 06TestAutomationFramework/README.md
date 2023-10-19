**Instructions**
1. Clone repository
2. Open `TestAutomationFramework[test-automation-framework]` as a IntelliJ IDEA Project
3. Build.

**Running Instructions API**
```sh
(Preconditions) : Database server and WEare project must be running.

1. API Tests are located under api.socialnetwork.tests
2. To run API tests suite right click on api-testsuite.xml file and click run.
3. Html report will be generated under test-output file. 
```
**Running Instructions Selenium UI Tests**
(Preconditions) : Database server and WEare project must be running.

1. UI Tests are located under ui.socialnetwork.tests
2. To run UI tests suite navigate to your cloned repository in your local machine 
"your path" + T-H-K\TestAutomationFramework\seleniumRunBat.bat"
3. Double-click on seleniumRunBat.bat file and tests will start running.
4. Report will be generated in T-H-K\TestAutomationFramework\target\site\surefire-report.html

**Running Instructions API Security Test**
(Preconditions) : 
Database server and WEare project must be running.
ZAP must be installed in the local machine and started with active session.(click "No" on pop-up)
Change API key in the class ZAPSecurityTest, paste the one from your ZAP.

1. Right click on ZAPSecurityTest class and run.
2. Html report will be generated as weare-zap-report.html in your project.
3. Right click on the report.

