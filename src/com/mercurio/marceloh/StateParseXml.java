package com.mercurio.marceloh;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mercurio.marceloh.entity.State;
import com.mercurio.marceloh.managedBean.StateBean;

public class StateParseXml {
	public static void main(String [] args){
		State state = new State();
		StateBean bean = new StateBean();
		
		Object[] botoes = { "Sim", "NÃ£o" };  
		int resposta = JOptionPane.showOptionDialog(null,  
		        "Deseja inserir os Estados?",  
		        "Alimentar Banco", // o tï¿½tulo da janela  
		        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  
		        botoes, botoes[0]);  
		  
		if(resposta == 0){  
			try{
				File fileXML = new File("xml/sguf.xml");
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
				Document document = builder.parse(fileXML);
				
				document.getDocumentElement().normalize();
				
				//System.out.println("Root element :" + document.getDocumentElement().getNodeName());
				NodeList nodeList = document.getElementsByTagName("SGUF");
				
				for(int temp = 0; temp < nodeList.getLength(); temp ++ ){
					Node node = nodeList.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;					
						
						state.setName(element.getAttribute("NAME"));
						state.setCountry("046");
						state.setAcronym(element.getAttribute("UF"));
						state.setTrash(false);		
						bean.onSave(state);										
					}	
				}			
			}catch(Exception e){
				e.printStackTrace();
			} 
		}else if(resposta == 1){  
		    JOptionPane.showMessageDialog(null, "Não");  
		}
	}

}
