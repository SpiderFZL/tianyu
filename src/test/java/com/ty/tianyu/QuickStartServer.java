package com.ty.tianyu;

import org.eclipse.jetty.server.Server;

import com.ty.tianyu.jetty.JettyFactory;
import com.ty.tianyu.jetty.Profiles;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车快速重新加载应用.
 * 
 * @author calvin
 */
public class QuickStartServer {

	public static final int PORT = 8080;
	public static final String CONTEXT = "/tianyu";
	public static final String[] TLD_JAR_NAMES = new String[] {"spring-webmvc", "shiro-web"};

	public static void main(String[] args) throws Exception {
		// 设定Spring的profile
		Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);

		// 启动Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {
			server.start();

			System.out.println("[INFO] 服务启动  http://localhost:" + PORT + CONTEXT);
			System.out.println("[HINT] 按回车迅速重启");

			// 等待用户输入回车重载应用.
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}