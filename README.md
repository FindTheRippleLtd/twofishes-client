twofishes-client
================

A simple http java client for Foursquare's Twofishes geocoder https://github.com/foursquare/twofishes

### Versions
The current version of the client is developed against the following artifact of the server: 

```
server-assembly-0.84.9.jar
```

this table sums up the compatibilities:

| Twofishes Server  | Twofishes Client  | Release date          |
| ----------------- | ----------------- | --------------------- |
| 0.84.9            | 0.84.9.0          | Mar 19, 2015          |
| 0.82.14           | 0.82.14.0         | not yet, dev in 2014  |

### Installation
Artifacts are not released on public repositories, so you have to build and install it yourself: 

```
$ git clone git@github.com:Cybion-Italy/twofishes-client.git
$ cd twofishes-client
$ mvn clean install
```

Then, import the dependency in your maven project
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
Geocoder geocoderClient = new GeocoderImpl(
	"localhost", 5101,
	ObjectMapperFactory.INSTANCE.getObjectMapper(), 
	HttpClients.createDefault());
```

Dependencies of the geocoder client include: 

* host and http port of the running instance of twofishes server
* a jackson ```org.codehaus.jackson.map.ObjectMapper```, 
already configured with a module that takes care of deserializing twofishes' server responses
* a ```org.apache.http.impl.client.CloseableHttpClient``` http client, 
used to do http GET requests to the server

As a good practice, you should always use a more detailed configuration of the http client, 
explicitly specifying timeouts: 

```
int tenSecondsMsecs = 10000;
RequestConfig requestConfig = RequestConfig.custom()
  .setConnectTimeout(tenSecondsMsecs)
  .setSocketTimeout(tenSecondsMsecs)
  .build();

PoolingHttpClientConnectionManager connManager =
  new PoolingHttpClientConnectionManager();

this.httpClient = HttpClients.custom()
        .setDefaultRequestConfig(requestConfig)
        .setConnectionManager(connManager)
        .build();

this.geocoder = new GeocoderImpl("localhost", 5101,
        ObjectMapperFactory.INSTANCE.getObjectMapper(), httpClient);
```

When something goes wrong at any point, ```GeocoderException extends RuntimeException```s are thrown. 

#### How to run integration tests
There are integration tests to verify the correct functioning of the client against 
a locally running instance of  Twofishes geocoder server. 
They can be useful when testing the client against more recent versions of the server. 

* Download and install Twofishes geocoder (client was developed against ```server-assembly-0.82.14.jar```), 
following instructions at: http://twofishes.net

* Run the server on localhost. You can use the file in ```scripts/server.sh``` to launch it

* Run integration tests
```mvn clean test -P integration```

#### Future improvements
* consider GeocodeRequest.bounds for query - https://github.com/Cybion-Italy/twofishes-client/issues/2
* add implementation of asynchronous APIs
* support deserialization from json of well known binary data 
(see all issues at https://github.com/Cybion-Italy/twofishes-client/issues )
* explicitly state license

#### 
