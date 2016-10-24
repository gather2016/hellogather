
	import java.awt.AlphaComposite;
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.geom.AffineTransform;
	import java.awt.image.AffineTransformOp;
	import java.awt.image.BufferedImage;
	import java.io.BufferedInputStream;
	import java.io.BufferedOutputStream;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.OutputStream;
	import java.util.ArrayList;
	import java.util.List;

	import javax.imageio.ImageIO;

	/**
	 * 该类是图片处理类
	 * 
	 * @author 王永丰
	 * 出处:http://www.cnblogs.com/zovon/p/4345501.html
	 * @update gather
	 */
	public final class ImageUtil {
	    /** 图片格式：PNG */
	    private static final String PICTRUE_FORMATE_JPG = "PNG";

	    private ImageUtil() {
	    }
	/**
	     * 生成组合头像
	     * 
	     * @param paths
	     *            用户图像
	     * @throws IOException
	     */
	    public static void getCombinationOfhead(List<String> paths)
	            throws IOException {
	        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
	        // 压缩图片根据个数生成不同尺寸的图片 124x124 60x60 40x40
	        for (int i = 0; i < paths.size(); i++) {
	        	if(paths.size()==1){
	        		bufferedImages.add(ImageUtil.resize2(paths.get(i), 124, 124, true));
	        	}else if(paths.size()<=4&&paths.size()>1){
	        		bufferedImages.add(ImageUtil.resize2(paths.get(i), 60, 60, true));
	        	}else{
	        		bufferedImages.add(ImageUtil.resize2(paths.get(i), 40, 40, true));
	        	}
	        }

	        int width = 132; // 这是画板的宽高

	        int height = 132; // 这是画板的高度

	        // BufferedImage.TYPE_INT_RGB可以自己定义可查看API

	        BufferedImage outImage = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);

	        // 生成画布
	        Graphics g = outImage.getGraphics();
	        
	        Graphics2D g2d = (Graphics2D) g;
	        
	        // 设置背景色
	        g2d.setBackground(new Color(231,231,231));
	        
	        // 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
	        g2d.clearRect(0, 0, width, height);
	        
	        // 开始拼凑 根据图片的数量判断该生成那种样式的组合头像目前为4中
	        int j = 1;
	        int m = 1;
	        for (int i = 1; i <= bufferedImages.size(); i++) {
	        	if (bufferedImages.size() == 9) {
	        		if (i <= 3) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * i + 3 * i - 40
	                            , 3, null);
	                } else if (i >3&&i<=6) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * j + 3 * j
	                    		- 40, 46, null);
	                    j++;
	                } else {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * m + 3 * m
	                            - 40, 89, null);
	                    m++;
	                }
	            }else if (bufferedImages.size() == 8) {
	            	if (i <= 2) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * i + 3 * i - 20
	                            , 3, null);
	                } else if (i >2&&i<=5) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * j + 3 * j
	                    		- 40, 46, null);
	                    j++;
	                } else {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * m + 3 * m
	                            - 40, 89, null);
	                    m++;
	                }
	            }else if (bufferedImages.size() == 7) {
	            	if (i <= 1) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 46
	                            , 3, null);
	                } else if (i >1&&i<=4) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * j + 3 * j
	                    		- 40, 46, null);
	                    j++;
	                } else {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * m + 3 * m
	                            - 40, 89, null);
	                    m++;
	                }
	            }else if (bufferedImages.size() == 6) {
	        		if (i <= 3) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * i + 2 * i-40
	                            , 22, null);
	                } else {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * j + 2 * j
	                            - 40, 66, null);
	                    j++;
	                }
	            }else if (bufferedImages.size() == 5) {
	        		if (i <= 2) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * i + 5 * i-20
	                            , 22, null);
	                } else {
	                    g2d.drawImage(bufferedImages.get(i - 1), 40 * j + 5 * j
	                            - 40, 66, null);
	                    j++;
	                }
	            }else if (bufferedImages.size() == 4) {
	                if (i <= 2) {
	                    g2d.drawImage(bufferedImages.get(i - 1), 60 * i + 4 * i
	                            - 60, 4, null);
	                } else {
	                    g2d.drawImage(bufferedImages.get(i - 1), 60 * j + 4 * j
	                            - 60, 68, null);
	                    j++;
	                }
	            } else if (bufferedImages.size() == 3) {
	                if (i <= 1) {

	                    g2d.drawImage(bufferedImages.get(i - 1), 36, 4, null);

	                } else {

	                    g2d.drawImage(bufferedImages.get(i - 1), 60 * j + 4 * j
	                            - 60, 68, null);

	                    j++;
	                }

	            } else if (bufferedImages.size() == 2) {

	                g2d.drawImage(bufferedImages.get(i - 1), 60 * i + 4 * i - 60,
	                        36, null);

	            } else if (bufferedImages.size() == 1) {

	                g2d.drawImage(bufferedImages.get(i - 1), 4, 4, null);

	            }

	            // 需要改变颜色的话在这里绘上颜色。可能会用到AlphaComposite类
	        }

	        String outPath = "E:\\b.png";

	        String format = "PNG";

	        ImageIO.write(outImage, format, new File(outPath));
	    }

	    /**
	     * 图片缩放
	     * 
	     * @param filePath
	     *            图片路径
	     * @param height
	     *            高度
	     * @param width
	     *            宽度
	     * @param bb
	     *            比例不对时是否需要补白
	     */
	    public static BufferedImage resize2(String filePath, int height, int width,
	            boolean bb) {
	        try {
	            double ratio = 0; // 缩放比例
	            File f = new File(filePath);
	            BufferedImage bi = ImageIO.read(f);
	            Image itemp = bi.getScaledInstance(width, height,
	                    Image.SCALE_SMOOTH);
	            // 计算比例
	            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
	                if (bi.getHeight() > bi.getWidth()) {
	                    ratio = (new Integer(height)).doubleValue()
	                            / bi.getHeight();
	                } else {
	                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
	                }
	                AffineTransformOp op = new AffineTransformOp(
	                        AffineTransform.getScaleInstance(ratio, ratio), null);
	                itemp = op.filter(bi, null);
	            }
	            if (bb) {
	                // copyimg(filePath, "D:\\img");
	                BufferedImage image = new BufferedImage(width, height,
	                        BufferedImage.TYPE_INT_RGB);
	                Graphics2D g = image.createGraphics();
	                g.setColor(Color.white);
	                g.fillRect(0, 0, width, height);
	                if (width == itemp.getWidth(null))
	                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
	                            itemp.getWidth(null), itemp.getHeight(null),
	                            Color.white, null);
	                else
	                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
	                            itemp.getWidth(null), itemp.getHeight(null),
	                            Color.white, null);
	                g.dispose();
	                itemp = image;
	            }
	            return (BufferedImage) itemp;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	}
