package raccoonman.reterraforged.common.worldgen.data;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import raccoonman.reterraforged.common.level.levelgen.noise.Noise;
import raccoonman.reterraforged.common.registries.RTFRegistries;
import raccoonman.reterraforged.common.worldgen.data.preset.Preset;
import raccoonman.reterraforged.common.worldgen.data.preset.WorldSettings;

public final class RTFNoiseGeneratorSettings {
	
	public static void bootstrap(BootstapContext<NoiseGeneratorSettings> ctx, Preset preset) {
		HolderGetter<DensityFunction> densityFunctions = ctx.lookup(Registries.DENSITY_FUNCTION);
		HolderGetter<NormalNoise.NoiseParameters> noiseParams = ctx.lookup(Registries.NOISE);
		HolderGetter<Noise> noise = ctx.lookup(RTFRegistries.NOISE);
		HolderGetter<Preset> presets = ctx.lookup(RTFRegistries.PRESET);
		ctx.register(NoiseGeneratorSettings.OVERWORLD, createOverworld(densityFunctions, noiseParams, noise, presets, preset));
    }

    private static NoiseGeneratorSettings createOverworld(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noiseParams, HolderGetter<Noise> noise, HolderGetter<Preset> presets, Preset preset) {
    	WorldSettings world = preset.world();
    	return new NoiseGeneratorSettings(
	  		new NoiseSettings(-world.properties.worldDepth, 320, 1, 2),
	  		Blocks.STONE.defaultBlockState(),
	  		Blocks.WATER.defaultBlockState(),
	  		RTFNoiseRouterData.overworld(densityFunctions, noiseParams, presets, preset),
	  		RTFSurfaceRuleData.overworld(densityFunctions, noise, preset),
	  		new OverworldBiomeBuilder().spawnTarget(),
	  		preset.world().properties.seaLevel,
	  		false, 
	  		true,
	  		true, 
	  		false
    	);
    }
}
