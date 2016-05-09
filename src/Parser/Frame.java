package Parser;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

class Frame extends JPanel{
	
	private JFrame frame;
	private Logic logic;
	private JTextArea resultArea;
	
	public Frame(Logic logic){
		this.logic = logic;
		initFrame();
		initContent();		
	}

	private void initContent() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel instructionLabel = new JLabel("Insert tag to search:");
		springLayout.putConstraint(SpringLayout.NORTH, instructionLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, instructionLabel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, instructionLabel, 40, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, instructionLabel, 154, SpringLayout.WEST, this);
		add(instructionLabel);
		
		JTextField tagTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, tagTextField, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, tagTextField, 18, SpringLayout.EAST, instructionLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, tagTextField, 40, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, tagTextField, 162, SpringLayout.EAST, instructionLabel);
		add(tagTextField);
		tagTextField.setColumns(10);
		
		JButton btnStartSearch = new JButton("Start search");
		springLayout.putConstraint(SpringLayout.NORTH, btnStartSearch, 17, SpringLayout.SOUTH, tagTextField);
		springLayout.putConstraint(SpringLayout.WEST, btnStartSearch, 0, SpringLayout.WEST, tagTextField);
		add(btnStartSearch);
		
		btnStartSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tag = tagTextField.getText();
				String result = logic.getTags(tag);
				resultArea.setText(result);								
			}
		});
		
		resultArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, resultArea, 20, SpringLayout.SOUTH, btnStartSearch);
		springLayout.putConstraint(SpringLayout.WEST, resultArea, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, resultArea, 232, SpringLayout.SOUTH, btnStartSearch);
		springLayout.putConstraint(SpringLayout.EAST, resultArea, -10, SpringLayout.EAST, this);
		resultArea.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(resultArea);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 17, SpringLayout.SOUTH, btnStartSearch);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, instructionLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 232, SpringLayout.SOUTH, btnStartSearch);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 384, SpringLayout.WEST, this);
		
		add(scrollPane);

		
		frame.setVisible(true);
		
	}

	private void initFrame() {
		frame = new JFrame("InstaTagParser");
		frame.setSize(400, 350);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);	
		frame.setResizable(false);
	}
}