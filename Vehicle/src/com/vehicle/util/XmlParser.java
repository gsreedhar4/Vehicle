package com.vehicle.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.vehicle.properties.Frame;
import com.vehicle.properties.Vehicle;
import com.vehicle.properties.Wheel;

/*
 * XmlParser parses the given XML file and gives back parsed data
 */

public class XmlParser extends DefaultHandler
{

	private List<Vehicle> vehicles;
	private String content = null;
	private Vehicle vehicle;
	private Wheel wheel;
	private Frame frame;
	private boolean isWheel;
	private boolean isPowerTrain;

	public XmlParser()
	{
		vehicles = new ArrayList<Vehicle>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException
	{
		
		if ("vehicle".equalsIgnoreCase(qName))
		{
			vehicle = new Vehicle();
		} else if ("frame".equalsIgnoreCase(qName))
		{
			frame = new Frame();
		} else if ("wheel".equalsIgnoreCase(qName))
		{
			isWheel = true;
			wheel = new Wheel();
		} else if ("wheels".equalsIgnoreCase(qName))
		{
			vehicle.setWheel(new ArrayList<Wheel>());
		} else if ("powertrain".equalsIgnoreCase(qName))
		{
			isPowerTrain = true;
		} else if (isPowerTrain)
		{
			vehicle.setPowerTrain(qName);
			isPowerTrain = false;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if ("vehicle".equalsIgnoreCase(qName))
		{
			vehicles.add(vehicle);
		} 
		else if ("wheel".equalsIgnoreCase(qName))
		{
			vehicle.getWheel().add(wheel);
		}
		else if("id".equalsIgnoreCase(qName))
		{
			vehicle.setVehicleId(content);
		}
		else if("position".equalsIgnoreCase(qName))
		{
			 wheel.setPosition(content);
		}
		else if("material".equalsIgnoreCase(qName) && isWheel)
		{
			/*
			 * Material property is applicable for both 
			 * Frame and Wheel, to classify this use isWheel flag.
			 * 
			 */
			 wheel.setMaterial(content);
		}
		else if("material".equalsIgnoreCase(qName))
		{
			 frame.setMaterial(content);
			 vehicle.setFrame(frame);
		}else if("wheels".equalsIgnoreCase(qName))
		{
			isWheel =false;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException 
	{
		content = String.copyValueOf(ch, start, length).trim();
	}
	
	public List<Vehicle> getParsedVehicles()
	{
		return vehicles;
	}
}
