# Java JCStress tests
Java Concurrent Stress tests.
## Running tests:
### 1. On default JVM
```
mvn clean install
java -jar target/jcstress.jar
```
### 2. On specific JVM
```
mvn clean install
"C:\Program Files (x86)\Java\jre1.8.0_221\bin\java.exe" -jar target/jcstress.jar
```
### 3. Run specific test
```
mvn clean install
java -jar target/jcstress.jar -t "LongFieldWrite*"
```
