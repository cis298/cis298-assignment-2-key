package edu.kvcc.cis298.temperatureconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TemperatureConverterActivity extends AppCompatActivity {

    // Constants for onSavedInstanceState Keys
    static final String TEMPERATURE_TO_CONVERT = "temperature_to_convert";
    static final String RESULT = "result";
    static final String FORMULA = "formula";

    // Var for conversion class
    Conversion mConversion;

    // Vars for widgets
    Button mConvertButton;
    RadioGroup mFromGroup;
    RadioGroup mToGroup;
    EditText mTemperatureToConvertEditText;
    TextView mResultTextView;
    TextView mFormulaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        // Instantiate the Conversion class
        mConversion = new Conversion(TemperatureConverterActivity.this);

        // Set view handles to all of the needed View controls
        mFromGroup = (RadioGroup) this.findViewById(R.id.from_group);
        mToGroup = (RadioGroup) this.findViewById(R.id.to_group);
        mTemperatureToConvertEditText = (EditText) this.findViewById(R.id.temperature_to_convert);
        mResultTextView = (TextView) this.findViewById(R.id.result_output);
        mFormulaTextView = (TextView) this.findViewById(R.id.formula_output);
        mConvertButton = (Button) this.findViewById(R.id.conversion_button);

        //Set onclick listener for the button
        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get the resource integer for the from and to button
                int fromButtonId = mFromGroup.getCheckedRadioButtonId();
                int toButtonId = mToGroup.getCheckedRadioButtonId();

                //Get the string for the conversion amount
                String mTemperatureToConvert = mTemperatureToConvertEditText.getText().toString();

                // Get the string for the From and To selected choices
                String fromString = ((RadioButton)findViewById(fromButtonId)).getText().toString();
                String toString = ((RadioButton)findViewById(toButtonId)).getText().toString();

                //If all of the fields are filled out, calculate the result
                if (
                    fromButtonId != -1 &&
                    toButtonId != -1 &&
                    !mTemperatureToConvert.isEmpty() &&
                    !fromString.equals(toString)
                ) {
                    String result = convertTemperature(fromButtonId, toButtonId, mTemperatureToConvert);

                    mResultTextView.setText(result);
                    mFormulaTextView.setText(mConversion.getFormula());
                } else {
                    //Something was not filled out. Toast a message to say so.
                    Toast.makeText(
                        TemperatureConverterActivity.this,
                        R.string.form_error,
                        Toast.LENGTH_LONG
                    ).show();
                }
            }
        });

        //If information exists in the savedInstanceState, pull it out and assign it.
        if (savedInstanceState != null) {
            mTemperatureToConvertEditText.setText(savedInstanceState.getString(TEMPERATURE_TO_CONVERT));
            mResultTextView.setText(savedInstanceState.getString(RESULT));
            mFormulaTextView.setText(savedInstanceState.getString(FORMULA));
        }
    }

    // Override the onSaveInstanceState method to save the temperature to convert, result, and formula.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(TEMPERATURE_TO_CONVERT, mTemperatureToConvertEditText.getText().toString());
        savedInstanceState.putString(RESULT, mResultTextView.getText().toString());
        savedInstanceState.putString(FORMULA, mFormulaTextView.getText().toString());
    }

    // Take in the integer resIds for From and To as well as the Temperature to convert
    // Convert the string temperature to a double.
    // Then use the Conversion class to convert the temperature and return the result
    // NOTE: Should not need to wrap the Double.parseDouble in a try/catch since the editText
    // collecting the string is setup to only allow signed numbers.
    private String convertTemperature(int From, int To, String TemperatureToConvert) {
        double originalTemp = Double.parseDouble(TemperatureToConvert);
        return mConversion.ConvertTemperature(From, To, originalTemp);
    }
}
