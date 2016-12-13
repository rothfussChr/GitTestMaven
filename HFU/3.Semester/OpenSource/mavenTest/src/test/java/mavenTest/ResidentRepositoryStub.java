package mavenTest;

import mavenSource.*;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Sascha on 06.12.2016.
 */
public class ResidentRepositoryStub implements ResidentRepository {

    List<Resident> residents = Arrays.asList(new Resident("Alfred", "Zwiebel", "Hansstraße 2", "Musterstadt", new Date()),
                                             new Resident("Werner1", "Zwiebel1", "Hansstraße 21", "Musterstadt1", new Date()),
                                             new Resident("Peter3", "Zwiebel3", "Hansstraße 23", "Musterstadt3", new Date()));

    public List<Resident> getResidents() {
        return residents;
    }
}
