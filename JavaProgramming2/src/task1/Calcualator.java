package task1;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Calculator 클래스는 기본적인 계산 기능을 제공하는 GUI 계산기 애플리케이션입니다.
 * 이 클래스는 덧셈, 뺄셈, 곱셈, 나눗셈과 같은 기본적인 수학 연산을 수행할 수 있습니다.
 * 
 * @author 김유건
 * @version 1.0
 */
public class Calcualator extends JFrame {
    /** 버튼에 표시될 텍스트 배열 */
    String[] button_text = { "%", "CE", "C", "◀", "1/x", "x²", "√", "/", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "=" };
    
    /** 버튼 배열 */
    JButton[] jbutton = new JButton[24];  // 배열 크기를 24로 수정
    
    /** 현재 계산 결과를 표시하는 텍스트 필드 */
    JTextField text;
    
    /** 계산식을 보여줄 텍스트 필드 */
    JTextField expresstext;  // 계산식을 보여줄 텍스트 필드
    
    /** 현재 숫자가 정수인지 실수인지 구분하기 위한 문자열 */
    String int_double;
    
    /** 계산 결과를 저장하는 문자열 */
    String result = "0";
    
    /** 현재 상태를 나타내는 문자열 */
    String state = null;
    
    /** 연산 과정을 저장할 변수 */
    String expression = "";  // 연산 과정을 저장할 변수
    
    /** 정수 계산 결과 */
    int result_int;
    
    /** 실수 계산 결과 */
    double result_double;
    
