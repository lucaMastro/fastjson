package param_tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*  nella directory target/test-classes/ ci sono i vari packages e le risorse. Il class loader parte da
    target/test-classes/param_tests perché è dove è definita la classe Support. Il file di configurazione
    viene messo nella directory superiore.
*/

public class Support {

    public static String getProperty(String key) throws IOException {
        Properties prop = new Properties();
        String path = "../parameters.properties";
        InputStream stream = Support.class.getResourceAsStream(path);
        prop.load(stream);
        return prop.getProperty(key);
    }

    public static String stripTrailingL(String str) {
        return str.endsWith("L") ? str.substring(0, str.length() - 1) : str;
    }


    public static void main(String[] args){
        Long l = Long.valueOf("1L");
        System.out.println(l);
    }
}

