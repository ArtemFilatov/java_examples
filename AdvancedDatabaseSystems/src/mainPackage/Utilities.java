package mainPackage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.mysql.jdbc.Blob;

/**
 * Paquete de funciones necesarias para realizar distintas accions dentro de la aplicacion.
 *
 */
public class Utilities {
	
	/**
	 * Devuelve un imageIcon a partir de un blob
	 * @param avatarBlob
	 * @return ImageIcon i
	 */
	public static ImageIcon blobToImageIcon(java.sql.Blob avatarBlob) {
        Image i = null;
        try {
			i= javax.imageio.ImageIO.read(avatarBlob.getBinaryStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ImageIcon(i);
		
	}
	
	/**
	 * Devuelve un stream de bytes a partir de un imageicon
	 * @param icon
	 * @return
	 */
	public static ByteArrayInputStream imageIconToByteArrayInputStream(ImageIcon icon) {
        Image image = icon.getImage();  
        BufferedImage bImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);  
        Graphics bg = bImage.getGraphics();  
        bg.drawImage(image,0,0,null);  
        bg.dispose();  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        try {
			ImageIO.write(bImage,"jpeg",out);
		} catch (IOException e) {
			e.printStackTrace();
		}  
        byte[] buf = out.toByteArray();  
        // setup stream for blob  
        ByteArrayInputStream inStream = new ByteArrayInputStream(buf);  
        return inStream;
	}
	
	/**
	 * Función para hashear strings en sha1
	 * @param input
	 * @return String - sha1
	 * @throws NoSuchAlgorithmException
	 */
    public static String stringToSha1(String input) {
        MessageDigest mDigest;
        StringBuffer sb = null;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
		
			byte[] result = mDigest.digest(input.getBytes());
        
			sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
        return sb.toString();
    }

}
