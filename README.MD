# Business trip calculator
## How to set up environment on Windows
1. Install JDK (Java 11 minimum)
2. Set up JAVA_HOME environment variable
3. Install maven
4. Execute command ``` mvn package ``` in BTCalculator\btcalculator-back. It will build application and run tests
5. Download Apache Tomcat here https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.12/bin/apache-tomcat-10.1.12.zip
6. Extract archive in some folder, for example C:\Tomcat 
7. Set up CATALINA_HOME environment variable to pathToTomcat\apache-tomcat-10.1.12
8. Change docBase in file _BTCalculator\btcalculator-back\additionalFiles\ROOT.xml_ in according to your location where you cloned this repository (it should point to btcalculator.war file)
9. Copy file ROOT.xml from _BTCalculator\btcalculator-back\additionalFiles_ to _%CATALINA_HOME%\conf\Catalina\localhost_ (create directories if they don't exist)
10. Rename or delete _%CATALINA_HOME%\webapps\ROOT_ directory
11. Run tomcat server by command ``` "%CATALINA_HOME%\bin\startup.bat" ```
12. After that the application will be available on address http://localhost:8080 with pre-built version of frontend which is in BTCalculator\btcalculator-back\src\main\webapp
13. To build and run frontend you need to install Node.js. Download page: https://nodejs.org/en/download
14. Execute following commands to build and run frontend:
        ```
        npm install
        npm start
        ```
15. Frontend will be available by address http://localhost:3000, and it will work with backend if it hosted on http://localhost:8080
16. To make optimized build you can execute ``` npm run build ```. It will be in _build_ directory