package chapter4;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;

import java.util.HashMap;
import java.util.Map;

public class SocialNetworkQueries {
    private final GraphDatabaseService db;

    public SocialNetworkQueries(GraphDatabaseService db) {
        this.db = db;
    }

    public Result distance(String firstUser, String secondUser){
        String query = "MATCH (first:User{name:{firstUser}}),\n" +
                "(second:User{name:{secondUser}})\n" +
                "MATCH p = shortestPath((first-[*..4]-(second))\n" +
                "RETURN length(p) AS distance";

        Map<String, Object> params = new HashMap<>();
        params.put("firstUser", firstUser);
        params.put("secondUser", secondUser);
        return db.execute(query,params);
    }
}
