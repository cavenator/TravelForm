package travel;

import java.util.*;
import java.lang.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 * This class acts as the central controller for all user interactions with the view.
 *
 * <PRE>
 * Filename:         MainController.java
 *
 * Description:      The purpose of this class is to act as the Main Controller for all listening events associated to the view.
 *		     This class contains object references to the view and the TravelFormModel data model in order to update
 *		     the model accordingly and reflect those changes in the view.  There are three nested classes within this main
 *		     controller class which monitors the user interactions with the view.  The Combo Box listener makes use of the setActionCommand()
 *		     to determine which Combo Box has been activated so that the appropriate value can be set in the TravelFormModel object.
 *		     The TravelDocumentListener listens for updates within the Text Fields and updates the TravelFormModel object accordingly.
 *		     The ButtonListener responds whenever the user clicks "Submit".
 *
 * Aggregate Objects:TravelFormModel.java, TravelItineraryView.java, TravelComboBoxModel.java
 *
 * Inner classes:    TravelDocumentListener, ComboBoxListener, ButtonListener.
 *
 * Instantiated By:  TravelForm.java
 *
 * Maintainer:       A. Cave
 *
 * Version:          0.103
 *
 * Lasted Modified:  04/24/2010  by: A. Cave
 *
 * Changes Made -------
 * 0.100: A. Cave   Initial Version.
 * 0.101: A. Cave   Added an object reference of TravelFormConfirmation to validate the TravelFormInformation object when the user clicks "Submit".
 * 0.102: A. Cave   Removed dead code and finalized comments.
 * 0.103: A. Cave	modified for use of TravelFormModel class method "submit()".
 * </PRE>
  */

public class MainController
{
	private TravelFormModel travelForm;
	private TravelItineraryView view;
	private TravelComboBoxModel tcbm;
	private JOptionPane confirmationDialog;

	/**
	* This constructor accepts the TravelFormModel object reference, TravelItineraryView object reference, and a
	* TravelComboBoxModel object reference.  The aim is to utilize the "pull model", where the view calls the model in
	* order to request updates. This is implemented by registering event listeners to the view in order to automatically
	* update the TravelForm object as the user interacts with the view.
	*/
	public MainController(TravelFormModel dataModel, TravelItineraryView frame, TravelComboBoxModel tcbm)
	{
		this.travelForm = dataModel;
		this.view = frame;
		this.tcbm = tcbm;
		this.assignListeners();
		this.provideViewWithComboBoxModels();
		this.initializeTravelModel();
		confirmationDialog = new JOptionPane();
	}

	/*
	* This private method assigns Event Listeners to the Components displayed on the view.  The purpose of these event
	* listeners is to update the TravelFormModel model when the user interacts with the GUI form.
	*/
	private void assignListeners()
	{
		addDocumentListener(new TravelDocumentListener());
		addComboBoxListener(new ComboBoxListener());
		addButtonListener(new ButtonListener());
	}

	/*
	* This private method initializes the Combo Boxes with the values provided by the TravelComboBoxModel object.
	*/
	private void provideViewWithComboBoxModels()
	{
		view.getDepartureMonthDropDown().setModel(tcbm.getMonthValues());
		view.getDepartureDayDropDown().setModel(tcbm.getDayValues());
		view.getDepartureYearDropDown().setModel(tcbm.getYearValues());
		view.getDepartureSettingDropDown().setModel(tcbm.getPreferredTimes());

		view.getReturnMonthDropDown().setModel(tcbm.getMonthValues());
		view.getReturnDayDropDown().setModel(tcbm.getDayValues());
		view.getReturnYearDropDown().setModel(tcbm.getYearValues());
		view.getReturnSettingDropDown().setModel(tcbm.getPreferredTimes());

		view.getPassengerDropDown().setModel(tcbm.getPassengerValues());
	}

