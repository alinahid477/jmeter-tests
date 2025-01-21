package ww.test.mongotest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mongodb")
public class MongoProperties {
    
    private String uri;
    private String dbname;
    private String collectoinname;

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDbname() {
        return this.dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getCollectoinname() {
        return this.collectoinname;
    }

    public void setCollectoinname(String collectoinname) {
        this.collectoinname = collectoinname;
    }

        
}
