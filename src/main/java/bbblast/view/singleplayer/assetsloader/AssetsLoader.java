package bbblast.view.singleplayer.assetsloader;

import javafx.scene.image.Image;

/**
 * Interface that models an assets loader.
 */
public interface AssetsLoader {
    /**
     * 
     * @param assetName name of the desired asset
     * @return the asset's image
     */
    Image load(String assetName);

}