	/*
	* This private method initializes the Travel Form Model object with the default values of the view.
	*/
	private void initializeTravelModel()
	{
		view.getFromLocationTextField().setText(travelForm.getFromLocation());
		view.getToLocationTextField().setText(travelForm.getToLocation());

		view.getDepartureMonthDropDown().setSelectedItem(travelForm.getDepartureDate().getMonth());
		view.getDepartureDayDropDown().setSelectedItem(travelForm.getDepartureDate().getDay());
		view.getDepartureYearDropDown().setSelectedItem(travelForm.getDepartureDate().getYear());
		view.getDepartureSettingDropDown().setSelectedItem(travelForm.getDepartureDate().getPreferredTime());

		view.getReturnMonthDropDown().setSelectedItem(travelForm.getReturnDate().getMonth());
		view.getReturnDayDropDown().setSelectedItem(travelForm.getReturnDate().getDay());
		view.getReturnYearDropDown().setSelectedItem(travelForm.getReturnDate().getYear());
		view.getReturnSettingDropDown().setSelectedItem(travelForm.getReturnDate().getPreferredTime());

		view.getPassengerDropDown().setSelectedItem(travelForm.getNumberOfPassengers());
	}

		/*
		* This method adds DocumentListeners to the Text Fields that update the TravelFormModel accordingly.
		*/
	    private void addDocumentListener(DocumentListener docListener)
	    {
	        view.getFromLocationTextField().getDocument().putProperty("property",view.getFromLocationTextField());
	        view.getFromLocationTextField().getDocument().putProperty("name","From Field");
	        view.getFromLocationTextField().getDocument().addDocumentListener(docListener);

	        view.getToLocationTextField().getDocument().putProperty("property",view.getToLocationTextField());
	        view.getToLocationTextField().getDocument().putProperty("name","To Field");
	        view.getToLocationTextField().getDocument().addDocumentListener(docListener);
		}

		/*
		* This method adds ActionListeners to the JComboBoxs that update the TravelFormModel accordingly.
		* This makes use of "Action Commands" to distinguish which JComboBox was activated.
		*/
		private void addComboBoxListener(ActionListener boxListener)
		{
	        view.getDepartureMonthDropDown().addActionListener(boxListener);
	        view.getDepartureMonthDropDown().setActionCommand("Set Departure Month");

	        view.getDepartureDayDropDown().addActionListener(boxListener);
	        view.getDepartureDayDropDown().setActionCommand("Set Departure Day");

	        view.getDepartureYearDropDown().addActionListener(boxListener);
	        view.getDepartureYearDropDown().setActionCommand("Set Departure Year");

	        view.getDepartureSettingDropDown().addActionListener(boxListener);
	        view.getDepartureSettingDropDown().setActionCommand("Set Departure Time");

	        view.getReturnMonthDropDown().addActionListener(boxListener);
	        view.getReturnMonthDropDown().setActionCommand("Set Return Month");

	        view.getReturnDayDropDown().addActionListener(boxListener);
	        view.getReturnDayDropDown().setActionCommand("Set Return Day");

	        view.getReturnYearDropDown().addActionListener(boxListener);
	        view.getReturnYearDropDown().setActionCommand("Set Return Year");

	        view.getReturnSettingDropDown().addActionListener(boxListener);
	        view.getReturnSettingDropDown().setActionCommand("Set Return Time");

	        view.getPassengerDropDown().addActionListener(boxListener);
			view.getPassengerDropDown().setActionCommand("Set Passengers");
		}

		/*
		* This method adds an ActionListener to the JButton that will submit the model and validate
		* it against the business rules.
		*/
		private void addButtonListener(ActionListener buttonListener)
		{
			view.getSubmitButton().addActionListener(buttonListener);
		}

	/*
	*  This inner class is responsible for updating the Text Fields viewed in the TravelItineraryView.
	*  This class updates the reference to the TravelFormModel object based on what the user enters
	*  into the text fields.  The To and From Destinations are modified based on their "name" and "property"
	*  attributes.
	*/
	public class TravelDocumentListener implements DocumentListener
	{

		public TravelDocumentListener()
		{
		}

		/**
		*  This method is not called since styled text is not used.
		*/
		public void changedUpdate(DocumentEvent e)
		{

		}

		/**
		*  This method updates the TravelFormModel text field attributes as they are being added typed.
		*  The name property maps to the appropriate attribute within the TravelFormModel object.
		*  For example, name may map to "To Field" (e.g. toLocation) or "From Field" (e.g. fromLocation).
		*  There is another property named "property" that contains a reference the appropriate JTextField
		*  box, where the text within the JTextField can be plugged into the appropriate attribute.
		*/
		public void insertUpdate(DocumentEvent e)
		{
			this.updateModel(e);
		}

