package com.motoband.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

public class BeanUtils {

	/**
	 * 
	 * @Description bean转map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> objectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (field.get(obj) == null) {
				try {
					if (field.getType().getName().contains("com.motoband.model")) {
						String res = JSON.toJSONString(field.get(obj));
						map.put(field.getName(), res);
					} else {
						map.put(field.getName(), String.valueOf(field.get(obj)));
					}
				} catch (Exception e) {
					map.put(field.getName(), field.get(obj) + "");
				}
			} else {
				if (field.getType().getName().contains("com.motoband.model")) {
					String res = JSON.toJSONString(field.get(obj));
					map.put(field.getName(), res);
				} else if (field.getType().getName().equals("byte")) {
					map.put(field.getName(), String.valueOf(field.get(obj)));
				} else if (field.getType().getName().equals("long")) {
					map.put(field.getName(), String.valueOf(field.get(obj)));
				} else if (field.getType().getName().equals("int")) {
					map.put(field.getName(), String.valueOf(field.get(obj)));
				} else if (field.getType().getName().equals("double")) {
					map.put(field.getName(), String.valueOf(field.get(obj)));
				}  else if (field.getType().getName().equals("float")) {
					map.put(field.getName(), String.valueOf(field.get(obj)));
				}else {
					map.put(field.getName(), String.valueOf(field.get(obj)));
				}
			}
		}
		return map;
	}
	
	public static Map<String, Object>  objectToMapObj(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = Maps.newHashMap();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (field.get(obj) == null) {
				try {
					if (field.getType().getName().contains("com.motoband.model")) {
						Object res = field.get(obj);
						map.put(field.getName(), res);
					} else {
						map.put(field.getName(), field.get(obj));
					}
				} catch (Exception e) {
					map.put(field.getName(), field.get(obj));
				}
			} else {
				if (field.getType().getName().contains("com.motoband.model")) {
					Object res = field.get(obj);
					map.put(field.getName(), res);
				} else if (field.getType().getName().equals("byte")) {
					map.put(field.getName(), field.get(obj));
				} else if (field.getType().getName().equals("long")) {
					map.put(field.getName(), field.get(obj));
				} else if (field.getType().getName().equals("int")) {
					map.put(field.getName(), field.get(obj));
				} else if (field.getType().getName().equals("double")) {
					map.put(field.getName(), field.get(obj));
				}  else if (field.getType().getName().equals("float")) {
					map.put(field.getName(), field.get(obj));
				}else {
					map.put(field.getName(), field.get(obj));
				}
			}
		}
		return map;
	}

	public static <T> T mapToObject(Map<String, String> map, Class<T> beanClass) throws Exception {
		if (map == null)
			return null;

		T obj = beanClass.newInstance();

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}
			field.setAccessible(true);
			Object name = map.get(field.getName());
//			 System.out.println(field.getName()+"-=--"+field.getType().getName());
//			 if("thumbUrl".equals(field.getName())){
//				 System.out.println("llll");
//			 }
			if (name==null|| name.equals("null")) {
				if (field.getType().getName().equals("byte")) {
					field.set(obj, (byte) 0);
				} else if (field.getType().getName().equals("long")) {
					field.set(obj, 0l);
				} else if (field.getType().getName().equals("int")) {
					field.set(obj, 0);
				} else if (field.getType().getName().equals("double")) {
					field.set(obj, 0.00);
				} else if (field.getType().getName().equals("float")) {
					field.set(obj, 0);
				}else {
					field.set(obj, null);
				}
			} else {
				if (field.getType().getName().contains("com.motoband.model")) {
					String str = String.valueOf(name);
					Map maps = (Map) JSON.parse(str);
					Class<?> clazz = Class.forName(field.getType().getName());
					Object o = mapToObject(maps, clazz);
					field.set(obj, o);
				} else if (field.getType().getName().equals("java.lang.String")) {
					field.set(obj, name);
				} else if (field.getType().getName().equals("byte")) {
					field.set(obj, Byte.parseByte(String.valueOf(name)));
				} else if (field.getType().getName().equals("long")) {
					field.set(obj, Long.parseLong(String.valueOf(name)));
				} else if (field.getType().getName().equals("int")) {
					field.set(obj, Integer.parseInt(String.valueOf(name)));
				} else if (field.getType().getName().equals("double")) {
					field.set(obj, Double.parseDouble(String.valueOf(name)));
				} else if (field.getType().getName().equals("float")) {
					field.set(obj, Float.parseFloat(String.valueOf(name)));
				}
			}
		}

		return obj;
	}
}
