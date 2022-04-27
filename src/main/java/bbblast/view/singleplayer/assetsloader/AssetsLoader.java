package bbblast.view.singleplayer.assetsloader;

/**
 * 
 * Represent a class that loads assets.
 *
 * @param <T> the type of assets to load
 */
public interface AssetsLoader<T> {
    /**
     * 
     * @param assetName name of the desired asset
     * @return the asset
     */
    T load(String assetName);

}