		/**
		*  This method updates the TravelFormModel text field attributes as they are being removed.
		*  The name property maps to the appropriate attribute within the TravelFormModel object.
		*  For example, name may map to "To Field" (e.g. toLocation) or "From Field" (e.g. fromLocation).
		*  There is another property named "property" that contains a reference the appropriate JTextField
		*  box, where the text within the JTextField can be plugged into the appropriate attribute.
		*/
    		public void removeUpdate(DocumentEvent e)
		{
			this.updateModel(e);
		}

		/*
		* This private method is where the Text Fields are modified and updates the TravelFormModel object accordingly.
		*/
		private void updateModel(DocumentEvent e)
		{
			Document doc = e.getDocument();
			String valueEntry = (String) doc.getProperty("name");
			JTextField docSource = (JTextField) doc.getProperty("property");
			if (valueEntry.equals("To Field"))
			{
            	travelForm.setToLocation(docSource.getText());
        	}
			else if (valueEntry.equals("From Field"))
			{
            	travelForm.setFromLocation(docSource.getText());
        	}
		}

	}

	/**
	*  This inner class is responsible for updating the Combo Boxes viewed in the TravelItineraryView.
	*  This class updates the reference to the TravelFormModel object based on what the user selects
	*  in the Combo Boxes.  The Departure Date and Return Date attributes are updated based on the configuration settings
	*  of the action settings.
	*/
	public class ComboBoxListener implements ActionListener
	{

		/**
		* This method is called whenever an event is performed on one of the Combo Boxes.  The TravelFormModel is updated
		* accordingly based on the user interaction with the JComboBoxes.
		*/
		public void actionPerformed(ActionEvent evt)
		{
			JComboBox activatedJComboBox = (JComboBox) evt.getSource();
			String actionCommand = evt.getActionCommand();
			updateModel(activatedJComboBox, actionCommand);
		}

		/*
		* This method sets the appropriate value to respective attribute within the TravelFormModel object reference.  This is
		* accomplished by using the action command passed from the event object to determine the selected value from the JComboBox
		* that fired the event.
		*/
		private void updateModel(JComboBox activatedJComboBox,String actionCommand) throws IllegalArgumentException
		{
			Object selectedItem = activatedJComboBox.getSelectedItem();
			DefaultComboBoxModel tempComboBoxModel;

			if (actionCommand.equals("Set Departure Month"))
			{
				travelForm.getDepartureDate().setMonth((String) selectedItem);
			}
			else if(actionCommand.equals("Set Departure Day"))
			{
				String localString = (String) selectedItem;
				travelForm.getDepartureDate().setDay(localString);
			}
			else if(actionCommand.equals("Set Departure Year"))
			{
				String localString = (String) selectedItem;
				travelForm.getDepartureDate().setYear(localString);
			}
			else if(actionCommand.equals("Set Departure Time"))
			{
				String localString = (String) selectedItem;
				travelForm.getDepartureDate().setPreferredTime(localString);
			}
			else if (actionCommand.equals("Set Return Month"))
			{
				travelForm.getReturnDate().setMonth((String) selectedItem);
			}
			else if(actionCommand.equals("Set Return Day"))
			{
				travelForm.getReturnDate().setDay((String) selectedItem);
			}
			else if(actionCommand.equals("Set Return Year"))
			{
				travelForm.getReturnDate().setYear((String) selectedItem);
			}
			else if(actionCommand.equals("Set Return Time"))
			{
				travelForm.getReturnDate().setPreferredTime((String) selectedItem);
			}
			else if(actionCommand.equals("Set Passengers"))
			{
				travelForm.setNumberOfPassengers((Integer) selectedItem);
			}
			else
				throw new IllegalArgumentException("No such action command exist!");
		}
	}

	/**
	*  This inner class is responsible for "submitting" the model information displayed in the TravelItineraryView.
	*  This class is responsible for ensuring that the TravelFormModel object ensures business rules have been met.
	*/
	public  class ButtonListener implements ActionListener
	{
		/**
		* This method is called whenever the user submits the form.
		*/
		public void actionPerformed(ActionEvent evt)
		{
			String errorMessage = "One or more fields contain invalid and/or default entries!\nPlease ensure all fields are populated with valid entries before submitting.";
			boolean validForm = TravelFormModel.submit(travelForm);

			if(validForm)
			{
				confirmationDialog.showMessageDialog(view, travelForm.toString(), "Confirmation Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				confirmationDialog.showMessageDialog(view, errorMessage, "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}