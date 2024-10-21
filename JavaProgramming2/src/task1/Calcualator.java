package task1;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calcualator extends JFrame {
	String[] button_text = 
		{ "%", "CE",  "C",  "X", 
		  "1/x","x^2","2/x","/", 
		  "7",  "8",  "9",  "x", 
		  "4",  "5",  "6",  "-",  
		  "1",  "2",  "3",  "+",
		  "+/-", "0", ".",  "="};

	JButton[] jbutton = new JButton[25];
	JTextField text;

	public Calcualator() {

		text = new JTextField("0");
		text.setEditable(false);
		text.setFont(new Font("Dialog",Font.PLAIN,55));
		
		text.setHorizontalAlignment(JTextField.RIGHT); 
		add(text, BorderLayout.NORTH);

		showButton();
		setSize(340, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void showButton() {
		JPanel jp1 = new JPanel(new GridLayout(6, 4, 3, 3));
		for (int i = 0; i < 24; i++) {
			jp1.add(jbutton[i] = new JButton(button_text[i]));
			jbutton[i].setBackground(Color.white);
			jbutton[i].setFont(new Font("Noto Sans", Font.PLAIN, 17)); 
		}
		jbutton[23].setForeground(Color.white);
		jbutton[23].setBackground(new Color(0,103,192));
		add(jp1, BorderLayout.CENTER);
		
		 ActionListener litener1= e-> {
				String s =text.getText();
				String btn_s = jbutton[i].getText();
				int z = Integer.parseInt(s);
				result
			}

	}

	public static void main(String[] args) {
		new Calcualator();
	}

}
