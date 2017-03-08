package test;
import Bean.Action;
import java.util.ArrayList;
import java.util.List;
public class test_returnaction {
	private List<Action> friendaction = new ArrayList<Action>();
	public List<Action> returnaction()
	{
	/*	Action this_action;
		int n,i;
		n=5;
		for(i=0;i<n;i++)
		{
			this_action=new Action("xxxx","xxxx","xxxx");
			friendaction.add(this_action);
		}
		for(i=0;i<n;i++)
			System.out.println(friendaction.get(i).getActiontext());*/
		return friendaction;
	}
}
