package fr.spaceiii.testmod.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class LegendaryArrowEntity extends ArrowEntity {
    private World world;

    public LegendaryArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
        this.world = world;
    }

    public LegendaryArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
        this.world = world;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        Entity hitEntity = entityHitResult.getEntity();
        Entity owner = this.getOwner();

        if (owner != null && world != null) {
            double distanceSq = owner.squaredDistanceTo(hitEntity);
            if (distanceSq >= 100) { // 📏 10 blocs = 10² = 100
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(this.world);
                if (lightning != null) {
                    lightning.refreshPositionAfterTeleport(hitEntity.getX(), hitEntity.getY(), hitEntity.getZ());
                    world.spawnEntity(lightning); // ⚡ Invoque l'éclair
                }
            }
        }
    }
}

