package com.mercurio.marceloh;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mercurio.marceloh.entity.Race;
import com.mercurio.marceloh.managedBean.RaceBean;

public class RaceParseXml {
	public static void main(String [] args){
		Race race = new  Race();
		RaceBean bean = new RaceBean();
		
		Object[] botoes = { "Sim", "Nao" };  
		int resposta = JOptionPane.showOptionDialog(null,  
		        "Deseja inserir",  
		        "Alimentar Banco", // o t�tulo da janela  
		        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  
		        botoes, botoes[0]);  
		  
		if(resposta == 0){  
			try{
				File fileXML = new File("xml/racaCor.xml");
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
				Document document = builder.parse(fileXML);
				
				document.getDocumentElement().normalize();
				
				//System.out.println("Root element :" + document.getDocumentElement().getNodeName());
				NodeList nodeList = document.getElementsByTagName("RACACOR");
				
				for(int temp = 0; temp < nodeList.getLength(); temp ++ ){
					Node node = nodeList.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;	
						
						race.setName(element.getAttribute("DSRACACOR"));
						race.setTrash(false);		
						bean.onSave(race);										
					}	
				}	
				
			}catch(Exception e){
				e.printStackTrace();
			} 
		}else if(resposta == 1){  
		    JOptionPane.showMessageDialog(null, "NÃO");  
		}
	}

}
