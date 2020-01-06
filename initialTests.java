import edu.uom.currencymanager.CurrencyManager;
import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.ExchangeRate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class initialTests {

    CurrencyDatabase CDB;
    Currency currencyValue;

    @Before
    public void setup() throws Exception {
        CDB = new CurrencyDatabase();
    }

    @After
    public void teardown() {
        CDB = null;
    }

    @Test
    public void testAddACurrency() throws Exception{
        //Setup
        List<Currency> original = CDB.getCurrencies();
        int original_size_list = original.size();

        //exercise
        currencyValue = new Currency("BGN", "Bulgarian Lev", true);
        CDB.addCurrency(currencyValue);
        int size_after_adding = original.size();

        //Verify
        assertEquals(original_size_list + 1, size_after_adding);
    }

    @Test
    public void testDeleteACurrency() throws Exception{
        //Setup
        List<Currency> original = CDB.getCurrencies();
        int original_size_list = original.size();

        //exercise
        currencyValue = new Currency("BGN", "Bulgarian Lev", true);
        CDB.deleteCurrency("BGN");
        int size_after_deleting = original.size();

        //Verify
        assertEquals(original_size_list-1, size_after_deleting);
    }

    @Test
    public void testingExchangeRate() throws Exception {
        //Setup
        Currency secondCurrency = new Currency("EUR", "Euro", true);
        Currency newCurrency = new Currency("USD", "US Dollar", true);

        //Exercise
        ExchangeRate output = CDB.getExchangeRate(secondCurrency.code, newCurrency.code);
        output.timeLastChecked = output.timeLastChecked - 6000000;
        ExchangeRate output2 = CDB.getExchangeRate(secondCurrency.code, newCurrency.code);
        //Verify
        Assert.assertFalse(output.rate == output2.rate);
    }

    @Test
    public void testGetMajorCurrencies() throws Exception {
        //Setup
        List<Currency> result = CDB.getCurrencies();
        int original_size_list = result.size();

        //Exercise
        CDB.getMajorCurrencies();

        int size_after_adding = result.size();
        //Verify
        assertEquals(original_size_list, size_after_adding);
    }

    @Test
    public void testing_valid_read() throws Exception {
        //setup
        String file = "target" + File.separator + "classes" + File.separator + "currencies.txt";

        //Exercise
        CDB.init(file);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String firstLine = reader.readLine();
        //Verify
        assertEquals("code,name,major",firstLine);
    }

}
