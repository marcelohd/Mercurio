package com.mercurio.marceloh;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mercurio.marceloh.entity.Country;
import com.mercurio.marceloh.managedBean.CountryBean;

public class CountryParseXml {
	
	public static void main(String [] args){
		Country country = new Country();
		CountryBean bean = new CountryBean();
		
		Object[] botoes = { "Sim", "Nao" };  
		int resposta = JOptionPane.showOptionDialog(null,  
		        "Deseja inserir",  
		        "Alimentar Banco", // o  
		        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  
		        botoes, botoes[0]);  
		  
		if(resposta == 0){  
			try{
				File fileXML = new File("xml/pais.xml");
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
				Document document = builder.parse(fileXML);
				
				document.getDocumentElement().normalize();
				
				//System.out.println("Root element :" + document.getDocumentElement().getNodeName());
				NodeList nodeList = document.getElementsByTagName("PAIS");
				
				for(int temp = 0; temp < nodeList.getLength(); temp ++ ){
					Node node = nodeList.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;					
						
						country.setName(element.getAttribute("NOPAIS"));
						country.setCodei18n(element.getAttribute("CDPAIS"));
						country.setAcronym("");
						country.setTrash(false);		
						bean.onSave(country);										
					}	
				}			
			}catch(Exception e){
				e.printStackTrace();
			} 
		}else if(resposta == 1){  
		    JOptionPane.showMessageDialog(null, "Nao");  
		}
	}
}
