package com.vehicle.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.*;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.vehicle.main.VehicleIdentifier;
import com.vehicle.properties.Vehicle;
import com.vehicle.util.VehicleClassifier;

public class VehicleIdentifierTest extends TestCase
{
	
	@Test
	public void testInputFile()
	{
		try
		{
			VehicleIdentifier vci = new VehicleIdentifier();			
			vci.run("", true);
			assertTrue("Programm accepting empty string as file path. ",false);
		} catch (ParserConfigurationException  | SAXException| IOException ex)
		{
			assertTrue(true);
		}		
	}
	
	@Test
	public void testVehicleCount() throws ParserConfigurationException, SAXException, IOException
	{
			VehicleIdentifier vci = new VehicleIdentifier();			
			List<Vehicle> vehicles=vci.run("/com/vehicle/resources/Vehicle_test.xml", false);
			assertTrue("Expected 4 vehicles but found "+vehicles.size() +"  vehicles", vehicles.size() == 4);		
	}
	
	@Test
	public void testVehicleTypes() throws ParserConfigurationException, SAXException, IOException
	{
			VehicleIdentifier vci = new VehicleIdentifier();			
			List<Vehicle> vehicles=vci.run("/com/vehicle/resources/Vehicle_Test1.xml", false);
			VehicleClassifier vc = new VehicleClassifier();
			Map<String, List<Vehicle>> vehicletypes=vc.classifyByType(vehicles);
			List<Vehicle>  cars = vehicletypes.get("Car");
			List<Vehicle>  bicycle = vehicletypes.get("Bicycle");
			List<Vehicle>  bigwheel = vehicletypes.get("Big Wheel");
			List<Vehicle>  motorCycle = vehicletypes.get("Motor Cycle");
			
			
			assertTrue("Expected 2 cars but found "+cars.size()+" cars", cars.size() == 2);
			assertTrue("Expected 2 bicycles but found "+bicycle.size()+" bicycles", bicycle.size() == 2);
			assertTrue("Expected 1 motor cycles but found "+motorCycle.size()+" motor cycles", motorCycle.size() == 1);
			assertTrue("Expected 2 Big Wheel but found "+bigwheel.size()+" Big Wheel", cars.size() == 2);
	}
	
	@Test
	public void testCarWheelCount() throws ParserConfigurationException, SAXException, IOException
	{
			VehicleIdentifier vci = new VehicleIdentifier();			
			List<Vehicle> vehicles=vci.run("/com/vehicle/resources/Vehicle_Test1.xml", false);
			VehicleClassifier vc = new VehicleClassifier();
			Map<String, List<Vehicle>> vehicletypes=vc.classifyByType(vehicles);
			List<Vehicle>  cars = vehicletypes.get("Car");	
			for(Vehicle car: cars)
			{				
				assertTrue("There should be 4 wheels for car, but found  "+car.getWheel().size()+" wheels for car "+ 
			                                                   car.getVehicleId(), car.getWheel().size() == 4);
			}
	}
	
	@Test
	public void testCarMotorCycleWheelCount() throws ParserConfigurationException, SAXException, IOException
	{
			VehicleIdentifier vci = new VehicleIdentifier();			
			List<Vehicle> vehicles=vci.run("/com/vehicle/resources/Vehicle_Test1.xml", false);
			VehicleClassifier vc = new VehicleClassifier();
			Map<String, List<Vehicle>> vehicletypes=vc.classifyByType(vehicles);
			List<Vehicle>  motorCycle = vehicletypes.get("Motor Cycle");	
			for(Vehicle mc: motorCycle)
			{				
				assertTrue("There should be 2 wheels for motor cycle, but found  "+mc.getWheel().size()+
						" wheels for motor cycle "+  mc.getVehicleId(), mc.getWheel().size() == 2);
			}
	}
	
	
	@Test
	public void testHangGlider() throws ParserConfigurationException, SAXException, IOException
	{
			VehicleIdentifier vci = new VehicleIdentifier();			
			List<Vehicle> vehicles=vci.run("/com/vehicle/resources/Vehicle_Test2.xml", false);
			VehicleClassifier vc = new VehicleClassifier();
			Map<String, List<Vehicle>> vehicletypes=vc.classifyByType(vehicles);
			List<Vehicle>  glider = vehicletypes.get("Hang Glider");	
			for(Vehicle glder: glider)
			{				
				assertTrue("There should not be wheels for Hang Glider, but found  "+  glder.getVehicleId(), glder.getWheel()== null);
			}
	}

}
