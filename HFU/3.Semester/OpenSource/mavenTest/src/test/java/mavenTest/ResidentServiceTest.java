package mavenTest;
import mavenSource.*;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sascha on 06.12.2016.
 */
public class ResidentServiceTest {

    @Test
    public void testGetUniqueResidentsListWithWirldcard(){
        ResidentRepository stub = new ResidentRepositoryStub();
        Resident resident = stub.getResidents().get(0);
        resident.setGivenName("Al*");
        BaseResidentService baseResidentService = new BaseResidentService();
        baseResidentService.setResidentRepository(stub);
        try{
            Resident filteredResident = baseResidentService.getUniqueResident(resident);
            fail("Exception wurde nicht geworfen");
        } catch(ResidentServiceException e){
            System.out.println(e.getMessage());
        }
        baseResidentService.getFilteredResidentsList(resident);
    }

    @Test
    public void testGetUniqueResidentsList() {
        ResidentRepository stub = new ResidentRepositoryStub();
//        Resident resident = new Resident("Alfred", "Zwiebel", "Hansstraße 2", "Musterstadt", new Date());
        Resident resident = stub.getResidents().get(0);
        BaseResidentService baseResidentService = new BaseResidentService();
        baseResidentService.setResidentRepository(stub);
        try{
            Resident filteredResident = baseResidentService.getUniqueResident(resident);
            assertEquals(resident, filteredResident);
        } catch(ResidentServiceException e){
            System.out.println(e.getMessage());
        }
        baseResidentService.getFilteredResidentsList(resident);
    }

    @Test
    public void testGetUniqueResidentListNotFound(){
        ResidentRepository stub = new ResidentRepositoryStub();
        Resident resident = new Resident("Alfhdjshdjsred", "Zwiebel", "Hansstraße 2", "Musterstadt", new Date());
        BaseResidentService baseResidentService = new BaseResidentService();
        baseResidentService.setResidentRepository(stub);
        try{
            Resident filteredResident = baseResidentService.getUniqueResident(resident);
            fail("Exception wurde nicht geworfen");
        } catch(ResidentServiceException e){
            System.out.println(e.getMessage());
        }
        baseResidentService.getFilteredResidentsList(resident);
    }

    @Test
    public void testGetFilteredResidentsListNoResult(){
        ResidentRepository stub = new ResidentRepositoryStub();
        BaseResidentService baseResidentService = new BaseResidentService();
        baseResidentService.setResidentRepository(stub);
        Resident resident = new Resident("Alfrejdksdmd", "*", "*", "*", null);
        List<Resident> list = baseResidentService.getFilteredResidentsList(resident);
        assertEquals(list.size(), 0);
    }

    @Test
    public void testGetFilteredResidentsListAll(){
        ResidentRepository stub = new ResidentRepositoryStub();
        BaseResidentService baseResidentService = new BaseResidentService();
        baseResidentService.setResidentRepository(stub);
        Resident resident = new Resident("*", "*", "*", "*", null);
        List<Resident> list = baseResidentService.getFilteredResidentsList(resident);
        assertEquals(list.size(), stub.getResidents().size());
    }

    @Test
    public void testGetFilteredResidentsList(){
        ResidentRepository stub = new ResidentRepositoryStub();
        BaseResidentService baseResidentService = new BaseResidentService();
        baseResidentService.setResidentRepository(stub);
        Resident resident = new Resident("Alfred", "*", "*", "*", null);
        List<Resident> list = baseResidentService.getFilteredResidentsList(resident);
        assertEquals(list.get(0), stub.getResidents().get(0));
    }
}