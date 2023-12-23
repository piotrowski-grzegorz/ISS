package api.people_in_space;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PeopleInSpaceResponse {
    String message;
    int number;
//    People people;
    List<People> people = new ArrayList<>();



}
