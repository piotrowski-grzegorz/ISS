package db;

import api.iss_current_location.CurrentLocationResponse;
import handlers.CalculateVelocityOfISS;

public class ISSCLDataEntityMapper {
    public static ISSCLDataEntity from(CurrentLocationResponse dto) {
        return new ISSCLDataEntity("HELLO FROM MAPPER",
                1L,
                null,
                new IssPositionEntity(dto.getIss_position().getLongitude(), dto.getIss_position().getLatitude()),
                new CalculateVelocityOfISS().calculateVolecity());
    }
}
