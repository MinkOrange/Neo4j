package chapter4;


import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;



public class SocialNetworkTest {
    private static GraphDatabaseService db;
    private static SocialNetworkQueries queries;


    @BeforeClass
    public static void init(){

    }

    @Test
    public void shouldReturnShortPathBetweenTwoFriends() throws Exception
    {
        Result result =queries.distance("Ben","Mike");
    }


    /*public GraphDatabaseService createDatabase(){
        //create nodes
        String createGraph = "CREATE\n" +
                "(ben:User{name:'Ben'}),\n" +
                "(arnold:User{name:'Arnold'}),\n" +
                "(charlie:User{name:'Charlie'}),\n" +
                "(gordon:User{name:'Gordon'}),\n" +
                "(lucy:User{name:'Lucy'}),\n" +
                "(emily:User{name:'Emily'}),\n" +
                "(sarah:User{name:'Sarah'}),\n" +
                "(kate:User{name:'Kate'}),\n" +
                "(mike:User{name:'Mike'}),\n" +
                "(paula:User{name:'Paula'}),\n" +
                "(ben)-[:FRIEND]->(charlie),\n" +
                "(charlie)-[:FRIEND]->(lucy),\n" +
                "(lucy)-[:FRIEND]->(sarah),\n" +
                "(sarah)-[:FRIEND]->(mike),\n" +
                "(arnold)-[:FRIEND]->(gordon),\n" +
                "(gordon)-[:FRIEND]->(emily),\n" +
                "(emily)-[:FRIEND]->(kate),\n" +
                "(kate)-[:FRIEND]->(paula)";

        String createIndex = "CREATE INDEX ON :User(name)";

        GraphDatabaseService db;

        return db;
    }*/
}
