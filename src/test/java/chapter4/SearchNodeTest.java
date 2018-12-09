package chapter4;

import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.driver.v1.*;
import org.neo4j.logging.Log;

import static org.neo4j.driver.v1.Values.parameters;

public class SearchNodeTest {
    private Driver driver;

    @Before
    public void init(){
        try {
            driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("connect to neo4j");


    }

    @After
    public void closeDriver() throws Exception{
        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("close connection failed");
            e.printStackTrace();
        }
        System.out.println("close connection");

    }

    @Ignore
    @Test
    public void broadcastTest1() {
        try(Session session = driver.session()){
            session.run("create (:Greeting{message:'hello,world'});");
        }
        System.out.println("run broadcastTest1");

    }

    @Ignore
    @Test
    public  void broadcastTest2(){
        try(Session session = driver.session()){
            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction transaction) {
                    transaction.run("create (g:Greeting{message: $message})",parameters("message","hello,kids"));
                    return null;
                }
            });
        }
        System.out.println("run broadcastTest2");
    }

    @Test
    public void broadcastTest3(){
        Log log = LogFactory.
        log.debug("123");
        addPerson("Jerry");
    }

    @Test
    public void deletePersonNodeTest(){
        deletePersonNode("Jerry");
    }

    private long addPerson(final String name){
        try (Session session = driver.session()){
            session.writeTransaction(new TransactionWork<Object>() {
                @Override
                public Object execute(Transaction transaction) {
                     createPersonNode(transaction, name);
                    return null;
                }
            });
            return session.readTransaction(new TransactionWork<Long>() {
                @Override
                public Long execute(Transaction transaction) {
                    return matchPersonNode(transaction, name);
                }
            });
        }
    }

    private static Void createPersonNode(Transaction tx, String name) {
        tx.run("CREATE (a:Person{name: $name})", parameters("name", name));
        return null;
    }

    private static long matchPersonNode(Transaction tx, String name){
        StatementResult result = tx.run("Match (a:Person{name: $name}) RETURN id(a)", parameters("name", name));
        return result.single().get(0).asLong();
    }

    private void deletePersonNode(String name){
        try (Session session = driver.session()){
            session.writeTransaction(new TransactionWork<Object>() {
                @Override
                public Object execute(Transaction transaction) {
                    transaction.run("MATCH (a:Person{name: $name}) delete a", parameters("name", name));
                    return null;
                }
            });
        }catch (Exception e){
            e.getStackTrace();
        }
    }


}
