package com.xmut303.utils;

import com.aliyun.com.viapi.FileUtils;
import com.aliyun.facebody20191230.models.AddFaceEntityResponse;
import com.aliyun.facebody20191230.models.AddFaceResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;

public class AddWarningFace {
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

    public static void AddEntityAndFace(String Entity_id,String url,String name) throws Exception {

        addFaceEntity(Entity_id);
        addFace(url,Entity_id,name);


    }

    public static void addFaceEntity(String Entity_id) throws Exception {
        //添加人脸样本
        com.aliyun.facebody20191230.Client client = AddWarningFace.createClient("LTAI5tP1dGq9U6fCUaihBJgL", "cz4BkYKz1p6AsXyTHDhgBXiBjs2xP3");
        com.aliyun.facebody20191230.models.AddFaceEntityRequest addFaceEntityRequest = new com.aliyun.facebody20191230.models.AddFaceEntityRequest()
                .setDbName("default")
                .setEntityId(Entity_id);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            AddFaceEntityResponse addFaceEntityResponse = client.addFaceEntityWithOptions(addFaceEntityRequest, runtime);
            // 获取整体结果
            System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(addFaceEntityResponse)));
            // 获取单个字段
//            System.out.println(addFaceResponse.getBody().getData().getFaceId());
        } catch (TeaException teaException) {
            // 获取整体报错信息
            System.out.println(com.aliyun.teautil.Common.toJSONString(teaException));
            // 获取单个字段
            System.out.println(teaException.getCode());
        }
    }

    /**
     *
     * @param url       图片地址
     * @param Entity_id 身份证号码
     * @param name
     * @throws Exception
     */
    public static void addFace(String url,String Entity_id,String name) throws Exception {
        FileUtils fileUtils = FileUtils.getInstance("LTAI5tP1dGq9U6fCUaihBJgL", "cz4BkYKz1p6AsXyTHDhgBXiBjs2xP3");
        String ossUrl = fileUtils.upload(url);
        com.aliyun.facebody20191230.Client client = AddWarningFace.createClient("LTAI5tP1dGq9U6fCUaihBJgL", "cz4BkYKz1p6AsXyTHDhgBXiBjs2xP3");
        com.aliyun.facebody20191230.models.AddFaceRequest addFaceRequest = new com.aliyun.facebody20191230.models.AddFaceRequest()
                .setDbName("default")
                .setImageUrl(ossUrl)
                .setEntityId(Entity_id)
                .setExtraData(name);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            AddFaceResponse addFaceResponse = client.addFaceWithOptions(addFaceRequest, runtime);
            // 获取整体结果
            System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(addFaceResponse)));
            // 获取单个字段
            System.out.println(addFaceResponse.getBody().getData().getFaceId());
        } catch (TeaException teaException) {
            // 获取整体报错信息
//            System.out.println(com.aliyun.teautil.Common.toJSONString(teaException));
            // 获取单个字段
            System.out.println(teaException.getCode());
        }
    }

}
