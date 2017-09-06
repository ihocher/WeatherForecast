import java.io.IOException;
import java.util.HashMap;

public class app {

	public static HashMap<String, Integer> catalog = new HashMap<String, Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		putAndAdd("Один", null);
		putAndAdd("Два", null);
		putAndAdd("Три", 5);
		putAndAdd("Пять", 3);
		putAndAdd("Шесть", 8);

		System.out.println(catalog);
		System.out.println(catalog.get("Три"));

	}

	public static void putAndAdd(String s, Integer i) {
		if (catalog.get(s) != null) {
			catalog.put(s, catalog.get(s) + i);
		} else {
			catalog.put(s, i);
		}

	}
}
