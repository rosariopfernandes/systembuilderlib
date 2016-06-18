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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.JPanel;

/**
 * An Image Component. Allows you to display images
 * @author Rosário Pereira Fernandes
 *
 */
public class JImagePanel extends JPanel{
    private BufferedImage image;
    private BufferedImage coloredImage;
    private BufferedImage grayScale;

    public JImagePanel(String file) 
    {
    	setClassPathImage(file);
    	addResizeListener();
    	initializeGrayScaleMode();
    }

    public JImagePanel(URL url)
    {
    	setImageFromURL(url);
    	addResizeListener();
    	initializeGrayScaleMode();
    }
    
    public JImagePanel(File file)
    {
    	setLocalImage(file);
    	addResizeListener();
    	initializeGrayScaleMode();
    }
    
    public JImagePanel()
    {
    	super();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    /**
     * Streches the image to fit ImagePanel
     * @param img
     * @return image resized
     */
    private BufferedImage resize(BufferedImage img, int imageType)
    {
    	Image tmp = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
    	BufferedImage image= new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g2d= image.createGraphics();
    	g2d.drawImage(tmp, 0, 0,null);
    	return image;
    }
    
    /**
     * loads the image from the specified {@link URL}
     * @param url
     */
    public void setImageFromURL(URL url)
    {
    	try
    	{
    		image= resize(ImageIO.read(url),BufferedImage.TYPE_INT_ARGB);
        	initializeGrayScaleMode();
    		repaint();
    	}
    	catch(MalformedURLException e)
    	{
    		e.printStackTrace();
    	} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    }
    
    /**
     * loads the image from the local classPath
     * @param classPath
     */
    public void setClassPathImage(String classPath)
    {

		try {
			image = ImageIO.read(this.getClass().getResource(classPath));
	    	initializeGrayScaleMode();
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * loads the image from the local machine
     * @param file
     */
    public void setLocalImage(File file)
    {
    	try {
			image = resize(ImageIO.read(file),BufferedImage.TYPE_INT_ARGB);
	    	initializeGrayScaleMode();
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void addResizeListener()
    {
    	this.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
			}
			@Override
			public void componentResized(ComponentEvent e) {
				image=resize(image,BufferedImage.TYPE_INT_ARGB);
		    	initializeGrayScaleMode();
				repaint();
			}
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
    }
    
    private void initializeGrayScaleMode()
    {
    	try
    	{
    	this.coloredImage = image;
    	ImageFilter filter = new GrayFilter(true, 35);  
    	ImageProducer producer = new FilteredImageSource(image.getSource(), filter);  
    	Image tmp = Toolkit.getDefaultToolkit().createImage(producer);
    	BufferedImage image= new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g2d= image.createGraphics();
    	g2d.drawImage(tmp, 0, 0,null);
    	this.grayScale=image;
    	}
    	catch (NullPointerException e)
    	{
    		
    	}
    }
    
    /**
     * Set the Image Type
     * This method is usually used to make your image greyscale
     * Simply pass BufferedImage.TYPE_BYTE_GRAY
     * @param type
     */
    public void setGrayScale(boolean isGrayScale)
    {
    	if(isGrayScale)
    		image = grayScale;
    	else
    		image = coloredImage;
    	repaint();
    }

}