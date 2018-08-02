package top.common.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import top.zhkumanage.equip.entity.EquipMSG;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileUtil {
    public static File creatFile(String filename, String filetype, String fileURL){
        File file = new File(fileURL + "/" + filename + filetype);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File creatXMLFile(String filename, String fileURL, EquipMSG equipMSG){
        File file = FileUtil.creatFile(filename,".xml",fileURL);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element myequip = document.createElement("myequip");
            myequip.setAttribute("id", String.valueOf(equipMSG.getIdEquip()));
            myequip.setAttribute("type",equipMSG.getTypeEquip());
            Element equipmsg = document.createElement("equipmsg");
            equipmsg.setAttribute("time", DateUtils.getTimeByStr());
            Element status = document.createElement("status");
            status.setTextContent(String.valueOf(equipMSG.getStatusEquip()));
            equipmsg.appendChild(status);
            Element message = document.createElement("message");
            message.setTextContent(equipMSG.getMessage());
            equipmsg.appendChild(message);
            myequip.appendChild(equipmsg);
            document.appendChild(myequip);

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT,"yes");
            tf.transform(new DOMSource(document),new StreamResult(file));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void updateXMLFile(File file){

    }
}
