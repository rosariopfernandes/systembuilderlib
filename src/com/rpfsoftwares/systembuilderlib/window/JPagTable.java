package com.rpfsoftwares.systembuilderlib.window;

/*Copyright (c) 2016 Ros�rio Pereira Fernandes

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.rpfsoftwares.systembuilderlib.database.ForeignKey;
import com.rpfsoftwares.systembuilderlib.database.JContentValues;
import com.rpfsoftwares.systembuilderlib.database.JDbUtils;
import com.rpfsoftwares.systembuilderlib.database.JMySQLHelper;

/**
 * Generates a table to load data from database
 * @author Ros�rio Pereira Fernandes
 *
 */
public class JPagTable extends JDialog {
	private static final long serialVersionUID = -5196619900028022319L;
	private JLabel lblSearch;
	private JTextField txtProcura;
	private JButton btnSearch;
	private JComboBox<String> cboAtributo;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnExtraButton;
	private JButton btnExtraButton_1;
	private JMySQLHelper db;
	private int offset=0;
	private String tableName;
	private JContentValues pairs;
	private String where;
	private String order;
	static JEditForm edit;

	public JPagTable(JMySQLHelper db,String tableName, JContentValues pairs, String where,
			String order) {
		setTitle("systembuilderlib.wordpress.com");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 640, 410);
		JAnimation.center(this);
		this.db=db;
		this.tableName=tableName;
		this.pairs=pairs;
		this.where=where;
		this.order=order;
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("180dlu"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblSearch = new JLabel("Search:");
		lblSearch.setVisible(false);
		getContentPane().add(lblSearch, "2, 2, 3, 1, right, default");
		
		txtProcura = new JTextField();
		txtProcura.setVisible(false);
		txtProcura.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				txtProcura.selectAll();
			}
		});
		getContentPane().add(txtProcura, "6, 2, 5, 1, fill, default");
		
		cboAtributo = new JComboBox();
		cboAtributo.setVisible(false);
		getContentPane().add(cboAtributo, "12, 2, 3, 1, fill, default");
		
		btnSearch = new JButton("");
		btnSearch.setVisible(false);
		btnSearch.setToolTipText("Search");
		btnSearch.setIcon(new ImageIcon(JPagTable.class.getResource("/material_icons/action/ic_search_black_18dp.png")));
		getContentPane().add(btnSearch, "16, 2");
				
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setColumnSelectionAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.setFillsViewportHeight(true);
		//TODO Deal with the column resize
		for(int i=1; i<table.getColumnModel().getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setMinWidth(250);

		updateInterface(where);
		
		scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPane, "2, 4, 15, 1, fill, fill");
		scrollPane.setViewportView(table);
		
		btnPrevious = new JButton("");
		btnPrevious.setToolTipText("Previous Page");
		btnPrevious.setEnabled(false);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				offset-=17;
				updateButtons();
			}
		});
		btnPrevious.setIcon(new ImageIcon(JPagTable.class.getResource("/material_icons/navigation/ic_chevron_left_black_18dp.png")));
		getContentPane().add(btnPrevious, "2, 6");
		
		btnNext = new JButton("");
		if(table.getRowCount()<17)
			btnNext.setEnabled(false);
		btnNext.setToolTipText("Next Page");
		btnNext.setIcon(new ImageIcon(JPagTable.class.getResource("/material_icons/navigation/ic_chevron_right_black_18dp.png")));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				offset+=17;
				updateButtons();
			}
		});
		getContentPane().add(btnNext, "4, 6");
		
		btnExtraButton = new JButton("Extra Button 1");
		btnExtraButton.setVisible(false);
		getContentPane().add(btnExtraButton, "6, 6");
		
		btnExtraButton_1 = new JButton("Extra Button 2");
		btnExtraButton_1.setVisible(false);
		getContentPane().add(btnExtraButton_1, "8, 6");
		
		btnEdit = new JButton("");
		btnEdit.setToolTipText("Edit");
		btnEdit.setVisible(false);
		btnEdit.setIcon(new ImageIcon(JPagTable.class.getResource("/material_icons/editor/ic_mode_edit_black_18dp.png")));
		getContentPane().add(btnEdit, "14, 6");
		
		btnDelete = new JButton("");
		btnDelete.setToolTipText("Delete");
		btnDelete.setVisible(false);
		btnDelete.setIcon(new ImageIcon(JPagTable.class.getResource("/material_icons/action/ic_delete_black_18dp.png")));
		getContentPane().add(btnDelete, "16, 6");
		

	}
	
	
	private ResultSet select(String tableName,JContentValues pairs,String where)
	{
		String[]columns=pairs.getKeysArray();
		String[]aliases=pairs.getValuesArray();
		String query="SELECT ";
		if(columns==null ||columns.length<=0)
		{
			query+="*";
		}
		else
		{
			query=query+columns[0]+" AS '"+aliases[0]+"'";
			for(int i=1;i<columns.length;i++)
				query+=", "+columns[i]+" AS '"+aliases[i]+"'";
		}
		query+=" FROM "+tableName;
		if(where!=null || where!="" || !where.equalsIgnoreCase(""))
			query+=where;
		try
		{
			//System.out.println(query);
			PreparedStatement st= db.getConnection().prepareStatement(query);
			return st.executeQuery();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorCode(),
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	private ResultSet select(String tableName,JContentValues pairs,String where,
			String order)
	{
		return select(tableName,pairs,where+" ORDER BY "+order+" LIMIT "
				+ 17 + " OFFSET "+offset);
	}
	
	private void updateButtons()
	{
		if(offset==0)
			btnPrevious.setEnabled(false);
		else
			btnPrevious.setEnabled(true);
		updateInterface(where);
		if(table.getRowCount()<17)
			btnNext.setEnabled(false);
		else
			btnNext.setEnabled(true);
	}
	
	private void updateInterface(String where)
	{
		TableModel model= JDbUtils.resultSetToNonEditTableModel(select(tableName, pairs, where, order));
		table.setModel(model);
	}
	
	/**
	 * Loads current database data to the JTable
	 */
	public void refreshTable()
	{
		updateInterface(where);
	}
	
	/**
	 * Add the search bar
	 * @param atributes filters
	 */
	public void addSearchBar(JContentValues atributes)
	{
		String [] attributes= atributes.getKeysArray();
		String [] aliases= atributes.getValuesArray();
		lblSearch.setVisible(true);
		txtProcura.setVisible(true);
		btnSearch.setVisible(true);
		cboAtributo.setVisible(true);
		cboAtributo.setModel(new DefaultComboBoxModel<String>(aliases));
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String where;
				if(JPagTable.this.where!="" && !JPagTable.this.where.equalsIgnoreCase(""))
					if(!JPagTable.this.where.contains("INNER") && !JPagTable.this.where.contains("inner"))
						where=JPagTable.this.where+" AND "+attributes[cboAtributo.getSelectedIndex()]+" LIKE \'%"+txtProcura.getText()+"%\'";
					else
						where=JPagTable.this.where+" WHERE "+attributes[cboAtributo.getSelectedIndex()]+" LIKE \'%"+txtProcura.getText()+"%\'";
				else
					where=" WHERE "+attributes[cboAtributo.getSelectedIndex()]+" LIKE \'%"+txtProcura.getText()+"%\'";
				updateInterface(where);
			}
		});
		offset=0;
	}
	
	/**
	 * Add the search bar with custom Text
	 * @param label text to be displayed on the search bar
	 * @param atributes filters
	 */
	public void addSearchBar(String label, JContentValues atributes)
	{
		lblSearch.setText(label+":");
		btnSearch.setToolTipText(label);
		addSearchBar(atributes);
	}

	/**
	 * Add an extra Button
	 * @param text to be displayed on the Button
	 * @param onClick ActionListener to handle clicks 
	 */
	public void addExtraButton(String text, ActionListener onClick)
	{
		btnExtraButton.setText(text);
		btnExtraButton.addActionListener(onClick);
		btnExtraButton.setVisible(true);
	}
	
	/**
	 * Add an extra Button
	 * @param text to be displayed on the Button
	 * @param icon to be displayed on the Button
	 * @param onClick to handle clicks
	 */
	public void addExtraButton(String text,Icon icon, ActionListener onClick)
	{
		addExtraButton(text, onClick);
		btnExtraButton.setIcon(icon);
	}
	
	/**
	 * Add a secondary extra Button
	 * @param text to be displayed on the Button
	 * @param onClick to handle clicks
	 */
	public void addSecondaryExtraButton(String text, ActionListener onClick)
	{
		btnExtraButton_1.setText(text);
		btnExtraButton_1.addActionListener(onClick);
		btnExtraButton_1.setVisible(true);
	}
	
	/**
	 * Add a secondary extra Button
	 * @param text to be displayed on the Button
	 * @param icon to be displayed on the Button
	 * @param onClick to handle clicks
	 */
	public void addSecondaryExtraButton(String text, Icon icon, ActionListener onClick)
	{
		addSecondaryExtraButton(text, onClick);
		btnExtraButton_1.setIcon(icon);
	}
	
	/**
	 * Add an Edit Button
	 * @param object - your java class
	 * @param primaryKeyDB - the column Name of the Primary Key
	 * @param attribute - name of the attribute that represents the Primary Key
	 * @param noRowSelected - Message to display if no row was selected
	 * @param onUpdate - Message to display if update was successful
	 */
	public void addEditButton(Class object, ForeignKey [] keys, String primaryKeyDB, String attribute, String noRowSelected,
			String onUpdate)
	{
		btnEdit.setVisible(true);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit = new JEditForm(object,
						keys,
						getRow(noRowSelected, object.getDeclaredFields()) ,
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								boolean sucess=db.update(tableName, edit.getData(), 
										primaryKeyDB+" ='"+getRow().get(attribute, "")+"'");
								if(sucess)
									JOptionPane.showMessageDialog(null, onUpdate,
											getTitle(), JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, 
											"There was an error when updating row. Please check your"
											+ "console.",
											getTitle(), JOptionPane.ERROR_MESSAGE);
								edit.dispose();
								refreshTable();
							}
						});
				edit.setTitle("Editar Cliente");
				edit.setVisible(true);				
			}
		});
	}
	
	/**
	 * Add an Edit Button
	 * @param object - your java class
	 * @param primaryKeyDB - the column Name of the Primary Key (make sure this matches your class attribute)
	 * @param noRowSelected - Message to display if no row was selected
	 * @param onUpdate - Message to display if update was successful
	 */
	public void addEditButton(Class object, ForeignKey [] keys, String primaryKeyDB, String noRowSelected, String onUpdate)
	{
		addEditButton(object, keys, primaryKeyDB,primaryKeyDB, noRowSelected, onUpdate);
	}

	/**
	 * Add Delete Button
	 * @param primaryKeyDB - the column Name of the Primary Key
	 * @param atribute - name of the attribute that represents the primary Key
	 * @param promptMessage - message to display when asking for user confirmation
	 * @param onConfirm - message to display if deletion was successful
	 */
	public void addDeleteButton(String primaryKeyDB, String atribute, String promptMessage, String onDelete)
	{
		btnDelete.setVisible(true);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x= JOptionPane.showConfirmDialog(null, promptMessage, getTitle(), JOptionPane.YES_NO_OPTION);
				if(x==JOptionPane.YES_OPTION)
				{
					if(db.delete(tableName, primaryKeyDB+"='"+getRow().get(atribute,"")+"'"))
						JOptionPane.showMessageDialog(null, onDelete,
								getTitle(),JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "There was an error when deleting row. Please check your"
								+ " console.",
								getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				refreshTable();
			}
		});
	}
	
	/**
	 * Add Delete Button
	 * @param primaryKey - the column Name of the Primary Key (make sure this matches your class attribute)
	 * @param promptMessage - message to display when asking for user confirmation
	 * @param onConfirm - message to display if deletion was successful
	 */
	public void addDeleteButton(String primaryKey, String promptMessage, String onConfirm)
	{
		addDeleteButton(primaryKey,primaryKey,promptMessage,onConfirm);
	}

	/**
	 * Returns data from the selected row
	 * or displays a Message dialog if no row was selected
	 * returning null
	 * @param Message to be displayed
	 * @return row data
	 */
	public JContentValues getRow(String onErrorMessage)
	{
		int row=table.getSelectedRow();
		if(row<0)
		{
			JOptionPane.showMessageDialog(null, onErrorMessage, getTitle(), JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else
		{
			JContentValues data= new JContentValues();
			String[] atributes=pairs.getKeysArray();
			for(int i=0;i<atributes.length;i++)
				data.put(atributes[i], table.getModel().getValueAt(row,i)+"");
			return data;
		}
	}
	
	/**
	 * Returns data from the selected row, but only the class fields.
	 * @param onErrorMessage
	 * @param fields
	 * @return
	 */
	public JContentValues getRow(String onErrorMessage, Field[] fields)
	{
		int row=table.getSelectedRow();
		if(row<0)
		{
			JOptionPane.showMessageDialog(null, onErrorMessage, getTitle(), JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else
		{
			JContentValues data= new JContentValues();
			String[]atributes = pairs.getKeysArray();
			for(int i=0;i<atributes.length;i++)
				if(atributes[i].contains("."))
				{
					atributes[i]=afterDot(atributes[i]);
				}
			for(int i=0;i<fields.length;i++)
			{
				for(int j=0; j<atributes.length; j++)
				{
					if(fields[i].getName().equalsIgnoreCase(atributes[j]))//checks if this fieldName is an attribute
					{
						data.put(fields[i].getName(), table.getModel().getValueAt(row,j)+"");
					}
				}
			}
			return data;
		}
	}
	
	/**
	 * Does the same as the method split() from the String class.
	 * @param text - text to split
	 * @return the result of split(".")
	 */
	private String afterDot(String text)
	{
		String newText="";
		if(text.contains("."))
		{
			byte index=0;
			for(int i=0; i<text.length();i++)
				if(text.charAt(i)=='.')
				{
					index=(byte)i;
					break;
				}
			for(int j=index+1; j<text.length();j++)
				newText+=text.charAt(j);
			return newText;
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * Returns data from the selected row
	 * or displays a Message dialog if no row was selected
	 * returning null
	 * @return row data
	 */
	public JContentValues getRow()
	{
		int row=table.getSelectedRow();
		if(row<0)
		{
			JOptionPane.showMessageDialog(null, "No row was selected!", getTitle(), JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else
		{
			JContentValues data= new JContentValues();
			String[] atributes=pairs.getKeysArray();
			for(int i=0;i<atributes.length;i++)
				data.put(atributes[i], table.getModel().getValueAt(row,i)+"");
			return data;
		}
	}

}
