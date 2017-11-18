package homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// 图形类
class MyShape {
	Shape shape;
	Color foreC;
	Color backC;
	// 是否是填充型图形
	boolean isFilled = true;
	// 如果是文字的话，是什么文字
	String inputValue = null;
	// 文字的坐标
	double strX = 0;
	double strY = 0;
}

// 画布类
public class Painter {
	// 画布大小
	private final int WIDTH = 1000;
	private final int HEIGHT = 800;
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	// 各个按钮
	private JButton pencil = new JButton("Pencil");
	private JButton oval = new JButton("Oval");
	private JButton line = new JButton("Line");
	private JButton rectangle = new JButton("Rectangle");
	private JButton text = new JButton("Text");
	private JButton foreC = new JButton("Foreground Color");
	private JButton backC = new JButton("Background Color");
	private JComboBox<String> chooseMethod = new JComboBox<>(new String[] {"Fill", "Outline"});
	private JButton clear = new JButton("Clear");
	private JButton save = new JButton("Save");
	private JButton open = new JButton("Open");
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);;
	private File file = null;
	private JFileChooser fileChooser = new JFileChooser();
	// 工具名
	private String name = "PENCIL";
	private Layer layer = new Layer();
	public void init() {
		// 设置按钮区域大小
		panel.setLayout(new GridLayout(11, 1));
		// 制成白色
		image.getGraphics().fillRect(0, 0, WIDTH, HEIGHT);
		// Add buttons
		// 为各个工具进行初始化
		panel.add(pencil);
		pencil.addActionListener(ae -> {
			name = "PENCIL";
			layer.preX = -1;
			layer.preY = -1;
		});
		panel.add(line);
		line.addActionListener(ae -> {
			name = "LINE";
			layer.preX = -1;
			layer.preY = -1;
		});
		panel.add(oval);
		oval.addActionListener(ae -> {
			name = "OVAL";
			layer.preX = -1;
			layer.preY = -1;
		});
		panel.add(rectangle);
		rectangle.addActionListener(ae -> {
			name = "RECTANGLE";
			layer.preX = -1;
			layer.preY = -1;
		});
		panel.add(text);
		text.addActionListener(ae -> {
			name = "TEXT";
			String inputValue = JOptionPane.showInputDialog("Please enter the text to paint:");
			layer.inputValue = inputValue;
			layer.preX = -1;
			layer.preY = -1;
		});
		panel.add(foreC);
		foreC.addActionListener(ae -> {
			layer.foreC = JColorChooser.showDialog(frame, "Choose foreground color", layer.foreC);
		});
		panel.add(backC);
		backC.addActionListener(ae -> {
			layer.backC = JColorChooser.showDialog(frame, "Choose background color", layer.backC);
		});
		panel.add(chooseMethod);
		chooseMethod.setSelectedIndex(0);
		chooseMethod.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (chooseMethod.getSelectedIndex() == 0) {
					layer.isFilled = true;
				}
				else {
					layer.isFilled = false;
				}
			}
		});
		panel.add(clear);
		clear.addActionListener(ae -> {
			layer.shapes.clear();
			layer.temp = null;
			layer.preX = -1;
			layer.preY = -1;
			layer.repaint();
		});
		panel.add(save);
		// 首先选择保存地址，随后重画保存的图形进缓存图片类中，在进行imageIO写进记录的地址中
		save.addActionListener(ae -> {
			fileChooser.showSaveDialog(frame);
			file = fileChooser.getSelectedFile();
			Graphics2D g2d = (Graphics2D)image.getGraphics();
			for (int i = 0; i < layer.shapes.size(); ++i) {
				if (layer.shapes.get(i).inputValue == null) {
					if (layer.shapes.get(i).isFilled == true) {
						g2d.setColor(layer.shapes.get(i).backC);
						g2d.fill(layer.shapes.get(i).shape);
					}
					g2d.setColor(layer.shapes.get(i).foreC);
					g2d.draw(layer.shapes.get(i).shape);
				}
				else {
					g2d.setColor(layer.shapes.get(i).foreC);
					g2d.drawString(layer.shapes.get(i).inputValue, (float)layer.shapes.get(i).strX, (float)layer.shapes.get(i).strY);
				}
			}
			if (layer.temp != null) {
				if (layer.temp.inputValue == null) {
					if (layer.temp.isFilled == true) {
						g2d.setColor(layer.temp.backC);
						g2d.fill(layer.temp.shape);
					}
					g2d.setColor(layer.temp.foreC);
					g2d.draw(layer.temp.shape);
				}
				else {
					g2d.setColor(layer.temp.foreC);
					g2d.drawString(layer.temp.inputValue, (float)layer.temp.strX, (float)layer.temp.strY);
				}
			}
			try {
				ImageIO.write(image, "jpeg", file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		panel.add(open);
		open.addActionListener(ae -> {
			fileChooser.showOpenDialog(frame);
			file = fileChooser.getSelectedFile();
			try {
				image = ImageIO.read(file);
				layer.shapes.clear();
				layer.temp = null;
				layer.preX = -1;
				layer.preY = -1;
				layer.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		// 设置画板初始化大小
		layer.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		layer.init();
		frame.add(layer);
		// 在最左侧添加按钮列表
		frame.add(panel, BorderLayout.WEST);
		// 整理画板
		frame.pack();
		// 设置默认关闭动作
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 画板可见
		frame.setVisible(true);
	}
	
	class Layer extends JPanel {
		Color foreC = Color.BLACK;
		Color backC = Color.WHITE;
		boolean isFilled = true;
		double preX = -1;
		double preY = -1;
		MyShape temp = null;
		String inputValue = null;
		ArrayList<MyShape> shapes = new ArrayList<>();
		public void init() {
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// 当鼠标按下，记录其坐标并根据工具类型选择创建新图形类
					temp = new MyShape();
					temp.foreC = foreC;
					temp.backC = backC;
					temp.isFilled = isFilled;
					preX = e.getPoint().getX();
					preY = e.getPoint().getY();
					if (name.equals("PENCIL")) {
						temp.isFilled = false;
						temp.shape = new Line2D.Double(preX, preY, preX, preY);
					}
					else if (name.equals("OVAL")) {
						temp.shape = new Ellipse2D.Double(preX, preY, 0, 0);
					}
					else if (name.equals("LINE")) {
						temp.isFilled = false;
						temp.shape = new Line2D.Double(preX, preY, 0, 0);
					}
					else if (name.equals("RECTANGLE")) {
						temp.shape = new Rectangle2D.Double(preX, preX, 0, 0);
					}
					else if (name.equals("TEXT")) {
						temp.isFilled = false;
						temp.inputValue = inputValue;
						temp.strX = preX;
						temp.strY = preY;
					}
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// 当鼠标放开，把最后停留的位置的图形写入图形集合并重绘
					if (name.equals("PENCIL")) {
						((Line2D.Double)temp.shape).x2 = e.getPoint().getX();
						((Line2D.Double)temp.shape).y2 = e.getPoint().getY();
					}
					else if (name.equals("OVAL")) {
						((Ellipse2D.Double)temp.shape).x = Math.min(preX, e.getPoint().getX());
						((Ellipse2D.Double)temp.shape).y = Math.min(preY, e.getPoint().getY());
						((Ellipse2D.Double)temp.shape).height = Math.abs(e.getPoint().getY() - preY);
						((Ellipse2D.Double)temp.shape).width = Math.abs(e.getPoint().getX() - preX);
					}
					else if (name.equals("LINE")) {
						((Line2D.Double)temp.shape).x2 = e.getPoint().getX();
						((Line2D.Double)temp.shape).y2 = e.getPoint().getY();
					}
					else if (name.equals("RECTANGLE")) {
						((Rectangle2D.Double)temp.shape).x = Math.min(preX, e.getPoint().getX());
						((Rectangle2D.Double)temp.shape).y = Math.min(preY, e.getPoint().getY());
						((Rectangle2D.Double)temp.shape).height = Math.abs(e.getPoint().getY() - preY);
						((Rectangle2D.Double)temp.shape).width = Math.abs(e.getPoint().getX() - preX);
					}
					shapes.add(temp);
					temp = null;
					preX = -1;
					preY = -1;
					layer.repaint();
				}
			});
			this.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					// 当鼠标拖动，若为铅笔，建立小直线模拟铅笔效果，若为其他，则修改temp内容并重绘以模拟预览效果
					if (name.equals("PENCIL")) {
						((Line2D.Double)temp.shape).x2 = e.getPoint().getX();
						((Line2D.Double)temp.shape).y2 = e.getPoint().getY();
						shapes.add(temp);
						temp = new MyShape();
						temp.foreC = foreC;
						temp.backC = backC;
						temp.isFilled = false;
						temp.shape = new Line2D.Double(e.getPoint().getX(), e.getPoint().getY(), e.getPoint().getX(), e.getPoint().getY());
					}
					else if (name.equals("OVAL")) {
						((Ellipse2D.Double)temp.shape).x = Math.min(preX, e.getPoint().getX());
						((Ellipse2D.Double)temp.shape).y = Math.min(preY, e.getPoint().getY());
						((Ellipse2D.Double)temp.shape).height = Math.abs(e.getPoint().getY() - preY);
						((Ellipse2D.Double)temp.shape).width = Math.abs(e.getPoint().getX() - preX);
					}
					else if (name.equals("LINE")) {
						((Line2D.Double)temp.shape).x2 = e.getPoint().getX();
						((Line2D.Double)temp.shape).y2 = e.getPoint().getY();
					}
					else if (name.equals("RECTANGLE")) {
						((Rectangle2D.Double)temp.shape).x = Math.min(preX, e.getPoint().getX());
						((Rectangle2D.Double)temp.shape).y = Math.min(preY, e.getPoint().getY());
						((Rectangle2D.Double)temp.shape).height = Math.abs(e.getPoint().getY() - preY);
						((Rectangle2D.Double)temp.shape).width = Math.abs(e.getPoint().getX() - preX);
					}
					else if (name.equals("TEXT")) {
						shapes.add(temp);
						temp = new MyShape();
						temp.isFilled = false;
						temp.inputValue = inputValue;
						temp.strX = e.getPoint().getX();
						temp.strY = e.getPoint().getY();
					}
					layer.repaint();
				}
			});
		}
		@Override
		protected void paintComponent(Graphics g) {
			// repaint()方法所调用的函数，可以重绘画板
			Graphics2D g2d = (Graphics2D) g;
			// 画板置为不透明，否则会变黑
			this.setOpaque(false);
			g2d.drawImage(image, 0, 0, null);
			for (int i = 0; i < shapes.size(); ++i) {
				if (shapes.get(i).inputValue == null) {
					// 如果不是文字，先判断是否填充，然后选择颜色，然后绘图
					if (shapes.get(i).isFilled == true) {
						g2d.setColor(shapes.get(i).backC);
						g2d.fill(shapes.get(i).shape);
					}
					g2d.setColor(shapes.get(i).foreC);
					g2d.draw(shapes.get(i).shape);
				}
				else {
					//如果是文字，先判断文字颜色，然后绘制
					g2d.setColor(shapes.get(i).foreC);
					g2d.drawString(shapes.get(i).inputValue, (float)shapes.get(i).strX, (float)shapes.get(i).strY);
				}
			}
			// 存在temp中的预览类也要绘制——不过在最后绘制
			if (temp != null) {
				if (temp.inputValue == null) {
					if (temp.isFilled == true) {
						g2d.setColor(temp.backC);
						g2d.fill(temp.shape);
					}
					g2d.setColor(temp.foreC);
					g2d.draw(temp.shape);
				}
				else {
					g2d.setColor(temp.foreC);
					g2d.drawString(temp.inputValue, (float)temp.strX, (float)temp.strY);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Painter painter = new Painter();
		painter.init();
	}
}
