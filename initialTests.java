import org.junit.*;
import org.junit.rules.ExpectedException;
import test.currencies.Currency;
import test.currencies.CurrencyDatabase;
import test.currencies.ExchangeRate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class initialTests {

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
    public void testGetMajorCurrencies(){
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

    ///////////////////////////////////

    @Rule
    public ExpectedException exception= ExpectedException.none();


    @Test
    public void testing_commas(){
        String res = "cdc , ss , yes";
        int output = CDB.numofCommas(res);
        assertEquals(2, output);
    }

    @Test
    public void testing_when_having_2_commas() throws Exception {
        String res = "cdc , ss, yes";
        int commas = CDB.numofCommas(res);
        String output = CDB.commasx2(res,commas);
        assertEquals("cdc , ss, yes", output);
    }


    @Test
    public void reading_invalid_file_due_to_commas() throws Exception {
        exception.expect(Exception.class);
        String file = "target" + File.separator + "classes" + File.separator + "currencieswithout2commas.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String nextLine ="";
        int numCommas = 0;
        while (reader.ready()) {
            nextLine = reader.readLine();
            numCommas = CDB.numofCommas(nextLine);
        }
        exception.expectMessage("Parsing error: expected two commas in line " + CDB.commasx2(nextLine,numCommas));

        CDB.init(file);
    }


    @Test
    public void testing_check_currency_curr_exists(){
        //Setup
        Currency currency = new Currency("EUR", "Euro", true);
        //Exercise
        String output = CDB.Check_currency(currency);
        //Verify
        assertEquals("Already Exists",output);

    }

    @Test
    public void testing_check_currency_not_exists(){
        //Setup
        Currency currency = new Currency("ABC", "Currency123", false);
        //Exercise
        String output = CDB.Check_currency(currency);
        //Verify
        assertEquals("Complete",output);


    }

    @Test
    public void testing_check_currency_invalid_currency(){
        //Setup
        Currency currency = new Currency("AB", "Currency123", false);
        //Exercise
        String output = CDB.Check_currency(currency);
        //Verify
        assertEquals("Invalid currency code detected: " + currency.code, output);


    }

    @Test
    public void testing_check_currency_invalid_currency_Code_more_than_3(){
        //Setup
        Currency currency = new Currency("ABCDE", "Currency123", false);
        //Exercise
        String output = CDB.Check_currency(currency);
        //Verify
        assertEquals("Invalid currency code detected: " + currency.code, output);


    }

}