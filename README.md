twofishes-client
================

java client for Foursquare's Twofishes geocoder https://github.com/foursquare/twofishes

### Supported server version
The current version of the client is developed against the following artifact of the server: 
server-assembly-0.82.14.jar

### Installation
Artifacts are not released on public repositories, so you have to build and install it yourself: 

* Checkout ```git clone git@github.com:Cybion-Italy/twofishes-client.git``` 
* ```mvn clean install```
* import the dependency in your maven project
```
<dependency>
    <groupId>it.cybion.geocoder</groupId>
    <artifactId>twofishes-client</artifactId>
    <version>1.0-SNAPSHOT</version>
<dependency>
```

#### The APIs
There is a single class with a single method: 
```
interface Geocoder {
	GeocodeResponse geocode(GeocodeRequest request); 
}
```

And the corresponding http implementation ```GeocoderImpl```. 

To build an instance of GeocoderImpl, see the following snippet: 

```
Geocoder geocoder = new GeocoderImpl("localhost", 5101,
                ObjectMapperFactory.INSTANCE.getObjectMapper(), 
                HttpClients.createDefault());
```

Dependencies include: 

* host and http port of the running instance of twofishes server
* a jackson object mapper with a module that takes care of deserializing twofishes' server responses
* a ```org.apache.http.impl.client.CloseableHttpClient``` http client, 
used to do http GET requests to the server

When something goes wrong at any point, unchecked exceptions are thrown. 

#### How to run integration tests
There are integration tests to verify correct functioning of the client against 
a running instance of  Twofishes geocoder server. They can be useful when testing the client 
against more recent versions of the server. 

* Download and install Twofishes geocoder, following instructions at: http://twofishes.net

* Run the server on localhost. You can use the file in ```scripts/server.sh``` to launch an instance

* Run integration tests
```mvn clean test -P integration```

#### Future improvements
* consider GeocodeRequest.bounds for query - https://github.com/Cybion-Italy/twofishes-client/issues/2
* add implementation of asynchronous APIs
* support deserialization from json of well known binary data 
(see all issues at https://github.com/Cybion-Italy/twofishes-client/issues )
