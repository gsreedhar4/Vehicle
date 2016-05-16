package com.vehicle.properties;

import java.util.List;

/*
 * Vehicle class with its attributes
 */

public class Vehicle
{

	private String vehicleId;
	private Frame frame;
	private String vehicleType;
	private String powerTrain;

	private List<Wheel> wheel;

	public String getVehicleId()
	{
		return vehicleId;
	}

	public void setVehicleId(String vehicleId)
	{
		this.vehicleId = vehicleId;
	}

	public List<Wheel> getWheel()
	{
		return wheel;
	}

	public void setWheel(List<Wheel> wheel)
	{
		this.wheel = wheel;
	}

	public Frame getFrame()
	{
		return frame;
	}

	public void setFrame(Frame frame)
	{
		this.frame = frame;
	}

	public String getVehicleType()
	{
		return vehicleType;
	}

	public void setVehicleType(String vehicleType)
	{
		this.vehicleType = vehicleType;
	}

	public String getPowerTrain()
	{
		return powerTrain;
	}

	public void setPowerTrain(String powerTrain)
	{
		this.powerTrain = powerTrain;
	}

}
