package com.yingluo.Appraiser.share;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class ShareView {

	public static void Share(int param) {
	switch (param) {
    case 1:
        // 微信分享
        Platform.ShareParams wechat = new Platform.ShareParams();
//        wechat.setTitle(ss.getSharetitle());
//        wechat.setImageUrl(ss.getShareimageUrl());
//        wechat.setUrl(UrlConst.SHARE + topicId);
//        wechat.setShareType(Platform.SHARE_WEBPAGE);
//        wechat.setImageData(small(showBitmap));

        Platform weixin = ShareSDK.getPlatform(Wechat.NAME);
//        weixin.setPlatformActionListener(this);
        weixin.share(wechat);
        break;
    case 2:
        // 朋友圈分享
        Platform.ShareParams wechatMoments = new Platform.ShareParams();
//        wechatMoments.setTitle(ss.getSharetitle());
//        wechatMoments.setImageUrl(ss.getShareimageUrl());
//        wechatMoments.setUrl(UrlConst.SHARE + topicId);
//        wechatMoments.setShareType(Platform.SHARE_WEBPAGE);
//        wechatMoments.setImageData(small(showBitmap));

        Platform pyq = ShareSDK.getPlatform(WechatMoments.NAME);
//        pyq.setPlatformActionListener(this);
        pyq.share(wechatMoments);
        break;
    case 3:
        //qq分享
        Platform.ShareParams sp1 = new Platform.ShareParams();
//        sp1.setTitle(ss.getSharetitle());
//        sp1.setTitleUrl(UrlConst.SHARE + topicId);
//        sp1.setImageUrl(ss.getShareimageUrl());
//        sp1.setSite(null);
//        sp1.setSiteUrl(null);
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
//        qq.setPlatformActionListener(this);
        qq.share(sp1);
        break;
    case 4:
        //qq空间分享
        Platform.ShareParams sp = new Platform.ShareParams();
//        sp.setTitle(ss.getSharetitle());
//        sp.setTitleUrl(UrlConst.SHARE + topicId); // 标题的超链接
//        sp.setImageUrl(ss.getShareimageUrl());
//        sp.setSite(null);
//        sp.setSiteUrl(null);
        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
//        qzone.setPlatformActionListener(this);
        qzone.share(sp);
        break;
    case 5:
        //微博
        Platform.ShareParams sp2 = new Platform.ShareParams();
//        sp2.setText(ss.getSharetitle() + UrlConst.SHARE + topicId + " - 来自6666图片新闻");
//        sp2.setImageUrl(ss.getShareimageUrl());
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//        weibo.setPlatformActionListener(this);
        weibo.share(sp2);
        break;
	}
	}

}
