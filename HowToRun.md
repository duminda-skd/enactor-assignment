## Client
-----------

### should be in enactor-assessment/travel-server

```
mvn clean package

mvn package cargo:run
```

```
instead of `mvn package carge:run` you can take the war file from target directory (created from the earlier command) and deploy it **manually** in tomcat server webapps directory.
```

### should be in enactor-assessment/travel-client

```
mvn clean package

java -jar target/travel-client-jar-with-dependencies.jar
```

