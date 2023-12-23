package handlers;

import api.iss_current_location.CurrentLocationResponse;
import api.iss_current_location.IssPosition;
import services.IssService;

import java.math.BigDecimal;

public class CalculateVelocityOfISS {
    //TODO
    //REFACTORING
    final CurrentLocationResponse locationFromOpenNotify = new IssService().getLocationFromOpenNotify();
    final CurrentLocationResponse locationFromOpenNotifyAfter5sec = new IssService().getLocationFromOpenNotify();

    private IssPosition checkPositionNow(){
        System.out.println("CHECKING SPEED OF ISS...");
        System.out.println("Connected with api.open-notify.org/iss-now:? " + locationFromOpenNotify.getMessage());
        System.out.println("POSITION:");
        var latitude = locationFromOpenNotify.getIss_position().getLatitude();
        var longitude = locationFromOpenNotify.getIss_position().getLongitude();
        System.out.println(latitude + "," + longitude);
        return new IssPosition(longitude, latitude);
    }
    private void wait5seconds(){
        System.out.println("CHECKING SPEED OF ISS AFTER 5 SEC...");
        System.out.println("Connected with api.open-notify.org/iss-now:? " + locationFromOpenNotify.getMessage());

    }
    private IssPosition checkPositionAfter5Seconds() {
        wait5seconds();
        var latitudeAfter5sec = locationFromOpenNotifyAfter5sec.getIss_position().getLatitude();
        var longitudeAfter5sec = locationFromOpenNotifyAfter5sec.getIss_position().getLongitude();
        return new IssPosition(longitudeAfter5sec, latitudeAfter5sec);
    }
    public BigDecimal calculateVolecity(){
        BigDecimal velocity2 = BigDecimal.ZERO;
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
        velocity2 = velocity.subtract(velocity).subtract(velocity);
        System.out.println("ISS Velocity is " + velocity2 + "[km/s]");
        return velocity2;
    }

    public BigDecimal calculateVelocity2(){
        checkPositionNow();
//        var lat1 = locationFromOpenNotify.getIss_position().getLatitude();
        var latitudeFirst = checkPositionNow().getLatitude();
//        var lat2 = locationFromOpenNotify2.getIss_position().getLatitude();

        var longitudeFirst = checkPositionNow().getLongitude();
        System.out.println(latitudeFirst + "," + longitudeFirst
        );
        final int secondsToSleep = 5;
        try {
            Thread.sleep(secondsToSleep * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        var latitudeSecond = checkPositionAfter5Seconds().getLatitude();
        var longitudeSecond = checkPositionAfter5Seconds().getLongitude();
        System.out.println(latitudeSecond +"," + longitudeSecond);
//        var longitude1 = locationFromOpenNotify.getIss_position().getLongitude();
//        var longitude2 = locationFromOpenNotify2.getIss_position().getLongitude();
        BigDecimal resultLat = latitudeFirst.subtract(latitudeSecond);
        BigDecimal resultLong = longitudeFirst.subtract(longitudeSecond);
        System.out.println("result Latitude: " + resultLat);
        System.out.println("result Longitude: " + resultLong);
        // 1 stopien = 111 km - szerokosc // 1 minuta = 1,85 km dlugosc

        BigDecimal constantLongitute = BigDecimal.valueOf(1.85);
        BigDecimal constantLatitude = BigDecimal.valueOf(111);
        BigDecimal latitudeChange = resultLat.multiply(constantLatitude);
        BigDecimal longitudeChange = resultLong.multiply(constantLongitute);
        System.out.println("latitudeChange: " + latitudeChange);
        System.out.println("longitudeChange: " + longitudeChange);
        BigDecimal velocity = latitudeChange.divide(BigDecimal.valueOf(5));
        BigDecimal velocity2 = velocity.subtract(velocity).subtract(velocity);

        System.out.println("ISS Velocity is " + velocity2 + "[km/s]");
        return velocity2;

    }
}
