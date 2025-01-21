package ww.test.mongotest.POJO;

public class ResponseObject {
    private String dbName;
    private String collectionName;
    private KeyValue connectionTest;
    private KeyValue countTest;
    private KeyValue queryTest;

    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public KeyValue getConnectionTest() {
        return this.connectionTest;
    }

    public void setConnectionTest(KeyValue connectionTest) {
        this.connectionTest = connectionTest;
    }

    public KeyValue getCountTest() {
        return this.countTest;
    }

    public void setCountTest(KeyValue countTest) {
        this.countTest = countTest;
    }

    public KeyValue getQueryTest() {
        return this.queryTest;
    }

    public void setQueryTest(KeyValue queryTest) {
        this.queryTest = queryTest;
    }

}
