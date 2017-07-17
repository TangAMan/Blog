package com.blog.util;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class jedisFactory {

	private static Properties properties;
	static{
		properties = new Properties();
		InputStream inputStream = null;
		try{
			inputStream=jedisFactory.class.getClassLoader().getResourceAsStream("redis.properties");
			properties.load(inputStream);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	static{
		jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
		jedisPoolConfig.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
		jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
		
	}
	//工厂
	private static jedisFactory factory=new jedisFactory();
	
	public static jedisFactory getFactory(){
		return factory;
	}
	//连接池
	private  JedisPool jedisPool=new JedisPool(jedisPoolConfig, properties.getProperty("url"), Integer.parseInt(properties.getProperty("port")));
	public JedisPool getPool(){
		return jedisPool;
	}
	
	
	public  void close(Jedis jedis){
		jedis.close();
	}
}
