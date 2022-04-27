package bbblast.view.singleplayer.assetsloader;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Decorator for another assetsLoader that keep a cache (HashMap) of the already
 * loaded assets. Works only if assets don't change at runtime.
 *
 * @param <T> type of asset to work with
 */
public class CachingAssetsLoader<T> implements AssetsLoader<T> {

    private final AssetsLoader<T> loader;
    private final Map<String, T> cache;

    /**
     * 
     * @param loader the loader to wrap with this AssetsLoader
     */
    public CachingAssetsLoader(final AssetsLoader<T> loader) {
        this.loader = loader;
        this.cache = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T load(final String assetName) {
        if (!this.cache.containsKey(assetName)) {
            cache.put(assetName, loader.load(assetName));
        }
        return cache.get(assetName);
    }

}
