import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        kaffeeBestellen("http://beans.itcarlow.ie/prices.html", 230, 234);

        kaffeeBestellen("http://beans.itcarlow.ie/prices-loyalty.html", 247, 251);
    }

    private static void kaffeeBestellen(String url, int offset, int end) {
        double preis = getPreis(url, offset, end);
        if(preis < 4.76)
            System.out.println(String.valueOf(preis));
        else {
            System.out.println("Der Kaffee ist im Moment zu teuer.");
            String eingabe = input("Kaffee trotzdem kaufen? (J/N)");
            if(eingabe.equals("J"))
                System.out.println("Der Preis beträgt: " + String.valueOf(preis));
        }
    }

    private static double getPreis(String url, int offset, int end)
    {
        String source = open_url(url);
        source = source.substring(offset, end);
        return Double.parseDouble(source);
    }

    private static String input(String ausgabe) {
        System.out.println(ausgabe);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private static String open_url(String u ) {
        System.out.println("Connecting to website...");
        String result = "";
        String line = null;

        try {
            URL url = new URL(u);
            InputStream stream = url.openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            while((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Connection error!");
        }

        return result;

    }
}
