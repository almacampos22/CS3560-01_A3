/**
 * 	@author Alma Campos
 * 	CS3560-01
 * 	Assignment 2
 */


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.SwingConstants;

public class Driver {

	private JFrame frame;
	private JTextField addUserTextField;
	private JTextField addGroupTextField;
	LastUpdateVisitor lastUpdate = new LastUpdateVisitor();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver window = new Driver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Driver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("AdminControlPanel");
		frame.setBounds(100, 100, 868, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ValidationVisitor validate = new ValidationVisitor();
		
		User user1 = new User("Stu1");
		AdminControlPanel.getInstance().addSystemEntry(user1);
		validate.visitUser(user1);
		User user2 = new User("Stu2");
		AdminControlPanel.getInstance().addSystemEntry(user2);
		validate.visitUser(user2);
		User user3 = new User("Stu3");
		AdminControlPanel.getInstance().addSystemEntry(user3);
		validate.visitUser(user3);
		User user4 = new User("Stu4");
		validate.visitUser(user4);
		User user5 = new User("Stu5");
		validate.visitUser(user5);
		User user6 = new User("Stu6");
		validate.visitUser(user6);
		UserGroup cs3560 = new UserGroup("CS3560");
		UserGroup cs3560StudyGroup = new UserGroup("CS3560 Study Group");
		cs3560.addEntry(user4);
		cs3560.addEntry(user5);
		cs3560.addEntry(user6);
		cs3560.addEntry(cs3560StudyGroup);
		AdminControlPanel.getInstance().addSystemEntry(cs3560);
		user1.follow(user2);
		user1.follow(user3);
		user1.follow(user6);
		user2.follow(user1);
		user1.tweetMessage("hello");
		
		
		JTree treeView = new JTree();
		treeView.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(AdminControlPanel.getInstance().getRoot()) {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode(user1);
					add(node_1);
					    
					DefaultMutableTreeNode node_2;
					node_2 = new DefaultMutableTreeNode(user2);
					add(node_2);
					
					DefaultMutableTreeNode node_3;
					node_3 = new DefaultMutableTreeNode(user3);
					add(node_3);
					    
					DefaultMutableTreeNode node_4;
					node_4 = new DefaultMutableTreeNode(cs3560);
					for (SystemEntry entry : cs3560.getEntries()) {
						node_4.add(new DefaultMutableTreeNode(entry));
					}
				    add(node_4);
				    
				}
			}
		));
		treeView.setBounds(15, 16, 322, 447);
		frame.getContentPane().add(treeView);
		
		addUserTextField = new JTextField();
		addUserTextField.setBounds(352, 16, 268, 44);
		frame.getContentPane().add(addUserTextField);
		addUserTextField.setColumns(10);
		
		addGroupTextField = new JTextField();
		addGroupTextField.setBounds(352, 78, 268, 44);
		frame.getContentPane().add(addGroupTextField);
		addGroupTextField.setColumns(10);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTreeModel model = (DefaultTreeModel)treeView.getModel();
				DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)
                                      treeView.getLastSelectedPathComponent();
				if (parentNode == null) {
					JOptionPane.showMessageDialog(null, "Select group to add user");
				}
				else {
				    if (parentNode.getUserObject() instanceof UserGroup) {
				    	User user = new User(addUserTextField.getText());
						addUserTextField.setText(null);
				        parentNode.add(new DefaultMutableTreeNode(user));
				        model.reload(parentNode);
				        ((UserGroup)parentNode.getUserObject()).addEntry(user);
				        validate.visitUser(user);
				    }
				    else {
					JOptionPane.showMessageDialog(null, "Cannot add user to a user");
				    }
				}
			}
		});
		btnAddUser.setBounds(661, 16, 170, 44);
		frame.getContentPane().add(btnAddUser);
		
		JButton btnAddGroupButton = new JButton("Add Group");
		btnAddGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTreeModel model = (DefaultTreeModel)treeView.getModel();
				DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)
                                      treeView.getLastSelectedPathComponent();
				if (parentNode == null) {
					JOptionPane.showMessageDialog(null, "Select group to add to");
				}
				else {
				    if (parentNode.getUserObject() instanceof UserGroup) {
				    	UserGroup group = new UserGroup(addGroupTextField.getText());
						addGroupTextField.setText(null);
				        parentNode.add(new DefaultMutableTreeNode(group));
				        model.reload(parentNode);
				        ((UserGroup)parentNode.getUserObject()).addEntry(group);
				        validate.visitGroup(group);
				    }
				    else {
					    JOptionPane.showMessageDialog(null, "Cannot add group to a user");
				    }
				}
			}
		});
		btnAddGroupButton.setBounds(661, 78, 170, 44);
		frame.getContentPane().add(btnAddGroupButton);
		
		JButton btnOpenUserView = new JButton("Open User View");
		btnOpenUserView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        treeView.getLastSelectedPathComponent();
				if (node == null) {
					JOptionPane.showMessageDialog(null, "User not selected");
				}
				else {
				
			        Object nodeInfo = node.getUserObject();
	                if (nodeInfo instanceof User) {
	                    UserView viewUser = new UserView((User)nodeInfo);
	                    viewUser.NewScreen();
	                } 
	                else {
	                	JOptionPane.showMessageDialog(null, "Select user");
	                }
				}
			}
		});
		btnOpenUserView.setBounds(352, 146, 479, 44);
		frame.getContentPane().add(btnOpenUserView);
		
		JLabel systemTotals = new JLabel("");
		systemTotals.setHorizontalAlignment(SwingConstants.CENTER);
		systemTotals.setBounds(490, 230, 204, 37);
		frame.getContentPane().add(systemTotals);
		
		JButton btnShowUserTotal = new JButton("Show User Total");
		btnShowUserTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TotalUserVisitor visitor = new TotalUserVisitor();
				int totalUsers = AdminControlPanel.getInstance().accept(visitor);
				systemTotals.setText("Total Users: " + String.valueOf(totalUsers));
			}
		});
		btnShowUserTotal.setBounds(352, 359, 228, 44);
		frame.getContentPane().add(btnShowUserTotal);
		
		JButton btnShowGroupTotal = new JButton("Show Group Total");
		btnShowGroupTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TotalGroupVisitor visitor = new TotalGroupVisitor();
				int totalGroups = AdminControlPanel.getInstance().accept(visitor);
				systemTotals.setText("Total Groups: " + String.valueOf(totalGroups));
			}
		});
		btnShowGroupTotal.setBounds(603, 359, 228, 44);
		frame.getContentPane().add(btnShowGroupTotal);
		
		JButton btnShowMessagesTotal = new JButton("Show Messages Total");
		btnShowMessagesTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TotalMessagesVisitor visitor = new TotalMessagesVisitor();
				int totalMessages = AdminControlPanel.getInstance().accept(visitor);
				systemTotals.setText("Total Messages: " + String.valueOf(totalMessages));
			}
		});
		btnShowMessagesTotal.setBounds(352, 419, 228, 44);
		frame.getContentPane().add(btnShowMessagesTotal);
		
		JButton btnPositivePercentageTotal = new JButton("Show Positive Percentage");
		btnPositivePercentageTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PositivePercentageVisitor visitor = new PositivePercentageVisitor();
				int percentage = visitor.visitAdminControlPanel(AdminControlPanel.getInstance());
				systemTotals.setText("Positive Tweet Percentage: " + String.valueOf(percentage) + "%");
			}
		});
		btnPositivePercentageTotal.setBounds(603, 419, 228, 44);
		frame.getContentPane().add(btnPositivePercentageTotal);
		
		JButton btnIdValidation = new JButton("ID Validation");
		btnIdValidation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validate.getValidation()) {
					systemTotals.setText("All IDs are valid");
				}
				else {
					systemTotals.setText("All IDs are not valid");
				}
			}
		});
		btnIdValidation.setBounds(352, 299, 228, 44);
		frame.getContentPane().add(btnIdValidation);
		
		JButton btnLastUpdatedUser = new JButton("Last Updated User");
		btnLastUpdatedUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastUpdate.visitAdminControlPanel(AdminControlPanel.getInstance());
				if(lastUpdate.getLastUpdatedUser() != null) {
					systemTotals.setText(lastUpdate.getLastUpdatedUser().getDisplayName());
				}
			}
		});
		btnLastUpdatedUser.setBounds(603, 299, 228, 44);
		frame.getContentPane().add(btnLastUpdatedUser);
		
	}
}


