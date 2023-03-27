./mvnw clean

mkdir -p ./target/generated-sources/delomboked
java -cp `./mvnw dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1` \
lombok.launch.Main delombok ./src/main/java \
-d ./target/generated-sources/delomboked

mkdir -p ./target/generated-test-sources/delomboked
java -cp `./mvnw dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1`:./target/generated-sources/delomboked \
lombok.launch.Main delombok ./src/test/java \
-d ./target/generated-test-sources/delomboked
