package com.xmut303.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.com.viapi.FileUtils;
import com.xmut303.pojo.FaceItem;

import java.math.BigDecimal;

public class FaceInfo {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.facebody20191230.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "facebody.cn-shanghai.aliyuncs.com";
        return new com.aliyun.facebody20191230.Client(config);
    }

    /**
    * 使用STS鉴权方式初始化账号Client，推荐此方式。本示例默认使用AK&SK方式。
    * @param accessKeyId
    * @param accessKeySecret
    * @param securityToken
    * @return Client
    * @throws Exception
    */
    public static com.aliyun.facebody20191230.Client createClientWithSTS(String accessKeyId, String accessKeySecret, String securityToken) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret)
                // 必填，您的 Security Token
                .setSecurityToken(securityToken)
                // 必填，表明使用 STS 方式
                .setType("sts");
        // 访问的域名
        config.endpoint = "facebody.cn-shanghai.aliyuncs.com";
        return new com.aliyun.facebody20191230.Client(config);
    }

    public FaceItem Sample(String url) throws Exception{
        String file = url;
        FileUtils fileUtils = FileUtils.getInstance("LTAI5tP1dGq9U6fCUaihBJgL", "cz4BkYKz1p6AsXyTHDhgBXiBjs2xP3");
        String ossUrl = fileUtils.upload(file);
        com.aliyun.facebody20191230.Client client = FaceInfo.createClient("LTAI5tP1dGq9U6fCUaihBJgL", "cz4BkYKz1p6AsXyTHDhgBXiBjs2xP3");
        com.aliyun.facebody20191230.models.SearchFaceRequest searchFaceRequest = new com.aliyun.facebody20191230.models.SearchFaceRequest()
                .setDbName("default")
                .setImageUrl(ossUrl)
                .setLimit(1);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.facebody20191230.models.SearchFaceResponse resp = client.searchFaceWithOptions(searchFaceRequest, runtime);
        JSONObject object = JSONObject.parseObject(com.aliyun.teautil.Common.toJSONString(resp));

        FaceItem faceIteminfo = new FaceItem();

        JSONObject faceItems = (JSONObject) ((JSONArray) ((JSONObject) ((JSONArray) ((JSONObject) ((JSONObject) object.get("body")).get("data")).get("matchList")).get(0)).get("faceItems")).get(0);

        faceIteminfo.setFaceId((String) faceItems.get("faceId"));
        faceIteminfo.setConfidence((BigDecimal) faceItems.get("confidence"));
//        faceIteminfo.setDbName((String) faceItems.get("dbName"));
        faceIteminfo.setEntityId((String) faceItems.get("entityId"));
        faceIteminfo.setScore((BigDecimal) faceItems.get("score"));

        return faceIteminfo;
    }
}
