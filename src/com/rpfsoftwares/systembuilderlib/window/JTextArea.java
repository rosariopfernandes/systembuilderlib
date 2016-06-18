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
import java.awt.ComponentOrientation;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.plaf.ScrollPaneUI;

/**
 * A pre-formatted JTextArea Swing Component.
 * This JTextArea creates scrollbars as needed and wraps lines.
 * @author Rosário Pereira Fernandes
 *
 */

public class JTextArea extends JScrollPane{
	private javax.swing.JTextArea txtArea;
	
	/**
	 * instantiate the JTextArea
	 */
	public JTextArea()
	{
		super();
		txtArea= new javax.swing.JTextArea();
		txtArea.setLineWrap(true);
		setViewportView(txtArea);
	}
	
	public String getText()
	{
		return txtArea.getText();
	}
	
	public void setText(String text)
	{
		txtArea.setText(text);
	}
	
	public void setLineWrap(boolean lineWrap)
	{
		txtArea.setLineWrap(lineWrap);
	}

	@Override
	public JViewport getViewport() {
		return null;
	}

	@Override
	public void setColumnHeader(JViewport arg0) {
		
	}

	@Override
	public void setColumnHeaderView(Component arg0) {
	}

	@Override
	public void setComponentOrientation(ComponentOrientation arg0) {
	}

	@Override
	public void setCorner(String arg0, Component arg1) {
	}

	@Override
	public void setHorizontalScrollBar(JScrollBar arg0) {
	}

	@Override
	public void setHorizontalScrollBarPolicy(int arg0) {
	}

	@Override
	public void setUI(ScrollPaneUI arg0) {
	}

	@Override
	public void setViewport(JViewport arg0) {
	}

	@Override
	public void setViewportView(Component arg0) {
	}
}
