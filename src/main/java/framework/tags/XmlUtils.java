package framework.tags;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtils {

    // Update an already existing XML
    public static String updateXML(String xml, String nodeNamn, String nodeVarde) {

        Document doc = StringToDocument(xml);
        doc = updateNodeValue(doc, nodeNamn, nodeVarde);
        String newxml = DocumentToString(doc);
        return newxml;
    }

    // Convert a java string to a xml document
    private static Document StringToDocument(String strXml) {

        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader strReader = new StringReader(strXml);
            InputSource is = new InputSource(strReader);
            doc = (Document) builder.parse(is);
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return doc;
    }

    // Convert XML document to a java string
    private static String DocumentToString(Document doc) {

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        try {
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        String output = writer.getBuffer().toString();
        return output;
    }

    // Add an entirely new node
    private static Document addNode(Document doc, String nodeNamnAttLaggTill, String nodeVardeAttSatta) {
        Node rootNode = doc.getFirstChild();
        //Document dom = parent.getOwnerDocument();

        // Create a new Node with the given tag name

        Node node = doc.createElement(nodeNamnAttLaggTill);

        //Add the node value as a child text node
        Text nodeVal = doc.createTextNode(nodeVardeAttSatta);
        Node c = node.appendChild(nodeVal);

        // Add the new node structure to the parent node
        rootNode.appendChild(node);
        return doc;
    }

    // Update a value in a specific node
    private static Document updateNodeValue(Document doc, String nodeNamnAttUppdatera, String nodeVardeAttSatta) {

        Node rootNode = doc.getFirstChild();
        NodeList list = rootNode.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node node = (Node) list.item(i);
            //Element element = (Element) list.item(i);
            //Node node = list.item(i);
            if (nodeNamnAttUppdatera.equals(node.getNodeName())) {
                node.setTextContent(nodeVardeAttSatta);
                return doc;
            }
        }
        //Om vi hamnar här då har vi inte hittat ett element att uppdatera, då ska vi lägga till en nytt element
        doc = addNode(doc, nodeNamnAttUppdatera, nodeVardeAttSatta);
        return doc;
    }
}
