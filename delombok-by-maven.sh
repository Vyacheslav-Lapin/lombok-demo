./mvnw clean yaml-properties:read-project-properties lombok:delombok lombok:testDelombok

#./mvnw clean \
#antrun:run@delombok-sources \
#antrun:run@delombok-test-sources \
#-Dmaven.compile.classpath=`./mvnw dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1` \
#-Dmaven.test.classpath=`./mvnw dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1` \
#-Dlombok.sourceDirectory=`yq '.lombok.sourceDirectory' maven.yml` \
#-Dlombok.outputDirectory=`yq '.lombok.outputDirectory' maven.yml` \
#-Dlombok.testSourceDirectory=`yq '.lombok.testSourceDirectory' maven.yml` \
#-Dlombok.testOutputDirectory=`yq '.lombok.testOutputDirectory' maven.yml`
