package com.tools.common;

/**
 * ��ȡ������е�����
 * @author Administrator
 *
 */
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ClipboardContent {
	public static void main(String[] args) {
		
		try {
			//��ȡճ����ͼƬ
			Image image = ClipboardContent.getImageFromClipboard();
			File file= new File("D:\\a.png");
			//ת��jpg
			//BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			//ת��png
			BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bufferedImage.createGraphics();
			g.drawImage(image, null, null);
			//ImageIO.write((RenderedImage)bufferedImage, "jpg", file);
			ImageIO.write((RenderedImage)bufferedImage, "png", file);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �Ӽ��а������֡�
	 */
	public static String getSysClipboardText() {
		String ret = "";
		Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
		// ��ȡ���а��е�����
		Transferable clipTf = sysClip.getContents(null);
		
		if (clipTf != null) {
			// ��������Ƿ����ı�����
			if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					ret = (String) clipTf
							.getTransferData(DataFlavor.stringFlavor);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	/**
	 * ���ַ������Ƶ����а塣
	 */
	public static void setSysClipboardText(String writeMe) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection(writeMe);
		clip.setContents(tText, null);
	}

	/**
	 * �Ӽ��а���ͼƬ��
	 */
	public static Image getImageFromClipboard() throws Exception {
		Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable cc = sysc.getContents(null);
		if (cc == null)
			return null;
		else if (cc.isDataFlavorSupported(DataFlavor.imageFlavor))
			return (Image) cc.getTransferData(DataFlavor.imageFlavor);
		return null;
	}

	/**
	 * ����ͼƬ�����а塣
	 */
	public static void setClipboardImage(final Image image) {
		Transferable trans = new Transferable() {
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}

			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if (isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}

		};
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans,
				null);
	}
}

