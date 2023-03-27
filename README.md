# Demo project of Lombok usage
____

Lombok features demo project, created as practical part of Lombok training.

## How to run
```shell
./mvnw
```
According to Maven pom `build/defaultGoal` section, this command is actually an alias for command:
```shell
./mvnw verify spring-boot:run 
```

## How to delombok
*Delombok* is a way to view lombok application result as a code-gen.  
Sources delombok action attached via Maven to `process-sources` life-cycle phase by [ant-run plugin](https://maven.apache.org/plugins/maven-antrun-plugin/). So, for delombok source code, placed in `src/main/java`, you should tap this command in terminal:
```shell
./mvnw clean generate-sources
```
Then you can view delombok result in `target/generated-sources/delomboked` folder.

Test sources delombok action attached to `process-test-sources` phase. So, for delombok sources and tests, placed in `src/main/java` and `src/test/java` respectively, tap this command in terminal:
```shell
./mvnw clean generate-test-sources
```
Then you can view delombok results in `target/generated-sources/delomboked` and `target/generated-test-sources/delomboked` folders respectively.

## How to enter
Then go to http://localhost:8080/swagger-ui.html for swagger-ui 

Use `user` as a login and see logs for automatically-generated password (every start it'll be different).

## Content

  - First example - package `ru.vlapin.demo.lombokdemo.first.example.*`
  - Stable features demo - package `ru.vlapin.demo.lombokdemo.stable.*`
  - Experimental features demo - package `ru.vlapin.demo.lombokdemo.experimental.*`
  - Example of realization of homework №6 of OTUS Java course: `ru.vlapin.demo.lombokdemo.homeworks.O6.*`
  - Example of AOP - `@Loggable` annotation aspect - `ru.vlapin.demo.lombokdemo.common.Loggable`
  - OpenFeign example by using http://jsonplaceholder.typicode.com/ site API