    /** 버튼 클릭 이벤트 리스너 */
    ActionListener listener = e -> {
        // 현재 텍스트 필드의 내용
        String currentText = text.getText();
        // 클릭한 버튼의 텍스트
        String btn_s = ((JButton) e.getSource()).getText();
        
        // "=" 버튼 클릭 시
        if (btn_s.equals("=")) {
            expression += currentText + " = ";  // 최종 식에 현재 값과 "=" 추가
            
            // 결과가 없거나 현재 텍스트가 비어있으면 종료
            if (result == null || currentText.isEmpty()) {
                return;
            }
            
            // 현재 텍스트와 결과가 정수인지 실수인지 확인
            boolean isCurrentTextDouble = currentText.contains(".");
            boolean isResultDouble = result.contains(".");
          
            if (!isCurrentTextDouble && !isResultDouble) {
                int_double = "int";
            } else {
                int_double = "double";
            }
            
            try {
                if (state != null) {
                    if (state.equals("+")) {
                        if (int_double.equals("int")) {
                            result_int = Integer.parseInt(result) + Integer.parseInt(currentText);
                            text.setText(Integer.toString(result_int));
                        } else {
                            result_double = Double.parseDouble(result) + Double.parseDouble(currentText);
                            text.setText(Double.toString(result_double));
                        }
                    } else if (state.equals("-")) {
                        if (int_double.equals("int")) {
                            result_int = Integer.parseInt(result) - Integer.parseInt(currentText);
                            text.setText(Integer.toString(result_int));
                        } else {
                            result_double = Double.parseDouble(result) - Double.parseDouble(currentText);
                            text.setText(Double.toString(result_double));
                        }
                    } else if (state.equals("/")) {
                        if (Integer.parseInt(currentText) == 0) {
                            text.setText("Error: Division by zero");
                            return;
                        }
                        if (int_double.equals("int")) {
                            result_int = Integer.parseInt(result) / Integer.parseInt(currentText);
                            text.setText(Integer.toString(result_int));
                        } else {
                            result_double = Double.parseDouble(result) / Double.parseDouble(currentText);
                            text.setText(Double.toString(result_double));
                        }
                    } else if (state.equals("x")) {
                    	
                    	    if (int_double.equals("int")) {
                    	        result_int = Integer.parseInt(result) * Integer.parseInt(currentText);
                    	        text.setText(Integer.toString(result_int));
                    	    } else {
                    	    	result_double = Double.parseDouble(result) * Double.parseDouble(currentText);
                    	        text.setText(Double.toString(result_double));
                    	    }
                    	}
                }
            } catch (NumberFormatException ex) {
                text.setText("Error: Invalid input");
            }

            result = text.getText();  // 결과를 업데이트
            state = null;
            expression = "";  // 계산 후 식 초기화
            expresstext.setText("");  // 식 텍스트 초기화
        }
        // 연산자 버튼 클릭 시
        else if (btn_s.equals("+") || btn_s.equals("-") || btn_s.equals("/") || btn_s.equals("x")) {
            expression += currentText + " " + btn_s + " ";  // 연산자와 현재 값 추가
            
            boolean isCurrentTextDouble = currentText.contains(".");
            boolean isResultDouble = result.contains(".");
            
            if (!isCurrentTextDouble && !isResultDouble) {
                int_double = "int";
            } else {
                int_double = "double";
            }

            if (state != null) {
                try {
                    if (int_double.equals("int")) {
                        if (state.equals("+")) {
                            result_int += Integer.parseInt(currentText);
                        } else if (state.equals("-")) {
                            result_int -= Integer.parseInt(currentText);
                        } else if (state.equals("/")) {
                            if (Integer.parseInt(currentText) == 0) {
                                text.setText("Error: Division by zero");
                                return;
                            }
                            result_int /= Integer.parseInt(currentText);
                        } else if (state.equals("x")) {
                            result_int *= Integer.parseInt(currentText);
                        }
                        text.setText(Integer.toString(result_int));
                        result = Integer.toString(result_int);
                    } else {
                        if (state.equals("+")) {
                            result_double += Double.parseDouble(currentText);
                        } else if (state.equals("-")) {
                            result_double -= Double.parseDouble(currentText);
                        } else if (state.equals("/")) {
                            if (Double.parseDouble(currentText) == 0) {
                                text.setText("Error: Division by zero");
                                return;
                            }
                            result_double /= Double.parseDouble(currentText);
                        } else if (state.equals("x")) {
                            result_double *= Double.parseDouble(currentText);
                        }
                        text.setText(Double.toString(result_double));
                        result = Double.toString(result_double);
                    }
                } catch (NumberFormatException ex) {
                    text.setText("Error: Invalid input");
                    return;
                }
            } else {
                result = currentText;
                if (isCurrentTextDouble) {
                    result_double = Double.parseDouble(currentText);
                    int_double = "double";
                } else {
                    result_int = Integer.parseInt(currentText);
                    int_double = "int";
                }
            }
            
            state = btn_s;
            text.setText("0");
            expresstext.setText(expression);  // 계산식 업데이트
        }
        // "C" 버튼 클릭 시
        else if (btn_s.equals("C")) {
            result = "0";
            result_int = 0;
            result_double = 0;
            state = null;
            expression = "";  // 식 초기화
            expresstext.setText("");  // 식 텍스트 초기화
            text.setText("0");
        }
        // "CE" 버튼 클릭 시
        else if (btn_s.equals("CE")) {
            text.setText("0");
        }
        // "%" 버튼 클릭 시
        else if (btn_s.equals("%")) {
            try {
                result_double = Double.parseDouble(currentText) / 100.0;
                text.setText(Double.toString(result_double));
            } catch (NumberFormatException ex) {
                text.setText("Error: Invalid input");
            }
        }
        // "◀" 버튼 클릭 시
        else if (btn_s.equals("◀")) {
            if (currentText.length() > 1) {
                currentText = currentText.substring(0, currentText.length() - 1);
                text.setText(currentText);
            } else {
                text.setText("0");
            }
        }
        // "1/x" 버튼 클릭 시
        else if (btn_s.equals("1/x")) {
            try {
                result = text.getText();
                result_double = 1 / Double.parseDouble(result);
                text.setText(Double.toString(result_double));
            } catch (NumberFormatException ex) {
                text.setText("Error: Invalid input");
            }
        }
        // "x²" 버튼 클릭 시
        else if (btn_s.equals("x²")) {
            try {
                result = text.getText();
                if (result.contains(".")) {
                    result_double = Double.parseDouble(result) * Double.parseDouble(result);
                    text.setText(Double.toString(result_double));
                } else {
                    result_int = Integer.parseInt(result) * Integer.parseInt(result);
                    text.setText(Integer.toString(result_int));
                }
            } catch (NumberFormatException ex) {
                text.setText("Error: Invalid input");
              
            }
        }
        // "√" 버튼 클릭 시
        else if (btn_s.equals("√")) {
            try {
                result = text.getText();
                result_double = Math.sqrt(Double.parseDouble(result));
                text.setText(Double.toString(result_double));
            } catch (NumberFormatException ex) {
                text.setText("Error: Invalid input");
            }
        }
        // "+/-" 버튼 클릭 시 @수정 result 값에 -를 안넣고 저장함
        else if (btn_s.equals("+/-")) {
            result = text.getText();
            if (result.indexOf("-") == -1) {
            	result="-"+result;
                text.setText(result);
            } else {
                text.setText(result.substring(1));
            }
        }
        // "." 버튼 클릭 시
        else if (btn_s.equals(".")) {
            result = text.getText();
            if (!result.contains(".")) {
            	 result =result + ".";
                text.setText(result);
            }
        }
        // 숫자 버튼 클릭 시
        else {
            if (currentText.equals("0")) {
                text.setText(btn_s);  // 처음 숫자 입력
            } else {
                text.setText(currentText + btn_s);  // 기존 숫자 뒤에 추가
            }
            result = text.getText();  // 현재 텍스트 필드의 값을 result에 업데이트
        }
        expresstext.setText(expression);  // 계산식 업데이트
    };

