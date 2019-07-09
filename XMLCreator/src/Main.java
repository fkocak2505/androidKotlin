
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException,
            TransformerException {

        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw e;
        }

        Document document = builder.newDocument();

        Element rootLayut = document.createElement("LinearLayout");
        rootLayut.setAttribute("xmlns:android","http://schemas.android.com/apk/res/android");
        rootLayut.setAttribute("xmlns:app","http://schemas.android.com/apk/res-auto");
        rootLayut.setAttribute("xmlns:tools","http://schemas.android.com/tools");
        rootLayut.setAttribute("android:layout_width","match_parent");
        rootLayut.setAttribute("android:layout_height","match_parent");
        rootLayut.setAttribute("android:orientation","vertical");
        rootLayut.setAttribute("tools:context",".MainActivity");

        document.appendChild(rootLayut);
        Element textView = document.createElement("TextView");
        textView.setAttribute("android:id", "@+id/textView1");
        textView.setAttribute("android:layout_width", "200dp");
        textView.setAttribute("android:layout_height", "200dp");
        textView.setAttribute("android:gravity", "center");
        textView.setAttribute("android:text", "Fatih Koçak");

        Element textView1 = document.createElement("TextView");
        textView1.setAttribute("android:id", "@+id/textView");
        textView1.setAttribute("android:layout_width", "200dp");
        textView1.setAttribute("android:layout_height", "200dp");
        textView1.setAttribute("android:gravity", "center");
        textView1.setAttribute("android:text", "Android Developer");

        // add componnet node to rootLayout node
        rootLayut.appendChild(textView);
        rootLayut.appendChild(textView1);

        // write xml
        Transformer transformer;

        try {
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            transformer = transformerFactory.newTransformer();
            Result output = new StreamResult(new File("codippa.xml"));
            Source input = new DOMSource(document);
            // if you want xml to be properly formatted
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(input, output);
        } catch (TransformerConfigurationException e) {
            throw e;
        } catch (TransformerException e) {
            throw e;
        }
    }
}
