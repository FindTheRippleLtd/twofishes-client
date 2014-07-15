twofishes-client
================

java client for Foursquare's Twofishes geocoder https://github.com/foursquare/twofishes

### Install Server
Download and install Twofishes geocoder, following instructions at:

### Run Server
```java -Xms1g -Xmx1g -jar server-assembly-0.82.14.jar --hfile_basepath latest/ --port 5100 --run_http_server true```

### Run tests
```mvn clean install```

### Use the APIs
```it.cybion.geocoder.GeocoderImpl```
