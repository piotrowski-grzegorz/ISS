import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Scanner;

public class Main {
    //pobieranie daych o ruchu ISS z odpowiedniego serwisu sieciowego
    //przetwarzanie pobranych danych po stronie aplikacji\
    //pobrane dane powinny być zapisaywane do bazy danych
    ////pakiet db- > klasa z CRUD,
    // ARCHITEKTURA WARSTWOWOWA , CZYLI JAKA ?

    //ZADANIE 1 - INTEGRACJA Z API
    //ZADANIE 2 - OBLICZANIE PRĘDKOŚCI ISS
    //ZADANIE 3 - ZWRACANIE LISTY NADCHODZĄCYCH PRZEBIEGÓW ISS DLA OKREŚLONEJ LOKALIZACJI
    //ZADANIE 4 - ZWRACANIE LICZBY OSÓB PRZEBYWAJĄCYCH W KOSMOSIE W RAMACH ISS


    public static void main(String[] args) {
        boolean isRunning = true;
        boolean returnMenu = false;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            openScreen();
            String input = scanner.nextLine();
            switch (input) {
                case "S":
                    System.out.println("CHECKING SPEED OF ISS...");
                    System.out.println("SPEED IS 10000 KM/H");
                    isRunning = goBackToMenu();
                    break;

                case "R":
                    System.out.println("CHECKING UPCOMING RUNS FOR LOC");
                    isRunning = goBackToMenu();

                case "P":
                    System.out.println("CHECKING CREW....");
                    isRunning = goBackToMenu();

                case "C":
                    System.out.println("VER 1.0 BY layerman");
                    isRunning = goBackToMenu();

                case "X":
                    System.out.println("QUITING..");
                    isRunning = false;

                default:
                    System.out.println("ERROR: WRONG LETTER");
                    break;
            }
        }
        //HttpClientService
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://api.open-notify.org/iss-now.json"))
                .GET()
                .header("Accept", "application/json")
                .build();
    }
        private static void openScreen() {
            System.out.println("""
                #######################################
                Hello to my ISSapp
                Please choose from conntex menu:
                #######################################
                S - CHECK SPEED OF ISS
                R - CHECK UPCOMING RUNS FOR LOCALISATION
                P - CHECK CREW ON ISS
                C - CREDITS
                X - EXIT
                #######################################
             
                              """);

        }

        private static boolean goBackToMenu() {
            System.out.println(("""
                    #######################################
                    GO BACK TO MAIN MENU ?
                    Y - YES , N - NO EXIT
                    #######################################
                    """));
            Scanner scannerResult = new Scanner(System.in);
            String inputResult = scannerResult.nextLine();
            boolean result = true;
            switch (inputResult) {
                case "Y":
                    System.out.println("Y - return true;");
                    return result;
                case "N":
                    System.out.println("N - return false");
                    return result = false;
                default:
                    System.out.println("default: return true");
                    return result;
            }
        }
    }
