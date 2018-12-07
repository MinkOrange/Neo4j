package chapter4;

import org.junit.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class ManualFullTextIndexTest {
    private final Driver driver = GraphDatabase.driver("bolt://localhost:7687",AuthTokens.basic("neo4j","123456"));


    @Test
    public void HelloWorldExampleTest() throws Exception{
        /*try ( HelloWorldExample greeter = new HelloWorldExample( "bolt://localhost:7687", "neo4j", "123456" ) )
        {
            greeter.printGreeting( "hello, world" );
        }*/



    }
}
