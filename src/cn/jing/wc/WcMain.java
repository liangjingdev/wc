/**
 * 
 */
package cn.jing.wc;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cn.jing.wc.entity.CodeFileInfo;
import cn.jing.wc.function.BaseFunction;
import cn.jing.wc.function.ExtendFunction;
import cn.jing.wc.handler.CalculateHandler;

/**
 * function:WC程序功能界面
 * 
 * @author liangjing
 *
 */
public class WcMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3368040846659761612L;
	private final JLabel charcount = new JLabel("字符数：");
	private final JLabel wordcount = new JLabel("单词数：");
	private final JLabel linecount = new JLabel("行数：");
	private final JLabel codeLines = new JLabel("有效代码行的行数：");
	private final JLabel commentLines = new JLabel("注释行的行数：");
	private final JLabel blankLines = new JLabel("空行的行数：");
	private final JLabel filePath = new JLabel("文件路径");
	private final JTextField filePathText = new JTextField(10);
	private final JButton dqtj = new JButton("源程序文件读取并统计");
	private final JButton exit = new JButton("退出");
	private final JButton about = new JButton("关于");
	private final JButton browser = new JButton("选择");
	private CodeFileInfo codeFileInfo;
	private int code;

	/**
	 * function:构造函数
	 */
	public WcMain() {
		codeFileInfo = new CodeFileInfo();
		// 设置整个内容布局
		getContentPane().setLayout(new BorderLayout());
		// 添加监听事件
		exit.addActionListener(this);
		dqtj.addActionListener(this);
		about.addActionListener(this);
		browser.addActionListener(this);
		// 设置JFrame
		this.setTitle("代码统计器");
		this.setVisible(true);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(3, 1)); // 设置总布局

		// 设置分布局1（BorderLayout.NORTH）
		JPanel pane1 = new JPanel();
		pane1.setLayout(new GridLayout(4, 1));
		pane1.add(charcount);
		pane1.add(commentLines);
		pane1.add(wordcount);
		pane1.add(codeLines);
		pane1.add(linecount);
		pane1.add(blankLines);
		getContentPane().add(pane1);

		// 设置分布局2（BorderLayout.CENTER）
		JPanel pane2 = new JPanel();
		pane2.add(filePath);
		pane2.add(filePathText);
		pane2.add(browser);
		getContentPane().add(pane2);

		// 设置分布局3（BorderLayout.SOUTH）
		JPanel pane3 = new JPanel();
		pane3.setLayout(new GridLayout(3, 1));
		pane3.add(dqtj);
		pane3.add(exit);
		pane3.add(about);
		getContentPane().add(pane3);
	}

	/**
	 * function:程序主入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		WcMain wcMain = new WcMain();
	}

	/**
	 * function:监听事件
	 */
	public void actionPerformed(ActionEvent e) {

		// 监听按钮点击事件（e.getActionCommand()方法依赖于按钮上的字符串）
		String soruceName = e.getActionCommand();

		// 判断触发点击事件的按钮是哪一个
		if (soruceName.equals("选择")) {
			// 以当前路径创建JFileChooser文件选择器
			JFileChooser jFileChooser = new JFileChooser(".");
			// 设置该文件选择器的标题
			jFileChooser.setDialogTitle("选择文件或者文件夹");
			// 默认情况下，该文件选择器只能选择一个文件，通过该方法可以设置允许选择多个文件（设置参数为true即可）
			jFileChooser.setMultiSelectionEnabled(true);
			// 默认情况下，该文件选择器只能选择一个文件，通过调用该方法可以设置只允许选择文件，只允许选择路径，或者允许选择文件与路径
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			// 弹出文件选择对话框，该对话框的"同意"按钮的文本（默认是”保存“或”取消“）由approveButtonText来指定，有需要的时候可以自定义那个按钮的文字
			int returnval = jFileChooser.showDialog(new JLabel(), "选择");
			// 如果用户点击了"选择"按钮
			if (returnval == JFileChooser.APPROVE_OPTION) {
				// 用户选择了单个文件或者文件夹
				File file = jFileChooser.getSelectedFile();
				// 用户选择了多个文件
				File[] files = jFileChooser.getSelectedFiles();
				if (files != null && files.length > 1) {
					code = 2;
					ArrayList<String> sourceFilePath = new ArrayList<String>();
					for (int i = 0; i < files.length; i++) {
						sourceFilePath.add(files[i].getAbsolutePath());
					}
					// 多文件绝对路径
					filePathText.setText(arrayListToString(sourceFilePath));
				} else if (file != null && file.isFile()) {
					code = 1;
					// 单文件绝对路径
					filePathText.setText(file.getAbsolutePath());
				} else if (file != null && file.isDirectory()) {
					code = 3;
					// 文件夹绝对路径
					filePathText.setText(file.getAbsolutePath());
				}
			}
		} else if (soruceName.equals("退出")) {
			// 退出程序
			System.exit(0);
		} else if (soruceName.equals("源程序文件读取并统计")) {
			CalculateHandler calculateHandler = new CalculateHandler();
			setValue(calculateHandler.handle(code, codeFileInfo, filePathText.getText()));
		} else if (soruceName.equals("关于")) {
			Dialog dia = new Dialog(new Frame());
			JPanel aboutNorthpipe = new JPanel();
			JPanel aboutSouthpipe = new JPanel();

			Label lab1 = new Label();
			Label lab2 = new Label();
			Label lab3 = new Label();

			dia.setTitle("软件介绍");
			dia.setLayout(new BorderLayout());
			dia.add(aboutNorthpipe, "North");
			dia.add(aboutSouthpipe, "South");

			aboutNorthpipe.setLayout(new GridLayout(8, 3));
			aboutNorthpipe.add(lab1);
			aboutNorthpipe.add(lab2);
			aboutNorthpipe.add(lab3);

			lab1.setText("编写者：梁竞");
			lab2.setText("班级：信息安全2班");
			lab3.setText("介绍：本程序的功能是源程序文件的代码统计器");

			dia.setSize(270, 200);
			dia.setVisible(true);
			dia.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					e.getWindow().setVisible(false);
					((Window) e.getComponent()).dispose();
				}
			});
		}
	}

	/**
	 * function:将计算数据显示到界面上
	 * 
	 * @param codeFileInfo
	 */
	public void setValue(CodeFileInfo codeFileInfo) {
		linecount.setText("总行数：" + codeFileInfo.getLinecount());
		charcount.setText("字符数：" + codeFileInfo.getCharcount());
		wordcount.setText("单词数：" + codeFileInfo.getWordcount());
		codeLines.setText("有效代码行的行数：" + codeFileInfo.getCodeLines());
		commentLines.setText("注释行的行数：" + codeFileInfo.getCommentLines());
		blankLines.setText("空白行的行数：" + codeFileInfo.getBlankLines());

		initObject(codeFileInfo);

	}

	/**
	 * function:将ArrayList类型转化为String类型
	 * 
	 * @param arrayList
	 * @return
	 */
	private String arrayListToString(ArrayList<String> arrayList) {
		String result = "";
		if (arrayList != null && arrayList.size() > 0) {
			for (String item : arrayList) {
				// 把列表中的每条数据用逗号分割开来，然后拼接成字符串
				result += item + ",";
			}
			// 去掉最后一个逗号
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * function:将CodeFileInfo对象的值重新进行初始化
	 * 
	 * @param codeFileInfo
	 */
	private void initObject(CodeFileInfo codeFileInfo) {
		codeFileInfo.setBlankLines(0);
		codeFileInfo.setCharcount(0);
		codeFileInfo.setCodeLines(0);
		codeFileInfo.setCommentLines(0);
		codeFileInfo.setLinecount(0);
		codeFileInfo.setWordcount(0);
	}

}
