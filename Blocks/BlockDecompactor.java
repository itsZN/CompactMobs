package compactMobs.Blocks;

import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.core.Position;

import compactMobs.CompactMobsCore;
import compactMobs.DefaultProps;
import compactMobs.Utils;
import compactMobs.TileEntity.TileEntityDecompactor;

public class BlockDecompactor extends BlockContainer {

    Icon textureFront, textureSides, textureBack, textureTop;

    public BlockDecompactor(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setLightOpacity(10);
        this.setCreativeTab(CreativeTabs.tabRedstone);
//        textureFront = 3; //3
//        textureSides = 5; //5
//        textureBack = 6; //6
//        textureTop = 4; //4
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        Utils.preDestroyBlock(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public void registerIcons(IconRegister register) {
    	textureFront = register.registerIcon("compactMobs:DecompactorFront");
    	textureSides = register.registerIcon("compactMobs:DecompactorSides");
    	textureBack = register.registerIcon("compactMobs:DecompactorBack");
    	textureTop = register.registerIcon("compactMobs:DecompactorTop");
    }

    @Override
    public Icon getIcon(int i, int j) {
        if (j == 0 && i == 3) {
            return textureFront;
        }

        if (i == 1) {
            return textureTop;
        } else if (i == 0) {
            return textureBack;
        } else if (i == j) {
            return textureFront;
        } else if (j >= 0 && j < 6 && ForgeDirection.values()[j].getOpposite().ordinal() == i) {
            return textureBack;
        } else {
            return textureSides;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack stack) {
        ForgeDirection orientation = Utils.get2dOrientation(new Position(entityliving.posX, entityliving.posY, entityliving.posZ),
                new Position(i, j, k));

        world.setBlockMetadataWithNotify(i, j, k, orientation.getOpposite().ordinal(),2);
        //CompactMobsCore.instance.cmLog.info(String.valueOf(world.getBlockMetadata(i,j,k)));
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityDecompactor();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

        if (world.isRemote) {
            return true;
        } else if (par5EntityPlayer.isSneaking()) {
            return false;
        } else {
            par5EntityPlayer.openGui(CompactMobsCore.instance, 1, world, par2, par3, par4);
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
    }
}
