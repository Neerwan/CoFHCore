package cofh.core.world.decoration;

import cofh.core.util.WeightedRandomBlock;
import cofh.core.world.IGeneratorParser;
import cofh.core.world.WorldGenMinableCluster;
import cofh.core.world.WorldGenSparseMinableCluster;
import com.typesafe.config.Config;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClusterParser implements IGeneratorParser {

	private final boolean sparse;

	public ClusterParser(boolean sparse) {

		this.sparse = sparse;
	}

	@Override
	public WorldGenerator parseGenerator(String name, Config genObject, Logger log, List<WeightedRandomBlock> resList, List<WeightedRandomBlock> matList) {

		int clusterSize = genObject.getInt("cluster-size");
		if (clusterSize <= 0) {
			log.warn("Invalid cluster size for generator '%s'", name);
			return null;
		}

		if (sparse) {
			return new WorldGenSparseMinableCluster(resList, clusterSize, matList);
		}
		return new WorldGenMinableCluster(resList, clusterSize, matList);
	}

}
