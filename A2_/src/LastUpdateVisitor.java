
public class LastUpdateVisitor implements AdminVisitor {

	User lastUpdatedUser = new User();
	
	@Override
	public int visitAdminControlPanel(AdminControlPanel instance) {
		lastUpdatedUser = instance.getLastUpdated();
		return 0;
	}

	@Override
	public void visitUser(User user) {
		// does nothing
	}

	@Override
	public void visitGroup(UserGroup group) {
		// does nothing
		
	}
	
	public User getLastUpdatedUser() {
		return lastUpdatedUser;
	}

}
