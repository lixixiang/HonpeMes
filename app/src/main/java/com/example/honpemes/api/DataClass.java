package com.example.honpemes.api;


import android.os.Bundle;

import com.example.honpemes.MyApplication;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.OrderItemFragment;
import com.example.honpemes.fragment.a.menu.fragment.position1.item3.ChangeOrderFragment;
import com.example.honpemes.fragment.a.menu.fragment.position10.item5.BusinessMangerOrderFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.OrderFormFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item2.MeterManagerFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.KeepPlanMainFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.DeviceStatusHomeFragment;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.FileManagerFragment;
import com.example.honpemes.fragment.a.menu.fragment.position9.item2.CustomerComplaintFragment;
import com.example.honpemes.fragment.a.menu.fragment.position9.item4.EmployeeDataFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * FileName: DataClass
 * Author: asus
 * Date: 2021/11/26 9:14
 * Description:
 */
public class DataClass {
    public static String service = "技加大师尊重并保护所有使用服务用户的个人隐私权。为了给您提供更准确、更有个性化的服务，技加大师会按照本隐私权政策的规定使用和披露您的个人信息。但技加大师将以高度的勤勉、审慎义务对待这些信息。除本隐私权政策另有规定外，在未征得您事先许可的情况下，技加大师不会将这些信息对外披露或向第三方提供。技加大师会不时更新本隐私权政策。在您同意技加大师服务使用协议之时，即视为您已经同意本隐私权政策全部内容。本隐私权政策属于技加大师服务使用协议不可分割的一部分。\n" +
            "\n" +
            "\n" +
            "1. 适用范围\n" +
            "(a) 在您注册技加大师帐号时，您根据技加大师要求提供的个人或者企业注册信息；\n" +
            "(b) 在您使用技加大师网络服务，访问技加大师平台网页时，技加大师自动接收并记录的您的浏览器和计算机上的信息，包括但不限于您的IP地址、浏览器的类型、使用的语言、访问日期和时间、软硬件特征信息及您需求的网页记录等数据；\n" +
            "(c) 技加大师通过合法途径从商业伙伴处取得的用户个人数据。\n" +
            "\n" +
            "您了解并同意，以下信息不适用本隐私权政策：\n" +
            "(a) 您在使用技加大师平台提供的搜索服务时输入的关键字信息；\n" +
            "(b) 技加大师收集到您在技加大师发布的有关信息数据，包括但不限于参与活动、成交信息及评价详情；\n" +
            "(c) 违反法律规定或违反技加大师规则行为及技加大师已对您采取的措施。\n" +
            "2. 信息使用\n" +
            "(a) 技加大师不会向任何无关第三方提供、出售、出租、分享或交易您的个人信息，除非事先得到您的许可，或该第三方和技加大师（含技加大师关联公司）单独或共同为您提供服务，且在该服务结束后，其将被禁止访问包括其以前能够访问的所有这些资料；\n" +
            "(b) 技加大师亦不允许任何第三方以任何手段收集、编辑、出售或者无偿传播您的个人信息，任何技加大师平台用户如从事上述活动，一经发现技加大师有权立即终止与该用户的服务协议；\n" +
            "(c) 为服务用户的目的，技加大师可能通过使用您的个人信息，向您提供您感兴趣的信息，包括但不限于向您发出产品和服务信息，或者与技加大师合作伙伴共享信息以便他们向您发送有关其产品和服务的信息（后者需要您的事先同意）。\n" +
            "\n" +
            "3. 信息披露\n" +
            "在如下情况下，技加大师将依据您的个人意愿或法律的规定全部或部分的披露您的个人信息：\n" +
            "(a) 经您事先同意，向第三方披露；\n" +
            "(b) 为提供您所要求的产品和服务，而必须和第三方分享您的个人信息；\n" +
            "(c) 根据法律的有关规定，或者行政或司法机构的要求，向第三方或者行政、司法机构披露；\n" +
            "(d) 如您出现违反中国有关法律、法规或者技加大师服务协议或相关规则的情况，需要向第三方披露；\n" +
            "(e) 如您是适格的知识产权投诉人并已提起投诉，应被投诉人要求，向被投诉人披露，以便双方处理可能的权利纠纷；\n" +
            "(f) 在技加大师平台上创建的某一交易中，如交易任何一方履行或部分履行了交易义务并提出信息披露请求的，技加大师有权决定向该用户提供其交易对方的联络方式等必要信息，以促成交易的完成或纠纷的解决；\n" +
            "(g) 其它技加大师根据法律、法规或者网站政策认为合适的披露。\n" +
            "\n" +
            "4. 信息存储和交换\n" +
            "技加大师收集的有关您的信息和资料将保存在技加大师及（或）其关联公司的服务器上，这些信息和资料可能传送至您所在国家、地区或技加大师收集信息和资料所在地的境外并在境外被访问、存储和展示。\n" +
            "\n" +
            "5. Cookie的使用\n" +
            "(a) 在您未拒绝接受cookies的情况下，技加大师会在您的计算机上设定或取用cookies ，以便您能登录或使用依赖于cookies的技加大师平台服务或功能，技加大师使用cookies可为您提供更加周到的个性化服务，包括推广服务；\n" +
            "(b) 您有权选择接受或拒绝接受cookies，您可以通过修改浏览器设置的方式拒绝接受cookies，但如果您选择拒绝接受cookies，则您可能无法登录或使用依赖于cookies的技加大师网络服务或功能；\n" +
            "(c) 通过技加大师所设cookies所取得的有关信息，将适用本政策。\n" +
            "\n" +
            "6. 信息安全\n" +
            "(a) 技加大师帐号均有安全保护功能，请妥善保管您的用户名及密码信息，技加大师将通过对用户密码进行加密等安全措施确保您的信息不丢失，不被滥用和变造，尽管有前述安全措施，但同时也请您注意在信息网络上不存在“完善的安全措施”；\n" +
            "(b) 在使用技加大师网络服务进行网上交易时，您不可避免的要向交易对方或潜在的交易对方披露自己的个人信息，如联络方式或者邮政地址。请您妥善保护自己的个人信息，仅在必要的情形下向他人提供。如您发现自己的个人信息泄密，尤其是技加大师用户名及密码发生泄露，请您立即联络技加大师客服，以便技加大师采取相应措施。\n" +
            "\n";

