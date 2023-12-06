package api.iss_current_location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor

public class Iss_Position {
    BigDecimal longitude;
    BigDecimal latitude;
}
