package bbblast.view.singleplayer.assetsloader;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * Decorator for another assetsLoader that keep a cache (HashMap) of the already loaded
 * assets. Works only if assets don't change at runtime.
 *
 */
public class CachingAssetsLoader implements AssetsLoader {

    private final AssetsLoader loader;
    private final Map<String, Image> cache;

    public CachingAssetsLoader(final AssetsLoader loader) {
        this.loader = loader;
        this.cache = new HashMap<>();
    }

    @Override
    public Image load(final String assetName) {
        if (!this.cache.containsKey(assetName)) {
            cache.put(assetName, loader.load(assetName));
        }
        return cache.get(assetName);
    }

}
