package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;

public class SwingImageDisplay extends JPanel implements ImageDisplay{

    private Image currentImage;
    private BufferedImage image;
    
    @Override
    public Image current() {
        return currentImage;
    }

    @Override
    public void show(Image im) {
        this.currentImage = im;
        image = imageOf(currentImage);
        this.repaint();
    }
    
    private BufferedImage imageOf(Image image) {
        try {
            return ImageIO.read(image.stream());
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void paint(Graphics g){
        if (image == null) return;
        super.paint(g);
        int width = Width();
        int height= Height();
        g.drawImage(image,getOX(width),getOY(height),width,height,null);   
    }

    private int getOX(int width) {
        return (this.getWidth() - width) /2;
    }
    
    private int getOY(int height) {
        return (this.getHeight() - height)/2;
    }
    
    private int Width() {
        if (image.getWidth() < this.getWidth() && image.getHeight() < this.getHeight()) 
            return image.getWidth();  
        else if (image.getHeight() < this.getHeight() || (double)image.getWidth()/image.getHeight() > (double) this.getWidth()/this.getHeight()) 
            return this.getWidth();
        return image.getWidth()*this.getHeight()/image.getHeight();
    }
    
    private int Height() {
        if (image.getHeight() < this.getHeight() && image.getWidth() < this.getWidth()) 
            return image.getHeight();
        else if (image.getWidth() < this.getWidth() || (double) image.getWidth()/image.getHeight() < (double) this.getWidth()/this.getHeight())
            return this.getHeight();
        return image.getHeight()*this.getWidth()/image.getWidth();
    }
    
}