    public static String users = "本用户协议（以下简称“本协议”）适用于红品晶英科技有限公司运营和管理（以下简称“我们”，“本公司”）旗下的大部分产品与服务（以下简称“本产品”范围详见附录1），如果某项产品与服务有单独的服务规则，则该产品的服务规则优先适用。本协议是您（个人或单一实体）与本公司之间就使用本产品达成的具有法律约束力的法律协议。该法律协议包括但不限于本页面的全部条款、第8条的《隐私政策》以及我们针对特定产品部分功能单独发布的政策或服务规则等（若有）。\n" +
            "\n" +
            "请您在使用本产品之前仔细阅读下列条款。您下载、安装或使用产本品或者单击“ 我同意”表明您已经阅读本协议并充分理解、遵守本协议所有条款，包括涉及免除或者限制本公司责任的免责条款、用户权利限制条款、约定争议解决方式等，这些条款均用粗体字标注。如果您不同意本协议的全部或部分内容，请不要下载、安装和使用本产品。\n" +
            "\n" +
            "1. 权利声明\n" +
            "\n" +
            "1.1 知识产权。\n" +
            "\n" +
            "本公司拥有“本产品”的所有权和知识产权等全部权利。本产品受中国及其他国家的知识产权法、国际知识产权公约（包括但不限于著作权法、商标法、专利法等）的保护。所有未授予您的权利均被本公司保留，您不可以从本产品上移除本公司的版权标记或其他权利声明。\n" +
            "\n" +
            "1.2 软件所有权保留。\n" +
            "\n" +
            "您确定不享有本软件的所有权，本软件未被出售给用户，本公司保留本软件的所有权。\n" +
            "\n" +
            "2. 授权许可\n" +
            "\n" +
            "2.1 授权许可。\n" +
            "\n" +
            "本公司授予您一项非排他的、不可转让的、非商业性的、可撤销的许可，以下载、安装、备份和使用本产品。本公司授予您仅出于个人非商业目在移动设备上使用本产品，如果您希望将本产品用于其他非本公司授权的目的或其他商业目的，您必须另行取得本公司的单独书面许可。\n" +
            "\n" +
            "2.2 限制.\n" +
            "\n" +
            "除非就某些第三方软件软件有明文规定或适用法律允许，否则您不得在未取得本公司书面许可的情况下修改、翻译、反向汇编、反向工程、反编译本软件的部分或全部。\n" +
            "\n" +
            "3. 用户行为\n" +
            "\n" +
            "3.1 如果您在使用本公司产品或服务的过程中发布相关用户内容，您需要对自己发布的所有用户内容负责。用户内容是指您发布或以其他方式使用本产品时产生的所有内容（例如：您的信息、声音、图片或其他内容）。您是您的用户内容唯一的责任人，您将承担因您的用户内容违法法律、侵犯第三方权益的所有法律责任。\n" +
            "\n" +
            "3.2 在使用过程中，您将承担因下述行为而产生的全部法律责任，本公司不对您的下述行为承担任何责任：\n" +
            "\n" +
            "破坏宪法所确定的基本原则的；\n" +
            "\n" +
            "危害国家安全、泄露国家秘密、颠覆国家政权、破坏国家统一的；\n" +
            "\n" +
            "损害国家荣誉和利益的；\n" +
            "\n" +
            "煽动民族仇恨、民族歧视，破坏民族团结的；\n" +
            "\n" +
            "破坏国家宗教政策，宣扬邪教和封建迷信的；\n" +
            "\n" +
            "散布谣言，扰乱社会秩序，破坏社会稳定的；\n" +
            "\n" +
            "散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；\n" +
            "\n" +
            "侮辱或者诽谤他人，侵害他人合法权益的；\n" +
            "\n" +
            "含有法律、行政法规禁止的其他内容的。\n" +
            "\n" +
            "3.3 您同意不通过本产品从事下列行为：\n" +
            "\n" +
            "发布或分享电脑病毒、蠕虫、恶意代码、故意破坏或改变计算机系统或数据的软件；\n" +
            "\n" +
            "未经授权，收集其他用户的信息或数据，例如非法收集第三人的个人信息侵犯第三人隐私或其他合法民事权益；\n" +
            "\n" +
            "用自动化的方式恶意使用本产品，给服务器造成过度的负担或以其他方式干扰或损害本产品服务器和网络链接；\n" +
            "\n" +
            "在未授权的情况下，尝试访问本产品的服务器数据或通信数据；\n" +
            "\n" +
            "干扰、破坏本产品其他用户的使用；\n" +
            "\n" +
            "未经本公司授权，修改、破解、反编译、反汇编、逆向工程本产品，发布本产品的修改版、破解版等；\n" +
            "\n" +
            "3.4 费用。\n" +
            "\n" +
            "我们的大部产品或服务都是是免费的，包括但不限于个人上网或第三方（包括但不限于电信或移动通讯提供商）收取的通讯费、信息费等相关费用。\n" +
            "\n" +
            "4. 功能的调整、改进与升级\n" +
            "\n" +
            "我们可能对产品进行不时地调整、改进和增减，甚至下线我们部分产品，以不断适应我们的运营需要。任何本产品的更新版本或未来版本或者其他变更同样受到本协议约束。\n" +
            "\n" +
            "5. 无担保声明\n" +
            "\n" +
            "5.1 本公司在发布本产品之前，已尽可能对产品进行了详尽的技术测试和功能测试，但鉴于电子设备、操作系统、网络环境的复杂性，本公司不能保证本产品会兼容所有用户的电子设备，也无法保证用户在使用本产品过程中能够持续不出现任何技术故障。\n" +
            "\n" +
            "5.2 在法律允许的最大限度内，本公司无法对产品或服务做任何明示、暗示和强制的担保，包括但不限于软件的兼容性；产品一定满足您的需求或期望；或产品将不间断的、及时的、安全的、或无错误的运行。\n" +
            "\n" +
            "5.3  由于网络环境的自由与开放特征，我们的产品或服可能会被第三方擅自修改、破解发布于互联网，建议用户从本公司的官方应用渠道，如官网、本公司已申请认证的第三方应用商店下载、安装我们的产品，我们不会对任何非官方版本承担任何责任。\n" +
            "\n" +
            "6. 赔偿\n" +
            "\n" +
            "6.1 赔偿。在你违反本协议或你所提供的信息侵犯第三方合法权益而导致直接或间接损失的情况下，你应当赔偿其他用户、本公司、第三方合作伙伴的所有损失、费用或支出。\n" +
            "\n" +
            "6.2 赔偿程序。可以通知你及时要求赔偿。然而,本公司未能通知不会减轻你的赔偿义务，除了在某种程度上，未能及时通知你给你造成了实质上的损害。\n" +
            "\n" +
            "6.3 额外的责任。你的赔偿义务不是本公司的唯一补救措施，除此之外可能本公司对你依据本协议采取其他补救措施，你的赔偿义务在本协议终止后仍存在。\n" +
            "\n" +
            "7. 不可抗力与责任限制\n" +
            "\n" +
            "7.1 不可抗力：本协议有效期间，如若遭受不可抗力事件，任何一方可暂行中止履行本协议项下的义务直至不可抗力事件的影响消除，并且遭受方无需为此承担违约责任，但应及时将不可抗力事件及时通知对方，并尽最大努力克服该事件，减少损失的扩大。不可抗力指各方不能控制、不可预见或即使预见亦无法避免的事件且该事件足以妨碍、影响或延误任何一方根据本协议履行其全部或部分义务。该事件包括但不限于自然灾害、战争、法律法规变更、政府命令、计算机病毒、黑客攻击或基础电信运营商服务中断等。\n" +
            "\n" +
            "7.2 损害限制：本公司及其分支，和所属的管理人员、董事、合伙人、雇员、承包商给你造成的所有损害赔偿额度均仅限于你使用产品支付的款额。你放弃对特殊、间接、附带或间接损害要求赔偿的权利，包括并不限于利润损失、收入、使用、或数据和应用的损失，即使本公司知道此类损失的可能性。\n" +
            "\n" +
            " \n" +
            "\n" +
            "8. 个人信息保护\n" +
            "\n" +
            "保护用户跟个人信息安全、维护用户隐私是我们一贯的理念，并且我们贯穿于所有产品或服务的立项、开发和运营过程。为不断优化用户体验，向用户提供更加个性化、智能化的内容与服务，我们会收集您的个人信息与非个人信息。您在使用特定的产品时，可以查看关于该产品的隐私说明以及我们的《隐私政策》，了解关于我们收集信息的内容、使用目的以及如何保护你的信息安全，该《产品隐私说明》和《隐私政策》均构成本协议的一部分。\n" +
            "\n" +
            "9. 其他\n" +
            "\n" +
            "9.1 本协议的修改。\n" +
            "\n" +
            "由于业务的拓展、调整或法规变化等原因，本公司可能会适时修改本协议至被法律所允许的程度。如果调整会对您的权利与义务造成重大影响，我们会尽可能通过电子邮件、应用内通知等方式告知您。我们建议您定期访查看我们的网站和移动应用程序，关注本协议的任何变化。在本协议修改后您继续使用本产品代表您接受修改后的协议内容。\n" +
            "\n" +
            "9.2 适用法律和管辖法院。\n" +
            "\n" +
            "执行本协议和所有程序引起的纠纷适用法律为中华人民共和国法律、解释。由本协议引起的所有纠纷由我们附属公司的住所地法院管辖。" +
            "\n";

