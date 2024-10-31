package task1;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calcualator extends JFrame {
    String[] button_text = { "%", "CE", "C", "X", "1/x", "x^2", "2/x", "/", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "=" };
    JButton[] jbutton = new JButton[25];
    JTextField text;
    JTextField expressionText;  // 계산식을 보여줄 텍스트 필드
    String int_double;
    String result = "0";
    String state = null;
    String expression = "";  // 연산 과정을 저장할 변수
    int result_int;
    double result_double;
    
    ActionListener listener = e -> {
        String currentText = text.getText();
        String btn_s = ((JButton) e.getSource()).getText();
        
        if (btn_s.equals("=")) {
            expression += currentText + " = ";  // 최종 식에 현재 값과 "=" 추가
            
            if (result == null || currentText.isEmpty()) {
                return;
            }
            
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
                text.setText("Error");
            }

            result = "";
            state = null;
            expression = "";  // 계산 후 식 초기화
            expressionText.setText("");  // 식 텍스트 초기화
        }
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
            text.setText("0");
            expressionText.setText(expression);  // 계산식 업데이트
        }
        else if (btn_s.equals("C")) {
            result = "0";
            result_int = 0;
            result_double = 0;
            state = null;
            expression = "";  // 식 초기화
            expressionText.setText("");  // 식 텍스트 초기화
            text.setText("0");
        }
        else if (btn_s.equals("CE")) {
            text.setText("0");
        }
        else if (btn_s.equals("%")) {
            try {
                double percentageResult = Double.parseDouble(currentText) / 100.0;
                text.setText(Double.toString(percentageResult));
            } catch (NumberFormatException ex) {
                text.setText("Error");
            }
        }
        else if (btn_s.equals("X")) {
            if (currentText.equals("0")) {
                text.setText("0");
            } else {
                currentText = currentText.substring(0, currentText.length() - 1);
                text.setText(currentText);
                if (text.getText().length() == 0) {
                    text.setText("0");
                }
            }
        }
        else if (btn_s.equals("1/x")) {
            result = text.getText();
            result_double = (1 / Double.parseDouble(result));
            text.setText(Double.toString(result_double));
        }
        else if (btn_s.equals("x^2")) {
            result = text.getText();
            if (result.contains(".")) {
                result_double = Double.parseDouble(result) * Double.parseDouble(result);
                text.setText(Double.toString(result_double));
            } else {
                result_int = Integer.parseInt(result) * Integer.parseInt(result);
                text.setText(Integer.toString(result_int));
            }
        }
        else if (btn_s.equals("2/x")) {
            result = text.getText();
            result_double = Math.sqrt(Double.parseDouble(result));
            text.setText(Double.toString(result_double));
        }
        else if (btn_s.equals("+/-")) {
            result = text.getText();
            if (result.indexOf("-") == -1) {
                text.setText("-" + result);
            } else {
                text.setText(result.substring(1));
            }
        }
        else if (btn_s.equals(".")) {
            result = text.getText();
            if (!result.contains(".")) {
                text.setText(result + ".");
            }
        }
        else {
            if (currentText.equals("0")) {
                text.setText(btn_s);
            } else {
                text.setText(currentText + btn_s);
            }
        }
        
        expressionText.setText(expression);  // 계산식 업데이트
    };

    public Calcualator() {
        result = "0";
        text = new JTextField("0");
        text.setEditable(false);
        text.setFont(new Font("Dialog", Font.PLAIN, 55));
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setBorder(null);  // 경계 제거

        // 식을 보여줄 텍스트 필드 추가
        expressionText = new JTextField();
        expressionText.setEditable(false);
        expressionText.setFont(new Font("Dialog", Font.PLAIN, 30));
        expressionText.setForeground(Color.gray);
        expressionText.setHorizontalAlignment(JTextField.RIGHT);
        expressionText.setBorder(null);  // 경계 제거

        // 패널 생성 및 배경색 설정
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);  // 배경색을 흰색으로 설정
        panel.add(expressionText, BorderLayout.NORTH);
        panel.add(text, BorderLayout.CENTER);
        
        add(panel, BorderLayout.NORTH);
        
        showButton();
        setSize(400, 540);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void showButton() {
        JPanel jp1 = new JPanel(new GridLayout(6, 4, 3, 3));
        for (int i = 0; i < 24; i++) {
            jp1.add(jbutton[i] = new JButton(button_text[i]));
            jbutton[i].setBackground(Color.white);
            jbutton[i].setFont(new Font("Noto Sans", Font.PLAIN, 17));
            jbutton[i].addActionListener(listener);
        }
        jbutton[23].setForeground(Color.white);
        jbutton[23].setBackground(new Color(0, 103, 192));
        add(jp1, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Calcualator();
    }
}
