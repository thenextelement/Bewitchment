package com.bewitchment.common.potion;

import com.bewitchment.api.capability.IBloodReserve;
import com.bewitchment.common.core.capability.transformation.blood.CapabilityBloodReserve;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.ArrayList;
import java.util.List;

public class PotionBloodDrained extends PotionMod {

	public static final DamageSource DRAIN_DAMAGE = new DamageSourceDrain();
	public static final float TRESHOLD = 0.2f;
	private static final List<ItemStack> cure = new ArrayList<ItemStack>(0);

	public PotionBloodDrained() {
		super("blood_drain", true, 0x820000);
		this.setIconIndex(1, 0);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		return cure;// No healing
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return duration % 80 == 0;
	}

	@Override
	public void performEffect(EntityLivingBase entity, int amplifier) {

		IBloodReserve br = entity.getCapability(CapabilityBloodReserve.CAPABILITY, null);
		float amount = br.getPercentFilled();

		if (amount > 0 && amount < TRESHOLD) {
			entity.attackEntityFrom(DRAIN_DAMAGE, 0.5f);
			entity.addPotionEffect(new PotionEffect(this, 200, amplifier));
		} else {
			entity.removePotionEffect(this);
			br.setDrinker(null);
		}

	}

	public static class DamageSourceDrain extends DamageSource {

		public DamageSourceDrain() {
			super("drain_damage");
		}

		@Override
		public ITextComponent getDeathMessage(EntityLivingBase entity) {
			String name = entity.getCapability(CapabilityBloodReserve.CAPABILITY, null).getLastDrinker(entity.world);
			String s = "death.attack.drain_damage";
			if (name != null) {
				return new TextComponentTranslation(s + ".player", name);
			}
			return new TextComponentTranslation(s);

		}

	}

}
