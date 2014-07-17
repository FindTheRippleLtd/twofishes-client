package it.cybion.geocoder;

import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.requests.ResponseIncludes;
import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.responses.GeocodeResponse;
import it.cybion.geocoder.responses.Interpretation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderPerf extends GeocoderImplProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocoderPerf.class);

    private List<String> someLocations;

    @BeforeClass
    public void setUpDataset() throws Exception {

        final InputStream resourceAsStream = this.getClass().getResourceAsStream(
                "/100-locations-mixed.txt");

        final InputStreamReader in = new InputStreamReader(resourceAsStream);
        final BufferedReader r = new BufferedReader(in);

        someLocations = new ArrayList<String>();

        String line;

        while ((line = r.readLine()) != null) {
            someLocations.add(line);
        }

        resourceAsStream.close();
        assertTrue(someLocations.size() > 0);

    }

    @Test
    public void givenDatasetShouldTestPerformances() throws Exception {

        int missedProvinceNames = 0;
        int missedCountryNames = 0;

        for (final String location : this.someLocations) {

            final GeocodeRequest geocodeRequest = new GeocodeRequest.GeocodeRequestBuilder().query(
                    location).addWoeHint(YahooWoeType.ADMIN2).addResponseInclude(
                    ResponseIncludes.PARENTS).lang("en").build();

            final GeocodeResponse geocode = this.geocoder.geocode(geocodeRequest);
            final List<Interpretation> interpretations = geocode.getInterpretations();
            LOGGER.info("'" + location + "' '" + geocode + "'");

            //TODO re-add logic to count performances
        }
    }

    @Test
    public void givenSomeClientsRunningQueriesFromTheDatasetShouldPrintExecutionTimes()
            throws Exception {

        final int clientAmount = 4;
        final int perClientQueryAmount = 1;

        final ExecutorService executorService = Executors.newFixedThreadPool(clientAmount);

        for (int j = 0; j < clientAmount; j++) {
            executorService.submit(new TwoFishesClient(this.geocoder, perClientQueryAmount,
                    this.someLocations));
        }

        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdown();

    }

    private static class TwoFishesClient implements Runnable {

        private final Geocoder geocoder;

        private final int queriesAmount;
        private List<String> someLocations;

        public TwoFishesClient(final Geocoder geocoder, final int queriesAmount,
                final List<String> someLocations) {

            this.geocoder = geocoder;
            this.queriesAmount = queriesAmount;
            this.someLocations = someLocations;
        }

        @Override
        public void run() {

            for (int i = 0; i < queriesAmount; i++) {

                final Random randomGenerator = new Random();
                int randomIndex = randomGenerator.nextInt() % this.someLocations.size();
                if (randomIndex < 0 ) {
                    randomIndex = randomIndex * -1;
                }

                final String query = this.someLocations.get(randomIndex);

                final GeocodeRequest request = new GeocodeRequest.GeocodeRequestBuilder()
                        .query(query)
                        .lang("en")
                        .addWoeHint(YahooWoeType.ADMIN2)
                        .addResponseInclude(ResponseIncludes.PARENTS)
                        .build();

                long startTime = System.nanoTime();
                this.geocoder.geocode(request);
                final long duration = System.nanoTime() - startTime;
                LOGGER.info("query '" + query + "' duration: '" + duration + "' nanosecs");
            }
        }
    }
}
