package db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ISSCLDataEntity {
    private String message;
    private Long id;
    private LocalDateTime date;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal velocity;



    public ISSCLDataEntity(String message, Long id, LocalDateTime date, IssPositionEntity issPositionEntity, BigDecimal velocity ) {
        this.message = message;
        this.id = id;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
        this.velocity = velocity;

    }
}
