/**
 * Contains the constructor and a toString method
 * @author Guest
 */
public class Leader {

	// the name of the leader
	public String name;
	
	/**
	 * Sets the name of the leader as the name
	 * @param name
	 */
	public Leader(String name) 
	{
		this.name = name;
	}

	/**
	 * Concatenates the elements into a string
	 * @return theString
	 */
	public String toString()
	{
		String theString = "leader: " + "\n" + name;
		return theString;
	}
}
