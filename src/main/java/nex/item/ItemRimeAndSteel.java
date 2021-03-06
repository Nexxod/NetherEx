/*
 * NetherEx
 * Copyright (c) 2016-2017 by LogicTechCorp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nex.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nex.init.NetherExBlocks;

public class ItemRimeAndSteel extends ItemNetherEx
{
    public ItemRimeAndSteel()
    {
        super("item_crystal_rime_steel");

        setMaxStackSize(1);
        setMaxDamage(64);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        pos = pos.offset(facing);
        ItemStack stack = player.getHeldItem(hand);

        if(!player.canPlayerEdit(pos, facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if(world.isAirBlock(pos))
            {
                world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                world.setBlockState(pos, NetherExBlocks.BLOCK_FIRE_BLUE.getDefaultState(), 11);
            }

            stack.damageItem(1, player);
            return EnumActionResult.SUCCESS;
        }
    }

}
