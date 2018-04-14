import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.lang.model.util.ElementScanner6;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

public class controller {

    @FXML
    Button plus;

    @FXML
    Button minus;

    @FXML
    Button multify;

    @FXML
    Button divide;

    @FXML
    Button equal;

    @FXML
    TextField first;

    @FXML
    TextField second;

    @FXML
    TextField three;
    
    @FXML
    TextField four;

    @FXML   
    TextField five;

    @FXML
    public void plus(Action E  ){
        second.setText("+");
    }

    @FXML
    public void minus(Action E  ){
        second.setText("-");
    }

    @FXML
    public void multify(Action E  ){
        second.setText("×");
    }

    @FXML
    public void equal(Action E  ){
        try
        {
        double a = Double.parseDouble(first.getText());
        double b = Double.parseDouble(second.getText());
        }
        catch(Exception e)
        {
            five.setText("null");
        }
        if(five.getText()=="null")
        {
            return;
        }
        double result = 0;
        switch (second.getText()){
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "×":
                result = a * b;
                break;
            case "÷":
                if(b==0)
                {
                    five.setText("illegal");
                }
                else
                    result = a / b;
                break;
        }
        five.setText(""+result);
    }


}
