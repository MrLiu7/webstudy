package com.util;

import com.Log.Log;
import com.dao.impl.UserDaoImpl;
import com.domain.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AddUserByComputer {
    private final static UserDaoImpl dao = new UserDaoImpl();

    public static void main(String[] args) {
        Random random = new Random();
        // 存储姓氏
        String surname = "赵钱孙李周吴郑王冯陈楮卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方" +
                "俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅" +
                "庞熊纪舒屈项祝董梁杜阮蓝闽席季麻强贾路娄危江童颜郭梅盛林刁锺徐丘骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单" +
                "杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄麹家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘" +
                "斜厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍郤璩桑桂" +
                "濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庾终暨居衡步都耿满弘匡国文寇广禄阙东欧殳沃利蔚越夔隆师巩厍" +
                "聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逑盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台" +
                "公冶宗政濮阳淳于单于太叔申屠公孙仲孙轩辕令狐锺离宇文长孙慕容鲜于闾丘司徒司空丌官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓" +
                "拔夹谷宰父谷梁晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟";
        int surnameLen = surname.length();

        // 处理名字
        String names = "飞航、晓博、文博、宇文、辰锟、智晖、宜春、和雅、明杰、华采、兴安、文柏、君之、晗日、懿轩、睿德、昊嘉、宏伟、嘉慕、元德、" +
                "康德、阳泽、昆颉、良骏、理群、佑运、永元、高懿、良策、英华、周成、嘉纳、曦之、兴旺、飞翮、玉轩、俊楚、鹏池、朋兴、意智、阳舒、" +
                "运盛、勇男、心水、文山、嘉许、涵润、敏学、建同、伟茂、泰然、建安、铭晨、涵涤、骞北、璞瑜、康泰、光熙、建义、凯泽、阳伯、锐逸、" +
                "宏邈、良工、浦和、智刚、哲茂、鹏运、雅昶、经国、海阳、嘉茂、德义、飞沉、英耀、宏远、弘量、和悦、鸿文、烨磊、和宜、鑫靖、治遥、" +
                "飞浅、鉴合、恭满、刊何、潜泉、里博、高谦、风商、健奉、苑棕、祥孝、云哲、治致、唯弘、磊城、柏业、烈欧、杰冰、羽龙、轩健、龙霖、" +
                "钟钧、年奋、忆飘、峰理、雷腾、铁卫、绩荣、乐攀、余秋、琢新、临诗、耀闲、浪天、欢琪、宸幽、聚均、挚宏、棋腾、永庆、敬深、陌宏、" +
                "运渊、闲煜、毅煜、浚明、生栋、妙全、疏奇、物观、常攀、桐戈、千傲、峰复、睿帅、淡泉、闲翔、云古、秉哲、境翱、朋材、信熙、勉丰、" +
                "散蓝、浦文、卡神、谨壤、星曲、量叶、彬净、振田、安征、良年、飘吉、韬胜、池润、章鸽、俊杰";
        // 获取名的集合
        List<String> nameList = new ArrayList<>(Arrays.asList(names.split("、")));
        int nameLen = nameList.size();

        // 处理省份
        String province = "河北省、山西省、辽宁省、吉林省、黑龙江省、江苏省、浙江省、安徽省、福建省、江西省、山东省、河南省、湖北省、湖南省、" +
                "广东省、海南省、四川省、贵州省、云南省、陕西省、甘肃省、青海省、台湾省、内蒙古自治区、广西壮族自治区、西藏自治区、" +
                "宁夏回族自治区、新疆维吾尔自治区、北京市、天津市、上海市、重庆市、香港特别行政区、澳门特别行政区";
        // 获取省的集合
        List<String> provinceList = new ArrayList<>(Arrays.asList(province.split("、")));
        int provinceLen = provinceList.size();

        // 处理性别
        String[] sex = {"男", "女"};

        // 向服务器添加指定条数据
        for (int i = 0; i < 100000; i++) {
            String userName = surname.charAt(random.nextInt(surnameLen)) + nameList.get(random.nextInt(nameLen));
            String qq = getQq();
            String email = qq + "@163.com";
            User user = new User(0, userName, getPassword(15), sex[random.nextInt(2)], random.nextInt(100),
                    provinceList.get(random.nextInt(provinceLen-6)), getQq(), email);
            dao.addUser(user);
            System.out.println(user);
        }
    }

    /**
     * 处理密码
     *
     * @param length
     * @return
     */
    private static String getPassword(int length) {
        StringBuilder sb = new StringBuilder();
        String codeFather = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(codeFather.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }


    private static String getQq() {
        StringBuilder sb = new StringBuilder();
        String codeFather = "1234567890";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(codeFather.charAt(random.nextInt(10)));
        }
        return sb.toString();
    }
}
