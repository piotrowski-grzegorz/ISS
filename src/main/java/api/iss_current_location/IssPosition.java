package api.iss_current_location;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class IssPosition {
    BigDecimal longitude;
    BigDecimal latitude;

    public IssPosition(BigDecimal longitude, BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
