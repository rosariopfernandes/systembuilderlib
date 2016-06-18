package com.rpfsoftwares.systembuilderlib.window;

/*Copyright (c) 2016 Rosário Pereira Fernandes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Used to validate user's input data
 * @author Rosário Pereira Fernandes
 *
 */
public class JValidator {
	
	/**
	 * checks if a textField is empty
	 * @param textField
	 * @return true if the textField is empty or false if not
	 */
	public static boolean isEmpty(JTextField textField)
	{
		String text=textField.getText();
		return text.equalsIgnoreCase("") || text.equals(null);
	}

	/**
	 * checks if a JTextArea is empty
	 * @param textArea
	 * @return true if the textField is empty or false if not
	 */
	public static boolean isEmpty(com.rpfsoftwares.systembuilderlib.window.JTextArea textArea)
	{
		String text=textArea.getText();
		return text.equalsIgnoreCase("") || text.equals(null);
	}
	
	/**
	 * checks if a JTextArea is empty
	 * @param textArea
	 * @return true if the textField is empty or false if not
	 */
	public static boolean isEmpty(JTextArea textArea)
	{
		String text=textArea.getText();
		return text.equalsIgnoreCase("") || text.equals(null);
	}
	
	/**
	 * checks if an array of JTextField contains an empty JTextField
	 * @param textFieldArray
	 * @return true if one of the textFields is empty or false if none are
	 */
	public static boolean areEmpty(JTextField[]textFieldArray)
	{
		for(int i=0;i<textFieldArray.length;i++)
		{
			String texto=textFieldArray[i].getText();
			if(texto.equalsIgnoreCase("") || texto.equals(null))
				return true;
		}
		return false;
	}
	
	/**
	 * checks if an array of JTextArea contains an empty JTextField
	 * @param textAreaArray
	 * @return true if one of the textFields is empty or false if none are
	 */
	public static boolean areEmpty(JTextArea[]textAreaArray)
	{
		for(int i=0;i<textAreaArray.length;i++)
		{
			String texto=textAreaArray[i].getText();
			if(texto.equalsIgnoreCase("") || texto.equals(null))
				return true;
		}
		return false;
	}
	
	/**
	 * checks if an array of JTextArea contains an empty JTextField
	 * @param textAreaArray
	 * @return true if one of the textFields is empty or false if none are
	 */
	public static boolean areEmpty(com.rpfsoftwares.systembuilderlib.window.JTextArea[]textAreaArray)
	{
		for(int i=0;i<textAreaArray.length;i++)
		{
			String texto=textAreaArray[i].getText();
			if(texto.equalsIgnoreCase("") || texto.equals(null))
				return true;
		}
		return false;
	}

}
