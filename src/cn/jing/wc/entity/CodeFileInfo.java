/**
 * 
 */
package cn.jing.wc.entity;

/**
 * function:(源程序)代码文件信息实体类
 * 
 * @author liangjing
 *
 */
public class CodeFileInfo {

	// 字符数
	public int charcount = 0;
	// 单词数
	public int wordcount = 0;
	// 行数
	public int linecount = 0;
	// 代码行
	public int codeLines = 0;
	// 注释行
	public int commentLines = 0;
	// 空行
	public int blankLines = 0;

	public int getCharcount() {
		return charcount;
	}

	public void setCharcount(int charcount) {
		this.charcount = charcount;
	}

	public int getWordcount() {
		return wordcount;
	}

	public void setWordcount(int wordcount) {
		this.wordcount = wordcount;
	}

	public int getLinecount() {
		return linecount;
	}

	public void setLinecount(int linecount) {
		this.linecount = linecount;
	}

	public int getCodeLines() {
		return codeLines;
	}

	public void setCodeLines(int codeLines) {
		this.codeLines = codeLines;
	}

	public int getCommentLines() {
		return commentLines;
	}

	public void setCommentLines(int commentLines) {
		this.commentLines = commentLines;
	}

	public int getBlankLines() {
		return blankLines;
	}

	public void setBlankLines(int blankLines) {
		this.blankLines = blankLines;
	}

}
