import test.currencies.Currency;
import test.currencies.CurrencyDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class persist_test {

    Currency currencyValue;
    CurrencyDatabase CDB;

    @Before
    public void setup() throws Exception {
        CDB = new CurrencyDatabase();
    }

    @After
    public void teardown() {
        CDB = null;
    }

    @Test
    public void testingPersist () throws Exception {

        //Setup
        String newCreatedFile = "target" + File.separator + "classes" + File.separator + "file_empty.txt";
        BufferedReader reader = new BufferedReader(new FileReader(newCreatedFile));

        //exercise
        CDB.persist(newCreatedFile);


        String text = reader.readLine();
        //verify
        assertEquals("code,name,major", text);
    }
}