package travel;

import java.text.*;
import java.lang.*;
import javax.swing.*;

/**
 * Provides the values to be presented within the Combo Boxes of the Travel Itinerary.
 *
 * <PRE>
 * Filename:        TravelComboBoxModel.java
 *
 * Description:     Creates the values used for the Month, Day, Year, Preferred Time and Passengers selections
 *		     		and then is plugged into the appropriate Combo Boxes on the View.  This is accomplished
 *		     		by storing the values in Arrays and using static fields for verifying the
 *		     		business rules have been met.  The accessor methods return the appropriate data values which are
 *					encapsulated within DefaultComboBoxModels, providing the ComboBoxModel object that will be used
 *					within the JComboBoxes displayed in the View.
 *
 * Instantiated By:  TravelForm.java
 *
 * Aggregation Of:	 MainController.java
 *
 * Maintainer:       A. Cave
 *
 * Version:          0.102
 *
 * Lasted Modified:  04/19/2010  by: A. Cave
 *
 * Changes Made -------
 * 0.100: A. Cave   Initial Version.
 * 0.101: A. Cave	Replaced Vector<?> with arrays for storing the data values
 * 0.102: A. Cave	Cleaned up dead code and finalized commenting
 * </PRE>
  */

public class TravelComboBoxModel
{
	//These are the static default values for Month, Day, Year and Time selections.
	public final static String DEFAULT_MONTH = "Select Month";
	public final static String DEFAULT_DAY = "Select Day";
	public final static String DEFAULT_YEAR = "Select Year";
	public final static String DEFAULT_TIME = "anytime";

	//These values describe the ranges used for Days, Years, and amount of Passengers.  Default minimum passenger is assumed to be 1
	//unless otherwise noted.
	private final int MAX_PASSENGERS = 10;
	private final int MIN_DAY_VALUE = 1;
	private final int MAX_DAY_VALUE = 31;
	private final int MIN_YEAR_VALUE = 2007;
	private final int MAX_YEAR_VALUE = 2008;

	private String[] monthArrayModel;
	private String[] dayArrayModel;
	private String[] yearArrayModel;
	private String[] stringPreferredTimes = new String[]{DEFAULT_TIME,"early morning","morning","noon","afternoon","early evening","red eye"};
	private Integer[] intPassengersArray = new Integer[MAX_PASSENGERS];

	//The constructor populates the necessary data structures upon Object Construction, starting with the default value (if any)
	//and incrementally adding values.
	public TravelComboBoxModel()
	{
		this.constructMonths();
		this.constructDays();
		this.constructYears();
		this.constructPassengerValues();
	}

	/*
	* This method populates the values used for the Month JComboBox. The values start off with the DEFAULT_MONTH value
	* and contains the values of the DateFormatSymbols Months (minus the UNDECIMBER value).
	* month values:  January, February, March, .....December
	*/
	private void constructMonths()
	{

        monthArrayModel = new String[13];
        monthArrayModel[0] = DEFAULT_MONTH;

        //This function loads months January - December into their respective indexes after the default value.
        //within java.lang.System
        System.arraycopy(new DateFormatSymbols().getMonths(),0,monthArrayModel,1,12);
	}

	/*
	* This method populates the values used for the Day JComboBox. The values start off with the DEFAULT_DAY value
	* and contains the minimum day value to the maximum day value.
	*/
	private void constructDays()
	{
		dayArrayModel = new String[MAX_DAY_VALUE+1];
		dayArrayModel[0] = DEFAULT_DAY;
		for (int i = MIN_DAY_VALUE; i <= MAX_DAY_VALUE; i++)
		{
			dayArrayModel[i] = new Integer(i).toString();
		}
	}

	/*
	* This method populates the values used for the Year JComboBox. The values start off with the DEFAULT_YEAR value
	* and contains the minimum year value to the maximum year value.
	*/
	private void constructYears()
	{
		//The two is allocated for the following two values:  Default_value and the last value.
   		yearArrayModel = new String[(MAX_YEAR_VALUE-MIN_YEAR_VALUE)+2];
   		yearArrayModel[0] = DEFAULT_YEAR;
		for (int i = MIN_YEAR_VALUE, j = 1; i <= MAX_YEAR_VALUE; i++, j++)
		{
			yearArrayModel[j] = new Integer(i).toString();
		}
	}

	/*
	* This method populates the values used for the Passenger JComboBox. The range of values is one to MAX_PASSENGER.
	*/
	private void constructPassengerValues()
	{
		for (int i=1; i <= MAX_PASSENGERS; i++)
			intPassengersArray[i-1] = new Integer(i);

	}

	/**
	* Returns the Month values encapsulated within a DefaultComboBoxModel.
	*
	*/
	public DefaultComboBoxModel getMonthValues()
	{
		return new DefaultComboBoxModel(monthArrayModel);
	}

	/**
	* Returns the Day values encapsulated within a DefaultComboBoxModel.
	*/
	public DefaultComboBoxModel getDayValues()
	{
		return new DefaultComboBoxModel(dayArrayModel);
	}

	/**
	* Returns the Year values encapsulating within a DefaultComboBoxModel object.
	*/
	public DefaultComboBoxModel getYearValues()
	{
		return new DefaultComboBoxModel(yearArrayModel);
	}

	/**
	* Returns the Passengers values by encapsulating the Passenger values within a DefaultComboBoxModel object.
	*/
	public DefaultComboBoxModel getPassengerValues()
	{
		return new DefaultComboBoxModel(intPassengersArray);
	}

	/**
	* Returns the Time Preference values by encapsulating the preferred times within a DefaultComboBoxModel object.
	*/
	public DefaultComboBoxModel getPreferredTimes()
	{
		return new DefaultComboBoxModel(stringPreferredTimes);
	}
}