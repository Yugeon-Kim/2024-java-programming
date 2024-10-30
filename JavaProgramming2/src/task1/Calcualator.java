package task1;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calcualator extends JFrame {
	String[] button_text = { "%", "CE", "C", "X", "1/x", "x^2", "2/x", "/", "7", "8", "9", "x", "4", "5", "6", "-", "1",
			"2", "3", "+", "+/-", "0", ".", "=" };

	JButton[] jbutton = new JButton[25];
	JTextField text;
	String result,state,int_double; 	//result 결과 저장,state 상태 (+,-,/ 등등) 저장 
	

	double result_double;
	ActionListener litener1 = e -> {
		int result_int= 0;
		String currentText = text.getText();
		String btn_s = ((JButton) e.getSource()).getText();
		
		if (btn_s.equals("=")) {
			if(result.indexOf(".")==-1) { 
				int_double="int";
			}
			else {
				int_double="double";
			}
			
			if (state == "+" && int_double=="int") {
				result_int = Integer.parseInt(result) + Integer.parseInt(currentText);
				text.setText( Integer.toString(result_int));
			} else if (state == "-") {
				result_int = Integer.parseInt(result) - Integer.parseInt(currentText);
				text.setText( Integer.toString(result_int));
			} else if (state == "/") {
				result_int = Integer.parseInt(result) / Integer.parseInt(currentText);
				text.setText( Integer.toString(result_int));
			} else if (state == "x") {
				result_int = Integer.parseInt(result) * Integer.parseInt(currentText);
				text.setText( Integer.toString(result_int));
			}
			if (state == "+" && int_double=="double") {
				result_double = Double.parseDouble(result) + Double.parseDouble(currentText);
				text.setText(Double.toString(result_double));
			} else if (state == "-") {
				result_double = Double.parseDouble(result) - Double.parseDouble(currentText);
				text.setText(Double.toString(result_double));
			} else if (state == "/") {
				result_double = Double.parseDouble(result) / Double.parseDouble(currentText);
				text.setText(Double.toString(result_double));
			} else if (state == "x") {
				result_double = Double.parseDouble(result) * Double.parseDouble(currentText);
				text.setText(Double.toString(result_double));
			}
			
			result_int= 0;
			result_double= 0;
			state=null;
		}
		else if (btn_s.equals("+")) {
			result=text.getText();
			state=btn_s;
			text.setText("0");
		}
		else if (btn_s.equals("-")) {
			result=text.getText();
			state=btn_s;
			text.setText("0");
		}
		else if (btn_s.equals("/")) {
			result=text.getText();
			state=btn_s;
			text.setText("0");
		}
		else if (btn_s.equals("C")) {
			result="0";
			result_int= 0;
			result_double= 0;
			text.setText("0");
		}
		else if (btn_s.equals("CE")) {
			text.setText("0");
		}
		else if (btn_s.equals("%")) {
			state=btn_s;
			result=text.getText();
		}
		else if (btn_s.equals("X")) {
			if (currentText.equals("0")) { //0인 상태에서도 버튼을 누르면 안지워지도록 하는 if문  
				text.setText("0");}
			else{
			currentText=currentText.substring(0, currentText.length() - 1); //substring을 통해 마지막입력된 숫자 제거 
			text.setText(currentText);
			if(text.getText().length()==0) {
				text.setText("0"); //긴 문장에서 length를 저장했다가 X를 누르면 오류가 떠 0으로 하는 if문 추가
			}
			}
		}
		else if (btn_s.equals("x")) {
			state=btn_s;
			result=text.getText();
			text.setText("0");
		} 
		else if (btn_s.equals("1/x")) {
			
			result=text.getText();
			result_double = (1/Double.parseDouble(result) );
			text.setText(Double.toString(result_double));
		} 
		else if (btn_s.equals("x^2")) {
			result = text.getText();
			if(result.indexOf(".")==-1) { 
				result_double = (Double.parseDouble(result) * 2);
				text.setText(Double.toString(result_double));
			}
			else {
				result_int =(Integer.parseInt(result) * 2);
				text.setText(Integer.toString(result_int));
			}
			
				
		} 
		else if (btn_s.equals("2/x")) {
			state="2/x";
			result=text.getText();
			result_double=Math.sqrt(Double.parseDouble(result));
			text.setText(Double.toString(result_double));
		} 
		else if (btn_s.equals("+/-")) {
			
			result=text.getText();
			if(result.indexOf("-")==-1) {
				text.setText("-"+result);
			}
			else {
				text.setText(result.substring(1, result.length()));
			}
			
		} 
		else if (btn_s.equals(".")) {
			result=text.getText();
			text.setText(result+".");
		} 
		else {
			if (currentText.equals("0")) {
				text.setText(btn_s);
			} else {
				if (state == "2/x") {
					text.setText(btn_s);
					state = "";
				} else {
					text.setText(currentText + btn_s);
				}
			}
		}
	};

	public Calcualator() {

		text = new JTextField("0");
		text.setEditable(false);
		text.setFont(new Font("Dialog", Font.PLAIN, 55));

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
			jbutton[i].addActionListener(litener1);
		}
		jbutton[23].setForeground(Color.white);
		jbutton[23].setBackground(new Color(0, 103, 192));
		add(jp1, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		new Calcualator();
	}

}
