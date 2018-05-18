package com.example.lenovo.firevideo.widgets;

import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;

//视频窗口管理对象

public interface RTMPCViewHelper {

    /**
     * VideoView 显示属性。
     * RendererCommon.ScalingType.SCALE_ASPECT_FIT（适应屏幕大小填充）,
     * RendererCommon.ScalingType.SCALE_ASPECT_FILL（根据图像大小填充）,
     * RendererCommon.ScalingType.SCALE_ASPECT_BALANCED（平衡FIT和FILL）
     *
     * @param scalingType RendererCommon.ScalingType 枚举下的有一个属性
     * @return
     */
    //添加本地窗口渲染器
    //返回值：VideoRenderer render
    //说明：该方法返回一个本地视频渲染对象 VideoRenderer，此时应调用会议对象中的设置本地视频采集窗口方法。
    //setLocalVideoCapturer(render.GetRenderPointer(),true)
    public VideoRenderer OnRtcOpenLocalRender(RendererCommon.ScalingType scalingType);

    /**
     * Close main  Renderer
     */
    //移除本地视频渲染器
    //说明：调用此方法将会移除视频的渲染，将看不到视频。
    //RTC服务不会关闭
    public void OnRtcRemoveLocalRender();

    /**
     * VideoView 显示属性。
     * RendererCommon.ScalingType.SCALE_ASPECT_FIT（适应屏幕大小填充）,
     * RendererCommon.ScalingType.SCALE_ASPECT_FILL（根据图像大小填充）,
     * RendererCommon.ScalingType.SCALE_ASPECT_BALANCED（平衡FIT和FILL）
     *
     * @param strRtcPeerId RTC连接id
     * @param scalingType  RendererCommon.ScalingType 枚举下的有一个属性
     * @return
     */
    //添加其他人窗口渲染器
    //参数：String strPeerId ：RTC服务生成的标识Id 。(用于标识与会者，每次加入会议随机生成)
    //返回值：VideoRenderer render
    //说明：该方法返回一个视频渲染对象 VideoRenderer，用于其他人加入会议回调中。此时应调用会议对象中的设置其他与会者视频窗口方法。
    //setRTCVideoRender(strRtcPeerId, render.GetRenderPointer())
    public VideoRenderer OnRtcOpenRemoteRender(String strRtcPeerId, RendererCommon.ScalingType scalingType);

    /**
     * Close  sub  Renderer
     *
     * @param strRtcPeerId RTC连接id
     */
    //移除他人窗口渲染器
    //参数：String strPeerId ：RTC服务生成的标识Id 。(用于标识与会者，每次加入会议随机生成)
    //说明：移除其他人窗口：mVideoView.OnRtcRemoveRemoteRender(strPeerId)
    public void OnRtcRemoveRemoteRender(String strRtcPeerId);
}
