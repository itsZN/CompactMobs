package compactMobs.Containers;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class ClosedSlot extends Slot{

	public ClosedSlot(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
    {
		if (par1ItemStack==null)
				return true;
        return false;
    }
}
