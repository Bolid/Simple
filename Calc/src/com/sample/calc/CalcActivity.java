package com.sample.calc;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ToggleButton;
import android.content.Intent;

public class CalcActivity extends Activity 
	implements OnClickListener, CompoundButton.OnCheckedChangeListener
{	
	private 
		boolean FlagC = true; boolean FlagUmnoj = false; boolean FlagDel = false;
		boolean FlagP = false; boolean FlagM = false;
		boolean FlagT = false; boolean FlagMP = false;
		float f;
		float Rez = 0;
		TextView mED;
		ToggleButton button_memory;
		String RezMemory = "";
		String operand = "";
		float operand_F = 0;
		int i = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
    
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//*********1-3
		case R.id.But_1:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"1";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"1";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		case R.id.But_2:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"2";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"2";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		case R.id.But_3:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"3";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"3";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		//*********4-6
		case R.id.But_4:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"4";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"4";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		case R.id.But_5:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"5";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"5";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		case R.id.But_6:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"6";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"6";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		//*********7-0
		case R.id.But_7:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"7";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"7";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		case R.id.But_8:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"8";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"8";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		case R.id.But_9:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"9";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"9";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
		case R.id.But_0:
			if (FlagC==true)
			{
				operand = mED.getText().toString()+"0";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			} else
			{
				FlagC=true;
				mED.setText("");
				operand = mED.getText().toString()+"0";
				mED.setText(operand);
				operand_F = Float.valueOf(operand.trim()).floatValue();
			}
			break;
			
		//************операции
		case R.id.But_umnoj:
			if (FlagDel==true)
			{
				Rez = Rez / operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagDel = false; FlagC = false;
			}
			if (FlagP==true)
			{
				Rez = Rez + operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagP = false; FlagC = false;
			}
			if (FlagM==true)
			{
				Rez = Rez - operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagM = false; FlagC = false;
			}
			//****************
			if (FlagUmnoj==true)
			{
				Rez = Rez * operand_F;
				mED.setText(Float.toString(Rez));
				FlagUmnoj=true; FlagC = false;
			}	else
			{
				Rez = operand_F;
				FlagC = false;
				FlagUmnoj = true;
			}
			FlagT = false; FlagMP = false;
		break;
		case R.id.But_del:	
			if (FlagUmnoj==true)
			{
				Rez = Rez * operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagUmnoj = false; FlagC = false;
			}
			if (FlagP==true)
			{
				Rez = Rez + operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagP = false; FlagC = false;
			}
			if (FlagM==true)
			{
				Rez = Rez - operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagM = false; FlagC = false;
			}
			//***************
			if (FlagDel==true)
			{
				Rez = Rez / operand_F;
				mED.setText(Float.toString(Rez));
				FlagDel=true; FlagC = false;
			}	else
			{
				Rez = operand_F;
				FlagC = false;
				FlagDel = true;
			}
			FlagT = false; FlagMP = false;
		break;
		case R.id.But_plus:	
			if (FlagUmnoj==true)
			{
				Rez = Rez * operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagUmnoj = false; FlagC = false;
			}
			if (FlagDel==true)
			{
				Rez = Rez / operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagDel = false; FlagC = false;
			}
			if (FlagM==true)
			{
				Rez = Rez - operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagM = false; FlagC = false;
			}
			//****************
			if (FlagP==true)
			{
				Rez = Rez + operand_F;
				mED.setText(Float.toString(Rez));
				FlagP=true; FlagC = false;
			}	else
			{
				Rez = operand_F;
				FlagC = false;
				FlagP = true;
			}
			FlagT = false; FlagMP = false;
		break;
		case R.id.But_minus:	
			if (FlagUmnoj==true)
			{
				Rez = Rez * operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagUmnoj = false; FlagC = false;
			}
			if (FlagDel==true)
			{
				Rez = Rez / operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagDel = false; FlagC = false;
			}
			if (FlagP==true)
			{
				Rez = Rez + operand_F; operand_F = Rez;
				mED.setText(Float.toString(Rez));
				FlagP = false; FlagC = false;
			}
			//****************
			if (FlagM==true)
			{
				Rez = Rez - operand_F;
				mED.setText(Float.toString(Rez));
				FlagM=true; FlagC = false;
			}	else
			{
				Rez = operand_F;
				FlagC = false;
				FlagM = true;
			}
			FlagT = false; FlagMP = false;
		break;
		
		
		case R.id.But_ravno:
			if (FlagUmnoj==true) 
			{
				Rez = Rez * operand_F;
				operand_F = Rez;
				mED.setText(Float.toString(Rez));		
			}
			if (FlagDel==true) 
			{
				Rez = Rez / operand_F;
				operand_F = Rez;
				mED.setText(Float.toString(Rez));		
			}
			if (FlagP==true) 
			{
				Rez = Rez + operand_F;
				operand_F = Rez;
				mED.setText(Float.toString(Rez));		
			}
			if (FlagM==true) 
			{
				Rez = Rez - operand_F;
				operand_F = Rez;
				mED.setText(Float.toString(Rez));		
			}
		FlagT = false; FlagUmnoj=false; FlagC=false; FlagDel = false;
		FlagP = false; FlagM = false; FlagMP = false; 		
		break;
		case R.id.But_T:
			operand = mED.getText().toString();
			i = operand.indexOf(".")+1;
			if (i==0)
			{
				if (operand == "") operand = "0.";
				else operand = mED.getText().toString()+".";
				
				mED.setText(operand);
			}
			
				
			
		break;
		case R.id.But_M:
			operand = mED.getText().toString();
			operand_F = Float.valueOf(operand.trim()).floatValue()*(-1);
			operand = Float.toString(operand_F);
			mED.setText(operand);
			
		break;
		
		
		
		
		case R.id.Clear:
			mED.setText("");
			operand = "";
			operand_F = 0;
			Rez = 0;
			RezMemory = "";
			FlagC = true;
			FlagDel = false;
			FlagUmnoj = false;
			FlagP = false;
			FlagM = false;
			FlagT = false;
			FlagMP = false;
			button_memory.setChecked(false);
		
		break;
		
		case R.id.Memory_paste:
		{
			if (RezMemory != "")
				operand_F = Float.valueOf(RezMemory.trim()).floatValue();
				mED.setText(RezMemory);
		}
			break;
			
		default:
			break;
		}
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (button_memory.isChecked()) 
		{
			RezMemory = mED.getText().toString();
			FlagC = false;
		}	
		
		else RezMemory = "";
	
		
			
		
	}
}