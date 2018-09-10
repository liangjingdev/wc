/**
 * 
 */
package cn.jing.wc.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;

import cn.jing.wc.entity.CodeFileInfo;
import cn.jing.wc.filter.SourceFilenameFilter;

/**
 * function:拓展功能（计算源程序文件中的代码行行数、空行行数、注释行行数）
 * 
 * @author liangjing
 *
 */
public class ExtendFunction implements Function {

	/**
	 * function:计算单个源程序文件
	 * 
	 * @param codeFileInfo
	 * @param sourcefile
	 *            源程序文件绝对路径
	 * @return
	 * @throws FileNotFoundException
	 */
	public CodeFileInfo print(CodeFileInfo codeFileInfo, String sourcefile) {
		File file = new File(sourcefile);
		// 记录注释行的行数
		int commentLines = 0;
		// 记录空白行的行数
		int blankLines = 0;
		// 记录有效代码行的行数
		int codeLines = 0;
		// 假注释
		int fakeCommentLines = 0;
		BufferedReader bufferedReader = null;
		// 判断此行是否为注释行
		boolean comment = false;

		if (file != null && file.exists()) {
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					line = line.trim();
					if (line.matches("^[//s&&[^//n]]*$") || line.equals("{") || line.equals("}")) {
						// 空行:本行全部是空格或格式控制字符，如果包括代码，则只有不超过一个可显示的字符，例如“{”
						blankLines++;
					}
					// 本行不是代码行，并且本行包括注释。一个有趣的例子是有些程序员会在单字符后面加注释： }//注释
					else if (line.startsWith("/*") && !line.endsWith("*/")
							|| ((line.startsWith("{/*") || line.startsWith("}/*")) && !line.endsWith("*/"))) {
						// 判断此行为"/*"开头的注释行
						commentLines++;
						comment = true;
					} else if (comment == true && !line.endsWith("*/") && !line.startsWith("*/")) {
						// 为多行注释中的一行（不是开头和结尾）
						fakeCommentLines++;
						commentLines++;
					} else if (comment == true && (line.endsWith("*/") || line.startsWith("*/"))) {
						// 为多行注释的结束行
						commentLines++;
						comment = false;
					} else if (line.startsWith("//") || line.startsWith("}//") || line.startsWith("{//")
							|| ((line.startsWith("{/*") || line.startsWith("}/*") || line.startsWith("/*"))
									&& line.endsWith("*/"))) {
						// 单行注释行
						commentLines++;
					} else {
						// 有效代码行
						codeLines++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
						bufferedReader = null;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}

		codeFileInfo.setBlankLines(codeFileInfo.getBlankLines() + blankLines);
		codeFileInfo.setCodeLines(codeFileInfo.getCodeLines() + codeLines + fakeCommentLines);
		codeFileInfo.setCommentLines(codeFileInfo.getCommentLines() + commentLines - fakeCommentLines);

		return codeFileInfo;
	}

	/**
	 * function:计算某个文件夹下的所有源程序文件
	 * 
	 * @param codeFileInfo
	 *            文件夹绝对路径
	 * @param sourceDir
	 * @return
	 * @throws FileNotFoundException
	 */
	public CodeFileInfo printFromDir(CodeFileInfo codeFileInfo, String sourceDir) {
		// 过滤器(将相对应的源程序文件筛选出来)
		SourceFilenameFilter filter = new SourceFilenameFilter();
		// 获取该文件夹内的源程序文件
		File file = new File(sourceDir);
		File[] sourceFiles = file.listFiles(filter);
		ArrayList<String> sourceFilePath = new ArrayList<String>();

		// 获取该文件夹下的所有源程序文件所对应的绝对路径
		for (int i = 0; i < sourceFiles.length; i++) {
			if (sourceFiles[i].isFile()) {
				System.out.println("文件：" + sourceFiles[i]);
				sourceFilePath.add(sourceFiles[i].toString());
			}
			if (sourceFiles[i].isDirectory()) {
				System.out.println("文件夹：" + sourceFiles[i]);
			}
		}

		// 遍历每个源程序文件的绝对路径，利用计算方法对每个源程序文件进行计算，并且合并计算结果
		for (int i = 0; i < sourceFilePath.size(); i++) {
			print(codeFileInfo, sourceFilePath.get(i));
		}

		return codeFileInfo;
	}

	/**
	 * function:计算多个源文件
	 * 
	 * @param codeFileInfo
	 * @param files
	 * @return
	 * @throws FileNotFoundException
	 */
	public CodeFileInfo printFromFiles(CodeFileInfo codeFileInfo, String files) {

		ArrayList<String> sourceFilePath = new ArrayList<String>(Arrays.asList(files.split(",")));

		// 遍历多个源程序文件的绝对路径，利用计算方法对每个源程序文件进行计算，并且合并计算结果
		for (int i = 0; i < sourceFilePath.size(); i++) {
			print(codeFileInfo, sourceFilePath.get(i));
		}

		return codeFileInfo;
	}
}
