package compactMobs.Containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import compactMobs.Items.CompactMobsItems;

public class CatalystCoreSlot extends Slot{
	
	
	public CatalystCoreSlot(IInventory par1iInventory, int par2, int par3,
			int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
    {
		if (itemStack != null)
			if (itemStack.getItem() == CompactMobsItems.catalystCore)
				return true;
		if (itemStack == null)
			return true;
		return false;
		
    }
	
	


}
