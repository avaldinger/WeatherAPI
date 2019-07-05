import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;

public class Weather extends Sendmail {
	public static void main(String[] args) throws APIException, IOException {
		
		//File file = new File("Weather.txt");
		//file.createNewFile();
		
		ArrayList<Double> temperature = new ArrayList<>();
		ArrayList<Integer> time = new ArrayList<>();
		
		
		String result = "";
		
		String from = "From address";
		String pass = "app password";
		String[] to = {"recipients addresses"};
		String subject = "Weather report_v2";

		Weather wm = new Weather();	
		Weather wm2 = new Weather();	

		OWM callweather = new OWM("You API key goes here");

		callweather.setLanguage(OWM.Language.ENGLISH);

		callweather.setUnit(OWM.Unit.METRIC);

		//System.out.println(callweather.hourlyWeatherForecastByCityName("Barcelona"));

		CurrentWeather barcelona = callweather.currentWeatherByCityName("Barcelona");

		HourlyWeatherForecast bcn = callweather.hourlyWeatherForecastByCityName("Barcelona");

		//System.out.println("City " + barcelona.getCityName() + " " + barcelona.getWindData() + " " + barcelona.getWeatherList());

		System.out.println("\nTemperature: " + barcelona.getMainData().getTemp() + "\n Humidity: "
				+ barcelona.getMainData().getHumidity() + "%");

		
		 result = "Today at "  +  barcelona.getDateTime().getHours() + ":" + barcelona.getDateTime().getMinutes() + barcelona.getDateTime().getMinutes() + "h in " + barcelona.getCityName() + ":" + "\nTemperature: " + barcelona.getMainData().getTemp() + " C" + "\n Humidity: " + barcelona.getMainData().getHumidity() + "%";
		 String body = result;
		 
		 writeToFile(body);
		 
		 temperature.add(barcelona.getMainData().getTemp());
		 time.add(barcelona.getDateTime().getHours());
		 
		 System.out.println("\n" + bcn.getCityData().getName().toUpperCase());
		 
		 
		 for(Double c : temperature) {
			 System.out.println(c);
		 }
		 for(int a : time) {
			 System.out.println(a);
		 }
		// System.out.println(barcelona.);
		 
		 
		 //wm.sendFromGMail(from, pass, to, subject, body);
	}

	public static void writeToFile(String result) {
		try {
			FileWriter fw = new FileWriter("Weather.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);

			out.print("; " + result);

			System.out.println("\n" + result);

			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