    /**
     * Calculator 클래스의 생성자. 
     * GUI 구성 요소를 초기화하고 기본 설정을 적용합니다.
     */
    public Calcualator() {
        result = "0";
        text = new JTextField("0");
        text.setEditable(false);
        text.setFont(new Font("Dialog", Font.PLAIN, 55));
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setBorder(null);  // 경계 제거

        // 식을 보여줄 텍스트 필드 추가
        expresstext = new JTextField();
        expresstext.setEditable(false);
        expresstext.setFont(new Font("Dialog", Font.PLAIN, 30));
        expresstext.setForeground(Color.gray);
        expresstext.setHorizontalAlignment(JTextField.RIGHT);
        expresstext.setBorder(null);  // 경계 제거

        // 패널 생성 및 배경색 설정
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);  // 배경색을 흰색으로 설정
        panel.add(expresstext, BorderLayout.NORTH);
        panel.add(text, BorderLayout.CENTER);
        
        add(panel, BorderLayout.NORTH);
        
        showButton();
        setTitle("계산기-2021011983-김유건");
        setSize(400, 540);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 버튼을 생성하고 패널에 추가합니다.
     */
    void showButton() {
        JPanel jp1 = new JPanel(new GridLayout(6, 4, 3, 3));
        
        for (int i = 0; i < 24; i++) {
            jbutton[i] = new JButton(button_text[i]);
            jbutton[i].setBackground(Color.white);
            jbutton[i].setFont(new Font("Noto Sans", Font.PLAIN, 17));
            jbutton[i].addActionListener(listener);
            jp1.add(jbutton[i]);
        }
        
        jbutton[23].setForeground(Color.white);
        jbutton[23].setBackground(new Color(0, 103, 192));
        add(jp1, BorderLayout.CENTER);
    }

    /**
     * 프로그램의 진입점. 
     * Calculator 인스턴스를 생성하여 애플리케이션을 시작합니다.
     * 
     * @param args 명령줄 인수
     */
    public static void main(String[] args) {
        new Calcualator();
    }
}