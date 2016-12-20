package access;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AccessControl {
	
	public static ArrayList<String> getListOfUsers(ArrayList<ArrayList<String>> inputArray){
		ArrayList<String> result = new ArrayList<String>();
		Set<String> tempSet = new HashSet<String>();
		for(ArrayList<String> temp : inputArray){
			tempSet.add(temp.get(4));
		}
		for(String user : tempSet){
			result.add(user);
		}
		return result;
	}
}