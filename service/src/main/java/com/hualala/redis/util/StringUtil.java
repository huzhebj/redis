package com.hualala.redis.util;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class StringUtil
{


   public static Map<String, String> getParasMap(HttpServletRequest request)
   {
      Enumeration er = request.getParameterNames();
      Map<String, String> paras = new HashMap<String, String>();
      if (er != null)
      {
         while (er.hasMoreElements())
         {
            Object key = (Object) er.nextElement();
            String value = request.getParameter(key.toString());
            paras.put(key.toString(), value);
         }
      }
      return paras;
   }

   /**
    * 将双精度浮点转换小数后为4位的数
    * @param num
    * @return
    */
   public static double getDouble(double num)
   {
      DecimalFormat df = new DecimalFormat("#.####");
      String numStr = df.format(num);
      return Double.parseDouble(numStr);
   }

   public static Integer getInteger(Object obj, Integer defaultVal)
   {
      Integer val = null;
      if (obj != null)
      {
         String str = obj.toString();
         try
         {
            val = Integer.parseInt(str);
         }
         catch (Exception e)
         {
            if (defaultVal != null)
            {
               return defaultVal;
            }
         }
      }
      else
      {
         if (defaultVal != null)
         {
            return defaultVal;
         }
      }
      return val;
   }

   /**
    * 小数位数
    * @param num
    * @param position
    * @return
    */
   public static double getDouble(Double num, int position)
   {
      if (num == null)
      {
         num = 0D;
      }
      String formart = "#.";
      for (int i = 0; i < position; i++)
      {
         formart += "#";
      }
      DecimalFormat df = new DecimalFormat(formart);
      String numStr = df.format(num);
      return Double.parseDouble(numStr);
   }

   public static Double getDouble(Object obj, Double defaultVal)
   {
      Double val = null;
      if (obj != null)
      {
         String str = obj.toString();
         try
         {
            val = Double.parseDouble(str);
         }
         catch (Exception e)
         {
            if (defaultVal != null)
            {
               return defaultVal;
            }
         }
      }
      else
      {
         if (defaultVal != null)
         {
            return defaultVal;
         }
      }
      return val;
   }

   /**
    * 数字位数补齐
    * @param no
    * @param bit
    * @return
    */
   public static String getNo(int no, int bit)
   {
      String seqNum = String.format("%0" + bit + "d", no);
      return seqNum;
   }

   public static String[] getStringArry(String[] args)
   {
      List<String> list = new LinkedList<String>();
      for (String arg : args)
      {
         if (isNotEmpty(arg))
         {
            list.add(arg);
         }
      }
      return list.toArray(new String[list.size()]);
   }

   /**
    * gZip压缩方法
    * */
   public static byte[] gZip(byte[] data)
   {
      byte[] b = null;
      try
      {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         GZIPOutputStream gzip = new GZIPOutputStream(bos);
         gzip.write(data);
         gzip.finish();
         gzip.close();
         b = bos.toByteArray();
         bos.close();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return b;
   }

   /**
    * gZip解压方法
    * */
   public static byte[] unGZip(byte[] data)
   {
      byte[] b = null;
      try
      {
         ByteArrayInputStream bis = new ByteArrayInputStream(data);
         GZIPInputStream gzip = new GZIPInputStream(bis);
         byte[] buf = new byte[1024];
         int num = -1;
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         while ((num = gzip.read(buf, 0, buf.length)) != -1)
         {
            baos.write(buf, 0, num);
         }
         b = baos.toByteArray();
         baos.flush();
         baos.close();
         gzip.close();
         bis.close();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return b;
   }
   public static Long getLongDate(Long date, int remainBit)
   {
      return Long.parseLong((date + "").substring(0, remainBit));
   }
   /**
    * gZip解压方法
    * */
   public static byte[] unGZip(InputStream in) throws Exception
   {
      byte[] b = null;
      try
      {
         GZIPInputStream gzip = new GZIPInputStream(in);
         byte[] buf = new byte[1024];
         int num = -1;
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         while ((num = gzip.read(buf, 0, buf.length)) != -1)
         {
            baos.write(buf, 0, num);
         }
         b = baos.toByteArray();
         baos.flush();
         baos.close();
         gzip.close();
         in.close();
      }
      catch (Exception ex)
      {
         throw ex;
      }
      return b;
   }

   public static String getSql_In_String(Collection<String> sqlvalues)
   {
      StringBuffer sb = new StringBuffer();
      for (String username : sqlvalues)
      {
         sb.append("'" + username + "',");
      }
      if (sb.toString().length() > 0 && sb.toString() != "")
      {
         return sb.toString().substring(0, sb.toString().length() - 1);
      }
      return "''";
   }

   public static String getSql_In_String(String[] sqlvalues)
   {
      StringBuffer sb = new StringBuffer();
      for (String username : sqlvalues)
      {
         sb.append("'" + username + "',");
      }
      if (sb.toString().length() > 0 && sb.toString() != "")
      {
         return sb.toString().substring(0, sb.toString().length() - 1);
      }
      return "''";
   }

   public static String getSql_In_String(String sqlvalue)
   {
      StringBuffer sb = new StringBuffer();
      String[] usernames = sqlvalue.split(",");
      for (String username : usernames)
      {
         sb.append("'" + username + "',");
      }
      if (sb.toString().length() > 0 && sb.toString() != "")
      {
         return sb.toString().substring(0, sb.toString().length() - 1);
      }
      return "''";
   }

   public static boolean isBlank(String str)
   {
      int strLen;
      if (str == null || (strLen = str.length()) == 0)
         return true;
      for (int i = 0; i < strLen; i++)
         if (!Character.isWhitespace(str.charAt(i)))
            return false;

      return true;
   }

   public static boolean isNotBlank(String str)
   {
      return !isBlank(str);
   }

   /**
    *
    * @param ids 如：'id1','id2','id3'
    * @return
    */
   public static List<String> getListByIds(String ids)
   {
      List<String> idList = new ArrayList<String>();
      if (!isEmpty(ids))
      {
         ids = ids.replaceAll("'", "");
         String[] idarry = ids.split(",");
         for (String id : idarry)
         {
            if(!id.equals("")){
               idList.add(id);
            }
         }
      }
      return idList;
   }
   /*public static void main(String[] args)
   {
      String str = rmEqStrs("'id1','id2','id2','id3','id3'");
      System.out.println(str);
   }*/
   /**
    *
    * @param ids 如：'id1','id2','id3'
    * @return
    */
   public static String rmEqStrs(String ids)
   {
      Set<String> idList = new HashSet<String>();
      if (isNotEmpty(ids))
      {
         String[] idarry = ids.split(",");
         for (String id : idarry)
         {
            idList.add(id);
         }
         StringBuilder newids=new StringBuilder();
         for(String id:idList){
            newids.append(id+",");
         }
         return newids.toString().substring(0,newids.length()-1);
      }
      return "";
   }

   public static Map<String, Integer> getStrings(Collection<String> list)
   {
      Map<String, Integer> map = new HashMap<String, Integer>();
      if (list != null)
      {
         for (String key : list)
         {
            if (map.containsKey(key))
            {
               Integer count = map.get(key);
               count = count + 1;
               map.put(key, count);
            }
            else
            {
               map.put(key, 1);
            }
         }
         return map;
      }
      else
      {
         return null;
      }
   }

   /**
    * 得到Object的String
    * @param o
    * @return
    */
   public static String getObjectString(Object o)
   {
      if (o != null)
      {
         return o.toString().trim();
      }
      return null;
   }

   /**
    * 得到Object的String
    * @param o
    * @return
    */
   public static double getDouble(Object o)
   {
      String val = getObjectString(o);
      if (val == null)
      {
         return 0;
      }
      else
      {
         return Double.parseDouble(val);
      }
   }
   /**
    * 得到Object的String
    * @param o
    * @return
    */
   public static boolean getBoolean(Object o,boolean defaultvalue)
   {
      String val = getObjectString(o);
      if (val == null)
      {
         return defaultvalue;
      }
      else
      {
         return Boolean.parseBoolean(val);
      }
   }
   /**
    * 得到Object的String
    * @param o
    * @return
    */
   public static String getObjectString(String o)
   {
      if (o == null)
      {
         return "";
      }
      else
      {
         return o.trim();
      }
   }

   public static Object getSqlString(Object o)
   {
      if (o == null)
      {
         return " ";
      }
      else
      {
         return o;
      }
   }

   /**
    * 得到String
    * @param str
    * @return
    */
   public static String getString(Object str,String defaultString)
   {
      if (isNotEmpty(str))
      {
         return str.toString().trim();
      }
      else
      {
         return defaultString;
      }
   }

   /**
    * 得到字符串的指定个数字
    * @return
    */
   public static String getSubString(String content, int charNum)
   {
      if (isNotEmpty(content) && content.length() > charNum)
      {
         content = content.substring(0, charNum - 1) + "...";
      }
      return content;
   }

   /**
    * 判断是否为空
    * ""是为空
    * @param o
    * @return
    */
   public static boolean isEmpty(Object o)
   {
      boolean isEmpty = false;
      if (o != null && o instanceof String)
      {
         String ostr = o.toString().trim();
         if (ostr.equals(""))
         {
            isEmpty = true;
         }
      }
      else if (o == null)
      {
         isEmpty = true;
      }
      return isEmpty;
   }

   public static boolean isNotEmpty(Object o)
   {
      boolean e = isEmpty(o);
      return !e;
   }

   /**  
    * 删除字符里面的html格式  
    *   
    * @param content
    * @return  
    */
   public static String htmlFilter(String content)
   {
      if (isNotEmpty(content))
      {
         content = content.replaceAll("</?[^>]+>", ""); //剔出了<html>的标签 
         //content=content.replace("&nbsp;",""); 
         //content=content.replace(".",""); 
         //content=content.replace("\"","‘");
         //content=content.replace("'","‘");
      }
      return content;
   }

   /*public static void charcode(String str)
   {
      try
      {
         List<String> list = new ArrayList<String>();
         list.add("utf-8");
         list.add("gbk");
         list.add("gb2312");
         list.add("ISO-8859-1");
         for (String charcode : list)
         {
            String newstr = new String(str.getBytes(charcode));
            System.out.println(charcode);
            System.out.println(newstr);
         }
         for (int i = 0; i < list.size(); i++)
         {
            String charcode = list.get(i);
            for (int j = i; j < list.size(); j++)
            {
               String charcode1 = list.get(j);

               String newstr = new String(str.getBytes(charcode), charcode1);
               System.out.println(charcode + "==" + charcode1);
               System.out.println(newstr);
               String newstr1 = new String(str.getBytes(charcode1), charcode);
               System.out.println(charcode1 + "==" + charcode);
               System.out.println(newstr1);
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }*/

   /**
    * 
    * @Title: createRandomData
    * @Description: TODO(生成一个随机数)
    * @param num
    * @return String
    * @throws
    * @author yinls 2014-1-7
    */
   public static String createRandomData(int num)
   {
      Random r = new Random();
      String charValue = "";
      for (int i = 0; i < num; i++)
      {
         char c = (char) ((0 + r.nextInt(10 - 0)) + '0');
         charValue += String.valueOf(c);
      }
      return charValue;
   }

   /**
    * 对URL参数URL编码
    * @param parastr
    * @param endcode
    * @return
    * @throws Exception
    */
   public static String urlParaEncode(String parastr, String endcode) throws Exception
   {
      try
      {
         StringBuilder sb = new StringBuilder();
         if (!StringUtil.isEmpty(parastr) && parastr.indexOf("&") != -1)
         {
            String[] paras = parastr.split("&");
            for (String para : paras)
            {
               if (para.contains("="))
               {
                  String[] paraarry = para.split("=");
                  sb.append(paraarry[0]);
                  sb.append("=");
                  if (paraarry.length > 1)
                  {
                     String epara = URLEncoder.encode(paraarry[1], endcode);
                     sb.append(epara);
                  }
                  sb.append("&");
               }
            }
         }
         else
         {
            return parastr;
         }
         String newpara = sb.toString();
         if (!StringUtil.isEmpty(newpara) && newpara.endsWith("&"))
         {
            newpara = newpara.substring(0, newpara.length() - 1);
         }
         return newpara;
      }
      catch (Exception e)
      {
         throw e;
      }
   }

   public static String getSql_In_String_noQuot(Collection sqlvalues)
   {
      StringBuffer sb = new StringBuffer();
      if (sqlvalues != null && sqlvalues.size() > 0)
      {
         for (Object username : sqlvalues)
         {
            sb.append(username.toString() + ",");
         }
         if (sb.toString().length() > 0 && sb.toString() != "")
         {
            return sb.toString().substring(0, sb.toString().length() - 1);
         }
      }
      return null;
   }
}
