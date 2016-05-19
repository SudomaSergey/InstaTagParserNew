package com.kademika;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.kademika.parser.TagParser;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

class Frame extends JPanel {

	private JFrame frame;
	private TagParser parser;
	private JTextArea resultArea;
	private JFormattedTextField depthField;

	public Frame(TagParser parser) {
		this.parser = parser;
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
		springLayout.putConstraint(SpringLayout.NORTH, tagTextField, 0, SpringLayout.NORTH, instructionLabel);
		springLayout.putConstraint(SpringLayout.WEST, tagTextField, 6, SpringLayout.EAST, instructionLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, tagTextField, 40, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, tagTextField, 150, SpringLayout.EAST, instructionLabel);
		add(tagTextField);
		tagTextField.setColumns(10);

		JButton btnStartSearch = new JButton("Start");
		springLayout.putConstraint(SpringLayout.NORTH, btnStartSearch, 0, SpringLayout.NORTH, instructionLabel);
		springLayout.putConstraint(SpringLayout.WEST, btnStartSearch, 6, SpringLayout.EAST, tagTextField);
		add(btnStartSearch);

		btnStartSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String tag = tagTextField.getText();
					Integer depth = Integer.parseInt(depthField.getText());
					String result = parser.parse(tag, depth).toString();

					resultArea.setText(tag + " " + String.valueOf(depth));
					resultArea.setText(result);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Check input fields", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		resultArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, resultArea, 20, SpringLayout.SOUTH, btnStartSearch);
		springLayout.putConstraint(SpringLayout.WEST, resultArea, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, resultArea, 232, SpringLayout.SOUTH, btnStartSearch);
		springLayout.putConstraint(SpringLayout.EAST, resultArea, -10, SpringLayout.EAST, this);
		resultArea.setLineWrap(true);

		JScrollPane scrollPane = new JScrollPane(resultArea);
		springLayout.putConstraint(SpringLayout.SOUTH, btnStartSearch, -19, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 97, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnStartSearch, 0, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, instructionLabel);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 384, SpringLayout.WEST, this);

		add(scrollPane);

		JLabel depthText = new JLabel("Insert search depth:");
		springLayout.putConstraint(SpringLayout.NORTH, depthText, 21, SpringLayout.SOUTH, instructionLabel);
		springLayout.putConstraint(SpringLayout.WEST, depthText, 0, SpringLayout.WEST, instructionLabel);
		add(depthText);

		depthField = new JFormattedTextField(NumberFormat.getInstance());
		springLayout.putConstraint(SpringLayout.NORTH, depthField, 6, SpringLayout.SOUTH, tagTextField);
		springLayout.putConstraint(SpringLayout.WEST, depthField, 0, SpringLayout.WEST, tagTextField);
		springLayout.putConstraint(SpringLayout.SOUTH, depthField, -21, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, depthField, 65, SpringLayout.EAST, instructionLabel);
		depthField.setColumns(10);
		add(depthField);

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