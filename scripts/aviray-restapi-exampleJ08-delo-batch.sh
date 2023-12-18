echo "******** Starting Example Java8 Delo Batch *********************************"

#export PATH=.:/opt/ibm/java-x86_64-60/jre/bin:/opt/ibm/java-x86_64-60/jre/lib:$PATH
#export PATH=.:/c/ProgFiles/Java/jdk-11.0.9/bin:$PATH
export PATH=.:/usr/lib/jvm/java-8-openjdk-amd64/bin:$PATH

echo "PATH: $PATH "
echo "Attempting to run Example Java8 Delo Batch"
echo "********** Example Java8 Delo Batch is running ************************************"

java -XX:ParallelGCThreads=2 -XX:+UseG1GC -XX:+UseStringDeduplication -XX:MaxRAMPercentage=80.0 -Djava.security.egd=file:/dev/./urandom -Dserver.port=12080 -Dspring.application.name=exampleJ08DeloWeb -Dspring.profiles.active=batch -Dspring.cloud.consul.config.enabled=false -Dmanagement.health.consul.enabled=false -Dservice.log.location=/logs/exampleJ08DeloWeb -Dlogging.config=/applications/common/config/exampleJ08DeloWeb/logback-spring.xml  -Dspring.config.location=/applications/common/config/exampleJ08DeloWeb/application.yml,/applications/common/security/exampleJ08DeloWeb/application.yml -jar /applications/common/bin/exampleJ08DeloWeb/aviray-restapi-exampleJ08-delo-batch.jar
echo "********** Example Java8 Delo Batch has stopped ************************************"
