/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


public class TotalGroupVisitor implements AdminVisitor {

	@Override
	public int visitAdminControlPanel(AdminControlPanel instance) {
		return instance.getGroupTotal(); 
		//excludes root user group
		
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
