package travel;

import javax.swing.*;

/**
 * This is the main class where execution will occur.
 *
 * <PRE>
 * Filename:         TravelForm.java
 *
 * Description:      This serves as the main class of where the program executes from. It instantiates the necessary
 *		     data model objects, the view object and the controller object.
 *
 * Composition objects: TravelComboBoxModel.java, TravelFormModel.java, TravelItineraryView.java, MainController.java
 *
 * Implemented By:   None
 *
 * Maintainer:       A. Cave
 *
 * Version:          0.102
 *
 * Lasted Modified:  04/22/2010  by: A. Cave
 *
 * Changes Made -------
 * 0.100: A. Cave   Initial Version.
 * 0.101: A. Cave	Modified the classes to reflect new class names.
 * 0.102: A. Cave   Remove dead code and finalize comments.
 * </PRE>
  */

public class TravelForm
{
	/**
	* This is the main execution method. First, the data model objects are instantiated.  Then, the view
	* denoted by TravelItineraryView is instantiated and packed. Next, the MainController object is initialized and accepts the
	* TravelFormModel, TravelItineraryView, and TravelComboBoxModel objects in order to
	* 	-  update the ComboBoxes provided by the View with the values of the TravelComboBoxModel object.
	*   -  registers the necessary listeners with the view to update the TravelFormModel based on user interactions.
	*   -  renders the view with the values encapsulated within the TravelFormModel object.
	*  The TravelFormModel object is validated when the user decides to submit the form.
	*/
	public static void main(String[] args)
	{
		TravelComboBoxModel comboBoxModel = new TravelComboBoxModel();
		TravelFormModel travelModel = new TravelFormModel();
		TravelItineraryView view = new TravelItineraryView();
		MainController frontController = new MainController(travelModel, view, comboBoxModel);

		//set the frame visible
		view.setVisible(true);
	}
}