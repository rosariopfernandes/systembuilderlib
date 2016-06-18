package com.rpfsoftwares.systembuilderlib.database;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * A TableModel that makes cells non editable 
 * and sorts the first row as Integer.
 * @author Rosário Pereira Fernandes
 *
 */
public class NonEditableModel extends DefaultTableModel {

    public NonEditableModel(Vector data, Vector columnNames) {
    	super(data, columnNames);
	}

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    @Override
    public Class getColumnClass(int column)
    {
        if (column == 0)
            return Integer.class;
        else
            return String.class;
    }
}
