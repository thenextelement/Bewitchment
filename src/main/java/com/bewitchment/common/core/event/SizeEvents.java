package com.bewitchment.common.core.event;

import com.bewitchment.common.core.capability.CapabilityUtils;
import com.bewitchment.common.core.capability.resize.CapabilitySize;
import com.bewitchment.common.core.net.NetworkHandler;
import com.bewitchment.common.core.net.messages.SizeChanged;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@Mod.EventBusSubscriber
public class SizeEvents {
	
	@SubscribeEvent
	public static void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			CapabilityUtils.copyDataOnPlayerRespawn(event, CapabilitySize.CAPABILITY);
		}
	}
	
	@SubscribeEvent
	public static void onStartWatching(PlayerEvent.StartTracking evt) {
		if (evt.getEntityPlayer() instanceof EntityPlayerMP && evt.getTarget() instanceof EntityPlayer) {
			float s = evt.getTarget().getCapability(CapabilitySize.CAPABILITY, null).getScale();
			NetworkHandler.HANDLER.sendTo(new SizeChanged((EntityPlayer) evt.getTarget(), s), (EntityPlayerMP) evt.getEntityPlayer());
		}
	}
	
	@SubscribeEvent
	public static void onTick(PlayerTickEvent evt) {
		if (evt.phase == Phase.END) {
			CapabilitySize size_cap = evt.player.getCapability(CapabilitySize.CAPABILITY, null);
			if (size_cap.isDirty()) {
				NetworkHandler.HANDLER.sendToAllTracking(new SizeChanged(evt.player, size_cap.getScale()), evt.player);
				size_cap.setClean();
			}
		}
	}
}
