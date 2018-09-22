package com.bewitchment.client.core.event;

import com.bewitchment.common.core.capability.resize.CapabilitySize;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerSizeChanger {
	
	@SubscribeEvent
	public void onRenderPlayerPre(RenderPlayerEvent.Pre evt) {
		float scale = evt.getEntityPlayer().getCapability(CapabilitySize.CAPABILITY, null).getScale();
		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);
	}
	
	@SubscribeEvent
	public void onRenderPlayerPost(RenderPlayerEvent.Post evt) {
		float scale = 1f/evt.getEntityPlayer().getCapability(CapabilitySize.CAPABILITY, null).getScale();
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.popMatrix();
	}
}
