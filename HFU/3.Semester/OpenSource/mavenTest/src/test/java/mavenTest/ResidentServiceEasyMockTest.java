package mavenTest;

import mavenSource.*;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by Sascha on 13.12.2016.
 */
public class ResidentServiceEasyMockTest {
    List<Resident> residentList = Arrays.asList(new Resident("Alfred", "Zwiebel", "Hansstraße 2", "Musterstadt", new Date()),
            new Resident("Hans1", "Zwiebel1", "Hansstraße 21", "Musterstadt1", new Date()),
            new Resident("Werner3", "Zwiebel3", "Hansstraße 23", "Musterstadt3", new Date()));
    Resident resident = new Resident("Alfred", "Zwiebel", "Hansstraße 2", "Musterstadt", new Date());
    private ResidentRepository mock = createMock(ResidentRepository.class);
    private BaseResidentService baseResidentService = new BaseResidentService();

    public ResidentServiceEasyMockTest() {
        expect(mock.getResidents()).andReturn(residentList);
        replay(mock);
        baseResidentService.setResidentRepository(mock);
    }


    @Test(expected = ResidentServiceException.class)
    public void testGetUniqueResidentsListWithWirldcard() throws ResidentServiceException {
        Resident resident = residentList.get(0);
        resident.setGivenName("Al*");
        Resident filteredResident = baseResidentService.getUniqueResident(resident);
        verify(mock);
    }

    @Test
    public void testGetUniqueResidentsList() throws ResidentServiceException{
        Resident resident = residentList.get(0);
        Resident filteredResident = baseResidentService.getUniqueResident(resident);
        assertSame(resident, filteredResident);
        verify(mock);
    }
//
    @Test(expected = ResidentServiceException.class)
    public void testGetUniqueResidentListNotFound()throws ResidentServiceException{
        Resident resident = new Resident("Alfhdjshdjsred", "Zwiebel", "Hansstraße 2", "Musterstadt", new Date());
        Resident filteredResident = baseResidentService.getUniqueResident(resident);
        verify(mock);
    }

    @Test
    public void testGetFilteredResidentsListNoResult(){
        Resident resident = new Resident("Alfrejdksdmd", "*", "*", "*", null);
        List<Resident> list = baseResidentService.getFilteredResidentsList(resident);
        assertEquals(list.size(), 0);
        verify(mock);
    }
//
    @Test
    public void testGetFilteredResidentsListAll(){
        Resident resident = new Resident("*", "*", "*", "*", null);
        List<Resident> list = baseResidentService.getFilteredResidentsList(resident);

        assertArrayEquals(residentList.toArray(), list.toArray());
        verify(mock);
    }
}
