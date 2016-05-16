package com.vehicle.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.vehicle.properties.Vehicle;
import com.vehicle.util.VehicleClassifier;
import com.vehicle.util.XmlParser;

/**
 * VehicleIdentifier is a main class for Vehicle identification This class takes
 * the default XML file as input from package com/vehicle/resources/vehicles.xml
 * if the user does not provide the XML as argument.
 * 
 */
public class VehicleIdentifier
{

	public static void main(String[] args)
	{
		
		try
		{
			String xmlInuputFile = "";
			VehicleIdentifier vci =new VehicleIdentifier();
			if (args.length > 0) // To read the input file through command line.
			{
				xmlInuputFile = args[0];
				vci.run(xmlInuputFile, true);
			} else
			{
				xmlInuputFile = "/com/vehicle/resources/Vehicle_Test1.xml";
				vci.run(xmlInuputFile, false);
			}			

		} catch (Exception  ex)
		{
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public List<Vehicle> run(String filePath, boolean commandArg) throws ParserConfigurationException, SAXException, IOException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		InputStream xmlInput = null;
		if(commandArg)
		{
			xmlInput = new FileInputStream(filePath);
		}
		else
		{
			xmlInput = VehicleIdentifier.class
					.getResourceAsStream(filePath);
		}
		
		SAXParser saxParser = factory.newSAXParser();
		XmlParser xmlParser = new XmlParser();
		saxParser.parse(xmlInput, xmlParser);
		VehicleClassifier vc = new VehicleClassifier();

		List<Vehicle> vehicle = xmlParser.getParsedVehicles();

		vc.clisifyVehicles(vehicle);
		vc.classifyByType(vehicle);
		return vehicle;		
	}
	
	
}
