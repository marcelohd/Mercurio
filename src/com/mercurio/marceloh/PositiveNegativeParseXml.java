package com.mercurio.marceloh;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mercurio.marceloh.entity.PositiveNegative;
import com.mercurio.marceloh.managedBean.PositiveNegativeBean;
import com.mercurio.marceloh.managedBean.RaceBean;

public class PositiveNegativeParseXml {
	public static void main(String [] args){
		PositiveNegative positiveNegative = new  PositiveNegative();
		PositiveNegativeBean bean = new PositiveNegativeBean();
		
		Object[] botoes = { "Sim", "Nao" };  
		int resposta = JOptionPane.showOptionDialog(null,  
		        "Deseja inserir",  
		        "Alimentar Banco", // o t�tulo da janela  
		        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  
		        botoes, botoes[0]);  
		  
		if(resposta == 0){  
			try{
				File fileXML = new File("xml/positivoNegativo.xml");
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
				Document document = builder.parse(fileXML);
				
				document.getDocumentElement().normalize();
				
				//System.out.println("Root element :" + document.getDocumentElement().getNodeName());
				NodeList nodeList = document.getElementsByTagName("POSITIVONEGATIVO");
				
				for(int temp = 0; temp < nodeList.getLength(); temp ++ ){
					Node node = nodeList.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;	
						
						positiveNegative.setName(element.getAttribute("DSPOSITIVONEGATIVO"));
						positiveNegative.setTrash(false);		
						bean.onSave(positiveNegative);										
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
