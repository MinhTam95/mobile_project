package com.blogspot.hongthaiit.xmlandasynctask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherActivity extends Activity {

	private final static String url = "http://api.openweathermap.org/data/2.5/weather?q=hanoi&mode=xml";
	private TextView location, country, temperature, humidity, pressure;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);

		location = (TextView) findViewById(R.id.location);
		country = (TextView) findViewById(R.id.country);
		temperature = (TextView) findViewById(R.id.temperature);
		humidity = (TextView) findViewById(R.id.humidity);
		pressure = (TextView) findViewById(R.id.pressure);

		new GetWeatherDataTask(this, url).execute();
	}

	public void callBackData(String[] result) {
		temperature.setText((Float.parseFloat(result[2]) - 273) + " degree Celcius" );
		humidity.setText(result[0] + " %");
		pressure.setText(result[1] + " hPa");
		country.setText(result[4]);
		location.setText(result[3]);
	}
}