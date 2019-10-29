# Java JCStress tests
Java Concurrency Stress tests.

https://openjdk.java.net/projects/code-tools/jcstress/
## Running tests:
### 1. On a default JVM
```
mvn clean install
java -jar target/jcstress.jar -v
```
### 2. On a specified JVM (for example, on a 32-bit JVM)
```
mvn clean install
"C:\Program Files (x86)\Java\jre1.8.0_221\bin\java.exe" -jar target/jcstress.jar -v
```
### 3. From a specified class or package (regex)
```
mvn clean install
java -jar target/jcstress.jar -t "LongFieldWrite.*" -v
java -jar target/jcstress.jar -t ".*singletons.*" -v
```
### 4. With JVM flags for fastdebug builds (to randomize C2's instruction scheduling)
```
mvn clean install
java -XX:+StressLCM -XX:+StressGCM -XX:-RestrictContended -jar target/jcstress.jar -t ".*singletons.*" -v -f 1
```
