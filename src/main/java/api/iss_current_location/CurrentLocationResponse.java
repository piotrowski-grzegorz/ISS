package api.iss_current_location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor

public class CurrentLocationResponse {
    String message;
    LocalDateTime timeStamp;
    Iss_Position iss_position;

}
