package com.exformatgames.defender;

public final class Constants
{
	public static final float DIVIDER = 100;
	public static final float SCL = 0.03f;

	public static final String prefsName = "skedush_prefs";
	public static final String prefsMaxScoreKey = "maximum score";

	public static final float WORLD_WIDTH = 10.8f;
	public static final float WORLD_HEIGHT = 19.2f;

	public static final float UI_WIDTH = 1080;
	public static final float UI_HEIGHT = 1920;
	
	public static final short CATEGORY_PLAYER = 0x0001; 
	public static final short CATEGORY_BONUS = 0x0002; 
	public static final short CATEGORY_TRASH = 0x0004; 
	public static final short CATEGORY_PLATFORM = 0x0008; 
	public static final short CATEGORY_SENSOR = 0x0016;
	public static final short CATEGORY_WIND = 0x0032;
	
	
	public static final short MASK_PLAYER = CATEGORY_TRASH | CATEGORY_PLATFORM | CATEGORY_BONUS; 
	public static final short MASK_BONUS = CATEGORY_SENSOR; 
	public static final short MASK_TRASH = CATEGORY_PLAYER | CATEGORY_PLATFORM | CATEGORY_TRASH | CATEGORY_WIND; 
	public static final short MASK_PLATFORM = CATEGORY_TRASH | CATEGORY_PLAYER;
	public static final short MASK_SENSOR = CATEGORY_BONUS;
	public static final short MASK_WIND = CATEGORY_TRASH | CATEGORY_PLAYER;
	
}
