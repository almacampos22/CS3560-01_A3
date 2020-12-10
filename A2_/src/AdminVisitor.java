/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


public interface AdminVisitor {

	public int visitAdminControlPanel(AdminControlPanel instance);
	
	public void visitUser(User user);
	
	public void visitGroup(UserGroup group);
}
