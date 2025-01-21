package ww.test.mongotest.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ww.test.mongotest.MongoProperties;
import ww.test.mongotest.POJO.KeyValue;
import ww.test.mongotest.POJO.ResponseObject;

@RestController
@RequestMapping("/test")
public class PerformTest {
    
    @Autowired
    private MongoProperties mongoProperties;


    @GetMapping("/mongo")
    public ResponseObject doTest() {
        
        ResponseObject ro = new ResponseObject();
        ro.setDbName(mongoProperties.getDbname());
        ro.setCollectionName(mongoProperties.getCollectoinname());

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        
        // Create a new client and connect to the server
        
        try {
            long startTime = System.nanoTime();
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoProperties.getUri()))
                .serverApi(serverApi)
                .build();
            MongoClient mongoClient = MongoClients.create(settings);
            // Send a ping to confirm a successful connection
            MongoDatabase database = mongoClient.getDatabase(mongoProperties.getDbname());
            MongoCollection<Document> collection = database.getCollection(mongoProperties.getCollectoinname());
            long endTime = System.nanoTime();
            long executionTime1 = (endTime - startTime) / 1000000;
            System.out.println("Connected to collection: "+ mongoProperties.getCollectoinname() + " in " +executionTime1+ "ms");
            ro.setConnectionTest(new KeyValue() {{ 
                setKey("Connected to collection: "+ mongoProperties.getCollectoinname());
                setValue(executionTime1 + "ms");
            }});


            startTime = System.nanoTime();
            long docCount = collection.countDocuments();
            endTime = System.nanoTime();
            long executionTime2 = (endTime - startTime) / 1000000;
            System.out.println("Count total of collection("+mongoProperties.getCollectoinname()+"): " + docCount +"; Completed in " + executionTime2 + "ms");
            ro.setCountTest(new KeyValue() {{ 
                setKey("Count total of collection("+mongoProperties.getCollectoinname()+"): " + docCount);
                setValue(executionTime2 + "ms");
            }});


            Random rand = new Random();
            List<Document> claim = new ArrayList<Document>();
            startTime = System.nanoTime();
            List<Document> claims = collection.find().into(claim);
            endTime = System.nanoTime();
            long executionTime3 = (endTime - startTime) / 1000000;
            String loadedDocument = claims.get(rand.nextInt(claims.size())).toJson();
	        System.out.println("Loaded all documents. Randomly showing document: " + loadedDocument + "; Completed in:" + executionTime3 + "ms");
            ro.setQueryTest(new KeyValue() {{ 
                setKey("Loaded all documents. Randomly showing document: " + loadedDocument);
                setValue(executionTime3 + "ms");
            }});
            return ro;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ro;
    }
}
