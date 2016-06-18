package com.rpfsoftwares.systembuilderlib.database;

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

import java.util.ArrayList;

/**
 * A different implementation of the {@link java.util.HashMap} class;
 * Based on Android's {@link com.rpfsoftwares.systembuilderlib.database.ContentValues} class;
 * Used to bind values to corresponding keys.
 * @author Ros�rio Pereira Fernandes
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class JContentValues {
	private ArrayList<String> keys;
	private ArrayList<String> values;
	
	/**
	 * Instantiates a JContentValues with the default size
	 */
	public JContentValues()
	{
		keys= new ArrayList<String>();
		values= new ArrayList<String>();
	}
	
	/**
	 * Instantiates a JContentValues with the default size
	 * @param isDebuggable
	 */
	public JContentValues(boolean isDebuggable)
	{
		keys= new ArrayList<String>();
		values= new ArrayList<String>();
	}
	
	/**
	 * Instantiates a JContentValues with a selected size
	 * @param size
	 */
	public JContentValues(int size)
	{
		keys= new ArrayList<String>(size);
		values= new ArrayList<String>(size);
	}
	
	/**
	 * Put a {@link String} on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key, String value)
	{
		keys.add(key);
		values.add(value);
	}
	
	/**
	 * Gets a value
	 * @param key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public String get(String key,String defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return values.get(i);
		return defaultValue;
	}
	
	/**
	 * Put a boolean on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key, boolean value)
	{
		keys.add(key);
		values.add(value+"");
	}
	
	/**
	 * Gets a value as a boolean
	 * @param key - key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public boolean getAsBoolean(String key, boolean defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				if(values.get(i).equalsIgnoreCase("true"))
					return true;
				else
					return false;
		return defaultValue;
	}
	
	/**
	 * Put an int on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key,int value)
	{
		keys.add(key);
		values.add(value+"");
	}

	/**
	 * Gets a value as an int
	 * @param key - key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public int getAsInt(String key, int defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return Integer.parseInt(values.get(i));
		return defaultValue;
	}

	/**
	 * Put a byte on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key,byte value)
	{
		keys.add(key);
		values.add(value+"");
	}

	/**
	 * Gets a value as a byte
	 * @param key - key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public byte getAsByte(String key,byte defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return Byte.parseByte(values.get(i));
		return defaultValue;
	}

	/**
	 * Put a char on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key,char value)
	{
		keys.add(key);
		values.add(value+"");
	}

	/**
	 * Gets a value as a char
	 * @param key - key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public char getAsChar(String key, char defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return values.get(i).charAt(0);
		return defaultValue;
	}

	/**
	 * Put a long on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key,long value)
	{
		keys.add(key);
		values.add(value+"");
	}

	/**
	 * Gets a value as a long
	 * @param key - key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public long getAsLong(String key, long defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return Long.parseLong(values.get(i));
		return defaultValue;
	}

	/**
	 * Put a short on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key,short value)
	{
		keys.add(key);
		values.add(value+"");
	}

	/**
	 * Gets a value as a short
	 * @param key - key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public short getAsShort(String key, short defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return Short.parseShort(values.get(i));
		return defaultValue;
	}

	/**
	 * Put a float on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key,float value)
	{
		keys.add(key);
		values.add(value+"");
	}

	/**
	 * Gets a value as a float
	 * @param key - key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public float getAsFloat(String key, float defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return Float.parseFloat(values.get(i));
		return defaultValue;
	}

	/**
	 * Put a double on the current JContentValues
	 * @param key
	 * @param value
	 */
	public void put(String key,double value)
	{
		keys.add(key);
		values.add(value+"");
	}

	/**
	 * Gets a value as a double
	 * @param key - key the value to get
	 * @param defaultValue - what to get if the key isn't found
	 * @return the value, or defaultValue if the key isn't found
	 */
	public double getAsDouble(String key, double defaultValue)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return Double.parseDouble(values.get(i));
		return defaultValue;
	}
	
	/**
	 * removes a line at the given index
	 * @param index
	 */
	public void remove(int index)
	{
		keys.remove(index);
		values.remove(index);
	}
	
	/* Removed due to mal-functioning
	public void remove(Object arg0)
	{
		keys.remove(arg0);
		values.remove(arg0);
	}
	*/
	
	/**
	 * gets the number of the values
	 * @return size
	 */
	public int size()
	{
		if(keys.size()==values.size())
			return keys.size();
		else
			return -1;
	}
	
	/**
	 * gets an array containing all of the keys
	 * @return keys array
	 */
	public String[] getKeysArray()
	{
		String[] keys= new String[this.keys.size()];
		for(int i=0;i<keys.length;i++)
			keys[i]=this.keys.get(i);
		return keys;
	}
	
	/**
	 * gets an array containing all of the values
	 * @return values array
	 */
	public String[] getValuesArray()
	{
		String[] values= new String[this.values.size()];
		for(int i=0;i<values.length;i++)
			values[i]=this.values.get(i);
		return values;
	}
	
	/**
	 * deletes everything from the set
	 */
	public void clear()
	{
		this.keys.clear();
		this.values.clear();
	}
	
	/**
	 * check if set is empty
	 * @return false if set is filled, or true if it isn't
	 */
	public boolean isEmpty()
	{
		return keys.isEmpty() && values.isEmpty();
	}

	/**
	 * gets the keys as an {@link ArrayList}
	 * @return ArrayList containing the keys
	 */
	public ArrayList<String> getKeysList() {
		return keys;
	}

	/**
	 * gets the values as an {@link ArrayList}
	 * @return ArrayList containing the keys
	 */
	public ArrayList<String> getValuesList() {
		return values;
	}
	
	/**
	 * check if it contains a specific key
	 * @param key
	 * @return true if it contains this key, or false if it doesn't
	 */
	public boolean containsKey(String key)
	{
		String[]keys=getKeysArray();
		for(int i=0;i<keys.length;i++)
			if(keys[i].equalsIgnoreCase(key))
				return true;
		return false;
	}
	
	/**
	 * Puts keys and values from another {@link JContentValues}
	 * @param other
	 */
	public void putAll(JContentValues other)
	{
		for(int i=0;i<other.size();i++)
			put(other.getKeysArray()[i], other.getValuesArray()[i]);
	}
	
	/**
	 * Puts null
	 * @param key
	 */
	public void putNull(String key)
	{
		keys.add(key);
		values.add(null);
	}

	/**
	 * returns a {@link String} containing a human-readable description of this object
	 */
	@Override
	public String toString()
	{
		String retorno="";
		for(int i=0;i<keys.size();i++)
			retorno+=keys.get(i)+"="+values.get(i)+" ";
		return retorno;
	}

}
