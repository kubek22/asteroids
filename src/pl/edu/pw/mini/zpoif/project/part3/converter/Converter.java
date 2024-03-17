package pl.edu.pw.mini.zpoif.project.part3.converter;
import com.github.underscore.U;

public class Converter {

	public static String convertJSONToXML(String json) {
		String xml = U.jsonToXml(json);
		return xml;
	}

}
