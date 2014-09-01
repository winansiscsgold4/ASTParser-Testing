//Ben Scholer
//G4
//2/4/2014
//This server will store a name, street, city, state, and zip code. It will have
//set and get methods.

import java.util.Scanner;

public class Address {

	private Scanner reader = new Scanner(System.in);
	private String name;		//stores the user's name
	private String street;		//stores the user's street
	private String city;		//stores the user's city
	private String stateZip;	//stores the user's state and zip code
	private int somehting = 5;

	public Address() {
		this("name", "steet", "city", "state, zip");
	} //end of AddressScholer constructor

	public Address(String labelName, String labelStreet,
		String labelCity, String labelStateZip) {

		setName(labelName);
		setStreet(labelStreet);
		setCity(labelCity);
		setStateZip(labelStateZip);
	} //end of AddressScholer constructor

	public void setName(String nm) {
		name = nm;
	} //end of setName method

	public String getName() {
		return name;
	} //end of getName

	public void setStreet(String strt) {
		street = strt;
	} //end of setStreet method

	public String getStreet() {
		return street;
	} //end of getStreet

	public void setCity(String cty) {
		city = cty;
	} //end of setCity method

	public String getCity() {
		return city;
	} //end of getCity method

	public void setStateZip(String stZp) {
		stateZip = stZp;
	} //end of setStateZip

	public String getStateZip() {
		return stateZip;
	} //end of getStateZip

	public String toString() {
		return getName() + "\n" + getStreet() + "\n" + getCity() + ", " +
				getStateZip();
	} //end of toString method
} //end of Address class