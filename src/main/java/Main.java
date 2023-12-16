import api.iss_current_location.CurrentLocationResponse;
import services.IssService;

import java.math.BigDecimal;
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
                    final CurrentLocationResponse locationFromOpenNotify = new IssService().getLocationFromOpenNotify();

                    System.out.println("Connected with api.open-notify.org/iss-now:? " + locationFromOpenNotify.getMessage());
                    var pos = locationFromOpenNotify.getIss_position();
                    System.out.println("POSITION:");
                    System.out.println("Latitude: " + locationFromOpenNotify.getIss_position().getLatitude());
                    System.out.println("Longitude: " + locationFromOpenNotify.getIss_position().getLongitude());
                    int secondsToSleep = 5;
                    System.out.println("WAITING FOR SECOND POSITION...");
                    try {
                        Thread.sleep(secondsToSleep * 1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("");
                    System.out.println("POSITION AFTER 5 SEC:");
                    final CurrentLocationResponse locationFromOpenNotify2 = new IssService().getLocationFromOpenNotify();
                    System.out.println("Latitude2: " + locationFromOpenNotify2.getIss_position().getLatitude());
                    System.out.println("Longitude2: " + locationFromOpenNotify2.getIss_position().getLongitude());
                    var lat1 = locationFromOpenNotify.getIss_position().getLatitude();
                    var lat2 = locationFromOpenNotify2.getIss_position().getLatitude();
                    var longitude1 = locationFromOpenNotify.getIss_position().getLongitude();
                    var longitude2 = locationFromOpenNotify2.getIss_position().getLongitude();
                    BigDecimal resultLat = lat1.subtract(lat2);
                    BigDecimal resultLong = longitude1.subtract(longitude2);
                    System.out.println("result Latitude: " + resultLat);
                    System.out.println("result Longitude: " + resultLong);
                    // 1 stopien = 111 km - szerokosc // 1 minuta = 1,85 km dlugosc

                    BigDecimal constantLongitute = BigDecimal.valueOf(1.85);
                    BigDecimal constantLatitude = BigDecimal.valueOf(111);
                    BigDecimal latitudeChange = resultLat.multiply(constantLatitude);
                    BigDecimal longitudeChange = resultLong.multiply(constantLongitute);
                    System.out.println("latitudeChange: " + latitudeChange);
                    System.out.println("longitudeChange: " + longitudeChange);
                    System.out.println("");
                    var velocity = latitudeChange.divide(BigDecimal.valueOf(5));
                    var velocity2 = velocity.subtract(velocity).subtract(velocity);

                    System.out.println("ISS Velocity is " + velocity2 + "[km/s]");




//                    //OBLICZANIE PRĘDKOŚCI SATELITY // WZOR NA PREDKOSC SATELITY
//                    final double gravityConstant = 6.67 * Math.pow(10, -11);
//                    final double massOfEarth = 5.98 * Math.pow(10, 24);
//                    final double earthRadiusConstant = 6378;
//                    final double distanceFromEarth = 400 * earthRadiusConstant;
//                    double velocity = Math.sqrt(gravityConstant*massOfEarth/distanceFromEarth);
//                    System.out.println("velocity: " + velocity);


                    isRunning = goBackToMenu();
                    // calculate speed

                    break;

                case "R":
                    System.out.println("CHECKING UPCOMING RUNS FOR LOC");
                    isRunning = goBackToMenu();
                    break;

                case "P":
                    System.out.println("CHECKING CREW....");
                    isRunning = goBackToMenu();
                    break;

                case "C":
                    System.out.println("VER 1.0 BY layerman");
                    isRunning = goBackToMenu();
                    break;

                case "X":
                    System.out.println("QUITING..");
                    isRunning = false;

                default:
                    System.out.println("ERROR: WRONG LETTER");
                    break;
            }
        }
        //api.HttpClientService
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
