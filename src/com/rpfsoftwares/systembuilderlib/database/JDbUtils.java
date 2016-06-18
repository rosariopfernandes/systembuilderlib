package com.rpfsoftwares.systembuilderlib.database;

/* 

$Id: LICENSE.txt,v 1.11 2004/02/06 09:32:57 jhunter Exp $

Copyright (C) 2000-2004 Jason Hunter & Brett McLaughlin.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
   notice, this list of conditions, and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions, and the disclaimer that follows 
   these conditions in the documentation and/or other materials 
   provided with the distribution.

3. The name "JDOM" must not be used to endorse or promote products
   derived from this software without prior written permission.  For
   written permission, please contact <request_AT_jdom_DOT_org>.

4. Products derived from this software may not be called "JDOM", nor
   may "JDOM" appear in their name, without prior written permission
   from the JDOM Project Management <request_AT_jdom_DOT_org>.

In addition, we request (but do not require) that you include in the 
end-user documentation provided with the redistribution and/or in the 
software itself an acknowledgement equivalent to the following:
    "This product includes software developed by the
     JDOM Project (http://www.jdom.org/)."
Alternatively, the acknowledgment may be graphical using the logos 
available at http://www.jdom.org/images/logos.

THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED.  IN NO EVENT SHALL THE JDOM AUTHORS OR THE PROJECT
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
SUCH DAMAGE.

This software consists of voluntary contributions made by many 
individuals on behalf of the JDOM Project and was originally 
created by Jason Hunter <jhunter_AT_jdom_DOT_org> and
Brett McLaughlin <brett_AT_jdom_DOT_org>.  For more information
on the JDOM Project, please see <http://www.jdom.org/>. 

*/

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Contains auxiliary methods to help you load data from database to a
 * {@link JTable}
 * @author Jason Hunter & Brett McLaughlin
 * Edited by Rosário Pereira Fernandes
 *
 */
public class JDbUtils {
	public static TableModel resultSetToTableModel(ResultSet paramResultSet)
	 {
	    try
	    {
	      ResultSetMetaData localResultSetMetaData = paramResultSet.getMetaData();
	      int i = localResultSetMetaData.getColumnCount();
	      Vector localVector1 = new Vector();
	      for (int j = 0; j < i; j++) {
	        localVector1.addElement(localResultSetMetaData.getColumnLabel(j + 1));
	      }
	      Vector localVector2 = new Vector();
	      while (paramResultSet.next())
	      {
	        Vector localVector3 = new Vector();
	        for (int k = 1; k <= i; k++) {
	          localVector3.addElement(paramResultSet.getObject(k));
	        }
	        localVector2.addElement(localVector3);
	      }
	      return new DefaultTableModel(localVector2, localVector1);
	    }
	    catch (Exception e)
	    {
			JOptionPane.showMessageDialog(null , e.getMessage(),"Table Error",
					JOptionPane.ERROR_MESSAGE);
	    }
	    return null;
	 }
	
	public static TableModel resultSetToNonEditTableModel(ResultSet paramResultSet)
	 {
	    try
	    {
	      ResultSetMetaData localResultSetMetaData = paramResultSet.getMetaData();
	      int i = localResultSetMetaData.getColumnCount();
	      Vector localVector1 = new Vector();
	      for (int j = 0; j < i; j++) {
	        localVector1.addElement(localResultSetMetaData.getColumnLabel(j + 1));
	      }
	      Vector localVector2 = new Vector();
	      while (paramResultSet.next())
	      {
	        Vector localVector3 = new Vector();
	        for (int k = 1; k <= i; k++) {
	          localVector3.addElement(paramResultSet.getObject(k));
	        }
	        localVector2.addElement(localVector3);
	      }
	      return new NonEditableModel(localVector2, localVector1);
	    }
	    catch (Exception e)
	    {
			JOptionPane.showMessageDialog(null , e.getMessage(),"Table Error",
					JOptionPane.ERROR_MESSAGE);
	    }
	    return null;
	 }
}
