package com.hollingsworth.arsnouveau.common.familiars;

import com.hollingsworth.arsnouveau.api.familiar.AbstractFamiliarHolder;
import com.hollingsworth.arsnouveau.api.familiar.IFamiliar;
import com.hollingsworth.arsnouveau.common.entity.ModEntities;
import com.hollingsworth.arsnouveau.common.entity.Starbuncle;
import com.hollingsworth.arsnouveau.common.entity.familiar.FamiliarCarbuncle;
import com.hollingsworth.arsnouveau.common.lib.LibEntityNames;
import net.minecraft.world.level.Level;

public class StarbuncleFamiliar extends AbstractFamiliarHolder {

    public StarbuncleFamiliar(){
        super(LibEntityNames.STARBUNCLE, (e) -> e instanceof Starbuncle);
    }

    @Override
    public IFamiliar getSummonEntity(Level world) {
        return new FamiliarCarbuncle(ModEntities.ENTITY_FAMILIAR_CARBUNCLE, world);
    }

    @Override
    public String getBookName() {
        return "Starbuncle";
    }

    @Override
    public String getBookDescription() {
        return "A Starbuncle familiar that will grant you Speed 2. Additionally, using a Golden Nugget on the starbuncle will consume it and grant the owner a short duration of Scrying for Gold Ore. Obtained by performing the Ritual of Binding near a Starbuncle.";
    }
}
