package compactMobs.Containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import compactMobs.Items.CompactMobsItems;
import compactMobs.TileEntity.TileEntityCatalyst;

public class ContainerCatalyst extends Container {

    int inventorySize;

    public ContainerCatalyst(InventoryPlayer par1InventoryPlayer, TileEntityCatalyst tileEntity) {
        inventorySize = tileEntity.getSizeInventory();


        for (int var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new FullMobHolderSlot(tileEntity, var4 + var3 * 9, 8 + var4 * 18, 23 + var3 * 18));
            }
        }
        //62, 105
        this.addSlotToContainer(new EmptyMobHolderSlot(tileEntity, 27, 62, 105));
        this.addSlotToContainer(new EmptyMobHolderSlot(tileEntity, 28, 80, 105));
        this.addSlotToContainer(new EmptyMobHolderSlot(tileEntity, 29, 98, 105));
        this.addSlotToContainer(new CatalystCoreSlot(tileEntity, 30, 23, 91));
        this.addSlotToContainer(new UpgradeSlot(tileEntity, 31, 137, 91,new int[]{0,1}));

        for (int var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 153 + var3 * 18));
            }
        }

        for (int var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 211));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        /*
         * ItemStack itemstack = null; int inventorySize1 = 27; Slot slot =
         * (Slot) inventorySlots.get(i); if (slot != null && slot.getHasStack())
         * { ItemStack itemstack1 = slot.getStack(); itemstack =
         * itemstack1.copy(); if (i < inventorySize) { if
         * (!mergeItemStack(itemstack1, inventorySize1, inventorySlots.size(),
         * true)) return null; } else if (!mergeItemStack(itemstack1, 0,
         * inventorySize1, false)) return null; if (itemstack1.stackSize == 0)
         * slot.putStack(null); else slot.onSlotChanged(); } return itemstack;
         */
        ItemStack var2 = null;
        Slot var3 = (Slot) this.inventorySlots.get(par2);

        if (var3 != null && var3.getHasStack()) {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par2 >= 0 && par2 < 31) {
                if (!this.mergeItemStack(var4, 31, 67, true)) {
                    return null;
                }

                var3.onSlotChange(var4, var2);
            } else if (par2 >= 31) {
                if (var4.getItem() == CompactMobsItems.fullMobHolder) {
                    if (!this.mergeItemStack(var4, 0, 27, false)) {
                        return null;
                    }
                }
                else if (var4.getItem() == CompactMobsItems.fullMobHolder) 
                {
                	if (!this.mergeItemStack(var4, 30, 31, false)) {
                        return null;
                    }
                }
                else if (par2 >= 31 && par2 < 58) {
                    if (!this.mergeItemStack(var4, 58, 67, false)) {
                        return null;
                    }
                } else if (par2 >= 58 && par2 < 67 && !this.mergeItemStack(var4, 31, 57, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(var4, 31, 67, false)) {
                return null;
            }

            if (var4.stackSize == 0) {
                var3.putStack((ItemStack) null);
            } else {
                var3.onSlotChanged();
            }

            if (var4.stackSize == var2.stackSize) {
                return null;
            }

            var3.onPickupFromSlot(par1EntityPlayer, var4);
        }

        return var2;
    }

    @Override
    public void putStackInSlot(int slot, ItemStack stack) {
        if (getSlot(slot).isItemValid(stack)) {
            this.getSlot(slot).putStack(stack);
        }
    }
}
