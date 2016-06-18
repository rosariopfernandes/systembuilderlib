package com.rpfsoftwares.systembuilderlib.window;

import javax.swing.ImageIcon;


/**
 * Helps you using the material icons provided in this library.
 * @author Rosário Pereira Fernandes
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class IconManager {
	
	public static final byte COLOR_BLACK=1;
	public static final byte COLOR_WHITE=0;
	
	private static String suffixByColor(byte color)
	{
		if(color==1)
			return "_black_18dp.png";
		else
			return "_white_18dp.png";
	}
	
	/**
	 * Get an Icon from the Action Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getActionIcon(Class classe,String icon, byte color)
	{
			return new ImageIcon(classe.getResource("/material_icons/action/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Alert Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getAlertIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/alert/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Av Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getAvIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/av/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Communication Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getCommunicationIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/communication/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Content Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getContentIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/content/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Device Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getDeviceIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/device/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Editor Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getEditorIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/editor/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the File Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getFileIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/file/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Hardware Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getHardwareIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/hardware/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Image Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getImageIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/image/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Maps Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getMapsIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/maps/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Navigation Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getNavigationIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/navigation/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Notification Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getNotificationIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/notification/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Social Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getSocialIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/social/ic_"+icon+suffixByColor(color)));
	}

	
	/**
	 * Get an Icon from the Toggle Pack
	 * @param classe the class where this method is being called from.
	 * @param icon name of the icon you want to set.
	 * @param color user 1 for black, or 0 for white.
	 * @return
	 */
	public static ImageIcon getToggleIcon(Class classe, String icon, byte color)
	{
		return new ImageIcon(classe.getResource("/material_icons/toggle/ic_"+icon+suffixByColor(color)));
	}

}
