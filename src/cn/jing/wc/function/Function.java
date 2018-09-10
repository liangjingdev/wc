/**
 * 
 */
package cn.jing.wc.function;

import java.io.FileNotFoundException;

import cn.jing.wc.entity.CodeFileInfo;

/**
 * function:源程序文件的代码统计器程序的功能接口
 * 
 * @author liangjing
 *
 */
public interface Function {

	/**
	 * function:计算单个源程序文件
	 * 
	 * @param codeFileInfo
	 * @param sourcefile
	 * @return
	 */
	public CodeFileInfo print(CodeFileInfo codeFileInfo, String sourcefile);

	/**
	 * function:计算某个文件夹下的所有源程序文件
	 * 
	 * @param codeFileInfo
	 * @param sourceDir
	 * @return
	 * @throws FileNotFoundException
	 */
	public CodeFileInfo printFromDir(CodeFileInfo codeFileInfo, String sourceDir);

	/**
	 * function:计算多个源文件
	 * 
	 * @param codeFileInfo
	 * @param files
	 * @return
	 * @throws FileNotFoundException
	 */
	public CodeFileInfo printFromFiles(CodeFileInfo codeFileInfo, String files);
}
