package bbblast.view.singleplayer.assetsloader;

/**
 * Interface that models an assets loader.
 */
public interface AssetsLoader<T> {
    /**
     * 
     * @param assetName name of the desired asset
     * @return the asset's image
     */
    T load(String assetName);

}