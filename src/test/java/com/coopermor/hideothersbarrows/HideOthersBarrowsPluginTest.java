package com.coopermor.hideothersbarrows;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HideOthersBarrowsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HideOthersBarrowsPlugin.class);
		RuneLite.main(args);
	}
}