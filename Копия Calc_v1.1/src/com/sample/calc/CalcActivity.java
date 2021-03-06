package com.sample.calc;
import java.math.BigDecimal;
import java.lang.ArithmeticException;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.view.Window;

public class CalcActivity extends Activity
implements OnClickListener, CompoundButton.OnCheckedChangeListener
{
	private
	TextView mED;
	ToggleButton button_memory;
	boolean Operation = false;
	boolean OperRavno = false;
	boolean Fg = false;
	boolean Div_zero = false;
	int FlagMP = -1;
	int i = 0;
	int out = 0;
	int Point = 0;
	int Output = 0;
	int test1, test2;
	double Value_div_1 = 0;
	double Value_div_2 = 0;
	BigDecimal  Value_1;
	BigDecimal Value_2;
	double Answer = 0;
	String AnsMemory = ""; 
	String Symbol = "";
	String[] Example = new String[17];
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        mED = (TextView)findViewById(R.id.ET1);
        button_memory = (ToggleButton)findViewById(R.id.Memory_copy);
        final Button button_1 = (Button)findViewById(R.id.But_1);
        final Button button_2 = (Button)findViewById(R.id.But_2);
        final Button button_3 = (Button)findViewById(R.id.But_3);
        final Button button_4 = (Button)findViewById(R.id.But_4);
        final Button button_5 = (Button)findViewById(R.id.But_5);
        final Button button_6 = (Button)findViewById(R.id.But_6);
        final Button button_7 = (Button)findViewById(R.id.But_7);
        final Button button_8 = (Button)findViewById(R.id.But_8);
        final Button button_9 = (Button)findViewById(R.id.But_9);
        final Button button_0 = (Button)findViewById(R.id.But_0);
        final Button button_M = (Button)findViewById(R.id.But_M);
        final Button button_T = (Button)findViewById(R.id.But_T);
        final Button button_Umnoj = (Button)findViewById(R.id.But_umnoj);
        final Button button_Ravno = (Button)findViewById(R.id.But_ravno);
        final Button button_Del = (Button)findViewById(R.id.But_del);
        final Button button_Plus = (Button)findViewById(R.id.But_plus);
        final Button button_Minus = (Button)findViewById(R.id.But_minus);
        final Button button_Clear = (Button)findViewById(R.id.Clear);
        final Button button_memory_paste = (Button)findViewById(R.id.Memory_paste);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_Umnoj.setOnClickListener(this);
        button_Ravno.setOnClickListener(this);
        button_Del.setOnClickListener(this);
        button_Plus.setOnClickListener(this);
        button_Minus.setOnClickListener(this);
        button_Clear.setOnClickListener(this);
        button_M.setOnClickListener(this);
        button_T.setOnClickListener(this);
        button_memory_paste.setOnClickListener(this);
        button_memory.setOnCheckedChangeListener(this);
        for (int i = 0; i < Example.length; i++) Example[i] = "";
		
        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
		//input figure
		case R.id.But_1:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "1";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_2:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "2";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_3:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "3";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_4:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "4";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_5:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "5";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_6:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "6";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_7:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "7";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_8:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "8";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_9:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
			Example[i] = Example[i] + "9";
			mED.setText("");
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_0:
			if (OperRavno == true)
			{	
				for (int i = 0; i < Example.length; i++)
				Example[i] = "";
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			Operation = true;
				Example[i] = Example[i] + "0";
				mED.setText("");
				for (int i = 0; i < Example.length; i++)
					if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
					else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		//input operational
		case R.id.But_umnoj:
			OperRavno = false;
			if (Operation == true & Example[i] != "-" & i != Example.length-1)
			{
				i++;
				Example[i] = "*";
				mED.setText("");
				for (int i = 0; i < Example.length; i++) 
					if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
					else mED.setText(mED.getText().toString()+"("+Example[i]+")");
				Operation = false;
				i++;
			}
			break;
		case R.id.But_del:
			OperRavno = false;
			if (Operation == true & Example[i] != "-" & i != Example.length-1)
			{
				i++;
				Example[i] = ":";
				mED.setText("");
				for (int i = 0; i < Example.length; i++) 
					if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
					else mED.setText(mED.getText().toString()+"("+Example[i]+")");
				Operation = false;
				i++;
			}
			break;
		case R.id.But_plus:
			OperRavno = false;
			if (Operation == true & Example[i] != "-" & i != Example.length-1)
			{
				i++;
				Example[i] = "+";
				mED.setText("");
				for (int i = 0; i < Example.length; i++) 
					if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
					else mED.setText(mED.getText().toString()+"("+Example[i]+")");
				Operation = false;
				i++;
			}
			break;
		case R.id.But_minus:
			OperRavno = false;
			if (Operation == true & Example[i] != "-" & i != Example.length-1)
			{
				i++;
				Example[i] = "-";
				mED.setText("");
				for (int i = 0; i < Example.length; i++) 
					if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
					else mED.setText(mED.getText().toString()+"("+Example[i]+")");
				Operation = false;
				i++;
			}
			break;
		case R.id.But_ravno:
		
		{
			for (int out = 0; out < Example.length; out++) 
			{
			
				if (out == Example.length-1) 
					{if (Example[out] == "*" || Example[out] == ":" || Example[out] == "+" || Example[out] == "-") Example[out] = "";}
				else
				{
					if (Example[out] == "*" || Example[out] == ":" || Example[out] == "+" || Example[out] == "-")
					{	
						if (Example[out+1] == "-") Example[out+1] = "";
						if (Example[out+1] == "") Example[out] = "";
					}		
					
					
				}
			Symbol = Example[out];
			if (Symbol == "*")
			{				
				Value_1 = new BigDecimal(Example[out-1]);
				Value_2 = new BigDecimal(Example[out+1]);
				Answer = Value_1.multiply(Value_2).doubleValue();
				Example[out-1] = Double.toString(Answer);
				out = out - 1;
				for (int in = out+1; in < Example.length; in++) 
					{
						if (in == Example.length-1 || in == Example.length-2) 
						{
							Example[in] = "";
						}
						else 
						{
							Example[in] = Example[in+2];
							Example[in+2] = "";					
						}
						
					}
				
			}
			
			if (Symbol == ":")
			
			{
				Value_div_1 = Float.valueOf(Example[out-1].trim()).floatValue();
				Value_div_2 = Float.valueOf(Example[out+1].trim()).floatValue();
				if (Value_div_2 == 0) 
				{
					test1 = (int)Value_div_1;
					test2 = (int)Value_div_2;
					try
					{
						Answer = test1/test2;
					}
					catch (ArithmeticException e)
					{
						mED.setText("������� �� ����!");
						Div_zero = true;
						break;
					}
				}
				else Answer = Value_div_1 / Value_div_2;
				Example[out-1] = Double.toString(Answer);
				out = out - 1;
				for (int in = out+1; in < Example.length; in++) 
					{
						if (in == Example.length-1 || in == Example.length-2) 
						{
							Example[in] = "";
						}
						else 
						{
							Example[in] = Example[in+2];
							Example[in+2] = "";					
						}
						
					}
			}
			
			}	
			
			
			}
			for (int out = 0; out < Example.length; out++) 
			{
				
				Symbol = Example[out];
				if (Symbol == "+")
			{				
				Value_1 = new BigDecimal(Example[out-1]);
				Value_2 = new BigDecimal(Example[out+1]);
				Answer = Value_1.add(Value_2).doubleValue();
				Example[out-1] = Double.toString(Answer);
				out = out - 1;
				for (int in = out+1; in < Example.length; in++) 
					{
						if (in == Example.length-1 || in == Example.length-2) 
						{
							Example[in] = "";
						}
						else 
						{
							Example[in] = Example[in+2];
							Example[in+2] = "";					
						}
						
					}
				
			}
			if (Symbol == "-")
			{				
				Value_1 = new BigDecimal(Example[out-1]);
				Value_2 = new BigDecimal(Example[out+1]);
				Answer = Value_1.subtract(Value_2).doubleValue();
				Example[out-1] = Double.toString(Answer);
				out = out - 1;
				for (int in = out+1; in < Example.length; in++) 
					{
						if (in == Example.length-1 || in == Example.length-2) 
						{
							Example[in] = "";
						}
						else 
						{
							Example[in] = Example[in+2];
							Example[in+2] = "";					
						}
						
					}
				
			}	
			
			
			}
			if (Div_zero) break;
			mED.setText("= ");
			Value_1 = new BigDecimal(Answer);
			Answer = Value_1.setScale(6, BigDecimal.ROUND_HALF_DOWN).doubleValue();
			Example[0] = Double.toString(Answer);
			
							
			Output = (int)Answer;
			if (Answer - Output == 0) Example[0] = Integer.toString(Output);
			for (int i = 0; i < Example.length; i++) 
				if (Example[i].indexOf("-") == -1) mED.setText(mED.getText().toString()+Example[i]);
				else mED.setText(mED.getText().toString()+"("+Example[i]+")");	
			i = 0;
			Operation = true;
			OperRavno = true;
						
			break;
		case R.id.But_T:			
			Point = Example[i].indexOf(".")+1;
			
			if (OperRavno == true)
			{
				OperRavno = false;
				i = 0;
				FlagMP = -1;
			}
			if (Point == 0)
				if	(Example[i] == "" || Example[i] == "-")
				{
					Example[i] = Example[i] + "0.";
					mED.setText("");
					for (int i = 0; i < Example.length; i++) 
						if (Example[i].indexOf("-") == -1|| Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
						else mED.setText(mED.getText().toString()+"("+Example[i]+")");
				}
				else 
				{
					Example[i] = Example[i] + ".";
					mED.setText("");
					for (int i = 0; i < Example.length; i++) 
						if (Example[i].indexOf("-") == -1|| Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
						else mED.setText(mED.getText().toString()+"("+Example[i]+")");
				}
			break;
		case R.id.Clear:
			for (int i = 0; i < Example.length; i++) 
				Example[i] = "";
				Operation = false;
				OperRavno = false;
				mED.setText("");
				Answer = 0;
				i = 0;
				FlagMP = -1;
				Div_zero = false;
			break;	
		case R.id.Memory_paste:
				Operation = true;
				Example[i] = AnsMemory;
				mED.setText("");
				for (int i = 0; i < Example.length; i++) 
					if (Example[i].indexOf("-") == -1 || Example[i] == "-") mED.setText(mED.getText().toString()+Example[i]);
					else mED.setText(mED.getText().toString()+"("+Example[i]+")");
			break;
		case R.id.But_M:
			if (Example[i] != "" & Example[i] != "-")
			{
				Value_1 = new BigDecimal(Example[i]);
				Value_2 = new BigDecimal(-1);
				Answer = Value_1.multiply(Value_2).doubleValue();
				if (Answer == (int) Answer) Example[i] = Integer.toString((int)Answer);
				else Example[i] = Double.toString(Answer);
			}
			else 
				if (Example[i] == "") Example[i] = "-";
				else Example[i] = "";
				
			
			mED.setText("");
			if (Example[0].indexOf("-") == -1) mED.setText(mED.getText().toString() + Example[0]);
			else mED.setText(mED.getText().toString() +"("+ Example[0]+")");
			for (int i = 1; i < Example.length; i++)
			{
				if (Example[i].indexOf("-") == -1)
					mED.setText(mED.getText().toString() + Example[i]);
				else
					if (Example[i-1] != "-" & Example[i-1] != "+" & Example[i-1] != "*" & Example[i-1] != ":")
						mED.setText(mED.getText().toString() + Example[i]);
					else
						if (Example[i-1] == "-" || Example[i-1] == "+" || Example[i-1] == "*" || Example[i-1] == ":")
							mED.setText(mED.getText().toString() +"("+ Example[i]+")");
				
			}
			
				
			
				
			
			
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}

	

	
}