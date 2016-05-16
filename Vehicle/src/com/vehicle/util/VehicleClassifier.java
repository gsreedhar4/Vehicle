package com.vehicle.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vehicle.properties.Vehicle;
import com.vehicle.properties.Wheel;

/*
 * VehicleClassifier.class classify the vehicles based on its properties(Wheels, Powertrain, Material)
 * Displays the output.
 * 
 */

public class VehicleClassifier
{

	public void clisifyVehicles(List<Vehicle> vehicles)
	{

		for (Vehicle vehicle : vehicles)
		{
			int numOfWheels = 0;
			if (vehicle.getWheel() != null)
			{
				numOfWheels = vehicle.getWheel().size();
			}

			// Classify the vehicle based on wheels count and Powertrain.
			switch (numOfWheels)
			{
			case 0:
				if (vehicle.getPowerTrain().equalsIgnoreCase("Bernoulli"))
				{
					vehicle.setVehicleType("Hang Glider");
				} else
				{
					vehicle.setVehicleType("Unclacified");
				}
			case 2:
				if (vehicle.getPowerTrain().equalsIgnoreCase("human"))
				{
					vehicle.setVehicleType("Bicycle");
				} else if (vehicle.getPowerTrain().equalsIgnoreCase(
						"InternalCombustion"))
				{
					vehicle.setVehicleType("Motor Cycle");
				}
				break;
			case 3:
				vehicle.setVehicleType("Big Wheel");
				break;
			case 4:
				if (vehicle.getPowerTrain().equalsIgnoreCase(
						"InternalCombustion"))
				{
					vehicle.setVehicleType("Car");
				}
				break;
			default:
			}
		}
	}

	public Map<String, List<Vehicle>> classifyByType(List<Vehicle> vehicles)
	{
		StringBuilder sb = new StringBuilder();
		Map<String, List<Vehicle>> similarType = new HashMap<String, List<Vehicle>>();
		Set<String> vehicleType = new HashSet<String>();

		sb.append(" Vehicle Id     " + "   Vehicle Type     "
				+ "     Frame     " + "    Powertrain     "
				+ "   Wheels     \n");
		sb.append("\n");
		for (Vehicle vehicle : vehicles)
		{
			sb.append(vehicle.getVehicleId() + "          ");
			sb.append(vehicle.getVehicleType() + "           ");
			sb.append(vehicle.getFrame().getMaterial() + "        ");
			sb.append(vehicle.getPowerTrain() + "           ");
			List<Wheel> wheels = vehicle.getWheel();
			if (wheels != null && wheels.size() > 0)
			{
				sb.append(wheels.size() + " ");
				int i = 0;
				for (Wheel wheel : wheels)
				{
					if (i == 0)
					{
						sb.append(wheel.getMaterial() + " (");
						i++;
					}
					sb.append(wheel.getPosition() + ",");
				}
				sb.append(" ) \n");
			} else
			{
				sb.append("   None    \n");
			}

			// Find out the similar vehicle types and store them in a Map
			if (vehicleType.add(vehicle.getVehicleType()))
			{
				List<Vehicle> list = new ArrayList<Vehicle>();
				list.add(vehicle);
				similarType.put(vehicle.getVehicleType(), list);
			} else
			{
				List<Vehicle> list = similarType.get(vehicle.getVehicleType());
				list.add(vehicle);
				similarType.put(vehicle.getVehicleType(), list);
			}
		}
		System.out.println(sb.toString());

		System.out.println("There are ");
		for (String type : similarType.keySet())
		{
			System.out.println(similarType.get(type).size() + "   " + type);
		}
		System.out.println("In the provided XML.");
		return similarType;
	}
}
