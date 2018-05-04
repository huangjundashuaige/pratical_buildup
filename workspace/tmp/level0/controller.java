package level0;

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
    public void plus(ActionEvent E){
        second.setText("+");
    }

    @FXML
    public void minus(ActionEvent E){
        second.setText("-");
    }

    @FXML
    public void multify(ActionEvent E){
        second.setText("×");
    }

	@FXML
	public void divide(ActionEvent E)
	{
		second.setText("/");
	}
	@FXML
	public void first_change(ActionEvent E)
	{
	}
		@FXML
	public void three_change(ActionEvent E)
	{
	}
    @FXML
    public void equal(ActionEvent E){
    	double a;
    	double b;
    	try
    	{
        a = Double.parseDouble(first.getText());
        b = Double.parseDouble(three.getText());
        }
        catch(Exception e)
        {
        	five.setText("null");
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
