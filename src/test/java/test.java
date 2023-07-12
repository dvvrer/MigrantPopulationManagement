import com.xmut303.pojo.FaceItem;
import com.xmut303.utils.FaceInfo;

import java.text.DecimalFormat;

public class test {
    public static void main(String[] args) throws Exception {

        FaceInfo faceInfo = new FaceInfo();
        FaceItem faceItem = faceInfo.Sample("src/main/java/com/xmut303/img/img.png");
        System.out.println("身份证号："+faceItem.getEntityId());
        System.out.println("相似度："+new DecimalFormat("###,##0.00").format(faceItem.getScore().movePointRight(2))+"%");
        System.out.println("可信度："+new DecimalFormat("###,##0.00").format(faceItem.getConfidence()));

    }
}
