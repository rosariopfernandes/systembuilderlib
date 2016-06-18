package com.rpfsoftwares.systembuilderlib.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * Generates the system's main frame with a menu
 * created using {@link com.rpfsoftwares.systembuilderlib.window.JSysMenu}
 * and {@link com.rpfsoftwares.systembuilderlib.window.JSysMenuItem}
 * @author Rosário Pereira Fernandes
 *
 */
public class JSysMainFrame extends JFrame{
	JImagePanel panel;
	
	public JSysMainFrame(String title,ArrayList<JSysMenu> menus,String image)
	{
		addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent arg0) {
				if(arg0.getNewState()==0)
				{
					setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
			}
		});
		setResizable(false);
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0,0,1920,1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar= new JMenuBar();
		setJMenuBar(menuBar);
		
		for(int i=0;i<menus.size();i++)
			menuBar.add(menus.get(i));
		
		panel= new JImagePanel(image);
		panel.setBounds(0, 0, 1920, 1080);
		setContentPane(panel);
		panel.setLayout(null);
	}

	@Override
	public int getExtendedState() {
		return MAXIMIZED_BOTH;
	}

	@Override
	public boolean isResizable() {
		return false;
	}

	@Override
	public void setExtendedState(int arg0) {
		setBounds(0, 0, (int)getBounds().getWidth(),(int) getBounds().getHeight());
		super.setExtendedState(MAXIMIZED_BOTH);
	}

	@Override
	public void setResizable(boolean arg0) {
		super.setResizable(false);
	}
	
	

}
