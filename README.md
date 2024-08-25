# Selenium Java Web Test Framework

This project is a robust and scalable Selenium-based web testing framework using Java, TestNG, and Maven. It provides a structured approach to writing and executing automated web tests with built-in reporting capabilities.

## Prerequisites

- Java 11 or higher
- Maven 3.9.8 or higher
- Chrome and/or Firefox browsers installed

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/selenium-java-web.git
   ```
2. Navigate to the project directory:
   ```
   cd selenium-java-web
   ```
3. Install dependencies:
   ```
   mvn clean install
   ```

## Project Structure

```
selenium-java-web/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── framework/
│   │   │               ├── config/
│   │   │               ├── driver/
│   │   │               ├── listeners/
│   │   │               ├── reporting/
│   │   │               └── utils/
│   │   └── resources/
│   │       └── config.properties
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── tests/
│       └── resources/
│           └── testng.xml
├── .dockerignore
├── Dockerfile
├── pom.xml
└── README.md
```

## Running Tests

There are multiple ways to run the tests in this framework:

### Using Maven

1. Run all tests using the default TestNG XML file:
   ```
   mvn clean test
   ```

2. Run tests using a specific TestNG XML file:
   ```
   mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
   ```

3. Run a specific test class:
   ```
   mvn clean test -Dtest=com.example.tests.SampleTest
   ```

4. Run a specific test method:
   ```
   mvn clean test -Dtest=com.example.tests.SampleTest#testPageTitle
   ```

### Using TestNG

1. Run all tests in the default TestNG XML file:
   ```
   java -cp "target/test-classes:target/classes:path/to/testng.jar:path/to/dependencies/*" org.testng.TestNG testng.xml
   ```

2. Run a specific test class:
   ```
   java -cp "target/test-classes:target/classes:path/to/testng.jar:path/to/dependencies/*" org.testng.TestNG -testclass com.example.tests.SampleTest
   ```

3. Run specific test methods:
   ```
   java -cp "target/test-classes:target/classes:path/to/testng.jar:path/to/dependencies/*" org.testng.TestNG -testclass com.example.tests.SampleTest -methods testPageTitle,testHeadingText
   ```

Note: Replace "path/to/testng.jar" and "path/to/dependencies/*" with the actual paths to your TestNG JAR file and other dependencies.

## Configuration

The framework can be configured using the `config.properties` file located in `src/main/resources/`. You can set the following properties:

- `browser`: Choose between `chrome` or `firefox`

The base URL for tests is now passed as a parameter in the TestNG XML file, allowing for greater flexibility in running tests on different environments or websites.

## Built With

- [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/) - Web browser automation
- [TestNG](https://testng.org/doc/) - Testing framework
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) - Automated driver management
- [ExtentReports](http://www.extentreports.com/) - Reporting library

## Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.