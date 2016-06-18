package com.rpfsoftwares.systembuilderlib.database;

import java.util.Arrays;

/**
 * Helps setting foreign keys on {@link com.rpfsoftwares.systembuilderlib.window.JForm}.
 * @author Rosário Pereira Fernandes
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class ForeignKey {
	
	private String foreignKey;
	private int [] ids;
	private String [] keys;
	
	public ForeignKey(String foreignKey, int[] ids, String[] keys) {
		this.foreignKey=foreignKey;
		this.ids = ids;
		this.keys = keys;
	}
	
	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	@Override
	public String toString() {
		return "ForeignKey [foreignKey=" + foreignKey + ", ids=" + Arrays.toString(ids) + ", keys="
				+ Arrays.toString(keys) + "]";
	}

}
