/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


public class TotalUserVisitor implements AdminVisitor{

	@Override
	public int visitAdminControlPanel(AdminControlPanel instance) {
		
		return instance.getUserTotal();
	}

	@Override
	public void visitUser(User user) {
		// does nothing
		
	}

	@Override
	public void visitGroup(UserGroup group) {
		// does nothing
		
	}

}
