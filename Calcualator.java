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
    boolean clearExpressionAfterEqual = false;  // = 후 계산식 초기화를 위한 플래그
    
    ActionListener listener = e -> {
        String currentText = text.getText();
        String btn_s = ((JButton) e.getSource()).getText();

        // "=" 버튼 클릭 시
        /**
         * "=" 버튼을 클릭하면 현재 입력된 값과 이전 결과를 기반으로 계산을 수행합니다.
         * 연산 결과를 텍스트 필드에 표시하고, 계산식도 업데이트합니다.
         * 
         * @throws NumberFormatException 숫자 형식이 올바르지 않을 때 발생
         */
        if (btn_s.equals("=")) {
            expression += currentText + " = ";  // @see 출처: ChatGPT , 계산식에 현재 값과 "=" 추가
            clearExpressionAfterEqual = true;  // 다음 입력 시 계산식 초기화 플래그 설정

            if (result == null || currentText.isEmpty()) return;

            // 현재 값과 기존 결과의 정수/실수 여부 확인
            boolean isCurrentTextDouble = currentText.contains(".");
            boolean isResultDouble = result.contains(".");

            int_double = (!isCurrentTextDouble && !isResultDouble) ? "int" : "double";

            try {
                if (state != null) {
                    // 덧셈 연산
                    if (state.equals("+")) {
                        if (int_double.equals("int")) {
                            result_int = Integer.parseInt(result) + Integer.parseInt(currentText);
                            result = Integer.toString(result_int);
                        } else {
                            result_double = Double.parseDouble(result) + Double.parseDouble(currentText);
                            result = Double.toString(result_double);
                        }
                    }
                    // 뺄셈 연산
                    else if (state.equals("-")) {
                        if (int_double.equals("int")) {
                            result_int = Integer.parseInt(result) - Integer.parseInt(currentText);
                            result = Integer.toString(result_int);
                        } else {
                            result_double = Double.parseDouble(result) - Double.parseDouble(currentText);
                            result = Double.toString(result_double);
                        }
                    }
                    // 나눗셈 연산
                    else if (state.equals("/")) {
                        if (Double.parseDouble(currentText) == 0) {
                            text.setText("Error: Division by zero");
                            return;
                        }
                        if (int_double.equals("int")) {
                            result_int = Integer.parseInt(result) / Integer.parseInt(currentText);
                            result = Integer.toString(result_int);
                        } else {
                            result_double = Double.parseDouble(result) / Double.parseDouble(currentText);
                            result = Double.toString(result_double);
                        }
                    }
                    // 곱셈 연산
                    else if (state.equals("x")) {
                        if (int_double.equals("int")) {
                            result_int = Integer.parseInt(result) * Integer.parseInt(currentText);
                            result = Integer.toString(result_int);
                        } else {
                            result_double = Double.parseDouble(result) * Double.parseDouble(currentText);
                            result = Double.toString(result_double);
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                /**
                 * 숫자 형식이 올바르지 않을 때 발생하는 예외로, 
                 * 입력된 값이 숫자로 변환할 수 없는 경우 처리됩니다.
                 * 
                 * @see 출처: ChatGPT
                 */
                text.setText("Error: Invalid input");
                result = "0";
            }

            text.setText(result);  // 결과 표시
            state = null;  // 연산 상태 초기화
            expresstext.setText(expression);  // 계산식 표시 유지
        }
        // 연산자 버튼 클릭 시
        /**
         * "+", "-", "/", "x" 버튼을 클릭하면 현재 입력된 값과 이전 결과를 기반으로 
         * 다음 연산을 설정합니다. 연산자가 변경될 때마다 계산식을 업데이트하고, 
         * 현재 입력된 값을 기반으로 결과를 계산합니다.
         * 
         * @throws NumberFormatException 숫자 형식이 올바르지 않을 때 발생
         */
        else if (btn_s.equals("+") || btn_s.equals("-") || btn_s.equals("/") || btn_s.equals("x")) {
            if (clearExpressionAfterEqual) {
                expression = ""; // = 이후 새로운 계산 시 식 초기화
                clearExpressionAfterEqual = false;
            }

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
                            if (Integer.parseInt(currentText) != 0) {
                                result_int /= Integer.parseInt(currentText);
                            } else {
                                text.setText("Error");
                                return;
                            }
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
                            if (Double.parseDouble(currentText) != 0) {
                                result_double /= Double.parseDouble(currentText);
                            } else {
                                text.setText("Error");
                                return;
                            }
                        } else if (state.equals("x")) {
                            result_double *= Double.parseDouble(currentText);
                        }
                        text.setText(Double.toString(result_double));
                        result = Double.toString(result_double);
                    }
                } catch (NumberFormatException ex) {
                    /**
                     * 숫자 형식이 올바르지 않을 때 발생하는 예외로, 
                     * 입력된 값이 숫자로 변환할 수 없는 경우 처리됩니다.
                     * 
                     * @see 출처: ChatGPT
                     */
                    text.setText("Error");
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
            expression += currentText + " " + btn_s + " ";  
            text.setText("0");
            expresstext.setText(expression); // 계산식 업데이트
        }

        // clearExpressionAfterEqual
        /**
         * clearExpressionAfterEqual 변수는 "=" 버튼 클릭 후 다음 입력 시 
         * 계산식을 초기화하는 플래그입니다. 이 변수는 사용자가 "=" 버튼을 클릭한 후 
         * 새로운 계산을 시작할 때 이전 계산식이 남아있지 않도록 합니다.
         * @see 출처: ChatGPT
         */
        // 숫자 및 기타 버튼 처리
       
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
        // "+/-" 버튼 클릭 시 @수정 currentText 값에 -를 안넣고 저장함
        
        else if (btn_s.equals("+/-")) {
        	
            if (currentText.indexOf("-") == -1) {
            	currentText="-"+currentText;
            	System.out.println(currentText);
                text.setText(currentText);
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
            if (clearExpressionAfterEqual) {
                expression = "";  // 새 계산 시작 시 계산식 초기화
                clearExpressionAfterEqual = false;
            }
            text.setText(currentText.equals("0") ? btn_s : currentText + btn_s);  // 숫자 이어 붙이기
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