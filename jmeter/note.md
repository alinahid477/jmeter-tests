# jmeter-tets


```
ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
	MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb+srv://user:pass@mongatlas_sample.df0bi.mongodb.net:27017")).applyToConnectionPoolSettings(builder ->
        builder.maxWaitTime(10, SECONDS)
        .maxSize(200).serverApi(serverApi).build();



https://blog.nonstopio.com/mongodb-performance-testing-using-jmeter-f98382844fbc




import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.Arrays;

try {
	String connectionString = "mongodb+srv://user:pass@mongoserver.df0bi.mongodb.net/sample_mflix?retryWrites=true&w=majority&appName=mongoserver";
	ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
	MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(connectionString)).serverApi(serverApi).build();
	MongoClient mongoClient = MongoClients.create(settings);
	MongoDatabase database = mongoClient.getDatabase("sample_mflix");
	MongoCollection<Document> collection = database.getCollection(vars.get("collectionName"));
	Document result = collection.find();
	
	vars.putObject("collection",collection);
	return "Connected to " + vars.get("collectionName");
} catch(Exception e) {
    	SampleResult.setSuccessful(false);
    	SampleResult.setResponseCode("500");
    	SampleResult.setResponseMessage("Exception: "+e);
}


```


jmeter -n -t ww_mongo_test.nogit.jmx -l output.jtl -e -o output/


oc adm policy add-scc-to-user privileged -z scc-admin -n jmeter-test
kubectl exec -n jmeter-test jmeter-container-5cbf4ff6d8-d2nzs -- cat /opt/apache-jmeter-5.6.3/output/statistics.json > statstics.nogit.json