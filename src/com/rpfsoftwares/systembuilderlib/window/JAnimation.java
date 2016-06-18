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

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
*
*Contains Animations for {@link javax.swing.JDialog} and
* {@link javax.swing.JFrame}
* @author Rosário Pereira Fernandes
* @version 1.0.0
* @since 1.0.0
*
**/
public class JAnimation {

	/**
	 * Fastest animation speed.
	 */
	public static int SPEED_FAST=1;
	
	/**
	 * Slowest animation speed.
	 */
	public static int SPEED_SLOW=5;

	/**
	 * Reveal Effect starting from the center.
	 * @param dialog the {@link javax.swing.JDialog} to reveal.
	 */
	public static void reveal(JDialog dialog)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int height=0;
			Rectangle bounds=dialog.getBounds();
			int targetHeight=bounds.height;
		    @Override
		    public void run() {
		    	if(height<targetHeight)
		    	{
		    		dialog.setBounds(bounds.x,bounds.y,bounds.width,height);
		    		height++;
		    	}
		    	else
		    	{
		    		dialog.setBounds(bounds.x,bounds.y,bounds.width,height);
		    		t.cancel();
		    	}
		    }
		}, 0, SPEED_FAST);
	}
	
	/**
	 * Reveal Effect starting from the center.
	 * @param frame the {@link javax.swing.JFrame} to reveal.
	 */
	public static void reveal(JFrame frame)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int height=0;
			Rectangle bounds=frame.getBounds();
			int targetHeight=bounds.height;
		    @Override
		    public void run() {
		    	if(height<targetHeight)
		    	{
		    		frame.setBounds(bounds.x,bounds.y,bounds.width,height);
		    		height++;
		    	}
		    	else
		    	{
		    		frame.setBounds(bounds.x,bounds.y,bounds.width,height);
		    		t.cancel();
		    	}
		    }
		}, 0, SPEED_FAST);
	}
	
	/**
	 * Reveal Effect starting from the center.
	 * @param dialog The {@link javax.swing.JDialog} to reveal.
	 * @param speed int to set custom animation speed.
	 */
	public static void reveal(JDialog dialog, int speed)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int height=0;
			Rectangle bounds=dialog.getBounds();
			int targetHeight=bounds.height;
		    @Override
		    public void run() {
		    	if(height<targetHeight)
		    	{
		    		dialog.setBounds(bounds.x,bounds.y,bounds.width,height);
		    		height++;
		    	}
		    	else
		    	{
		    		dialog.setBounds(bounds.x,bounds.y,bounds.width,height);
		    		t.cancel();
		    	}
		    }
		}, 0, speed);
	}

	/**
	 * Reveal Effect starting from the center.
	 * @param frame The {@link javax.swing.JFrame} to reveal.
	 * @param speed int to set custom animation speed. 
	 */
	public static void reveal(JFrame frame, int speed)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int height=0;
			Rectangle bounds=frame.getBounds();
			int targetHeight=bounds.height;
		    @Override
		    public void run() {
		    	if(height<targetHeight)
		    	{
		    		frame.setBounds(bounds.x,bounds.y,bounds.width,height);
		    		height++;
		    	}
		    	else
		    	{
		    		frame.setBounds(bounds.x,bounds.y,bounds.width,height);
		    		t.cancel();
		    	}
		    }
		}, 0, speed);
	}

	/**
	 * Fading Effect. The {@link javax.swing.JDialog} starts transparent 
	 * and gains opacity slowly. 
	 * @param dialog The {@link javax.swing.JDialog} to fade.
	 */
	public static void enterFading(JDialog dialog)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			float opacity=0.1f;
		    @Override
		    public void run() {
		    	if(opacity<1.0f)
		    	{
		    		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    		dialog.setOpacity(opacity);
		    		opacity=opacity+0.1f;
		    	}
		    	else
		    	{
		    		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		dialog.setOpacity(1.0f);
		    		t.cancel();
		    	}
		    }
		}, 0, 150);
	}

	/**
	 * Fading Effect. The {@link javax.swing.JFrame} starts transparent 
	 * and gains opacity slowly. 
	 * @param frame The {@link javax.swing.JFrame} to fade.
	 */
	public static void enterFading(JFrame frame)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			float opacity=0.1f;
		    @Override
		    public void run() {
		    	if(opacity<1.0f)
		    	{
		    		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    		frame.setOpacity(opacity);
		    		opacity=opacity+0.1f;
		    	}
		    	else
		    	{
		    		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		frame.setOpacity(1.0f);
		    		t.cancel();
		    	}
		    }
		}, 0, 150);
	}

	/**
	 * Fading Effect. The {@link javax.swing.JDialog} starts transparent 
	 * and gains opacity slowly. 
	 * @param dialog The {@link javax.swing.JDialog} to fade.
	 * @param speed {@link int} to set custom animation speed 
	 */
	public static void enterFading(JDialog dialog, int speed)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			float opacity=0.1f;
		    @Override
		    public void run() {
		    	if(opacity<1.0f)
		    	{
		    		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    		dialog.setOpacity(opacity);
		    		opacity=opacity+0.1f;
		    	}
		    	else
		    	{
		    		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		dialog.setOpacity(1.0f);
		    		t.cancel();
		    	}
		    }
		}, 0, speed);
	}

	/**
	 * Fading Effect. The {@link javax.swing.JFrame} starts transparent 
	 * and gains opacity slowly. 
	 * @param frame The {@link javax.swing.JFrame} to fade.
	 * @param speed {@link int} to set custom animation speed 
	 */
	public static void enterFading(JFrame frame, int speed)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			float opacity=0.1f;
		    @Override
		    public void run() {
		    	if(opacity<1.0f)
		    	{
		    		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    		frame.setOpacity(opacity);
		    		opacity=opacity+0.1f;
		    	}
		    	else
		    	{
		    		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		frame.setOpacity(1.0f);
		    		t.cancel();
		    	}
		    }
		}, 0, speed);
	}

	/**
	 * The {@link javax.swing.JDialog} enters from the Left side of the screen. 
	 * @param dialog The {@link javax.swing.JDialog} to enter 
	 */
	public static void enterFromLeft(JDialog dialog)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			Rectangle bounds=dialog.getBounds();
			int x=bounds.x;
			int y=bounds.y;
			int positionX=-bounds.width;
		    @Override
		    public void run() {
		    	if(positionX<x)
		    	{
		    		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    		dialog.setLocation(positionX, y);
		    		positionX++;
		    	}
		    	else
		    	{
		    		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		dialog.setLocation(x, y);
		    		t.cancel();
		    	}
		    }
		}, 0, SPEED_FAST);
	}

	/**
	 * The {@link javax.swing.JFrame} enters from the Left side of the screen. 
	 * @param frame The {@link javax.swing.JFrame} to enter. 
	 */
	public static void enterFromLeft(JFrame frame)
	{
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			Rectangle bounds=frame.getBounds();
			int x=bounds.x;
			int y=bounds.y;
			int positionX=-bounds.width;
		    @Override
		    public void run() {
		    	if(positionX<x)
		    	{
		    		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    		frame.setLocation(positionX, y);
		    		positionX++;
		    	}
		    	else
		    	{
		    		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		frame.setLocation(x, y);
		    		t.cancel();
		    	}
		    }
		}, 0, SPEED_FAST);
	}
	
	/**
	 * Centralizes the {@link javax.swing.JFrame}
	 * @param frame
	 */
	public static void center(JFrame frame)
	{
		Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(int)((dimension.getWidth()-frame.getWidth())/2);
		int y=(int)((dimension.getHeight()-frame.getHeight())/2);
		frame.setLocation(x, y);
	}

	/**
	 * Centralizes the {@link javax.swing.JDialog}
	 * @param dialog
	 */
	public static void center(JDialog dialog)
	{
		Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(int)((dimension.getWidth()-dialog.getWidth())/2);
		int y=(int)((dimension.getHeight()-dialog.getHeight())/2);
		dialog.setLocation(x, y);
	}
}
