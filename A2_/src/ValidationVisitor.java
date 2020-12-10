import java.util.ArrayList;

public class ValidationVisitor implements AdminVisitor{
	ArrayList<String> allTakenUserIds = new ArrayList<String>();
	private boolean validation = true;
	
	@Override
	public int visitAdminControlPanel(AdminControlPanel instance) {
		// does nothing
		return 0;
	}

	@Override
	public void visitUser(User user) {
		if ((user.getDisplayName().contains(" ")) || (allTakenUserIds.contains(user.getDisplayName()))) {
			validation = false;
		}
		else {
			allTakenUserIds.add(user.getDisplayName());
		}
	}

	@Override
	public void visitGroup(UserGroup group) {
		if ((group.getDisplayName().contains(" ")) || (allTakenUserIds.contains(group.getDisplayName()))) {
			validation = false;
		}
		else {
			allTakenUserIds.add(group.getDisplayName());
		}
		
	}
	
	public boolean getValidation() {
		return validation;
	}

}
