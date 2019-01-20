package edu.kvcc.cis298.temperatureconverter;

import android.content.Context;

import java.util.Locale;

public class Conversion {

    // String to represent the formula
    private String mFormula;

    // Handle to the app context
    private Context mAppContext;

    // Constructor
    public Conversion(Context TemperatureContext) {
        mFormula = "";
        mAppContext = TemperatureContext;
    }

    // Public getter for the formula
    public String getFormula() {
        return mFormula;
    }

    // Public conversion method
    public String ConvertTemperature(int From, int To, double TemperatureToConvert) {

        String returnString = "";

        switch (From) {
            case R.id.from_celsius:
                returnString = this.ConvertCelsiusTo(To, TemperatureToConvert);
                break;

            case R.id.from_fahrenheit:
                returnString = this.ConvertFahrenheitTo(To, TemperatureToConvert);
                break;

            case R.id.from_kelvin:
                returnString = this.ConvertKelvinTo(To, TemperatureToConvert);
                break;
        }

        return returnString;
    }

    // Private method do convert celsius to something
    private String ConvertCelsiusTo(int To, double Celsius)
    {
        String returnString = "";

        switch (To) {

            case R.id.to_fahrenheit:
                returnString = this.ConvertCelsiusToFahrenheit(Celsius);
                break;

            case R.id.to_kelvin:
                returnString = this.ConvertCelsiusToKelvin(Celsius);
                break;
        }

        return returnString;
    }

    // Private method to convert Fahrenheit to something
    private String ConvertFahrenheitTo(int To, double Fahrenheit)
    {
        String returnString = "";

        switch (To) {

            case R.id.to_celsius:
                returnString = this.ConvertFahrenheitToCelsius(Fahrenheit);
                break;

            case R.id.to_kelvin:
                returnString = this.ConvertFahrenheitToKelvin(Fahrenheit);
                break;
        }

        return returnString;
    }

    // Private method to convert Kelvin to something.
    private String ConvertKelvinTo(int To, double Kelvin)
    {
        String returnString = "";

        switch (To) {

            case R.id.to_celsius:
                returnString = this.ConvertKelvinToCelsius(Kelvin);
                break;

            case R.id.to_fahrenheit:
                returnString = this.ConvertKelvinToFahrenheit(Kelvin);
                break;
        }

        return returnString;
    }

    // Private methods to do all of the conversions between the various types.
    private String ConvertCelsiusToFahrenheit(double Celsius) {
        // Get the formula from the strings.xml
        mFormula = mAppContext.getString(R.string.c_to_f_formula);
        // Calculate the result
        double result = Celsius * (9.0/5.0) + 32.0;
        // Get the resultString from strings.xml
        String resultString = mAppContext.getString(R.string.c_to_f_result);
        // Put the entire result string together using the from temp and result.
        return String.format(Locale.getDefault(), resultString, Celsius, result);
    }

    private String ConvertCelsiusToKelvin(double Celsius) {
        // Get the formula from the strings.xml
        mFormula = mAppContext.getString(R.string.c_to_k_formula);
        // Calculate the result
        double result = Celsius + 273.15;
        // Get the resultString from strings.xml
        String resultString = mAppContext.getString(R.string.k_to_c_result);
        // Put the entire result string together using the from temp and result.
        return String.format(Locale.getDefault(), resultString, Celsius, result);
    }


    private String ConvertFahrenheitToCelsius(double Fahrenheit) {
        // Get the formula from the strings.xml
        mFormula = mAppContext.getString(R.string.f_to_c_formula);
        // Calculate the result
        double result = (Fahrenheit - 32.0)/(9.0/5.0);
        // Get the resultString from strings.xml
        String resultString = mAppContext.getString(R.string.f_to_c_result);
        // Put the entire result string together using the from temp and result.
        return String.format(Locale.getDefault(), resultString, Fahrenheit, result);
    }

    private String ConvertFahrenheitToKelvin(double Fahrenheit) {
        // Get the formula from the strings.xml
        mFormula = mAppContext.getString(R.string.f_to_k_formula);
        // Calculate the result
        double result = (Fahrenheit + 459.67)/(9.0/5.0);
        // Get the resultString from strings.xml
        String resultString = mAppContext.getString(R.string.f_to_k_result);
        // Put the entire result string together using the from temp and result.
        return String.format(Locale.getDefault(), resultString, Fahrenheit, result);
    }


    private String ConvertKelvinToCelsius(double Kelvin) {
        // Get the formula from the strings.xml
        mFormula = mAppContext.getString(R.string.k_to_c_formula);
        // Calculate the result
        double result = Kelvin - 273.15;
        // Get the resultString from strings.xml
        String resultString = mAppContext.getString(R.string.k_to_c_result);
        // Put the entire result string together using the from temp and result.
        return String.format(Locale.getDefault(), resultString, Kelvin, result);
    }

    private String ConvertKelvinToFahrenheit(double Kelvin) {
        // Get the formula from the strings.xml
        mFormula = mAppContext.getString(R.string.k_to_f_formula);
        // Calculate the result
        double result = (Kelvin * (9.0/5.0)) - 459.67;
        // Get the resultString from strings.xml
        String resultString = mAppContext.getString(R.string.k_to_f_result);
        // Put the entire result string together using the from temp and result.
        return String.format(Locale.getDefault(), resultString, Kelvin, result);
    }
}
