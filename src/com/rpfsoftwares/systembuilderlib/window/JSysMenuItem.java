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

import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JMenuItem;

/**
 * Item to add to the {@link com.rpfsoftwares.systembuilderlib.window.JSysMenu}
 * @author user
 *
 */
public class JSysMenuItem extends JMenuItem{
	
	public JSysMenuItem(String label,ActionListener listener) {
		setText(label);
		addActionListener(listener);
	}
	
	public JSysMenuItem(Icon icon,String label,ActionListener listener) {
		setIcon(icon);
		setText(label);
		addActionListener(listener);
	}
}