    public final static String DAY_THEME = "白天主题";
    public final static String NIGHT_THEME = "夜晚主题";
    //    private int[] colors = {R.color.blue_l_l,R.color.blue_alpha_l_l_l_l};

    public static final int[] COLORS = {
            MyApplication.getContext().getResources().getColor(R.color.blue_l),
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),
            MyApplication.getContext().getResources().getColor(R.color.green_l),
    };
    public static final int[] COLORS4 = {
            MyApplication.getContext().getResources().getColor(R.color.green_l),
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),
            MyApplication.getContext().getResources().getColor(R.color.red_l),
    };

    public static final int[] COLORS1 = {
            MyApplication.getContext().getResources().getColor(R.color.green_l),
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),
            MyApplication.getContext().getResources().getColor(R.color.red_l),
            MyApplication.getContext().getResources().getColor(R.color.grey_l),
    };
    public static final int[] COLORS2 = {
            MyApplication.getContext().getResources().getColor(R.color.black_l),
            MyApplication.getContext().getResources().getColor(R.color.green_l),
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),
            MyApplication.getContext().getResources().getColor(R.color.red_l),
            MyApplication.getContext().getResources().getColor(R.color.grey_l),
    };

    public static final int[] COLORS3 = {
            MyApplication.getContext().getResources().getColor(R.color.blue_l),  //机壳色
            MyApplication.getContext().getResources().getColor(R.color.green_l), //汽车色
            MyApplication.getContext().getResources().getColor(R.color.red_l),  //数码色
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),//五金色

            MyApplication.getContext().getResources().getColor(R.color.blue_l),
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),

            MyApplication.getContext().getResources().getColor(R.color.blue_l),
            MyApplication.getContext().getResources().getColor(R.color.grey_l), //模具色
            MyApplication.getContext().getResources().getColor(R.color.red_l),
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),
    };

    public static final int[] COLORS7 = {
            MyApplication.getContext().getResources().getColor(R.color.blue_l),  //机壳色
            MyApplication.getContext().getResources().getColor(R.color.green_l), //汽车色
            MyApplication.getContext().getResources().getColor(R.color.red_l),  //数码色
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),//五金色

            MyApplication.getContext().getResources().getColor(R.color.grey_l),
            MyApplication.getContext().getResources().getColor(R.color.red_l_l),

            MyApplication.getContext().getResources().getColor(R.color.orange_l_l_l),
            MyApplication.getContext().getResources().getColor(R.color.green_alpha), //模具色
            MyApplication.getContext().getResources().getColor(R.color.zhi),
            MyApplication.getContext().getResources().getColor(R.color.qing),
    };

    public static final int[] COLORS_RUN_1 = {
            MyApplication.getContext().getResources().getColor(R.color.green_l),
            MyApplication.getContext().getResources().getColor(R.color.green_alpha),

    };
    public static final int[] COLORS_RUN_2 = {
            MyApplication.getContext().getResources().getColor(R.color.yellow_l),
            MyApplication.getContext().getResources().getColor(R.color.yellow_alpha_l),
    };
    public static final int[] COLORS_RUN_3 = {
            MyApplication.getContext().getResources().getColor(R.color.red_l),
            MyApplication.getContext().getResources().getColor(R.color.red_alpha_l),
    };
    public static final int[] COLORS_RUN_4 = {
            MyApplication.getContext().getResources().getColor(R.color.grey_l),
            MyApplication.getContext().getResources().getColor(R.color.grey_alpha1),
    };

    public static final int[] testStart = {3,2,6, 2,1,
            4, 5,3,4,10,3,2,6, 2,1,
            4, 5,3,4,10,3,2,6, 2,1,
            4, 5,3,4,10,3,2,6, 2,1,
            4, 5,3,4,10};

    public static final int[] testRun = {7,9,3, 16,24,
            41, 25,23,4,10,7,9,3, 16,24,
            41, 25,23,4,10,7,9,3, 16,24,
            41, 25,23,4,10,7,9,3, 16,24,
            41, 25,23,4,10};

    public static final int[] testStop = {0,1,2, 4,15,
            2, 0,7,8,9,0,1,2, 4,15,
            2, 0,7,8,9,0,1,2, 4,15,
            2, 0,7,8,9,0,1,2, 4,15,
            2, 0,7,8,9};

    public static final int[] testClose = {1,1,1, 1,1,
            1, 1,1,1,1,1,1,1, 1,1,
            1, 1,1,1,1,1,1,1, 1,1,
            1, 1,1,1,1,1,1,1, 1,1,
            1, 1,1,1,1};

    public static final String[] MS = {"00:00,10:00","20:00","30:00","40:00","50:00","59:00"};

    public static String[] editTitles = {"产品名称", "生产单号", "下单人员"};
    public static String[] editTitles2 = {"生产单号",  "下单人员","下单时间","交货日期","制作组别", "订单状态"};
    public static String[] btnTitles = {"订单状态", "订单组别", "制作组别"};
    public static String[] orderStatus = {"未提交", "已提交", "已审核", "已暂停", "已出货", "异常"};
    public static String[] orderTeams = {"国内业务部", "日本业务部", "国际业务部", "其他"};
    public static String[] madeTeams = {"五金组", "数码组", "机壳组", "复模组",
            "模具组", "研发组", "汽车事业部", "国内五金组",
            "日本五金组", "国内机壳组", "国际机壳组", "日本机壳组"
            , "国内数码组", "国际数码组", "国际五金组"};
    public static String[] strHeads = {"日期", "运行", "暂停", "报警", "关机"};


    public static final void startFragment(String title, SupportFragment fragment, Bundle bundle){
        switch (title) {
            case "待审核订单":
                fragment.start(OrderItemFragment.newInstance(bundle));
                break;
            case "保养":
                fragment.start(KeepPlanMainFragment.newInstance(bundle));
                break;
            case "订单变更":
                fragment.start(ChangeOrderFragment.newInstance(bundle));
                break;
            case "设备状态":
                fragment.start(DeviceStatusHomeFragment.newInstance(bundle));
                break;
            case "文档":
                fragment.start(FileManagerFragment.newInstance(bundle));
                break;
            case "客供资料":
                fragment.start(CustomerComplaintFragment.newInstance(bundle));
                break;
            case "员工资料":
                fragment.start(EmployeeDataFragment.newInstance(bundle));
                break;
            case "订单看板":
                fragment.start(OrderFormFragment.newInstance(bundle));
                break;
            case "电表管理":
                fragment.start(MeterManagerFragment.newInstance(bundle));
                break;
            case "订单":
                fragment.start(BusinessMangerOrderFragment.newInstance(bundle));
                break;
        }
    }
}































