package com.motoband.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Consts {
	private static Logger logger = Logger.getLogger(Consts.class);

	static {
		String env = System.getProperty("databasesenv", "windowstest");
		if (StringUtils.isNotBlank(env)) {
			ENVIRONMENT = env;
		} else {
			ENVIRONMENT = "windowstest";
		}
		if (env.equals("windowstest")) {
			String s = System.getProperty("SealServices.root");
			FileInputStream in;
			try {
				in = new FileInputStream(new File(s + "/WEB-INF/classes/log4j.properties"));
				Properties p = new Properties();
				p.load(in);
				in.close();
				p.setProperty("log4j.rootLogger", "DEBUG, Console,DailyRollingFile");
				PropertyConfigurator.configure(p);
			} catch ( IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.setProperty("log4j.rootLogger", "DEBUG, Console,DailyRollingFile");
			logger.debug("debug start");
		}
		logger.error("consts init " + env);
	}

	public static final String ENVIRONMENT_PROD = "production";
	public static final String ENVIRONMENT_TEST = "windowstest";

	// 环境
	// public static final String ENVIRONMENT = ENVIRONMENT_TEST;//测试
	public static final String ENVIRONMENT;// 生产

	// 公共参数
	public static final String TIM_AdminUserID = "admin";
	public static final String TIM_YZCUSTOMERID = "admin_yzcustomer";
	public static final String TIM_ACTIVITYCENTERID = "admin_activitycenter";
	public static final String TIM_UserBlackListBanUrl = "https://console.tim.qq.com/v4/openconfigsvr/setnospeaking?usersig=%s&identifier=%s&sdkappid=%s&random=%s&contenttype=json";
	public static final String TIM_SendBatchMessageUrl = "https://console.tim.qq.com/v4/openim/batchsendmsg?usersig=%s&identifier=%s&sdkappid=%s&random=%s&contenttype=json";

	public static final String REDIS_SCHEME_FOLLOW = "REDIS_SCHEME_FOLLOW";
	public static final String REDIS_SCHEME_USER = "REDIS_SCHEME_USER";
	public static final String REDIS_SCHEME_RIDEDATA = "REDIS_SCHEME_RIDEDATA";
	public static final String REDIS_SCHEME_NEWS = "REDIS_SCHEME_NEWS";
	public static final String REDIS_SCHEME_RALLY = "REDIS_SCHEME_RALLY";
	public static final String REDIS_SCHEME_RUN = "REDIS_SCHEME_RUN";

	public static final String SECRETID = "AKIDl8fUHCxeOZaB8gzRsipx6AsJKb4NatKS";
	public static final String SECRETKEY = "14FYwQ4PtIeui1qk35XwUoi6gOiaY2SO";
	public static final String REQUESTMETHOD = "GET";
	public static final String DEFAULTREGION = "bj";
	public static final int YUNSOUAPPID = 51950002;
	public static final int NUM_PER_PAGE = 10;

	public static final String app_privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAo2VMTMp1v/bddhDSRiZsnTnMjN8paDlHKUI6yf+ZZWEg2zzsGQRG+vDSsYC9+FtE06XNEno3SGelhmYhQ6savwIDAQABAkEAhqqwbgHXdnWCJRLMG2ED17mRavFqcSn0Cn85cM6moLRPu/HBKNHrD9Iu+tXgkqXbDu3lrCwCieRUrN1CwRNrQQIhAOJ6CJvTOmBKJ4nEPw1ZGn31fCF6yzVVnb2w9DyUxgsPAiEAuLIhE00Br41lKeM7s7WyhFv5LDj4gS0jL5Moi+HQtVECIQCcjAgpVi/y4S9FXn7LBj12tdqQ9eVDP6QivA+HVLs0ZwIgKRkCASB2ipDE/QAiTcfVlFw+4tc+fMgFd1WghRfXcDECIAtbg9pD4x5JeNvftHntBXlEkyBMGGAl8p0SxCGFW5j6";
	public static final String server_publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJO2i9BK6HJucAP/71qXHMEz2JcTjfvC+2qePfGhCKiCYdMHOedfPJ/QHSF/iM2G7tDU5hLTV12ekro2gjW6o0sCAwEAAQ==";

	public static final String secretId = "AKIDKpo6me25b14nzcNefQeoqR95syh2ayx97s0g";
	public static final String secretKey = "5a38htm67thU4xyrLvtektm1OP53FjjfMtw3trf8";

	// 跟环境变化参数
	// public static String ServiceUrl = "http://192.168.50.21:8088/";
	public static String ServiceUrl = "https://api.test.motuobang.com/test";

	public static String MOTOBAND_SEARCH = "http://127.0.0.1:8091/motoband-search/";
	public static String MOTOBAND_LBSTableID = "57d11c377bbf195d70fb6130";
	public static String YZ_CLIENT_ID = "d9381d72e5755e134e";
	public static String YZ_CLIENT_SECRET = "ef8853684fb318056995a9618bab4ff3";
	public static String YZ_MALL_ID = "42441700";

	public static boolean ISOPENTASK = false;

	public static String REDIS_FOLLOW_IP = "123.206.24.130";
	public static int REDIS_FOLLOW_PORT = 6379;
	public static int REDIS_FOLLOW_DBINDEX = 0;
	public static int REDIS_FOLLOW_TIMEOUT = 100000;
	public static String REDIS_FOLLOW_AUTH = "EgM=f)";

	public static String REDIS_USER_IP = "123.206.24.130";
	public static int REDIS_USER_PORT = 6380;
	public static int REDIS_USER_DBINDEX = 0;
	public static int REDIS_USER_TIMEOUT = 100000;
	public static String REDIS_USER_AUTH = "EgM=f)";

	public static String REDIS_RIDEDATA_IP = "123.206.24.130";
	public static int REDIS_RIDEDATA_PORT = 6381;
	public static int REDIS_RIDEDATA_DBINDEX = 0;
	public static int REDIS_RIDEDATA_TIMEOUT = 100000;
	public static String REDIS_RIDEDATA_AUTH = "EgM=f)";

	public static String REDIS_NEWS_IP = "123.206.24.130";
	public static int REDIS_NEWS_PORT = 6382;
	public static int REDIS_NEWS_DBINDEX = 0;
	public static int REDIS_NEWS_TIMEOUT = 100000;
	public static String REDIS_NEWS_AUTH = "EgM=f)";

	public static String REDIS_RUN_IP = "123.206.24.130";
	public static int REDIS_RUN_PORT = 6383;
	public static int REDIS_RUN_DBINDEX = 0;
	public static int REDIS_RUN_TIMEOUT = 100000;
	public static String REDIS_RUN_AUTH = "EgM=f)";
	/* tim */
	public static String TIM_Appid = "1400025315";
	public static String TIM_AdminSig = "eJxlj11PgzAYhe-5FYTbGW1LuwWTXfhBsi8VJgnuqmHQyeuytpQiY8b-ruISSTy3z3Nycj4c13W9ZPV8meW5aqTlttPCc69dD3kXf1BrKHhmuW*Kf1AcNRjBs50VpoeYMUYQGjpQCGlhB2cjKw4gB7gu9rzf*O3T7zJhPmZDBV57*BDGd-N7FahTup6HUfTSkatolYxTZBtxLCdlWz2CNsvN7E3qUbO8gdsJNXWXLvIgLONZXG2qChtltuXpaS3bZBEQVY-MFu193U6ng0kLB3E*RMcBpYjRAX0XpgYle4EgzDDx0U8859P5Aqu-XnQ_";
	public static String TIM_YZCUSTOMERSIG = "eJxlj81qg0AYRfc*hbgudfyZSApZFFvQYBqkaYjdDJOZ0Xw16lTHRCl591oTiNC7PYd7uT*aruvGJnp-pIxVbamI6qUw9CfdQMbDHUoJnFBFnJr-g6KTUAtCUyXqEVoYYxuhqQNclApSuBmUF1ASyhScQPVsYAO42w3PyTh5rXOHLhs7Fp4qkI1w9frhh-FLztI*lFVAuzVfrr7QwY-a7XH*7c*bw2dgVg1aZnJ79sJ9HGYhTdr8*Ris33iwT8xzYQWJmdXMzeQmbtI4Zl6R71TU7arFZFJBIW7-XA8hZzabPjyJuoGqHAUbWdiyHfQXQ7tov1EjZNY_";
	public static String TIM_ACTIVITYCENTERSIG = "eJxlj81qg0AYRfc*hbgudfyZSApZFFvQYBqkaYjdDJOZ0Xw16lTHRCl591oTiNC7PYd7uT*aruvGJnp-pIxVbamI6qUw9CfdQMbDHUoJnFBFnJr-g6KTUAtCUyXqEVoYYxuhqQNclApSuBmUF1ASyhScQPVsYAO42w3PyTh5rXOHLhs7Fp4qkI1w9frhh-FLztI*lFVAuzVfrr7QwY-a7XH*7c*bw2dgVg1aZnJ79sJ9HGYhTdr8*Ris33iwT8xzYQWJmdXMzeQmbtI4Zl6R71TU7arFZFJBIW7-XA8hZzabPjyJuoGqHAUbWdiyHfQXQ7tov1EjZNY_";
	//3.7.0 mongo
	public static  String MONGO_IP="188.131.149.211";
	public static  int MONGO_PORT=20008;
	public static  String MONGO_USERNAME="mongouser";
	public static  String MONGO_PASSWORD="Motoband2015!@#";
	public static  String MONGO_DATABASE="motobandtest";
	public Consts() {

		if (ENVIRONMENT.equals(ENVIRONMENT_PROD)) {

			ISOPENTASK = true;
			ServiceUrl = "https://api.motuobang.com/release";
			MOTOBAND_SEARCH = "http://10.0.0.11:8091/motoband-search/";
			MOTOBAND_LBSTableID = "58f74481305a2a06c4e2b1b4";
			YZ_CLIENT_ID = "5f7d5616a9f8abfddd";
			YZ_CLIENT_SECRET = "3b413a9d5012f2d452f1d002d2743918";
			YZ_MALL_ID = "19045787";

			REDIS_FOLLOW_IP = "10.0.0.7";
			REDIS_FOLLOW_PORT = 6379;
			REDIS_FOLLOW_DBINDEX = 0;
			REDIS_FOLLOW_TIMEOUT = 100000;
			REDIS_FOLLOW_AUTH = "r@#Ra9lE";

			REDIS_USER_IP = "10.0.16.15";
			REDIS_USER_PORT = 6379;
			REDIS_USER_DBINDEX = 0;
			REDIS_USER_TIMEOUT = 100000;
			REDIS_USER_AUTH = "r@@Ra9lE";

			REDIS_RIDEDATA_IP = "10.0.16.2";
			REDIS_RIDEDATA_PORT = 6379;
			REDIS_RIDEDATA_DBINDEX = 0;
			REDIS_RIDEDATA_TIMEOUT = 100000;
			REDIS_RIDEDATA_AUTH = "r@@Ra9lE";

			REDIS_NEWS_IP = "10.0.16.13";
			REDIS_NEWS_PORT = 6379;
			REDIS_NEWS_DBINDEX = 0;
			REDIS_NEWS_TIMEOUT = 100000;
			REDIS_NEWS_AUTH = "r@@Ra9lE";

			REDIS_RUN_IP = "10.0.0.8";
			REDIS_RUN_PORT = 6379;
			REDIS_RUN_DBINDEX = 0;
			REDIS_RUN_TIMEOUT = 100000;
			REDIS_RUN_AUTH = "r@#Ra9lE";

			TIM_Appid = "1400025196";
			TIM_AdminSig = "eJxlj81Og0AYRfc8BWGLkWH4CZh0gY0G0lap1Da6mVBmoF*awnRmQND47io2kcS7Pefm5n5ouq4bm2V2nRdF09aKqIEzQ7-RDWRc-UHOgZJcEUfQf5D1HAQjeamYGKHteR5GaOoAZbWCEi5GTk9QT7CkRzJu-Pbd7zL27NCfKlCNcHW3nidR6lodO0jxMMgXUw5Z92i9C2uRzZ*Wb1XMkxTH-MwW99vXCCLf3G1i9BxYqzBBtwEu*rTbtfu2b1B6PuxFCUdZRUpuzfVsNplUcGKXQ77j2mEYBBPaMSGhqUcBI9uzsYN*Ymif2hdA*F2A";
			TIM_YZCUSTOMERSIG = "eJxlj11PgzAYhe-5FYRbjSuFzmniReUjLrIthBmVm4bQTrsVqLSdMrP-LuKSkfhevs*Tc3K*Ldu2nXWSXRVl2ZhaE91J5ti3tgOcyzOUklNSaOK19B9kX5K3jBQbzdoBugghCMDY4ZTVmm-4yShoxWvSHUqjdFP1z7Op6I4MdX9Rfp8DkXszHSv8bYCL6CmYx0LtxcpPEwxzlbIarwVa7e7xAr4HUTiv6PUrOLw8XiTUYB5hH8lnM8tNmjUq-lyaMBPLCQ7ySQxkxR5cEYiwg1u6-UjvRpWaV*y0ber5-Tjoj*ietYo39SBA4CIXeuD3HOto-QCQy2F5";
			TIM_ACTIVITYCENTERSIG = "eJxlj11PgzAYhe-5FU1vNQqFkszEC5xMJZuaCHLZdG1nXpGPlZaBxv8u4pKReG6fJ*fkfDkIIZyuXy64ELWtDDNDozC6QtjF5yfYNCAZN8zX8h9UfQNaMb4zSk-Qo5QS1507IFVlYAdHg8sSKsaFgQ7MIEY2gpPdyoJNk391wdhFqLcI5wq8TXATZ8uH1WN*qRIB5f32Lu0Cuh3bpeohoWn1dNvrwfhdIexrUMcRxNH*zFf6*SYkdpMLOPQk-4zSZL1c7A-2vagz2mYDsR8kX8XXs0kDpTr*C-1gPEjnDzulW6irSSCuRz3iu7-BzrfzAzzdZJE_";
			
			
			MONGO_IP="10.0.0.5";
			MONGO_PORT=27017;
			MONGO_USERNAME="mongouser";
			MONGO_PASSWORD="Motoband2015!@#";
			MONGO_DATABASE="motobandprod";
			
		}
	}

}
