package loyer.gui;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class ImageSelection implements Transferable {

  private Image img;
  
  public ImageSelection(Image img) {
    this.img = img;
  }
  
  @Override
  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[] {DataFlavor.imageFlavor};
  }

  @Override
  public boolean isDataFlavorSupported(DataFlavor flavor) {
    return flavor.equals(DataFlavor.imageFlavor);
  }

  @Override
  public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
    if(flavor.equals(DataFlavor.imageFlavor)) {
      return img;
    }
    else {
      throw new UnsupportedFlavorException(flavor);
    }
  }

}
