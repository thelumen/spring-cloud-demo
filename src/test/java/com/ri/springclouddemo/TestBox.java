package com.ri.springclouddemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ri.springclouddemo.imp.CustomizeCheckHandle;
import com.ri.springclouddemo.imp.IObjectGet;
import com.ri.springclouddemo.imp.IZhuRU;
import com.ri.springclouddemo.pojo.VmInstance;
import org.junit.Test;
import org.mockito.internal.util.StringUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static com.ri.springclouddemo.util.SM2Util.decryptBySM2;
import static com.ri.springclouddemo.util.SM2Util.encryptBySM2;
import static com.ri.springclouddemo.util.SM3Util.SM3Digest;
import static com.ri.springclouddemo.util.SM4Util.*;

public class TestBox {

    String SIGNATURE = "signature";
    String KEY = "key";
    String VALUE = "value";
    String TEXT = "plainText";
    String TIME = "signatureTime";


    @Test
    public void t14() {
        VmInstance vmInstance = new VmInstance(1, null, "asd", "asdfgh");
        VmInstance vmInstance2 = new VmInstance(null, null, "asdasdfgh", "afgh");
        List<VmInstance> vmInstances = new ArrayList<>();
        vmInstances.add(vmInstance2);
        vmInstance.setVmInstances(vmInstances);
        List<String> resultList = new ArrayList<>();
        try {
            if (!CustomizeCheckHandle.check(vmInstance, resultList)) {
                System.out.println(Arrays.toString(resultList.toArray()));
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t13() {
        String test = "{\"plainText\":\"ba77f948685feb4190b\",\"page\":1,\"pageNum\":1,\"size\":10,\"sizeNum\":10,\"signatureTime\":1561455889542,\"plainText\":\"ba77f948685feb4190b\"}";
        System.out.println(getStringWithoutPlainText(test));
    }


    private String getStringWithoutPlainText(String jsonStr) {
        int start = jsonStr.indexOf(TEXT);
        if (start == -1) {
            return jsonStr;
        }
        int end1 = jsonStr.indexOf(",", start);
        int end2 = jsonStr.lastIndexOf("}", start) + jsonStr.length();
        if (end1 < 0) {
            String front = jsonStr.substring(0, start - 2);
            String behind = jsonStr.substring(end2);
            return front + behind;
        } else if (end1 < end2) {
            String front = jsonStr.substring(0, start - 1);
            String behind = jsonStr.substring(end1 + 1);
            return front + behind;
        } else {
            String front = jsonStr.substring(0, start - 2);
            String behind = jsonStr.substring(end2);
            return front + behind;
        }
    }


    @Test
    public void t12() {
        String pubKey = "048F64431904049D1070F55E1187AE11A25A44B1D07A1C6173299FDA2289C2FAC8CA925CA1EAEA21E3EEA4B8CFBADB002FD089C9546619337846235456FAB54040";
        String body = "{\"availableZone\":\"\",\"pool\":\"\\r\\n\",\"userId\":\"cd7a4440-d171-495a-ab1b-26bbfb8eb5cb\"}";
        String generateKey = generateKey();

        System.out.println("SM4 秘钥：" + generateKey);
        JSONObject jsonObject = JSON.parseObject(body);
        System.out.println("SM3 加密前：" + jsonObject.toJSONString());
        String digestBySM3 = SM3Digest(body);
        System.out.println("SM3 加密：" + digestBySM3);
        jsonObject.put("plainText", digestBySM3);
        System.out.println("SM3 加密后：" + jsonObject.toJSONString());
        String encryptBySM4 = encryptBySM4(jsonObject.toJSONString(), generateKey);
        System.out.println("SM4 加密：" + encryptBySM4);
        String encryptBySM2 = encryptBySM2(generateKey, pubKey);
        System.out.println("SM2 加密：" + encryptBySM2);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put(KEY, encryptBySM4);
        jsonObject1.put(VALUE, encryptBySM2);
        System.out.println("密文: " + jsonObject1.toJSONString());
        System.out.println("----------------------------------------------------------");
        decrypt(jsonObject1.toJSONString());
    }

    private void decrypt(String param) {
        String prikey = "E591BB132C991B969CBAF8FB5938F48E628F6CC02B613078CA76BEC88A7912C1";
        JSONObject signatureText = JSON.parseObject(param);
        // 获取加密秘钥
        String paramsSignature = signatureText.getString(VALUE);
        // 获取加密报文
        String encryptParam = signatureText.getString(KEY);
        try {
            // 根据 SM2 获取 SM4 秘钥
            String hexKey = decryptBySM2(paramsSignature, prikey);
            System.out.println("SM4 秘钥：" + hexKey);
            // 根据 SM4 秘钥解密报文
            String body = decryptBySM4(encryptParam, hexKey);
            // 转换报文格式
            JSONObject jsonObject = JSON.parseObject(body);
            // 保存 SM3 校验码
            String plainText = jsonObject.getString(TEXT);
            // 检查 SM3 校验码
            if (StringUtils.isEmpty(plainText)) {
                // 无法获取校验码，失败
                System.out.println("校验码错误");
            }
            System.out.println("SM3 校验码前：" + plainText);
            // 移除报文中的 SM3 校验码字段
            jsonObject.remove(TEXT);

            String jsonString = getStringWithoutPlainText(body);
            System.out.println("原始报文    ：" + body);
            System.out.println("截取方法输出：" + jsonString);

            System.out.println("SM4 解密后的报文：" + jsonObject.toJSONString());
            // 将报文进行 SM3 加密后与校验字段比较
            String t = SM3Digest(getStringWithoutPlainText(body));
            System.out.println("SM3 校验码后：" + t);
            if (!plainText.equals(t)) {
                // 报文被修改，失败
                System.out.println("报文被修改");
            }
            // 获取报文中的时间戳
            String time = jsonObject.getString(TIME);
            if (!StringUtils.isEmpty(time)) {
                // 传输时间
                long timeDifference = System.currentTimeMillis() - Long.valueOf(time);
                // 允许传输时间
                long allowableTime = 5 * 1000;
                // 判断请求是否超时
                if (timeDifference >= allowableTime) {
                    // 请求传输时间超时，失败
                    System.out.println("请求传输时间超时");
                }
            }
            // 设置过滤成功标志
            System.out.println(jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void t11() {
        String param = "{\"key\":\"213db3bfaca3a169697027314f64ff7c256966b444be8fc2ff0a61c7768e9878dabda649593be13a6be0411438df7cba7aea060bb71790d2285723e44cd59df70bd3b1f197e8f20ad4c70bf191501ee1ebc98f339756d61863fb57c48200ab3eedbcfd30bfd9fde4078abe15e53721991e9042a89db49bc5f494fe9575fca783380bdf4a8fe6b849e4f40b8290bf8e22\",\"value\":\"04f104d54054a7213f075ac1a241c5fb46bbe3c407b71e21f49ff52e9f89d008b88dd85c123375c72c179bc4e3ce0d2061f69376a2658dda0439f803ad943332a55ab9fdf7e96cc029ec3e7ad228c1528d2eb78137e361253c7e383f510e7eae9f1b248ae3f7f9247410ac9c529d22b8bb\"}";

        decrypt(param);
    }

    @Test
    public void t10() {
        String aStr = "string";
        String bStr = "stren";
        int aLen = aStr.length();
        int bLen = bStr.length();
        int[][] dp = new int[aLen + 1][bLen + 1];
        for (int i = 0; i < aLen + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < bLen + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < aLen + 1; i++) {
            for (int j = 1; j < bLen + 1; j++) {
                if (aStr.charAt(i - 1) == bStr.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        System.out.println(dp[aLen][bLen]);
    }

    @Test
    public void t9() {
        int i = 0;
        intAdd(i);
        System.out.println(i);
    }

    private void intAdd(int i) {
        i++;
    }

    @Test
    public void t8() {
        String[] test = new String[4];
        test[0] = "a";
        test[2] = "b";
        test[3] = "c";
        List<String> strings = Arrays.asList(test);
        System.out.println(Arrays.toString(strings.toArray()));
    }

    @Test
    public void t7() {
        Set<String> strings = new HashSet<>();
        strings.add("a");
        strings.add("b");
        System.out.println(strings.contains("a"));
    }

    @Test
    public void t6() {
        Class clazz = User.class;
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.print(" " + field.getName());
        }
//        for (Method m:methods
//             ) {
//            System.out.println(m);
//        }
    }

    @Test
    public void t5() {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("a", 1);
            put("b", 2);
            put("c", 3);
            put("d", 4);
            put("e", 5);
        }};
        map.forEach((s, o) -> System.out.println(s + " -> " + o.toString()));
    }

    @Test
    public void t4() {
        Map<String, Object> exclude = new HashMap<>();
        exclude.put("sortOrder", "");
        exclude.put("pageSize", "");
        exclude.put("pageNumber", "");
        exclude.put("page", "");
        exclude.put("pageNum", "");
        exclude.put("size", "");
        exclude.put("_", "");

        String url = "{'id':46,'recordNumber':'1231245','website':[{'id':70,'websiteNumber':'1231245','websiteName':'ç\u0099¾åº¦','websiteUrl':'https://www.baidu.com','websiteContent':'ç»¼å\u0090\u0088é\u0097¨æ\u0088·','websiteLanguage':null,'contentType':'æ\u0096°é\u0097»','remark':'2018å¹´11æ\u009C\u008813æ\u0097¥ä¸\u008Aå\u008D\u00889ç\u0082¹32å\u0088\u0086','recordInfoId':46,'createTime':1542072779000,'new':false}]}";
        url = url.replace("\"", "");
        url = url.substring(1, url.length() - 1);
        String[] parameter = url.split(",");
        for (String s : parameter) {
            String[] sSplit = s.split(":");
            if (exclude.containsKey(sSplit[0])) {
                continue;
            }
            System.out.println(sSplit[0] + " : " + sSplit[1]);
        }
    }

    @Test
    public void t3() {
        String url = "http://192.168.1.79/customer/removeCustomer?id=07c35cf8-ab1e-4f03-b17d-054e0af8b12c";
        url = url.substring(20);
        String[] impl = url.split("/");
        String[] parameter_t = impl[impl.length - 1].split("\\?");
        StringBuilder implement = new StringBuilder();
        for (int i = 0; i < impl.length - 1; i++) {
            implement.append("/").append(impl[i]);
        }
        implement.append("/").append(parameter_t[0]);
        System.out.println(implement.toString());
        String[] parameter = parameter_t[1].split("&");
        Map<String, Object> exclude = new HashMap<>();
        exclude.put("sortOrder", "");
        exclude.put("pageSize", "");
        exclude.put("pageNumber", "");
        exclude.put("page", "");
        exclude.put("pageNum", "");
        exclude.put("size", "");
        exclude.put("_", "");
        for (String s : parameter) {
            String[] sSplit = s.split("=");
            if (exclude.containsKey(sSplit[0])) {
                continue;
            }
            if (sSplit.length < 2) {
                System.out.println(sSplit[0] + " = ");
            } else {
                System.out.println(sSplit[0] + " = " + sSplit[1]);
            }
        }
    }

    @Test
    public void t2() {
        System.out.println(calculatedUsage(10901, 48));
    }

    @Test
    public void contextLoads() {
//        test(User::setRate, new User(), 0.144d);
//        t2(User::getRate, new User().setRate(0.122d));
        List<User> users = new ArrayList<>();
        tList(users, 0.111d);
        System.out.println(users.get(0).toString());
    }

    private void test(IZhuRU<Double> iZhuRU, User u1, Double u2) {
        iZhuRU.setDouble(u1, u2);
    }

    private Object t2(IObjectGet iObjectGet, User u1) {
        System.out.println(iObjectGet.GetO(u1).getClass());
//        tDouble(iObjectGet.GetO(u1));
        return iObjectGet.GetO(u1);
    }

    private Double calculatedUsage(int total, int used) {
        Double usedRate = used / (double) total;
        return Double.parseDouble(String.format("%.3f", usedRate));
    }

    private void tDouble(double d) {
        System.out.println(d);
    }

    private void tList(List<User> userList, double d) {
        userList.add(new User().setRate(d));
    }
}
