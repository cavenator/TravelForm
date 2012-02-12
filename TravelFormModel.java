package travel;

import java.util.*;
import java.text.*;

/**
 * This class represents the Travel Form entry which represents the User's entered data.
 *
 * <PRE>
 * Filename:         TravelFormModel.java
 *
 * Description:     The purpose of this class is to store (and represent) the information that the user inputs into the View.
 *		     		This class makes use of a static nested class called TravelDate, which stores the
 *		     		Month, Day, Year and Preferred Time of the either the Return Date or Departure Date. This
 *		     		class serves as the primary data model to be used for any view which wishes to represent a
 *		     		Travel Form model.
 *
 * Nested Class:     TravelDate
 *
 * Instantiated By:  TravelForm.java
 *
 * Aggregated By:	 MainController.java
 *
 * Maintainer:       A. Cave
 *
 * Version:          0.102
 *
 * Last Modified:  	04/20/2010  by: A. Cave
 *
 * Changes Made -------
 * 0.100: A. Cave   Initial Version.
 * 0.101: A. Cave   Added the isValid() method into class and removed the TravelFormConfirmation Class
 * 0.102: A. Cave	Removed dead code and finalized comments.
 * 0.103: A. Cave   Modified method "isFormValid" to class method "submit(TravelFormModel travelForm)".
 * </PRE>
  */

public class TravelFormModel
{
	private TravelDate returnDate;
	private TravelDate departureDate;
	private String fromLocation;
	private String toLocation;
	private Integer numberOfPassengers;

	/*
	* The constructor initializes all variables with empty values.
	*/
	public TravelFormModel()
	{
		fromLocation = new String();
		toLocation = new String();
		departureDate = new TravelDate();
		returnDate = new TravelDate();
		numberOfPassengers = new Integer(1);
	}

	/**
	* This method sets the From Location (aka Departure) with the parameter value passed.
	*/
	public void setFromLocation(String fromLocation)
	{
		this.fromLocation = fromLocation;
	}

	/**
	* This method sets the To Location (aka Destination) with the parameter value passed.
	*/
	public void setToLocation(String toLocation)
	{
		this.toLocation = toLocation;
	}

	/**
	* This method returns the From Location (aka Departure) that is currently stored.
	*/
	public String getFromLocation()
	{
		return fromLocation;
	}

	/**
	* This method returns the To Location that is currently stored.
	*/
	public String getToLocation()
	{
		return toLocation;
	}

	/**
	* This method returns the Departure Date object.
	*/
	public TravelDate getDepartureDate()
	{
		return departureDate;
	}

	/**
	* This method returns the Return Date object.
	*/
	public TravelDate getReturnDate()
	{
		return returnDate;
	}

	/**
	* This method sets the number of passengers selected by the user. It accepts an Integer object since the JComboBox does not handle primitive data types.
	*/
	public void setNumberOfPassengers(Integer numOfPassengers)
	{
		this.numberOfPassengers = numOfPassengers;
	}

	/**
	* This method returns the number of passengers selected by the user in the form of an Integer object.
	*/
	public Integer getNumberOfPassengers()
	{
		return numberOfPassengers;
	}

	/**
	* This method determines if the submitted form meets all the necessary business requirements.
	*/
	public static boolean submit(TravelFormModel travelForm)
	{
		boolean condition1, condition2;

		condition1 = travelForm.verifyTextFields();

		condition2 = travelForm.verifyTravelDates();

		return (condition1 && condition2);
	}

	/*
	* This method verifies that the data entered into the TravelFormInformationObject via the text boxes from the User Interface
	* meets the proper business rules.  The business rules mandate that these fields cannot be blank. A trim() method is used to
	* ensure that white spaces are not used as leading valid characters.
	*/
	private boolean verifyTextFields()
	{
		return (!(fromLocation.trim().isEmpty()) && !(toLocation.trim().isEmpty()));
	}

	/*
	* This method returns the overall validity of the TravelDates submitted by the user.  Returns true if both dates are valid. False otherwise.
	*/
	private boolean verifyTravelDates()
	{
		boolean date1 = true;
		boolean date2 = true;

		date1 = departureDate.isDateValid();
		date2 = returnDate.isDateValid();
		return (date1 && date2);
	}

	/**
	* This method returns a string representation of the Travel Form object. This method will display each field on a separate
	* line since this method is called when a successful confirmation has been achieved.
	*/
	public String toString()
	{
		return "From: "+fromLocation+"\nTo: "+toLocation+"\nDeparting: "+departureDate+"\nReturning: "+returnDate+"\nPassengers: "+numberOfPassengers.intValue();
	}

/**
*    Static Nested class used to store Travel Date information associated with the TravelFormInformation class.
*    Unlike the GregorianCalendar class, this static nested class is specific to the behavior of the TravelFormModel class
*    as it only serves to store information in a Date-like data structure.
*/
	public static class TravelDate
	{
		private String month;
		private String day;
		private String year;
		private String preferredTime;

		/*
		* The constructor initializes all of the fields with the appropriate default settings of the TravelComboBoxModel class.
		*/
		public TravelDate()
		{
			month = TravelComboBoxModel.DEFAULT_MONTH;
			day = TravelComboBoxModel.DEFAULT_DAY;
			year = TravelComboBoxModel.DEFAULT_YEAR;
			preferredTime = TravelComboBoxModel.DEFAULT_TIME;
		}

		/**
		* This method assigns the month based on the String selection chosen by the user, even the
		* default value if chosen.
		*/
		public void setMonth(String month)
		{
			this.month = month;
		}

		/**
		* This method assigns the day based on the String selection chosen by the user, even the
		* default value if chosen.
		*/
		public void setDay(String day)
		{
			this.day = day;
		}

		/**
		* This method assigns the year based on the String selection chosen by the user, even the default
		* value if chosen.
		*/
		public void setYear(String year)
		{
			this.year = year;
		}

		/**
		* This method assigns the preferred time based on the String selection chosen by the user, even the default
		* value if chosen.
		*/
		public void setPreferredTime(String preferredTime)
		{
			this.preferredTime = preferredTime;
		}

		/**
		* This method returns the Month associated with the TravelDate object.
		*/
		public String getMonth()
		{
			return month;
		}

		/**
		* This method returns the Day associated with the TravelDate object.
		*/
		public String getDay()
		{
			return day;
		}

		/**
		* This method returns the Year associated with the TravelDate object.
		*/
		public String getYear()
		{
			return year;
		}

		/**
		* This method returns the Preferred Time associated with the TravelDate object.
		*/
		public String getPreferredTime()
		{
			return preferredTime;
		}

		/**
		* This method verifies the validity of the TravelDate.  As per the business rules, this TravelDate cannot
		* contain any of the default values from the View's Combo Boxes.
		*/
		public boolean isDateValid()
		{
			return (!(month.equals(TravelComboBoxModel.DEFAULT_MONTH)) && !(day.equals(TravelComboBoxModel.DEFAULT_DAY)) && !(year.equals(TravelComboBoxModel.DEFAULT_YEAR)));
		}

		/**
		* String representation of the Travel Date.
		*/
		public String toString()
		{
			return month+" "+day+", "+year+" at "+preferredTime;
		}
	}
}