package bbblast.view.singleplayer.assetsloader;

import javafx.scene.image.Image;
/**
 * Loads assets from files.
 */
public class StandardAssetsLoader implements AssetsLoader {

    @Override
    public Image load(final String assetName) {
        return new Image(assetName);
    }

}
