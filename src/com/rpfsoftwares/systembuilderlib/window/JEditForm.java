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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.rpfsoftwares.systembuilderlib.annotation.Date;
import com.rpfsoftwares.systembuilderlib.annotation.Disabled;
import com.rpfsoftwares.systembuilderlib.annotation.Formatted;
import com.rpfsoftwares.systembuilderlib.annotation.Interval;
import com.rpfsoftwares.systembuilderlib.annotation.IntervalReal;
import com.rpfsoftwares.systembuilderlib.annotation.LongText;
import com.rpfsoftwares.systembuilderlib.annotation.MultiValues;
import com.rpfsoftwares.systembuilderlib.annotation.Secure;
import com.rpfsoftwares.systembuilderlib.annotation.Skip;
import com.rpfsoftwares.systembuilderlib.database.ForeignKey;
import com.rpfsoftwares.systembuilderlib.database.JContentValues;
import com.toedter.calendar.JDateChooser;

/**
 * Automatically generates a JDialog with the class fields to edit data
 * @author Rosário Pereira Fernandes
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class JEditForm extends JDialog{
	private ColumnSpec [] columns= new ColumnSpec[]
				{
					FormSpecs.RELATED_GAP_COLSPEC,
					FormSpecs.DEFAULT_COLSPEC,
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormSpecs.RELATED_GAP_COLSPEC
				};
	private RowSpec [] rows;
	private int currentRow=2;
	private Field[] fields;
	
	private ForeignKey [] keys;
	
	public JEditForm(Class object, ForeignKey [] keys, JContentValues values, ActionListener onConfirm)
	{
		fields = getFieldsNotSkipped(object.getDeclaredFields());
		
		this.keys=keys;
		
		String [] labels=new String[fields.length];
		for(int i=0;i<labels.length;i++)
			labels[i]=splitAndCapitalize(fields[i].getName())+":";

		rows= new RowSpec[fields.length*2+4];
		for(int i=0;i<rows.length;i++)
			if(i%2==0)
				rows[i]=FormSpecs.RELATED_GAP_ROWSPEC;
			else
				rows[i]=FormSpecs.DEFAULT_ROWSPEC;
		
		byte fieldNr=(byte)fields.length;
		int height=350;
		switch(fieldNr)
		{
			case 1: height=125; break;
			case 2: height=fieldNr*75;break;
			case 3: height=fieldNr*60;break;
			case 4: height=fieldNr*50;break;
			case 5: height=fieldNr*46;break;
			case 6: height=fieldNr*43;break;
			case 7: height=fieldNr*41;break;
			case 8: height=fieldNr*40;break;
			default: height=fieldNr*37; break;
		}
		
		setBounds(100, 100, 350, height); 
		
		JAnimation.center(this);
		
		getContentPane().setLayout(new FormLayout(columns, rows));
		
		initDialog();
		
		for(int i=0;i<fields.length;i++)
		{
			if(!fields[i].isAnnotationPresent(Skip.class))
			{
				String type=fields[i].getType().getSimpleName();
				String label = labels[i];
				boolean isEnabled=!fields[i].isAnnotationPresent(Disabled.class);
				//	Handling Strings
				if(type.equalsIgnoreCase("String"))
				{
					if(fields[i].isAnnotationPresent(MultiValues.class))
					{
						MultiValues annotation= fields[i].getAnnotation(MultiValues.class);
						addComboBox(label, values.get(fields[i].getName(), ""), annotation.options(), isEnabled);
					}
					else
						if(fields[i].isAnnotationPresent(Formatted.class))
						{
							Formatted annotation= fields[i].getAnnotation(Formatted.class);
							addFormattedTextField(label, values.get(fields[i].getName(), ""), annotation.format(),
									isEnabled);
						}
						else
							if(fields[i].isAnnotationPresent(Secure.class))
							{
								Secure annotation = fields[i].getAnnotation(Secure.class);
								addPasswordField(label, values.get(fields[i].getName(), ""), 
										annotation.echoChar(), isEnabled);
							}
							else
								if(fields[i].isAnnotationPresent(Date.class))
								{
									Date annotation = fields[i].getAnnotation(Date.class);
									addDateChooser(label, values.get(fields[i].getName(), ""),
											annotation.format(), isEnabled);
								}
								else
									if(fields[i].isAnnotationPresent(LongText.class))
										addTextArea(label, values.get(fields[i].getName(), ""), isEnabled);
									else
										addTextField(label, values.get(fields[i].getName(), ""), isEnabled);
				}
				else //Handling integers
					if(type.equalsIgnoreCase("int") || type.equalsIgnoreCase("byte") || type.equalsIgnoreCase("short")
							|| type.equalsIgnoreCase("long"))
					{
						if(fields[i].isAnnotationPresent(Interval.class))
						{
							Interval annotation= fields[i].getAnnotation(Interval.class);
							addSpinner(label,annotation.initialValue(),annotation.minValue(),annotation.maxValue()
									,annotation.stepSize(), isEnabled);
						}
						else
							if(fields[i].isAnnotationPresent(Formatted.class))
							{
								Formatted annotation= fields[i].getAnnotation(Formatted.class);
								addFormattedTextField(label, values.get(fields[i].getName(), ""), 
										annotation.format(), isEnabled);
							}
							else
								if(fields[i].isAnnotationPresent(MultiValues.class))
								{
									MultiValues annotation= fields[i].getAnnotation(MultiValues.class);
									addComboBox(label, values.get(fields[i].getName(), ""), 
											annotation.options(), isEnabled);
								}
								else
									if(fields[i].isAnnotationPresent(Secure.class))
									{
										Secure annotation = fields[i].getAnnotation(Secure.class);
										addPasswordField(label, values.get(fields[i].getName(), ""), 
												annotation.echoChar(), isEnabled);
									}
									else
										if(fields[i].isAnnotationPresent(Date.class))
										{
											Date annotation = fields[i].getAnnotation(Date.class);
											addDateChooser(label, values.get(fields[i].getName(), ""), annotation.format(), isEnabled);
										}
										else
											if(fields[i].isAnnotationPresent(com.rpfsoftwares.systembuilderlib.
													annotation.ForeignKey.class))
											{
												String [] options={};
												if(keys!=null)
													for(int k=0; k<keys.length; k++)
														if(fields[i].getName()
																.equalsIgnoreCase(keys[k].getForeignKey()))
															options=keys[k].getKeys();
												addComboBox(label,values.get(fields[i].getName(), ""), options, isEnabled);
											}
											else
												addSpinner(label, values.getAsInt(fields[i].getName(), 0), isEnabled);
					}
					else //Handling booleans
						if(type.equalsIgnoreCase("boolean"))
						{
							addCheckBox(label, values.getAsBoolean(fields[i].getName(), false), isEnabled);
						}
						else //Handling reals
							if(type.equalsIgnoreCase("double") || type.equalsIgnoreCase("float"))
							{
								if(fields[i].isAnnotationPresent(IntervalReal.class))
								{
									IntervalReal annotation= fields[i].getAnnotation(IntervalReal.class);
									addSpinner(label,annotation.initialValue(),annotation.minValue(),annotation.maxValue()
											,annotation.stepSize(), isEnabled);
								}
								else
									if(fields[i].isAnnotationPresent(MultiValues.class))
									{
										MultiValues annotation= fields[i].getAnnotation(MultiValues.class);
										addComboBox(label, values.get(fields[i].getName(), ""), annotation.options(), isEnabled);
									}
									else
										addSpinner(label, values.getAsInt(fields[i].getName(), 0), isEnabled);
							}
				}
		}
		
		
		//Add Confirmation Button
		addButton("Ok", onConfirm);
		
		//Add the cancel button
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		getContentPane().add(button,"6, "+(currentRow+2));
		
	}
	
	
	private void addTextField(String label, String value, boolean isEnabled)
	{
		JLabel jlabel= new JLabel(label);
		jlabel.setEnabled(isEnabled);
		JTextField textField= new JTextField();
		textField.setEnabled(isEnabled);
		textField.setText(value);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
		getContentPane().add(textField,"4, "+currentRow+", 3, 1, fill, default");
		currentRow+=2;
	}
	
	private void addButton(String label, ActionListener onClick)
	{
		JButton button = new JButton(label);
		button.addActionListener(onClick);
		getContentPane().add(button,"4, "+(currentRow+2));
	}
	
	private void addSpinner(String label, int value, boolean isEnabled)
	{
		JLabel jlabel = new JLabel(label);
		JSpinner spinner= new JSpinner();
		jlabel.setEnabled(isEnabled);
		spinner.setEnabled(isEnabled);
		spinner.setValue(value);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
		getContentPane().add(spinner,"4, "+currentRow+", 3, 1, fill, default");
		currentRow+=2;
	}
	
	private void addSpinner(String label, int initalVal, int minVal, int maxVal, int stepSize, boolean isEnabled)
	{
		JLabel jlabel= new JLabel(label);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
		JSpinner spinner= new JSpinner();
		spinner.setModel(new SpinnerNumberModel(initalVal,minVal,maxVal,stepSize));
		spinner.setEnabled(isEnabled);
		getContentPane().add(spinner,"4, "+currentRow+", 3, 1, fill, default");
		currentRow+=2;
	}
	
	private void addSpinner(String label, double initalVal, double minVal, double maxVal, double stepSize, boolean isEnabled)
	{
		JLabel jlabel= new JLabel(label);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
		JSpinner spinner= new JSpinner();
		spinner.setModel(new SpinnerNumberModel(initalVal,minVal,maxVal,stepSize));
		spinner.setEnabled(isEnabled);
		getContentPane().add(spinner,"4, "+currentRow+", 3, 1, fill, default");
		currentRow+=2;
	}
	
	private void addCheckBox(String label, boolean value, boolean isEnabled)
	{
		JLabel jlabel= new JLabel(label);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
		JCheckBox checkBox= new JCheckBox();
		checkBox.setEnabled(isEnabled);
		checkBox.setSelected(value);
		getContentPane().add(checkBox,"4, "+currentRow+", 3, 1, fill, default");
		currentRow+=2;
	}
	
	private void addComboBox(String label, Object value, String [] options, boolean isEnabled)
	{
		JLabel jlabel= new JLabel(label);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
		JComboBox comboBox= new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(options));
		comboBox.setEnabled(isEnabled);
		comboBox.setSelectedItem(value);
		getContentPane().add(comboBox,"4, "+currentRow+", 3, 1, fill, default");
		currentRow+=2;
	}
	
	private void addFormattedTextField(String label, String value, String format, boolean isEnabled)
	{
		JLabel jlabel= new JLabel(label);
		jlabel.setEnabled(isEnabled);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
		JFormattedTextField textField;
		try {
			textField = new JFormattedTextField(new MaskFormatter(format));
			textField.setEnabled(isEnabled);
			textField.setText(value);
			getContentPane().add(textField,"4, "+currentRow+", 3, 1, fill, default");
			currentRow+=2;
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorOffset(),
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void addPasswordField(String label, String value, char echoChar, boolean isEnabled)
	{
		JLabel jlabel= new JLabel (label);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
		JPasswordField passwordField= new JPasswordField();
		passwordField.setEchoChar(echoChar);
		passwordField.setEnabled(isEnabled);
		passwordField.setText(value);
		getContentPane().add(passwordField,"4, "+currentRow+", 3, 1, fill, default");
		currentRow+=2;
	}
	
	private void addDateChooser(String label, String value, String format, boolean isEnabled)
	{
		addLabel(label,isEnabled);
		JDateChooser dateChooser= new JDateChooser();
		dateChooser.setDateFormatString(format);
		dateChooser.setEnabled(isEnabled);
		DateFormat dt= new SimpleDateFormat(dateChooser.getDateFormatString());
		try {
			dateChooser.setDate(dt.parse(value));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorOffset(),
					JOptionPane.ERROR_MESSAGE);
		}
		getContentPane().add(dateChooser,"4, "+currentRow+", 3, 1, fill, default");
		currentRow+=2;
	}
	
	private void addTextArea(String label, String value, boolean isEnabled)
	{
		addLabel(label, isEnabled);
		JScrollPane scrollPane = new JScrollPane();
		JTextArea textArea= new JTextArea();
		textArea.setLineWrap(true);
		textArea.setText(value);
		scrollPane.setViewportView(textArea);
		getContentPane().add(scrollPane,"4, "+currentRow+", 3, 1, fill, fill");
		currentRow+=2;
	}
	
	private void addLabel(String label, boolean isEnabled)
	{
		JLabel jlabel= new JLabel (label);
		getContentPane().add(jlabel,"2, "+currentRow+", right, default");
	}
	
	private void initDialog()
	{
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
	}
	
	private String splitAndCapitalize(String fieldName)
	{
		String field="";
		char [] chars=fieldName.toCharArray();
		field+=Character.toUpperCase(chars[0]);
		for(int i=1;i<chars.length;i++)
		{
			if(Character.isUpperCase(chars[i]))
				field+=" ";
			field+=Character.toLowerCase(chars[i]);
		}
		return field;
	}
	
	private Field[] getFieldsNotSkipped(Field[] fields)
	{
		int newLength=0;
		for(int i=0;i<fields.length;i++)
		{
			if(!fields[i].isAnnotationPresent(Skip.class))
				newLength++;
		}
		Field[] newFields= new Field[newLength];
		int currentField=0;
		for(int i=0;i<fields.length;i++)
		{
			if(!fields[i].isAnnotationPresent(Skip.class))
			{
				newFields[currentField]=fields[i];
				currentField++;
			}
		}
		return newFields;
	}
	
	/**
	 * Returns data from the form's fields
	 * as an JContentValues object
	 * @return
	 */
	public JContentValues getData()
	{
		JContentValues data = new JContentValues();
		Component [] components=getContentPane().getComponents();
		int currentField=0;
		for(int i=0;i<components.length;i++)
		{
			String component=getContentPane().getComponent(i).getClass().getSimpleName();
			if(component.equalsIgnoreCase("JTextField") || component.equalsIgnoreCase("JFormattedTextField") ||
					component.equalsIgnoreCase("JPasswordField") || component.equalsIgnoreCase("JTextArea"))
			{
				JTextField textField = (JTextField) components[i];
				data.put(fields[currentField].getName(), textField.getText());
				currentField++;
			}
			else
				if(component.equalsIgnoreCase("JSpinner"))
				{
					JSpinner spinner= (JSpinner) components[i];
					try
					{
						data.put(fields[currentField].getName(), (int)spinner.getValue());
					}
					catch(ClassCastException e)
					{
						data.put(fields[currentField].getName(),(double)spinner.getValue());
					}
					currentField++;
				}
				else
					if(component.equalsIgnoreCase("JCheckBox"))
					{
						JCheckBox checkbox= (JCheckBox) components[i];
						data.put(fields[currentField].getName(), checkbox.isSelected());
						currentField++;
					}
					else
						if(component.equalsIgnoreCase("JComboBox"))
						{
							JComboBox<String> comboBox=(JComboBox<String>)components[i];
							boolean isForeignKey=false;
							if(keys!=null)
							{
								for(int k=0; k<keys.length; k++)
									if(fields[currentField].getName()
											.equalsIgnoreCase(keys[k].getForeignKey()))
									{
										isForeignKey=true;
										data.put(fields[currentField].getName(), keys[k].getIds()[comboBox.getSelectedIndex()]);
									}
							}
							else
								isForeignKey=false;
							if(!isForeignKey)
								data.put(fields[currentField].getName(), comboBox.getSelectedItem()+"");
							currentField++;
						}
						else
							if(component.equalsIgnoreCase("JDateChooser"))
							{
								JDateChooser dateChooser= (JDateChooser) components[i];
								data.put(fields[currentField].getName(),
										new SimpleDateFormat(dateChooser.getDateFormatString())
										.format(dateChooser.getDate()));
								currentField++;
							}
							else
								if(component.equalsIgnoreCase("JScrollPane"))
								{
									JScrollPane scrollPane=(JScrollPane)components[i];
									JTextArea textArea= (JTextArea) scrollPane.getViewport()
											.getComponent(0);
									data.put(fields[currentField].getName(), textArea.getText());
									currentField++;
								}
		}
		
		return data;
	}
}
