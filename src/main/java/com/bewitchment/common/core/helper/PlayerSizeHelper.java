package com.bewitchment.common.core.helper;

import com.bewitchment.common.core.capability.resize.CapabilitySize;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class PlayerSizeHelper {
	
	private static void setPlayerSize(EntityPlayer player, float height, float width, float eyeHeight) {
		player.eyeHeight = eyeHeight;
		AxisAlignedBB bb = new AxisAlignedBB(-width/2, 0, -width/2, width/2, height, width/2).offset(player.getPosition());
		player.setEntityBoundingBox(bb);
		player.width = width;
		player.height = height;
	}
	
	public static void setPlayerScale(EntityPlayer player, float scale) {
		setPlayerSize(player, 1.8f*scale, 0.6f*scale, player.getDefaultEyeHeight()*scale);
		player.getCapability(CapabilitySize.CAPABILITY, null).setScale(scale);
	}
	
	public static void resetPlayerSize(EntityPlayer player) {
		setPlayerScale(player, 1);
	}
}
