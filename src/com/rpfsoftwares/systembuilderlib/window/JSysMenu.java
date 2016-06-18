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

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JMenu;
/**
 * Generates a Menu using {@link com.rpfsoftwares.systembuilderlib.window.JSysMenuItem}
 * for the {@link com.rpfsoftwares.systembuilderlib.window.JSysMainFrame}
 * @author Rosário Pereira Fernandes
 *
 */
public class JSysMenu extends JMenu{
	
	public JSysMenu(Icon icon, String label,ArrayList<JSysMenuItem> items)
	{
		setIcon(icon);
		setText(label);
		for(int i=0;i<items.size();i++)
			add(items.get(i));
	}
	
	public JSysMenu(String label,ArrayList<JSysMenuItem> items)
	{
		setText(label);
		for(int i=0;i<items.size();i++)
			add(items.get(i));
	}
}
