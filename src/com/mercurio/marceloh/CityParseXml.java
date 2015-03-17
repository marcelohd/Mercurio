package com.mercurio.marceloh;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mercurio.marceloh.entity.City;
import com.mercurio.marceloh.entity.Country;
import com.mercurio.marceloh.managedBean.CityBean;
import com.mercurio.marceloh.managedBean.CountryBean;

public class CityParseXml { 
	
	public static void main (String args[]){

	City city = new City();
	CityBean bean = new CityBean();
	
	Object[] botoes = { "Sim", "Não" };  
	//int resposta = JOptionPane.showOptionDialog(null,  
	      //  "Deseja inserir as Cidades?",  
	      //  "Alimentar Banco", // 
	       // JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  botoes, botoes[0]);  
	  
	//if(resposta == 0){  
		try{
			File fileXML = new File("xml/municipio.xml");
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(fileXML);
			
			document.getDocumentElement().normalize();
			
			//System.out.println("Root element :" + document.getDocumentElement().getNodeName());
			NodeList nodeList = document.getElementsByTagName("MUNICIPIO");
			
			for(int temp = 0; temp < nodeList.getLength(); temp ++ ){
				Node node = nodeList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;	
					
					city.setName(element.getAttribute("NOMUNICIPIO"));
					city.setIbge(Integer.parseInt(element.getAttribute("CDMUNICIPIO")));
					city.setState(element.getAttribute("CDSGUF"));
					city.setTrash(false);	
					bean.onSave(city);										
				}	
			}			
		}catch(Exception e){
			e.printStackTrace();
		} 
	//}else if(resposta == 1){  
	//    JOptionPane.showMessageDialog(null, "NãO");  
	//}
}

}
