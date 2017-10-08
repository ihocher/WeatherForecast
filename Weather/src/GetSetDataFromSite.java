import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetSetDataFromSite {

	private Map<String, String> citiesAndUrls = new HashMap<String, String>();
	private ArrayList<DailyDataBase> threeDaysBase = new ArrayList<DailyDataBase>();
	private static String city;
	private String element = "day";
	private static boolean mousePressed;
	private String str;

	protected String getStr() {
		return str;
	}

	protected static void setMousePressed(boolean mousePressed) {
		GetSetDataFromSite.mousePressed = mousePressed;
	}

	protected boolean isMousePressed() {
		return mousePressed;
	}

	protected ArrayList<DailyDataBase> getThreeDaysBase() {
		return threeDaysBase;
	}

	protected static void setCity(String city) {
		GetSetDataFromSite.city = city;
	}

	{
		citiesAndUrls.put("вінниця", "http://gazeta.ua/weather/vinitca");
		citiesAndUrls.put("полтава", "http://gazeta.ua/weather/poltava");
		citiesAndUrls.put("дніпро", "http://gazeta.ua/weather/dnepropetrovsk");
		citiesAndUrls.put("донецьк", "http://gazeta.ua/weather/doneck");
		citiesAndUrls.put("житомир", "http://gazeta.ua/weather/zhitomir");
		citiesAndUrls.put("запоріжжя", "http://gazeta.ua/weather/zaporizhe");
		citiesAndUrls.put("івано-франківськ", "http://gazeta.ua/weather/ivano-frankovsk");
		citiesAndUrls.put("київ", "http://gazeta.ua/weather/kiev");
		citiesAndUrls.put("кіровоград", "http://gazeta.ua/weather/kirovograd");
		citiesAndUrls.put("луганськ", "http://gazeta.ua/weather/lugansk");
		citiesAndUrls.put("львів", "http://gazeta.ua/weather/lvov");
		citiesAndUrls.put("луцьк", "http://gazeta.ua/weather/luck");
		citiesAndUrls.put("миколаїв", "http://gazeta.ua/weather/nikolaev");
		citiesAndUrls.put("одеса", "http://gazeta.ua/weather/odessa");
		citiesAndUrls.put("рівне", "http://gazeta.ua/weather/rivne");
		citiesAndUrls.put("севастопіль", "http://gazeta.ua/weather/sevastopol");
		citiesAndUrls.put("сімферопіль", "http://gazeta.ua/weather/simferopol");
		citiesAndUrls.put("суми", "http://gazeta.ua/weather/sumi");
		citiesAndUrls.put("тернопіль", "http://gazeta.ua/weather/ternopol");
		citiesAndUrls.put("ужгород", "http://gazeta.ua/weather/uzhgorod");
		citiesAndUrls.put("львів", "http://gazeta.ua/weather/lvov");
		citiesAndUrls.put("харків", "http://gazeta.ua/weather/kharkov");
		citiesAndUrls.put("херсон", "http://gazeta.ua/weather/kherson");
		citiesAndUrls.put("хмельницький", "http://gazeta.ua/weather/chmelnickiy");
		citiesAndUrls.put("черкаси", "http://gazeta.ua/weather/cherkassi");
		citiesAndUrls.put("чернігів", "http://gazeta.ua/weather/chernigov");
		citiesAndUrls.put("ялта", "http://gazeta.ua/weather/jalta");
	}

	GetSetDataFromSite() {

		StringBuffer strBuf = new StringBuffer();
		// Waiting till city will be selected
		while (!mousePressed)
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		String url = citiesAndUrls.get(city);

		try {
			getAndSetDataFromSite(url, element, city);
		} catch (IOException e) {

			e.printStackTrace();
		}

		for (int i = 0; i < threeDaysBase.size(); i++) {
			strBuf.append(threeDaysBase.get(i));

		}
		str = strBuf.toString();
		Window.setText(str);

		mousePressed = false;
		threeDaysBase.clear();

	}

	protected void getAndSetDataFromSite(String url, String element, String city) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com").timeout(1000).get();

		Elements trElements = doc.getElementsByClass(element);

		DailyDataBase today = new DailyDataBase(city);
		DailyDataBase tomorrow = new DailyDataBase(city);
		DailyDataBase dayAfterTomorrow = new DailyDataBase(city);

		for (int i = 0; i < trElements.size(); i++) {
			if (i == 1)
				today.setSky(trElements.get(i).text());
			else if (i == 2)
				today.setDownfall(trElements.get(i).text());
			else if (i == 3)
				today.setTemperature(trElements.get(i).text());
			else if (i == 5)
				tomorrow.setSky(trElements.get(i).text());
			else if (i == 6)
				tomorrow.setDownfall(trElements.get(i).text());
			else if (i == 7)
				tomorrow.setTemperature(trElements.get(i).text());
			else if (i == 9)
				dayAfterTomorrow.setSky(trElements.get(i).text());
			else if (i == 10)
				dayAfterTomorrow.setDownfall(trElements.get(i).text());
			else if (i == 11)
				dayAfterTomorrow.setTemperature(trElements.get(i).text());
		}

		today.setDate(getTodaysDate());
		tomorrow.setDate(getTomorrowDate());
		dayAfterTomorrow.setDate(getDayAfterTomorrowDate());

		threeDaysBase.add(today);
		threeDaysBase.add(tomorrow);
		threeDaysBase.add(dayAfterTomorrow);
	}

	// Get from site info about three days(today, tomorrow, day after
	// tomorrow).Write this info into array.
	public String getTodaysDate() {
		Date todaysDate = new Date();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(todaysDate);
	}

	public String getTomorrowDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		return dateFormat.format(cal.getTime());
	}

	public String getDayAfterTomorrowDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +2);
		return dateFormat.format(cal.getTime());
	}
}
