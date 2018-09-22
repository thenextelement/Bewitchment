package com.bewitchment.common.core.net.messages;

import java.util.UUID;

import com.bewitchment.common.core.capability.resize.CapabilitySize;
import com.bewitchment.common.core.net.SimpleMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SizeChanged extends SimpleMessage<SizeChanged> {
	
	public float size;
	public UUID player;
	
	public SizeChanged() {
		//NO_OP
	}
	
	public SizeChanged(EntityPlayer p, float scale) {
		player = p.getUniqueID();
		size = scale;
	}
	
	@Override
	public IMessage handleMessage(MessageContext context) {
		EntityPlayer p = Minecraft.getMinecraft().world.getPlayerEntityByUUID(player);
		if (p!=null) {
			p.getCapability(CapabilitySize.CAPABILITY, null).setScale(size);
		}
		
		return null;
	}
}
