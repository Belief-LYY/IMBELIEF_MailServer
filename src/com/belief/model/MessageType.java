package com.belief.model;

import java.util.regex.Pattern;

public class MessageType {

	public static final String MSG_TYPE_CRLF = "\r\n";
	// 匹配空行
	public static final Pattern MSG_PATTERN_BLANK = Pattern.compile("^[\\s]$");
	// 匹配CHARSET:
	public static final Pattern MSG_PATTERN_CHARSET = Pattern
			.compile("[C|c][H|h][A|a][R|r][S|s][E|e][T|t][\\=][\\\"][\\S]+[\\\"]");
	// 匹配<CRLF>.<CRLF>
	public static final Pattern MSG_PATTERN_DATA_END = Pattern.compile("[\\r|\\n][\\.]");
	// 匹配DELE <Mail ID>
	public static final Pattern MSG_PATTERN_DELE = Pattern.compile("^[D|d][E|e][L|l][E|e][\\u0020]([0-9.]+)$");
	// Verification Regular Expression------------------------
	// 匹配HELO命令
	public static final Pattern MSG_PATTERN_HELO = Pattern
			.compile("^([H|h][E|e][L|l][O|o]|[E|e][H|h][L|l][O|o])[\\u0020]([\\S]+)$");
	// 匹配MAIL_ADDRESS
	public static final Pattern MSG_PATTERN_MAIL_ADDRESS = Pattern
			.compile("[\\w]+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	// 匹配MAIL FROM:命令
	public static final Pattern MSG_PATTERN_MAIL_FROM = Pattern.compile(
			"^[M|m][A|a][I|i][L|l][\\u0020][F|f][R|r][O|o][M|m][\\:]([\\<]?)[\\w]+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*([\\>]?)$");
	// 匹配PASS AAAAAAA
	public static final Pattern MSG_PATTERN_PASS = Pattern.compile("^[P|p][A|a][S|s][S|s][\\u0020]([^\\s]+)$");
	// 匹配QUIT命令
	public static final Pattern MSG_PATTERN_QUIT = Pattern.compile("^[Q|q][U|u][I|i][T|t]$");
	// 匹配RCPT TO:命令
	public static final Pattern MSG_PATTERN_RCPT_TO = Pattern.compile(
			"^[R|r][C|c][P|p][T|t][\\u0020][T|t][O|o][\\:]([\\<]?)[\\w]+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*([\\>]?)$");
	// 匹配RETR <Mail ID>
	public static final Pattern MSG_PATTERN_RETR = Pattern.compile("^[R|r][E|e][T|t][R|r][\\u0020]([0-9.]+)$");
	// 匹配SUBJECT:
	public static final Pattern MSG_PATTERN_SUBJECT = Pattern
			.compile("[S|s][U|u][B|b][J|j][E|e][C|c][T|t][\\:]([\\S]|\\u0020|\\t)+[\\r|\\n]");
	// 匹配UIDL <Mail ID>
	public static final Pattern MSG_PATTERN_UIDL = Pattern.compile("^[U|u][I|i][D|d][L|l][\\u0020]([0-9.]+)$");
	// 匹配USER AAAAAAA
	public static final Pattern MSG_PATTERN_USER = Pattern.compile("^[U|u][S|s][E|e][R|r][\\u0020]([\\w]+)$");

	public static final String MSG_TYPE_220 = "220 www.imbelief.com Esmtp Belief Mail Server" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_221 = "221 Bye" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_235 = "235 Authentication successful" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_250 = "250 Ok www.imbelief.com" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_334 = "334 VXNlcm5hbWU6" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_354 = "354 End data with <CR><LF>.<CR><LF>" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_502 = "502 Error: command not implemented" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_504 = "504 Error: The sending user and the current user are not the same user"
			+ MSG_TYPE_CRLF;
	public static final String MSG_TYPE_535 = "535 Please use the authorization code to login" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_AUTH_LOGIN = "AUTH LOGIN";
	// Response-----------------------------------

	public static final String MSG_TYPE_DATA = "DATA";
	public static final String MSG_TYPE_DATA_END = ".";
	// Require-------------------------------------
	public static final String MSG_TYPE_DATA_FINISH = MSG_TYPE_CRLF + "." + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_DATE = "DATE";
	public static final String MSG_TYPE_DELE_MAIL = "DELE MAIL";// 删件
	// Store-----------------------------------------
	public static final String MSG_TYPE_DOMAIN = "@imbelief.com.cn";
	public static final String MSG_TYPE_ERR = "-ERR";
	public static final String MSG_TYPE_FROM = "FROM";

	public static final String MSG_TYPE_LIST = "LIST";
	// Uncertainty-----------------------------------
	public static final String MSG_TYPE_LOGIN = "LOGIN";// 登录
	public static final String MSG_TYPE_MAIL_ADJU = "DELE MAIL";// 下载附件

	public static final String MSG_TYPE_OK = "+OK" + MSG_TYPE_CRLF;
	public static final String MSG_TYPE_RECV_MAIL = "RECV MAIL";// 收件
	public static final String MSG_TYPE_REGISTER = "REGISTER";// 注册
	public static final String MSG_TYPE_RSET = "RSET";
	public static final String MSG_TYPE_SEND_MAIL = "SEND MAIL";// 发件
	public static final String MSG_TYPE_ST_SUCCESS = "成功";
	public static final String MSG_TYPE_ST_UNREAD = "未读";
	public static final String MSG_TYPE_STARTTLS = "STARTTLS";
	public static final String MSG_TYPE_STAT = "STAT";
	public static final String MSG_TYPE_TO = "TO";
}