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

package nex.init;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nex.client.particle.ParticleSporeExplosionHuge;
import nex.client.particle.ParticleSporeExplosionLarge;

public enum NetherExParticleTypes
{
    SPORE_EXPLOSION_LARGE,
    SPORE_EXPLOSION_HUGE;

    @SideOnly(Side.CLIENT)
    public static IParticleFactory get(NetherExParticleTypes type)
    {
        switch(type.ordinal())
        {
            default:
            case 0:
                return new ParticleSporeExplosionLarge.Factory();
            case 1:
                return new ParticleSporeExplosionHuge.Factory();
        }
    }
}
